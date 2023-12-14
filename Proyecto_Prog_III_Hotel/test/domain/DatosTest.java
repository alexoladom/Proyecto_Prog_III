package domain;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.junit.Before;
import org.junit.Test;

public class DatosTest {

	private Datos datos;
	
	@Before
	public void setUp(){
		datos = new Datos();
		Datos.setFichero("resources/data/datosTest.data");
	}
	@Test 
	public void testDatos() {
		assertNotNull(datos);
	}
	
	@Test 
	public void testDatos2() {
		Datos datos2 = new Datos(new ArrayList<Trabajador> (), new ArrayList<Cliente> (), new HashMap<Integer, List<Habitacion>> (),
				new ArrayList<Reserva> (), new ArrayList<Tarea> (), new ArrayList<Mesa> (),new  HashMap<String, Cliente> (),
				new HashMap<String, Trabajador> (),new HashMap<LocalDate, Parking> ());
		assertNotNull(datos2);
	}
	@Test
	public void testInicializarDatos() {
		Habitacion.setNumId(0);
		Mesa.setNumId(0);
		Parking.setNumId(0);
		datos.inicializarDatos();
		LocalDate a = LocalDate.of(1999, 9, 11);
		Trabajador t1 = new Trabajador("18087363T", "Mario", "Martinez","mario@gmail.com", "Calle Alfonso 2", a, "123", "673821992", 1200.00, 0, new ArrayList<>(), new ArrayList<>(),"src/Imagenes/imagenPerfilpng.png");
		LocalDate b = LocalDate.of(1989, 7, 23);
		Trabajador t2 = new Trabajador("18177653W", "Jorge", "Gonzalez","jorge@gmail.com", "Avenida de la Paz", b, "123", "673927462", 1200.00, 0, new ArrayList<>(), new ArrayList<>(),"src/Imagenes/imagenPerfilpng.png");
		LocalDate c = LocalDate.of(1995, 3, 18);
		Trabajador t3 = new Trabajador("18087826Y", "Alex", "Merino","alex@gmail.com", "Calle Deusto", c, "123", "673826592", 1200.00, 0, new ArrayList<>(), new ArrayList<>(),"src/Imagenes/imagenPerfilpng.png");
		LocalDate d = LocalDate.of(1993, 2, 6);
		Trabajador t4 = new Trabajador("18072643T", "Iñigo", "Jimenez","iñigo@gmail.com", "Calle Adolfo Dominguez", d, "123", "673826392", 1200.00, 0, new ArrayList<>(), new ArrayList<>(),"src/Imagenes/imagenPerfilpng.png");
		LocalDate e = LocalDate.of(1991, 1, 12);
		Trabajador t5 = new Trabajador("18562153W", "Gonzalo", "Mitegui","gonzalo@gmail.com", "Calle Ubayar", e, "123", "623627462", 1200.00, 0, new ArrayList<>(), new ArrayList<>(),"src/Imagenes/imagenPerfilpng.png");
		LocalDate f = LocalDate.of(2000, 3, 7);
		Trabajador t6 = new Trabajador("18927456Y", "Daniel", "Larrea","daniel@gmail.com", "Calle Ugasko Bidea", f, "123", "676127592", 1200.00, 0, new ArrayList<>(), new ArrayList<>(),"src/Imagenes/imagenPerfilpng.png");
		List <Trabajador> listaTrabajadores = new ArrayList<Trabajador>();
		listaTrabajadores.add(t1);
		listaTrabajadores.add(t2);
		listaTrabajadores.add(t3);
		listaTrabajadores.add(t4);
		listaTrabajadores.add(t5);
		listaTrabajadores.add(t6);
		
		assertArrayEquals(datos.getListaTrabajadores().toArray(), listaTrabajadores.toArray());
		assertEquals(datos.getListaTrabajadores(), listaTrabajadores);
		
		//Habitaciones planta A
		Habitacion.setNumId(0);
		HabitacionSimple h1 = new HabitacionSimple(false, 1, 101);
		HabitacionSimple h2 = new HabitacionSimple(false, 1, 102);
		HabitacionDoble h3 = new HabitacionDoble(false, 1, 103);
		HabitacionSuite h4 = new HabitacionSuite(false, 1, 104);
		Map<Integer,List<Habitacion>> mapaHabitaciones = new HashMap<>();
		mapaHabitaciones.put(0, new ArrayList<>());
		mapaHabitaciones.get(0).add(h1);
		mapaHabitaciones.get(0).add(h2);
		mapaHabitaciones.get(0).add(h3);
		mapaHabitaciones.get(0).add(h4);
		//Habitaciones planta B
		HabitacionSimple h5 = new HabitacionSimple(false, 2, 201);
		HabitacionSimple h6 = new HabitacionSimple(false, 2, 202);
		HabitacionDoble h7 = new HabitacionDoble(false, 2, 203);
		HabitacionSuite h8 = new HabitacionSuite(false, 2, 204);
		mapaHabitaciones.put(1, new ArrayList<>());
		mapaHabitaciones.get(1).add(h5);
		mapaHabitaciones.get(1).add(h6);
		mapaHabitaciones.get(1).add(h7);
		mapaHabitaciones.get(1).add(h8);
		//Habitaciones planta C
		HabitacionSimple h9 = new HabitacionSimple(false, 3, 301);
		HabitacionSimple h10 = new HabitacionSimple(false, 3, 302);
		HabitacionDoble h11 = new HabitacionDoble(false, 3, 303);
		HabitacionSuite h12 = new HabitacionSuite(false, 3, 304);
		mapaHabitaciones.put(2, new ArrayList<>());
		mapaHabitaciones.get(2).add(h9);
		mapaHabitaciones.get(2).add(h10);
		mapaHabitaciones.get(2).add(h11);
		mapaHabitaciones.get(2).add(h12);
		
		assertEquals(datos.getMapaHabitaciones(), mapaHabitaciones);
		
		
		//Lugares del comedor
		Mesa.setNumId(0);
		List<Mesa> listaComedor = new ArrayList<>();
		Mesa m1 = new Mesa(0, false);
		listaComedor.add(m1);
		Mesa m2 = new Mesa(1, false);
		listaComedor.add(m2);
		Mesa m3 = new Mesa(2, false);
		listaComedor.add(m3);
		Mesa m4 = new Mesa(3, false);
		listaComedor.add(m4);
		Mesa m5 = new Mesa(4, false);
		listaComedor.add(m5);
		Mesa m6 = new Mesa(5, false);
		listaComedor.add(m6);
		Mesa m7 = new Mesa(6, false);
		listaComedor.add(m7);
		Mesa m8 = new Mesa(7, false);
		listaComedor.add(m8);
		Mesa m9 = new Mesa(8, false);
		listaComedor.add(m9);
		Mesa m10 = new Mesa(9, false);
		listaComedor.add(m10);
		Mesa m11 = new Mesa(10, false);
		listaComedor.add(m11);
		Mesa m12 = new Mesa(11, false);
		listaComedor.add(m12);
		
		assertEquals(datos.getListaComedor(), listaComedor);
		
		Map <String,Trabajador> mapaTraDNI = new HashMap<>();
		for (Trabajador trabajador : listaTrabajadores) {
			mapaTraDNI.putIfAbsent(trabajador.getDni(), trabajador);
		}
		
		assertEquals(datos.getMapaTrabajadoresPorDNI(),mapaTraDNI);
		
		Parking.setNumId(0);
		Map<LocalDate,Parking> mapaParkingFecha = new HashMap<>();
		for (int i = 0; i < 15; i++) {
			mapaParkingFecha.put(LocalDate.now().plusDays(i), new Parking());
		}
		
		assertEquals(datos.getMapaParkingPorFecha(), mapaParkingFecha);
		
		Reserva.setNumId(0);
		

		
	}
	
