package domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class SalaEventosTest {

	private SalaEventos sala;
	
	@Before
	public void setUp() {
		sala = new SalaEventos();
		
	}
	
	@Test
	public void testSalaEventos() {
		assertNotNull(sala);
	}
	
	@Test
	public void isOcupado() {
		sala.setOcupado(true);
		assertTrue(sala.isOcupado());
	}
	
	@Test
	public void testToString() {
		String a =  String.format("La sala de eventos esta ocupada: %s", sala.isOcupado());
		assertEquals(a, sala.toString());
	}
}
