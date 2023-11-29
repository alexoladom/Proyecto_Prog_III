package domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class ReservaTest {

	private Reserva reserva;
	@Before
	public void setUp() {
		reserva = new Reserva();
		Reserva.setNumId(0);
	}
	
	@Test 
	public void testReservaAtrib() {
		Reserva a = new Reserva(new  Cliente(), LocalDate.now(),LocalDate.now() ,
				false, new ArrayList<Habitacion> (), new ArrayList<PlazaParking> ());
		assertNotNull(a);
	}
	@Test
	public void testGetId() {
		assertEquals(reserva.id, reserva.getId());
	}

	@Test
	public void testGetCliente() {
		Cliente a = new Cliente();
		reserva.setCliente(a);
		assertEquals(a, reserva.getCliente());
	}
	
	@Test
	public void testGetFechaInicio() {
		LocalDate a = LocalDate.now();
		reserva.setFechaInicio(a);
		assertEquals(a, reserva.getFechaInicio());
	}
	@Test
	public void testGetFechaFinal() {
		LocalDate a = LocalDate.now();
		reserva.setFechaFinal(a);
		assertEquals(a, reserva.getFechaFinal());
	}
	
	@Test
	public void testGetPrecioCobrar() {
		HabitacionSimple a= new HabitacionSimple();
		Habitacion.setNumId(0);
		reserva.getListaHabitacionesReservadas().add(a);
		assertEquals(40, reserva.getPrecioCobrar(),0);
	}
	
	@Test
	public void testIsEstaPagado() {
		reserva.setEstaPagado(true);
		assertTrue(reserva.isEstaPagado());;
	}
	
	@Test
	public void testSetListaHabitacionesReservadas(){
		List<Habitacion> a =new ArrayList<>();
		a.add(new HabitacionSimple());
		Habitacion.setNumId(0);
		reserva.setListaHabitacionesReservadas(a);
		assertEquals(a, reserva.getListaHabitacionesReservadas());
		
	}
	
	@Test
	public void testGetListaPlazasParking() {
		List<PlazaParking> a = new ArrayList<>();
		reserva.setListaPlazasParking(a);
		assertEquals(a, reserva.getListaPlazasParking());
	}
	
	@Test
	public void testHashCode() {
		int a = Objects.hash(reserva.fechaFinal, reserva.fechaInicio, reserva.id);
		assertEquals(reserva.hashCode(), a);
	}

	
	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void testEquals() {
		assertTrue(reserva.equals(reserva));
		Reserva a = null;
		Habitacion b = new HabitacionSimple();
		Habitacion.setNumId(0);
		assertFalse(reserva.equals(a));
		assertFalse(reserva.equals(b));
		
		Reserva.setNumId(10);
		Reserva c = new Reserva();
		Reserva.setNumId(0);
		assertFalse(reserva.equals(c));
	}
	
	@Test
	public void testToString() {
		String a = String.format(
				"Reserva %s, con fecha de inicio %s, fecha final %s, precio: %s,  estado de pago: %s, plazas de parking: %s, numero de habitaciones: %s ", reserva.id,
				reserva.fechaInicio, reserva.fechaFinal,reserva.getPrecioCobrar(), reserva.estaPagado, reserva.listaPlazasParking.size(), reserva.listaHabitacionesReservadas.size());
		
		assertEquals(a, reserva.toString());
	}
	
	

}
