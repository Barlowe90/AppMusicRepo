package umu.tds.controlador;

import java.time.LocalDate;

import umu.tds.modelo.CatalogoCanciones;
import umu.tds.modelo.CatalogoUsuarios;
import umu.tds.modelo.CreadorPDF;
import umu.tds.modelo.Reproductor;
import umu.tds.modelo.Usuario;

public class AppMusic {
	private static AppMusic unicaInstancia;

//	private IAdaptadorUsuarioDAO adaptadorUsuario;
//	private IAdaptadorCancionDAO adaptadorCancion; 

	private CatalogoUsuarios catalogoUsuarios;
	private CatalogoCanciones catalogoCanciones;

	private Reproductor reproductor;
	private CreadorPDF crearPDF;

	private AppMusic() {
//		inicializarAdaptadores();

		inicializarCatalogos();
	}

	public static AppMusic getUnicaInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new AppMusic();
		return unicaInstancia;
	}

	public void registrarUsuario(String user, String password, String email, LocalDate fechaNacimiento) {
		// TODO controlar usuarios duplicados
		Usuario usuario = new Usuario(user, password, email, fechaNacimiento);
		// Adaptadores
	}
}
