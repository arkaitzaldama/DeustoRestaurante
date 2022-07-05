package serialitation;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;



import serialitation.Producto;
import serialitation.Usuario;

public class ProductoTest {
	
	
	private Producto p;
	
	private Usuario u;
	
	
	
	@Test
	public void testPrecio() {
		p.setPrecio(100);
		assertEquals(p.getPrecio(),100);
	}

}
