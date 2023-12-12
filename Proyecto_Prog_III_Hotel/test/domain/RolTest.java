package domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RolTest {

	
	@Test
	public void testRol() {
		assertEquals(Rol.values()[0], Rol.Cocinero);
		assertEquals(Rol.values()[1], Rol.Camarero);
		assertEquals(Rol.values()[2], Rol.Limpiador);
		assertEquals(Rol.values()[3], Rol.Recepcionista);
	}
}
