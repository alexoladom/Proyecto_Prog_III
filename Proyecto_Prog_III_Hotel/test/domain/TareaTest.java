package domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Objects;

import org.junit.Before;
import org.junit.Test;

public class TareaTest {
	private Tarea tarea;
	@Before
	public void setUp() {
		tarea = new Tarea();
		Tarea.setNumId(0);
	}
	
	@Test 
	public void testTareaAtrib() {
		assertNotNull(tarea);
	}
	@Test
	public void testGetId() {
		assertEquals(tarea.id, tarea.getId());
	}

	@Test
    public void testGetRol() {
        assertEquals(Rol.Cocinero, tarea.getRol());
    }

    @Test
    public void testGetNumHoras() {
        assertEquals(0, tarea.getNumHoras());
    }

    @Test
    public void testSetNumHoras() {
        tarea.setNumHoras(5);
        assertEquals(5, tarea.getNumHoras());
    }

    @Test
    public void testIsEstaCompletada() {
        assertFalse(tarea.isEstaCompletada());
    }

    @Test
    public void testSetEstaCompletada() {
        tarea.setEstaCompletada(true);
        assertTrue(tarea.isEstaCompletada());
    }

    @Test
    public void testGetDescripcion() {
        assertEquals("", tarea.getDescripcion());
    }

    @Test
    public void testSetDescripcion() {
        tarea.setDescripcion("Realizar tarea importante");
        assertEquals("Realizar tarea importante", tarea.getDescripcion());
    }

    @Test
    public void testSetCompletadaPor() {
        Trabajador trabajador = new Trabajador();
        tarea.setCompletadaPor(trabajador);
        assertEquals(trabajador, tarea.getCompletadaPor());
    }

    @Test
    public void testHashCode() {
        int expectedHashCode = Objects.hash(tarea.descripcion, tarea.id, tarea.rol);
        assertEquals(expectedHashCode, tarea.hashCode());
    }

    @Test
    public void testToString() {
        String expectedToString = String.format("Tarea id=%s, %s, %s horas, %s, descripcion: %s",
                tarea.id, tarea.rol, tarea.numHoras, tarea.estaCompletada, tarea.descripcion);
        assertEquals(expectedToString, tarea.toString());
    }
}
