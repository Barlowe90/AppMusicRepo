package umu.tds.controlador;

import java.time.LocalDate;
import java.util.List;
import umu.tds.modelo.Cancion;
import umu.tds.modelo.CatalogoCanciones;
import umu.tds.modelo.CatalogoUsuarios;
import umu.tds.modelo.CreadorPDF;
import umu.tds.modelo.Reproductor;
import umu.tds.modelo.Usuario;
import umu.tds.persistencia.IAdaptadorCancionDAO;
import umu.tds.persistencia.IAdaptadorUsuarioDAO;
import umu.tds.persistencia.DAOException;
import umu.tds.persistencia.FactoriaDAO;

public class AppMusic {
	private static AppMusic unicaInstancia = null;

	private IAdaptadorUsuarioDAO adaptadorUsuario;
	private IAdaptadorCancionDAO adaptadorCancion;

	private CatalogoUsuarios catalogoUsuarios;
	private CatalogoCanciones catalogoCanciones;

	private Reproductor reproductor;
	private CreadorPDF creadorPDF;

	private Usuario usuarioActual;

	private AppMusic() {
		inicializarAdaptadores();
		inicializarCatalogos();
		inicializarServicios();
		usuarioActual = null;
	}

	public static AppMusic getUnicaInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new AppMusic();
		return unicaInstancia;
	}

	public Usuario getUsuarioActual() {
		return usuarioActual;
	}

	public boolean loginUsuario(String nick, String password) {
		Usuario usuario = catalogoUsuarios.getUsuario(nick);
		if (usuario != null && usuario.getPassword().equals(password)) {
			this.usuarioActual = usuario;
			return true;
		}
		return false;
	}

	public boolean registrarUsuario(String nick, String password, String email, LocalDate fechaNacimiento) {
		Usuario usuario = new Usuario(nick, password, email, fechaNacimiento);
		adaptadorUsuario.registrarUsuario(usuario);
		catalogoUsuarios.addUsuario(usuario);

		return true;
	}

	public void registrarCancion(String titulo, String interprete, String estiloMusical, String rutaCancion) {
		Cancion cancion = new Cancion(titulo, interprete, estiloMusical, rutaCancion);
		adaptadorCancion.registrarCancion(cancion);
		catalogoCanciones.addCancion(cancion);
	}

	public boolean borrarUsuario(Usuario usuario) {
		if (!isUsuarioRegistrado(usuario.getNick()))
			return false;

		adaptadorUsuario.borrarUsuario(usuario);
		catalogoUsuarios.removeUsuario(usuario);
		return true;
	}

	private void inicializarAdaptadores() {
		FactoriaDAO factoria = null;
		try {
			factoria = FactoriaDAO.getInstancia(FactoriaDAO.DAO_TDS);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		adaptadorUsuario = factoria.getUsuarioDAO();
		adaptadorCancion = factoria.getCancionDAO();
	}

	private void inicializarCatalogos() {
		catalogoUsuarios = CatalogoUsuarios.getUnicaInstancia();
		catalogoCanciones = CatalogoCanciones.getUnicaInstancia();
	}

	private void inicializarServicios() {
		reproductor = Reproductor.getUnicaInstancia();
		creadorPDF = CreadorPDF.getUnicaInstancia();
	}

	// TODO funcion play
//	public void reproducirCancion(String url) {
// 		reproductor.play(url);
//	}

	// TODO funcion crearPDF
//	public void crearPDF() {
//		creadorPDF.crearPDF();
//	}

	public boolean isUsuarioRegistrado(String nick) {
		return CatalogoUsuarios.getUnicaInstancia().getUsuario(nick) != null;
	}

	public List<Usuario> getUsuarios() throws DAOException {
		return catalogoUsuarios.getAllUsuarios();
	}

	public List<Cancion> getCanciones() throws DAOException {
		return catalogoCanciones.getAllCanciones();
	}
}
