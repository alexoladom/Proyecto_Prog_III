package domain;

import java.io.Serializable;
import java.util.Objects;

public class PlazaParking implements Serializable{

	

	private static final long serialVersionUID = 1L;
	protected static int contador;
	protected int id;
	protected int row,column;
	protected boolean ocupada;
	protected Reserva reserva;
	protected Parking parking;
	
	public PlazaParking( int row, int column, boolean ocupada, Reserva reserva, Parking parking) {
		super();
		contador++;
		this.id = contador;
		this.row = row;
		this.column = column;
		this.ocupada = ocupada;
		this.reserva = reserva;
		this.parking = parking;
	}
	
	public PlazaParking() {
		super();
		contador++;
		this.id = contador;
		this.row = 0;
		this.column = 0;
		this.ocupada = false;
		this.reserva = null;
		this.parking = null;
	}

	public int getId() {
		return id;
	}

	public int getRow() {
		return row;
	}

	public static int getContador() {
		return contador;
	}

	public static void setContador(int contador) {
		PlazaParking.contador = contador;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public boolean isOcupada() {
		return ocupada;
	}

	public void setOcupada(boolean ocupada) {
		this.ocupada = ocupada;
	}

	public Parking getParking() {
		return parking;
	}

	public void setParking(Parking parking) {
		this.parking = parking;
	}

	@Override
	public int hashCode() {
		return Objects.hash(column, id, ocupada, row);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlazaParking other = (PlazaParking) obj;
		return  id == other.id && this.parking.equals(other.getParking());
	}

	@Override
	public String toString() {
		return "PlazaParkin "+ id + " row=" + row + ", column=" + column + ", ocupada=" + ocupada ;
	}
	
	
	
	
}
