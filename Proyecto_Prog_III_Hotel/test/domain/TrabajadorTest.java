package domain;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TrabajadorTest {

	private Trabajador trabajador;
	@Before
	public void setUp() {
		trabajador = new Trabajador();
	}
	
	@Test
	public void testGetSalario() {
		trabajador.setSalario(2);
		assertEquals(2, trabajador.getSalario(), 0);
	}
	
}
