package umu.tds.modelo;

public class Cancion {
	private int codigo;
	private String titulo;
	private final String interprete;
	private final String estilo;
	private String rutaCancion;
	private int numReproducciones;
	
	private String URL;

	public Cancion(String titulo, String interprete, String estilo, String rutaCancion) {
		this.codigo = 0;
		this.titulo = titulo;
		this.interprete = interprete;
		this.estilo = estilo;
		this.rutaCancion = rutaCancion;
		this.numReproducciones = 0;
		this.URL = "";
	}

	public Cancion(String titulo, String interprete, String estilo, String rutaCancion, int numRepro) {
		this(titulo, interprete, estilo, rutaCancion);
		this.numReproducciones = numRepro;
		this.URL = "";
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getInterprete() {
		return interprete;
	}

	public String getEstilo() {
		return estilo;
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
	
	public String getURL() {
		return URL;
	}
	
	public void setURL(String url) {
		this.URL = url;
	}

	@Override
	public String toString() {
		return "Cancion [codigo=" + codigo + ", titulo=" + titulo + ", interprete=" + interprete + ", estilo=" + estilo
				+ ", rutaCancion=" + rutaCancion + ", numReproducciones=" + numReproducciones + ", URL=" + URL + "]";
	}

}
