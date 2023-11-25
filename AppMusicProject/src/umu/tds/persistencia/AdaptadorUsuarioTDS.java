package umu.tds.persistencia;

import java.text.SimpleDateFormat;
import java.util.List;

import umu.tds.modelo.Usuario;

public class AdaptadorUsuarioTDS implements IAdaptadorUsuarioDAO {
	private static ServicioPersistencia servicioPersistencia;
	private SimpleDateFormat dateFormat;

	private static AdaptadorUsuarioTDS unicaInstancia;

	public static AdaptadorUsuarioTDS getUnicaInstancia() {
		if (unicaInstancia == null)
			return new AdaptadorUsuarioTDS();
		else
			return unicaInstancia;
	}

//	private AdaptadorUsuarioTDS() {
//		servicioPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
//		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//	}

	// TODO

	@Override
	public void registrarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub

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
