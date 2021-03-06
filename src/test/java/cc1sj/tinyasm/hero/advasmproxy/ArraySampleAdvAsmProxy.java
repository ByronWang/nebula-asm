package cc1sj.tinyasm.hero.advasmproxy;

import static cc1sj.tinyasm.Adv.MAGIC_CODES_NUMBER;
import static cc1sj.tinyasm.Adv.MAGIC_CODES_String;

import cc1sj.tinyasm.Adv;
import cc1sj.tinyasm.AdvContext;
import cc1sj.tinyasm.AdvRuntimeReferNameObject;
import cc1sj.tinyasm.ConsumerWithException;
import cc1sj.tinyasm.MethodCode;
import cc1sj.tinyasm.hero.helperclass.ArraySample;
import cc1sj.tinyasm.hero.helperclass.SimplePojoClassSample;

public class ArraySampleAdvAsmProxy extends ArraySample implements AdvRuntimeReferNameObject {
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

	@Override
	public byte[] getByteArray() {
		AdvContext context = _contextThreadLocal.get();
		ConsumerWithException<MethodCode> objEval = context.resolve(this);
		byte codeIndex = context.push(byte[].class, c -> {
			objEval.accept(c);
			c.VIRTUAL(ArraySample.class, "getByteArray").return_(byte[].class).INVOKE();
		});

		int magicNumber = MAGIC_CODES_NUMBER + codeIndex;
		byte[] tarray = new byte[1];
		tarray[0] = (byte) magicNumber;

		return tarray; // int.class);
	}

	@Override
	public short[] getShortArray() {
		AdvContext context = _contextThreadLocal.get();
		ConsumerWithException<MethodCode> objEval = context.resolve(this);
		byte codeIndex = context.push(short[].class, c -> {
			objEval.accept(c);
			c.VIRTUAL(ArraySample.class, "getShortArray").return_(short[].class).INVOKE();
		});

		int magicNumber = MAGIC_CODES_NUMBER + codeIndex;
		short[] tarray = new short[1];
		tarray[0] = (short) magicNumber;

		return tarray; // int.class);
	}

	@Override
	public int[] getIntArray() {
		AdvContext context = _contextThreadLocal.get();
		ConsumerWithException<MethodCode> objEval = context.resolve(this);
		byte codeIndex = context.push(int[].class, c -> {
			objEval.accept(c);
			c.VIRTUAL(ArraySample.class, "getIntArray").return_(int[].class).INVOKE();
		});

		int magicNumber = MAGIC_CODES_NUMBER + codeIndex;
		int[] tarray = new int[1];
		tarray[0] = magicNumber;

		return tarray; // int.class);
	}

	@Override
	public Short[] getShort2Array() {
		AdvContext context = _contextThreadLocal.get();
		ConsumerWithException<MethodCode> objEval = context.resolve(this);
		byte codeIndex = context.push(Short[].class, c -> {
			objEval.accept(c);
			c.VIRTUAL(ArraySample.class, "getShort2Array").return_(Short[].class).INVOKE();
		});

		int magicNumber = MAGIC_CODES_NUMBER + codeIndex;
		Short[] tarray = new Short[1];
		tarray[0] = (short) magicNumber;

		return tarray; // int.class);
	}

	@Override
	public String[] getStringArray() {
		AdvContext context = _contextThreadLocal.get();
		ConsumerWithException<MethodCode> objEval = context.resolve(this);
		byte codeIndex = context.push(String[].class, c -> {
			objEval.accept(c);
			c.VIRTUAL(ArraySample.class, "getStringArray").return_(String[].class).INVOKE();
		});

		String magicNumber = new StringBuilder(MAGIC_CODES_String).append(codeIndex).toString(); // String.class);
		String[] tarray = new String[1];
		tarray[0] = magicNumber;

		return tarray; // int.class);
	}

	@Override
	public SimplePojoClassSample[] getSimplePojoClassSampleArray() {

		AdvContext context = _contextThreadLocal.get();
		ConsumerWithException<MethodCode> objEval = context.resolve(this);
		byte codeIndex = context.push(SimplePojoClassSample[].class, c -> {
			objEval.accept(c);
			c.VIRTUAL(ArraySample.class, "getSimplePojoClassSampleArray").return_(SimplePojoClassSample[].class).INVOKE();
		});
		byte magicNumber = (byte) (MAGIC_CODES_NUMBER + codeIndex);
		SimplePojoClassSample simplePojoClassSample = null;
		SimplePojoClassSample[] tarray = new SimplePojoClassSample[1];
		
		if (Adv.canProxy(SimplePojoClassSample.class)) {
			simplePojoClassSample = Adv.buildProxyClass(SimplePojoClassSample.class, magicNumber);
			tarray[0] = simplePojoClassSample;
			return tarray; // int.class);
		} else {
			return null;
		}
	}

