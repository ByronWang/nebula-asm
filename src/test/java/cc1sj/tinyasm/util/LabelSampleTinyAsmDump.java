package cc1sj.tinyasm.util;
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
import java.io.PrintStream;
import java.lang.System;
import java.lang.Object;
import java.lang.String;
@SuppressWarnings("unused")
public class LabelSampleTinyAsmDump {

	public static byte[] dump () throws Exception {
		return new LabelSampleTinyAsmDump().dump("cc1sj.tinyasm.util.LabelSample");
	}

	public byte[] dump(String className) throws Exception {
		ClassBody classBody = ClassBuilder.class_(className)
			.access(ACC_PUBLIC | ACC_SUPER).body();

		__init_(classBody);
		_t(classBody);

		return classBody.end().toByteArray();
	}

	protected void __init_(ClassBody classBody) {
		MethodCode code = classBody.public_().method("<init>").begin();

		code.LINE();
		code.LOAD("this");
		code.SPECIAL(Object.class, "<init>").INVOKE();
		code.RETURN();

		code.END();
	}

	protected void _t(ClassBody classBody) {
		MethodCode code = classBody.public_().method("t").begin();

		code.LINE();
		code.LOADConst(10);
		code.STORE("i",int.class);

		code.LINE();
		code.LOAD("i");
		code.LOADConst(1);
		Label label2OfIF_ICMPNE = new Label();
		code.IF_ICMPNE(label2OfIF_ICMPNE);

		code.LINE();
		code.IINC("i", 1);

		code.visitLabel(label2OfIF_ICMPNE);

		code.LINE();
		code.GETSTATIC(System.class, "out", PrintStream.class);
		code.LOADConst("ddd");
		code.VIRTUAL(PrintStream.class, "println")
			.parameter(String.class).INVOKE();

		code.LINE();
		code.RETURN();

		code.END();
	}

}
