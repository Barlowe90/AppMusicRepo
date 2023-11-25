package umu.tds.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import umu.tds.persistencia.DAOException;

public class CatalogoCanciones {

	private Map<Integer, Cancion> canciones;
	private static CatalogoCanciones unicaInstancia = new CatalogoCanciones();

//	private FactoriaDAO dao;
//	private IAdaptadorCancionDAO adaptadorCancion;

	private CatalogoCanciones() {
		try {

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static CatalogoCanciones getUnicaInstancia() {
		return unicaInstancia;
	}

	public void addCancion(Cancion cancion) {
		canciones.put(cancion.getCodigo(), cancion);
	}

	public void removeCancion(Cancion cancion) {
		canciones.remove(cancion.getCodigo());
	}

	public Cancion getCancion(int key) {
		return canciones.get(key);
	}

	public List<Cancion> getAllCanciones() {
		ArrayList<Cancion> lista = new ArrayList<Cancion>();
		for (Cancion cancion : canciones.values()) {
			lista.add(cancion);
		}
		return lista;
	}

	/**
	 * Función que nos permite recuperar todas las canciones para trabajar con ellas
	 * en memoria
	 * 
	 * @throws DAOException
	 */
//	private void cargarCatalogo() throws DAOException {
//		List<Cancion> cancionesBD = adaptadorCancion.recuperarTodasCanciones();
//		for (Cancion cancion : cancionesBD) {
//			canciones.put(cancion.getCodigo(), cancion);
//		}
//	}
}
