package dao;

import java.util.List;


import serialitation.Venta;
import serialitation.Producto;
import serialitation.Usuario;


public interface IUsuarioDAO{
	public void storeUsuario(Usuario usuario);
	public List<Usuario> getUsuarios();
	public Usuario getUsuario(String email);
}