	@Test
	public void testGetMapaTrabajadoresPorDNI() {
		Map<String,Trabajador> mapa = new HashMap<>();
		mapa.put("abc", new Trabajador());
		datos.setMapaTrabajadoresPorDNI(mapa);
		assertEquals(mapa, datos.getMapaTrabajadoresPorDNI());
	}
	
	@Test 
	public void setListaTrabajadores() {
		List<Trabajador> lista = new ArrayList<>();
		lista.add(new Trabajador());
		datos.setListaTrabajadores(lista);
		assertEquals(datos.getListaTrabajadores(), lista);
	}
	
	@Test 
	public void testSetListaClientes() {
		List<Cliente> lista = new ArrayList<>();
		lista.add(new Cliente());
		datos.setListaClientes(lista);
		assertEquals(datos.getListaClientes(), lista);
	}
	
	@Test
	public void testSetListaComedor() {
		List<Mesa> lista = new ArrayList<>();
		lista.add(new Mesa());
		datos.setListaComedor(lista);
		assertEquals(datos.getListaComedor(), lista);
	}
	
	@Test 
	public void testSetMapaHabitaciones() {
		Map<Integer,List<Habitacion>> mapa = new HashMap<>();
		mapa.put(0,new ArrayList<Habitacion>());
		mapa.get(0).add(new HabitacionSimple());
		datos.setMapaHabitaciones(mapa);
		assertEquals(datos.getMapaHabitaciones(), mapa);
	}
	
