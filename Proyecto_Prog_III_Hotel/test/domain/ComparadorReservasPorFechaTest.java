package domain;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ComparadorReservasPorFechaTest {

	private ComparadorReservasPorFecha comparador;
	private Reserva r1,r2;
	@Before
	public void setUp() {
		r1 = new Reserva();
		r2 = new Reserva();
		r2.setFechaInicio(LocalDate.of(3000, 10, 12));
		r1.setFechaInicio(LocalDate.now());
		comparador = new ComparadorReservasPorFecha();
	}
	
	@Test
	public void testComparadorReservasPorFecha() {
		List<Reserva> lista = new ArrayList<>();
		lista.add(r1);
		lista.add(r2);
		lista.sort(comparador);
		assertEquals(0, lista.indexOf(r1));
	}
}
