package Clases;

import java.util.List;

public class Hotel {
	//Clase principal de cada hotel
	
	//El codigo es unico en cada hotel
	//Las estrellas pueden ser de entre 1 y 5
	
	
	protected String nombre;
	protected static int numCodigo;
	protected int codigo;
	protected String direccion;
	protected int numEstrellas;
	
	//Cada hotel tiene varias listas con diferentes objetos
	
	protected List<Trabajador> listaTrabajadores;
	protected List<Cliente> listaClientes;
	protected List<Habitacion> listaHabitaciones;
	protected List<Reserva> listaReservas;
	protected Cocina cocina;
	//protected Parking parking
	//protected SalaEventos salaEventos;
	
	
	
	
}
