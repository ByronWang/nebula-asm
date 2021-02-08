package nebula.tinyasm;
import org.objectweb.asm.Label;
import nebula.tinyasm.ClassBody;
import nebula.tinyasm.ClassBuilder;
import nebula.tinyasm.MethodCode;
import static org.objectweb.asm.Opcodes.*;
public class MethodASMArraySampleTinyAsmDump {

public static byte[] dump () throws Exception {

ClassBody classWriter = ClassBuilder.make("nebula.tinyasm.MethodASMArraySample").body();

classWriter.field("ba", byte[].class);
classWriter.field("ca", char[].class);
classWriter.field("sa", short[].class);
classWriter.field("ia", int[].class);
classWriter.field("la", long[].class);
classWriter.field("fa", float[].class);
classWriter.field("da", double[].class);
classWriter.field("stra", java.lang.String[].class);
classWriter.method("<init>").code(code -> {

	code.LINE(3);
	code.LOAD("this");
	code.SPECIAL(java.lang.Object.class, "<init>").INVOKE();
	code.RETURN();
});
classWriter.method("init").code(code -> {

	code.LINE(14);
	code.LOAD("this");
	code.LOADConst(10);
	code.NEWARRAY(byte.class);
	code.PUTFIELD("ba", byte[].class);

	code.LINE(15);
	code.LOAD("this");
	code.LOADConst(10);
	code.NEWARRAY(char.class);
	code.PUTFIELD("ca", char[].class);

	code.LINE(16);
	code.LOAD("this");
	code.LOADConst(10);
	code.NEWARRAY(short.class);
	code.PUTFIELD("sa", short[].class);

	code.LINE(17);
	code.LOAD("this");
	code.LOADConst(10);
	code.NEWARRAY(int.class);
	code.PUTFIELD("ia", int[].class);

	code.LINE(18);
	code.LOAD("this");
	code.LOADConst(10);
	code.NEWARRAY(long.class);
	code.PUTFIELD("la", long[].class);

	code.LINE(19);
	code.LOAD("this");
	code.LOADConst(10);
	code.NEWARRAY(float.class);
	code.PUTFIELD("fa", float[].class);

	code.LINE(20);
	code.LOAD("this");
	code.LOADConst(10);
	code.NEWARRAY(double.class);
	code.PUTFIELD("da", double[].class);

	code.LINE(21);
	code.LOAD("this");
	code.LOADConst(10);
	code.NEWARRAY(java.lang.String.class);
	code.PUTFIELD("stra", java.lang.String[].class);

	code.LINE(22);
	code.RETURN();
});
classWriter.method("setArrayValue").code(code -> {

	code.LINE(25);
	code.LOAD("this");
	code.GETFIELD("ba", byte[].class);
	code.LOADConst(0);
	code.LOADConst(100);
	code.visitInsn(BASTORE);

	code.LINE(26);
	code.LOAD("this");
	code.GETFIELD("ca", char[].class);
	code.LOADConst(0);
	code.LOADConst(1000);
	code.visitInsn(CASTORE);

	code.LINE(27);
	code.LOAD("this");
	code.GETFIELD("sa", short[].class);
	code.LOADConst(0);
	code.LOADConst(10000);
	code.visitInsn(SASTORE);

	code.LINE(28);
	code.LOAD("this");
	code.GETFIELD("ia", int[].class);
	code.LOADConst(0);
	code.LOADConst(new Integer(100000));
	code.visitInsn(IASTORE);

	code.LINE(29);
	code.LOAD("this");
	code.GETFIELD("la", long[].class);
	code.LOADConst(0);
	code.LOADConst(new Long(1000000L));
	code.visitInsn(LASTORE);

	code.LINE(30);
	code.LOAD("this");
	code.GETFIELD("fa", float[].class);
	code.LOADConst(0);
	code.LOADConst(new Float("1.0E7"));
	code.visitInsn(FASTORE);

	code.LINE(31);
	code.LOAD("this");
	code.GETFIELD("da", double[].class);
	code.LOADConst(0);
	code.LOADConst(new Double("1.0E8"));
	code.visitInsn(DASTORE);

	code.LINE(32);
	code.LOAD("this");
	code.GETFIELD("stra", java.lang.String[].class);
	code.LOADConst(0);
	code.LOADConst("1000000000s");
	code.visitInsn(AASTORE);

	code.LINE(33);
	code.RETURN();
});
classWriter.method("getArrayValue").code(code -> {

	code.LINE(36);
	code.LOAD("this");
	code.GETFIELD("ba", byte[].class);
	code.LOADConst(1);
	code.LOAD("this");
	code.GETFIELD("ba", byte[].class);
	code.LOADConst(0);
	code.visitInsn(BALOAD);
	code.visitInsn(BASTORE);

	code.LINE(37);
	code.LOAD("this");
	code.GETFIELD("ca", char[].class);
	code.LOADConst(1);
	code.LOAD("this");
	code.GETFIELD("ca", char[].class);
	code.LOADConst(0);
	code.visitInsn(CALOAD);
	code.visitInsn(CASTORE);

	code.LINE(38);
	code.LOAD("this");
	code.GETFIELD("sa", short[].class);
	code.LOADConst(1);
	code.LOAD("this");
	code.GETFIELD("sa", short[].class);
	code.LOADConst(0);
	code.visitInsn(SALOAD);
	code.visitInsn(SASTORE);

	code.LINE(39);
	code.LOAD("this");
	code.GETFIELD("ia", int[].class);
	code.LOADConst(1);
	code.LOAD("this");
	code.GETFIELD("ia", int[].class);
	code.LOADConst(0);
	code.visitInsn(IALOAD);
	code.visitInsn(IASTORE);

	code.LINE(40);
	code.LOAD("this");
	code.GETFIELD("la", long[].class);
	code.LOADConst(1);
	code.LOAD("this");
	code.GETFIELD("la", long[].class);
	code.LOADConst(0);
	code.visitInsn(LALOAD);
	code.visitInsn(LASTORE);

	code.LINE(41);
	code.LOAD("this");
	code.GETFIELD("fa", float[].class);
	code.LOADConst(1);
	code.LOAD("this");
	code.GETFIELD("fa", float[].class);
	code.LOADConst(0);
	code.visitInsn(FALOAD);
	code.visitInsn(FASTORE);

	code.LINE(42);
	code.LOAD("this");
	code.GETFIELD("da", double[].class);
	code.LOADConst(1);
	code.LOAD("this");
	code.GETFIELD("da", double[].class);
	code.LOADConst(0);
	code.visitInsn(DALOAD);
	code.visitInsn(DASTORE);

	code.LINE(43);
	code.LOAD("this");
	code.GETFIELD("stra", java.lang.String[].class);
	code.LOADConst(1);
	code.LOAD("this");
	code.GETFIELD("stra", java.lang.String[].class);
	code.LOADConst(0);
	code.visitInsn(AALOAD);
	code.visitInsn(AASTORE);

	code.LINE(44);
	code.RETURN();
});
return classWriter.end().toByteArray();
}
}
