package umu.tds.componente;

import java.util.EventObject;

public class CancionesEvent extends EventObject {
	private static final long serialVersionUID = 1L;
	private final Canciones canciones;

	public CancionesEvent(Object source, Canciones canciones) {
		super(source);
		this.canciones = canciones;
	}

	public Canciones getCanciones() {
		return canciones;
	}
}
