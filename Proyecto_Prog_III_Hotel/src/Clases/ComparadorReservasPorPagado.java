package Clases;

import java.util.Comparator;

public class ComparadorReservasPorPagado implements Comparator<Reserva>{

	@Override
	public int compare(Reserva o1, Reserva o2) {
		if(o1.isEstaPagado()==true && o2.isEstaPagado()==false) {
			return -1;
		}else if(o1.isEstaPagado()==false && o2.isEstaPagado()==true) {
			return 1;
		}else {
			return 0;
		}
	}

	

}
