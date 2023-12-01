package umu.tds.persistencia;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import beans.Entidad;
import beans.Propiedad;
import umu.tds.modelo.Cancion;
import umu.tds.modelo.PlayList;
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

		eUsuario.setNombre("venta");
		eUsuario.setPropiedades(
				new ArrayList<Propiedad>(Arrays.asList(new Propiedad("usuario", String.valueOf(usuario.getUsuario())),
						new Propiedad("email", String.valueOf(usuario.getEmail())),
						new Propiedad("fechaNacimiento", dateFormat.format(usuario.getFechaNacimiento())),
						new Propiedad("esPremium", String.valueOf(usuario.isPremium())),
						new Propiedad("password", String.valueOf(usuario.getPassword())))));

		eUsuario = servicioPersistencia.registrarEntidad(eUsuario);

		usuario.setId(eUsuario.getId());
	}

	@Override
	public void borrarUsuario(Usuario usuario) {
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
		servicioPersistencia.borrarEntidad(eUsuario);
	}

	@Override
	public void modificarUsuario(Usuario usuario) {
		Entidad eUsuario = servicioPersistencia.recuperarEntidad(usuario.getId());

		for (Propiedad prop : eUsuario.getPropiedades()) {
			if (prop.getNombre().equals("id")) {
				prop.setValor(String.valueOf(usuario.getId()));
			} else if (prop.getNombre().equals("usuario")) {
				prop.setValor(String.valueOf(usuario.getUsuario()));
			} else if (prop.getNombre().equals("password")) {
				prop.setValor(dateFormat.format(usuario.getPassword()));
			} else if (prop.getNombre().equals("email")) {
				prop.setValor(dateFormat.format(usuario.getEmail()));
			} else if (prop.getNombre().equals("premium")) {
				prop.setValor(dateFormat.format(usuario.isPremium()));
			} else if (prop.getNombre().equals("fechaNacimiento")) {
				prop.setValor(dateFormat.format(usuario.getFechaNacimiento()));
			} else if (prop.getNombre().equals("descuentoAplicado")) {
				prop.setValor(dateFormat.format(usuario.getDescuentoAplicado()));
			} else if (prop.getNombre().equals("playLists")) {
				String playlist = obtenerPlayList(usuario.getPlaylists());
				prop.setValor(playlist);
			} else if (prop.getNombre().equals("recientes")) {
				String recientes = obtenerRecientes(usuario.getRecientes());
				prop.setValor(recientes);
			}
			servicioPersistencia.modificarPropiedad(prop);
		}

	}

	@Override
	public Usuario recuperaUsuario(int key) {
		Entidad eUsuario;
		List<Venta> ventas = new LinkedList<Venta>();
		String dni;
		String nombre;

		// recuperar entidad
		eCliente = servPersistencia.recuperarEntidad(codigo);

		// recuperar propiedades que no son objetos
		dni = servPersistencia.recuperarPropiedadEntidad(eCliente, "dni");
		nombre = servPersistencia.recuperarPropiedadEntidad(eCliente, "nombre");

		Cliente cliente = new Cliente(dni, nombre);
		cliente.setCodigo(codigo);

		// IMPORTANTE:a�adir el cliente al pool antes de llamar a otros
		// adaptadores
		PoolDAO.getUnicaInstancia().addObjeto(codigo, cliente);

		// recuperar propiedades que son objetos llamando a adaptadores
		// ventas
		ventas = obtenerVentasDesdeCodigos(servPersistencia.recuperarPropiedadEntidad(eCliente, "ventas"));

		for (Venta v : ventas)
			cliente.addVenta(v);

		return cliente;
	}

	@Override
	public List<Usuario> recuperarTodosUsuarios() {
		// TODO Auto-generated method stub
		return null;
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
