package cc1sj.tinyasm;

import static cc1sj.tinyasm.Adv.MAGIC_CODES_NUMBER;
import static cc1sj.tinyasm.Adv.MAGIC_CODES_String;
import static org.objectweb.asm.Opcodes.ACC_BRIDGE;
import static org.objectweb.asm.Opcodes.ACC_FINAL;
import static org.objectweb.asm.Opcodes.ACC_NATIVE;
import static org.objectweb.asm.Opcodes.ACC_PRIVATE;
import static org.objectweb.asm.Opcodes.ACC_PUBLIC;
import static org.objectweb.asm.Opcodes.ACC_STATIC;
import static org.objectweb.asm.Opcodes.ACC_SYNTHETIC;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Handle;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.ModuleVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.TypePath;

class AdvAsmProxyForClassAsmBuilder extends ClassVisitor {

	public static byte[] dump2(Class<?> target, String proxyClassName) throws Exception {
		ClassReader cr = new ClassReader(target.getName());
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);

		AdvAsmProxyForClassAsmBuilder bw;
//		target.getConstructor();
		bw = new AdvAsmProxyForClassAsmBuilder(Opcodes.ASM9, cw, Type.getType(target).getInternalName(), proxyClassName);
		cr.accept(bw, ClassReader.SKIP_CODE);

		Class<?> superClass = target.getSuperclass();
		while (superClass != null && superClass != Object.class) {
			cr = new ClassReader(superClass.getName());
			cr.accept(bw, ClassReader.SKIP_CODE);

			superClass = superClass.getSuperclass();
		}

		bw.finish();

		return cw.toByteArray();
	}

	ClassBody classBody;
	String proxyClassName;
	Type targetType;
	Type objectType;

	public AdvAsmProxyForClassAsmBuilder(int api, String targetName, String proxyClassName) {
		super(api);
		this.proxyClassName = proxyClassName;
		dump(targetName, proxyClassName);
	}

	public AdvAsmProxyForClassAsmBuilder(int api, ClassVisitor classVisitor, String targetName, String proxyClassName) {
		super(api, classVisitor);
		this.proxyClassName = proxyClassName;
		this.targetType = Clazz.of(targetName).getType();

		dump(targetName, proxyClassName);
	}

	protected void dump(String targetName, String proxyClassName) {
		targetType = Type.getObjectType(targetName);
		objectType = Type.getObjectType(proxyClassName.replace('.', '/'));
		ClassHeader ch = ClassBuilder.make(cv, proxyClassName);
//		if(superName)
		ch.eXtend(Clazz.of(targetType));
		ch.implement(AdvRuntimeReferNameObject.class);
//		ch.access(access);
		classBody = ch.body();

		classBody.referInnerClass(ACC_PUBLIC | ACC_FINAL | ACC_STATIC, MethodHandles.class.getName(), "Lookup");

		classBody.field("_magicNumber", Clazz.of(byte.class));
		classBody.field("_contextThreadLocal", Clazz.of(ThreadLocal.class, Clazz.of(AdvContext.class)));

		__init_(classBody);
		_get__MagicNumber(classBody);
		_set__MagicNumber(classBody);
		_set__Context(classBody);

	}

	public void finish() {
		for (LambdaBuilder lambdaBuilder : lambdas) {
			lambdaBuilder.exec(classBody);
		}
	}

	protected void __init_(ClassBody classBody) {
		MethodCode code = classBody.method("<init>").begin();

		code.LINE();
		code.LOAD("this");
		code.SPECIAL(Clazz.of(targetType), "<init>").INVOKE();
		code.RETURN();

		code.END();
	}

	protected void _get__MagicNumber(ClassBody classBody) {
		MethodCode code = classBody.method(byte.class, "get__MagicNumber").begin();

		code.LINE();
		code.LOAD("this");
		code.GETFIELD_OF_THIS("_magicNumber");
		code.RETURNTop();

		code.END();
	}

	protected void _set__MagicNumber(ClassBody classBody) {
		MethodCode code = classBody.method("set__MagicNumber").parameter("_magicNumber", byte.class).begin();

		code.LINE();
		code.LOAD("this");
		code.LOAD("_magicNumber");
		code.PUTFIELD_OF_THIS("_magicNumber");

		code.LINE();
		code.RETURN();

		code.END();
	}

	protected void _set__Context(ClassBody classBody) {
		MethodCode code = classBody.method("set__Context").parameter("context", Clazz.of(ThreadLocal.class, Clazz.of(AdvContext.class)))
				.parameter("_magicNumber", byte.class).begin();

		code.LINE();
		code.LOAD("this");
		code.LOAD("context");
		code.PUTFIELD_OF_THIS("_contextThreadLocal");

		code.LINE();
		code.LOAD("this");
		code.LOAD("_magicNumber");
		code.PUTFIELD_OF_THIS("_magicNumber");

		code.LINE();
		code.RETURN();

		code.END();
	}

	@Override
	public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