	@Override
	public void setByteArray(byte[] param0) {
		AdvContext context = _contextThreadLocal.get();
		ConsumerWithException<MethodCode> eval_param0 = context.resolve(param0);
		ConsumerWithException<MethodCode> objEval = context.resolve(this);
		context.execLine(c -> {
			objEval.accept(c);
			eval_param0.accept(c);

			c.VIRTUAL(ArraySample.class, "setByteArray").parameter(byte[].class).INVOKE();
		});
	}

	@Override
	public void setShortArray(short[] param0) {
		AdvContext context = _contextThreadLocal.get();
		ConsumerWithException<MethodCode> eval_param0 = context.resolve(param0);
		ConsumerWithException<MethodCode> objEval = context.resolve(this);
		context.execLine(c -> {
			objEval.accept(c);
			eval_param0.accept(c);

			c.VIRTUAL(ArraySample.class, "setShortArray").parameter(short[].class).INVOKE();
		});
	}

	@Override
	public void setIntArray(int[] param0) {
		AdvContext context = _contextThreadLocal.get();
		ConsumerWithException<MethodCode> eval_param0 = context.resolve(param0);
		ConsumerWithException<MethodCode> objEval = context.resolve(this);
		context.execLine(c -> {
			objEval.accept(c);
			eval_param0.accept(c);

			c.VIRTUAL(ArraySample.class, "setIntArray").parameter(int[].class).INVOKE();
		});
	}

	@Override
	public void setShort2Array(Short[] param0) {
		AdvContext context = _contextThreadLocal.get();
		ConsumerWithException<MethodCode> eval_param0 = context.resolve(param0);
		ConsumerWithException<MethodCode> objEval = context.resolve(this);
		context.execLine(c -> {
			objEval.accept(c);
			eval_param0.accept(c);

			c.VIRTUAL(ArraySample.class, "setShort2Array").parameter(Short[].class).INVOKE();
		});
	}

	@Override
	public void setStringArray(String[] param0) {
		AdvContext context = _contextThreadLocal.get();
		ConsumerWithException<MethodCode> eval_param0 = context.resolve(param0);
		ConsumerWithException<MethodCode> objEval = context.resolve(this);
		context.execLine(c -> {
			objEval.accept(c);
			eval_param0.accept(c);

			c.VIRTUAL(ArraySample.class, "setStringArray").parameter(String[].class).INVOKE();
		});
	}

