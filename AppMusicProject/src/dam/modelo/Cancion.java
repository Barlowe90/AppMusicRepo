package dam.modelo;

public class Cancion {
	private int codigo;
	private String titulo;
	private String interprete;
	private String estilo;
	private String url;
	private int numReproducciones;

	public Cancion(String titulo, String interprete, String estilo, String url) {
		this.codigo = 0;
		this.titulo = titulo;
		this.interprete = interprete;
		this.estilo = estilo;
		this.url = url;
		this.numReproducciones = 0;
	}

	public Cancion(String titulo, String interprete, String estilo, String url, int numRepro) {
		this(titulo, interprete, estilo, url);
		this.numReproducciones = numRepro;
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

	public String getURL() {
		return url;
	}

	public void setURL(String url) {
		this.url = url;
	}

	public int getNumReproducciones() {
		return numReproducciones;
	}

	public void setNumReproducciones(int numReproducciones) {
		this.numReproducciones = numReproducciones;
	}

	@Override
	public String toString() {
		return "Cancion [codigo=" + codigo + ", titulo=" + titulo + ", interprete=" + interprete + ", estilo=" + estilo
				+ ", url=" + url + ", numReproducciones=" + numReproducciones + "]";
	}
}
