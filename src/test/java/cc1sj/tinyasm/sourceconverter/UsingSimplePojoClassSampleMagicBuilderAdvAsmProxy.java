package cc1sj.tinyasm.sourceconverter;

import cc1sj.tinyasm.AdvClassBuilder;
import cc1sj.tinyasm.AdvContext;
import cc1sj.tinyasm.AdvMagicRuntime;
import cc1sj.tinyasm.ConsumerWithException;
import cc1sj.tinyasm.MethodCode;

public class UsingSimplePojoClassSampleMagicBuilderAdvAsmProxy extends UsingSimplePojoClassSampleMagicBuilder implements AdvMagicRuntime {
	private byte _magicNumber;
	private ThreadLocal<AdvContext> _contextThreadLocal;
	private AdvClassBuilder _classBuilder;

	@Override
	public byte get__MagicNumber() {
		return this._magicNumber;
	}

	@Override
	public void set__MagicNumber(byte _magicNumber) {
		this._magicNumber = _magicNumber;
	}

	@Override
	public void set__Context(ThreadLocal<AdvContext> _contextThreadLocal, byte _magicNumber) {
		this._contextThreadLocal = _contextThreadLocal;
		this._magicNumber = _magicNumber;
	}

	@Override
	public AdvClassBuilder get__ClassBuilder() {
		return _classBuilder;
	}

	@Override
	public void set__ClassBuilder(AdvClassBuilder _classBuilder) {
		this._classBuilder = _classBuilder;
//		this._classBuilder.getClazz();
	}

	@Override
	public void sayHello() {
		AdvContext context = _contextThreadLocal.get();
		ConsumerWithException<MethodCode> objEval = context.resolve(this);
		context.execLine(c -> {
			objEval.accept(c);
			c.VIRTUAL(UsingSimplePojoClassSampleMagicBuilder.class, "sayHello").INVOKE();
		});
	}

	public void $_sayHello() {
		super.sayHello();
	}
}
