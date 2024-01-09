package umu.tds.modelo;

import java.util.LinkedList;
import java.util.List;

public class PlayList {
	private int codigo;
	private String nombre;
	private List<Cancion> canciones;

	public PlayList(String nombre) {
		this.nombre = nombre;
	}

	public PlayList(String nombre, List<Cancion> canciones) {
		this(nombre);
		this.codigo = 0;
		this.canciones = new LinkedList<Cancion>();
	}

	/**
	 * Funcion que añade una cancion a la playlist actual.
	 * 
	 * @param cancion que se desea añadir.
	 * @return true si se añade, false si no se añade.
	 */
	public boolean addCancion(Cancion cancion) {
		if (canciones.contains(cancion)) {
			System.out.println("CanciÃ³n actualmente en lista.");
			return true;
		} else
			return canciones.add(cancion);
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Cancion> getCanciones() {
		return new LinkedList<Cancion>(canciones);
	}

	public void setCanciones(List<Cancion> canciones) {
		this.canciones = canciones;
	}

	@Override
	public String toString() {
		return "PlayList [nombre=" + nombre + ", canciones=" + canciones + "]";
	}

}
