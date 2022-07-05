package dao;

import java.util.List;


import serialitation.Producto;


public interface IProductoDAO {
	public void storeProducto(Producto prod);
	public List<Producto> getProductos();
	
}
