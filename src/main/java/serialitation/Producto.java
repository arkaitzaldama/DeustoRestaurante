package serialitation;

import java.util.ArrayList;

import java.util.Date;
import java.util.List;

import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;


import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;

@PersistenceCapable(detachable = "true")
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
public class Producto {
	
	
	@PrimaryKey
	@Persistent(valueStrategy=IdGeneratorStrategy.INCREMENT)
	private int id;
	private double precio;
	private String nombre;

	



	public Producto(double precio, String nombre) {
		
		this.nombre=nombre;
		this.precio=precio;
		
	}
	
	
	
	/**
	 * @return devuelve el precio
	 */
	public double getPrecio() {
		return precio;
	}
	/**
	 * @param modifica el precioSalida
	 */
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	/**
	 * 
	 * @return devuelve el nombre
	 * 
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param modfica el nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	

	/**
	 * 
	 * @return devuelve el id
	 * 
	 */
	public int getId() {
		return id;
	}

	/**
	 * 
	 * @param modifica el id
	 * 
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	
	
	
	public String toString() {
		return nombre + " " + precio + "Euros";
	}
	
	

	

}
