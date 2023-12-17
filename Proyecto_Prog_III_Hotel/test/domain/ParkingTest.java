package domain;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.Objects;

import org.junit.Before;
import org.junit.Test;

public class ParkingTest {

	private Parking parking;
	
	@Before
	public void setUp() {
		parking = new Parking();
	}
	
	@Test 
	public void testParkingAtrin() {
		Parking a = new Parking( LocalDate.now(), false, 25);
		assertNotNull(a);
	}
	
	
	@Test
	public void testIsCompleto() {
		parking.setCompleto(false);
		assertFalse(parking.isCompleto());
	}
	
	
	@Test
	public void testGetDistribucion() {
		PlazaParking[][] distribucion = new PlazaParking[7][7];
		parking.setDistribucion(distribucion);
		assertArrayEquals(distribucion, parking.getDistribucion());
	}
	
	@Test
	public void testGetFecha() {
		parking.setFecha(LocalDate.now());
		assertEquals(LocalDate.now(), parking.getFecha());
	}
	
	@Test
	public void testComprobarPlazaDisponible() {
		Cliente c = new Cliente();
		Reserva r = new Reserva();
		PlazaParking p = new PlazaParking();
		r.getListaPlazasParking().add(p);
		c.getListaReservasCliente().add(r);
		r.setCliente(c);
		assertTrue(parking.comprobarPlazaDisponible(c, p));
	}
	@Test
	public void testToString() {
		String s= String.format("Parking %s, completo? %s, Plazas disponibles: %s", parking.fecha, parking.completo,
				parking.numPlazasDisponibles);
		
		assertEquals(parking.toString(), s);
	}
	@Test
	public void testHashCode() {
		int h =  Objects.hash(parking.fecha);
		assertEquals(parking.hashCode(), h);
	}
	
	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void testEquals() {
		Parking p = new Parking();
		assertTrue(parking.equals(parking));
		assertFalse(parking.equals(null));
		assertFalse(parking.equals(3));
		assertTrue(parking.equals(p));
	}
	
	
}
