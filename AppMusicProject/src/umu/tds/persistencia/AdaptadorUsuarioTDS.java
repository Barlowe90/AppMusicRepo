package umu.tds.persistencia;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import beans.Entidad;
import beans.Propiedad;
import umu.tds.modelo.Cancion;
import umu.tds.modelo.Descuento;
import umu.tds.modelo.FactoriaDescuento;
import umu.tds.modelo.PlayList;
import umu.tds.modelo.Usuario;

/**
 * Clase que implementa el Adaptador DAO concreto de Usuaruio para la DB H2
 */

public class AdaptadorUsuarioTDS implements IAdaptadorUsuarioDAO {

	private static ServicioPersistencia servicioPersistencia;
	private static AdaptadorUsuarioTDS unicaInstancia = null;
	private SimpleDateFormat dateFormat;
	private DateTimeFormatter formatoFecha;
	Entidad eUsuario;

	private static final String USUARIO = "usuario";
	private static final String NICK = "nick";
	private static final String PASSWORD = "password";
	private static final String EMAIL = "email";
	private static final String PREMIUM = "premium";
	private static final String FECHA_NACIMIENTO = "fechaNacimiento";
	private static final String DESCUENTO_APLICADO = "descuentoAplicado";
	private static final String PLAYLISTS = "playLists";
	private static final String RECIENTES = "recientes";

