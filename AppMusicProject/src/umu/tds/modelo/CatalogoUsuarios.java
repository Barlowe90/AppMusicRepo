package umu.tds.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import umu.tds.persistencia.DAOException;

public class CatalogoUsuarios {
	private Map<Integer, Usuario> usuariosID;
	private Map<String, Usuario> usuariosLogin;
	private static CatalogoUsuarios unicaInstancia = new CatalogoUsuarios();

//	private FactoriaDAO dao;
//	private IAdaptadorUsuarioDAO adaptadorUsuario;

	private CatalogoUsuarios() {
		try {

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static CatalogoUsuarios getUnicaInstancia() {
		return unicaInstancia;
	}

	public void addUsuario(Usuario usuario) {
		usuariosID.put(usuario.getId(), usuario);
		usuariosLogin.put(usuario.getUsuario(), usuario);
	}

	public void removeUsuario(Usuario usuario) {
		usuariosID.remove(usuario.getId());
		usuariosLogin.remove(usuario.getUsuario());
	}

	public Usuario getUsuario(int key) {
		return usuariosID.get(key);
	}

	public Usuario getUsuario(String user) {
		return usuariosLogin.get(user);
	}

	public List<Usuario> getAllUsuarios() {
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		for (Usuario usuario : usuariosID.values()) {
			lista.add(usuario);
		}
		return lista;
	}

	/**
	 * Función que nos permite recuperar todos los usuarios para trabajar con ellos
	 * en memoria
	 * 
	 * @throws DAOException
	 */
//	private void cargarUsuario() throws DAOException {
//		List<Usuario> usuariosBD = adaptadorUsuario.recuperarTodosUsuarios();
//		for (Usuario usuario : usuariosBD) {
//			usuariosID.put(usuario.getId(), usuario);
//		}
//	}
}
