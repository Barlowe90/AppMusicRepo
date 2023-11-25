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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IAdaptadorCancionDAO getCancionDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IAdaptadorReproductorDAO getReproductorDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IAdaptadorInterpreteDAO getInterpreteDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IAdaptadorEstiloMusicalDAO getEstiloMusicalDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IAdaptadorCreadorPDFDAO getCreadorPDFDAO() {
		// TODO Auto-generated method stub
		return null;
	}

}
