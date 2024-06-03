package dam.persistencia;

import dam.modelo.PlayList;

public interface IAdaptadorPlayListDAO {

	public void registrarPlayList(PlayList playlist);

	public boolean borrarPlayList(PlayList playlist);

	public void updatePlayList(PlayList playlist);

	public PlayList getPlayList(int id);
}
