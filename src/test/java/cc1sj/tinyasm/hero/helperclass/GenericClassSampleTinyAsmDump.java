package cc1sj.tinyasm.hero.helperclass;
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
import cc1sj.tinyasm.hero.helperclass.GenericClass;
import cc1sj.tinyasm.hero.helperclass.PojoClassSample;
import java.lang.Object;
@SuppressWarnings("unused")
public class GenericClassSampleTinyAsmDump {

	public static byte[] dump () throws Exception {
		return new GenericClassSampleTinyAsmDump().dump("cc1sj.tinyasm.hero.helperclass.GenericClassSample");
	}

	public byte[] dump(String className) throws Exception {
		ClassBody classBody = ClassBuilder.make(className, Clazz.of(GenericClass.class,Clazz.of(PojoClassSample.class)))
			.access(ACC_PUBLIC | ACC_SUPER).body();

		__init_(classBody);
		_getT(classBody);
		_setT(classBody);
		_bridge_setT(classBody);
		_bridge_getT(classBody);

		return classBody.end().toByteArray();
	}

	protected void __init_(ClassBody classBody) {
		MethodCode code = classBody.publicMethod("<init>").begin();

		code.LINE();
		code.LOAD("this");
		code.SPECIAL(GenericClass.class, "<init>").INVOKE();
		code.RETURN();

		code.END();
	}

	protected void _getT(ClassBody classBody) {
		MethodCode code = classBody.publicMethod(PojoClassSample.class, "getT").begin();

		code.LINE();
		code.LOAD("this");
		code.SPECIAL(GenericClass.class, "getT")
			.reTurn(Object.class).INVOKE();
		code.CHECKCAST(PojoClassSample.class);
		code.RETURNTop();

		code.END();
	}

	protected void _setT(ClassBody classBody) {
		MethodCode code = classBody.publicMethod("setT")
			.parameter("t",PojoClassSample.class).begin();

		code.LINE();
		code.LOAD("this");
		code.LOAD("t");
		code.SPECIAL(GenericClass.class, "setT")
			.parameter(Object.class).INVOKE();

		code.LINE();
		code.RETURN();

		code.END();
	}

	protected void _bridge_setT(ClassBody classBody) {
		MethodCode code = classBody.method(ACC_PUBLIC | ACC_BRIDGE | ACC_SYNTHETIC, "setT")
			.parameter("var1",Object.class).begin();

		code.LINE();
		code.LOAD("this");
		code.LOAD("var1");
		code.CHECKCAST(PojoClassSample.class);
		code.VIRTUAL("setT")
			.parameter(PojoClassSample.class).INVOKE();
		code.RETURN();

		code.END();
	}

	protected void _bridge_getT(ClassBody classBody) {
		MethodCode code = classBody.method(ACC_PUBLIC | ACC_BRIDGE | ACC_SYNTHETIC, Object.class, "getT").begin();

		code.LINE();
		code.LOAD("this");
		code.VIRTUAL("getT")
			.reTurn(PojoClassSample.class).INVOKE();
		code.RETURNTop();

		code.END();
	}

}
