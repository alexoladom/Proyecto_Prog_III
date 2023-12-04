package domain;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ComparadorReservasPorPrecioTest {

	private ComparadorReservasPorPrecio comparador;
	private Reserva r1,r2;
	@Before
	public void setUp() {
		r1 = new Reserva();
		r2 = new Reserva();
		r2.getListaHabitacionesReservadas().add(new HabitacionSimple());
		comparador = new ComparadorReservasPorPrecio();
	}
	
	@Test
	public void testComparadorReservasPorFecha() {
		List<Reserva> lista = new ArrayList<>();
		lista.add(r2);
		lista.add(r1);
		lista.sort(comparador);
		assertEquals(0, lista.indexOf(r1));
	}
}
