package domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Parking implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Atributos
	//El parking es un array bidimensional de 5x5
	protected LocalDate fecha;
	protected boolean completo;
	protected int numPlazasDisponibles = 25;
	protected PlazaParking[][] distribucion = new PlazaParking[5][5];
	
	
	//Constructores
	public Parking(LocalDate fecha, boolean completo, int numPlazas ) {
		super();
		this.completo = completo;
		this.numPlazasDisponibles = numPlazas;
		this.fecha=fecha;
		this.distribucion = new PlazaParking [5][5];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				distribucion[i][j]= new PlazaParking(i,j,false,null,this);
			}
		}
	} 
	public Parking() {
		super();
		this.completo = false;
		this.numPlazasDisponibles = 25;
		this.distribucion = new PlazaParking [5][5];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				distribucion[i][j]= new PlazaParking(i,j,false,null,this);
			}
		}
		this.fecha= LocalDate.now();
	}
	//Getters y setters
	
	public boolean isCompleto() {
		return completo;
	}
	public void setCompleto(boolean completo) {
		this.completo = completo;
	}
	public int getNumPlazasDisponibles() {
		int cont =0;
		for (int i = 0; i < distribucion.length; i++) {
			for (int j = 0; j < distribucion.length; j++) {
				PlazaParking p = distribucion [i][j];
				if(p.isOcupada()) {
					cont++;
				}
			}
		}
		return 25-cont;
	}
	
	
	
	public void setNumPlazasDisponibles(int numPlazasDisponibles) {
		this.numPlazasDisponibles = numPlazasDisponibles;
	}
	public PlazaParking[][] getDistribucion() {
		return distribucion;
	}
	public void setDistribucion(PlazaParking[][] parking) {
		this.distribucion = parking;
	}
	
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	
	
	public boolean comprobarPlazaDisponible(Cliente cliente, PlazaParking plaza) {
		boolean esta = false;
		for (Reserva reserva : cliente.getListaReservasCliente()) {
			if (reserva.getListaPlazasParking().contains(plaza)||!plaza.isOcupada()) {
				esta = true;
			}
		}
		return esta;
	}
	//Metodo toString
	
	
	
	@Override
	public String toString() {
		return String.format("Parking %s, completo? %s, Plazas disponibles: %s", fecha, completo,
				numPlazasDisponibles);
	} 
	
	@Override
	public int hashCode() {
		return Objects.hash(fecha);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Parking other = (Parking) obj;
		return Objects.equals(fecha, other.fecha);
	}
	
	
}
