package domain;

import java.util.Comparator;

public class ComparadorReservasPorPrecio implements Comparator<Reserva>{

	@Override
	public int compare(Reserva r1, Reserva r2) {
		return (int) (r1.getPrecioCobrar()-r2.getPrecioCobrar());
	}

}
