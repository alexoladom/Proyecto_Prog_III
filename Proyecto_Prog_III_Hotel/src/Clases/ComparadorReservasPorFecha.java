package Clases;

import java.util.Comparator;

public class ComparadorReservasPorFecha implements Comparator<Reserva>{

	@Override
	public int compare(Reserva r1, Reserva r2) {
		return r1.getFechaInicio().compareTo(r2.getFechaInicio());
	}

}
