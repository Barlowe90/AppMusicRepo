package umu.tds.modelo;

public class Interprete {
	private String nombre;

	public Interprete(String nombre) {
		this.nombre = nombre;
	}

	// TODO
	// getCanciones()

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Interprete [nombre=" + nombre + "]";
	}

}