//		super.visit(version, access, name, signature, superName, interfaces);

	}

	Map<String, String> definedMethodes = new HashMap<>();

	@Override
	public MethodVisitor visitMethod(int access, String methodName, String descriptor, String signature, String[] exceptions) {

		if (methodName.equals("<init>") || methodName.equals("<clinit>") 
				|| (access & (ACC_STATIC | ACC_PRIVATE | ACC_SYNTHETIC | ACC_NATIVE | ACC_BRIDGE)) != 0) {
			return null;
		}
		String referkey = methodName + descriptor + signature;
		if (definedMethodes.containsKey(referkey)) {
			return null;
		}
		definedMethodes.put(referkey, referkey);

//		List<StringBuilder> methodParamClazzes = null;
//		if (signature == null) {
//			if (returnType != Type.VOID_TYPE) {
//				stringBuilder.append(clazzOf(returnType));
//				stringBuilder.append(", ");
//			}
//			appendConstant(name);
//		} else {
//			SignatureReader sr = new SignatureReader(signature);
//			ClassSignature signatureVistor = new ClassSignature(Opcodes.ASM5);
//			sr.accept(signatureVistor);
//			stringBuilder.append(signatureVistor.returnClass.toString());
//			stringBuilder.append(", ");
//			appendConstant(name);
//			methodParamClazzes = signatureVistor.paramsClass;
////			stringBuilder.append(", ");
////			stringBuilder.append(signatureVistor.paramsClass.toString());
//		}

		// Return Type
		Type returnType = Type.getReturnType(descriptor);
		Clazz returnClazz = Clazz.of(returnType);
		// ParamType
		Type[] methodParamTypes = Type.getArgumentTypes(descriptor);

		MethodHeader mh = classBody.method(returnClazz, methodName);
//			mh.access(access);
		for (int i = 0; i < methodParamTypes.length; i++) {
			mh.parameter("param" + i, Clazz.of(methodParamTypes[i]));
		}
		if (exceptions != null) for (String e : exceptions) mh.tHrow(Clazz.of(Type.getObjectType(e)));

		MethodCode code = mh.begin();

		code_getContext(code);
		// resolve this
		code_resolve_this("objEval", code);
		// resolve parameters
		for (int i = 0; i < methodParamTypes.length; i++) {
			code_resolve("eval_param" + i, code, "param" + i, methodParamTypes[i]);
		}

		// LOAD All Parameter
		code.LINE();
		code.LOAD("context");
		code.LOAD("objEval");
		for (int i = 0; i < methodParamTypes.length; i++) {
			code.LOAD("eval_param" + i);
		}

		// invoke method
		String lambdaName = push(1 + methodParamTypes.length, c -> {
			c.LINE();
			c.LOAD("c");
			c.LOADConst(targetType);
			c.LOADConst(methodName);
			c.VIRTUAL(MethodCode.class, "VIRTUAL").reTurn(MethodCaller.class).parameter(Class.class).parameter(String.class).INVOKE();
			for (Type type : methodParamTypes) {
				_type(c, Clazz.of(type));
				c.INTERFACE(MethodCaller.class, "parameter").reTurn(MethodCaller.class).parameter(Class.class).INVOKE();
			}

			if (returnType != Type.VOID_TYPE) {
				_type(c, returnClazz);
				c.INTERFACE(MethodCaller.class, "reTurn").reTurn(MethodCaller.class).parameter(Class.class).INVOKE();
			}

			c.INTERFACE(MethodCaller.class, "INVOKE").INVOKE();
		});

		dynamicInvoke(code, methodParamTypes.length, proxyClassName.replace('.', '/'), lambdaName);

		code.stackPush(Type.getType(ConsumerWithException.class));

		code.VIRTUAL(AdvContext.class, "push").reTurn(byte.class).parameter(ConsumerWithException.class).INVOKE();

		// Refer
		if (returnType != Type.VOID_TYPE) {
			code.STORE("codeIndex", byte.class);

//				code.CONVERTTO(returnClazz);

			if (BoxUnbox.ClazzObjectToPrimitive.containsKey(returnType)) {

				code.LINE();
				code.LOADConst(MAGIC_CODES_NUMBER);
				code.LOAD("codeIndex");
				code.ADD();
				Type primitiveType = BoxUnbox.ClazzObjectToPrimitive.get(returnType);
				code.CONVERTTO(primitiveType);
				BoxUnbox.PrimaryToBoxFunc.get(primitiveType).accept(code);
				code.RETURNTop();
			} else if (BoxUnbox.PrimativeToClazzObject.containsKey(returnType)) {
				code.LINE();
				code.LOADConst(MAGIC_CODES_NUMBER);
				code.LOAD("codeIndex");
				code.ADD();
				code.CONVERTTO(returnType);
				code.RETURNTop();
			} else if (returnType.getSort() == Type.OBJECT && returnType.equals(Type.getType(String.class))) {
				code.LINE();
				code.NEW(StringBuilder.class);
				code.DUP();
				code.LOADConst(MAGIC_CODES_String);
				code.SPECIAL(StringBuilder.class, "<init>").parameter(String.class).INVOKE();
				code.LOAD("codeIndex");
				code.VIRTUAL(StringBuilder.class, "append").reTurn(StringBuilder.class).parameter(int.class).INVOKE();
				code.VIRTUAL(StringBuilder.class, "toString").reTurn(String.class).INVOKE();
				code.RETURNTop();
			}
			code.LINE();
			code.LOAD("context");
			code.VIRTUAL(AdvContext.class, "execAndPop").INVOKE();

		} else {
			code.POP();

			code.LINE();
			code.LOAD("context");
			code.VIRTUAL(AdvContext.class, "execAndPop").INVOKE();

			code.LINE();
			code.RETURN();
		}

		code.END();

		// TODO Auto-generated method stub
		return null;

	}

	@Override
	public void visitSource(String source, String debug) {
//		super.visitSource(source.replaceAll("[.]java", this.suffix + ".java"), debug);
	}

	@Override
	public ModuleVisitor visitModule(String name, int access, String version) {
//		return super.visitModule(name, access, version);
		return null;
	}

	@Override
	public void visitOuterClass(String owner, String name, String descriptor) {
//		super.visitOuterClass(owner, name, descriptor);
	}

	@Override
	public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
