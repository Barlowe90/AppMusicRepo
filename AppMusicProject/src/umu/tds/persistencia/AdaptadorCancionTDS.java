package umu.tds.persistencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.modelo.Cancion;
import umu.tds.modelo.EstiloMusical;
import umu.tds.modelo.Interprete;

public class AdaptadorCancionTDS implements IAdaptadorCancionDAO {

	private static ServicioPersistencia servPersistencia;
	private static AdaptadorCancionTDS unicaInstancia = null;

	public static AdaptadorCancionTDS getUnicaInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new AdaptadorCancionTDS();
		return unicaInstancia;
	}

	private AdaptadorCancionTDS() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}

	@Override
	public void registrarCancion(Cancion cancion) {
		Entidad eCancion = null;

		try {
			eCancion = servPersistencia.recuperarEntidad(cancion.getCodigo());
		} catch (NullPointerException e) {
		}

		if (eCancion != null)
			return;

		// Crear entidad cancion
		eCancion = new Entidad();
		eCancion.setNombre("cancion");
		eCancion.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(new Propiedad("titulo", cancion.getTitulo()),
				new Propiedad("interprete", String.valueOf(cancion.getInterprete())),
//				new Propiedad("interprete", String.valueOf(cancion.getInterprete().getNombre())),
//				new Propiedad("estiloMusical", String.valueOf(cancion.getEstilo().getNombreEstilo())),
				new Propiedad("estiloMusical", String.valueOf(cancion.getEstilo())),
				new Propiedad("rutaCancion", String.valueOf(cancion.getRutaCancion())),
				new Propiedad("numReproducciones", String.valueOf(cancion.getNumReproducciones())))));

		// Registrar entidad cancion
		eCancion = servPersistencia.registrarEntidad(eCancion);
		// Asignar ID. Lo genera el servicio de persistencia
		cancion.setCodigo(eCancion.getId());
	}

	@Override
	public void borrarCancion(Cancion cancion) {
		Entidad eCancion = servPersistencia.recuperarEntidad(cancion.getCodigo());
		servPersistencia.borrarEntidad(eCancion);
	}

	@Override
	public void modificarCancion(Cancion cancion) {
		Entidad eCancion = servPersistencia.recuperarEntidad(cancion.getCodigo());

		for (Propiedad prop : eCancion.getPropiedades()) {
			if (prop.getNombre().equals("codigo")) {
				prop.setValor(String.valueOf(cancion.getCodigo()));
			} else if (prop.getNombre().equals("titulo")) {
				prop.setValor(cancion.getTitulo());
			} else if (prop.getNombre().equals("interprete")) {
				prop.setValor(String.valueOf(cancion.getInterprete()));
			} else if (prop.getNombre().equals("estiloMusical")) {
				prop.setValor(String.valueOf(cancion.getEstilo()));
			} else if (prop.getNombre().equals("rutaCancion")) {
				prop.setValor(String.valueOf(cancion.getRutaCancion()));
			} else if (prop.getNombre().equals("numReproducciones")) {
				prop.setValor(String.valueOf(cancion.getNumReproducciones()));
			}
			servPersistencia.modificarPropiedad(prop);
		}
	}

	@Override
	public Cancion recuperaCancion(int key) {
		Entidad eCancion;
		String titulo;
//		String interpreteS;
		String interprete;
		String estiloMusical;
		String rutaCancion;
		int numReproducciones;

		eCancion = servPersistencia.recuperarEntidad(key);
		titulo = servPersistencia.recuperarPropiedadEntidad(eCancion, "titulo");

		// esta es una opción, pero se crearían más objetos de tipo Interprete
//		interpreteS = servPersistencia.recuperarPropiedadEntidad(eCancion, "interprete");
//		interprete = new Interprete(interpreteS);

		// por el momento voy a cambiarlos a tipos String directamente hasta encontrar
		// la manera de recuperar sus objetos sin necesidad de crear un adaptador
		interprete = servPersistencia.recuperarPropiedadEntidad(eCancion, "interprete");
		estiloMusical = servPersistencia.recuperarPropiedadEntidad(eCancion, "estiloMusical");

		rutaCancion = servPersistencia.recuperarPropiedadEntidad(eCancion, "rutaCancion");
		numReproducciones = Integer.parseInt(servPersistencia.recuperarPropiedadEntidad(eCancion, "numReproducciones"));

		Cancion cancion = new Cancion(titulo, interprete, estiloMusical, rutaCancion);
		cancion.setCodigo(key);
		return cancion;
	}

	@Override
	public List<Cancion> recuperarTodosCanciones() {
		List<Cancion> canciones = new LinkedList<Cancion>();
		List<Entidad> entidades = servPersistencia.recuperarEntidades("cancion");

		for (Entidad eCancion : entidades) {
			canciones.add(recuperaCancion(eCancion.getId()));

		}
		return canciones;
	}

}
