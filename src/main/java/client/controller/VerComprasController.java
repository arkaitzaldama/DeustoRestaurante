package client.controller;

import java.util.ArrayList;


import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import dao.UsuarioDAO;
import serialitation.Venta;

import serialitation.Producto;
import util.RestauranteException;



public class VerComprasController {
	private WebTarget webTarget;
	private String email;
	UsuarioDAO uDao;

	
	public VerComprasController(WebTarget webTarget, String email) {
		super();
		this.webTarget = webTarget;
		this.email = email;
	}
	
	/**
	 * @param email
	 * @return lista de los productos comprados
	 * @throws RestauranteException
	 */
	public List<Venta>  getListaProductosComprados(String email)throws RestauranteException {
		WebTarget webTarget = this.webTarget.path("restaurante/compras/"+email);
		List<Venta>lCompra = webTarget.request( MediaType.APPLICATION_JSON ).get( new GenericType<List<Venta>>() {
	     } );
		return lCompra;
	}
	

		
	
	

	
}
