package domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TrabajadorTest {

	private Trabajador trabajador;
	@Before
	public void setUp() {
		trabajador = new Trabajador();
	}
	
	@Test
	public void testGetSalario() {
		trabajador.setSalario(2);
		assertEquals(2, trabajador.getSalario(), 0);
	}
	@Test
	public void testSetsalario() {
		trabajador.setSalario(12);
		trabajador.setSalario(-1);
		assertEquals(12, trabajador.getSalario(),0);
	}
	@Test
	public void testGetNumHorasTrabajadas() {
		trabajador.setNumHorasTrabajadas(10);
		assertEquals(10, trabajador.getNumHorasTrabajadas());
	}
	
	@Test
	public void testSetNumHorasTrabajadasMenorCero() {
		trabajador.setNumHorasTrabajadas(10);
		trabajador.setNumHorasTrabajadas(-1);
		assertEquals(10, trabajador.getNumHorasTrabajadas());
		
	}
	@Test
	public void getListaTareasHechas() {
		List<Tarea> a = new ArrayList<>();
		a.add(new Tarea());
		trabajador.setListaTareasHechas(a);
		assertEquals(a, trabajador.getListaTareasHechas());
	}
	@Test
	public void getListaTareasHechasNull() {
		trabajador.setListaTareasHechas(null);
		assertNull(trabajador.getListaTareasHechas());
	}
	
	
	@Test 
	public void testToString() {
		String a = "Trabajador [salario=" + trabajador.salario + ", numHorasTrabajadas=" + trabajador.numHorasTrabajadas + ", dni=" + trabajador.dni
		+ ", nombre=" + trabajador.nombre + ", apellido1=" + trabajador.apellido1 + ", email=" + trabajador.email + ", telefono=" + trabajador.telefono
		+ "]";
		
		assertEquals(a, trabajador.toString());
	}
}
