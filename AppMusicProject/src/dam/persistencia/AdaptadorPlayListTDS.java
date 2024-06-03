package dam.persistencia;

import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import beans.Entidad;
import beans.Propiedad;
import dam.modelo.Cancion;
import dam.modelo.PlayList;

public class AdaptadorPlayListTDS implements IAdaptadorPlayListDAO {

	private static ServicioPersistencia servPersistencia;
	private static AdaptadorPlayListTDS unicaInstancia = null;

	public static AdaptadorPlayListTDS getUnicaInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new AdaptadorPlayListTDS();
		return unicaInstancia;
	}

	private AdaptadorPlayListTDS() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}

	@Override
	public void registrarPlayList(PlayList playlist) {
		Entidad ePlayList = null;
		try {
			ePlayList = servPersistencia.recuperarEntidad(playlist.getCodigo());
		} catch (NullPointerException e) {
		}
		if (ePlayList != null)
			return;

		AdaptadorCancionTDS adaptadorCancion = AdaptadorCancionTDS.getUnicaInstancia();
		for (Cancion cancion : playlist.getCanciones()) {
			adaptadorCancion.registrarCancion(cancion);
		}

		ePlayList = new Entidad();
		ePlayList.setNombre("playlist");
		ePlayList.setPropiedades(
				new ArrayList<Propiedad>(Arrays.asList(new Propiedad("nombre", String.valueOf(playlist.getNombre())),
						new Propiedad("canciones", obtenerCodigosCancion(playlist.getCanciones())))));

		ePlayList = servPersistencia.registrarEntidad(ePlayList);
		playlist.setCodigo(ePlayList.getId());

	}

	@Override
	public boolean borrarPlayList(PlayList playlist) {
		Entidad ePlayList;
		AdaptadorCancionTDS adaptadorCancion = AdaptadorCancionTDS.getUnicaInstancia();
		for (Cancion cancion : playlist.getCanciones()) {
			adaptadorCancion.borrarCancion(cancion);
		}
		ePlayList = servPersistencia.recuperarEntidad(playlist.getCodigo());
		return servPersistencia.borrarEntidad(ePlayList);
	}

	@Override
	public void updatePlayList(PlayList playlist) {
		Entidad ePlayList = servPersistencia.recuperarEntidad(playlist.getCodigo());

		for (Propiedad prop : ePlayList.getPropiedades()) {
			if (prop.getNombre().equals("codigo")) {
				prop.setValor(String.valueOf(playlist.getCodigo()));
			} else if (prop.getNombre().equals("nombre")) {
				prop.setValor(String.valueOf(playlist.getNombre()));
			} else if (prop.getNombre().equals("canciones")) {
				String lineas = obtenerCodigosCancion(playlist.getCanciones());
				prop.setValor(lineas);
			}
			servPersistencia.modificarPropiedad(prop);
		}
	}

	@Override
	public PlayList getPlayList(int id) {
		Entidad ePlayList = servPersistencia.recuperarEntidad(id);

		String nombre = String.valueOf(servPersistencia.recuperarPropiedadEntidad(ePlayList, "nombre"));
		PlayList playlist = new PlayList(nombre);
		playlist.setCodigo(id);

		List<Cancion> canciones = obtenerCancionesDesdeCodigos(
				servPersistencia.recuperarPropiedadEntidad(ePlayList, "canciones"));

		for (Cancion c : canciones)
			playlist.addCancion(c);

		return playlist;
	}

	public List<PlayList> getAllPlayList() {
		List<PlayList> playlists = new LinkedList<PlayList>();
		List<Entidad> ePlayLists = servPersistencia.recuperarEntidades("playlist");

		for (Entidad ePlayList : ePlayLists) {
			playlists.add(getPlayList(ePlayList.getId()));
		}
		return playlists;
	}

	private String obtenerCodigosCancion(List<Cancion> playlist) {
		String lineas = "";
		for (Cancion c : playlist) {
			lineas += c.getCodigo() + " ";
		}
		return lineas.trim();
	}

	private List<Cancion> obtenerCancionesDesdeCodigos(String lineas) {
		List<Cancion> canciones = new LinkedList<Cancion>();
		StringTokenizer strTok = new StringTokenizer(lineas, " ");
		AdaptadorCancionTDS adaptadorC = AdaptadorCancionTDS.getUnicaInstancia();
		while (strTok.hasMoreTokens()) {
			canciones.add(adaptadorC.getCancion(Integer.valueOf((String) strTok.nextElement())));
		}
		return canciones;
	}

}
