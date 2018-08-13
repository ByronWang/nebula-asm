package nebula.tinyasm;

import static nebula.tinyasm.util.TypeUtils.is;
import static org.objectweb.asm.Opcodes.ACC_SYNTHETIC;
import static org.objectweb.asm.Opcodes.ASM5;
import static org.objectweb.asm.Opcodes.BIPUSH;
import static org.objectweb.asm.Opcodes.ICONST_0;
import static org.objectweb.asm.Opcodes.INVOKEINTERFACE;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.function.Consumer;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

import nebula.tinyasm.api.MethodCode;
import nebula.tinyasm.api.MethodHeader;
import nebula.tinyasm.data.ClassAnnotation;
import nebula.tinyasm.data.ClassField;
import nebula.tinyasm.data.LocalsStack;
import nebula.tinyasm.data.LocalsVariable;

abstract class MethodBuilder<H, C extends MethodCode< C>> extends MethodVisitor
		implements MethodCode<C>, MethodHeader<C> {

//	abstract class AbstractMethodCaller extends AbstractInvokeMethod<M, C> implements MethodCaller<M, C> {
//
//		Type objectType;
//
//		public AbstractMethodCaller(Type objectType) {
//			this.objectType = objectType;
//		}
//
//		@Override
//		public M addParam(String name) {
//			LOAD(name);
//			return caller();
//		}
//
//		abstract M caller();
//
//		@Override
//		public C code() {
//			return AbstractMethodBuilder.this.code();
//		}
//
//		@Override
//		public Type getStackTopType() {
//			return objectType;
//		}
//
//		@Override
//		public M with(Consumer<C> invocation) {
//			invocation.accept(code());
//			return caller();
//		}
//	}

	class ThisMethod {
		int access;
		List<ClassAnnotation> annotations = new ArrayList<>();
		// final Type thisType;
		String[] excptions;

		String name;

		List<ClassAnnotation> parameterAnnotations = new ArrayList<>(10);

		List<ClassField> params = new ArrayList<>();

		Type returnType;
		Type type;
		boolean hasEnded = false;
	}

	public static void visitParameterAnnotation(MethodVisitor mv, int parameter, Type annotationType, Object value) {
		AnnotationVisitor av0 = mv.visitParameterAnnotation(parameter, annotationType.getDescriptor(), true);
		if (value != null) {
			AnnotationVisitor av1 = av0.visitArray("value");
			av1.visit(null, value);
			av1.visitEnd();
		}
		av0.visitEnd();
	}

	ThisMethod thisMethod;

	private final ClassVisitor classVisitor;

	protected Label labelCurrent;

	boolean labelHasDefineBegin = false;

	Type stackTopType;

	protected LocalsStack locals = new LocalsStack();

	final Stack<Type> stack = new Stack<>();

	int lastLineNumber = 0;

	public MethodBuilder(ClassVisitor cv, Type thisType, int access, Type returnType, String methodName,
			String[] exceptiones) {
		super(ASM5);
		this.classVisitor = cv;
		thisMethod = new ThisMethod();
		thisMethod.name = methodName;
		thisMethod.access = access;
		thisMethod.returnType = returnType;
		thisMethod.excptions = exceptiones;
		thisMethod.type = thisType;
	}

	@Override
	public MethodHeader<C> annotation(Type type, Object value) {
		thisMethod.annotations.add(new ClassAnnotation(type, null, value));
		return this;
	}

	@Override
	public MethodHeader<C> annotation(Type type, String name, Object value) {
		thisMethod.annotations.add(new ClassAnnotation(type, name, value));
		return this;
	}

	@Override
	public C block(Consumer<C> invocation) {
		invocation.accept(code());
		return code();
	}

	@Override
	public void code(Consumer<C> invocation) {
		invocation.accept(this.codeBegin());
		this.end();
	}

	@Override
	public C codeAccessLabel(Label label) {
		labelCurrent = label;
		mv.visitLabel(label);
		return code();
	}

	@Override
	public C codeAccessLabel(Label label, int line) {
		labelCurrent = label;
		mv.visitLabel(label);
		mv.visitLineNumber(line, label);
		return code();
	}

	@Override
	public C codeBegin() {
		makeMethodDefine();
		makeMethodBegin();
		return code();
	}

	@Override
	public Type codeGetStack(int i) {
		return stack.get(stack.size() - i - 1);
	}

	@Override
	public int codeLocalLoadAccess(String name) {
		return locals.accessLoad(name, labelCurrent).locals;
	}

	@Override
	public Type codeLocalLoadAccessType(String name) {
		return locals.accessLoad(name, labelCurrent).type;
	}

	@Override
	public int codeLocalStoreAccess(String name) {
		return locals.accessStore(name, labelCurrent).locals;
	}

	@Override
	public Type codeLocalStoreAccessType(String name) {
		return locals.accessStore(name, labelCurrent).type;
	}

	@Override
	public Label codeNewLabel() {
		Label label = new Label();
		return label;
	}

	@Override
	public Type codePopStack() {
		Type type = stack.pop();
		printStack(stack);
		return type;
	}

	@Override
	public void codePush(Type type) {
		stack.push(type);
		printStack(stack);
	}

	@Override
	public C def(String name, Type type, String signature) {
		locals.push(new LocalsVariable(name, type, signature));
//		recomputerLocals();
		return code();
	}

	@Override
	public void end() {
		makeMethodEnd();
	}

	protected Type getStackTopType() {
		return stackTopType;
	}

	protected Label labelWithoutLineNumber() {
		Label label = new Label();
		labelCurrent = label;
		mv.visitLabel(label);
		return label;
	}

	public C line() {
		Label label;
		if (!labelHasDefineBegin) {
			label = new Label();
			labelCurrent = label;
			mv.visitLabel(label);
		} else {
			label = labelCurrent;
		}
		lastLineNumber = lastLineNumber + 1;
		mv.visitLineNumber(lastLineNumber, label);
		return code();
	}

	public C line(int line) {
		Label label;
		if (!labelHasDefineBegin) {
			label = new Label();
			labelCurrent = label;
			mv.visitLabel(label);
		} else {
			label = labelCurrent;
		}
		lastLineNumber = line;
		mv.visitLineNumber(line, label);
		return code();
	}

	protected C makeMethodBegin() {
		mv.visitCode();

		labelCurrent = labelWithoutLineNumber();
		// TODO add class sign
		for (ClassField field : thisMethod.params) {
			locals.push(new LocalsVariable(field, labelCurrent));
		}
//		recomputerLocals();

		return code();
	}

	protected void makeMethodDefine() {
		String signature = null;
		boolean definedSignature = false;
		{
			StringBuilder sb = new StringBuilder();
			sb.append("(");
			for (ClassField param : thisMethod.params) {
				if (param.signature != null) {
					sb.append(param.signature);
					definedSignature = true;
				} else {
					sb.append(param.type.getDescriptor());
				}
			}
			sb.append(")");
			sb.append(thisMethod.returnType.getDescriptor());
			String signatureFromParameter = sb.toString();

			if (definedSignature) {
				signature = signatureFromParameter;
			}
		}

		this.mv = classVisitor.visitMethod(thisMethod.access, thisMethod.name,
				Type.getMethodDescriptor(thisMethod.returnType, ClassField.typesOf(thisMethod.params)), signature,
				null);

		assert this.mv != null;
		for (ClassAnnotation annotation : thisMethod.annotations) {
			mvAnnotation(this.mv, annotation.type, annotation.name, annotation.value);
		}
		for (ClassAnnotation annotation : thisMethod.parameterAnnotations) {
			if (annotation != null) {
				visitParameterAnnotation(this.mv, annotation.parameter, annotation.type, annotation.value);
			}
		}
	}

	public void makeMethodEnd() {
		if (thisMethod.hasEnded) return;
		Label endLabel = this.labelWithoutLineNumber();
		for (LocalsVariable var : locals) {
			if (!is(var.access, ACC_SYNTHETIC)) {
				assert mv != null;
				assert var != null;
				assert var.type.getDescriptor() != null;
				mv.visitLocalVariable(var.name, var.type.getDescriptor(), var.signature, var.startFrom, endLabel,
						var.locals);
			}
		}
		mv.visitMaxs(0, 0);
		mv.visitEnd();
		thisMethod.hasEnded = true;
	}

	public void mvAnnotation(MethodVisitor mv, Type annotationType, String name, Object value) {
		AnnotationVisitor av0 = mv.visitAnnotation(annotationType.getDescriptor(), true);
		if (value != null) {
			if (name != null) {
				av0.visit(name, value);
			} else {
				av0.visit("value", value);
			}
		}
		av0.visitEnd();
	}

	@Override
	public void mvFieldInsn(int opcode, Type ownerType, String fieldName, Type fieldType) {
		mv.visitFieldInsn(opcode, ownerType.getInternalName(), fieldName, fieldType.getDescriptor());
	}

	@Override
	public void mvInst(int opcode) {
		mv.visitInsn(opcode);
	}

	@Override
	public void mvInst(int opcode, int var) {
		mv.visitVarInsn(opcode, var);
	}

	@Override
	public void mvIntInsn(int opcode, int operand) {
		if (opcode == BIPUSH && -1 <= operand && operand <= 5) {
			mv.visitInsn(ICONST_0 + operand);
		} else {
			mv.visitIntInsn(opcode, operand);
		}
	}

	@Override
	public void mvInvoke(int opcode, Type objectType, Type returnType, String methodName, Type... paramTypes) {
		mv.visitMethodInsn(opcode, objectType.getInternalName(), methodName,
				Type.getMethodDescriptor(returnType, paramTypes), opcode == INVOKEINTERFACE);

	}

	@Override
	public void mvJumpInsn(int opcode, Label label) {
		mv.visitJumpInsn(opcode, label);
	}

	@Override
	public void mvLdcInsn(Object cst) {
		mv.visitLdcInsn(cst);
	}

	@Override
	public void mvTypeInsn(int opcode, Type type) {
		mv.visitTypeInsn(opcode, type.getInternalName());
	}

	@Override
	public MethodHeader<C> parameter(ClassField field) {
		thisMethod.params.add(field);
		thisMethod.parameterAnnotations.add(null);
		return this;
	}

	@Override
	public MethodHeader<C> parameter(String fieldName, Type fieldType) {
		thisMethod.params.add(new LocalsVariable(fieldName, fieldType));
		thisMethod.parameterAnnotations.add(null);
		return this;
	}

	@Override
	public MethodHeader<C> parameterAnnotation(Type annotationType, Object value) {
		thisMethod.parameterAnnotations.set(thisMethod.params.size() - 1,
				new ClassAnnotation(annotationType, null, value));
		return this;
	}

	@Override
	public MethodHeader<C> parameterGeneric(String fieldName, Type fieldType, String signature) {
		thisMethod.params.add(new LocalsVariable(fieldName, fieldType, signature));
		thisMethod.parameterAnnotations.add(null);
		return this;
	}

	@Override
	public MethodHeader<C> parameterGenericWithAnnotation(Type annotationType, Object value, String fieldName,
			Type fieldType, String signature) {
		thisMethod.params.add(new LocalsVariable(fieldName, fieldType, signature));
		thisMethod.parameterAnnotations.set(thisMethod.params.size() - 1,
				new ClassAnnotation(annotationType, null, value));
		return this;
	}

	@Override
	public MethodHeader<C> parameterWithAnnotation(Type annotationType, Object value, String fieldName,
			Type fieldType) {
		thisMethod.params.add(new LocalsVariable(fieldName, fieldType));
		thisMethod.parameterAnnotations.set(thisMethod.params.size() - 1,
				new ClassAnnotation(annotationType, null, value));
		return this;
	}

	private void printStack(Stack<Type> stack) {
		StringBuffer sb = new StringBuffer();
		sb.append(thisMethod.name).append(" : ");
		for (Type type : stack) {
			sb.append(type);
			sb.append(" > ");
		}
		sb.setCharAt(sb.length() - 2, '\n');
		System.out.println(sb.toString());
	}

}