package cc1sj.tinyasm.heroadv;

import cc1sj.tinyasm.AdvContext;
import cc1sj.tinyasm.AdvRuntimeReferNameObject;
import cc1sj.tinyasm.ConsumerWithException;
import cc1sj.tinyasm.MethodCode;

public class AdvSampleReferObjectObjenesisAdvAsmProxy extends AdvJavaSourceCodeConverterSampleReferedObject implements AdvRuntimeReferNameObject {
	private byte _magicNumber;

	private ThreadLocal<AdvContext> _contextThreadLocal;

	public byte get__MagicNumber() {
		return this._magicNumber;
	}

	public void set__MagicNumber(byte _magicNumber) {
		this._magicNumber = _magicNumber;
	}

	public void set__Context(ThreadLocal<AdvContext> _contextThreadLocal, byte _magicNumber) {
		this._contextThreadLocal = _contextThreadLocal;
		this._magicNumber = _magicNumber;
	}

	public void sayHello() {
		AdvContext context = this._contextThreadLocal.get();
		ConsumerWithException<MethodCode> objEval = context.resolve(this);
		context.push(paramMethodCode -> {
			objEval.accept(paramMethodCode);
			paramMethodCode.VIRTUAL(AdvJavaSourceCodeConverterSampleReferedObject.class, "sayHello").INVOKE();
		});
		context.popAndExec();
	}

	public String getHelloString() {
		AdvContext context = this._contextThreadLocal.get();
		ConsumerWithException<MethodCode> objEval = context.resolve(this);
		byte codeIndex = context.push(paramMethodCode -> {
			objEval.accept(paramMethodCode);
			paramMethodCode.VIRTUAL(AdvJavaSourceCodeConverterSampleReferedObject.class, "getHelloString").reTurn(String.class).INVOKE();
		});
		return "#MAGIC-CODES#" + codeIndex;
	}

	public void setHelloString(String param0) {
		AdvContext context = this._contextThreadLocal.get();
		ConsumerWithException<MethodCode> consumerWithException = context.resolve(this);
		ConsumerWithException<MethodCode> eval_param0 = context.resolve(param0);
		context.push(paramMethodCode -> {
			consumerWithException.accept(paramMethodCode);
			eval_param0.accept(paramMethodCode);
			paramMethodCode.VIRTUAL(AdvJavaSourceCodeConverterSampleReferedObject.class, "setHelloString").parameter(String.class).INVOKE();
		});
		context.popAndExec();
	}

	public boolean beGood() {
		AdvContext context = this._contextThreadLocal.get();
		ConsumerWithException<MethodCode> objEval = context.resolve(this);
		context.push(paramMethodCode -> {
			objEval.accept(paramMethodCode);
			paramMethodCode.VIRTUAL(AdvJavaSourceCodeConverterSampleReferedObject.class, "beGood").reTurn(boolean.class).INVOKE();
		});
		return false;
	}
}
