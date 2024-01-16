package domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class BDExceptionTest {

	private BDexception exception2;
	private BDexception exception;
	private Throwable t;
	
	@Before
	public void setUp() {
		t = new Throwable();
		exception= new BDexception("ERROR",t);
		exception2 = new BDexception("ERROR");
		
	}
	
	@Test
	public void testBDexception(){
		assertNotNull(exception);
		assertNotNull(exception2);
		assertEquals("ERROR", exception.getMessage());
		assertEquals("ERROR", exception2.getMessage());
		assertEquals(t, exception.getCause());
		
		
	}
}
