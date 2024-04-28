package umu.tds.persistencia;

import umu.tds.modelo.PlayList;

public interface IAdaptadorPlayListDAO {

	public void registrarPlayList(PlayList playlist);

	public boolean borrarPlayList(PlayList playlist);

	public void updatePlayList(PlayList playlist);

	public PlayList getPlayList(int id);
}
