package domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Objects;

import org.junit.Before;
import org.junit.Test;

public class PlazaParkingTest {

    private PlazaParking plazaParking;

    @Before
    public void setUp() {
        plazaParking = new PlazaParking();
        PlazaParking.setContador(0);
    }

    @Test
    public void testConstructor() {
        assertNotNull(plazaParking);
    }

    @Test
    public void testGetId() {
        assertEquals(1, plazaParking.getId());
    }

    @Test
    public void testGetRow() {
        assertEquals(0, plazaParking.getRow());
    }

    @Test
    public void testSetRow() {
        plazaParking.setRow(2);
        assertEquals(2, plazaParking.getRow());
    }

    @Test
    public void testGetContador() {
        assertEquals(0, PlazaParking.getContador());
    }

    @Test
    public void testSetContador() {
        PlazaParking.setContador(5);
        assertEquals(5, PlazaParking.getContador());
    }

    @Test
    public void testGetReserva() {
        assertNull(plazaParking.getReserva());
    }

    @Test
    public void testSetReserva() {
        Reserva reserva = new Reserva();
        plazaParking.setReserva(reserva);
        assertEquals(reserva, plazaParking.getReserva());
    }

    @Test
    public void testGetColumn() {
        assertEquals(0, plazaParking.getColumn());
    }

    @Test
    public void testSetColumn() {
        plazaParking.setColumn(3);
        assertEquals(3, plazaParking.getColumn());
    }

    @Test
    public void testIsOcupada() {
        assertFalse(plazaParking.isOcupada());
    }

    @Test
    public void testSetOcupada() {
        plazaParking.setOcupada(true);
        assertTrue(plazaParking.isOcupada());
    }

    @Test
    public void testGetParking() {
        assertNull(plazaParking.getParking());
    }

    @Test
    public void testSetParking() {
        Parking parking = new Parking();
        plazaParking.setParking(parking);
        assertEquals(parking, plazaParking.getParking());
    }

    @Test
    public void testHashCode() {
        int expectedHashCode = Objects.hash(plazaParking.column, plazaParking.id, plazaParking.ocupada, plazaParking.row);
        assertEquals(expectedHashCode, plazaParking.hashCode());
    }

    @Test
    public void testToString() {
        String expectedToString = String.format("PlazaParkin %s row=%s, column=%s, ocupada=%s",
                plazaParking.id, plazaParking.row, plazaParking.column, plazaParking.ocupada);
        assertEquals(expectedToString, plazaParking.toString());
    }
}