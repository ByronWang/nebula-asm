package cc1sj.tinyasm;

import static org.objectweb.asm.Opcodes.ACC_PUBLIC;
import static org.objectweb.asm.Opcodes.ASM9;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

class AdvMagicBuilderEngine {

	@SuppressWarnings("unchecked")
	public static <T> byte[] execMagicBuilder(ThreadLocal<AdvContext> threadLocal, AdvClassBuilder classBuilder, T magicBuilderProxy) {
		Class<T> magicBuilderClass = (Class<T>) magicBuilderProxy.getClass().getSuperclass();
		try {

//			Adv.enterClass(classBuilder);
			Class<?> magicBuilderProxyClass = magicBuilderProxy.getClass();
			ClassReader cr = new ClassReader(magicBuilderClass.getName());
			cr.accept(new ClassVisitor(ASM9) {
				@Override
				public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
					Adv.logger.debug("{} {} ", name, signature);
				}

				@Override
				public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
					try {
						if ((access & ACC_PUBLIC) > 0 && !name.startsWith("dump")) {
							Method thisMethod = null;
							Method thisProxyMethod = null;
							for (Method method : magicBuilderClass.getMethods()) {
								if (method.getName().equals(name) && !method.isBridge()) {
									Class<?>[] parameterTypes = method.getParameterTypes();
									Type[] types = new Type[parameterTypes.length];
									for (int i = 0; i < parameterTypes.length; i++) {
										types[i] = Type.getType(parameterTypes[i]);
									}
									String reflectMethodDescriptor = Type.getMethodDescriptor(Type.getType(method.getReturnType()), types);
									if (reflectMethodDescriptor.equals(descriptor)) {
										thisMethod = method;
									}
								}
							}
							for (Method method : magicBuilderProxyClass.getMethods()) {
								if (method.getName().equals("$_" + name) && !method.isBridge()) {
									Class<?>[] parameterTypes = method.getParameterTypes();
									Type[] types = new Type[parameterTypes.length];
									for (int i = 0; i < parameterTypes.length; i++) {
										types[i] = Type.getType(parameterTypes[i]);
									}
									String reflectMethodDescriptor = Type.getMethodDescriptor(Type.getType(method.getReturnType()), types);
									if (reflectMethodDescriptor.equals(descriptor)) {
										thisProxyMethod = method;
									}
								}
							}
							// Method method = clazz.getMethod(name);
							Adv.logger.debug("{} {} ", name, signature);
							if (thisMethod != null) {
								buildWithMethod(classBuilder, magicBuilderProxy, thisMethod, thisProxyMethod, threadLocal);
							}

						}
					} catch (SecurityException e) {
						throw new UnsupportedOperationException(magicBuilderProxyClass.getName(), e);
					}
					return null;
				}

			}, ClassReader.SKIP_CODE);

//			Adv.exitClass();
		} catch (Exception e1) {
			throw new UnsupportedOperationException(magicBuilderClass.getName(), e1);
		}

		return classBuilder.end().toByteArray();
	}

	@SuppressWarnings("unused")
	public static void buildWithMethod(AdvClassBuilder classBuilder, Object simpleSampleCodeBuilder, Method method, Method thisProxyMethod,
			ThreadLocal<AdvContext> threadLocal) {
		if (method.getName().startsWith("_") && method.getParameters()[0].getType() == AdvClassBuilder.class) {
			try {
				Adv.logger.debug("enter asm method {}" + method.getName());
				method.invoke(simpleSampleCodeBuilder, classBuilder);
				Adv.logger.debug("exit  asm method {}" + method.getName());
			} catch (Exception e) {
				throw new UnsupportedOperationException(method.getName(), e);
			}
		} else {
			Adv.logger.debug("enter magic method {}" + method.getName());
			AdvMethodBuilder methodBuilder = (AdvMethodBuilder) classBuilder.method(method.getModifiers(), method.getName());
			if (method.getReturnType() != Void.class) methodBuilder.return_(Clazz.of(method.getGenericReturnType()));
			Parameter[] parameters = method.getParameters();
			java.lang.reflect.Type[] parameterTypes = method.getGenericParameterTypes();
			for (int i = 0; i < parameters.length; i++) {
				Parameter parameter = parameters[i];
				methodBuilder.parameter_(parameter.getName(), Clazz.of(parameterTypes[i]));
			}
			Class<?>[] exceptionClasses = method.getExceptionTypes();
			methodBuilder.throws_(exceptionClasses);
			methodBuilder.code(code -> {
				AdvContext context = threadLocal.get();
				Object[] params = new Object[parameters.length];
				for (int i = 0; i < parameters.length; i++) {
					Parameter parameter = parameters[i];
					Class<?> parameterClass = parameter.getType();

					java.lang.reflect.Type type = parameterTypes[i];
					if (type instanceof ParameterizedType) {
						ParameterizedType parameterType = (ParameterizedType) type;
						Adv.logger.debug("{} {} {} {}", method.getName(), parameter.getName(), parameter.getType().getName(),
								parameterType.getActualTypeArguments());

					} else {
						Adv.logger.debug("{} {} {} {}", method.getName(), parameter.getName(), parameter.getType().getName());
					}
					if (parameterClass == boolean.class || parameterClass == Boolean.class) {
						params[i] = false;
					} else if (parameterClass == byte.class || parameterClass == Byte.class) {
						params[i] = (byte) (Adv.MAGIC_LOCALS_NUMBER + i + 1);
					} else if (parameterClass == short.class || parameterClass == Short.class) {
						params[i] = (short) (Adv.MAGIC_LOCALS_NUMBER + i + 1);
					} else if (parameterClass == int.class || parameterClass == Integer.class) {
						params[i] = (int) (Adv.MAGIC_LOCALS_NUMBER + i + 1);
					} else if (parameterClass == long.class || parameterClass == Long.class) {
						params[i] = (long) (Adv.MAGIC_LOCALS_NUMBER + i + 1);
					} else if (parameterClass == float.class || parameterClass == Float.class) {
						params[i] = (float) (Adv.MAGIC_LOCALS_NUMBER + i + 1);
					} else if (parameterClass == double.class || parameterClass == Double.class) {
						params[i] = (double) (Adv.MAGIC_LOCALS_NUMBER + i + 1);
					} else if (parameterClass == String.class) {
						params[i] = Adv.MAGIC_LOCALS_String + (i + 1);
					} else if (Adv.canProxy(parameterClass)) {
						params[i] = Adv.buildProxyClass(parameterClass, (byte) (Adv.MAGIC_LOCALS_NUMBER + i + 1));
					} else if (parameterClass == Object.class) {
						throw new UnsupportedOperationException();
					} else {
						throw new UnsupportedOperationException();
					}
				}
				thisProxyMethod.invoke(simpleSampleCodeBuilder, params);
				Adv.logger.debug("exit magic method {}", method.getName());
			});
		}
	}

	public static <T> byte[] execMagicBuilder(ThreadLocal<AdvContext> _contextThreadLocal, String classname, T magicBuilderProxy) {
		// TODO Auto-generated method stub
		return null;
	}

}
