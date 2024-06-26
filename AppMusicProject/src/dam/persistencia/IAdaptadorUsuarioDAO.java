package dam.persistencia;

import java.util.List;

import dam.modelo.Usuario;

public interface IAdaptadorUsuarioDAO {
	public void registrarUsuario(Usuario usuario);

	public boolean borrarUsuario(Usuario usuario);

	public void updateUsuario(Usuario usuario);

	public Usuario getUsuario(int id);

	public List<Usuario> getAllUsuarios();
}
