package umu.tds.modelo;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import umu.tds.persistencia.DAOException;
import umu.tds.persistencia.FactoriaDAO;
import umu.tds.persistencia.IAdaptadorCancionDAO;

public class CatalogoCanciones {
	private Map<Integer, Cancion> canciones;
	private static CatalogoCanciones unicaInstancia = new CatalogoCanciones();
	private FactoriaDAO factoria;
	private IAdaptadorCancionDAO adaptadorCancion;

	private CatalogoCanciones() {
		canciones = new HashMap<Integer, Cancion>();

		try {
			factoria = FactoriaDAO.getInstancia();
			adaptadorCancion = factoria.getCancionDAO();
			this.cargarCatalogo();
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	public static CatalogoCanciones getUnicaInstancia() {
		return unicaInstancia;
	}

	public void addCancion(Cancion cancion) {
		canciones.put(cancion.getCodigo(), cancion);
		adaptadorCancion.registrarCancion(cancion);
	}

	public void removeCancion(Cancion cancion) {
		canciones.remove(cancion.getCodigo());
	}

	public Cancion getCancion(int key) {
		return canciones.get(key);
	}

	public Cancion getCancion(String titulo) {
		return canciones.values().stream().filter(cancion -> cancion.getTitulo().equals(titulo)).findFirst()
				.orElse(null);
	}

	public List<Cancion> getAllCanciones() throws DAOException {
		return new LinkedList<Cancion>(canciones.values());
	}

	/**
	 * Función que nos permite recuperar todas las canciones para trabajar con
	 * ellas en memoria
	 * 
	 * @throws DAOException
	 */
	private void cargarCatalogo() throws DAOException {
		List<Cancion> cancionesBD = adaptadorCancion.getAllCanciones();
		for (Cancion cancion : cancionesBD) {
			canciones.put(cancion.getCodigo(), cancion);
		}
	}
}
