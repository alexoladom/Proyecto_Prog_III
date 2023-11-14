package Clases;

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

public class Datos {
	
	private static final String FICHERO = "datosHotel.dat";
	protected List<Trabajador> listaTrabajadores;
	protected List<Cliente> listaClientes;
	protected Map<Integer, List<Habitacion>> MapaHabitaciones;
	protected List<Reserva> listaReservas;
	protected List<Tarea> listaTareas;
	protected Map<String, Cliente> mapaClientesPorDNI;
	protected Map<LocalDate,Parking> mapaParkingPorFecha;
	protected Map<String, Trabajador> mapaTrabajadoresPorDNI;

	
	
	
	public Datos(List<Trabajador> listaTrabajadores, List<Cliente> listaClientes, Map<Integer, List<Habitacion>> MapaHabitaciones,
			List<Reserva> listaReservas, List<Tarea> listaTareas,Map<String, Cliente> mapaClientesPorDNI,
			Map<String, Trabajador> mapaTrabajadoresPorDNI,Map<LocalDate, Parking> mapaParkingPorFecha) {
		this.listaTrabajadores = listaTrabajadores;
		this.listaClientes = listaClientes;
		this.MapaHabitaciones = MapaHabitaciones;
		this.listaReservas = listaReservas;
		this.listaTareas = listaTareas;
		this.mapaClientesPorDNI = mapaClientesPorDNI;
		this.mapaTrabajadoresPorDNI = mapaTrabajadoresPorDNI;
		this.mapaParkingPorFecha = mapaParkingPorFecha;
	}
	
	public Datos() {
		this.listaTrabajadores = new ArrayList<Trabajador>();
		this.listaClientes = new ArrayList<Cliente>();
		this.MapaHabitaciones = new HashMap<Integer, List<Habitacion>>();
		this.listaReservas = new ArrayList<Reserva>();
		this.listaTareas = new ArrayList<Tarea>();
		this.mapaClientesPorDNI = new HashMap<String, Cliente> ();
		this.mapaTrabajadoresPorDNI = new HashMap<String, Trabajador> ();
		this.mapaParkingPorFecha = new HashMap<LocalDate, Parking>();


	}
	
	public void inicializarDatos() {
		LocalDate a = LocalDate.of(1999, 9, 11);
		Trabajador t1 = new Trabajador("18087363T", "Mario", "Martinez","mario@gmail.com", "Calle Alfonso 2", a, "123", "673821992", 1200.00, 0, new ArrayList<>(), new ArrayList<>());
		LocalDate b = LocalDate.of(1989, 7, 23);
		Trabajador t2 = new Trabajador("18177653W", "Jorge", "Gonzalez","jorge@gmail.com", "Avenida de la Paz", b, "123", "673927462", 1200.00, 0, new ArrayList<>(), new ArrayList<>());
		LocalDate c = LocalDate.of(1995, 3, 18);
		Trabajador t3 = new Trabajador("18087826Y", "Alex", "Merino","alex@gmail.com", "Calle Deusto", c, "123", "673826592", 1200.00, 0, new ArrayList<>(), new ArrayList<>());
		LocalDate d = LocalDate.of(1993, 2, 6);
		Trabajador t4 = new Trabajador("18072643T", "Iñigo", "Jimenez","iñigo@gmail.com", "Calle Adolfo Dominguez", d, "123", "673826392", 1200.00, 0, new ArrayList<>(), new ArrayList<>());
		LocalDate e = LocalDate.of(1991, 1, 12);
		Trabajador t5 = new Trabajador("18562153W", "Gonzalo", "Mitegui","gonzalo@gmail.com", "Calle Ubayar", e, "123", "623627462", 1200.00, 0, new ArrayList<>(), new ArrayList<>());
		LocalDate f = LocalDate.of(2000, 3, 7);
		Trabajador t6 = new Trabajador("18927456Y", "Daniel", "Larrea","daniel@gmail.com", "Calle Ugasko Bidea", f, "123", "676127592", 1200.00, 0, new ArrayList<>(), new ArrayList<>());
		getListaTrabajadores().add(t1);
		getListaTrabajadores().add(t2);
		getListaTrabajadores().add(t3);
		getListaTrabajadores().add(t4);
		getListaTrabajadores().add(t5);
		getListaTrabajadores().add(t6);

		for (Trabajador trabajador : getListaTrabajadores()) {
			getMapaTrabajadoresPorDNI().putIfAbsent(trabajador.getDni(), trabajador);
		}
		
		for (int i = 0; i < 15; i++) {
			mapaParkingPorFecha.put(LocalDate.now().plusDays(i), new Parking());
		}
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
		return MapaHabitaciones;
	}

	public void setMapaHabitaciones(Map<Integer, List<Habitacion>> mapaHabitaciones) {
		MapaHabitaciones = mapaHabitaciones;
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
	public void guardarDatos() {
		
		try (FileOutputStream fos = new FileOutputStream (FICHERO);
			ObjectOutputStream oos = new ObjectOutputStream(fos)){
				oos.writeObject(listaClientes);
				oos.writeObject(MapaHabitaciones);
				oos.writeObject(listaReservas);
				oos.writeObject(listaTareas);
				oos.writeObject(listaTrabajadores);
				oos.writeObject(mapaClientesPorDNI);
				oos.writeObject(mapaTrabajadoresPorDNI);
				oos.writeObject(mapaParkingPorFecha);

			}catch (FileNotFoundException e) {
			System.err.println("No se encontro el fichero");
			}catch (IOException e) {
			System.err.println("Error al guardar los datos");
		}
	}
	
	
	public void cargarDatos() {
		try(FileInputStream fis = new FileInputStream(FICHERO);
			ObjectInputStream ois = new ObjectInputStream(fis)){
			
			this.listaClientes = (List<Cliente>) ois.readObject();
			this.MapaHabitaciones = (Map<Integer, List<Habitacion>>) ois.readObject();
			this.listaReservas = (List<Reserva>) ois.readObject();
			this.listaTareas = (List<Tarea>) ois.readObject();
			this.listaTrabajadores = (List<Trabajador>) ois.readObject();
			this.mapaClientesPorDNI = (Map<String, Cliente>) ois.readObject();
			this.mapaTrabajadoresPorDNI = (Map<String, Trabajador>) ois.readObject();
			this.mapaParkingPorFecha= (Map<LocalDate, Parking>) ois.readObject();

			
		} catch (FileNotFoundException e) {
			System.err.println("No se encontro el fichero");
		} catch (IOException e) {
			System.err.println("Error al guardal los datos");
		} catch (ClassNotFoundException e) {
			System.err.println("Clase no encontrada");
		}
	}
	
	
}
