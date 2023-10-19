package Clases;

public abstract class Habitacion {

	//TODO Implementa reservable
	
	//Clase principal para cualquier habitacion
	
	protected static int numId;
	protected int id;
	protected boolean ocupado;
	protected int planta;
	protected int numero;
	
	
	public Habitacion(boolean ocupado, int planta, int numero) {
		super();
		numId++;
		this.id = numId;
		this.ocupado = ocupado;
		this.planta = planta;
		this.numero = numero;
	}
	
	public Habitacion() {
		super();
		numId++;
		this.id = numId;
		this.ocupado = false;
		this.planta = 0;
		this.numero = 0;
	}

	public static int getNumId() {
		return numId;
	}

	public int getId() {
		return id;
	}

	public boolean isOcupado() {
		return ocupado;
	}

	public void setOcupado(boolean ocupado) {
		this.ocupado = ocupado;
	}

	public int getPlanta() {
		return planta;
	}

	public void setPlanta(int planta) {
		if(planta>0) {
			this.planta = planta;
		}
		
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		if(numero>0) {
			this.numero = numero;	
		}
		
	}

	@Override
	public String toString() {
		return String.format("Habitacion %s, ocupado=%s, planta=%s, %s", id, ocupado, planta, numero);
	}
	
	
	
}
