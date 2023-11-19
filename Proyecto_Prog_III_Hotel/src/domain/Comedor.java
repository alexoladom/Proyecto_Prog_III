package domain;

import java.util.List;

public class Comedor {
	protected static int numId;
	protected int id;
	protected boolean completo;
	protected List<Mesa> listaMesas;
	
	public Comedor(boolean completo, List<Mesa> listaMesas) {
		super();
		numId++;
		this.id =numId;
		this.completo = completo;
		this.listaMesas = listaMesas;
	}
	public Comedor() {
		super();
	}
	public static int getNumId() {
		return numId;
	}

	public int getId() {
		return id;
	}
	public List<Mesa> getListaMesas() {
		return listaMesas;
	}
	public void setListaMesas(List<Mesa> listaMesas) {
		this.listaMesas = listaMesas;
	}
	@Override
	public String toString() {
		return "Comedor [id=" + id + ", completo=" + completo + ", listaMesas=" + listaMesas + "]";
	}
}
