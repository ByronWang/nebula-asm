package cc1sj.tinyasm.hero.advasmproxy;

import org.objectweb.asm.Label;
import org.objectweb.asm.Handle;
import org.objectweb.asm.Opcodes;
import cc1sj.tinyasm.ClassBody;
import cc1sj.tinyasm.ClassBuilder;
import cc1sj.tinyasm.MethodCode;
import org.objectweb.asm.Type;
import static org.objectweb.asm.Opcodes.*;
import cc1sj.tinyasm.Annotation;
import cc1sj.tinyasm.Clazz;
import cc1sj.tinyasm.hero.helperclass.ReferSimplePojoClassSample;
import java.lang.ThreadLocal;
import cc1sj.tinyasm.AdvContext;
import java.lang.Exception;
import cc1sj.tinyasm.hero.helperclass.SimplePojoClassSample;
import cc1sj.tinyasm.MethodCode;
import cc1sj.tinyasm.MethodCaller;
import cc1sj.tinyasm.AdvRuntimeReferNameObject;
import cc1sj.tinyasm.Adv;
import java.lang.Object;
import java.lang.Class;
import cc1sj.tinyasm.ConsumerWithException;
import java.lang.String;

@SuppressWarnings("unused")
public class ReferSimplePojoClassSampleAdvAsmProxyTinyAsmDump {

	public static byte[] dump() throws Exception {
		return new ReferSimplePojoClassSampleAdvAsmProxyTinyAsmDump()
				.dump("cc1sj.tinyasm.hero.advasmproxy.ReferSimplePojoClassSampleAdvAsmProxy");
	}

	public byte[] dump(String className) throws Exception {
		ClassBody classBody = ClassBuilder.class_(className, ReferSimplePojoClassSample.class, AdvRuntimeReferNameObject.class)
				.access(ACC_PUBLIC | ACC_SUPER).body();

		classBody.referInnerClass(ACC_PUBLIC | ACC_FINAL | ACC_STATIC, "java.lang.invoke.MethodHandles", "Lookup");

		classBody.private_().field("_magicNumber", Clazz.of(byte.class));
		classBody.private_().field("_contextThreadLocal", Clazz.of(ThreadLocal.class, Clazz.of(AdvContext.class)));
		__init_(classBody);
		_get__MagicNumber(classBody);
		_set__MagicNumber(classBody);
		_set__Context(classBody);
		_getSimplePojoClassSample(classBody);
		_lambda$getSimplePojoClassSample$0(classBody);

		return classBody.end().toByteArray();
	}

	protected void __init_(ClassBody classBody) {
		MethodCode code = classBody.public_().method("<init>").begin();

		code.LINE();
		code.LOAD("this");
		code.SPECIAL(ReferSimplePojoClassSample.class, "<init>").INVOKE();
		code.RETURN();

		code.END();
	}

	protected void _get__MagicNumber(ClassBody classBody) {
		MethodCode code = classBody.public_().method("get__MagicNumber").return_(byte.class).begin();

		code.LINE();
		code.LOAD("this");
		code.GETFIELD_OF_THIS("_magicNumber");
		code.RETURNTop();

		code.END();
	}

	protected void _set__MagicNumber(ClassBody classBody) {
		MethodCode code = classBody.public_().method("set__MagicNumber").parameter("_magicNumber", byte.class).begin();

		code.LINE();
		code.LOAD("this");
		code.LOAD("_magicNumber");
		code.PUTFIELD_OF_THIS("_magicNumber");

		code.LINE();
		code.RETURN();

		code.END();
	}

	protected void _set__Context(ClassBody classBody) {
		MethodCode code = classBody.public_().method("set__Context")
				.parameter("_contextThreadLocal", Clazz.of(ThreadLocal.class, Clazz.of(AdvContext.class)))
				.parameter("_magicNumber", byte.class).begin();

		code.LINE();
		code.LOAD("this");
		code.LOAD("_contextThreadLocal");
		code.PUTFIELD_OF_THIS("_contextThreadLocal");

		code.LINE();
		code.LOAD("this");
		code.LOAD("_magicNumber");
		code.PUTFIELD_OF_THIS("_magicNumber");

		code.LINE();
		code.RETURN();

		code.END();
	}

