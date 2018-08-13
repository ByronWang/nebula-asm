package nebula.tinyasm.data;

import org.objectweb.asm.Label;
import org.objectweb.asm.Type;

public class LocalsVariable extends ClassField {
	public Label startFrom;

	public Object value;

	public LocalsVariable(ClassField field, Label startFrom) {
		super(field.access, field.name, field.type, field.signature, null);
		this.startFrom = startFrom;
	}

	public LocalsVariable(String name, Type type) {
		super(0, name, type, null, null);
	}

	public LocalsVariable(String name, Type type, Label startFrom) {
		super(0, name, type, null, startFrom);
	}

	public LocalsVariable(String name, Type type, String signature) {
		super(0, name, type, signature, null);
	}

	public LocalsVariable(String name, Type type, String signature, Label startFrom) {
		super(0, name, type, signature, null);
		this.startFrom = startFrom;
	}

	public int locals = 0;
}