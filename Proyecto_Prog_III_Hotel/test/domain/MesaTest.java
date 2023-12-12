package domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class MesaTest {

	
	private Mesa mesa;
	
	@Before 
	public void setUp() {
		Mesa.setNumId(0);
		mesa = new Mesa();
	}
	
	@Test
	public void testGetNumId() {
		assertEquals(0, Mesa.getNumId());
	}
	
	@Test
	public void testGetId() {
		assertEquals(0, mesa.getId());
	}
	
	@Test
	public void testIsOcupado() {
		mesa.setOcupado(true);
		assertTrue(mesa.isOcupado());
	}
	
	@Test
	public void testgGetNumero() {
		mesa.setNumero(10);
		assertEquals(10, mesa.getNumero());
	}
	
	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void testEquals() {
		Mesa.setNumId(2);
		Mesa mesa2 = new Mesa();
		mesa2.setNumero(23);
		
		assertTrue(mesa.equals(mesa));
		assertFalse(mesa.equals("s"));
		assertFalse(mesa.equals(null));
		assertFalse(mesa.equals(mesa2));
	}
	
	@Test
	public void testToString() {
		String s =  "Mesa [id=" + mesa.id + ", numero=" + mesa.numero + ", ocupado=" 
				+ mesa.ocupado + "]";
		
		assertEquals(s, mesa.toString());
	}
}
