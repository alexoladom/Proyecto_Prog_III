package domain;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;

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
		Parking a = new Parking( false,10,new PlazaParking[5][5] , LocalDate.now());
		assertNotNull(a);
	}
	
	@Test
	public void testGetNumId() {
		Parking.setNumId(0);
		assertEquals(0, Parking.getNumId());
	}
	
	@Test
	public void testIsCompleto() {
		parking.setCompleto(false);
		assertFalse(parking.isCompleto());
	}
	
	@Test
	public void testGetNumPlazasDisponibles() {
		parking.setNumPlazasDisponibles(10);
		parking.setNumPlazasDisponibles(-1);
		assertEquals(10, parking.getNumPlazasDisponibles());
		
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
	
}
