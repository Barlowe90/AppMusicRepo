package umu.tds.persistencia;

public class TDSFactoriaDAO extends FactoriaDAO {
	public TDSFactoriaDAO() {
	}

	@Override
	public IAdaptadorUsuarioDAO getUsuarioDAO() {
		return AdaptadorUsuarioTDS.getUnicaInstancia();
	}

	// TODO
//	@Override
//	public IAdaptadorPlayListDAO getLineaPlayListDAO() {
//		return AdaptadorPlayListTDS.getUnicaInstancia();
//	}

	@Override
	public IAdaptadorCancionDAO getCancionDAO() {
		return AdaptadorCancionTDS.getUnicaInstancia();
	}

	// TODO
//	@Override
//	public IAdaptadorReproductorDAO getReproductorDAO() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public IAdaptadorCreadorPDFDAO getCreadorPDFDAO() {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
