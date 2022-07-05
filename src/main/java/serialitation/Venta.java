package serialitation;

import javax.jdo.annotations.IdGeneratorStrategy;


import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;


@PersistenceCapable(detachable = "true")
public class Venta {
	
	@PrimaryKey
	@Persistent(valueStrategy=IdGeneratorStrategy.INCREMENT)
	private int id;
	private double precio;
	private int idCat;

	
	public void setIdCat(int idCat) {
		this.idCat = idCat;
	}
	

	private int producto;
	
	
	/**
	 * 
	 * @return devuelve el id de la venta
	 * 
	 */
	public int getId() {
		return id;
	}
	/**
	 * 
	 * @param id modifica el id de la compra
	 * 
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * 
	 * @return devuelve el producto de la compra
	 * 
	 */
	public int getProducto() {
		return producto;
	}
	/**
	 * 
	 * @param modifica el producto
	 */
	public void setProducto(int producto) {
		this.producto = producto;
	}
	/**
	 * 
	 * @return devuelve el producto 
	 * 
	 */

	/**
	 * 
	 * @return devuelve el precio de la venta
	 * 
	 */
	public double getPrecio() {
		return precio;
	}
	/**
	 * @param modifica el  precio de la venta
	 * 
	 */
	public void setPrecio(double precio) {
		this.precio = precio;
	}

	

}
