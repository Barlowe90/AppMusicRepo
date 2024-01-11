package umu.tds.persistencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;
import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.modelo.Cancion;
import umu.tds.modelo.PlayList;

public class AdaptadorPlayListTDS implements IAdaptadorPlayListDAO {

	private static ServicioPersistencia servicioPersistencia;
	private static AdaptadorPlayListTDS unicaInstancia = null;
	Entidad ePlayList;

	private static final String PLAYLIST = "PlayList";
	private static final String CODIGO = "codigo";
	private static final String NOMBRE = "nombre";
	private static final String CANCIONES = "canciones";

	public static AdaptadorPlayListTDS getUnicaInstancia() {
		if (unicaInstancia == null) {
			unicaInstancia = new AdaptadorPlayListTDS();
		}
		return unicaInstancia;
	}

	private AdaptadorPlayListTDS() {
		servicioPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}

	private Entidad playListToEntidad(PlayList playlist) {
		ePlayList = new Entidad();
		ePlayList.setNombre(PLAYLIST);

		ePlayList.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(new Propiedad(NOMBRE, playlist.getNombre()),
				new Propiedad(CANCIONES, obtenerCodigosCanciones(playlist.getCanciones())))));

		return ePlayList;
	}

	private PlayList entidadToPlayList(Entidad ePlayList) {
		if (ePlayList == null) {
			throw new IllegalArgumentException("La entidad de la lista de reproducción es nula");
		}

		String nombre = servicioPersistencia.recuperarPropiedadEntidad(ePlayList, NOMBRE);

		PlayList playlist = new PlayList(nombre);
		playlist.setCodigo(ePlayList.getId());

		List<Cancion> canciones = obtenerCancionDesdeCodigo(
				servicioPersistencia.recuperarPropiedadEntidad(ePlayList, CANCIONES));

		for (Cancion cancion : canciones) {
			playlist.addCancion(cancion);
		}

		return playlist;
	}

	@Override
	public void registrarPlayList(PlayList playlist) {
		ePlayList = this.playListToEntidad(playlist);
		ePlayList = servicioPersistencia.registrarEntidad(ePlayList);
		playlist.setCodigo(ePlayList.getId());
	}

	@Override
	public boolean borrarPlayList(PlayList playlist) {
		Entidad ePlayList;
		AdaptadorCancionTDS adaptadorC = AdaptadorCancionTDS.getUnicaInstancia();

		for (Cancion c : playlist.getCanciones()) {
			if (adaptadorC.borrarCancion(c))
				System.out.println("fallo al eliminar c: " + c.getTitulo());
		}

		ePlayList = servicioPersistencia.recuperarEntidad(playlist.getCodigo());
		return servicioPersistencia.borrarEntidad(ePlayList);
	}

	@Override
	public void updatePlayList(PlayList playlist) {
		ePlayList = servicioPersistencia.recuperarEntidad(playlist.getCodigo());

		for (Propiedad prop : ePlayList.getPropiedades()) {
			if (prop.getNombre().equals(CODIGO)) {
				prop.setValor(String.valueOf(playlist.getCodigo()));
			} else if (prop.getNombre().equals(NOMBRE)) {
				prop.setValor(playlist.getNombre());
			} else if (prop.getNombre().equals(CANCIONES)) {
				String canciones = obtenerCodigosCanciones(playlist.getCanciones());
				prop.setValor(canciones);
			}
			servicioPersistencia.modificarPropiedad(prop);
		}
	}

	@Override
	public PlayList getPlayList(int id) {
		ePlayList = servicioPersistencia.recuperarEntidad(id);
		return entidadToPlayList(ePlayList);
	}

	@Override
	public List<PlayList> getAllPlayLists() {
		List<PlayList> playList = new LinkedList<PlayList>();
		List<Entidad> ePlayList = servicioPersistencia.recuperarEntidades(PLAYLIST);

		for (Entidad ePL : ePlayList) {
			playList.add(getPlayList(ePL.getId()));
		}

		return playList;
	}

	// Funciones auxiliares

	/**
	 * Recibe una lista de canciones y devuelve sus titulos en formato String
	 * 
	 * @param canciones
	 * @return String de canciones
	 */
	private String obtenerCodigosCanciones(List<Cancion> canciones) {
		String aux = "";
		for (Cancion c : canciones) {
			aux += c.getCodigo() + " ";
		}
		return aux.trim();
	}

	// Funciones auxiliares para getPlaylist

	private List<Cancion> obtenerCancionDesdeCodigo(String canciones) {
		List<Cancion> listaCanciones = new LinkedList<Cancion>();
		StringTokenizer strTok = new StringTokenizer(canciones, " ");
		AdaptadorCancionTDS adaptadorC = AdaptadorCancionTDS.getUnicaInstancia();
		while (strTok.hasMoreTokens()) {
			listaCanciones.add(adaptadorC.getCancion(Integer.valueOf((String) strTok.nextElement())));
		}
		return listaCanciones;
	}

}
