package umu.tds.modelo;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Cancion {
	private int codigo;
	private String titulo;
	private final String interprete;
//	private final Interprete interprete;
	private final String estilo;
//	private final EstiloMusical estilo;
	private Path rutaCancion;
	private int numReproducciones;

	public Cancion(String titulo, String interprete, String estilo, String rutaCancion) {
		this.codigo = 0;
		this.titulo = titulo;
		this.interprete = interprete;
		this.estilo = estilo;
		this.rutaCancion = Paths.get(rutaCancion);
		this.numReproducciones = 0;
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

	public Path getRutaCancion() {
		return rutaCancion;
	}

	public void setRutaCancion(Path rutaCancion) {
		this.rutaCancion = rutaCancion;
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
				+ ", rutaCancion=" + rutaCancion + ", numReproducciones=" + numReproducciones + "]";
	}

}
