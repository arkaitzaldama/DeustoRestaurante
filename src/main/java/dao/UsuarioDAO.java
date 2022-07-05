package dao;

import java.util.ArrayList;

import java.util.List;

import javax.jdo.Extent;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;


import serialitation.*;


public class UsuarioDAO implements IUsuarioDAO {
	

	private PersistenceManagerFactory pmf;

	/**
	 * Constructor de la clase
	 */
	public UsuarioDAO() {
		pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
	}

	/**
	 * Metodo para almacenar un usuario en la BD
	 */
	public void storeUsuario(Usuario usuario) {
		this.storeObject(usuario);
	}

	/** Metodo que almacena un objeto en la BD
	 * @param object objeto de cualquier clase a guardar en la BD
	 */
	private void storeObject(Object object) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();
			System.out.println("   * Storing an object: " + object);
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
	 * Metodo que devuelve una lista con todos los usuarios de la BD
	 */
	public List<Usuario> getUsuarios() {
		PersistenceManager pm = pmf.getPersistenceManager();
		/*
		 * By default only 1 level is retrieved from the db so if we wish to fetch more
		 * than one level, we must indicate it
		 */

		Transaction tx = pm.currentTransaction();
		List<Usuario> usuarios = new ArrayList<>();

		try {
			System.out.println("   * Retrieving an Extent for Products.");

			tx.begin();
			Extent<Usuario> extent = pm.getExtent(Usuario.class, true);

			for (Usuario u : extent) {
				usuarios.add(u);
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

		return usuarios;
	}
	
	
	




	
	

	
	/**
	 * Metodo que devuelve un objeto usuario de la BD a traves de su email
	 */

	public Usuario getUsuario(String email) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Usuario usuario = null;

		try {
			usuario=pm.getObjectById(Usuario.class, email);
			pm.makeTransient(usuario);
//			System.out.println(usuario.getEmail());

		} catch (Exception ex) {
			System.out.println("   $ Error no existe ese usuario: " + ex.getMessage());
			return null;
		} finally {
			//System.out.println(usuario.getEmail());
			pm.close();
		}

		return usuario;
	}
	
	
	
	
	/** getter del PersistanceManagerFactory
	 * @return devuelve el PersistanceManagerFactory
	 */
	public PersistenceManagerFactory getPmf() {
		return pmf;
	}

	/** setter del PersistanceManagerFactory
	 * @param pmf recibe como parametro el valor a asignar al PersistanceManagerFactory
	 */
	public void setPmf(PersistenceManagerFactory pmf) {
		this.pmf = pmf;
	}



}
