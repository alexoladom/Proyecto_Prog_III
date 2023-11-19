package domain;

import java.util.ArrayList;
import java.util.List;

public class Cocina {

	//Atributos
	//Los sitios disponibles empiezan desde 40 y van bajando
	
	
	protected static int numId;
	protected int id;
	protected boolean completo;
	protected int numSitiosDisponibles = 40;
	protected List<Plato> listaPlatosAPreparar;
	protected List<Plato> listaPlatosPreparados;
	
	
	
	//Constructores
	
	public Cocina(int numSitiosDisponibles, List<Plato> listaPlatosAPreparar,
			List<Plato> listaPlatosPreparados,boolean completo) {
		super();
		numId++;
		this.id =numId;
		this.numSitiosDisponibles = numSitiosDisponibles;
		this.listaPlatosAPreparar = listaPlatosAPreparar;
		this.listaPlatosPreparados = listaPlatosPreparados;
		this.completo=completo;
		
	}
	
	public Cocina() {
		super();
		numId++;
		this.id =numId;
		this.listaPlatosAPreparar = new ArrayList<Plato>();
		this.listaPlatosPreparados = new ArrayList<Plato>();
		this.completo=false;
	}

	
	//Getters y setters
	
	public static int getNumId() {
		return numId;
	}

	public int getId() {
		return id;
	}

	public int getNumSitiosDisponibles() {
		return numSitiosDisponibles;
	}

	public void setNumSitiosDisponibles(int numSitiosDisponibles) {
		if(numSitiosDisponibles<0) {
			System.err.println("No hay sitio en el comedor");
		}else {
			this.numSitiosDisponibles = numSitiosDisponibles;
		}
	}

	public List<Plato> getListaPlatosAPreparar() {
		return listaPlatosAPreparar;
	}

	public void setListaPlatosAPreparar(List<Plato> listaPlatosAPreparar) {
		this.listaPlatosAPreparar = listaPlatosAPreparar;
	}

	public List<Plato> getListaPlatosPreparados() {
		return listaPlatosPreparados;
	}

	public void setListaPlatosPreparados(List<Plato> listaPlatosPreparados) {
		this.listaPlatosPreparados = listaPlatosPreparados;
	}
	public boolean isCompleto() {
		return completo;
	}

	public void setCompleto(boolean completo) {
		this.completo = completo;
	}
	//Metodo toString
	
	

	@Override
	public String toString() {
		return String.format(
				"Cocina %s, Sitios disponibles: %s", id,
				numSitiosDisponibles);
	}
	
	
	
}
