package domain;

import java.util.ArrayList;
import java.util.List;

public class Plato {

	
	//Atributos
	
	protected static int numId;
	protected int id;
	protected String nombre;
	protected String descripcion;
	protected List<String> listaIngredientes;
	protected boolean aptoCeliacos;

	
	//Constructores 
	
	public Plato(String nombre, String descripcion, List<String> listaIngredientes, boolean aptoCeliacos) {
		super();
		numId++;
		this.id=numId;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.listaIngredientes = listaIngredientes;
		this.aptoCeliacos = aptoCeliacos;
	}
	
	public Plato() {
		super();
		numId++;
		this.id=numId;
		this.nombre = "";
		this.descripcion = "";
		this.listaIngredientes = new ArrayList<String>();
		this.aptoCeliacos = false;
	}
	
	
	//Getters y setters

	public static int getNumId() {
		return numId;
	}
	
	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<String> getListaIngredientes() {
		return listaIngredientes;
	}

	public void setListaIngredientes(List<String> listaIngredientes) {
		this.listaIngredientes = listaIngredientes;
	}

	public boolean isAptoCeliacos() {
		return aptoCeliacos;
	}

	public void setAptoCeliacos(boolean aptoCeliacos) {
		this.aptoCeliacos = aptoCeliacos;
	}

	
	//Metodo toString
	
	@Override
	public String toString() {
		return String.format("Plato %s, %s, %s, %s, aptoCeliacos=%s", id,
				nombre, descripcion, listaIngredientes, aptoCeliacos);
	}
	
	
	
	
	
	
}
