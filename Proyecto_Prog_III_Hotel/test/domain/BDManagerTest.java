package domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;



public class BDManagerTest {

	
	private BDmanager manager;
	
	
	
	@Before
	public void setUp() {
		manager = new BDmanager();
		try {
			manager.connect("bd/test.db");
		} catch (BDexception e) {
			e.printStackTrace();
		}
		
	}
	
	@After
	public void after() {
		try {
			manager.disconnect();
		} catch (BDexception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetClientes() {
		ArrayList<Cliente> lista = new ArrayList<>();
		try {
			lista = (ArrayList<Cliente>) manager.getClientes();
		} catch (BDexception e) {
			e.printStackTrace();
		}
		assertEquals(lista.size(), 10);
		assertEquals(lista.get(0).getDni(), "a");
	}
	
	@Test
	public void testGetCliente() {
		try {
			Cliente c = manager.getCliente("a");
			assertNotNull(c);
			assertEquals("a", c.getDni());
		} catch (BDexception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testActualizarCliente() {
		try {
			Cliente c = manager.getCliente("a");
			c.setApellido1("b");
			manager.actualizarCliente(c);
			Cliente c2 = manager.getCliente("a");
			assertEquals("b", c2.getApellido1());
		} catch (BDexception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testgetReservas() {
		try {
			ArrayList<Reserva> l = (ArrayList<Reserva>) manager.getReservas();
			assertEquals(l.size(), 3);
			assertEquals(l.get(0).getId(), 213);
		} catch (BDexception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testgetReserva() {
		try {
			Reserva r = manager.getReserva(213);
			assertNotNull(r);
			assertEquals(213, r.getId());
		} catch (BDexception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testActualizarReserva() {
		try {
			Reserva r = manager.getReserva(213);
			r.setEstaPagado(true);
			manager.actualizarReserva(r);
			Reserva r2 = manager.getReserva(213);
			assertTrue(r2.estaPagado);
		} catch (BDexception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testgetTrabajadores() {
		try {
			ArrayList<Trabajador> l = (ArrayList<Trabajador>) manager.getTrabajadores();
			assertEquals(l.size(), 6);
			assertEquals(l.get(0).getDni(), "a");
		} catch (BDexception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testgetTrabajador() {
		try {
			Trabajador r = manager.getTrabajador("a");
			assertNotNull(r);
			assertEquals("a",r.getDni());
		} catch (BDexception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testActualizarTrabajador() {
		try {
			Trabajador r = manager.getTrabajador("a");
			r.setApellido1("b");
			manager.actualizarTrabajador(r);
			Trabajador r2 = manager.getTrabajador("a");
			assertEquals("b", r2.getApellido1());
		} catch (BDexception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testgetHabitaciones() {
		ArrayList<Habitacion> l = (ArrayList<Habitacion>) manager.getHabitaciones();
		assertEquals(l.size(), 12);
		System.out.println(l.get(0).getId());
		//Primero recoge las simples
		assertEquals(l.get(0).getId(), 2);
	}
	
	@Test
	public void testgetHabitacion() {
		try {
			Habitacion h = manager.getHabitacion(1);
			assertNotNull(h);
			assertEquals(1, h.getId());
		} catch (BDexception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testActualizarHabitacion() {
		try {
			Habitacion r = manager.getHabitacion(1);
			r.setOcupado(true);
			manager.actualizarHabitacion(r);
			Habitacion r2 = manager.getHabitacion(1);
			assertTrue(r2.isOcupado());
		} catch (BDexception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testgetTareas() {
		try {
			ArrayList<Tarea> l = (ArrayList<Tarea>) manager.getTareas();
			assertEquals(l.size(), 30);
			assertEquals(l.get(0).getId(), 1);
		} catch (BDexception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testgetTarea() {
		try {
			Tarea r = manager.getTarea(1);
			assertNotNull(r);
			assertEquals(1, r.getId());
		} catch (BDexception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testActualizarTarea() {
		try {
			Tarea r = manager.getTarea(1);
			r.setEstaCompletada(true);
			manager.actualizarTarea(r);
			Tarea r2 = manager.getTarea(1);
			assertTrue(r2.isEstaCompletada());
		} catch (BDexception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testgetPlazasParkingTodas() {
		try {
			ArrayList<PlazaParking> l = (ArrayList<PlazaParking>) manager.getPlazasParkingTodas();
			assertEquals(l.size(), 650);
			assertEquals(l.get(0).getId(), 26);
		} catch (BDexception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testgetPlazaParking() {
		try {
			PlazaParking r = manager.getPlazaParking(26);
			assertNotNull(r);
			assertEquals(26, r.getId());
		} catch (BDexception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testActualizarPlazaParking() {
		try {
			PlazaParking r = manager.getPlazaParking(26);
			r.setOcupada(true);
			manager.actualizarPlazaparking(r);
			PlazaParking r2 = manager.getPlazaParking(26);
			assertTrue(r2.isOcupada());
		} catch (BDexception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testgetMesas() {
		try {
			ArrayList<Mesa> l = (ArrayList<Mesa>) manager.getMesas();
			assertEquals(l.size(), 12);
			assertEquals(l.get(0).getId(), 1);
		} catch (BDexception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testgetMesa() {
		try {
			Mesa r = manager.getMesa(1);
			assertNotNull(r);
			assertEquals(1, r.getId());
		} catch (BDexception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testActualizarMesa() {
		try {
			Mesa r = manager.getMesa(1);
			r.setOcupado(true);
			manager.actualizarMesa(r);
			Mesa r2 = manager.getMesa(1);
			assertTrue(r2.isOcupado());
		} catch (BDexception e) {
			e.printStackTrace();
		}
	}
}
