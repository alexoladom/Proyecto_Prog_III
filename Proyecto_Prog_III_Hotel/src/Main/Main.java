package Main;

import javax.swing.SwingUtilities;
import domain.Datos;
import gui.VentanaDeCarga;


public class Main {
	public static void main(String[] args) {
		Datos datos = new Datos();

		datos.inicializarDatos();
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new VentanaDeCarga(datos);
				
			}
		});
	}
}
