package domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class HabitacionSuiteTest {

	private HabitacionSuite habitacion;
	
	@Before 
	public void setUp() {
		habitacion = new HabitacionSuite();
		HabitacionSuite.setNumId(0);
	}
	
	@Test
	public void testHabitacionSuite() {
		assertNotNull(habitacion);
	}
	
	@Test
	public void testGetNumcamas() {
		assertEquals(HabitacionSuite.getNumcamas(), 4);
	}
	
	@Test
	public void testGetPrecioPorNoche() {
		assertEquals(100, habitacion.getPrecioPorNoche(),0);
	}
	
	@Test
	public void testToString() {
		String s =  String.format("HabitacionSuite %s, ocupado=%s, planta=%s, %s]", habitacion.id, habitacion.ocupado, habitacion.planta, habitacion.numero);
		assertEquals(s, habitacion.toString());
	}

}
