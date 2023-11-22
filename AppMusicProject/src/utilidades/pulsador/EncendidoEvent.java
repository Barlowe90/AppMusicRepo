package utilidades.pulsador;

import java.util.EventObject;

public class EncendidoEvent extends EventObject {
	private static final long serialVersionUID = 1L;
	protected boolean oldEnvedido, newEncendido;

	public EncendidoEvent(Object fuente, boolean anterior, boolean nuevo) {
		super(fuente);
		newEncendido = nuevo;
		oldEnvedido = anterior;
	}

	public boolean getOldEnvedido() {
		return oldEnvedido;
	}

	public boolean getNewEncendido() {
		return newEncendido;
	}

}
