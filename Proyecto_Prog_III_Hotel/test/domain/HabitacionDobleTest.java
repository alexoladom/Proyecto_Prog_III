package domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class HabitacionDobleTest {

	
	private HabitacionDoble habitacion;
	@Before
	public void setUp() {
		Habitacion.setNumId(0);
		habitacion = new HabitacionDoble();
	}
	
	@Test 
	public void testHabitacionDoble() {
		assertNotNull(habitacion);
	}
	@Test
	public void  testGetNumcamas() {
		assertEquals(2, HabitacionDoble.getNumcamas());
	}
	
	@Test
	public void testGetPrecioPorNoche() {
		assertEquals(60, habitacion.getPrecioPorNoche(),0);
	}
	
	@Test
	public void testToString() {
		String s = String.format("HabitacionDoble %s, ocupado=%s, planta=%s, %s]", habitacion.id, habitacion.ocupado, habitacion.planta, habitacion.numero);
		assertEquals(s, habitacion.toString());
	}
}
