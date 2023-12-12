package domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ComedorTest {

	
	
	private Comedor comedor;
	
	@Before
	public void setUp() {
		comedor = new Comedor();
	}
	
	@Test
	public void testComedorParam() {
		Comedor c = new Comedor (true , new ArrayList<Mesa>());
		assertTrue(c.isCompleto());
	}
	
	@Test
	
	public void testComedor() {
		assertNotNull(comedor);
	}
	
	@Test
	public void testSetCompleto() {
		comedor.setCompleto(true);
		assertTrue(comedor.isCompleto());
	}
	
	@Test
	public void testGetNumId() {
		assertEquals(4, Comedor.getNumId());
	}
	
	@Test
	public void testGetId() {
		assertEquals(5, comedor.getId());
	}
	
	@Test
	public void testGetListaMesas() {
		List<Mesa> lista = new ArrayList<>();
		lista.add(new Mesa());
		
		comedor.setListaMesas(lista);
		
		assertEquals(lista, comedor.getListaMesas());
	}
	
	@Test
	public void testToString() {
		String s =  "Comedor [id=" + comedor.id + ", completo=" + comedor.completo + ", listaMesas=" + comedor.listaMesas + "]";
		
		assertEquals(s, comedor.toString());
	}
	
	
}
