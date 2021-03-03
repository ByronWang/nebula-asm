package cc1sj.tinyasm;
import org.objectweb.asm.Label;
import cc1sj.tinyasm.ClassBody;
import cc1sj.tinyasm.ClassBuilder;
import cc1sj.tinyasm.MethodCode;
import org.objectweb.asm.Type;
import static org.objectweb.asm.Opcodes.*;
import cc1sj.tinyasm.Annotation;
import cc1sj.tinyasm.Clazz;
import java.lang.Object;
@SuppressWarnings("unused")
public class TinyAsmBuilder_ifTinyAsmDump {

	public static byte[] dump () throws Exception {
		return new TinyAsmBuilder_ifTinyAsmDump().dump("cc1sj.tinyasm.TinyAsmBuilder_if");
	}

	public byte[] dump(String className) throws Exception {
		ClassBody classBody = ClassBuilder.make(className)
			.access(ACC_PUBLIC | ACC_SUPER).body();

		__init_(classBody);
		_testIfThen(classBody);
		_testIfThenElse(classBody);

		return classBody.end().toByteArray();
	}

	protected void __init_(ClassBody classBody) {
		MethodCode code = classBody.method("<init>").begin();

		code.LINE(3);
		code.LOAD("this");
		code.SPECIAL(Object.class, "<init>").INVOKE();
		code.RETURN();

		code.END();
	}

	protected void _testIfThen(ClassBody classBody) {
		MethodCode code = classBody.method("testIfThen").begin();

		code.LINE(6);
		code.LOADConst(20);
		code.STORE("iGT",int.class);

		code.LINE(7);
		code.LOAD("iGT");
		code.LOADConst(10);
		Label label2OfIF_ICMPLE = new Label();
		code.IF_ICMPLE(label2OfIF_ICMPLE);

		code.LINE(8);
		code.IINC("iGT", -1);

		code.visitLabel(label2OfIF_ICMPLE);

		code.LINE(11);
		code.LOADConst(20);
		code.STORE("iGE",int.class);

		code.LINE(12);
		code.LOAD("iGE");
		code.LOADConst(10);
		Label label5OfIF_ICMPLT = new Label();
		code.IF_ICMPLT(label5OfIF_ICMPLT);

		code.LINE(13);
		code.IINC("iGE", -1);

		code.visitLabel(label5OfIF_ICMPLT);

		code.LINE(16);
		code.LOADConst(10);
		code.STORE("iEQ",int.class);

		code.LINE(17);
		code.LOAD("iEQ");
		code.LOADConst(10);
		Label label8OfIF_ICMPNE = new Label();
		code.IF_ICMPNE(label8OfIF_ICMPNE);

		code.LINE(18);
		code.IINC("iEQ", 1);

		code.visitLabel(label8OfIF_ICMPNE);

		code.LINE(21);
		code.LOADConst(10);
		code.STORE("iNE",int.class);

		code.LINE(22);
		code.LOAD("iNE");
		code.LOADConst(10);
		Label label11OfIF_ICMPEQ = new Label();
		code.IF_ICMPEQ(label11OfIF_ICMPEQ);

		code.LINE(23);
		code.IINC("iNE", 1);

		code.visitLabel(label11OfIF_ICMPEQ);

		code.LINE(26);
		code.LOADConst(0);
		code.STORE("iLE",int.class);

		code.LINE(27);
		code.LOAD("iLE");
		code.LOADConst(10);
		Label label14OfIF_ICMPGT = new Label();
		code.IF_ICMPGT(label14OfIF_ICMPGT);

		code.LINE(28);
		code.IINC("iLE", 1);

		code.visitLabel(label14OfIF_ICMPGT);

		code.LINE(31);
		code.LOADConst(0);
		code.STORE("iLT",int.class);

		code.LINE(32);
		code.LOAD("iLT");
		code.LOADConst(10);
		Label label17OfIF_ICMPGE = new Label();
		code.IF_ICMPGE(label17OfIF_ICMPGE);

		code.LINE(33);
		code.IINC("iLT", 1);

		code.visitLabel(label17OfIF_ICMPGE);

		code.LINE(35);
		code.RETURN();

		code.END();
	}

