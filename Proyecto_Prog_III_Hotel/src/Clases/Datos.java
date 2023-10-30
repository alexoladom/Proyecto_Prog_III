package Clases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Datos {
	
	private static final String FICHERO = "datosHotel.dat";
	protected List<Trabajador> listaTrabajadores;
	protected List<Cliente> listaClientes;
	protected List<Habitacion> listaHabitaciones;
	protected List<Reserva> listaReservas;
	protected List<Tarea> listaTareas;
	protected Map<String, Cliente> mapaClientesPorDNI;
	protected Parking parking;
	
	
	
	public Datos(List<Trabajador> listaTrabajadores, List<Cliente> listaClientes, List<Habitacion> listaHabitaciones,
			List<Reserva> listaReservas, List<Tarea> listaTareas,Map<String, Cliente> mapaClientesPorDNI) {
		this.listaTrabajadores = listaTrabajadores;
		this.listaClientes = listaClientes;
		this.listaHabitaciones = listaHabitaciones;
		this.listaReservas = listaReservas;
		this.listaTareas = listaTareas;
		this.mapaClientesPorDNI = mapaClientesPorDNI;
	}
	
	public Datos() {
		this.listaTrabajadores = new ArrayList<Trabajador>();
		this.listaClientes = new ArrayList<Cliente>();
		this.listaHabitaciones = new ArrayList<Habitacion>();
		this.listaReservas = new ArrayList<Reserva>();
		this.listaTareas = new ArrayList<Tarea>();
		this.mapaClientesPorDNI = new HashMap<String, Cliente> ();
		this.parking= new Parking();

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

	public List<Habitacion> getListaHabitaciones() {
		return listaHabitaciones;
	}

	public void setListaHabitaciones(List<Habitacion> listaHabitaciones) {
		this.listaHabitaciones = listaHabitaciones;
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
	
	public Parking getParking() {
		return parking;
	}

	public void setParking(Parking parking) {
		this.parking = parking;
	}

	public Map<String, Cliente> getMapaClientesPorDNI() {
		return mapaClientesPorDNI;
	}

	public void setMapaClientesPorDNI(Map<String, Cliente> mapaClientesPorDNI) {
		this.mapaClientesPorDNI = mapaClientesPorDNI;
	}

	
	public boolean comprobarContraseña(String dni, String contraseña) {
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
	public void guardarDatos() {
		
		try (FileOutputStream fos = new FileOutputStream (FICHERO);
			ObjectOutputStream oos = new ObjectOutputStream(fos)){
				oos.writeObject(listaClientes);
				oos.writeObject(listaHabitaciones);
				oos.writeObject(listaReservas);
				oos.writeObject(listaTareas);
				oos.writeObject(listaTrabajadores);
				oos.writeObject(mapaClientesPorDNI);
				oos.writeObject(parking);
			}catch (FileNotFoundException e) {
			System.err.println("No se encontro el fichero");
			}catch (IOException e) {
			System.err.println("Error al guardar los datos");
		}
	}
	
	
	public void cargarDatos() {
		try(FileInputStream fis = new FileInputStream(FICHERO);
			ObjectInputStream ois = new ObjectInputStream(fis)){
			
			this.listaClientes= (List<Cliente>) ois.readObject();
			this.listaHabitaciones= (List<Habitacion>) ois.readObject();
			this.listaReservas = (List<Reserva>) ois.readObject();
			this.listaTareas = (List<Tarea>) ois.readObject();
			this.listaTrabajadores = (List<Trabajador>) ois.readObject();
			this.mapaClientesPorDNI = (Map<String, Cliente>) ois.readObject();
			this.parking= (Parking) ois.readObject();
			
		} catch (FileNotFoundException e) {
			System.err.println("No se encontro el fichero");
		} catch (IOException e) {
			System.err.println("Error al guardal los datos");
		} catch (ClassNotFoundException e) {
			System.err.println("Clase no encontrada");
		}
	}
	
	
}
