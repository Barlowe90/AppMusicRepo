package umu.tds.modelo;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import umu.tds.persistencia.DAOException;
import umu.tds.persistencia.FactoriaDAO;
import umu.tds.persistencia.IAdaptadorUsuarioDAO;

/**
 * El catálogo mantiene los objetos en memoria usando una tabla hash para
 * mejorar el rendimiento. Esto no se podría hacer en la base de datos con una
 * gran cantidad de objetos. En dicho caso directamente se ejecutaría en la base
 * de datos.
 */
public class CatalogoUsuarios {
	private Map<Integer, Usuario> usuariosID;
	private Map<String, Usuario> usuariosLogin;
	private static CatalogoUsuarios unicaInstancia = new CatalogoUsuarios();
	private FactoriaDAO dao;
	private IAdaptadorUsuarioDAO adaptadorUsuario;

	private CatalogoUsuarios() {
		try {
//			dao = FactoriaDAO.getInstancia(FactoriaDAO.DAO_TDS); //tiendaTPV
			dao = FactoriaDAO.getUnicaInstancia(); // TODO revisar, no estoy seguro. login2022
			adaptadorUsuario = dao.getUsuarioDAO();

			usuariosID = new HashMap<Integer, Usuario>();
			usuariosLogin = new HashMap<String, Usuario>();
			this.cargarCatalogo();
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	public static CatalogoUsuarios getUnicaInstancia() {
		return unicaInstancia;
	}

	public void addUsuario(Usuario usuario) {
		usuariosID.put(usuario.getId(), usuario);
		usuariosLogin.put(usuario.getNick(), usuario);
	}

	public void removeUsuario(Usuario usuario) {
		usuariosID.remove(usuario.getId());
		usuariosLogin.remove(usuario.getNick());
	}

	public Usuario getUsuario(int key) {
		return usuariosID.get(key);
	}

	public Usuario getUsuario(String nick) {
		return usuariosLogin.get(nick);
	}

	public List<Usuario> getAllUsuarios() {
		return new LinkedList<Usuario>(usuariosLogin.values());
	}

	/**
	 * Función que nos permite recuperar todos los usuarios para trabajar con ellos
	 * en memoria
	 * 
	 * @throws DAOException
	 */
	private void cargarCatalogo() throws DAOException {
		List<Usuario> usuariosBD = adaptadorUsuario.getAllUsuarios();
		for (Usuario usuario : usuariosBD) {
			usuariosID.put(usuario.getId(), usuario);
			usuariosLogin.put(usuario.getNick(), usuario);
		}
	}
}
