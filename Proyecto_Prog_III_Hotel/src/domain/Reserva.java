package domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Reserva implements Serializable{

	
	
	//Atributos
	private static final long serialVersionUID = 1L;
	protected static int numId;
	protected int id;
	protected Cliente cliente;
	protected LocalDate fechaInicio;
	protected LocalDate fechaFinal;
	protected List<Habitacion> listaHabitacionesReservadas;
	protected List<PlazaParking> listaPlazasParking;
	protected boolean estaPagado;
	
	//Constructores
	 
	public Reserva( Cliente cliente, LocalDate fechaInicio, LocalDate fechaFinal,
			boolean estaPagado, List<Habitacion> listaHabitacions, List<PlazaParking> listaPlazasParking) {
		super();
		numId++;
		this.id = numId;
		this.cliente = cliente;
		this.fechaInicio = fechaInicio;
		this.fechaFinal = fechaFinal;
		this.estaPagado = estaPagado;
		this.listaHabitacionesReservadas = listaHabitacions;
		this.listaPlazasParking = listaPlazasParking;
	}
	
	
	public Reserva() {
		super();
		numId++;
		this.id = numId;
		this.cliente = null;
		this.fechaInicio = LocalDate.now();
		this.fechaFinal = LocalDate.now().plusDays(1);
		this.estaPagado = false;
		this.listaHabitacionesReservadas = new ArrayList<Habitacion>();
		this.listaPlazasParking = new ArrayList<PlazaParking>();
	}

	
	//Getters y setters

	public static int getNumId() {
		return numId;
	}
	public static void setNumId(int a) {
		 numId=a;
	}

	public void setId(int id) {
		this.id=id;
	}

	

	public int getId() {
		return id;
	}

	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public LocalDate getFechaInicio() {
		return fechaInicio;
	}


	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}


	public LocalDate getFechaFinal() {
		return fechaFinal;
	}


	public void setFechaFinal(LocalDate fechaFinal) {
		this.fechaFinal = fechaFinal;
	}


	public double getPrecioCobrar() {
		double a = 0;
		for (Habitacion habitacion : listaHabitacionesReservadas) {
			a = a+ habitacion.getPrecioPorNoche();
		}
		return a;
	}


	public boolean isEstaPagado() {
		return estaPagado;
	}


	public void setEstaPagado(boolean estaPagado) {
		this.estaPagado = estaPagado;
	}


	public List<Habitacion> getListaHabitacionesReservadas() {
		return listaHabitacionesReservadas;
	}


	public void setListaHabitacionesReservadas(List<Habitacion> listaHabitacionesReservadas) {
		this.listaHabitacionesReservadas = listaHabitacionesReservadas;
	}
	
	public List<PlazaParking> getListaPlazasParking() {
		return listaPlazasParking;
	}


	public void setListaPlazasParking(List<PlazaParking> listaPlazasParking) {
		this.listaPlazasParking = listaPlazasParking;
	}
	
	
	
	//Metodo toString

	@Override
	public int hashCode() {
		return Objects.hash(fechaFinal, fechaInicio, id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reserva other = (Reserva) obj;
		return id == other.id;
	}


	@Override
	public String toString() {
		return String.format(
				"Reserva %s, con fecha de inicio %s, fecha final %s, precio: %s,  estado de pago: %s, plazas de parking: %s, numero de habitaciones: %s ", id,
				 fechaInicio, fechaFinal,this.getPrecioCobrar(), estaPagado, listaPlazasParking.size(), listaHabitacionesReservadas.size());
	}
	
	
	
}
