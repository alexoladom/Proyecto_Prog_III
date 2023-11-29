package domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.time.LocalDate;

import javax.swing.ImageIcon;

import org.junit.Before;
import org.junit.Test;

public class PersonaTest {

	private Persona persona ;
	@Before
	public void setUp() {
		persona = new Cliente();
	}
	
	@Test
	public void testGetFotoPerfil() {
		ImageIcon a = new ImageIcon("src/Imagenes/imagenPerfilpng.png");
		persona.setFotoPerfil(a);
		assertEquals(a, persona.getFotoPerfil());
	}
	
	@Test
	public void testGetNombre() {
		persona.setNombre("Alex");
		assertEquals("Alex", persona.getNombre());
	}
	
	@Test
	public void testGetApellido1() {
		persona.setApellido1("Olazabal");
		assertEquals("Olazabal", persona.getApellido1());
	}
	
	@Test
	public void testGetEmail() {
		persona.setEmail("email@a.com");
		assertEquals("email@a.com", persona.getEmail());
	}
	
	@Test
	public void testGetDireccion() {
		persona.setDireccion("Ugasko 5");
		assertEquals("Ugasko 5", persona.getDireccion());
	}
	@Test
	public void testGetfNacimiento() {
		persona.setfNacimiento(LocalDate.now());
		assertEquals(persona.getfNacimiento(), LocalDate.now());
	}
	@Test
	public void testgGetTelefono() {
		persona.setTelefono("123456789");
		assertEquals("123456789", persona.getTelefono());
	}
	
	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void testEquals() {
		Persona a= null;
		assertFalse(persona.equals(a));
		int b = 0;
		assertFalse(persona.equals(b));
		
	}
	
	
	

}
