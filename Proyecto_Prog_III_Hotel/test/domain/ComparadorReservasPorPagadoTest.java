package domain;

import static org.junit.Assert.assertEquals;


import org.junit.Before;
import org.junit.Test;

public class ComparadorReservasPorPagadoTest {

	private ComparadorReservasPorPagado comparador;
	private Reserva r1,r2;
	@Before
	public void setUp() {
		r1 = new Reserva();
		r2 = new Reserva();
		r1.setEstaPagado(true);
		comparador = new ComparadorReservasPorPagado();
	}
	
	@Test
	public void testCompare() {
		assertEquals(-1, comparador.compare(r1, r2));
		r1.setEstaPagado(false);
		assertEquals(0, comparador.compare(r1, r2));
		r2.setEstaPagado(true);
		assertEquals(1, comparador.compare(r1, r2));
		
	}
}
