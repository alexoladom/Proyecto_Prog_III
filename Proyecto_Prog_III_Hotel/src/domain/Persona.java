package domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

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
	protected String fotoPerfil;
	
	
	public Persona(String dni, String nombre, String apellido1, String email, String direccion,
			LocalDate fNacimiento, String contraseña, String telefono, String fotoPerfil) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.email = email;
		this.direccion = direccion;
		this.fNacimiento = fNacimiento;
		this.contraseña = contraseña;
		this.telefono = telefono;
		this.fotoPerfil = fotoPerfil;
	}
	
	public Persona() {
		super();
		this.dni = "";
		this.nombre = "";
		this.apellido1 = "";
		this.email = "";
		this.direccion = "";
		this.fNacimiento = LocalDate.now();
		this.contraseña = "";
		this.telefono = "";
		this.fotoPerfil ="src/Imagenes/imagenPerfilpng.png";
	}
	

	public String getFotoPerfil() {
		return fotoPerfil;
	}

	public void setFotoPerfil(String fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
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
	public int hashCode() {
		return Objects.hash(apellido1, dni, fNacimiento, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		return Objects.equals(apellido1, other.apellido1) && Objects.equals(dni, other.dni)
		&& Objects.equals(fNacimiento, other.fNacimiento) && Objects.equals(nombre, other.nombre);
	}

	
	
	
	
	
	
}
