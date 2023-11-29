package umu.tds.persistencia;

import java.text.SimpleDateFormat;
import java.util.List;

import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import beans.Entidad;
import beans.Propiedad;

import umu.tds.modelo.Usuario;

public class AdaptadorUsuarioTDS implements IAdaptadorUsuarioDAO {
	private static ServicioPersistencia servicioPersistencia;
	private static AdaptadorUsuarioTDS unicaInstancia = null;
	private SimpleDateFormat dateFormat;

	public static AdaptadorUsuarioTDS getUnicaInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new AdaptadorUsuarioTDS();
		return unicaInstancia;
	}

	private AdaptadorUsuarioTDS() {
		servicioPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	}

	// TODO

	@Override
	public void registrarUsuario(Usuario usuario) {
		Entidad eUsuario = null;

		try {
			eUsuario = servicioPersistencia.recuperarEntidad(usuario.getId());
		} catch (NullPointerException e) {
		}
		if (eUsuario != null)
			return;
	}

	@Override
	public void borrarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub

	}

	@Override
	public void modificarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub

	}

	@Override
	public Usuario recuperaUsuario(int key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> recuperarTodosUsuarios() {
		// TODO Auto-generated method stub
		return null;
	}

	// TOD
}
