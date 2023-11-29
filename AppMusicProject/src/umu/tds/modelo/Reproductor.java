package umu.tds.modelo;

public class Reproductor {

	private Cancion cancionActual;
	private static Reproductor unicaInstancia;

	private Reproductor() {
	}

	public static Reproductor getUnicaInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new Reproductor();
		return unicaInstancia;
	}

	// TOOD
	// play()

	public Cancion getCancionActual() {
		return cancionActual;
	}

	public void setCancionActual(Cancion cancionActual) {
		this.cancionActual = cancionActual;
	}

	@Override
	public String toString() {
		return "Reproductor [cancionActual=" + cancionActual + "]";
	}

}
