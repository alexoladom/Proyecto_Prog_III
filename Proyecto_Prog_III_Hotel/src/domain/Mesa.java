package domain;

import java.util.Objects;

public class Mesa {
	private static final long serialVersionUID = 1L;
	protected static int numId;
	protected int id;
	protected int numero;
	protected boolean ocupado;
	
	public Mesa(int numero, boolean ocupado) {
		super();
		numId++;
		this.id = numId;
		this.numero = numero;
		this.ocupado = ocupado;
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
