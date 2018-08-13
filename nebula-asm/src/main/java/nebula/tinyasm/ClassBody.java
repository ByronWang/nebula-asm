package nebula.tinyasm;

import java.util.List;

import org.objectweb.asm.Type;

import nebula.tinyasm.data.Field;

public interface ClassBody extends ClassDefineField<ClassBody>, ClassDefineStaticMethod, ClassDefineInstanceMethod {

	ClassBuilder end();

	List<Field> getFields();

	Type getSuperType();

	Type referInnerClass(String innerClass);

	@Deprecated
	void visitInnerClass(String name, String outerName, String innerName, int access);

}