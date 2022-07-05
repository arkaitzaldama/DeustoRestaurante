package server;

import java.util.List;

import java.util.concurrent.atomic.AtomicBoolean;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;


import serialitation.Producto;
import serialitation.Tarjeta;
import serialitation.Usuario;
import service.VentasService;

/**
 * 
 * clase que añade datos de prueba
 * @author usuario
 *
 */
public class MainServer{
	
	public static void main(String []args) {
		VentasService vs= new VentasService();

		
		
		Tarjeta t1= new Tarjeta();
		t1.setAnyoVencimiento(2023);
		t1.setMesVencimiento(5);
		t1.setCodigoSecreto(279);
		t1.setNombre("6739M");
		Usuario u1=new Usuario();
		u1.setEmail("u@gmail.com");
		u1.setPassword("u");
		u1.setTarjeta(t1);
		vs.registro(u1);
		
		
		Tarjeta t2= new Tarjeta();
		t2.setAnyoVencimiento(2024);
		t2.setMesVencimiento(5);
		t2.setCodigoSecreto(270);
		t2.setNombre("6839M");
		Usuario u2=new Usuario();
		u2.setEmail("a@gmail.com");
		u2.setPassword("a");
		u2.setTarjeta(t2);
		vs.registro(u2);
		
		
		
		Producto p1= new Producto(0, null);
		p1.setPrecio(4.30);
		p1.setNombre("Menu BigKing");
		
		Producto p2=new Producto(0, null);
		p2.setPrecio(10);
		p2.setNombre("Menu Tagliatela");
		
		
		Producto p3= new Producto(0,null);
		p3.setPrecio(20);
		p3.setNombre("Menu Txakoli Simon");

		
		Producto p4= new Producto(0,null);
		p4.setPrecio(12);
		p4.setNombre("cervecera La palmera");

		
		

		
	}
}
