package umu.tds.componente;

import java.util.ArrayList;
import java.util.List;

import umu.tds.modelo.Cancion;

public class Canciones {
	private List<Cancion> canciones;
	
	
	public List<Cancion> getCancion(){
		if(canciones == null) {
			canciones = new ArrayList<Cancion>();
		}
		return this.canciones;
	}
	
	// Funcion auxiliar
	
	public boolean imprimirCanciones() {
		for(Cancion cancion : this.getCancion()) {
			System.out.println("Titulo: " + cancion.getTitulo());
			System.out.println("Interprete: " + cancion.getInterprete());
			System.out.println("Estilo: " + cancion.getEstilo());
			System.out.println("URL: " + cancion.getRutaCancion());
			System.out.println("***** ***** *****");
		}
		return true;
	}
	
}
