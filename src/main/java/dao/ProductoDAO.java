package dao;

import java.util.ArrayList;

import java.util.List;

import javax.jdo.Extent;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;


import serialitation.Producto;

import serialitation.Usuario;



public class ProductoDAO implements IProductoDAO{

	private PersistenceManagerFactory pmf;

	/**
	 * Constructor de ProductoDAO
	 */
	public ProductoDAO() {
		// TODO Auto-generated constructor stub
		pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
	}
	
	/**
	 *Metodo para almacenar un producto en la BD
	 */
	public void storeProducto(Producto producto) {
		this.storeObject(producto);
	}
	
	/** Metodo que almacena un objeto en la BD
	 * @param object objeto de cualquier clase a guardar en la BD
	 */
	private void storeObject(Object object) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();
			System.out.println("   * Storing an Producto: " + object);
			pm.makePersistent(object);
			tx.commit();
		} catch (Exception ex) {
			System.out.println("   $ Error storing an object: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
	}
	
	
	/**
	 * Metodo que devuelve la lista de todos los productos de la BD
	 */
	public List<Producto> getProductos() {
		PersistenceManager pm = pmf.getPersistenceManager();
		/*
		 * By default only 1 level is retrieved from the db so if we wish to fetch more
		 * than one level, we must indicate it
		 */

		Transaction tx = pm.currentTransaction();
		List<Producto> productos = new ArrayList<>();

		try {
			System.out.println("   * Retrieving an Extent for Products.");

			tx.begin();
			Extent<Producto> extent = pm.getExtent(Producto.class, true);

			for (Producto p : extent) {
				System.out.println(p.getPrecio());
				productos.add(p);
			}

			tx.commit();
		} catch (Exception ex) {
			System.out.println("   $ Error retrieving an extent: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}

		return productos;
	}
	
	
	
	
	
	
	

	/** Setter del PersistenceManagerFactory
	 * @param pmf2 Valor que queremos asignar con el setter
	 */
	public void setPmf(PersistenceManagerFactory pmf2) {
		this.pmf=pmf2;
	}
	
}
