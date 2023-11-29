package umu.tds.persistencia;

import java.util.List;
import umu.tds.modelo.Cancion;

public interface IAdaptadorCancionDAO {
	public void registrarCancion(Cancion cancion);

	public void borrarCancion(Cancion cancion);

	public void modificarCancion(Cancion cancion);

	public Cancion recuperaCancion(int key);

	public List<Cancion> recuperarTodosCanciones();
}
