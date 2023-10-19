package Clases;

import java.time.LocalDate;

public abstract class Persona {

	//Clase principal persona de donde heredaran cliente y trabajador
	
	//El DNI solo puede tener 9 letras (8 int y un string)
	//La fNacimiento es un LocalDate
	
	protected String dni;
	protected String nombre;
	protected String apellido1;
	protected String apellido2;
	protected String email;
	protected String direccion;
	protected LocalDate fNacimiento;
	protected String contraseña;
	protected String telefono;
	
	
	public Persona(String dni, String nombre, String apellido1, String apellido2, String email, String direccion,
			LocalDate fNacimiento, String contraseña, String telefono) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
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
		this.apellido2 = "";
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
		if (dni.length()!=9) {
			System.err.println("Introduzca un dni valido");
		}else {
			this.dni = dni;
		}
		
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

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
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
		if (telefono.length()!=9) {
			System.err.println("Introduzca un telefono valido");
		}else {
			this.telefono = telefono;
		}
	}

	@Override
	public String toString() {
		return String.format(
				"Persona %s, %s, %s, %s, %s, %s, %s, %s",
				dni, nombre, apellido1, apellido2, email, direccion, fNacimiento, telefono);
	}
	
	
	
	
	
	
	
}
