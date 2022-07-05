package performance;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.JDOUserException;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.ws.rs.core.Response;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.junit.ContiPerfRule;
import org.databene.contiperf.junit.ContiPerfSuiteRunner;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;



import dao.IProductoDAO;
import dao.IUsuarioDAO;
import dao.ProductoDAO;
import dao.UsuarioDAO;

import serialitation.Venta;
import serialitation.Producto;

import serialitation.Usuario;
import server.ReventaServer;
import service.VentasService;


public class ServicePerformanceTest {
	VentasService vs;
	private Usuario u;
	private Producto p;

	private ReventaServer rs;
	
	@Rule public ContiPerfRule rule = new ContiPerfRule();
	
	@Before
	public void setUp() {
		vs= new VentasService();
		rs= new ReventaServer();
		u= new Usuario();
		u.setEmail("u@gmail.com");
		u.setPassword("u");
		
		
		
	}
	
	
	
	
	@Test
	@PerfTest(invocations = 400, threads = 8)
    @Required(max = 2600, average = 1000)
	public void testGetProductos() {
		List<Producto> pos = vs.getProductos();
		assertEquals(vs.getProductos().size(),4);
	}
	

	
	

	
	

}
