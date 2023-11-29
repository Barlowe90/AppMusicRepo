package umu.tds.controlador;

import java.time.LocalDate;
import java.util.List;

import umu.tds.modelo.Cancion;
import umu.tds.modelo.CatalogoCanciones;
import umu.tds.modelo.CatalogoUsuarios;
import umu.tds.modelo.CreadorPDF;
import umu.tds.modelo.EstiloMusical;
import umu.tds.modelo.Interprete;
import umu.tds.modelo.Reproductor;
import umu.tds.modelo.Usuario;
import umu.tds.persistencia.IAdaptadorCancionDAO;
import umu.tds.persistencia.IAdaptadorUsuarioDAO;
import umu.tds.persistencia.DAOException;
import umu.tds.persistencia.FactoriaDAO;

public class AppMusic {
	private static AppMusic unicaInstancia;

	private IAdaptadorUsuarioDAO adaptadorUsuario;
	private IAdaptadorCancionDAO adaptadorCancion;

	private CatalogoUsuarios catalogoUsuarios;
	private CatalogoCanciones catalogoCanciones;

	private Reproductor reproductor;
	private CreadorPDF creadorPDF;

	private AppMusic() {
		inicializarAdaptadores();
		inicializarCatalogos();
		inicializarServicios();
	}

	public static AppMusic getUnicaInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new AppMusic();
		return unicaInstancia;
	}

	public void registrarUsuario(String user, String password, String email, LocalDate fechaNacimiento) {
		// TODO controlar usuarios duplicados
		Usuario usuario = new Usuario(user, password, email, fechaNacimiento);
		adaptadorUsuario.registrarUsuario(usuario);
		catalogoUsuarios.addUsuario(usuario);
	}

	public void registrarCancion(String titulo, Interprete interprete, EstiloMusical estiloMusical,
			String rutaCancion) {
		Cancion cancion = new Cancion(titulo, interprete, estiloMusical, rutaCancion);
		adaptadorCancion.registrarCancion(cancion);
		catalogoCanciones.addCancion(cancion);

	}

	private void inicializarAdaptadores() {
		FactoriaDAO factoria = null;
		try {
			factoria = FactoriaDAO.getInstancia(FactoriaDAO.DAO_TDS);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		adaptadorUsuario = factoria.getUsuarioDAO();
		adaptadorCancion = factoria.getCancionDAO();
	}

	private void inicializarCatalogos() {
		catalogoUsuarios = CatalogoUsuarios.getUnicaInstancia();
		catalogoCanciones = CatalogoCanciones.getUnicaInstancia();
	}

	private void inicializarServicios() {
		reproductor = Reproductor.getUnicaInstancia();
		creadorPDF = CreadorPDF.getUnicaInstancia();
	}

	public void reproducirCancion(Cancion cancion) {
		reproductor.play(cancion);
	}

	public void crearPDF() {
		creadorPDF.crearPDF();
	}

	public boolean existeUsuario(String key) {
		return CatalogoUsuarios.getUnicaInstancia().getUsuario(key) != null;
	}

	public List<Usuario> getUsuarios() {
		return catalogoUsuarios.getAllUsuarios();
	}

	public List<Cancion> getCanciones() {
		return CatalogoCanciones.getAllCanciones();
	}
}
