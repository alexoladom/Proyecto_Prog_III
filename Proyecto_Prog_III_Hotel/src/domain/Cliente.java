package domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class Cliente extends Persona{

	
	//Clase cliente que hereda de persona
	
	//Lista de las reservas hechas a lo largo del tiempo
	private static final long serialVersionUID = 1L;
	protected List<Reserva> listaReservasCliente;
	protected LocalDate ultimoLogin;
	
	
	public Cliente(String dni, String nombre, String apellido1, String email, String direccion,
			LocalDate fNacimiento, String contraseña, String telefono, List<Reserva> listaReservasCliente,
			LocalDate ultimoLogin,ImageIcon foto) {
		
		super(dni, nombre, apellido1, email, direccion, fNacimiento, contraseña, telefono, foto);
		
		this.listaReservasCliente = listaReservasCliente;
		this.ultimoLogin = ultimoLogin;
	}


	public Cliente() {
		super();
		this.listaReservasCliente = new ArrayList<Reserva>() ;
		this.ultimoLogin = LocalDate.now();
	}


	public List<Reserva> getListaReservasCliente() {
		if (this.listaReservasCliente==null) {
			System.err.println("No existe lista  de reservas para este cliente");
			return null;
		}else {
			return listaReservasCliente;
		}
		
	}


	public void setListaReservasCliente(List<Reserva> listaReservasCliente) {
		this.listaReservasCliente = listaReservasCliente;
	}


	public LocalDate getUltimoLogin() {
		return ultimoLogin;
	}


	public void setUltimoLogin(LocalDate ultimoLogin) {
		this.ultimoLogin = ultimoLogin;
	}


	@Override
	public String toString() {
		return String.format(
				"Cliente  ultimoLogin=%s, %s, %s, %s, %s, %s, %s, %s, %s",
				 ultimoLogin, dni, nombre, apellido1,  email, direccion, fNacimiento,
				contraseña, telefono);
	}




	
	

	
	
	
	
}
