package dam.controlador;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

import com.itextpdf.text.DocumentException;

import dam.componente.Canciones;
import dam.componente.CancionesEvent;
import dam.componente.CancionesListener;
import dam.componente.CargadorCanciones;
import dam.exceptions.EmailNoValidoException;
import dam.exceptions.UsuarioDuplicadoException;
import dam.modelo.Cancion;
import dam.modelo.CatalogoCanciones;
import dam.modelo.CatalogoUsuarios;
import dam.modelo.PlayList;
import dam.modelo.Usuario;
import dam.persistencia.AdaptadorPlayListTDS;
import dam.persistencia.DAOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

public class AppMusic implements CancionesListener {
	private static AppMusic unicaInstancia = null;

	private CatalogoUsuarios catalogoUsuarios;
	private CatalogoCanciones catalogoCanciones;

	private Reproductor reproductor;
	private CreadorPDF creadorPDF;

	private Usuario usuarioActual;

	private AdaptadorPlayListTDS catalogoPlayList;

	private final static String USER_DUPLICADO = "Usuario duplicado";
	private final static String WRONG_EMAIL = "El formato del email no es válido";

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
		catalogoPlayList = AdaptadorPlayListTDS.getUnicaInstancia();
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
			throws UsuarioDuplicadoException, EmailNoValidoException {
		if (isUsuarioRegistrado(nick)) {
			throw new UsuarioDuplicadoException(USER_DUPLICADO);
		}

		if (!isValidoEmail(email)) {
			throw new EmailNoValidoException(WRONG_EMAIL);
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

		List<Cancion> recientes = usuarioActual.getRecientes();

		if (!recientes.contains(cancion)) {
			recientes.add(cancion);
			usuarioActual.setRecientes(recientes);
			catalogoUsuarios.updateUsuario(usuarioActual);
		}
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
		} catch (FileNotFoundException | DocumentException e) {
			e.printStackTrace();
		}
	}

	public void altaUsuarioPremium() {
		usuarioActual.setPremium(true);
		catalogoUsuarios.updateUsuario(usuarioActual);
	}

	public boolean isUsuarioRegistrado(String nick) {
		return CatalogoUsuarios.getUnicaInstancia().getUsuario(nick) != null;
	}

	public boolean isValidoEmail(String email) {
		String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
		Pattern pattern = Pattern.compile(emailRegex);

		if (email == null) {
			return false;
		}

		Matcher matcher = pattern.matcher(email);

		return matcher.matches();
	}

	public List<Usuario> getUsuarios() throws DAOException {
		return catalogoUsuarios.getAllUsuarios();
	}

	public List<Cancion> getCanciones() throws DAOException {
		return catalogoCanciones.getAllCanciones();
	}

	public List<PlayList> getAllPlayList() {
		return new LinkedList<PlayList>(usuarioActual.getPlaylists());
	}

	public List<Cancion> getTopRecientes() throws DAOException {
		return getCanciones().stream().sorted(comparing(Cancion::getNumReproducciones).reversed()).limit(10)
				.collect(toList());
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
		usuarioActual.addPlayList(nombrePlaylist);
		catalogoUsuarios.updateUsuario(usuarioActual);
		PlayList playlistParaPersistir = getPlayListPorNommbre(nombrePlaylist);
		catalogoPlayList.registrarPlayList(playlistParaPersistir);
	}

	public void addCancionToPlayList(String nombrePlaylist, Cancion cancion) {
		usuarioActual.addCancionToPlayList(nombrePlaylist, cancion);
		PlayList p = usuarioActual.getPlayListPorNombre(nombrePlaylist);
		catalogoUsuarios.updateUsuario(usuarioActual);
		catalogoPlayList.updatePlayList(p);
	}

	public void addCancionToPlayList(PlayList nombrePlaylist, Cancion cancion) {
		usuarioActual.addCancionToPlayList(nombrePlaylist, cancion);
		catalogoUsuarios.updateUsuario(usuarioActual);
		catalogoPlayList.updatePlayList(nombrePlaylist);
	}

	public boolean borrarPlayListDelUsuario(String nombrePlaylist) {
		boolean eliminada = false;

		PlayList playlist = getPlayListPorNommbre(nombrePlaylist);
		if (usuarioActual.eliminarPlayList(playlist)) {
			eliminada = true;
			catalogoUsuarios.updateUsuario(usuarioActual);
		}
		return eliminada;
	}

	public PlayList getPlayListPorNommbre(String nombreplayList) {
		return usuarioActual.getPlaylists().stream().filter(pl -> pl.getNombre().equalsIgnoreCase(nombreplayList))
				.findFirst().orElse(null);
	}

	public boolean isPlayListCreada(String nombrePlaylist) {
		return usuarioActual.getPlaylists().stream().anyMatch(pl -> pl.getNombre().equals(nombrePlaylist));
	}

	public List<Cancion> getCancionesDePlaylist(String nombrePlaylist) {
		List<PlayList> playlistsUsuario = null;
		playlistsUsuario = getAllPlayList();
		return playlistsUsuario.stream().filter(pl -> pl.getNombre().equals(nombrePlaylist)).findFirst().orElse(null)
				.getCanciones();
	}

	public List<Cancion> getRecientes() {
		return catalogoUsuarios.getRecientes(usuarioActual);
	}

	@Override
	public void nuevasCancionesDisponibles(CancionesEvent event) {
		Canciones canciones = event.getCanciones();
		List<dam.componente.Cancion> listaCanciones = canciones.getCancion();
		for (dam.componente.Cancion cancion : listaCanciones) {
			registrarCancion(cancion.getTitulo(), cancion.getInterprete(), cancion.getEstilo(), cancion.getURL());
		}
	}

	public void eliminarCancion(PlayList nombrePlayList, Cancion cancion) {
		usuarioActual.eliminarCancioDePlayList(nombrePlayList, cancion);
		catalogoUsuarios.updateUsuario(usuarioActual);
		catalogoPlayList.updatePlayList(nombrePlayList);
	}

}
