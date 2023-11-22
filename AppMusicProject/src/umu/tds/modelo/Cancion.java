package umu.tds.modelo;

public class Cancion {
	private String titulo;
	private Interprete interprete;
	private EstiloMusical estilo;
	private String rutaCancion;
	private int numReproducciones;

	public Cancion(String titulo, Interprete interprete, EstiloMusical estilo, String rutaCancion, int numReproducciones) {
		super();
		this.titulo = titulo;
		this.interprete = interprete;
		this.estilo = estilo;
		this.rutaCancion = rutaCancion;
		this.numReproducciones = numReproducciones;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Interprete getInterprete() {
		return interprete;
	}

	public void setInterprete(Interprete interprete) {
		this.interprete = interprete;
	}

	public EstiloMusical getEstilo() {
		return estilo;
	}

	public void setEstilo(EstiloMusical estilo) {
		this.estilo = estilo;
	}

	public String getRutaCancion() {
		return rutaCancion;
	}

	public void setRutaCancion(String rutaCancion) {
		this.rutaCancion = rutaCancion;
	}

	public int getNumReproducciones() {
		return numReproducciones;
	}

	public void setNumReproducciones(int numReproducciones) {
		this.numReproducciones = numReproducciones;
	}

}
