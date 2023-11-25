package umu.tds.modelo;

import java.util.LinkedList;
import java.util.List;

public class PlayList {
	private String nombre;
	private List<Cancion> canciones;

	public PlayList(String nombre, List<Cancion> canciones) {
		this.nombre = nombre;
		this.canciones = new LinkedList<Cancion>();
	}

	/**
	 * Función que añade una canción a la playlist actual.
	 * 
	 * @param cancion que se desea añadir.
	 * @return true si se añadió, false si no se añadió.
	 */
	public boolean addCancion(Cancion cancion) {
		if (canciones.contains(cancion)) {
			System.out.println("Canción actualmente en lista.");
			return true;
		} else
			return canciones.add(cancion);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Cancion> getCanciones() {
		return canciones;
	}

	public void setCanciones(List<Cancion> canciones) {
		this.canciones = canciones;
	}

	@Override
	public String toString() {
		return "PlayList [nombre=" + nombre + ", canciones=" + canciones + "]";
	}

}
