package Clases;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class Persona implements Serializable{

	//Clase principal persona de donde heredaran cliente y trabajador
	
	//El DNI solo puede tener 9 letras (8 int y un string)
	//La fNacimiento es un LocalDate
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String dni;
	protected String nombre;
	protected String apellido1;
	protected String email;
	protected String direccion;
	protected LocalDate fNacimiento;
	protected String contraseña;
	protected String telefono;
	
	
	public Persona(String dni, String nombre, String apellido1, String email, String direccion,
			LocalDate fNacimiento, String contraseña, String telefono) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.email = email;
		this.direccion = direccion;
		this.fNacimiento = fNacimiento;
		this.contraseña = contraseña;
		this.telefono = telefono;
	}
	
	public Persona() {
		super();
		this.dni = "";
		this.nombre = "";
		this.apellido1 = "";
		this.email = "";
		this.direccion = "";
		this.fNacimiento = null;
		this.contraseña = "";
		this.telefono = "";
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public LocalDate getfNacimiento() {
		return fNacimiento;
	}

	public void setfNacimiento(LocalDate fNacimiento) {
		this.fNacimiento = fNacimiento;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return String.format(
				"Persona %s, %s, %s, %s, %s, %s, %s",
				dni, nombre, apellido1, email, direccion, fNacimiento, telefono);
	}
	
	
	
	
	
	
	
}
