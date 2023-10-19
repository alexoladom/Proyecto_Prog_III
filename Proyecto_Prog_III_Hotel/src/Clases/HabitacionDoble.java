package Clases;

public class HabitacionDoble extends Habitacion{

	protected final static int numCamas = 2;
	protected final static double precioPorNoche = 60;
	
	public HabitacionDoble(boolean ocupado, int planta, int numero) {
		super(ocupado,  planta, numero);
	}
	
	public HabitacionDoble() {
		super();
	}

	public static int getNumcamas() {
		return numCamas;
	}

	public static double getPreciopornoche() {
		return precioPorNoche;
	}

	@Override
	public String toString() {
		return String.format("HabitacionDoble %s, ocupado=%s, planta=%s, %s]", id, ocupado, planta, numero);
	}
	
	
	
}
