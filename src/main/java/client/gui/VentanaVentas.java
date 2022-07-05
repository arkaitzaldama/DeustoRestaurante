package client.gui;

import java.awt.event.ActionEvent;


import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;


import client.controller.VentasController;


import client.controller.VentasController;
import client.controller.VerComprasController;
import dao.UsuarioDAO;

import serialitation.Venta;
import serialitation.Producto;
import serialitation.Usuario;
import util.RestauranteException;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;

public class VentanaVentas extends JFrame {
	
	private Client client;
	private WebTarget webTarget;
	private String email;
	private List<Producto> productos;
	private VentasController controller;
	private static VentanaVentas v1;
	private JPanel pNorte;
	private JPanel pCentro;
	private JPanel pProductos;
	private Producto p;
	private JList<Producto> lProductos;
	private DefaultListModel<Producto> mProductos;
	/**Metodo que construye la ventanaCompras
	 * @param cc Controller de la ventana donde se almacenan los metodos necesarios
	 * @param cliente cliente de la aplicacion
	 * @param webTarget para poder hacer las llamadas al server
	 * @param email email del usuario
	 */
	public VentanaVentas(VentasController cc, final Client cliente, final WebTarget webTarget, final String email) {
		this.client=cliente;
		this.webTarget=webTarget;
		this.email=email;
		this.controller= cc;
		v1 = this;
		pCentro= new JPanel();
		pProductos= new JPanel();
		pProductos.setLayout(new BoxLayout(pProductos, BoxLayout.Y_AXIS));
		pCentro.setLayout(new BorderLayout());

		

		
		
		pNorte= new JPanel();
		pNorte.setLayout(new BorderLayout());
		

		

		
		
		
		JButton button= new JButton("Comprar");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (p!=null) {
					try {
						System.out.println(p.getId());
						controller.comprar(email,p.getId(),p.getPrecio());
						mProductos.removeAllElements();
						productos.remove(p);
						mProductos.addAll(productos);
						pCentro.revalidate();
					} catch (RestauranteException e1) {
						System.out.println(e1.getMessage());
					}
				}
			}
			
		});

		JButton bMeGusta = new JButton("Anadir a favoritos");
		JButton bUsuarioFav = new JButton("Me gusta");
		
		bMeGusta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (p!=null) {
					try {
						controller.anadirFav(p,email)	;				//ComprasController.this.();
											
					} catch (RestauranteException e1) {
						System.out.println(e1.getMessage());
					}
				}
			}
			
		});
		
		
		
		lProductos= new JList<Producto>();
		
		mProductos= new DefaultListModel<>();
		mProductos.addAll(productos);
		lProductos.setModel(mProductos);
		
		lProductos.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				p=lProductos.getSelectedValue();
			}
		});
		
		lProductos.setSelectedIndex(0);
		
		JScrollPane jspProductos= new JScrollPane(lProductos);
		
		pCentro.add(jspProductos,BorderLayout.CENTER);

		
	
		
		
		
		
		
		JButton bVerCompras= new JButton("Ver compras");
		bVerCompras.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaVerCompras v=new VentanaVerCompras(new VerComprasController(webTarget, email), cliente, webTarget, email);
				v1.dispose();	
			}
			
		});
		

		
		
		
		
		
		
	}
	

	

	
	/** Crea el panel norte
	 * @param pCategorias panel de categorias que se añade al panel norte
	 * @param pOrdenar panel ordenar que se añade al panel norte
	 */
	public void crearPNorte(JPanel pCategorias, JPanel pOrdenar) {
		pNorte.add(pCategorias, BorderLayout.NORTH);
		pNorte.add(pOrdenar, BorderLayout.CENTER);
	}
	

	


}
