package umu.tds.persistencia;

import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.modelo.Cancion;
import umu.tds.modelo.PlayList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
		Entidad ePlayList = null;
		try {
			ePlayList = servPersistencia.recuperarEntidad(playlist.getCodigo());
		} catch (NullPointerException e) {
		}
		if (ePlayList != null)
			return;

		// registrar primero los atributos que son objetos
		AdaptadorCancionTDS adaptadorCancion = AdaptadorCancionTDS.getUnicaInstancia();
		for (Cancion cancion : playlist.getCanciones()) {
			adaptadorCancion.registrarCancion(cancion);
		}

		ePlayList = new Entidad();
		ePlayList.setNombre("playlist");
		ePlayList.setPropiedades(
				new ArrayList<Propiedad>(Arrays.asList(new Propiedad("nombre", String.valueOf(playlist.getNombre())),
						new Propiedad("canciones", obtenerCodigosPlayList(playlist.getCanciones())))));

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
	public Cancion getPlayList(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	private String obtenerCodigosPlayList(List<Cancion> playlist) {
		String lineas = "";
		for (Cancion c : playlist) {
			lineas += c.getCodigo() + " ";
		}
		return lineas.trim();
	}

}
