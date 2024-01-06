package umu.tds.componente;

import java.util.ArrayList;
import java.util.List;

public class CargadorCanciones {
	private static CargadorCanciones unicaInstancia = null;
	private String archivoCanciones;
	private List<CancionesListener> oyentes;

	public static CargadorCanciones getUnicaInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new CargadorCanciones();
		return unicaInstancia;
	}

	private CargadorCanciones() {
		oyentes = new ArrayList<>();
	}

	public void agregarOyente(CancionesListener oyente) {
		oyentes.add(oyente);
	}

	public void quitarOyente(CancionesListener oyente) {
		oyentes.remove(oyente);
	}

	private void notificarOyentes(CancionesEvent event) {
		for (CancionesListener oyente : oyentes) {
			oyente.nuevasCancionesDisponibles(event);
		}
	}

	public void setArchivoCanciones(String archivoCanciones) {
		this.archivoCanciones = archivoCanciones;
		Canciones nuevasCanciones = MapperCancionesXMLtoJava.cargarCanciones(archivoCanciones);
		notificarOyentes(new CancionesEvent(this, nuevasCanciones));
	}

	public String getArchivoCanciones() {
		return archivoCanciones;
	}

}
