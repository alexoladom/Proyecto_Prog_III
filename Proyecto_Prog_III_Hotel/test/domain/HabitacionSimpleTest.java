package domain;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class HabitacionSimpleTest {

	
	private HabitacionSimple habitacion;
	@Before
	public void setUp() {
		habitacion = new HabitacionSimple();
		HabitacionSimple.setNumId(0);
	}
	
	@Test
	public void testGetNumcamas() {
		assertEquals(1,HabitacionSimple.getNumcamas());
	}
	
	@Test
	public void testToString() {
		String s =  String.format("HabitacionSimple %s, ocupado=%s, planta=%s, %s]", habitacion.id, habitacion.ocupado, habitacion.planta, habitacion.numero);
		assertEquals(s, habitacion.toString());
	}
	
}
