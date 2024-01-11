package umu.tds.persistencia;

/**
 * Patrón Adaptador y Factoria Abstracta DAO. Para cada entidad persistente se
 * crean adaptadoresw que implementan una interfaz DAO.
 */
public abstract class FactoriaDAO {
	private static FactoriaDAO unicaInstancia;
	public static final String DAO_TDS = "umu.tds.persistencia.TDSFactoriaDAO";

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

	public static FactoriaDAO getInstancia() throws DAOException {
		if (unicaInstancia == null)
			unicaInstancia = getInstancia(FactoriaDAO.DAO_TDS);
		return unicaInstancia;
	}

	// Métodos factoria para obtener adaptadores

	public abstract IAdaptadorUsuarioDAO getUsuarioDAO(); // UsuarioDAO

	public abstract IAdaptadorPlayListDAO getPlayListDAO(); // PlayListDAO

	public abstract IAdaptadorCancionDAO getCancionDAO(); // CancionDAO

}
