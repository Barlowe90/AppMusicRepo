package umu.tds.persistencia;

import java.util.List;

import tds.driver.ServicioPersistencia;
import umu.tds.modelo.PlayList;

public class AdaptadorPlayListTDS implements IAdaptadorPlayListDAO {
	
	private static ServicioPersistencia servicioPersistencia;
	private static AdaptadorPlayListTDS unicaInstancia = null;
	
	public static AdaptadorPlayListTDS getUnicaInstancia() {
		if(unicaInstancia == null) {
			unicaInstancia = new AdaptadorPlayListTDS();
		}
		return unicaInstancia;
	}
	

	@Override
	public void registrarPlayList(PlayList playlist) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean borrarPlayList(PlayList playlist) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void updatePlayList(PlayList playlist) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PlayList getPlayList(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PlayList> getAllPlayLists() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
