package Clases;

import java.io.Serializable;
import java.util.Arrays;

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
	protected boolean[][] parking = new boolean[10][10];
	
	//Constructores
	public Parking( boolean completo, int numPlazasDisponibles, boolean[][] parking) {
		super();
		numId++;
		this.id = numId;
		this.completo = completo;
		this.numPlazasDisponibles = numPlazasDisponibles;
		this.parking = parking;
	} 
	public Parking() {
		super();
		numId++;
		this.id = numId;
		this.completo = false;
		this.numPlazasDisponibles = 100;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				parking[i][j]= false;
			}
		}
	}
	//Getters y setters
	
	public static int getNumId() {
		return numId;
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
	public boolean[][] getParking() {
		return parking;
	}
	public void setParking(boolean[][] parking) {
		this.parking = parking;
	}
	
	//Metodo toString
	
	@Override
	public String toString() {
		return String.format("Parking %s, completo? %s, Plazad disponibles: %s", id, completo,
				numPlazasDisponibles);
	} 
	
	//Metodo para visualizar las plazas disponibles del parking 
	//True si esta ocupado, false si esta libre
	public String parkinigToString() {
		String a = "";
		for (int i = 0; i < 10; i++) {
			a = a+ Arrays.toString(parking[i])+ "\n";	
			}
		return a;
	}
	
}
