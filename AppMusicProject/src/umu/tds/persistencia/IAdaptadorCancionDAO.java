package umu.tds.persistencia;

import java.util.List;
import umu.tds.modelo.Cancion;

public interface IAdaptadorCancionDAO {
	public void registrarCancion(Cancion cancion);

	public boolean borrarCancion(Cancion cancion);

	public void updateCancion(Cancion cancion);

	public Cancion getCancion(int id);

	public List<Cancion> getAllCanciones();

	void registrarCanciones(List<Cancion> canciones);
}
