package umu.tds.persistencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.modelo.Cancion;

public class AdaptadorCancionTDS implements IAdaptadorCancionDAO {

	private static ServicioPersistencia servPersistencia;
	private static AdaptadorCancionTDS unicaInstancia = null;
	private Entidad eCancion;

	private static final String CANCION = "cancion";
	private static final String ID = "id";
	private static final String TITULO = "titulo";
	private static final String INTERPRETE = "interprete";
	private static final String ESTILO_MUSICAL = "estiloMusical";
	private static final String RUTA_CANCION = "rutaCancion";
	private static final String NUM_REPRODUCCIONES = "numReproducciones";

	public static AdaptadorCancionTDS getUnicaInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new AdaptadorCancionTDS();
		return unicaInstancia;
	}

	private AdaptadorCancionTDS() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}

	private Cancion entidadToCancion(Entidad eCancion) {
		String titulo = servPersistencia.recuperarPropiedadEntidad(eCancion, TITULO);
		String interprete = servPersistencia.recuperarPropiedadEntidad(eCancion, INTERPRETE);
		String estiloMusical = servPersistencia.recuperarPropiedadEntidad(eCancion, ESTILO_MUSICAL);
		String rutaCancion = servPersistencia.recuperarPropiedadEntidad(eCancion, RUTA_CANCION);

		Cancion cancion = new Cancion(titulo, interprete, estiloMusical, rutaCancion);
		cancion.setCodigo(eCancion.getId());

		return cancion;
	}

	private Entidad cancionToEntidad(Cancion cancion) {
		eCancion = new Entidad();
		eCancion.setNombre(CANCION);

		eCancion.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(new Propiedad(TITULO, cancion.getTitulo()),
				new Propiedad(INTERPRETE, cancion.getInterprete()), new Propiedad(ESTILO_MUSICAL, cancion.getEstilo()),
				new Propiedad(RUTA_CANCION, cancion.getURL()),
				new Propiedad(NUM_REPRODUCCIONES, String.valueOf(cancion.getNumReproducciones())))));

		return eCancion;
	}

	@Override
	public void registrarCancion(Cancion cancion) {
		try {
			eCancion = servPersistencia.recuperarEntidad(cancion.getCodigo());
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

		if (eCancion != null)
			return;

		eCancion = this.cancionToEntidad(cancion);
		eCancion = servPersistencia.registrarEntidad(eCancion);
		cancion.setCodigo(eCancion.getId());
	}

	@Override
	public void registrarCanciones(List<Cancion> canciones) {
		for (Cancion cancion : canciones) {
			registrarCancion(cancion);
		}
	}

	@Override
	public boolean borrarCancion(Cancion cancion) {
		eCancion = servPersistencia.recuperarEntidad(cancion.getCodigo());
		return servPersistencia.borrarEntidad(eCancion);
	}

	@Override
	public void updateCancion(Cancion cancion) {
		eCancion = servPersistencia.recuperarEntidad(cancion.getCodigo());

		for (Propiedad prop : eCancion.getPropiedades()) {
			if (prop.getNombre().equals(ID)) {
				prop.setValor(String.valueOf(cancion.getCodigo()));
			} else if (prop.getNombre().equals(TITULO)) {
				prop.setValor(cancion.getTitulo());
			} else if (prop.getNombre().equals(INTERPRETE)) {
				prop.setValor(cancion.getInterprete());
			} else if (prop.getNombre().equals(ESTILO_MUSICAL)) {
				prop.setValor(cancion.getEstilo());
			} else if (prop.getNombre().equals(RUTA_CANCION)) {
				prop.setValor(cancion.getURL());
			} else if (prop.getNombre().equals(NUM_REPRODUCCIONES)) {
				prop.setValor(String.valueOf(cancion.getNumReproducciones()));
			}
			servPersistencia.modificarPropiedad(prop);
		}
	}

	@Override
	public Cancion getCancion(int key) {
		eCancion = servPersistencia.recuperarEntidad(key);
		return entidadToCancion(eCancion);
	}

	@Override
	public List<Cancion> getAllCanciones() {
		List<Entidad> eCanciones = servPersistencia.recuperarEntidades(CANCION);
		List<Cancion> canciones = new LinkedList<Cancion>();

		for (Entidad eCancion : eCanciones) {
			canciones.add(getCancion(eCancion.getId()));

		}
		return canciones;
	}

}
