package Clases;

import java.util.ArrayList;
import java.util.List;

public class Datos {
	
	private static final String FICHERO = "datosHotel.dat";
	protected List<Trabajador> listaTrabajadores;
	protected List<Cliente> listaClientes;
	protected List<Habitacion> listaHabitaciones;
	protected List<Reserva> listaReservas;
	protected List<Tarea> listaTareas;
	protected Cocina cocina;
	protected Parking parking;
	protected SalaEventos salaEventos;
	
	
	public Datos(List<Trabajador> listaTrabajadores, List<Cliente> listaClientes, List<Habitacion> listaHabitaciones,
			List<Reserva> listaReservas, List<Tarea> listaTareas) {
		this.listaTrabajadores = listaTrabajadores;
		this.listaClientes = listaClientes;
		this.listaHabitaciones = listaHabitaciones;
		this.listaReservas = listaReservas;
		this.listaTareas = listaTareas;
	}
	
	public Datos() {
		this.listaTrabajadores = new ArrayList<Trabajador>();
		this.listaClientes = new ArrayList<Cliente>();
		this.listaHabitaciones = new ArrayList<Habitacion>();
		this.listaReservas = new ArrayList<Reserva>();
		this.listaTareas = new ArrayList<Tarea>();

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
	
	
	
	
	
	
	
	
	
	
}
