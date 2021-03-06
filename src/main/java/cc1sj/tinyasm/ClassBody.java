package cc1sj.tinyasm;

import static cc1sj.tinyasm.TypeUtils.stringInnerUserType;

import java.util.List;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Label;

public interface ClassBody
		extends AfterModifier, UsingModifier, UsingDefineStaticField, UsingDefineField, WithMakeStaticMethod, UsingMethodWithModified,UsingMethodWithoutModified {

	ClassBuilder end();

	<T extends Field> List<T> getFields();

	ClassVisitor getClassWriter();

	String getSuperClass();

	String referInnerClass(String innerClass);

	default String referInnerClass(String objectclazz, String innerClass) {
		return referInnerClass(0, objectclazz, innerClass);
	}

	default String referInnerClass(Class<?> objectclazz, Class<?> innerClass) {
		return referInnerClass(0, objectclazz.getName(), innerClass.getName());
	}

	default String referInnerClass(int access, Class<?> objectclazz, Class<?> innerClass) {
		return referInnerClass(access, objectclazz.getName(), innerClass.getName());
	}

	String referInnerClass(int access, String objectclazz, String innerClass);

	String getName();

	String getSimpleName();

	// TODO constructerEmpty
	default ClassBody constructerEmpty() {
		public_().method("<init>").code(code -> {
			Label label0 = new Label();
			code.visitLabel(label0);
			code.LINE(3);
			code.LOAD("this");
			code.SPECIAL(getSuperClass(), "<init>").INVOKE();
			code.RETURN();
		});
		return this;
	}

	// TODO constructerWithAllFields
	default ClassBody constructerWithAllFields() {
		final List<Field> fields = getFields();
		public_().method("<init>").parameter(fields).code(code -> {
			code.LINE();
			code.LOAD("this");
			code.SPECIAL(getSuperClass(), "<init>").INVOKE();

			for (Field param : fields) {
				code.LINE();
				code.LOAD("this");
				code.LOAD(param.name);
				code.PUTFIELD(param.name, param.clazz.getType().getClassName());
			}
			code.LINE(10);
			code.RETURN();

		});
		return this;
	}

	Clazz clazzOfField(String name);

	default ClassBody makePropertyGet(final String fieldName) {
		Clazz fieldClass = clazzOfField(fieldName);
		public_().method("get" + toPropertyName(fieldName)).return_(fieldClass).code(code -> {
			code.LINE();
			code.LOAD_THIS();
			code.GETFIELD(fieldName, fieldClass.getType());
			code.RETURNTop();
		});
		return this;
	}

	default ClassBody makePropertyGet(final Class<?> annotationClazz, final String fieldName) {
		Clazz fieldClass = clazzOfField(fieldName);
		public_().method("get" + toPropertyName(fieldName)).return_(fieldClass).annotation(annotationClazz).code(code -> {
			code.LINE();
			code.LOAD_THIS();
			code.GETFIELD(fieldName, fieldClass.getType());
			code.RETURNTop();
		});
		return this;
	}

	default ClassBody makePropertyGet(final String annotationClazz, final String fieldName) {
		Clazz fieldClass = clazzOfField(fieldName);
		public_().method("get" + toPropertyName(fieldName)).return_(fieldClass).annotation(annotationClazz).code(code -> {
			code.LINE();
			code.LOAD_THIS();
			code.GETFIELD(fieldName, fieldClass.getType());
			code.RETURNTop();
		});
		return this;
	}

	// TODO need do now
	default String toPropertyName(String fieldName) {
		return Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
	}

	default ClassBody makePropertyGet(final Class<?> annotationClazz, Object value, final String fieldName) {
		Clazz fieldClass = clazzOfField(fieldName);
		public_().method("get" + toPropertyName(fieldName)).return_(fieldClass).annotation(annotationClazz, value).code(code -> {
			code.LINE();
			code.LOAD_THIS();
			code.GETFIELD(fieldName, fieldClass.getType());
			code.RETURNTop();
		});
		return this;
	}

	default ClassBody makePropertyGet(final String annotationClazz, Object value, final String fieldName) {
		Clazz fieldClass = clazzOfField(fieldName);
		public_().method("get" + toPropertyName(fieldName)).return_(fieldClass).annotation(annotationClazz, value).code(code -> {
			code.LINE();
			code.LOAD_THIS();
			code.GETFIELD(fieldName, fieldClass.getType());
			code.RETURNTop();
		});
		return this;
	}

	default ClassBody makePropertyGet(final Class<?> annotationClazz, String name, Object value, final String fieldName) {
		Clazz fieldClass = clazzOfField(fieldName);
		public_().method("get" + toPropertyName(fieldName)).return_(fieldClass).annotation(annotationClazz, name, value).code(code -> {
			code.LINE();
			code.LOAD_THIS();
			code.GETFIELD(fieldName, fieldClass.getType());
			code.RETURNTop();
		});
		return this;
	}

	default ClassBody makePropertyGet(final String annotationClazz, String name, Object value, final String fieldName) {
		Clazz fieldClass = clazzOfField(fieldName);
		public_().method("get" + toPropertyName(fieldName)).return_(fieldClass).annotation(annotationClazz, name, value).code(code -> {
			code.LINE();
			code.LOAD_THIS();
			code.GETFIELD(fieldName, fieldClass.getType());
			code.RETURNTop();
		});
		return this;
	}

	default ClassBody makePropertySet(final String fieldName) {
		Clazz fieldClass = clazzOfField(fieldName);
		public_().method("set" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1)).parameter(fieldName, fieldClass)
				.code(code -> {
					code.LINE();
					code.LOAD("this");
					code.LOAD(fieldName);
					code.PUTFIELD(fieldName, fieldClass.getType().getClassName());
					code.LINE();
					code.RETURN();
				});
		return this;
	}

	default ClassBody makePropertySet(final Class<?> annotationClazz, final String fieldName) {
		Clazz fieldClass = clazzOfField(fieldName);
		public_().method("set" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1)).annotation(annotationClazz)
				.parameter(fieldName, fieldClass).code(code -> {
					code.LINE();
					code.LOAD("this");
					code.LOAD(fieldName);
					code.PUTFIELD(fieldName, fieldClass.getType().getClassName());
					code.LINE();
					code.RETURN();
				});
		return this;
	}

	default ClassBody makePropertySet(final String annotationClazz, final String fieldName) {
		Clazz fieldClass = clazzOfField(fieldName);
		public_().method("set" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1)).annotation(annotationClazz)
				.parameter(fieldName, fieldClass).code(code -> {
					code.LINE();
					code.LOAD("this");
					code.LOAD(fieldName);
					code.PUTFIELD(fieldName, fieldClass.getType().getClassName());
					code.LINE();
					code.RETURN();
				});
		return this;
	}