	protected void _getSimplePojoClassSample(ClassBody classBody) {
		MethodCode code = classBody.public_().method("getSimplePojoClassSample").return_(SimplePojoClassSample.class).begin();

		code.LINE();
		code.LOAD("this");
		code.GETFIELD_OF_THIS("_contextThreadLocal");
		code.VIRTUAL(ThreadLocal.class, "get").return_(Object.class).INVOKE();
		code.CHECKCAST(AdvContext.class);
		code.STORE("context", AdvContext.class);

		code.LINE();
		code.LOAD("context");
		code.LOAD("this");
		code.VIRTUAL(AdvContext.class, "resolve").return_(ConsumerWithException.class).parameter(Object.class).INVOKE();
		code.STORE("objEval", Clazz.of(ConsumerWithException.class, Clazz.of(MethodCode.class)));

		code.LINE();
		code.LOAD("context");
		code.LOADConst(Type.getType("Lcc1sj/tinyasm/hero/helperclass/SimplePojoClassSample;"));
		code.LOAD("objEval");
		code.visitInvokeDynamicInsn("accept", "(Lcc1sj/tinyasm/ConsumerWithException;)Lcc1sj/tinyasm/ConsumerWithException;", new Handle(
				Opcodes.H_INVOKESTATIC, "java/lang/invoke/LambdaMetafactory", "metafactory",
				"(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;",
				false),
				new Object[] { Type.getType("(Ljava/lang/Object;)V"),
						new Handle(Opcodes.H_INVOKESTATIC, "cc1sj/tinyasm/hero/advasmproxy/ReferSimplePojoClassSampleAdvAsmProxy",
								"lambda$getSimplePojoClassSample$0", "(Lcc1sj/tinyasm/ConsumerWithException;Lcc1sj/tinyasm/MethodCode;)V",
								false),
						Type.getType("(Lcc1sj/tinyasm/MethodCode;)V") });
		code.VIRTUAL(AdvContext.class, "push").return_(byte.class).parameter(Class.class).parameter(ConsumerWithException.class).INVOKE();
		code.STORE("codeIndex", byte.class);

		code.LINE();
		code.LOADConst(80);
		code.LOAD("codeIndex");
		code.ADD();
		code.CONVERTTO(byte.class);
		code.STORE("magicNumber", byte.class);

		code.LINE();
		code.LOADConst(Type.getType("Lcc1sj/tinyasm/hero/helperclass/SimplePojoClassSample;"));
		code.STATIC(Adv.class, "canProxy").return_(boolean.class).parameter(Class.class).INVOKE();
		Label label5OfIFEQ = new Label();
		code.IFEQ(label5OfIFEQ);

		code.LINE();
		code.LOADConst(Type.getType("Lcc1sj/tinyasm/hero/helperclass/SimplePojoClassSample;"));
		code.LOAD("magicNumber");
		code.STATIC(Adv.class, "buildProxyClass").return_(Object.class).parameter(Class.class).parameter(byte.class).INVOKE();
		code.CHECKCAST(SimplePojoClassSample.class);
		code.RETURNTop();

		code.visitLabel(label5OfIFEQ);

		code.LINE();
		code.LOADConstNULL();
		code.RETURNTop();

		code.END();
	}

	protected void _lambda$getSimplePojoClassSample$0(ClassBody classBody) {
		MethodCode code = classBody.staticMethod(ACC_PRIVATE | ACC_STATIC | ACC_SYNTHETIC, "lambda$getSimplePojoClassSample$0")
				.throws_(Exception.class).parameter("objEval", ConsumerWithException.class).parameter("c", MethodCode.class).begin();

		code.LINE();
		code.LOAD("objEval");
		code.LOAD("c");
		code.INTERFACE(ConsumerWithException.class, "accept").parameter(Object.class).INVOKE();

		code.LINE();
		code.LOAD("c");
		code.LOADConst(Type.getType("Lcc1sj/tinyasm/hero/helperclass/ReferSimplePojoClassSample;"));
		code.LOADConst("getSimplePojoClassSample");
		code.VIRTUAL(MethodCode.class, "VIRTUAL").return_(MethodCaller.class).parameter(Class.class).parameter(String.class).INVOKE();
		code.LOADConst(Type.getType("Lcc1sj/tinyasm/hero/helperclass/SimplePojoClassSample;"));
		code.INTERFACE(MethodCaller.class, "reTurn").return_(MethodCaller.class).parameter(Class.class).INVOKE();
		code.INTERFACE(MethodCaller.class, "INVOKE").INVOKE();

		code.LINE();
		code.RETURN();

		code.END();
	}

}
