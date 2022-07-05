package service;



import javax.jdo.JDOHelper;
import javax.jdo.JDOUserException;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

import org.omg.CORBA.UserException;

import dao.*;
import serialitation.*;
import java.util.*;


/**
 * clase que gestiona la logica de la aplicacion
 * 
 * @author usuario
 *
 */
public class VentasService {
	IProductoDAO productDao;
	IUsuarioDAO	usuarioDao;
	

	PersistenceManagerFactory pmf;
	
	public VentasService() {
		productDao= new ProductoDAO();
		usuarioDao= new UsuarioDAO();
		pmf= JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
	}
	
	
	public IProductoDAO getProductDao() {
		return productDao;
	}


	public void setProductDao(IProductoDAO productDao) {
		this.productDao = productDao;
	}


	public IUsuarioDAO getUsuarioDao() {
		return usuarioDao;
	}


	public void setUsuarioDao(IUsuarioDAO usuarioDao) {
		this.usuarioDao = usuarioDao;
	}




	public PersistenceManagerFactory getPmf() {
		return pmf;
	}


	public void setPmf(PersistenceManagerFactory pmf) {
		this.pmf = pmf;
	}


	public boolean logIn(String email, String contrasena) {	
		try {
			Usuario u= usuarioDao.getUsuario(email);
			if (u!=null) {
				if (u.getPassword().contentEquals(contrasena)) {
					return true;
				}
			}
			return false;
		}catch(JDOUserException exception) {
			return false;
		}
		
	}
	

	
	public boolean registro(Usuario u) {
		if (usuarioDao.getUsuario(u.getEmail())==null) {
			usuarioDao.storeUsuario(u);
			return true;
		}
		System.out.println("*No se ha realizado el registro*");
		return false;	
	}
	
	/**
	 * compra un producto, si este existe
	 * @param email
	 * @param idProducto
	 * @param precio
	 */
	public void comprarProducto(String email, int idProducto, double precio) {
		PersistenceManager pm=pmf.getPersistenceManager();
		Usuario u= null;
		Producto p=null;
		try {	
			u=pm.getObjectById(Usuario.class, email);
			p=pm.getObjectById(Producto.class, idProducto);
			Venta c= new Venta();
			c.setProducto(p.getId());
			c.setPrecio(precio);
			if (p instanceof Producto) {
				c.setIdCat(1);
			}else {
				c.setId(2);
			}
			u.getCompras().add(c);
			//uVendedor.getProductosVendidos().add(p);
			
		}catch(Exception e){
			if (u==null) {
				System.out.println("Error al realizar la compra no existe ese usuario");
			}else {
				System.out.println("Error al realizar la compra no existe producto");
				System.out.println(e.getMessage());
			}
		}finally {
			pm.close();
		}
	}
	

	/**
	 * Anade productos a favoritos
	 * @param id
	 * @param email
	 * @exception si no se puede anadir a favoritos
	 */
	public void addProductoFav(int id, String email) {
		// TODO Auto-generated method stub
		PersistenceManager pm=pmf.getPersistenceManager();
		Usuario u= null;
		try {
			u=pm.getObjectById(Usuario.class, email);
			Producto p=pm.getObjectById(Producto.class,id);
			u.getProductosFavoritos().add(p);
			//tengo que meter en una lista todos 
		}catch(Exception e){
			if (u==null) {
				System.out.println("Error al seleccionar el producto para guardarlo en FAV");
			}else {
				System.out.println("Error al guardar el producto como FAV");
			}
		}finally {
			pm.close();
		}
		
	}
	

	
	


	/**
	 * coger un producto
	 * @param x
	 * @return
	 */
	public Producto getProducto(int x) {
		PersistenceManager pm=pmf.getPersistenceManager();
		Producto p=null;
		try {
			p=pm.getObjectById(Producto.class,x);
		}catch(Exception e) {
			System.out.println("* Error el producto no existe *");
		}finally {
			pm.close();
		}
		return p;
		
	}
	

	/**
	 * anade productos a la lista
	 * @return
	 */
	public List<Producto> getProductos() {
		PersistenceManager pm=pmf.getPersistenceManager();
		List<Producto> p=new ArrayList<Producto>();
		try {
			p=productDao.getProductos();
		}catch(Exception e) {
			System.out.println("* Error el producto no existe *");
		}finally {
			pm.close();
		}
		return p;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	/** coje los usuarios con el email
	 * @param email
	 * @return
	 */
	public Usuario getUsuario(String email) {
		return usuarioDao.getUsuario(email);
	}

	


	/**
	 * coje los productos  que tienes en favoritos
	 * @param x
	 * @return
	 * @exception si el usuario no existe al realizar la compra
	 */
	public List<Producto> getProductosOrdenadorFavoritos(String x) {
		PersistenceManager pm=pmf.getPersistenceManager();
		List<Producto>po= new ArrayList<Producto>();
		Usuario u= null;
		try {	
			u=pm.getObjectById(Usuario.class, x);
			for(Producto p:u.getProductosFavoritos()) {
				if (p instanceof Producto) {
					Producto prod=(Producto)p;
					po.add(prod);
				}
			}
		}catch(Exception e){
				System.out.println("Error al realizar la compra no existe ese usuario");
		}finally {
			pm.close();
		}
		return po;
	}
	
	
	



	
	
	/** 
	 * @param x
	 * @return  cuenta los productos que hay en favoritos
	 */
	public int getProductosFavoritos(int x){
		PersistenceManager pm=pmf.getPersistenceManager();
		int contador = 0;
		Producto p= null;
		try {
			p=pm.getObjectById(Producto.class, x);
			List<Usuario> usuarios = usuarioDao.getUsuarios();
			for(Usuario u: usuarios) {
				for(Producto p2: u.getProductosFavoritos()) {
					if(p2.getId()==p.getId()) {
						contador++;
					}
				}
			}
		}catch(Exception e){
			System.out.println("Error! ese usuario no tiene favoritos");
		}finally {
			pm.close();
		}
		return contador;

	}

	


	/**
	 * @param email
	 * @return las compras realizadas por cada usuario
	 */
	public List<Venta> getCompras(String email) {
		PersistenceManager pm=pmf.getPersistenceManager();
		Usuario u= null;
		List<Venta>compras= new ArrayList<Venta>();
		try {	
			u=pm.getObjectById(Usuario.class, email);
			return u.getCompras();
		}catch(Exception e){
				System.out.println("Error al realizar la Reclamacion no existe ese usuario");
		}finally {
			pm.close();
		}
		return compras;
	}


	
}
