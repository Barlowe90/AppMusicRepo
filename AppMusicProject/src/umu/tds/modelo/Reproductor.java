package umu.tds.modelo;

public class Reproductor {

	private Cancion cancionActual; // solo puede tener 1

	public Reproductor(Cancion cancionActual) {
		this.cancionActual = cancionActual;
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
