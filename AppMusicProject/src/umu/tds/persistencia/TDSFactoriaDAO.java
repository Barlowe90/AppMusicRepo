package umu.tds.persistencia;

public class TDSFactoriaDAO extends FactoriaDAO {
	public TDSFactoriaDAO() {
	}

	@Override
	public IAdaptadorUsuarioDAO getUsuarioDAO() {
		return AdaptadorUsuarioTDS.getUnicaInstancia();
	}

	@Override
	public IAdaptadorPlayListDAO getLineaPlayListDAO() {
		return AdaptadorPlayListTDS.getUnicaInstancia();
	}

	@Override
	public IAdaptadorCancionDAO getCancionDAO() {
		return AdaptadorCancionTDS.getUnicaInstancia();
	}

	@Override
	public IAdaptadorReproductorDAO getReproductorDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IAdaptadorCreadorPDFDAO getCreadorPDFDAO() {
		// TODO Auto-generated method stub
		return null;
	}

}
