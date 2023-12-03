package umu.tds.persistencia;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import beans.Entidad;
import beans.Propiedad;
import umu.tds.modelo.Cancion;
import umu.tds.modelo.Descuento;
import umu.tds.modelo.PlayList;
import umu.tds.modelo.Usuario;

/**
 * Clase que implementa el Adaptador DAO concreto de Usuaruio para la DB H2
 */

public class AdaptadorUsuarioTDS implements IAdaptadorUsuarioDAO {

	private static ServicioPersistencia servicioPersistencia;
	private static AdaptadorUsuarioTDS unicaInstancia = null;
	private SimpleDateFormat dateFormat;

	private static final String USUARIO = "usuario";
//	private static final String NOMBRE = "nombre";	// En caso de añadir estas propiedades
//	private static final String APELLIDOS = "apellidos";	// En caso de añadir estas propiedades
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
	}

	@Override
	public void registrarUsuario(Usuario usuario) {
		Entidad eUsuario = null;

		try {
			eUsuario = servicioPersistencia.recuperarEntidad(usuario.getId());
		} catch (NullPointerException e) {
		}
		if (eUsuario != null)
			return;

		AdaptadorCancionTDS adaptadorC = AdaptadorCancionTDS.getUnicaInstancia();
		for (Cancion cancion : usuario.getRecientes())
			adaptadorC.registrarCancion(cancion);

		AdaptadorPlayListTDS adaptadorPL = AdaptadorPlayListTDS.getUnicaInstancia();
		for (PlayList pl : usuario.getPlaylists())
			adaptadorPL.registrarPlayList(pl);

		eUsuario = new Entidad();

		eUsuario.setNombre(USUARIO);
		eUsuario.setPropiedades(
				new ArrayList<Propiedad>(Arrays.asList(new Propiedad(NICK, String.valueOf(usuario.getNick())),
						new Propiedad(PASSWORD, String.valueOf(usuario.getPassword())),
						new Propiedad(EMAIL, String.valueOf(usuario.getEmail())),
						new Propiedad(FECHA_NACIMIENTO, dateFormat.format(usuario.getFechaNacimiento())),
						new Propiedad(PREMIUM, String.valueOf(usuario.isPremium())),
						new Propiedad(DESCUENTO_APLICADO, String.valueOf(usuario.getDescuentoAplicado())),
						new Propiedad(PLAYLISTS, String.valueOf(usuario.getPlaylists())),
						new Propiedad(RECIENTES, String.valueOf(usuario.getRecientes())))));

		eUsuario = servicioPersistencia.registrarEntidad(eUsuario);
		usuario.setId(eUsuario.getId());
	}

	@Override
	public boolean borrarUsuario(Usuario usuario) {
		Entidad eUsuario;
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
		Entidad eUsuario = servicioPersistencia.recuperarEntidad(usuario.getId());

		for (Propiedad prop : eUsuario.getPropiedades()) {
			if (prop.getNombre().equals(NICK)) {
				prop.setValor(String.valueOf(usuario.getNick()));
			} else if (prop.getNombre().equals(PASSWORD)) {
				prop.setValor(usuario.getPassword());
			} else if (prop.getNombre().equals(EMAIL)) {
				prop.setValor(usuario.getEmail());
			} else if (prop.getNombre().equals(PREMIUM)) {
				prop.setValor(String.valueOf(usuario.isPremium())); // CHECK
			} else if (prop.getNombre().equals(FECHA_NACIMIENTO)) {
				prop.setValor(dateFormat.format(usuario.getFechaNacimiento()));
			} else if (prop.getNombre().equals(DESCUENTO_APLICADO)) {
				prop.setValor(String.valueOf(usuario.getDescuentoAplicado())); // CHECK
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
		Entidad eUsuario;
		eUsuario = servicioPersistencia.recuperarEntidad(key);

		String nick = servicioPersistencia.recuperarPropiedadEntidad(eUsuario, NICK);
		String password = servicioPersistencia.recuperarPropiedadEntidad(eUsuario, PASSWORD);
		String email = servicioPersistencia.recuperarPropiedadEntidad(eUsuario, EMAIL);
		// TODO solucionar errores
		boolean premium = servicioPersistencia.recuperarPropiedadEntidad(eUsuario, PREMIUM);
		LocalDate fechaNacimiento = servicioPersistencia.recuperarPropiedadEntidad(eUsuario, FECHA_NACIMIENTO);
		Descuento descuentoAplicado = servicioPersistencia.recuperarPropiedadEntidad(eUsuario, DESCUENTO_APLICADO);
		List<PlayList> playlist = new LinkedList<PlayList>();
		List<Cancion> recientes = new LinkedList<Cancion>();

		Usuario user = new Usuario(nick, password, email, fechaNacimiento);
		user.setId(key);

		// recuperar propiedades que son objetos llamando a adaptadores
		playlist = obtenerPlayList(servicioPersistencia.recuperarPropiedadEntidad(eUsuario, PLAYLISTS));
		for (PlayList pl : playlist)
			user.addPlayList(pl);

		recientes = obtenerRecientes(servicioPersistencia.recuperarPropiedadEntidad(eUsuario, RECIENTES));
		for (Cancion c : recientes)
			user.addPlayList(c);

		return user;
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

	private String obtenerPlayList(List<PlayList> listaPlayList) {
		String aux = "";
		for (PlayList pl : listaPlayList) {
			aux += pl.getNombre() + " ";
		}
		return aux.trim();
	}

	private String obtenerRecientes(List<Cancion> listaCancion) {
		String aux = "";
		for (Cancion c : listaCancion) {
			aux += c.getCodigo() + " ";
		}
		return aux.trim();
	}
}
