package server;

import java.util.ArrayList;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;


import serialitation.Venta;
import serialitation.Producto;
import serialitation.Usuario;
import service.VentasService;

import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;


@Path("/Restaurante")
@Produces(MediaType.APPLICATION_JSON)
public class ReventaServer {
		
	private VentasService vs;
	
	

	public void setVs(VentasService vs) {
		this.vs = vs;
	}


	public ReventaServer() {
		vs= new VentasService();
	}
	
	
	/** 
	 * Anade una compra
	 * @param c
	 * @param x
	 * @param idProd
	 * @return
	 */
	@POST
	@Path("/comprar/{x}/{y}")
	public Response addCompra(Venta c, @PathParam("x") String x, @PathParam("y") int idProd) {
		vs.comprarProducto(x, idProd,c.getPrecio());
		System.out.println("*Received purchase*");
		return Response.ok().build();
	}
	
	
	
	/** 
	 * Devuelve el login del usuario
	 * @param u
	 * @return
	 */
	@POST
	@Path("/logIn")
	public Response logIn(Usuario u) {
		boolean logIn=vs.logIn(u.getEmail(), u.getPassword());
		System.out.println("*Realized logIn*");
		Response r=Response.ok(logIn).build();
		System.out.println(r.getEntity());
		return Response.ok(logIn).build();
	}
	
	/**
	 * Devuelve el registro realizado
	 * @param u
	 * @return
	 */
	@POST
	@Path("/registro")
	public Response registro(Usuario u) {
		boolean registro=vs.registro(u);
		System.out.println("*Realizando registro*");
		return Response.ok(registro).build();
	}
	
	

	/**
	 * Obtener un producto
	 * @param x
	 * @return
	 */
	@GET
	@Path("/getProducto/{x}")
	public Response getProducto(@PathParam("x") int x) {
		Producto producto=vs.getProducto(x);
		return Response.ok(producto).build();
	}
	
	/**
	 * @return devuelve un producto 
	 */
	@GET
	@Path("/productos")
	public List<Producto> getProducto() {
		 List<Producto>prod=vs.getProductos();
		 return prod;
	}
	

	
	
	
	
	
	
	

	/**
	 * @param x
	 * @return productos favoritos
	 */
	@GET
	@Path("/numFavoritos/{x}")
	public int getNumFavoritos(@PathParam("x") int x) {
		return vs.getProductosFavoritos(x);
	}
	
	
	
	/**
	 * @param x
	 * @return los usuarios
	 */
	@GET
	@Path("/getUsuario/{x}")
	public Response getUsuario(@PathParam("x") String x) {
		System.out.println(x);
		Usuario usuario=vs.getUsuario(x);
		return Response.ok(usuario).build();
	}
	
	
	
	/**
	 * @param id
	 * @param email
	 * @return producto aniadido a favoritso
	 */
	@POST
	@Path("/anadirFav/{x}")
	public Response addProductoFav(int id, @PathParam("x") String email ) {
		vs.addProductoFav(id, email);
		System.out.println("*Producto añadido*");
		return Response.ok().build();
	}
	
	
	
	
	
	/**
	 * @param email
	 * @return compras realizadas pasandole el email
	 */
	@GET
	@Path("/compras/{x}")
	public List<Venta> getCompras(@PathParam("x") String email) {
		return vs.getCompras(email);
	}
	
	
	

	
}
