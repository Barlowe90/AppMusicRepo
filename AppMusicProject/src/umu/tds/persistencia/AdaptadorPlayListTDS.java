package umu.tds.persistencia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import beans.Entidad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.modelo.Cancion;
import umu.tds.modelo.PlayList;

public class AdaptadorPlayListTDS implements IAdaptadorPlayListDAO {

	private static ServicioPersistencia servicioPersistencia;
	private static AdaptadorPlayListTDS unicaInstancia = null;

	private static final String NOMBRE = "nombre";

	public static AdaptadorPlayListTDS getUnicaInstancia() {
		if (unicaInstancia == null) {
			unicaInstancia = new AdaptadorPlayListTDS();
		}
		return unicaInstancia;
	}

	private AdaptadorPlayListTDS() {
		servicioPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}

	private PlayList entidadToPlayList(Entidad ePlayList) {
		String nombre = servicioPersistencia.recuperarPropiedadEntidad(ePlayList, NOMBRE);
		List<Cancion> lista = new LinkedList<Cancion>();

		PlayList playlist = new PlayList(nombre, lista);
		return playlist;
	}

	private Entidad playlistToEntidad(PlayList playlist) {
		Entidad ePlayList = new Entidad();
		ePlayList.setNombre(NOMBRE);
		// TODO como tratar la lista

		return ePlayList;
	}

	@Override
	public void registrarPlayList(PlayList playlist) {
		Entidad ePlayList = this.playlistToEntidad(playlist);
		ePlayList = servicioPersistencia.registrarEntidad(ePlayList);
	}

	@Override
	public boolean borrarPlayList(PlayList playlist) {
		ArrayList<Entidad> ePlayList = servicioPersistencia.recuperarEntidades(playlist.getNombre());
		return servicioPersistencia.borrarEntidad(ePlayList.get(0));
	}

	@Override
	public void updatePlayList(PlayList playlist) {
		ArrayList<Entidad> ePlayList = servicioPersistencia.recuperarEntidades(playlist.getNombre());
		Entidad entidad = ePlayList.get(0);
		// TODO
	}

	@Override
	public PlayList getPlayList(int id) {
		// TODO
		return null;
	}

	@Override
	public List<PlayList> getAllPlayLists() {
		// TODO
		return null;
	}

	// Funcion auxiliar

	private List<Cancion> obtenerCancionesDesdeNombrePlayList(String nombre) {
		// TODO obtener la lista de canciones
		return null;
	}

}