	@Test
	public void testSetListaReservas() {
		List<Reserva> lista = new ArrayList<>();
		lista.add(new Reserva());
		datos.setListaReservas(lista);
		assertEquals(datos.getListaReservas(), lista);
	}
	
	@Test
	public void testGetListaTareas() {
		List<Tarea> lista = new ArrayList<>();
		lista.add(new Tarea());
		datos.setListaTareas(lista);
		assertEquals(datos.getListaTareas(), lista);
	}
	
	@Test
	public void testSetFichero() {
		Datos.setFichero("ruta");
		assertEquals("ruta", Datos.getFichero());
	}
	
	@Test 
	public void testSetMapaParkingPorFecha() {
		Map<LocalDate,Parking> mapa = new HashMap<>();
		mapa.put(LocalDate.now(),new Parking());
		datos.setMapaParkingPorFecha(mapa);
		assertEquals(datos.getMapaParkingPorFecha(), mapa);
	}
	
	@Test
	public void testSetMapaClientesPorDNI() {
		Map<String,Cliente> mapa = new HashMap<>();
		mapa.put("123",new Cliente());
		datos.setMapaClientesPorDNI(mapa);
		assertEquals(datos.getMapaClientesPorDNI(), mapa);
	}
	
	@Test 
	public void testComprobarContraseñaClienteTrue() {
		Cliente t = new Cliente();
		t.setDni("abc");
		t.setContraseña("123");
		datos.getMapaClientesPorDNI().put("abc",t);
		assertTrue(datos.comprobarContraseñaCliente("abc","123"));
		
	}
	
	@Test
	public void testComprobarContraseñaClienteFalse() {
		Cliente t = new Cliente();
		t.setDni("abc");
		t.setContraseña("123");
		datos.getMapaClientesPorDNI().put("abc",t);
		assertFalse(datos.comprobarContraseñaCliente("abc","0"));
		
	}
	
	@Test
	public void testComprobarContraseñaClienteDNINotFound() {
		Cliente t = new Cliente();
		t.setDni("abc");
		t.setContraseña("123");
		datos.getMapaClientesPorDNI().put("abc",t);
		assertFalse(datos.comprobarContraseñaCliente("0","123"));
		
	}
	
	@Test 
	public void testComprobarContraseñaTrabajadorTrue() {
		Trabajador t = new Trabajador();
		t.setDni("abc");
		t.setContraseña("123");
		datos.getMapaTrabajadoresPorDNI().put("abc",t);
		assertTrue(datos.comprobarContraseñaTrabajador("abc","123"));
		
	}
	
	@Test
	public void testComprobarContraseñaTrabajadorFalse() {
		Trabajador t = new Trabajador();
		t.setDni("abc");
		t.setContraseña("123");
		datos.getMapaTrabajadoresPorDNI().put("abc",t);
		assertFalse(datos.comprobarContraseñaTrabajador("abc","0"));
		
	}
	
	@Test
	public void testComprobarContraseñaTrabajadorDNINotFound() {
		Trabajador t = new Trabajador();
		t.setDni("abc");
		t.setContraseña("123");
		datos.getMapaTrabajadoresPorDNI().put("abc",t);
		assertFalse(datos.comprobarContraseñaTrabajador("0","123"));
		
	}
	
