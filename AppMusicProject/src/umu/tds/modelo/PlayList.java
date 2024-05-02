package umu.tds.modelo;

import java.util.LinkedList;
import java.util.List;

public class PlayList {
	private int codigo;
	private String nombre;
	private List<Cancion> canciones;

	public PlayList(String nombre) {
		this.codigo = 0;
		this.nombre = nombre;
		this.canciones = new LinkedList<Cancion>();
	}

	public PlayList(String nombre, List<Cancion> canciones) {
		this(nombre);
		this.canciones = new LinkedList<Cancion>(canciones);
	}

	/**
	 * Funcion que añade una cancion a la playlist actual.
	 * 
	 * @param cancion que se desea añadir.
	 * @return true si se añade, false si no se añade.
	 */
	public void addCancion(Cancion cancion) {
		if (!canciones.contains(cancion))
			canciones.add(cancion);
	}

	public boolean contieneCancion(Cancion cancion) {
		return canciones.contains(cancion);
	}

	public void eliminarCancion(Cancion cancion) {
		this.canciones.remove(cancion);
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
		return new LinkedList<Cancion>(this.canciones);
	}

	public void setCanciones(List<Cancion> canciones) {
		this.canciones = canciones;
	}

	@Override
	public String toString() {
		return "PlayList [nombre=" + nombre + ", canciones=" + canciones + "]";
	}

}
