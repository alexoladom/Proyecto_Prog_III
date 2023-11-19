package domain;

import java.io.Serializable;
import java.util.Objects;

public class Tarea implements Serializable{

	//Atributos 
	
	private static final long serialVersionUID = 1L;
	protected static int numId;
	protected int id;
	protected Rol rol;
	protected int numHoras;
	protected boolean estaCompletada;
	protected String descripcion;

	
	//Constructores
	
	
	public Tarea(Rol rol, int numHoras, boolean estaCompletada, String descripcion) {
		super();
		numId++;
		this.id = numId;
		this.rol = rol;
		this.numHoras = numHoras;
		this.estaCompletada = estaCompletada;
		this.descripcion = descripcion;
	}
	
	public Tarea() {
		super();
		numId++;
		this.id = numId;
		this.rol = null;
		this.numHoras = 0;
		this.estaCompletada = false;
		this.descripcion = "";
	}

	
	//Getters y setters
	
	
	public static int getNumId() {
		return numId;
	}


	public int getId() {
		return id;
	}


	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public int getNumHoras() {
		return numHoras;
	}

	public void setNumHoras(int numHoras) {
		if(numHoras>0) {
			this.numHoras = numHoras;
		}
	}

	public boolean isEstaCompletada() {
		return estaCompletada;
	}

	public void setEstaCompletada(boolean estaCompletada) {
		this.estaCompletada = estaCompletada;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	
	
	//Metdo tostring
	
	@Override
	public int hashCode() {
		return Objects.hash(descripcion, id, rol);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tarea other = (Tarea) obj;
		return Objects.equals(descripcion, other.descripcion) && id == other.id && rol == other.rol;
	}

	@Override
	public String toString() {
		return String.format("Tarea id=%s, %s, %s horas, %s, descripcion: %s", id, rol, numHoras,
				estaCompletada, descripcion);
	}
	
	
	
	
}
