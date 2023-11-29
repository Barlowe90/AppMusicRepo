package umu.tds.persistencia;

import java.util.List;

import umu.tds.modelo.Usuario;

public interface IAdaptadorUsuarioDAO {
	public void registrarUsuario(Usuario usuario);

	public void borrarUsuario(Usuario usuario);

	public void modificarUsuario(Usuario usuario);

	public Usuario recuperaUsuario(int key);

	public List<Usuario> recuperarTodosUsuarios();
}