package Clases;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Reserva implements Serializable{

	
	//TODO metodo sacarFactura()
	
	//Atributos
	private static final long serialVersionUID = 1L;
	protected static int numId;
	protected int id;
	protected Cliente cliente;
	protected LocalDate fechaInicio;
	protected LocalDate fechaFinal;
	protected List<Habitacion> listaHabitacionesReservadas;
	protected double precioCobrar;
	protected boolean estaPagado;
	
	//Constructores
	 
	public Reserva( Cliente cliente, LocalDate fechaInicio, LocalDate fechaFinal, double precioCobrar,
			boolean estaPagado, List<Habitacion> listaHabitacions) {
		super();
		numId++;
		this.id = numId;
		this.cliente = cliente;
		this.fechaInicio = fechaInicio;
		this.fechaFinal = fechaFinal;
		this.precioCobrar = precioCobrar;
		this.estaPagado = estaPagado;
		this.listaHabitacionesReservadas = listaHabitacions;
	}
	
	
	public Reserva() {
		super();
		numId++;
		this.id = numId;
		this.cliente = null;
		this.fechaInicio = null;
		this.fechaFinal = null;
		this.precioCobrar = 0;
		this.estaPagado = false;
		this.listaHabitacionesReservadas = new ArrayList<Habitacion>();
	}

	
	//Getters y setters

	public static int getNumId() {
		return numId;
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
		return precioCobrar;
	}


	public void setPrecioCobrar(double precioCobrar) {
		if(precioCobrar>0) {
			this.precioCobrar = precioCobrar;	
		}
		
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
	
	
	//Metodo toString


	@Override
	public String toString() {
		return String.format(
				"Reserva %s, %s, fechaInicio: %s, fechaFinal: %s, %s, pagado? %s", id,
				cliente, fechaInicio, fechaFinal, precioCobrar, estaPagado);
	}
	
	
	
}
