package nebula.tinyasm;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import nebula.tinyasm.api.InstanceMethodCode;
import nebula.tinyasm.api.MethodHeader;
import nebula.tinyasm.data.ClassField;
import nebula.tinyasm.data.LocalsVariable;

class MethodInstanceBuilder extends
		MethodBuilder<MethodHeader<InstanceMethodCode>, InstanceMethodCode>
		implements MethodHeader<InstanceMethodCode>, InstanceMethodCode, Opcodes {

	ClassBuilderImpl cv;

	public MethodInstanceBuilder(ClassBuilderImpl cv, Type thisType, int access, Type returnType, String methodName,
			String[] exceptionClasses) {
		super(cv, thisType, access, returnType, methodName, exceptionClasses);
		this.cv = cv;
	}

	@Override
	public InstanceMethodCode code() {
		return this;
	}

	@Override
	protected InstanceMethodCode makeMethodBegin() {
		mv.visitCode();

		labelCurrent = labelWithoutLineNumber();
		// TODO add class sign
		locals.push(_THIS, thisMethod.type, labelCurrent);
		for (ClassField field : thisMethod.params) {
			locals.push(new LocalsVariable(field, labelCurrent));
		}

		return code();
	}

}