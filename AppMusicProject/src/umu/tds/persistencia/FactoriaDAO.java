package umu.tds.persistencia;

/**
 * Patrón Adaptador y Factoria Abstracta DAO. Para cada entidad persistente se
 * crean adaptadoresw que implementan una interfaz DAO.
 */
public abstract class FactoriaDAO {
	private static FactoriaDAO unicaInstancia;
	public static final String DAO_TDS = "persistencia.TDSFactoriaDAO";

	/**
	 * Constructor de la clase
	 */
	protected FactoriaDAO() {
	}

	/**
	 * Crea un tipo de Factoría DAO. Solo existe el tipo TDSFactoriaDAO
	 * 
	 * @param tipo String del tipo
	 * @return único objeto Singleton
	 */
	@SuppressWarnings("deprecation")
	public static FactoriaDAO getInstancia(String tipo) throws DAOException {
		if (unicaInstancia == null)
			try {
				unicaInstancia = (FactoriaDAO) Class.forName(tipo).newInstance();
			} catch (Exception e) {
				throw new DAOException(e.getMessage());
			}
		return unicaInstancia;
	}

	public static FactoriaDAO getUnicaInstancia() throws DAOException {
		if (unicaInstancia == null)
			return getInstancia(FactoriaDAO.DAO_TDS);
		else
			return unicaInstancia;
	}

	public abstract IAdaptadorUsuarioDAO getUsuarioDAO();

	public abstract IAdaptadorPlayListDAO getLineaPlayListDAO();

	public abstract IAdaptadorCancionDAO getCancionDAO();

	public abstract IAdaptadorReproductorDAO getReproductorDAO();

	public abstract IAdaptadorInterpreteDAO getInterpreteDAO();

	public abstract IAdaptadorEstiloMusicalDAO getEstiloMusicalDAO();

	public abstract IAdaptadorCreadorPDFDAO getCreadorPDFDAO();
}
