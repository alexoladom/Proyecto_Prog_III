package domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

public class Parking implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Atributos
	//El parking es un array bidimensional de 10x10
	protected static int numId;
	protected int id;
	protected boolean completo;
	protected int numPlazasDisponibles = 100;
	protected LocalDate fecha;
	protected PlazaParking[][] distribucion = new PlazaParking[5][5];
	
	//Constructores
	public Parking( boolean completo, int numPlazasDisponibles, PlazaParking[][] parking, LocalDate fecha) {
		super();
		numId++;
		this.id = numId;
		this.completo = completo;
		this.numPlazasDisponibles = numPlazasDisponibles;
		this.distribucion = parking;
		this.fecha=fecha;
	} 
	public Parking() {
		super();
		numId++;
		this.id = numId;
		this.completo = false;
		this.numPlazasDisponibles = 100;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				distribucion[i][j]= new PlazaParking(i,j,false);
			}
		}
		this.fecha= LocalDate.now();
	}
	//Getters y setters
	
	public static int getNumId() {
		return numId;
	}
	public static void setNumId(int a) {
		 numId = a;
	}
	public int getId() {
		return id;
	}
	
	public boolean isCompleto() {
		return completo;
	}
	public void setCompleto(boolean completo) {
		this.completo = completo;
	}
	public int getNumPlazasDisponibles() {
		return numPlazasDisponibles;
	}
	public void setNumPlazasDisponibles(int numPlazasDisponibles) {
		if (numPlazasDisponibles<0) {
			System.err.println("El numero de plazas debe de ser positivo");
		}else {
			this.numPlazasDisponibles = numPlazasDisponibles;
		}
		
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
		return String.format("Parking %s, completo? %s, Plazad disponibles: %s", id, completo,
				numPlazasDisponibles);
	} 
	
	@Override
	public int hashCode() {
		return Objects.hash(fecha, id);
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
		return Objects.equals(fecha, other.fecha) && id == other.id;
	}
	//Metodo para visualizar las plazas disponibles del parking 
	//True si esta ocupado, false si esta libre
	public String parkinigToString() {
		String a = "";
		for (int i = 0; i < 5; i++) {
			a = a+ Arrays.toString(distribucion[i])+ "\n";	
			}
		return a;
	}
	
}
