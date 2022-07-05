package client.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import serialitation.Venta;
import serialitation.Producto;
import serialitation.Usuario;
import util.RestauranteException;

public class VentasController {
	
	private WebTarget webTarget;
	private String email,nombre;
	private Producto prod;
	private List<Producto>productos,productosFav;
	
	
	
	
	public VentasController(WebTarget webTarget, String email) {
		super();
		this.webTarget = webTarget;
		this.email = email;
	}

	/**Realiza la compra de un producto
	 * @param email correo del usuario que realiza la compra
	 * @param idProducto id del producto a comprar
	 * @param precio por el cual se realiza la compra
	 * @throws RestauranteException excepcion lanzada cuando no se puede conectar
	 * con el servidor
	 */
	public void comprar(String email, int idProducto, double precio) throws RestauranteException {
		WebTarget webTarget = this.webTarget.path("restaurante/comprar/"+email +"/"+ idProducto);
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Venta c= new Venta();
		c.setPrecio(precio);
		Response response = invocationBuilder.post(Entity.entity(c, MediaType.APPLICATION_JSON));
		if (response.getStatus() != Status.OK.getStatusCode()) {
			throw new RestauranteException("" + response.getStatus());
		}
	}

	
	/**
	 * @return devuelve una lista de productos registrados
	 * @throws RestauranteException excepcion lanzada cuando no se puede conectar con el servidor
	 */
	public List<Producto> getProductos() throws RestauranteException{
		WebTarget webTarget = this.webTarget.path("restaurante/productos");
		List<Producto>lProductos = webTarget.request( MediaType.APPLICATION_JSON ).get( new GenericType<List<Producto>>() {
	     } );
		
		lProductos.addAll(lProductos);
		return lProductos;
	}
	
	/**
	 * @return devuelve una lista de productos registrados
	 * @throws RestauranteException excepcion lanzada cuando no se puede conectar con el servidor
	 */
	public List<Producto> getProductosEnVenta() throws RestauranteException{
		WebTarget webTarget = this.webTarget.path("restaurante/productos/venta");
		List<Producto>lProductos = webTarget.request( MediaType.APPLICATION_JSON ).get( new GenericType<List<Producto>>() {
	    } );

		lProductos.addAll(lProductos);
		return lProductos;
	}
	
	/** devuelve un producto a partir de su id
	 * @param idProducto id del producto a devolver
	 * @return Producto con el id introducido
	 */
	public Producto getProducto(int idProducto)throws RestauranteException {
		WebTarget webTarget = this.webTarget.path("collector/getProducto/"+ idProducto);
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();
		if (response.getStatus() == Status.OK.getStatusCode()) {
			Producto producto = response.readEntity(Producto.class);
			return producto;
		} else {
			throw new RestauranteException("" + response.getStatus());
		}
	}
	
	/** 
	 * @return devuelve la lista de productos favoritos
	 * @throws RestauranteException excepcion lanzada cuando no se puede conectar con el servidor
	 */
	public List<Producto> getProductosFavoritos() throws RestauranteException{
		WebTarget webTarget = this.webTarget.path("restaurante/producto/favoritos/"+ email);
		List<Producto>lProductos = webTarget.request( MediaType.APPLICATION_JSON ).get( new GenericType<List<Producto>>() {
	     } );
		
		
		lProductos.addAll(lProductos);
		return lProductos;
	}
	
	
	
	
	
	
	/**Crea el panel de la ventana para un producto
	 * @param p Producto para el que crear el panel
	 * @param pCentro JPanel al que anadir el producto
	 */
	public void crearPanel(Producto p, JPanel pCentro) {
		this.prod=p;
		JPanel pContenido= new JPanel();
		pContenido.setLayout(new BoxLayout(pContenido, BoxLayout.Y_AXIS));
		JPanel pProducto= new JPanel();
		pProducto.add(new JLabel (p.getNombre()));
		pProducto.add(new JLabel(""+ p.getPrecio()+ "Euros"));
		
		
		
		pContenido.add(pProducto);
		
		
		pCentro.add(pContenido);
		pCentro.revalidate();
		pCentro.repaint();

	}
	
	/** Anade un producto a favoritos
	 * @param p Producto a anadir a productos favoritos
	 * @param email del usuario que lo anade a favoritos
	 * @throws RestauranteException excepcion lanzada cuando no se puede conectar con el servidor
	 */
	public void anadirFav(Producto p, String email) throws RestauranteException {
		WebTarget webTarget = this.webTarget.path("restaurante/anadirFav/"+ email);
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(p.getId(), MediaType.APPLICATION_JSON));
		if (response.getStatus() != Status.OK.getStatusCode()) {
			throw new RestauranteException("" + response.getStatus());
		}
	}
	
	

	

	
	

	
	/**Ordena un panel por precio ascendente
	 * @param pCentro panel a ordenar
	 */
	public void ordenarPorPrecioAsc( JList<Producto> lProducto, DefaultListModel<Producto> mProducto) {
		productos.sort(new Comparator<Producto>(){
			   @Override
			   public int compare(Producto p1,Producto p2) {
				   
		               Double precio = p1.getPrecio();
		               Double precio2= p2.getPrecio();
					   return precio.compareTo(precio2);
				 //TODO return 1 if rhs should be before lhs 
			     //     return -1 if lhs should be before rhs
			     //     return 0 otherwise (meaning the order stays the same)
			     }
			 });
		mProducto.removeAllElements();
		mProducto.addAll(productos);
		lProducto.revalidate();
		lProducto.repaint();
		//TODO si no pCentro repaint
	}
	
	/**Ordena un panel por precio descendente
	 * @param pCentro panel a ordenar
	 */
	public void ordenarPorPrecioDesc( JList<Producto> lProducto, DefaultListModel<Producto> mProducto) {
		productos.sort(new Comparator<Producto>(){
			   @Override
			   public int compare(Producto p1,Producto p2) {
				   
		               Double precio = p1.getPrecio();
		               Double precio2= p2.getPrecio();
					   return precio2.compareTo(precio);
				 //TODO return 1 if rhs should be before lhs 
			     //     return -1 if lhs should be before rhs
			     //     return 0 otherwise (meaning the order stays the same)
			     }
			 });
		mProducto.removeAllElements();
		mProducto.addAll(productos);
		lProducto.revalidate();
		lProducto.repaint();
	}
	
	
	
	
	
	/**Muestra los favoritos de un usuario en un panel
	 * @param pCentro panel en el que se muestra
	 * @param email del usuario que tiene los productos en favoritos
	 */
	public void mostrarFavoritos(JList<Producto> lProducto, DefaultListModel<Producto> mProducto) {
		try {
			List<Producto>productos= getProductosFavoritos();
			System.out.println("Se estan haciendo los favoritos");
			mProducto.removeAllElements();
			mProducto.addAll(productos);
			lProducto.revalidate();
		} catch (RestauranteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	

	

	
	



}
