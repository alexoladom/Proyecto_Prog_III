package domain;

import java.io.Serializable;
import java.util.Objects;

public class Mesa implements Serializable{
	private static final long serialVersionUID = 1L;
	protected static int numId;
	protected int id;
	protected int numero;
	protected boolean ocupado;
	protected Reserva reserva;
	
	
	public Reserva getReserva() {
		return reserva;
	}
	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Mesa(int numero, boolean ocupado) {
		super();
		numId++;
		this.id = numId;
		this.numero = numero;
		this.ocupado = ocupado;
		Reserva r = new Reserva();
		r.setId(-1);
		this.reserva=r;
	}
	public Mesa() {
		super();
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
	public boolean isOcupado() {
		return ocupado;
	}
	public void setOcupado(boolean ocupado) {
		this.ocupado = ocupado;
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
	public int hashCode() {
		return Objects.hash(id, numero);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mesa other = (Mesa) obj;
		return id == other.id && numero == other.numero;
	}
	@Override
	public String toString() {
		return "Mesa [id=" + id + ", numero=" + numero + ", ocupado=" + ocupado + "]";
	}
}
