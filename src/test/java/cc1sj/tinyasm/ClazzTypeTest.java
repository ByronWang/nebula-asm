package cc1sj.tinyasm;

import static org.junit.Assert.assertEquals;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.objectweb.asm.Type;

public class ClazzTypeTest {

	@Test
	public void testClazzTypeTypeArray() {
		assertEquals("[Lcc1sj/tinyasm/ClazzTypeTest;", Clazz.of(ClazzTypeTest.class,true).toString());
	}
	
	@Test
	public void testClazzTypeType() {
		assertEquals("Lcc1sj/tinyasm/ClazzTypeTest;", new ClazzSimple(Type.getType(ClazzTypeTest.class)).toString());
	}

	@Test
	public void testClazzTypeClassOfQ() {
		assertEquals("Lcc1sj/tinyasm/ClazzTypeTest;", new ClazzSimple(ClazzTypeTest.class).toString());
	}

	@Test
	public void testClazzTypeString() {
		assertEquals("Lcc1sj/tinyasm/ClazzTypeTest;", new ClazzSimple(Type.getType(ClazzTypeTest.class)).toString());
	}

	@Test
	public void testGetType() {
		assertEquals("[Lcc1sj/tinyasm/ClazzTypeTest;", Clazz.of(ClazzTypeTest.class,true).getType().getDescriptor());
	}

	@Test
	public void testSignatureAnyway() {
		assertEquals("[Lcc1sj/tinyasm/ClazzTypeTest;", Clazz.of(ClazzTypeTest.class,true).signatureAnyway());
	}

	@Test
	public void testSignatureWhenNeed() {
		assertEquals(null, Clazz.of(ClazzTypeTest.class,true).signatureWhenNeed());
	}

	@Test
	public void testNeedSignature() {
		assertEquals(null, Clazz.of(ClazzTypeTest.class,true).signatureWhenNeed());
	}

	@Test
	public void testGetDescriptor() {
		assertEquals("[Lcc1sj/tinyasm/ClazzTypeTest;", Clazz.of(ClazzTypeTest.class,true).getDescriptor());
	}

	@Test
	public void testSignatureOf() {
		assertEquals("[Lcc1sj/tinyasm/ClazzTypeTest;", Clazz.of(ClazzTypeTest.class,true).signatureOf());
	}

	
	@Test
	public void testSignatureOfArrayList() {
		assertEquals("Ljava/util/ArrayList<Ljava/lang/String;>;", Clazz.of(ArrayList.class,String.class).signatureOf());
	}
	
	@Test
	public void testSignatureOfArrayListStringArray() {
		assertEquals("Ljava/util/ArrayList<[Ljava/lang/String;>;", Clazz.of(ArrayList.class,Clazz.of(String.class,true)).signatureOf());
	}
	@Test
	public void testSignatureOfArrayListStringArray222() {
		//extends HashMap<String, Map<String,String[]>> implements Map<String, Map<String,String[]>>,Serializable{
		assertEquals("Ljava/util/HashMap<Ljava/lang/String;Ljava/util/Map<Ljava/lang/String;[Ljava/lang/String;>;>;", Clazz.of(HashMap.class,Clazz.of(String.class),Clazz.of(Map.class,Clazz.of(String.class),Clazz.of(String[].class))).signatureOf());
		assertEquals("Ljava/util/Map<Ljava/lang/String;Ljava/util/Map<Ljava/lang/String;[Ljava/lang/String;>;>;", Clazz.of(Map.class,Clazz.of(String.class),Clazz.of(Map.class, Clazz.of(String.class),Clazz.of(String[].class))).signatureOf());
		assertEquals("Ljava/io/Serializable;", Clazz.of(Serializable.class).signatureOf());
	}
	
	
	
}
