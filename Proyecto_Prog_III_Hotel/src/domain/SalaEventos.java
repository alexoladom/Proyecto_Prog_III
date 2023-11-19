package domain;

public class SalaEventos {

	
	
	protected boolean ocupado;
	
	public SalaEventos() {	
		this.ocupado= false;
	}

	public boolean isOcupado() {
		return ocupado;
	}

	public void setOcupado(boolean ocupado) {
		this.ocupado = ocupado;
	};
	
	
	
	public String toString() {
		return String.format("La sala de eventos esta ocupada: %s", ocupado);
	}
	
	
	
}
