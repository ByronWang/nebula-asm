package nebula.tinyasm;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.util.ASMifier;
import org.objectweb.asm.util.TraceClassVisitor;

public class RefineCode {

	public static String dddd(Class<?> clazz) {
		try {
			ClassReader cr = new ClassReader(clazz.getName());
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			ClassVisitor visitor = new TraceClassVisitor(null, new ASMifier(), pw);
			cr.accept(visitor, ClassReader.EXPAND_FRAMES);
			return refineCode(sw.toString());
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void print(Class<?> clazz) {
		System.out.println(dddd(clazz));
	}

	public static String refineCode(String input) {

		// match by line
		input = input.replaceAll("Label (l\\d*) = new Label\\(\\);\\s*\\n", "Label $1 = mv.codeNewLabel();");
		input = input.replaceAll("mv.visitLabel\\((l\\d*)\\);", "mv.codeAccessLabel\\($1\\);");
		input = input.replaceAll("mv.visitLineNumber\\(\\d, \\w\\d\\);\\s*\\n", "");

		input = input.replaceAll("mv.visitInsn\\(LCMP\\);", "mv.CMP();");
		input = input.replaceAll("mv.visitInsn\\((?:D|F)CMPG\\);", "mv.CMPG();");
		input = input.replaceAll("mv.visitInsn\\((?:D|F)CMPL\\);", "mv.CMPL();");

		input = input.replaceAll("mv.visitInsn\\(\\wRETURN\\);", "mv.RETURNTop();");
		input = input.replaceAll("mv.visitVarInsn\\(\\wLOAD, (\\d*)\\);", "mv.LOAD($1);");
		input = input.replaceAll("mv.visitInsn\\(\\wALOAD\\);", "mv.ARRAYLOAD();");
		input = input.replaceAll("mv.visitInsn\\(\\wASTORE\\);", "mv.ARRAYSTORE();");
		input = input.replaceAll("mv.visitFieldInsn\\(GETFIELD, \"[^\"]*\", (\"[^\"]*\")[^\\n]*;\\n",
				"mv.GETFIELD($1);");
		input = input.replaceAll("mv.visitFieldInsn\\(PUTFIELD, \"[^\"]*\", (\"[^\"]*\")[^\\n]*;\\n",
				"mv.PUTFIELD($1);");

		input = input.replaceAll("mv.visitLdcInsn(\\([^;]*)[^\\n]*;\\n", "mv.LOADConst$1;");

		input = input.replaceAll("mv.visitJumpInsn\\((\\w+), (l\\d+)\\);", "mv.$1($2);");

		input = input.replaceAll("mv.visitLocalVariable\\(\"([\\d\\w]*)\", \"(\\w+)\"[^;]+;",
				"<def>mv.define(\"$1\",\"$2\"\\);</def>");
		input = input.replaceAll("mv.visitLocalVariable\\(\\\"this\\\"[^\\n]*\\n", "");
		input = input.replaceAll("mv.visitFrame[^;]*;\\s*\\n", "");
		input = input.replaceAll("mv.visitMaxs[^;]*;\\s*\\n", "");
		input = input.replaceAll("mv.visitInsn\\(ICONST_(\\d)\\);", "mv.LOADConst($1);");

		// match by method
		input = input.replaceAll("\\n", "");
		input = input.replaceAll("\\{(mv = cw.visitMethod\\()", "\n\n<method>$1");
		input = input.replaceAll("(mv.visitCode\\(\\);)", "<code>");
		input = input.replaceAll("(mv.visitEnd\\(\\);})", "</code></method>\n\n");

		input = input.replaceAll(";(<def>.*)(?:;</def></code>)", ";<defs>$1;</def></defs></code>");
		input = input.replaceAll("#def#", ";");

		input = input.replaceAll("(<code>)(.*)(<defs>.*</defs>)", "$1$3$2");
//			
		input = input.replaceAll("</?def>", "");
		input = input.replaceAll("<defs>(.*)</defs>", "$1");

		input = input.replaceAll("mv = cw.visitMethod\\(([^\\n]*);\\n", "cw.method($1");
		input = input.replaceAll("<method>(.*)</method>", "$1");
		input = input.replaceAll("<code>(.*)</code>", ".code(mv -> {\n\t$1});");

//		mv.visitFieldInsn(GETFIELD, "nebula/tinyasm/MethodASMArraySample", "ia", "[I");

		// reformater by line
		input = input.replaceAll("\\);", "\\);\n\t");

		input = input.replaceAll("mv = cw.visitMethod\\(ACC_PUBLIC, (\"[^\"]*\"), \"\\(([^\\)]*)\\)([^\"]*)\",[^;]*;",
				"\tcw.method(/*$3*/, $1,/*$2*/)");

		input = input.replaceAll("(?:\\})?\\{fv = cw.visitField\\(ACC_PRIVATE, \"([^\"]*)\", \"([^\"]*)\",[^;]*;",
				"cw.field(\"$1\", \"$2\");");
		input = input.replaceAll("fv.visitEnd\\(\\);", "");
		return input;
	}
}