//		return super.visitAnnotation(descriptor, visible);
		return null;
	}

	@Override
	public AnnotationVisitor visitTypeAnnotation(int typeRef, TypePath typePath, String descriptor, boolean visible) {
//		return super.visitTypeAnnotation(typeRef, typePath, descriptor, visible);
		return null;
	}

	@Override
	public void visitAttribute(Attribute attribute) {
//		super.visitAttribute(attribute);
	}

	@Override
	public void visitInnerClass(String name, String outerName, String innerName, int access) {
//		super.visitInnerClass(name, outerName, innerName, access);
	}

	@Override
	public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
//		return super.visitField(access, name, descriptor, signature, value);
		return null;
	}

	@Override
	public void visitEnd() {
//		super.visitEnd();
//		int.class.isPrimitive()
	}

	/********************************************************************************************************************************************
	 * 
	 * 
	 * 共同部分代码，简化主函数逻辑
	 * 
	 * 
	 * 
	 ********************************************************************************************************************************************/

	static Map<Type, Type> primitive_BoxedClazz_Maps = asMap(
			new Type[] { Type.BOOLEAN_TYPE/* Boolean.class */, Type.BYTE_TYPE/* Byte.class */, Type.CHAR_TYPE/* Character.class */,
					Type.SHORT_TYPE/* Short.class */, Type.INT_TYPE/* Integer.class */, Type.LONG_TYPE/* Long.class */,
					Type.FLOAT_TYPE/* Float.class */, Type.DOUBLE_TYPE/* Double.class */
			},
			new Type[] { /* boolean.class */Type.getType(Boolean.class), /* byte.class */Type.getType(Byte.class),
					/* char.class */Type.getType(Character.class), /* short.class */Type.getType(Short.class),
					/* int.class */Type.getType(Integer.class), /* long.class */Type.getType(Long.class),
					/* float.class */Type.getType(Float.class), /* double.class */Type.getType(Double.class) });

	static Map<Type, String> primitive_BoxedClassIntValue_Maps = asMap(
			new Type[] { Type.BOOLEAN_TYPE/* Boolean.class */, Type.BYTE_TYPE/* Byte.class */, Type.CHAR_TYPE/* Character.class */,
					Type.SHORT_TYPE/* Short.class */, Type.INT_TYPE/* Integer.class */, Type.LONG_TYPE/* Long.class */,
					Type.FLOAT_TYPE/* Float.class */, Type.DOUBLE_TYPE/* Double.class */
			},
			new String[] { /* boolean.class" */"booleanValue", /* byte.class */ "byteValue", /* char.class */ "charValue",
					/* short.class */ "shortValue", /* int.class */ "intValue", /* long.class */ "longValue",
					/* float.class */ "floatValue", /* double.class */ "doubleValue" });

