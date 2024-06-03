package dam.modelo;

public class EstiloMusical {
	private String nombre;

	public EstiloMusical(String nombreEstilo) {
		this.nombre = nombreEstilo;
	}

	public String getNombreEstilo() {
		return nombre;
	}

	public void setNombreEstilo(String nombreEstilo) {
		this.nombre = nombreEstilo;
	}

	@Override
	public String toString() {
		return "EstiloMusical [nombre=" + nombre + "]";
	}

}
