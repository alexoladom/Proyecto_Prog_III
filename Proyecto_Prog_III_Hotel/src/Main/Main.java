package Main;



import javax.swing.SwingUtilities;


import Clases.Datos;

import Ventanas.VentanaDeCarga;


public class Main {
	public static void main(String[] args) {
		System.out.println("hola mundo!");
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
