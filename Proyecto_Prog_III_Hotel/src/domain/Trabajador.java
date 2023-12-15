package domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Trabajador extends Persona{

	//Clase trabajador que hereda de persona
	
	//protected Cargo cargo;
	private static final long serialVersionUID = 1L;
	protected double salario;
	protected int numHorasTrabajadas;
	
	//Listas de las tareas hechas y por hacer
	
	
	protected List<Tarea> listaTareasHechas;
	
	
	public Trabajador(String dni, String nombre, String apellido1, String email, String direccion,
			LocalDate fNacimiento, String contraseña, String telefono, double salario, int numHorasTrabajadas,
			List<Tarea> listaTareasHechas, String foto) {
		
		super(dni, nombre, apellido1, email, direccion, fNacimiento, contraseña, telefono,foto);
		
		this.salario = salario;
		this.numHorasTrabajadas = numHorasTrabajadas;
		this.listaTareasHechas = listaTareasHechas;

	}
	
	public Trabajador() {
		
		super();
		
		this.salario = 0;
		this.numHorasTrabajadas = 0;
		this.listaTareasHechas = new ArrayList<Tarea>();

	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		if (salario>0) {
			this.salario = salario;
		}
	}

	public int getNumHorasTrabajadas() {
		return numHorasTrabajadas;
	}

	public void setNumHorasTrabajadas(int numHorasTrabajadas) {
		if(numHorasTrabajadas>0) {
			this.numHorasTrabajadas = numHorasTrabajadas;	
		}
		
	}

	public List<Tarea> getListaTareasHechas() {
		if (this.listaTareasHechas==null) {
			System.err.println("No existe lista de tareas hechas para este trabajador");
			return null;
		}else {
			return listaTareasHechas;
		}
	}

	public void setListaTareasHechas(List<Tarea> listaTareasHechas) {
		this.listaTareasHechas = listaTareasHechas;
	}

	

	@Override
	public String toString() {
		return "Trabajador [salario=" + salario + ", numHorasTrabajadas=" + numHorasTrabajadas + ", dni=" + dni
				+ ", nombre=" + nombre + ", apellido1=" + apellido1 + ", email=" + email + ", telefono=" + telefono
				+ "]";
	}
	
	
	
}
