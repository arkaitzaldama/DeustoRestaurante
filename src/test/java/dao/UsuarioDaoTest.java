package dao;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.FetchPlan;
import javax.jdo.JDOHelper;
import javax.jdo.JDOUserException;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


import serialitation.Venta;
import serialitation.Producto;
import serialitation.Usuario;

@RunWith(MockitoJUnitRunner.class)
public class UsuarioDaoTest {
	
	@Mock
	PersistenceManagerFactory pmf;
	@Mock
	PersistenceManager pm;
	@Mock
	Transaction tx;
	
	UsuarioDAO dao;
	Usuario u;
	
	@Before
	public void setup() {
		dao= new UsuarioDAO();
		dao.setPmf(pmf);
		u= new Usuario();
		u.setEmail("a");
	}
	
	
	@Test
	public void testGetUsuario() {
		when(pmf.getPersistenceManager()).thenReturn(pm);
		when(pm.getObjectById(Usuario.class, "a")).thenReturn(u);
		when(pm.getObjectById(Usuario.class, "b")).thenThrow(JDOUserException.class);
		Usuario u1=dao.getUsuario("a");
		assertEquals(u,u1);
		Usuario u2=dao.getUsuario("b");
		assertEquals(null,u2);
	}

}
