package umu.tds.modelo;

import java.util.LinkedList;
import java.util.List;

public class Interprete {
	private String nombre;
	private List<Cancion> canciones;

	public Interprete(String nombre) {
		this.nombre = nombre;
		this.canciones = new LinkedList<>();
	}

	public void agregarCancion(Cancion cancion) {
		this.canciones.add(cancion);
	}

	public String getNombre() {
		return nombre;
	}

	public List<Cancion> getCanciones() {
		return new LinkedList<>(canciones);
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Interprete [nombre=" + nombre + "]";
	}

}
