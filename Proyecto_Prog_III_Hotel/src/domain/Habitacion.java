package domain;

import java.io.Serializable;
import java.util.Objects;

public abstract class Habitacion implements Serializable{
	
	//Clase principal para cualquier habitacion
	private static final long serialVersionUID = 1L;
	protected static int numId;
	protected int id;
	protected boolean ocupado;
	protected int planta;
	protected int numero;
	protected Reserva reserva;
	
	
	public Habitacion(boolean ocupado, int planta, int numero, Reserva reserva) {
		super();
		numId++;
		this.id = numId;
		this.ocupado = ocupado;
		this.planta = planta;
		this.numero = numero;
		this.reserva=reserva;
	}
	
	public Habitacion() {
		super();
		numId++;
		this.id = numId;
		this.ocupado = false;
		this.planta = 0;
		this.numero = 0;
		this.reserva=null;
	}

	public static int getNumId() {
		return numId;
	}
	public static void setNumId(int a) {
		numId=a;
	}

	public int getId() {
		return id;
	}

	
	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public void setId(int id) {
		this.id=id;
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
<<<<<<< HEAD
	//Metodo para comprobar si esta ocupada una habitación
	public boolean comprobarHabitacionDisponible(Cliente cliente, Habitacion plaza) {
		boolean esta = false;
		for (Reserva reserva : cliente.getListaReservasCliente()) {
			if (reserva.getListaHabitacionesReservadas().contains(plaza)||!plaza.isOcupado()) {
				esta = true;
			}
		}
		return esta;
	}
	/////////////////////////////////////////////////////////////
=======

>>>>>>> branch 'master' of https://github.com/alexoladom/Proyecto_Prog_III_E1-08.git
	public abstract double getPrecioPorNoche();
	
	@Override
	public int hashCode() {
		return Objects.hash(id, numero, planta);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Habitacion other = (Habitacion) obj;
		return id == other.id && numero == other.numero && planta == other.planta;
	}

	@Override
	public String toString() {
		return String.format("Habitacion %s, ocupado=%s, planta=%s, %s", id, ocupado, planta, numero);
	}
	
	
	
}
