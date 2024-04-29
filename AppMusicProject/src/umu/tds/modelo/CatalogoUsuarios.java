package umu.tds.modelo;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import umu.tds.persistencia.DAOException;
import umu.tds.persistencia.FactoriaDAO;
import umu.tds.persistencia.IAdaptadorUsuarioDAO;

/**
 * El catalogo mantiene los objetos en memoria usando una tabla hash para
 * mejorar el rendimiento. Esto no se podrria hacer en la base de datos con una
 * gran cantidad de objetos. En dicho caso directamente se ejecutaria en la base
 * de datos.
 */
public class CatalogoUsuarios {
	private Map<Integer, Usuario> usuariosID;
	private Map<String, Usuario> usuariosLogin;
	private static CatalogoUsuarios unicaInstancia = new CatalogoUsuarios();
	private FactoriaDAO factoria;
	private IAdaptadorUsuarioDAO adaptadorUsuario;

	private CatalogoUsuarios() {
		usuariosID = new HashMap<Integer, Usuario>();
		usuariosLogin = new HashMap<String, Usuario>();

		try {
			factoria = FactoriaDAO.getInstancia();
			adaptadorUsuario = factoria.getUsuarioDAO();
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
		adaptadorUsuario.registrarUsuario(usuario);
	}

	public void removeUsuario(Usuario usuario) {
		usuariosID.remove(usuario.getId());
		usuariosLogin.remove(usuario.getNick());
		adaptadorUsuario.borrarUsuario(usuario);
	}

	public void updateUsuario(Usuario usuario) {
		adaptadorUsuario.updateUsuario(usuario);
		usuariosID.put(usuario.getId(), usuario);
		usuariosLogin.put(usuario.getNick(), usuario);
	}

	public Usuario getUsuario(int key) {
		return usuariosID.get(key);
	}

	public Usuario getUsuario(String nick) {
		return usuariosLogin.get(nick);
	}

	public List<Usuario> getAllUsuarios() throws DAOException {
		return new LinkedList<Usuario>(usuariosLogin.values());
	}

	public List<Cancion> getRecientes(Usuario usuario) {
		return usuario.getRecientes();
	}

	public List<Cancion> getCancionesDePlayList(PlayList playList) {
		return playList.getCanciones();
	}

	public List<PlayList> getAllPlayListPorUsuario(Usuario usuario) {
		return usuario.getPlaylists();
	}

	/**
	 * Funcion que nos permite recuperar todos los usuarios para trabajar con ellos
	 * en memoria
	 * 
	 * @throws DAOException
	 */
	private void cargarCatalogo() throws DAOException {
		List<Usuario> usuariosBD = adaptadorUsuario.getAllUsuarios();
		for (Usuario usuario : usuariosBD) {
			System.out.println("cojo al usuario de la bd " + usuario.getNick() + " y las playlist que tiene son "
					+ usuario.getPlaylists().toString());
			usuariosID.put(usuario.getId(), usuario);
			usuariosLogin.put(usuario.getNick(), usuario);
		}
	}
}
