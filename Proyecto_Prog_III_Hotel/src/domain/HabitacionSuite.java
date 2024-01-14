package domain;

public class HabitacionSuite extends Habitacion{

	private static final long serialVersionUID = 1L;
	protected static final int numCamas = 4;
	protected final static double precioPorNoche = 100;
	
	
	public HabitacionSuite(boolean ocupado, int planta, int numero, Reserva reserva) {
		super(ocupado,planta,numero, reserva);
	}
	
	public HabitacionSuite() {
		super();
	}

	public static int getNumcamas() {
		return numCamas;
	}
	
	public double getPrecioPorNoche() {
		return precioPorNoche;
	}

	@Override
	public String toString() {
		return String.format("HabitacionSuite %s, ocupado=%s, planta=%s, %s]", id, ocupado, planta, numero);
	}
}
