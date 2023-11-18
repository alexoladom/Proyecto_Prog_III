package Main;



import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import Clases.Cliente;
import Clases.Datos;
import Clases.Reserva;
import Clases.Trabajador;
import Ventanas.VentanaDeCarga;


public class Main {
	public static void main(String[] args) {
		Datos datos = new Datos();
		LocalDate a = LocalDate.of(1999, 9, 11);
		Trabajador t1 = new Trabajador("18087363T", "Mario", "Martinez","mario@gmail.com", "Calle Alfonso 2", a, "123", "673821992", 1200.00, 0, new ArrayList<>(), new ArrayList<>());
		LocalDate b = LocalDate.of(1989, 7, 23);
		Trabajador t2 = new Trabajador("18177653W", "Jorge", "Gonzalez","jorge@gmail.com", "Avenida de la Paz", b, "123", "673927462", 1200.00, 0, new ArrayList<>(), new ArrayList<>());
		LocalDate c = LocalDate.of(1995, 3, 18);
		Trabajador t3 = new Trabajador("18087826Y", "Alex", "Merino","alex@gmail.com", "Calle Deusto", c, "123", "673826592", 1200.00, 0, new ArrayList<>(), new ArrayList<>());
		LocalDate d = LocalDate.of(1993, 2, 6);
		Trabajador t4 = new Trabajador("18072643T", "Iñigo", "Jimenez","iñigo@gmail.com", "Calle Adolfo Dominguez", d, "123", "673826392", 1200.00, 0, new ArrayList<>(), new ArrayList<>());
		LocalDate e = LocalDate.of(1991, 1, 12);
		Trabajador t5 = new Trabajador("18562153W", "Gonzalo", "Mitegui","gonzalo@gmail.com", "Calle Ubayar", e, "123", "623627462", 1200.00, 0, new ArrayList<>(), new ArrayList<>());
		LocalDate f = LocalDate.of(2000, 3, 7);
		Trabajador t6 = new Trabajador("18927456Y", "Daniel", "Larrea","daniel@gmail.com", "Calle Ugasko Bidea", f, "123", "676127592", 1200.00, 0, new ArrayList<>(), new ArrayList<>());
		datos.getListaTrabajadores().add(t1);
		datos.getListaTrabajadores().add(t2);
		datos.getListaTrabajadores().add(t3);
		datos.getListaTrabajadores().add(t4);
		datos.getListaTrabajadores().add(t5);
		datos.getListaTrabajadores().add(t6);
		
		
		for (Trabajador trabajador : datos.getListaTrabajadores()) {
			datos.getMapaTrabajadoresPorDNI().putIfAbsent(trabajador.getDni(), trabajador);
		}
		datos.inicializarDatos();
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new VentanaDeCarga(datos);
				
			}
		});
		
	}
}
