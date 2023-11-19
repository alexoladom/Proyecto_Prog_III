package domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class ClienteTest {

	private Cliente cliente;
	@Before
	public void setUp() {
		cliente= new Cliente();
	}
	
	@Test
	public void testClienteConParam() {
		cliente = new  Cliente("a", "a", "2", "2", "2",
				LocalDate.now(), "2", "2", new ArrayList<Reserva> (),
				LocalDate.now());
		assertNotNull(cliente);
	}
	
	@Test 
	public void testGetListaReservasClienteNull() {
		cliente.setListaReservasCliente(null);
		assertNull(cliente.getListaReservasCliente());
	}
	
	@Test
	public void testGetUltimoLogin() {
		cliente.setUltimoLogin(LocalDate.now());
		assertEquals(LocalDate.now(), cliente.getUltimoLogin());
	}
	
	@Test
	public void testToString() {
		String s =String.format(
				"Cliente  ultimoLogin=%s, %s, %s, %s, %s, %s, %s, %s, %s",
				 cliente.ultimoLogin, cliente.dni, cliente.nombre, cliente.apellido1,  cliente.email, cliente.direccion, cliente.fNacimiento,
				 cliente.contraseña, cliente.telefono);
		
		assertEquals(s, cliente.toString());
	}
	
	
}
