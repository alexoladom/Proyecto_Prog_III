package Main;

import Clases.Cliente;
import Clases.Datos;
import Ventanas.VentanaInicioCliente;
import Ventanas.VentanaParking;
import Ventanas.VentanaSeleccion;

public class Main {
	public static void main(String[] args) {
		System.out.println("hola mundo!");
		Datos datos = new Datos();
		new VentanaSeleccion(datos);
	}
}