//

	default ClassBody makePropertySet(final Class<?> annotationClazz, Object annotationValue, final String fieldName) {
		Clazz fieldClass = clazzOfField(fieldName);
		public_().method("set" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1))
				.annotation(annotationClazz, annotationValue).parameter(fieldName, fieldClass).code(code -> {
					code.LINE();
					code.LOAD("this");
					code.LOAD(fieldName);
					code.PUTFIELD(fieldName, fieldClass.getType().getClassName());
					code.LINE();
					code.RETURN();
				});
		return this;
	}

//

	default ClassBody makePropertySet(final String annotationClazz, Object annotationValue, final String fieldName) {
		Clazz fieldClass = clazzOfField(fieldName);
		public_().method("set" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1))
				.annotation(annotationClazz, annotationValue).parameter(fieldName, fieldClass).code(code -> {
					code.LINE();
					code.LOAD("this");
					code.LOAD(fieldName);
					code.PUTFIELD(fieldName, fieldClass.getType().getClassName());
					code.LINE();
					code.RETURN();
				});
		return this;
	}
//

	default ClassBody makePropertySet(final Class<?> annotationClazz, String annotationName, Object annotationValue,
			final String fieldName) {
		Clazz fieldClass = clazzOfField(fieldName);
		public_().method("set" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1))
				.annotation(annotationClazz, annotationName, annotationValue).parameter(fieldName, fieldClass).code(code -> {
					code.LINE();
					code.LOAD("this");
					code.LOAD(fieldName);
					code.PUTFIELD(fieldName, fieldClass.getType().getClassName());
					code.LINE();
					code.RETURN();
				});
		return this;
	}
