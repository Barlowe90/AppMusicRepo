package umu.tds.modelo;

import java.util.List;

public class PlayList {
	private String nombre;
	private List<Cancion> canciones;

	public PlayList(String nombre, List<Cancion> canciones) {
		super();
		this.nombre = nombre;
		this.canciones = canciones;
	}

	// TODO
	
	// addCancion()
	
	// getCanciones()
	
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

}
