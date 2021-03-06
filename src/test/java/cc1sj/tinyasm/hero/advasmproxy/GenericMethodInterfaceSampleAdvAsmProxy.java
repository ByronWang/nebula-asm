package cc1sj.tinyasm.hero.advasmproxy;

import static cc1sj.tinyasm.Adv.MAGIC_CODES_NUMBER;

import java.lang.reflect.Array;

import cc1sj.tinyasm.Adv;
import cc1sj.tinyasm.AdvContext;
import cc1sj.tinyasm.AdvRuntimeReferNameObject;
import cc1sj.tinyasm.ConsumerWithException;
import cc1sj.tinyasm.MethodCode;
import cc1sj.tinyasm.hero.helperclass.GenericMethodInterface;
import cc1sj.tinyasm.hero.helperclass.PojoClassSample;

public class GenericMethodInterfaceSampleAdvAsmProxy implements GenericMethodInterface<PojoClassSample>, AdvRuntimeReferNameObject {
	private byte _magicNumber;
	private ThreadLocal<AdvContext> _contextThreadLocal;

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

	@SuppressWarnings("unchecked")
	@Override
	public <T> T[] arrayToArray(T[] param0) {

		AdvContext context = _contextThreadLocal.get();
		ConsumerWithException<MethodCode> eval_param0 = context.resolve(param0);
		ConsumerWithException<MethodCode> objEval = context.resolve(this);
		byte codeIndex = context.push(Object[].class, c -> {
			objEval.accept(c);
			eval_param0.accept(c);

			c.INTERFACE(GenericMethodInterface.class, "arrayToArray").parameter(Object[].class).return_(Object[].class).INVOKE();
		});

		byte magicNumber = (byte) (MAGIC_CODES_NUMBER + codeIndex);

		Class<?> targetClassT = param0.getClass().getComponentType();

		T[] targetArray = (T[]) Array.newInstance(targetClassT, 1);

		T targetElement = null;
		if (Adv.canProxy(targetClassT)) {
			targetElement = Adv.buildProxyClass((Class<T>) targetClassT, magicNumber);
			targetArray[0] = targetElement;
			return targetArray; // int.class);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T arrayToObject(T[] param0) {

		AdvContext context = _contextThreadLocal.get();
		ConsumerWithException<MethodCode> eval_param0 = context.resolve(param0);
		ConsumerWithException<MethodCode> objEval = context.resolve(this);
		byte codeIndex = context.push(Object.class, c -> {
			objEval.accept(c);
			eval_param0.accept(c);

			c.INTERFACE(GenericMethodInterface.class, "arrayToObject").parameter(Object[].class).return_(Object.class).INVOKE();
		});

		byte magicNumber = (byte) (MAGIC_CODES_NUMBER + codeIndex);

		Class<?> targetClassT = param0.getClass().getComponentType();

		if (Adv.canProxy(targetClassT)) {
			return Adv.buildProxyClass((Class<T>) targetClassT, magicNumber);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T objectToObject(T param0) {
		AdvContext context = _contextThreadLocal.get();
		ConsumerWithException<MethodCode> eval_param0 = context.resolve(param0);
		ConsumerWithException<MethodCode> objEval = context.resolve(this);
		byte codeIndex = context.push(Object.class, c -> {
			objEval.accept(c);
			eval_param0.accept(c);

			c.INTERFACE(GenericMethodInterface.class, "objectToObject").parameter(Object.class).return_(Object.class).INVOKE();
		});

		byte magicNumber = (byte) (MAGIC_CODES_NUMBER + codeIndex);

		Class<?> targetClassT = param0.getClass();
		if (Adv.canProxy(targetClassT)) {
			return Adv.buildProxyClass((Class<T>) targetClassT, magicNumber);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T[] objectToArray(T param0) {
		AdvContext context = _contextThreadLocal.get();
		ConsumerWithException<MethodCode> eval_param0 = context.resolve(param0);
		ConsumerWithException<MethodCode> objEval = context.resolve(this);
		byte codeIndex = context.push(Object[].class, c -> {
			objEval.accept(c);
			eval_param0.accept(c);

			c.INTERFACE(GenericMethodInterface.class, "objectToArray").parameter(Object.class).return_(Object[].class).INVOKE();
		});

		byte magicNumber = (byte) (MAGIC_CODES_NUMBER + codeIndex);

		Class<?> targetClassT = param0.getClass();

		T[] targetArray = (T[]) Array.newInstance(targetClassT, 1);

		T targetElement = null;
		if (Adv.canProxy(targetClassT)) {
			targetElement = Adv.buildProxyClass((Class<T>) targetClassT, magicNumber);
			targetArray[0] = targetElement;
			return targetArray; // int.class);
		} else {
			return null;
		}
	}

//	@Override
//	public <T> T classToObject(Class<T> param0) {
//		AdvContext context = _contextThreadLocal.get();
//		ConsumerWithException<MethodCode> eval_param0 = context.resolve(param0);
//		ConsumerWithException<MethodCode> objEval = context.resolve(this);
//		byte codeIndex = context.push(Object[].class, c -> {
//			objEval.accept(c);
//			eval_param0.accept(c);
//
//			c.INTERFACE(GenericMethodInterface.class, "toArray").parameter(Object[].class).reTurn(Object[].class).INVOKE();
//		});
//
//		byte magicNumber = (byte) (MAGIC_CODES_NUMBER + codeIndex);
//
//		Class<?> targetClassT = param0;
//
//		if (Adv.canProxy(targetClassT)) {
//			return Adv.buildProxyClass((Class<T>) targetClassT, magicNumber);
//		} else {
//			return null;
//		}
//	}

}