//	static Map<Type, Class<?>> primitive_ToValueClassMaps = asMap(
//			new Type[] { Type.BOOLEAN_TYPE/* Boolean.class */, Type.BYTE_TYPE/* Byte.class */, Type.CHAR_TYPE/* Character.class */,
//					Type.SHORT_TYPE/* Short.class */, Type.INT_TYPE/* Integer.class */, Type.LONG_TYPE/* Long.class */,
//					Type.FLOAT_TYPE/* Float.class */, Type.DOUBLE_TYPE/* Double.class */
//			},
//			new Class<?>[] { boolean.class /* "booleanValue" */, byte.class /* "byteValue" */, char.class /* "charValue" */,
//					short.class /* "shortValue" */, int.class /* "intValue" */, long.class /* "longValue" */,
//					float.class /* "floatValue" */, double.class /* "doubleValue" */ });

	static <K, V> Map<K, V> asMap(K[] keys, V[] values) {
		Map<K, V> maps = new HashMap<>();
		for (int i = 0; i < keys.length; i++) {
			maps.put(keys[i], values[i]);
		}
		return maps;
	}

	static void _cast(MethodCode code, Clazz returnType) {
		Clazz returnClazz = Clazz.of(returnType);
		final boolean returnValueNeedBoxing = primitive_BoxedClazz_Maps.containsKey(returnType.getType());
		Type returnValueboxedClazz = returnValueNeedBoxing ? primitive_BoxedClazz_Maps.get(returnType.getType()) : null;
		String returnValueUnboxValueMethodName = returnValueNeedBoxing ? primitive_BoxedClassIntValue_Maps.get(returnType.getType()) : null;
		if (returnValueNeedBoxing) {
			code.CHECKCAST(returnValueboxedClazz);
			code.VIRTUAL(Clazz.of(returnValueboxedClazz), returnValueUnboxValueMethodName).reTurn(returnClazz).INVOKE();
		} else {
			code.CHECKCAST(returnClazz);
		}
	}

	static void _type(MethodCode code, Clazz returnClass) {
		final boolean returnValueNeedBoxing = primitive_BoxedClazz_Maps.containsKey(returnClass.getType());
		Type returnValueboxedClazz = returnValueNeedBoxing ? primitive_BoxedClazz_Maps.get(returnClass.getType()) : null;
		if (returnValueNeedBoxing) code.GETSTATIC(returnValueboxedClazz, "TYPE", Type.getType(Class.class));
		else code.LOADConst(returnClass.getType());
	}

	static Map<String, String> mps = asMap(
			new String[] { Boolean.class.getName(), Character.class.getName(), Byte.class.getName(), Short.class.getName(),
					Integer.class.getName(), Long.class.getName(), Float.class.getName(), Double.class.getName(), String.class.getName() },
			new String[] { Boolean.class.getName(), Character.class.getName(), Byte.class.getName(), Short.class.getName(),
					Integer.class.getName(), Long.class.getName(), Float.class.getName(), Double.class.getName(), String.class.getName() });

	protected void code_contextThreadLocal_execAndPop(MethodCode code) {
		code.LINE();
		code.LOAD("context");
		code.VIRTUAL(AdvContext.class, "execAndPop").INVOKE();
	}

	protected Type[] codeTypes(int paramSize, Class<?>... classes) {
		Type[] typesLambda = new Type[paramSize + classes.length];
		for (int i = 0; i < paramSize; i++) {
			typesLambda[i] = Type.getType(ConsumerWithException.class);
		}
		for (int i = 0; i < classes.length; i++) {
			typesLambda[paramSize + i] = Type.getType(classes[i]);
		}
		return typesLambda;
	}

	protected void dynamicInvoke(MethodCode code, int paramSize, String proxyClassName, String lambdaName) {
		String lambdaRealMethodDesriptor = Type.getMethodDescriptor(Type.VOID_TYPE,
				codeTypes(paramSize, ConsumerWithException.class, MethodCode.class));

		String dontKnowByNowMethodDesriptor = Type.getMethodDescriptor(Type.getType(ConsumerWithException.class),
				codeTypes(paramSize, ConsumerWithException.class));
		dynamicInvokeLambda(code, proxyClassName, lambdaName, dontKnowByNowMethodDesriptor, lambdaRealMethodDesriptor);

		code.stackPop();
		for (int i = 0; i < paramSize; i++) {
			code.stackPop();
		}
	}

	protected void code_getContext(MethodCode code) {
		code.LINE();
		code.LOAD("this");
		code.GETFIELD_OF_THIS("_contextThreadLocal");
		code.VIRTUAL(ThreadLocal.class, "get").reTurn(Object.class).INVOKE();
		code.CHECKCAST(AdvContext.class);
		code.STORE("context", AdvContext.class);
	}

	protected void code_resolve_this(String thisBlockName, MethodCode code) {
		code.LINE();
		code.LOAD("context");
		code.LOAD("this");
		code.VIRTUAL(AdvContext.class, "resolve").reTurn(ConsumerWithException.class).parameter(Object.class).INVOKE();
		code.STORE(thisBlockName, Clazz.of(ConsumerWithException.class, Clazz.of(MethodCode.class)));
	}

	protected void code_resolve(String codeBlockName, MethodCode code, String paramName, Type paramClass) {
		code.LINE();
		code.LOAD("context");
		code.LOAD(paramName);
		code.VIRTUAL(AdvContext.class, "resolve").reTurn(ConsumerWithException.class).parameter(Clazz.of(paramClass)).INVOKE();
		code.STORE(codeBlockName, Clazz.of(ConsumerWithException.class, Clazz.of(MethodCode.class)));
	}

	protected void dynamicInvokeLambda(MethodCode code, String objClass, String lambdaName, String dontKnowByNowMethodDesriptor,
			String lambdaRealMethodDesriptor) {

		Type lambdaDefinedMethodDescriptor = Type.getType(Type.getMethodDescriptor(Type.VOID_TYPE, Type.getType(MethodCode.class)));
		code.visitInvokeDynamicInsn("accept", dontKnowByNowMethodDesriptor, new Handle(Opcodes.H_INVOKESTATIC,
				"java/lang/invoke/LambdaMetafactory", "metafactory",
				"(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;",
				false),
				new Object[] { Type.getType("(Ljava/lang/Object;)V"),
						new Handle(Opcodes.H_INVOKESTATIC, objClass, lambdaName, lambdaRealMethodDesriptor, false),
						lambdaDefinedMethodDescriptor });
	}

	List<LambdaBuilder> lambdas = new ArrayList<>();

	public String push(int params, Consumer<MethodCode> lambdaInvokeSuperMethod) {
		String name = "lambda$" + this.lambdas.size();
		lambdas.add(new LambdaBuilder(name, params, lambdaInvokeSuperMethod));
		return name;
	}

	class LambdaBuilder {
		String name;// "lambda$0""lambda$0",
		int params;
		Consumer<MethodCode> lambdaInvokeSuperMethod;

		public void exec(ClassBody classBody) {

			MethodHeader methodHeader = classBody.staticMethod(ACC_PRIVATE | ACC_STATIC | ACC_SYNTHETIC, name).tHrow(Exception.class);
			for (int i = 0; i < params; i++) {
				methodHeader.parameter("var" + i, ConsumerWithException.class);
			}
			methodHeader.parameter("c", MethodCode.class);
			MethodCode code = methodHeader.begin();

			for (int i = 0; i < params; i++) {
				code_param_eval_accept("var" + i, "c", code);
			}

			lambdaInvokeSuperMethod.accept(code);

			code.LINE();
			code.RETURN();
			code.END();
		}

		public LambdaBuilder(String name, int params, Consumer<MethodCode> lambdaInvokeSuperMethod) {
			super();
			this.name = name;
			this.params = params;
			this.lambdaInvokeSuperMethod = lambdaInvokeSuperMethod;
		}
	}

	protected void code_param_eval_accept(String var1, String c, MethodCode code) {
		code.LINE();
		code.LOAD(var1);
		code.LOAD(c);
		code.INTERFACE(ConsumerWithException.class, "accept").parameter(Object.class).INVOKE();
	}
}
