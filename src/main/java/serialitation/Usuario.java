package serialitation;

import java.util.*;


import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import serialitation.Venta;
import serialitation.Producto;
import serialitation.Tarjeta;
import serialitation.Usuario;


@PersistenceCapable(detachable = "true")
public class Usuario {
	
	@PrimaryKey
	private String email;
	private String password;
	private Tarjeta tarjeta;

	
	
	
	
	
	
	/**
	 * compras realizadas por el usuario
	 */
	@Persistent(defaultFetchGroup = "true",  dependentElement = "true")
	@Join
	private List<Venta>compras;
	
	/**
	 * productos del usuario
	 */
	@Persistent(defaultFetchGroup = "true",dependentElement = "true")
	@Join
	private List<Producto>productos;
	
	
	
	/**
	 * productos FAV 
	 */
	@Persistent(defaultFetchGroup = "true")
	@Join
	private List<Producto>productosFavoritos;


	
	
	public Usuario() {
		
		productosFavoritos= new ArrayList<>();
		compras= new ArrayList<>();
	}
	
	/**
	 * @return email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param modifica email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return devuelve xcontraseña
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param modifica la password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	/**
	 * @return devuelve la lista de productos FAV
	 */
	public List<Producto> getProductosFavoritos() {
		return productosFavoritos;
	}
	/**
	 * @param modifica la lista de productosFavoritos
	 */
	public void setProductosFavoritos(List<Producto> productosFavoritos) {
		this.productosFavoritos = productosFavoritos;
	}
	

	/**
	 * @return devuelve la tarjeta
	 */
	public Tarjeta getTarjeta() {
		return tarjeta;
	}

	/**
	 * @param modifica la tarjeta
	 */
	public void setTarjeta(Tarjeta tarjeta) {
		this.tarjeta = tarjeta;
	}

	/**
	 * @return devuelve la lista de compras
	 */
	public List<Venta> getCompras() {
		return compras;
	}

	/**
	 * @param modifica la lista de compras
	 */
	public void setCompras(List<Venta> compras) {
		this.compras = compras;
	}

	/**
	 * @return devuelve la lista de productos
	 */
	public List<Producto> getProductos() {
		return productos;
	}

	/**
	 * @param modfica la lista de productos
	 */
	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	
	public Usuario clone() {
		Usuario u= new Usuario();
		u.setTarjeta(this.tarjeta);
		u.getCompras().addAll(compras);
		u.getProductos().addAll(productos);
		u.getProductosFavoritos().addAll(productosFavoritos);
		
		
		u.setEmail(this.email);
		u.setPassword(password);
		return u;
		
	}

}