	@Override
	public void setSimplePojoClassSampleArray(SimplePojoClassSample[] param0) {
		AdvContext context = _contextThreadLocal.get();
		ConsumerWithException<MethodCode> eval_param0 = context.resolve(param0);
		ConsumerWithException<MethodCode> objEval = context.resolve(this);
		context.execLine(c -> {
			objEval.accept(c);
			eval_param0.accept(c);

			c.VIRTUAL(ArraySample.class, "setSimplePojoClassSampleArray").parameter(SimplePojoClassSample[].class).INVOKE();
		});
	}

//	@Override
//	public void setTwoParameter(int param0, byte param1) {
//		AdvContext context = _contextThreadLocal.get();
//		ConsumerWithException<MethodCode> eval_param1 = context.resolve(param1);
//		ConsumerWithException<MethodCode> eval_param0 = context.resolve(param0);
//		ConsumerWithException<MethodCode> objEval = context.resolve(this);
//		context.execLine(c -> {
//			objEval.accept(c);
//			eval_param0.accept(c);
//			eval_param1.accept(c);
//			c.VIRTUAL(PojoSample.class, "setTwoParameter").parameter(int.class).parameter(byte.class).INVOKE();
//		});
//	}
//
//	@Override
//	public String getName() {
//
//		AdvContext context = _contextThreadLocal.get();
//		ConsumerWithException<MethodCode> objEval = context.resolve(this);
//		byte codeIndex = context.push(String.class, c -> {
//			objEval.accept(c);
//			c.VIRTUAL(PojoClassSample.class, "getName").reTurn(String.class).INVOKE();
//		});
//		return new StringBuilder( MAGIC_CODES_String).append(codeIndex).toString(); // String.class);
//	}
//
//	@Override
//	public boolean isAgeBoolean() {
//		AdvContext context = _contextThreadLocal.get();
//		ConsumerWithException<MethodCode> objEval = context.resolve(this);
//		context.push(boolean.class, c -> {
//			objEval.accept(c);
//			c.VIRTUAL(PojoClassSample.class, "isAgeBoolean").reTurn(boolean.class).INVOKE();
//		});
//		return false;
//	}
//
//	@Override
//	public byte getAgeByte() {
//
//		AdvContext context = _contextThreadLocal.get();
//		ConsumerWithException<MethodCode> objEval = context.resolve(this);
//		byte codeIndex = context.push(byte.class, c -> {
//			objEval.accept(c);
//			c.VIRTUAL(PojoClassSample.class, "getAgeByte").reTurn(byte.class).INVOKE();
//		});
//		return (byte) (MAGIC_CODES_NUMBER + codeIndex); // byte.class);
//	}
//
//	@Override
//	public char getAgeChar() {
//	
//		AdvContext context = _contextThreadLocal.get();
//		ConsumerWithException<MethodCode> objEval = context.resolve(this);
//		byte codeIndex = context.push(char.class, c -> {
//			objEval.accept(c);
//			c.VIRTUAL(PojoClassSample.class, "getAgeChar").reTurn(char.class).INVOKE();
//		});
//		return (char) (MAGIC_CODES_NUMBER + codeIndex);// char.class);
//	}
//
//	@Override
//	public short getAgeShort() {
//
//		AdvContext context = _contextThreadLocal.get();
//		ConsumerWithException<MethodCode> objEval = context.resolve(this);
//		byte codeIndex = context.push(short.class, c -> {
//			objEval.accept(c);
//			c.VIRTUAL(PojoClassSample.class, "getAgeShort").reTurn(short.class).INVOKE();
//		});
//		return (short) (MAGIC_CODES_NUMBER + codeIndex); // short.class);
//	}
//
//	@Override
//	public int getAgeInt() {
//
//		AdvContext context = _contextThreadLocal.get();
//		ConsumerWithException<MethodCode> objEval = context.resolve(this);
//		byte codeIndex = context.push(int.class, c -> {
//			objEval.accept(c);
//			c.VIRTUAL(PojoClassSample.class, "getAgeInt").reTurn(int.class).INVOKE();
//		});
//		return MAGIC_CODES_NUMBER + codeIndex; // int.class);
//	}
//
//	@Override
//	public long getAgeLong() {
//
//		AdvContext context = _contextThreadLocal.get();
//		ConsumerWithException<MethodCode> objEval = context.resolve(this);
//		byte codeIndex = context.push(long.class, c -> {
//			objEval.accept(c);
//			c.VIRTUAL(PojoClassSample.class, "getAgeLong").reTurn(long.class).INVOKE();
//		});
//		return MAGIC_CODES_NUMBER + codeIndex; // long.class);
//	}
//
//	@Override
//	public float getAgeFloat() {
//
//		AdvContext context = _contextThreadLocal.get();
//		ConsumerWithException<MethodCode> objEval = context.resolve(this);
//		byte codeIndex = context.push(float.class, c -> {
//			objEval.accept(c);
//			c.VIRTUAL(PojoClassSample.class, "getAgeFloat").reTurn(float.class).INVOKE();
//		});
//		return MAGIC_CODES_NUMBER + codeIndex; // float.class);
//	}
//
//	@Override
//	public double getAgeDouble() {
//
//		AdvContext context = _contextThreadLocal.get();
//		ConsumerWithException<MethodCode> objEval = context.resolve(this);
//		byte codeIndex = context.push(double.class, c -> {
//			objEval.accept(c);
//			c.VIRTUAL(PojoClassSample.class, "getAgeDouble").reTurn(double.class).INVOKE();
//		});
//		return MAGIC_CODES_NUMBER + codeIndex; // double.class);
//	}
//
//	@Override
//	public Boolean getAgeBoolean2() {
//		AdvContext context = _contextThreadLocal.get();
//		ConsumerWithException<MethodCode> objEval = context.resolve(this);
//		context.push(Boolean.class, c -> {
//			objEval.accept(c);
//			c.VIRTUAL(PojoClassSample.class, "getAgeBoolean2").reTurn(Boolean.class).INVOKE();
//		});
//		return false;
//	}
//
//	@Override
//	public Byte getAgeByte2() {
//	
//		AdvContext context = _contextThreadLocal.get();
//		ConsumerWithException<MethodCode> objEval = context.resolve(this);
//		byte codeIndex = context.push(Byte.class, c -> {
//			objEval.accept(c);
//			c.VIRTUAL(PojoClassSample.class, "getAgeByte2").reTurn(Byte.class).INVOKE();
//		});
//		return (byte) (MAGIC_CODES_NUMBER + codeIndex); // Byte.class);
//	}
//
//	@Override
//	public Character getAgeCharacter() {
//
//		AdvContext context = _contextThreadLocal.get();
//		ConsumerWithException<MethodCode> objEval = context.resolve(this);
//		byte codeIndex = context.push(Character.class, c -> {
//			objEval.accept(c);
//			c.VIRTUAL(PojoClassSample.class, "getAgeCharacter").reTurn(Character.class).INVOKE();
//		});
//		return (char) (MAGIC_CODES_NUMBER + codeIndex); // Character.class);
//	}
//
//	@Override
//	public Short getAgeShort2() {
//
//		AdvContext context = _contextThreadLocal.get();
//		ConsumerWithException<MethodCode> objEval = context.resolve(this);
//		byte codeIndex = context.push(Short.class, c -> {
//			objEval.accept(c);
//			c.VIRTUAL(PojoClassSample.class, "getAgeShort2").reTurn(Short.class).INVOKE();
//		});
//		return (short) (MAGIC_CODES_NUMBER + codeIndex);// Short.class);
//	}
//
//	@Override
//	public Integer getAgeInteger() {
//
//		AdvContext context = _contextThreadLocal.get();
//		ConsumerWithException<MethodCode> objEval = context.resolve(this);
//		byte codeIndex = context.push(Integer.class, c -> {
//			objEval.accept(c);
//			c.VIRTUAL(PojoClassSample.class, "getAgeInteger").reTurn(Integer.class).INVOKE();
//		});
//		return MAGIC_CODES_NUMBER + codeIndex; // Integer.class);
//	}
//
//	@Override
//	public Long getAgeLong2() {
//
//		AdvContext context = _contextThreadLocal.get();
//		ConsumerWithException<MethodCode> objEval = context.resolve(this);
//		byte codeIndex = context.push(Long.class, c -> {
//			objEval.accept(c);
//			c.VIRTUAL(PojoClassSample.class, "getAgeLong2").reTurn(Long.class).INVOKE();
//		});
//		return (long) (MAGIC_CODES_NUMBER + codeIndex);// Long.class);
//	}
//
//	@Override
//	public Float getAgeFloat2() {
//
//		AdvContext context = _contextThreadLocal.get();
//		ConsumerWithException<MethodCode> objEval = context.resolve(this);
//		byte codeIndex = context.push(Float.class, c -> {
//			objEval.accept(c);
//			c.VIRTUAL(PojoClassSample.class, "getAgeFloat2").reTurn(Float.class).INVOKE();
//		});
//		return (float) (MAGIC_CODES_NUMBER + codeIndex); // Float.class);
//	}
//
//	@Override
//	public Double getAgeDouble2() {
//
//		AdvContext context = _contextThreadLocal.get();
//		ConsumerWithException<MethodCode> objEval = context.resolve(this);
//		byte codeIndex = context.push(Double.class, c -> {
//			objEval.accept(c);
//			c.VIRTUAL(PojoClassSample.class, "getAgeDouble2").reTurn(Double.class).INVOKE();
//		});
//		return (double) (MAGIC_CODES_NUMBER + codeIndex); // Double.class);
//	}
//
//	@Override
//	public void setName(String param0) {
//
//		AdvContext context = _contextThreadLocal.get();
//		ConsumerWithException<MethodCode> eval_param0 = context.resolve(param0);
//		ConsumerWithException<MethodCode> objEval = context.resolve(this);
//		context.execLine(c -> {
//			objEval.accept(c);
//			eval_param0.accept(c);
//
//			c.VIRTUAL(PojoClassSample.class, "setName").parameter(String.class).INVOKE();
//		});
//	}
//
//	@Override
//	public void setAgeBoolean(boolean param0) {
//		AdvContext context = _contextThreadLocal.get();
//		ConsumerWithException<MethodCode> eval_param0 = context.getCodeAndPop();
//		ConsumerWithException<MethodCode> objEval = context.resolve(this);
//		context.execLine(c -> {
//			objEval.accept(c);
//			eval_param0.accept(c);
//
//			c.VIRTUAL(PojoClassSample.class, "setAgeBoolean").parameter(boolean.class).INVOKE();
//		});
//	}
//
//	@Override
//	public void setAgeByte(byte param0) {
//	
//		AdvContext context = _contextThreadLocal.get();
//		ConsumerWithException<MethodCode> eval_param0 = context.resolve(param0);
//		ConsumerWithException<MethodCode> objEval = context.resolve(this);
//		context.execLine(c -> {
//			objEval.accept(c);
//			eval_param0.accept(c);
//	
//			c.VIRTUAL(PojoClassSample.class, "setAgeByte").parameter(byte.class).INVOKE();
//		});
//	
//	}
//
//	@Override
//	public void setAgeChar(char param0) {
//
//		AdvContext context = _contextThreadLocal.get();
//		ConsumerWithException<MethodCode> eval_param0 = context.resolve(param0);
//		ConsumerWithException<MethodCode> objEval = context.resolve(this);
//		context.execLine(c -> {
//			objEval.accept(c);
//			eval_param0.accept(c);
//
//			c.VIRTUAL(PojoClassSample.class, "setAgeChar").parameter(char.class).INVOKE();
//		});
//	}
//
//	@Override
//	public void setAgeShort(short param0) {
//
//		AdvContext context = _contextThreadLocal.get();
//		ConsumerWithException<MethodCode> eval_param0 = context.resolve(param0);
//		ConsumerWithException<MethodCode> objEval = context.resolve(this);
//		context.execLine(c -> {
//			objEval.accept(c);
//			eval_param0.accept(c);
//
//			c.VIRTUAL(PojoClassSample.class, "setAgeShort").parameter(short.class).INVOKE();
//		});
//
//	}
//
//	@Override
//	public void setAgeInt(int param0) {
//
//		AdvContext context = _contextThreadLocal.get();
//		ConsumerWithException<MethodCode> eval_param0 = context.resolve(param0);
//		ConsumerWithException<MethodCode> objEval = context.resolve(this);
//		context.execLine(c -> {
//			objEval.accept(c);
//			eval_param0.accept(c);
//
//			c.VIRTUAL(PojoClassSample.class, "setAgeInt").parameter(int.class).INVOKE();
//		});
//
//	}
//
//	@Override
//	public void setAgeLong(long param0) {
//
//		AdvContext context = _contextThreadLocal.get();
//		ConsumerWithException<MethodCode> eval_param0 = context.resolve(param0);
//		ConsumerWithException<MethodCode> objEval = context.resolve(this);
//		context.execLine(c -> {
//			objEval.accept(c);
//			eval_param0.accept(c);
//
//			c.VIRTUAL(PojoClassSample.class, "setAgeLong").parameter(long.class).INVOKE();
//		});
//
//	}
//
//	@Override
//	public void setAgeFloat(float param0) {
//
//		AdvContext context = _contextThreadLocal.get();
//		ConsumerWithException<MethodCode> eval_param0 = context.resolve(param0);
//		ConsumerWithException<MethodCode> objEval = context.resolve(this);
//		context.execLine(c -> {
//			objEval.accept(c);
//			eval_param0.accept(c);
//
//			c.VIRTUAL(PojoClassSample.class, "setAgeFloat").parameter(float.class).INVOKE();
//		});
//
//	}
//
//	@Override
//	public void setAgeDouble(double param0) {
//
//		AdvContext context = _contextThreadLocal.get();
//		ConsumerWithException<MethodCode> eval_param0 = context.resolve(param0);
//		ConsumerWithException<MethodCode> objEval = context.resolve(this);
//		context.execLine(c -> {
//			objEval.accept(c);
//			eval_param0.accept(c);
//
//			c.VIRTUAL(PojoClassSample.class, "setAgeDouble").parameter(double.class).INVOKE();
//		});
//
//	}
//
//	@Override
//	public void setAgeBoolean2(Boolean param0) {
//		AdvContext context = _contextThreadLocal.get();
//		ConsumerWithException<MethodCode> eval_param0 = context.getCodeAndPop();
//		ConsumerWithException<MethodCode> objEval = context.resolve(this);
//		context.execLine(c -> {
//			objEval.accept(c);
//			eval_param0.accept(c);
//
//			c.VIRTUAL(PojoClassSample.class, "setAgeBoolean2").parameter(Boolean.class).INVOKE();
//		});
//
//	}
//
//	@Override
//	public void setAgeByte2(Byte param0) {
//	
//		AdvContext context = _contextThreadLocal.get();
//	
//		ConsumerWithException<MethodCode> eval_param0 = context.resolve(param0);
//		ConsumerWithException<MethodCode> objEval = context.resolve(this);
//		context.execLine(c -> {
//			objEval.accept(c);
//			eval_param0.accept(c);
//	
//			c.VIRTUAL(PojoClassSample.class, "setAgeByte2").parameter(Byte.class).INVOKE();
//		});
//	
//	}
//
//	@Override
//	public void setAgeCharacter(Character param0) {
//
//		AdvContext context = _contextThreadLocal.get();
//
//		ConsumerWithException<MethodCode> eval_param0 = context.resolve(param0);
//		ConsumerWithException<MethodCode> objEval = context.resolve(this);
//		context.execLine(c -> {
//			objEval.accept(c);
//			eval_param0.accept(c);
//
//			c.VIRTUAL(PojoClassSample.class, "setAgeCharacter").parameter(Character.class).INVOKE();
//		});
//
//	}
//
//	@Override
//	public void setAgeShort2(Short param0) {
//
//		AdvContext context = _contextThreadLocal.get();
//
//		ConsumerWithException<MethodCode> eval_param0 = context.resolve(param0);
//		ConsumerWithException<MethodCode> objEval = context.resolve(this);
//		context.execLine(c -> {
//			objEval.accept(c);
//			eval_param0.accept(c);
//
//			c.VIRTUAL(PojoClassSample.class, "setAgeShort2").parameter(Short.class).INVOKE();
//		});
//
//	}
//
//	@Override
//	public void setAgeInteger(Integer param0) {
//		AdvContext context = _contextThreadLocal.get();
//
//		ConsumerWithException<MethodCode> eval_param0 = context.resolve(param0);
//		ConsumerWithException<MethodCode> objEval = context.resolve(this);
//		context.execLine(c -> {
//			objEval.accept(c);
//			eval_param0.accept(c);
//
//			c.VIRTUAL(PojoClassSample.class, "setAgeInteger").parameter(Integer.class).INVOKE();
//		});
//
//	}
//
//	@Override
//	public void setAgeLong2(Long param0) {
//
//		AdvContext context = _contextThreadLocal.get();
//
//		ConsumerWithException<MethodCode> eval_param0 = context.resolve(param0);
//		ConsumerWithException<MethodCode> objEval = context.resolve(this);
//		context.execLine(c -> {
//			objEval.accept(c);
//			eval_param0.accept(c);
//
//			c.VIRTUAL(PojoClassSample.class, "setAgeLong2").parameter(Long.class).INVOKE();
//		});
//
//	}
//
//	@Override
//	public void setAgeFloat2(Float param0) {
//
//		AdvContext context = _contextThreadLocal.get();
//
//		ConsumerWithException<MethodCode> eval_param0 = context.resolve(param0);
//		ConsumerWithException<MethodCode> objEval = context.resolve(this);
//		context.execLine(c -> {
//			objEval.accept(c);
//			eval_param0.accept(c);
//
//			c.VIRTUAL(PojoClassSample.class, "setAgeFloat2").parameter(Float.class).INVOKE();
//		});
//
//	}
//
//	@Override
//	public void setAgeDouble2(Double param0) {
//
//		AdvContext context = _contextThreadLocal.get();
//
//		ConsumerWithException<MethodCode> eval_param0 = context.resolve(param0);
//		ConsumerWithException<MethodCode> objEval = context.resolve(this);
//		context.execLine(c -> {
//			objEval.accept(c);
//			eval_param0.accept(c);
//
//			c.VIRTUAL(PojoClassSample.class, "setAgeDouble2").parameter(Double.class).INVOKE();
//		});
//
//	}

}
