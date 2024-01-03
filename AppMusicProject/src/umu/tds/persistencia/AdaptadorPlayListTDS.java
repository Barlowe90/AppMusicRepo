package umu.tds.persistencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.modelo.Cancion;
import umu.tds.modelo.PlayList;

public class AdaptadorPlayListTDS implements IAdaptadorPlayListDAO {

	private static ServicioPersistencia servicioPersistencia;
	private static AdaptadorPlayListTDS unicaInstancia = null;

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

	@Override
	public void registrarPlayList(PlayList playlist) {
		// TODO ¿necesita codigo como LineaVenta?
//		try {
//			ePlayList = servicioPersistencia.recuperarEntidad(playlist.getNombre());
//		} catch (NullPointerException e) {}
//		if(ePlayList != null) return;
		
		AdaptadorPlayListTDS adaptadorPlaylist = AdaptadorPlayListTDS.getUnicaInstancia();
		adaptadorPlaylist.registrarPlayList(playlist);
		
		// Creamos entidad
		Entidad ePlaylist = new Entidad();
		ePlaylist.setNombre("playlist");
		ePlaylist.setPropiedades(new ArrayList<Propiedad>(
				Arrays.asList(new Propiedad(NOMBRE, playlist.getNombre()),
						new Propiedad(CANCIONES, String.valueOf(playlist.getCanciones())))));
		
		ePlaylist = servicioPersistencia.registrarEntidad(ePlaylist);		
	}

	@Override
	public boolean borrarPlayList(PlayList playlist) {
		ArrayList<Entidad> ePlayList = servicioPersistencia.recuperarEntidades(playlist.getNombre());
		return servicioPersistencia.borrarEntidad(ePlayList.get(0));
	}

	@Override
	public void updatePlayList(PlayList playlist) {
		// TODO necesitaríamos código para que funcione?
		Entidad ePlayList = servicioPersistencia.recuperarEntidad(playlist.getNombre());
		
		for (Propiedad prop : ePlayList.getPropiedades()) {
			if(prop.getNombre().equals(NOMBRE)) {
				prop.setValor(playlist.getNombre());
			}
			else if (prop.getNombre().equals(CANCIONES)) {
				prop.setValor(String.valueOf(playlist.getCanciones()));
			}
			servicioPersistencia.modificarPropiedad(prop);
		}
	}

	@Override
	public PlayList getPlayList(int id) {
		Entidad ePlayList;
		String nombre;
		// TODO como tratar lista?
		List<Cancion> canciones;
		
		ePlayList = servicioPersistencia.recuperarEntidad(id);
		nombre = servicioPersistencia.recuperarPropiedadEntidad(ePlayList, NOMBRE);
		canciones = servicioPersistencia.recuperarPropiedadEntidad(ePlayList, CANCIONES);
		
		
		PlayList playlist = new PlayList(nombre, canciones);
		//playlist.setId(id);
		return playlist;
	}

	@Override
	public List<PlayList> getAllPlayLists() {
		// TODO
		return null;
	}


}
