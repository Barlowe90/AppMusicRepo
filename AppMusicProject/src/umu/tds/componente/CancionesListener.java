package umu.tds.componente;

import java.util.EventListener;

public interface CancionesListener extends EventListener {
	void nuevasCancionesDisponibles(CancionesEvent event);
}