package umu.tds.persistencia;

import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.modelo.Cancion;
import umu.tds.modelo.PlayList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import beans.Entidad;
import beans.Propiedad;

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
		System.out.println("estamos en el adaptadorPlayList TDS");
		Entidad ePlayList = null;
		try {
			ePlayList = servPersistencia.recuperarEntidad(playlist.getCodigo());
		} catch (NullPointerException e) {
		}
		if (ePlayList != null)
			return;

		AdaptadorCancionTDS adaptadorCancion = AdaptadorCancionTDS.getUnicaInstancia();
		System.out.println("no hay canciones?" + playlist.getCanciones().size());

		for (Cancion cancion : playlist.getCanciones()) {
			System.out.println("cancion persistida" + cancion.getTitulo());
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void updatePlayList(PlayList playlist) {
		// TODO Auto-generated method stub

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