//

	default ClassBody makePropertySet(final String annotationClazz, String annotationName, Object annotationValue, final String fieldName) {
		Clazz fieldClass = clazzOfField(fieldName);
		public_().method("set" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1))
				.annotation(annotationClazz, annotationName, annotationValue).parameter(fieldName, fieldClass).code(code -> {
					code.LINE();
					code.LOAD("this");
					code.LOAD(fieldName);
					code.PUTFIELD(fieldName, fieldClass.getType().getClassName());
					code.LINE();
					code.RETURN();
				});
		return this;
	}

//
	default ClassBody makeAllPropertyGet() {
		for (Field param : getFields()) {
			makePropertyGet(param.name);
		}
		return this;
	}

	default ClassBody makeAllPropertySet() {
		for (Field param : getFields()) {
			final Field field = param;
			Clazz fieldClass = clazzOfField(field.name);
			String fieldName = field.name;
			public_().method("set" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1)).parameter(field.name, field.clazz)
					.code(code -> {
						code.LINE();
						code.LOAD("this");
						code.LOAD(fieldName);
						code.PUTFIELD(fieldName, fieldClass.getType().getClassName());
						code.LINE();
						code.RETURN();
					});
		}
		return this;
	}

	default ClassBody makePojo() {
		constructerEmpty();
		makeAllPropertyGet();
		makeAllPropertySet();
		return toStringWithAllFields();
	}

	default ClassBody makeReadonlyPojo() {
		ClassBody cb = constructerWithAllFields();
		cb = makeAllPropertyGet();
		cb = toStringWithAllFields();
		return cb;
	}

	default ClassBody toStringWithAllFields() {
		final List<Field> fields = getFields();

		public_().method("toString").return_(String.class).code(code -> {

			code.LINE(58);
			code.NEW(java.lang.StringBuilder.class);
			code.DUP();
			code.SPECIAL(java.lang.StringBuilder.class, "<init>").INVOKE();
			code.define("builder", java.lang.StringBuilder.class);
			code.STORE("builder");
			if (fields.size() > 0) {
				int i = 0;

				code.LINE(59);
				code.LOAD("builder");
				code.LOADConst(getSimpleName() + " [" + fields.get(i).name + "=");
				code.VIRTUAL(java.lang.StringBuilder.class, "append").return_(java.lang.StringBuilder.class)
						.parameter(java.lang.String.class).INVOKE();
				code.LOAD("this");
				code.GETFIELD_OF_THIS(fields.get(i).name);
				code.VIRTUAL(java.lang.StringBuilder.class, "append").return_(java.lang.StringBuilder.class)
						.parameter(stringInnerUserType(fields.get(i).clazz)).INVOKE();

				for (i = 1; i < fields.size(); i++) {

					code.LOADConst(", " + fields.get(i).name + "=");

					code.LINE(60);
					code.VIRTUAL(java.lang.StringBuilder.class, "append").return_(java.lang.StringBuilder.class)
							.parameter(java.lang.String.class).INVOKE();
					code.LOAD("this");
					code.GETFIELD(fields.get(i).name, fields.get(i).clazz.getType());
					code.VIRTUAL(java.lang.StringBuilder.class, "append").return_(java.lang.StringBuilder.class)
							.parameter(stringInnerUserType(fields.get(i).clazz)).INVOKE();
				}
				code.LOADConst("]");
				code.LINE(67);
				code.VIRTUAL(java.lang.StringBuilder.class, "append").return_(java.lang.StringBuilder.class)
						.parameter(java.lang.String.class).INVOKE();
				code.POP();
			} else {
				code.LINE(59);
				code.LOAD("builder");
				code.LOADConst(getSimpleName() + " []");
				code.VIRTUAL(java.lang.StringBuilder.class, "append").return_(java.lang.StringBuilder.class)
						.parameter(java.lang.String.class).INVOKE();
				code.POP();
			}
			code.LINE(68);
			code.LOAD("builder");
			code.VIRTUAL(java.lang.StringBuilder.class, "toString").return_(java.lang.String.class).INVOKE();
			code.RETURNTop();

		});

		return this;
	}

	public void visitInnerClass(String string, String string2, String string3, int i);

//	Class<?> stringInnerUserType(Clazz clazz);

}