	@Test
	public void testGuardarDatos(){
		Mesa.setNumId(0);
		Habitacion.setNumId(0);
		Mesa.setNumId(0);
		Reserva.setNumId(0);
		Parking.setNumId(0);


		datos.inicializarDatos();
		LocalDate a = LocalDate.of(1999, 9, 11);
		Trabajador t1 = new Trabajador("18087363T", "Mario", "Martinez","mario@gmail.com", "Calle Alfonso 2", a, "123", "673821992", 1200.00, 0, new ArrayList<>(), new ArrayList<>(),"src/Imagenes/imagenPerfilpng.png");
		LocalDate b = LocalDate.of(1989, 7, 23);
		Trabajador t2 = new Trabajador("18177653W", "Jorge", "Gonzalez","jorge@gmail.com", "Avenida de la Paz", b, "123", "673927462", 1200.00, 0, new ArrayList<>(), new ArrayList<>(),"src/Imagenes/imagenPerfilpng.png");
		LocalDate c = LocalDate.of(1995, 3, 18);
		Trabajador t3 = new Trabajador("18087826Y", "Alex", "Merino","alex@gmail.com", "Calle Deusto", c, "123", "673826592", 1200.00, 0, new ArrayList<>(), new ArrayList<>(),"src/Imagenes/imagenPerfilpng.png");
		LocalDate d = LocalDate.of(1993, 2, 6);
		Trabajador t4 = new Trabajador("18072643T", "Iñigo", "Jimenez","iñigo@gmail.com", "Calle Adolfo Dominguez", d, "123", "673826392", 1200.00, 0, new ArrayList<>(), new ArrayList<>(),"src/Imagenes/imagenPerfilpng.png");
		LocalDate e = LocalDate.of(1991, 1, 12);
		Trabajador t5 = new Trabajador("18562153W", "Gonzalo", "Mitegui","gonzalo@gmail.com", "Calle Ubayar", e, "123", "623627462", 1200.00, 0, new ArrayList<>(), new ArrayList<>(),"src/Imagenes/imagenPerfilpng.png");
		LocalDate f = LocalDate.of(2000, 3, 7);
		Trabajador t6 = new Trabajador("18927456Y", "Daniel", "Larrea","daniel@gmail.com", "Calle Ugasko Bidea", f, "123", "676127592", 1200.00, 0, new ArrayList<>(), new ArrayList<>(),"src/Imagenes/imagenPerfilpng.png");
		List <Trabajador> listaTrabajadores = new ArrayList<Trabajador>();
		listaTrabajadores.add(t1);
		listaTrabajadores.add(t2);
		listaTrabajadores.add(t3);
		listaTrabajadores.add(t4);
		listaTrabajadores.add(t5);
		listaTrabajadores.add(t6);
		
		
		//Habitaciones planta A
		Habitacion.setNumId(0);
		HabitacionSimple h1 = new HabitacionSimple(false, 1, 101);
		HabitacionSimple h2 = new HabitacionSimple(false, 1, 102);
		HabitacionDoble h3 = new HabitacionDoble(false, 1, 103);
		HabitacionSuite h4 = new HabitacionSuite(false, 1, 104);
		Map<Integer,List<Habitacion>> mapaHabitaciones = new HashMap<>();
		mapaHabitaciones.put(0, new ArrayList<>());
		mapaHabitaciones.get(0).add(h1);
		mapaHabitaciones.get(0).add(h2);
		mapaHabitaciones.get(0).add(h3);
		mapaHabitaciones.get(0).add(h4);
		//Habitaciones planta B
		HabitacionSimple h5 = new HabitacionSimple(false, 2, 201);
		HabitacionSimple h6 = new HabitacionSimple(false, 2, 202);
		HabitacionDoble h7 = new HabitacionDoble(false, 2, 203);
		HabitacionSuite h8 = new HabitacionSuite(false, 2, 204);
		mapaHabitaciones.put(1, new ArrayList<>());
		mapaHabitaciones.get(1).add(h5);
		mapaHabitaciones.get(1).add(h6);
		mapaHabitaciones.get(1).add(h7);
		mapaHabitaciones.get(1).add(h8);
		//Habitaciones planta C
		HabitacionSimple h9 = new HabitacionSimple(false, 3, 301);
		HabitacionSimple h10 = new HabitacionSimple(false, 3, 302);
		HabitacionDoble h11 = new HabitacionDoble(false, 3, 303);
		HabitacionSuite h12 = new HabitacionSuite(false, 3, 304);
		mapaHabitaciones.put(2, new ArrayList<>());
		mapaHabitaciones.get(2).add(h9);
		mapaHabitaciones.get(2).add(h10);
		mapaHabitaciones.get(2).add(h11);
		mapaHabitaciones.get(2).add(h12);
		
		
		
		//Lugares del comedor
		Mesa.setNumId(0);
		List<Mesa> listaComedor = new ArrayList<>();
		Mesa m1 = new Mesa(0, false);
		listaComedor.add(m1);
		Mesa m2 = new Mesa(1, false);
		listaComedor.add(m2);
		Mesa m3 = new Mesa(2, false);
		listaComedor.add(m3);
		Mesa m4 = new Mesa(3, false);
		listaComedor.add(m4);
		Mesa m5 = new Mesa(4, false);
		listaComedor.add(m5);
		Mesa m6 = new Mesa(5, false);
		listaComedor.add(m6);
		Mesa m7 = new Mesa(6, false);
		listaComedor.add(m7);
		Mesa m8 = new Mesa(7, false);
		listaComedor.add(m8);
		Mesa m9 = new Mesa(8, false);
		listaComedor.add(m9);
		Mesa m10 = new Mesa(9, false);
		listaComedor.add(m10);
		Mesa m11 = new Mesa(10, false);
		listaComedor.add(m11);
		Mesa m12 = new Mesa(11, false);
		listaComedor.add(m12);
		
		
		Map <String,Trabajador> mapaTraDNI = new HashMap<>();
		for (Trabajador trabajador : listaTrabajadores) {
			mapaTraDNI.putIfAbsent(trabajador.getDni(), trabajador);
		}
		
		
		Parking.setNumId(0);
		Map<LocalDate,Parking> mapaParkingFecha = new HashMap<>();
		for (int i = 0; i < 15; i++) {
			mapaParkingFecha.put(LocalDate.now().plusDays(i), new Parking());
		}
		
		
		
		List<Reserva> listaReservas = new ArrayList<>();
		List<Cliente> listaClientes = new ArrayList<>();
		Map<String,Cliente> mapaCliDNI = new HashMap<>();
		Reserva.setNumId(0);
		
		Cliente c1 = new Cliente();
		c1.setDni("abc");
		c1.setContraseña("123");
		Reserva r = new Reserva();
		c1.getListaReservasCliente().add(r);
		listaReservas.add(r);
		listaClientes.add(c1);
		mapaCliDNI.put("abc", c1);
		
		Cliente c2 = new Cliente();
		c2.setDni("a");
		c2.setContraseña("a");
		Reserva r2 = new Reserva();
		c2.getListaReservasCliente().add(r2);
		listaReservas.add(r2);
		listaClientes.add(c2);
		mapaCliDNI.put("a", c2);
		
		
		assertArrayEquals(datos.getListaTrabajadores().toArray(), listaTrabajadores.toArray());
		assertEquals(datos.getListaTrabajadores(), listaTrabajadores);
		
		assertEquals(datos.getMapaHabitaciones(), mapaHabitaciones);

		assertEquals(datos.getListaComedor(), listaComedor);

		assertEquals(datos.getMapaTrabajadoresPorDNI(),mapaTraDNI);

		assertEquals(datos.getMapaParkingPorFecha(), mapaParkingFecha);

	
		
	}
	@Test
	public void testHashCode() {
		Datos b = new Datos();
		b.inicializarDatos();
		int a = Objects.hash(b.mapaHabitaciones, b.listaClientes, b.listaComedor, b.listaReservas, b.listaTareas,
				b.listaTrabajadores, b.mapaClientesPorDNI, b.mapaParkingPorFecha, b.mapaTrabajadoresPorDNI);
		 assertEquals(b.hashCode(), a);
	}
	
}
