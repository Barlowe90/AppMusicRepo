package umu.tds.controlador;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.itextpdf.text.DocumentException;

import umu.tds.componente.CancionComponente;
import umu.tds.componente.CancionesEvent;
import umu.tds.componente.CancionesListener;
import umu.tds.componente.CargadorCanciones;
import umu.tds.exceptions.UsuarioDuplicadoException;
import umu.tds.modelo.Cancion;
import umu.tds.modelo.CatalogoCanciones;
import umu.tds.modelo.CatalogoUsuarios;
import umu.tds.modelo.CreadorPDF;
import umu.tds.modelo.PlayList;
import umu.tds.modelo.Reproductor;
import umu.tds.modelo.Usuario;
import umu.tds.persistencia.IAdaptadorCancionDAO;
import umu.tds.persistencia.IAdaptadorUsuarioDAO;
import umu.tds.persistencia.DAOException;
import umu.tds.persistencia.FactoriaDAO;

public class AppMusic implements CancionesListener {
	private static AppMusic unicaInstancia = null;

	private IAdaptadorUsuarioDAO adaptadorUsuario;
	private IAdaptadorCancionDAO adaptadorCancion;

	private CatalogoUsuarios catalogoUsuarios;
	private CatalogoCanciones catalogoCanciones;

	private Reproductor reproductor;
	private CreadorPDF creadorPDF;

	private Usuario usuarioActual;

	private final static String USER_DUPLICADO = "Usuario duplicado";

	private AppMusic() {
		inicializarAdaptadores();
		inicializarCatalogos();
		inicializarServicios();
		CargadorCanciones.getUnicaInstancia().agregarOyente(this);
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

	public void registrarUsuario(String nick, String password, String email, LocalDate fechaNacimiento)
			throws UsuarioDuplicadoException {
		if (isUsuarioRegistrado(nick)) {
			throw new UsuarioDuplicadoException(USER_DUPLICADO);
		}

		Usuario usuario = new Usuario(nick, password, email, fechaNacimiento);
		adaptadorUsuario.registrarUsuario(usuario);
		catalogoUsuarios.addUsuario(usuario);
	}

	public void registrarCancion(String titulo, String interprete, String estiloMusical, String rutaCancion) {
		boolean existeCancion = false;
		List<Cancion> canciones;
		try {
			canciones = catalogoCanciones.getAllCanciones();
			existeCancion = canciones.stream().anyMatch(c -> c.getTitulo().equals(titulo));
		} catch (DAOException e) {
			e.printStackTrace();
		}

		if (!existeCancion) {
			Cancion cancion = new Cancion(titulo, interprete, estiloMusical, rutaCancion);
			adaptadorCancion.registrarCancion(cancion);
			catalogoCanciones.addCancion(cancion);
		}
	}

	public boolean borrarUsuario(String nick) {
		if (!isUsuarioRegistrado(nick)) {
			return false;
		}
		Usuario usuario = catalogoUsuarios.getUsuario(nick);
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

	public void reproducirCancion(String url) {
		reproductor.playCancion(url);
		sumarNumReproducciones(url);
	}

	private void sumarNumReproducciones(String url) {
		try {
			Optional<Cancion> cancionOptional = getCanciones().stream().filter(c -> c.getURL().equals(url)).findFirst();
			if (cancionOptional.isPresent()) {
				Cancion cancion = cancionOptional.get();
				cancion.setNumReproducciones(cancion.getNumReproducciones() + 1);
			}
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	public void stopCancion() {
		reproductor.stopCancion();
	}

	public void pausarCancion() {
		reproductor.pauseCancion();
	}

	public void stopAllCanciones() {
		reproductor.stopAllCanciones();
	}

	public void crearPDF() {
		try {
			creadorPDF.crearPDF(usuarioActual, getAllPlayList());
		} catch (FileNotFoundException | DocumentException | DAOException e) {
			e.printStackTrace();
		}
	}

	public void altaUsuarioPremium() {
		catalogoUsuarios.altaPremium(usuarioActual);
		adaptadorUsuario.updateUsuario(usuarioActual);
	}

	public boolean isUsuarioRegistrado(String nick) {
		return CatalogoUsuarios.getUnicaInstancia().getUsuario(nick) != null;
	}

	public List<Usuario> getUsuarios() throws DAOException {
		return catalogoUsuarios.getAllUsuarios();
	}

	public List<Cancion> getCanciones() throws DAOException {
		return catalogoCanciones.getAllCanciones();
	}

	public List<PlayList> getAllPlayList() throws DAOException {
		return catalogoUsuarios.getAllPlayList(usuarioActual);
	}

	public void cargarCanciones(String xml) {
		CargadorCanciones.getUnicaInstancia().setArchivoCanciones(xml);
	}

	@Override
	public void nuevasCancionesDisponibles(CancionesEvent event) {
		for (CancionComponente cancion : event.getCanciones().getCancion()) {
			registrarCancion(cancion.getTitulo(), cancion.getInterprete(), cancion.getEstilo(), cancion.getURL());
		}
	}
}
