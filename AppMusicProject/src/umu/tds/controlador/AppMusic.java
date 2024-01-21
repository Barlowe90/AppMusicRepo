package umu.tds.controlador;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

import com.itextpdf.text.DocumentException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

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
import umu.tds.persistencia.DAOException;

public class AppMusic implements CancionesListener {
	private static AppMusic unicaInstancia = null;

	private CatalogoUsuarios catalogoUsuarios;
	private CatalogoCanciones catalogoCanciones;

	private Reproductor reproductor;
	private CreadorPDF creadorPDF;

	private Usuario usuarioActual;

	private final static String USER_DUPLICADO = "Usuario duplicado";

	private AppMusic() {
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

	private void inicializarCatalogos() {
		catalogoUsuarios = CatalogoUsuarios.getUnicaInstancia();
		catalogoCanciones = CatalogoCanciones.getUnicaInstancia();
	}

	private void inicializarServicios() {
		reproductor = Reproductor.getUnicaInstancia();
		creadorPDF = CreadorPDF.getUnicaInstancia();
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
			catalogoCanciones.addCancion(cancion);
		}
	}

	public boolean borrarUsuario(String nick) {
		if (!isUsuarioRegistrado(nick)) {
			return false;
		}
		Usuario usuario = catalogoUsuarios.getUsuario(nick);
		catalogoUsuarios.removeUsuario(usuario);
		return true;
	}

	public void reproducirCancion(String url) {
		reproductor.playCancion(url);
		sumarNumReproducciones(url);
		addCancionToRecientes(url);
	}

	private void addCancionToRecientes(String url) {
		Cancion cancion = catalogoCanciones.getCancionPorURL(url);
		catalogoUsuarios.addCancionToRecientes(usuarioActual, cancion);
	}

	private void sumarNumReproducciones(String url) {
		try {
			Optional<Cancion> cancionOptional = getCanciones().stream().filter(c -> c.getURL().equals(url)).findFirst();
			if (cancionOptional.isPresent()) {
				Cancion cancion = cancionOptional.get();
				cancion.setNumReproducciones(cancion.getNumReproducciones() + 1);
				catalogoCanciones.updateCancion(cancion);
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

	public List<Cancion> getTopRecientes() throws DAOException {
		return catalogoCanciones.getAllCanciones().stream().sorted(comparing(Cancion::getNumReproducciones).reversed())
				.limit(10).collect(toList());
	}

	public List<String> getEstilos() throws DAOException {
		return catalogoCanciones.getAllCanciones().stream().map(Cancion::getEstilo).distinct()
				.collect(Collectors.toList());
	}

	public List<Cancion> buscarCancion(String titulo, String artista, String estilo) throws DAOException {
		return AppMusic.getUnicaInstancia().getCanciones().stream().filter(c -> estaIncluida(titulo, c.getTitulo()))
				.filter(c -> estaIncluida(artista, c.getInterprete())).filter(c -> estaIncluida(estilo, c.getEstilo()))
				.collect(Collectors.toList());
	}

	private boolean estaIncluida(String consulta, String valor) {
		if (consulta == null || consulta.isEmpty()) {
			return true;
		}

		String[] palabrasClave = consulta.split("\\s+");
		for (String palabra : palabrasClave) {
			if (valor.toLowerCase().contains(palabra.toLowerCase())) {
				return true;
			}
		}

		return false;
	}

	public void cargarCanciones(String xml) {
		CargadorCanciones.getUnicaInstancia().setArchivoCanciones(xml);
	}

	public Cancion getCancionPorTitulo(String titulo) {
		return catalogoCanciones.getCancionPorTitulo(titulo);
	}

	public JFileChooser obtenerFicheroToken() {
		JFileChooser selectorFichero = new JFileChooser();
		selectorFichero.addChoosableFileFilter(new FileFilter() {
			public String getDescription() {
				return "GitHub Properties File (*.properties)";
			}

			public boolean accept(File f) {
				if (f.isDirectory()) {
					return true;
				} else {
					return f.getName().toLowerCase().endsWith(".properties");
				}
			}
		});
		selectorFichero.setAcceptAllFileFilterUsed(false);
		File directorioTrabajo = new File(System.getProperty("user.dir"));
		selectorFichero.setCurrentDirectory(directorioTrabajo);
		return selectorFichero;
	}

	public void registrarPlayList(String nombrePlaylist) {
		if (!isPlayListCreada(nombrePlaylist)) {
			PlayList playlist = new PlayList(nombrePlaylist);
			catalogoUsuarios.addPlayListToUsuario(usuarioActual, playlist);
		}
	}

	public void addCancionToPlayList(Cancion cancion, PlayList playList) {
		catalogoUsuarios.addCancionToPlayList(usuarioActual, playList, cancion);
		catalogoUsuarios.updateUsuario(usuarioActual);
	}

	public boolean isPlayListCreada(String nombrePlaylist) {
		return usuarioActual.getPlaylists().stream().anyMatch(pl -> pl.getNombre().equals(nombrePlaylist));
	}

	public boolean borrarPlayListDelUsuario(String nombrePlaylist) {
		catalogoUsuarios.getAllPlayList(usuarioActual).stream().forEach(pl -> System.out.println(pl.getNombre()));
		return catalogoUsuarios.eliminarPlayList(usuarioActual, nombrePlaylist);
	}

	public List<PlayList> getAllPlayListPorUsuario() {
		return usuarioActual.getPlaylists();
	}

	public List<Cancion> getCancionesDePlaylist(String nombrePlaylist) {
		List<PlayList> playlistsUsuario = catalogoUsuarios.getAllPlayList(usuarioActual);

		return playlistsUsuario.stream().filter(pl -> pl.getNombre().equals(nombrePlaylist)).findFirst().orElse(null)
				.getCanciones();
	}

	public List<Cancion> getRecientes() {
		return catalogoUsuarios.getRecientes(usuarioActual);
	}

	@Override
	public void nuevasCancionesDisponibles(CancionesEvent event) {
		for (CancionComponente cancion : event.getCanciones().getCancion()) {
			registrarCancion(cancion.getTitulo(), cancion.getInterprete(), cancion.getEstilo(), cancion.getURL());
		}
	}
}