	public static AdaptadorUsuarioTDS getUnicaInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new AdaptadorUsuarioTDS();
		return unicaInstancia;
	}

	private AdaptadorUsuarioTDS() {
		servicioPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	}

	private Usuario entidadToUsuario(Entidad eUsuario) {
		String nick = servicioPersistencia.recuperarPropiedadEntidad(eUsuario, NICK);
		String pw = servicioPersistencia.recuperarPropiedadEntidad(eUsuario, PASSWORD);
		String email = servicioPersistencia.recuperarPropiedadEntidad(eUsuario, EMAIL);
		boolean premium = Boolean.parseBoolean(servicioPersistencia.recuperarPropiedadEntidad(eUsuario, PREMIUM));

		LocalDate fechaNacimiento = LocalDate
				.parse(servicioPersistencia.recuperarPropiedadEntidad(eUsuario, FECHA_NACIMIENTO), formatoFecha);
		String codigoDescuento = servicioPersistencia.recuperarPropiedadEntidad(eUsuario, DESCUENTO_APLICADO);
		Descuento descuentoAplicado = FactoriaDescuento.obtenerDescuento(codigoDescuento);
		List<PlayList> playlist = new LinkedList<PlayList>();
		List<Cancion> recientes = new LinkedList<Cancion>();

		Usuario usuario = new Usuario(nick, pw, email, premium, fechaNacimiento, descuentoAplicado);
		usuario.setId(eUsuario.getId());

		playlist = obtenerPlayListDesdeCodigo(servicioPersistencia.recuperarPropiedadEntidad(eUsuario, PLAYLISTS));
		for (PlayList pl : playlist) {
			List<Cancion> canciones = pl.getCanciones();
			usuario.addPlayList(pl.getNombre(), canciones.toArray(new Cancion[0]));
		}

		recientes = obtenerRecientesDesdeCodigo(servicioPersistencia.recuperarPropiedadEntidad(eUsuario, RECIENTES));
		for (Cancion c : recientes)
			usuario.addToRecientes(c);

		return usuario;
	}

	private Entidad usuarioToEntidad(Usuario usuario) {
		eUsuario = new Entidad();
		eUsuario.setNombre(USUARIO);

		String fechaNacimiento = usuario.getFechaNacimiento().format(formatoFecha);

		eUsuario.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(new Propiedad(NICK, usuario.getNick()),
				new Propiedad(PASSWORD, usuario.getPassword()), new Propiedad(EMAIL, usuario.getEmail()),
				new Propiedad(PREMIUM, String.valueOf(usuario.isPremium())),
				new Propiedad(FECHA_NACIMIENTO, fechaNacimiento),
				new Propiedad(DESCUENTO_APLICADO, usuario.getDescuentoAplicado().getClass().getSimpleName()),
				new Propiedad(PLAYLISTS, obtenerPlayList(usuario.getPlaylists())),
				new Propiedad(RECIENTES, obtenerRecientes(usuario.getRecientes())))));

		return eUsuario;
	}

	@Override
	public void registrarUsuario(Usuario usuario) {
		try {
			eUsuario = servicioPersistencia.recuperarEntidad(usuario.getId());
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

		if (eUsuario != null)
			return;

		eUsuario = this.usuarioToEntidad(usuario);
		eUsuario = servicioPersistencia.registrarEntidad(eUsuario);
		usuario.setId(eUsuario.getId());
	}

	@Override
	public boolean borrarUsuario(Usuario usuario) {
		AdaptadorCancionTDS adaptadorC = AdaptadorCancionTDS.getUnicaInstancia();
		AdaptadorPlayListTDS adaptadorPL = AdaptadorPlayListTDS.getUnicaInstancia();

		for (Cancion cancion : usuario.getRecientes()) {
			adaptadorC.borrarCancion(cancion);
		}
		for (PlayList pl : usuario.getPlaylists()) {
			adaptadorPL.borrarPlayList(pl);
		}

		eUsuario = servicioPersistencia.recuperarEntidad(usuario.getId());
		return servicioPersistencia.borrarEntidad(eUsuario);
	}

	@Override
	public void updateUsuario(Usuario usuario) {
		eUsuario = servicioPersistencia.recuperarEntidad(usuario.getId());

		for (Propiedad prop : eUsuario.getPropiedades()) {
			if (prop.getNombre().equals(NICK)) {
				prop.setValor(String.valueOf(usuario.getNick()));
			} else if (prop.getNombre().equals(PASSWORD)) {
				prop.setValor(usuario.getPassword());
			} else if (prop.getNombre().equals(EMAIL)) {
				prop.setValor(usuario.getEmail());
			} else if (prop.getNombre().equals(PREMIUM)) {
				prop.setValor(String.valueOf(usuario.isPremium()));
			} else if (prop.getNombre().equals(FECHA_NACIMIENTO)) {
				prop.setValor(dateFormat.format(usuario.getFechaNacimiento()));
			} else if (prop.getNombre().equals(DESCUENTO_APLICADO)) {
				prop.setValor(String.valueOf(usuario.getDescuentoAplicado()));
			} else if (prop.getNombre().equals(PLAYLISTS)) {
				String playlist = obtenerPlayList(usuario.getPlaylists());
				prop.setValor(playlist);
			} else if (prop.getNombre().equals(RECIENTES)) {
				String recientes = obtenerRecientes(usuario.getRecientes());
				prop.setValor(recientes);
			}
			servicioPersistencia.modificarPropiedad(prop);
		}
	}

	@Override
	public Usuario getUsuario(int key) {
		eUsuario = servicioPersistencia.recuperarEntidad(key);
		return entidadToUsuario(eUsuario);
	}

	@Override
	public List<Usuario> getAllUsuarios() {
		List<Entidad> eUsuarios = servicioPersistencia.recuperarEntidades(USUARIO);
		List<Usuario> usuarios = new LinkedList<Usuario>();

		for (Entidad eUsuario : eUsuarios) {
			usuarios.add(getUsuario(eUsuario.getId()));
		}

		return usuarios;
	}

	// Funciones auxiliares para usuarioToEntiedad

	/**
	 * Recibe una lista de playlist y devuelve sus playlist en formato String
	 * 
	 * @param listaPlayList
	 * @return String de playlist
	 */
	private String obtenerPlayList(List<PlayList> listaPlayList) {
		String aux = "";
		for (PlayList pl : listaPlayList) {
			aux += pl.getNombre() + " ";
		}
		return aux.trim();
	}

	/**
	 * Recibe una lista de recientes y las devuelve en formato String
	 * 
	 * @param listaCancion
	 * @return String de canciones recientes
	 */
	private String obtenerRecientes(List<Cancion> listaCancion) {
		String aux = "";
		for (Cancion c : listaCancion) {
			aux += c.getCodigo() + " ";
		}
		return aux.trim();
	}

	// Funciones auxiliares para getUsuario

	private List<PlayList> obtenerPlayListDesdeCodigo(String playlist) {
		List<PlayList> listaPlaylist = new LinkedList<PlayList>();
		StringTokenizer strTok = new StringTokenizer(playlist, " ");
		AdaptadorPlayListTDS adaptadorPL = AdaptadorPlayListTDS.getUnicaInstancia();
		while (strTok.hasMoreTokens()) {
			listaPlaylist.add(adaptadorPL.getPlayList(Integer.valueOf((String) strTok.nextElement())));
		}
		return listaPlaylist;
	}

	private List<Cancion> obtenerRecientesDesdeCodigo(String recientes) {
		List<Cancion> listaRecientes = new LinkedList<Cancion>();
		StringTokenizer strTok = new StringTokenizer(recientes, " ");
		AdaptadorCancionTDS adaptadorC = AdaptadorCancionTDS.getUnicaInstancia();
		while (strTok.hasMoreTokens()) {
			listaRecientes.add(adaptadorC.getCancion(Integer.valueOf((String) strTok.nextElement())));
		}
		return listaRecientes;
	}

}
