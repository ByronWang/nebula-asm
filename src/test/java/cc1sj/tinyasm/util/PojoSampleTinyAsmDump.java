package cc1sj.tinyasm.util;
import org.objectweb.asm.Label;
import cc1sj.tinyasm.ClassBody;
import cc1sj.tinyasm.ClassBuilder;
import cc1sj.tinyasm.MethodCode;
import org.objectweb.asm.Type;
import static org.objectweb.asm.Opcodes.*;
import cc1sj.tinyasm.Annotation;
import cc1sj.tinyasm.Clazz;
import java.lang.Object;
import java.lang.StringBuilder;
import java.lang.String;
@SuppressWarnings("unused")
public class PojoSampleTinyAsmDump {

public static byte[] dump () throws Exception {

ClassBody classBody = ClassBuilder.make("cc1sj.tinyasm.util.PojoSample").access(ACC_PUBLIC | ACC_SUPER).body();

classBody.field("i1", Clazz.of(int.class));
classBody.field("i2", Clazz.of(int.class));
classBody.field("i3", Clazz.of(int.class));
classBody.field("i4", Clazz.of(int.class));
classBody.field("str", Clazz.of(String.class));
{
	MethodCode code = classBody.method("<init>")
	.parameter("i1",int.class)
	.parameter("i2",int.class)
	.parameter("i3",int.class)
	.parameter("i4",int.class)
	.parameter("str",String.class).begin();

	code.LINE(10);
	code.LOAD("this");
	code.SPECIAL(Object.class, "<init>").INVOKE();

	code.LINE(11);
	code.LOAD("this");
	code.LOAD("i1");
	code.PUTFIELD("i1", int.class);

	code.LINE(12);
	code.LOAD("this");
	code.LOAD("i2");
	code.PUTFIELD("i2", int.class);

	code.LINE(13);
	code.LOAD("this");
	code.LOAD("i3");
	code.PUTFIELD("i3", int.class);

	code.LINE(14);
	code.LOAD("this");
	code.LOAD("i4");
	code.PUTFIELD("i4", int.class);

	code.LINE(15);
	code.LOAD("this");
	code.LOAD("str");
	code.PUTFIELD("str", String.class);

	code.LINE(16);
	code.RETURN();
	code.END();
}

{
	MethodCode code = classBody.method(int.class, "getI1").begin();

	code.LINE(18);
	code.LOAD("this");
	code.GETFIELD("i1", int.class);
	code.RETURNTop();
	code.END();
}

{
	MethodCode code = classBody.method("setI1")
	.parameter("i1",int.class).begin();

	code.LINE(21);
	code.LOAD("this");
	code.LOAD("i1");
	code.PUTFIELD("i1", int.class);

	code.LINE(22);
	code.RETURN();
	code.END();
}

{
	MethodCode code = classBody.method(int.class, "getI2").begin();

	code.LINE(24);
	code.LOAD("this");
	code.GETFIELD("i2", int.class);
	code.RETURNTop();
	code.END();
}

{
	MethodCode code = classBody.method("setI2")
	.parameter("i2",int.class).begin();

	code.LINE(27);
	code.LOAD("this");
	code.LOAD("i2");
	code.PUTFIELD("i2", int.class);

	code.LINE(28);
	code.RETURN();
	code.END();
}

{
	MethodCode code = classBody.method(int.class, "getI3").begin();

	code.LINE(30);
	code.LOAD("this");
	code.GETFIELD("i3", int.class);
	code.RETURNTop();
	code.END();
}

{
	MethodCode code = classBody.method("setI3")
	.parameter("i3",int.class).begin();

	code.LINE(33);
	code.LOAD("this");
	code.LOAD("i3");
	code.PUTFIELD("i3", int.class);

	code.LINE(34);
	code.RETURN();
	code.END();
}

{
	MethodCode code = classBody.method(int.class, "getI4").begin();

	code.LINE(36);
	code.LOAD("this");
	code.GETFIELD("i4", int.class);
	code.RETURNTop();
	code.END();
}

{
	MethodCode code = classBody.method("setI4")
	.parameter("i4",int.class).begin();

	code.LINE(39);
	code.LOAD("this");
	code.LOAD("i4");
	code.PUTFIELD("i4", int.class);

	code.LINE(40);
	code.RETURN();
	code.END();
}

{
	MethodCode code = classBody.method(String.class, "getStr").begin();

	code.LINE(42);
	code.LOAD("this");
	code.GETFIELD("str", String.class);
	code.RETURNTop();
	code.END();
}

{
	MethodCode code = classBody.method("setStr")
	.parameter("str",String.class).begin();

	code.LINE(45);
	code.LOAD("this");
	code.LOAD("str");
	code.PUTFIELD("str", String.class);

	code.LINE(46);
	code.RETURN();
	code.END();
}

{
	MethodCode code = classBody.method(String.class, "toString").begin();

	code.LINE(49);
	code.NEW(StringBuilder.class);
	code.DUP();
	code.SPECIAL(StringBuilder.class, "<init>").INVOKE();
	code.STORE("builder",StringBuilder.class);

	code.LINE(50);
	code.LOAD("builder");
	code.LOADConst("Pojo [i1=");
	code.VIRTUAL(StringBuilder.class, "append")
		.reTurn(StringBuilder.class)
		.parameter(String.class).INVOKE();
	code.LOAD("this");
	code.GETFIELD("i1", int.class);
	code.VIRTUAL(StringBuilder.class, "append")
		.reTurn(StringBuilder.class)
		.parameter(int.class).INVOKE();

	code.LINE(51);
	code.LOADConst(", i2=");
	code.VIRTUAL(StringBuilder.class, "append")
		.reTurn(StringBuilder.class)
		.parameter(String.class).INVOKE();
	code.LOAD("this");
	code.GETFIELD("i2", int.class);
	code.VIRTUAL(StringBuilder.class, "append")
		.reTurn(StringBuilder.class)
		.parameter(int.class).INVOKE();

	code.LINE(52);
	code.LOADConst(", i3=");
	code.VIRTUAL(StringBuilder.class, "append")
		.reTurn(StringBuilder.class)
		.parameter(String.class).INVOKE();
	code.LOAD("this");
	code.GETFIELD("i3", int.class);
	code.VIRTUAL(StringBuilder.class, "append")
		.reTurn(StringBuilder.class)
		.parameter(int.class).INVOKE();

	code.LINE(53);
	code.LOADConst(", i4=");
	code.VIRTUAL(StringBuilder.class, "append")
		.reTurn(StringBuilder.class)
		.parameter(String.class).INVOKE();
	code.LOAD("this");
	code.GETFIELD("i4", int.class);
	code.VIRTUAL(StringBuilder.class, "append")
		.reTurn(StringBuilder.class)
		.parameter(int.class).INVOKE();

	code.LINE(54);
	code.LOADConst(", str=");
	code.VIRTUAL(StringBuilder.class, "append")
		.reTurn(StringBuilder.class)
		.parameter(String.class).INVOKE();
	code.LOAD("this");
	code.GETFIELD("str", String.class);
	code.VIRTUAL(StringBuilder.class, "append")
		.reTurn(StringBuilder.class)
		.parameter(String.class).INVOKE();

	code.LINE(55);
	code.LOADConst("]");
	code.VIRTUAL(StringBuilder.class, "append")
		.reTurn(StringBuilder.class)
		.parameter(String.class).INVOKE();
	code.POP();

	code.LINE(56);
	code.LOAD("builder");
	code.VIRTUAL(StringBuilder.class, "toString")
		.reTurn(String.class).INVOKE();
	code.RETURNTop();
	code.END();
}

return classBody.end().toByteArray();
}
}
