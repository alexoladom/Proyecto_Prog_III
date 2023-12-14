package domain;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Datos {
	
	private Logger logger = java.util.logging.Logger.getLogger("Logger");
	private static String FICHERO = "resources/data/datosHotel.dat";
	protected List<Trabajador> listaTrabajadores;
	protected List<Cliente> listaClientes;
	protected Map<Integer, List<Habitacion>> mapaHabitaciones;
	protected List<Reserva> listaReservas;
	protected List<Tarea> listaTareas;
	protected List<Mesa> listaComedor;//Creacion de la lista del comedor
	protected Map<String, Cliente> mapaClientesPorDNI;
	protected Map<LocalDate,Parking> mapaParkingPorFecha;
	protected Map<String, Trabajador> mapaTrabajadoresPorDNI;

	
	
	
	public Datos(List<Trabajador> listaTrabajadores, List<Cliente> listaClientes, Map<Integer, List<Habitacion>> MapaHabitaciones,
			List<Reserva> listaReservas, List<Tarea> listaTareas, List<Mesa> listaComedor, Map<String, Cliente> mapaClientesPorDNI,
			Map<String, Trabajador> mapaTrabajadoresPorDNI,Map<LocalDate, Parking> mapaParkingPorFecha) {
		this.listaTrabajadores = listaTrabajadores;
		this.listaClientes = listaClientes;
		this.mapaHabitaciones = MapaHabitaciones;
		this.listaReservas = listaReservas;
		this.listaTareas = listaTareas;
		this.listaComedor = listaComedor;//Comedor
		this.mapaClientesPorDNI = mapaClientesPorDNI;
		this.mapaTrabajadoresPorDNI = mapaTrabajadoresPorDNI;
		this.mapaParkingPorFecha = mapaParkingPorFecha;
	}
	
	public Datos() {
		this.listaTrabajadores = new ArrayList<Trabajador>();
		this.listaClientes = new ArrayList<Cliente>();
		this.mapaHabitaciones = new HashMap<Integer, List<Habitacion>>();
		this.listaReservas = new ArrayList<Reserva>();
		this.listaTareas = new ArrayList<Tarea>();
		this.listaComedor = new ArrayList<Mesa>();//Comedor
		this.mapaClientesPorDNI = new HashMap<String, Cliente> ();
		this.mapaTrabajadoresPorDNI = new HashMap<String, Trabajador> ();
		this.mapaParkingPorFecha = new HashMap<LocalDate, Parking>();


	}
	
	public void inicializarDatos() {
		setFichero("resources/data/datosHotel.dat");
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
		getListaTrabajadores().add(t1);
		getListaTrabajadores().add(t2);
		getListaTrabajadores().add(t3);
		getListaTrabajadores().add(t4);
		getListaTrabajadores().add(t5);
		getListaTrabajadores().add(t6);
		
		
		
		//Creacion de clientes de prueba
		
		Cliente cliente1 = new Cliente("123456789A", "Juan", "Pérez", "juan@example.com", "Calle Principal 123", LocalDate.of(1990, 5, 15), "contraseña123", "555-1234", new ArrayList<>(), LocalDate.of(2023, 12, 1), "src/Imagenes/imagenPerfilpng.png");
		Cliente cliente2 = new Cliente("987654321B", "María", "González", "maria@example.com", "Avenida Secundaria 456", LocalDate.of(1985, 9, 25), "contraseña456", "555-5678", new ArrayList<>(), LocalDate.of(2023, 11, 28), "src/Imagenes/imagenPerfilpng.png");
		Cliente cliente3 = new Cliente("111222333C", "Pedro", "Martínez", "pedro@example.com", "Plaza Principal 789", LocalDate.of(1982, 3, 8), "contraseña789", "555-9012", new ArrayList<>(), LocalDate.of(2023, 12, 5), "src/Imagenes/imagenPerfilpng.png");
		Cliente cliente4 = new Cliente("444555666D", "Ana", "López", "ana@example.com", "Calle Secundaria 789", LocalDate.of(1995, 7, 12), "contraseña789", "555-7890", new ArrayList<>(), LocalDate.of(2023, 12, 10), "src/Imagenes/imagenPerfilpng.png");
		Cliente cliente5 = new Cliente("777888999E", "Luis", "Ramírez", "luis@example.com", "Avenida Principal 456", LocalDate.of(1988, 10, 20), "contraseña456", "555-2345", new ArrayList<>(), LocalDate.of(2023, 11, 30), "src/Imagenes/imagenPerfilpng.png");
		Cliente cliente6 = new Cliente("112233445F", "Laura", "García", "laura@example.com", "Calle Principal 567", LocalDate.of(1980, 12, 5), "contraseña567", "555-6789", new ArrayList<>(), LocalDate.of(2023, 12, 3), "src/Imagenes/imagenPerfilpng.png");
		Cliente cliente7 = new Cliente("998877665G", "Carlos", "Fernández", "carlos@example.com", "Avenida Secundaria 890", LocalDate.of(1992, 4, 18), "contraseña890", "555-0123", new ArrayList<>(), LocalDate.of(2023, 12, 8), "src/Imagenes/imagenPerfilpng.png");
		Cliente cliente8 = new Cliente("554433221H", "Elena", "Díaz", "elena@example.com", "Plaza Principal 234", LocalDate.of(1987, 8, 30), "contraseña234", "555-4567", new ArrayList<>(), LocalDate.of(2023, 12, 12), "src/Imagenes/imagenPerfilpng.png");
		Cliente cliente9 = new Cliente("678905432I", "Javier", "Hernández", "javier@example.com", "Calle Secundaria 567", LocalDate.of(1997, 2, 9), "contraseña567", "555-8901", new ArrayList<>(), LocalDate.of(2023, 12, 6), "src/Imagenes/imagenPerfilpng.png");
		Cliente cliente10 = new Cliente("543216789J", "Sofía", "López", "sofia@example.com", "Avenida Principal 890", LocalDate.of(1983, 6, 22), "contraseña890", "555-2345", new ArrayList<>(), LocalDate.of(2023, 12, 15), "src/Imagenes/imagenPerfilpng.png");
		
		
		getListaClientes().add(cliente1);
		getListaClientes().add(cliente2);
		getListaClientes().add(cliente3);
		getListaClientes().add(cliente4);
		getListaClientes().add(cliente5);
		getListaClientes().add(cliente6);
		getListaClientes().add(cliente7);
		getListaClientes().add(cliente8);
		getListaClientes().add(cliente9);
		getListaClientes().add(cliente10);
		
		
		//Habitaciones planta A
		HabitacionSimple h1 = new HabitacionSimple(true, 1, 101);
		HabitacionSimple h2 = new HabitacionSimple(false, 1, 102);
		HabitacionDoble h3 = new HabitacionDoble(false, 1, 103);
		HabitacionSuite h4 = new HabitacionSuite(false, 1, 104);
		getMapaHabitaciones().put(0, new ArrayList<>());
		getMapaHabitaciones().get(0).add(h1);
		getMapaHabitaciones().get(0).add(h2);
		getMapaHabitaciones().get(0).add(h3);
		getMapaHabitaciones().get(0).add(h4);
		//Habitaciones planta B
		HabitacionSimple h5 = new HabitacionSimple(false, 2, 201);
		HabitacionSimple h6 = new HabitacionSimple(false, 2, 202);
		HabitacionDoble h7 = new HabitacionDoble(false, 2, 203);
		HabitacionSuite h8 = new HabitacionSuite(false, 2, 204);
		getMapaHabitaciones().put(1, new ArrayList<>());
		getMapaHabitaciones().get(1).add(h5);
		getMapaHabitaciones().get(1).add(h6);
		getMapaHabitaciones().get(1).add(h7);
		getMapaHabitaciones().get(1).add(h8);
		//Habitaciones planta C
		HabitacionSimple h9 = new HabitacionSimple(false, 3, 301);
		HabitacionSimple h10 = new HabitacionSimple(false, 3, 302);
		HabitacionDoble h11 = new HabitacionDoble(false, 3, 303);
		HabitacionSuite h12 = new HabitacionSuite(false, 3, 304);
		getMapaHabitaciones().put(2, new ArrayList<>());
		getMapaHabitaciones().get(2).add(h9);
		getMapaHabitaciones().get(2).add(h10);
		getMapaHabitaciones().get(2).add(h11);
		getMapaHabitaciones().get(2).add(h12);
		//Lugares del comedor
		Mesa m1 = new Mesa(0, false);
		getListaComedor().add(m1);
		Mesa m2 = new Mesa(1, true);
		getListaComedor().add(m2);
		Mesa m3 = new Mesa(2, false);
		getListaComedor().add(m3);
		Mesa m4 = new Mesa(3, false);
		getListaComedor().add(m4);
		Mesa m5 = new Mesa(4, false);
		getListaComedor().add(m5);
		Mesa m6 = new Mesa(5, false);
		getListaComedor().add(m6);
		Mesa m7 = new Mesa(6, false);
		getListaComedor().add(m7);
		Mesa m8 = new Mesa(7, false);
		getListaComedor().add(m8);
		Mesa m9 = new Mesa(8, false);
		getListaComedor().add(m9);
		Mesa m10 = new Mesa(9, false);
		getListaComedor().add(m10);
		Mesa m11 = new Mesa(10, false);
		getListaComedor().add(m11);
		Mesa m12 = new Mesa(11, false);
		getListaComedor().add(m12);
		
		for (Trabajador trabajador : getListaTrabajadores()) {
			getMapaTrabajadoresPorDNI().putIfAbsent(trabajador.getDni(), trabajador);
		}
		for (Cliente cliente : getListaClientes()) {
			getMapaClientesPorDNI().putIfAbsent(cliente.getDni(), cliente);
		}
		for (int i = 0; i < 15; i++) {
			mapaParkingPorFecha.put(LocalDate.now().plusDays(i), new Parking());
		}
		logger.info("DATOS INICIALIZADOS");

	}
	
	public Map<String, Trabajador> getMapaTrabajadoresPorDNI() {
		return mapaTrabajadoresPorDNI;
	}

	public void setMapaTrabajadoresPorDNI(Map<String, Trabajador> mapaTrabajadoresPorDNI) {
		this.mapaTrabajadoresPorDNI = mapaTrabajadoresPorDNI;
	}

	public List<Trabajador> getListaTrabajadores() {
		return listaTrabajadores;
	}

	public void setListaTrabajadores(List<Trabajador> listaTrabajadores) {
		this.listaTrabajadores = listaTrabajadores;
	}

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public Map<Integer, List<Habitacion>> getMapaHabitaciones() {
		return mapaHabitaciones;
	}
	
	public List<Mesa> getListaComedor() {//Comedor
		return listaComedor;
	}

	public void setListaComedor(List<Mesa> listaComedor) {//Comedor
		this.listaComedor = listaComedor;
	}

	public void setMapaHabitaciones(Map<Integer, List<Habitacion>> mapaHabitaciones) {
		this.mapaHabitaciones = mapaHabitaciones;
	}

	public List<Reserva> getListaReservas() {
		return listaReservas;
	}

	public void setListaReservas(List<Reserva> listaReservas) {
		this.listaReservas = listaReservas;
	}

	public List<Tarea> getListaTareas() {
		return listaTareas;
	}

	public void setListaTareas(List<Tarea> listaTareas) {
		this.listaTareas = listaTareas;
	}

	public static String getFichero() {
		return FICHERO;
	}

	public static void setFichero(String fichero) {
		FICHERO = fichero;
	}
	public Map<LocalDate, Parking> getMapaParkingPorFecha() {
		return mapaParkingPorFecha;
	}

	public void setMapaParkingPorFecha(Map<LocalDate, Parking> mapaParkingPorFecha) {
		this.mapaParkingPorFecha = mapaParkingPorFecha;
	}

	public Map<String, Cliente> getMapaClientesPorDNI() {
		return mapaClientesPorDNI;
	}

	public void setMapaClientesPorDNI(Map<String, Cliente> mapaClientesPorDNI) {
		this.mapaClientesPorDNI = mapaClientesPorDNI;
	}

	public boolean comprobarContraseñaCliente(String dni, String contraseña) {
		if (mapaClientesPorDNI.containsKey(dni)){
			if(mapaClientesPorDNI.get(dni).getContraseña().equals(contraseña)){
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}
	
	public boolean comprobarContraseñaTrabajador(String dni, String contraseña) {
		if (mapaTrabajadoresPorDNI.containsKey(dni)){
			if(mapaTrabajadoresPorDNI.get(dni).getContraseña().equals(contraseña)){
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(mapaHabitaciones, listaClientes, listaComedor, listaReservas, listaTareas,
				listaTrabajadores, mapaClientesPorDNI, mapaParkingPorFecha, mapaTrabajadoresPorDNI);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Datos other = (Datos) obj;
		return Objects.equals(mapaHabitaciones, other.mapaHabitaciones)
				&& Objects.equals(listaClientes, other.listaClientes)
				&& Objects.equals(listaComedor, other.listaComedor)
				&& Objects.equals(listaReservas, other.listaReservas) 
				&& Objects.equals(listaTareas, other.listaTareas)
				&& Objects.equals(listaTrabajadores, other.listaTrabajadores)
				&& Objects.equals(mapaClientesPorDNI, other.mapaClientesPorDNI)
				&& Objects.equals(mapaParkingPorFecha, other.mapaParkingPorFecha)
				&& Objects.equals(mapaTrabajadoresPorDNI, other.mapaTrabajadoresPorDNI);
	}

	public void guardarDatos() {
		
		try (FileOutputStream fos = new FileOutputStream (FICHERO);
			ObjectOutputStream oos = new ObjectOutputStream(fos)){
				oos.writeObject(listaClientes);
				oos.writeObject(mapaHabitaciones);
				oos.writeObject(listaReservas);
				oos.writeObject(listaTareas);
				oos.writeObject(listaTrabajadores);
				oos.writeObject(mapaClientesPorDNI);
				oos.writeObject(mapaTrabajadoresPorDNI);
				oos.writeObject(mapaParkingPorFecha);
				oos.writeObject(mapaHabitaciones);
				oos.writeObject(Reserva.getNumId());
				logger.info("GUARDANDO DATOS...");

			}catch (FileNotFoundException e) {
			logger.log(Level.WARNING, "FICHERO NO ENCONTRADO AL GUARDAR LOS DATOS");
			
			}catch (IOException e) {
			logger.log(Level.WARNING, "ERROR DE SALIDA AL GUARDAR LOS DATOS");
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public void cargarDatos() {
		try(FileInputStream fis = new FileInputStream(FICHERO);
			ObjectInputStream ois = new ObjectInputStream(fis)){
			
			this.listaClientes = (List<Cliente>) ois.readObject();
			this.mapaHabitaciones = (Map<Integer, List<Habitacion>>) ois.readObject();
			this.listaReservas = (List<Reserva>) ois.readObject();
			this.listaTareas = (List<Tarea>) ois.readObject();
			this.listaTrabajadores = (List<Trabajador>) ois.readObject();
			this.mapaClientesPorDNI= (Map<String, Cliente>) ois.readObject();
			this.mapaTrabajadoresPorDNI = (Map<String, Trabajador>) ois.readObject();
			this.mapaParkingPorFecha= (Map<LocalDate, Parking>) ois.readObject();
			this.mapaHabitaciones=(Map<Integer, List<Habitacion>>) ois.readObject();
			Reserva.setNumId((int) ois.readObject());
			logger.info("CARGANDO DATOS...");

			
		} catch (FileNotFoundException e) {
			logger.log(Level.WARNING, "FICHERO NO ENCONTRADO AL CARGAR LOS DATOS");
		} catch (IOException e) {
			logger.log(Level.WARNING, "ERROR EN LA ENTRADA EL CARGAR LOS DATOS");
		} catch (ClassNotFoundException e) {
			logger.log(Level.WARNING, "CLASE NO ENCONTRADA AL CARGAR LOS DATOS");
		}
	}
	
	
}