	protected void _testIfThenElse(ClassBody classBody) {
		MethodCode code = classBody.method("testIfThenElse").begin();

		code.LINE(38);
		code.LOADConst(20);
		code.STORE("iGT",int.class);

		code.LINE(39);
		code.LOAD("iGT");
		code.LOADConst(10);
		Label label2OfIF_ICMPLE = new Label();
		code.IF_ICMPLE(label2OfIF_ICMPLE);

		code.LINE(40);
		code.IINC("iGT", -1);
		Label label4OfGOTO = new Label();
		code.GOTO(label4OfGOTO);

		code.visitLabel(label2OfIF_ICMPLE);

		code.LINE(42);
		code.IINC("iGT", 1);

		code.visitLabel(label4OfGOTO);

		code.LINE(45);
		code.LOADConst(20);
		code.STORE("iGE",int.class);

		code.LINE(46);
		code.LOAD("iGE");
		code.LOADConst(10);
		Label label6OfIF_ICMPLT = new Label();
		code.IF_ICMPLT(label6OfIF_ICMPLT);

		code.LINE(47);
		code.IINC("iGE", -1);
		Label label8OfGOTO = new Label();
		code.GOTO(label8OfGOTO);

		code.visitLabel(label6OfIF_ICMPLT);

		code.LINE(49);
		code.IINC("iGE", 1);

		code.visitLabel(label8OfGOTO);

		code.LINE(52);
		code.LOADConst(10);
		code.STORE("iEQ",int.class);

		code.LINE(53);
		code.LOAD("iEQ");
		code.LOADConst(10);
		Label label10OfIF_ICMPNE = new Label();
		code.IF_ICMPNE(label10OfIF_ICMPNE);

		code.LINE(54);
		code.IINC("iEQ", 1);
		Label label12OfGOTO = new Label();
		code.GOTO(label12OfGOTO);

		code.visitLabel(label10OfIF_ICMPNE);

		code.LINE(56);
		code.IINC("iEQ", 1);

		code.visitLabel(label12OfGOTO);

		code.LINE(59);
		code.LOADConst(10);
		code.STORE("iNE",int.class);

		code.LINE(60);
		code.LOAD("iNE");
		code.LOADConst(10);
		Label label14OfIF_ICMPEQ = new Label();
		code.IF_ICMPEQ(label14OfIF_ICMPEQ);

		code.LINE(61);
		code.IINC("iNE", 1);
		Label label16OfGOTO = new Label();
		code.GOTO(label16OfGOTO);

		code.visitLabel(label14OfIF_ICMPEQ);

		code.LINE(63);
		code.IINC("iNE", -1);

		code.visitLabel(label16OfGOTO);

		code.LINE(66);
		code.LOADConst(0);
		code.STORE("iLE",int.class);

		code.LINE(67);
		code.LOAD("iLE");
		code.LOADConst(10);
		Label label18OfIF_ICMPGT = new Label();
		code.IF_ICMPGT(label18OfIF_ICMPGT);

		code.LINE(68);
		code.IINC("iLE", 1);
		Label label20OfGOTO = new Label();
		code.GOTO(label20OfGOTO);

		code.visitLabel(label18OfIF_ICMPGT);

		code.LINE(70);
		code.IINC("iLE", 1);

		code.visitLabel(label20OfGOTO);

		code.LINE(73);
		code.LOADConst(0);
		code.STORE("iLT",int.class);

		code.LINE(74);
		code.LOAD("iLT");
		code.LOADConst(10);
		Label label22OfIF_ICMPGE = new Label();
		code.IF_ICMPGE(label22OfIF_ICMPGE);

		code.LINE(75);
		code.IINC("iLT", 1);
		Label label24OfGOTO = new Label();
		code.GOTO(label24OfGOTO);

		code.visitLabel(label22OfIF_ICMPGE);

		code.LINE(77);
		code.IINC("iLT", 1);

		code.visitLabel(label24OfGOTO);

		code.LINE(79);
		code.RETURN();

		code.END();
	}

}