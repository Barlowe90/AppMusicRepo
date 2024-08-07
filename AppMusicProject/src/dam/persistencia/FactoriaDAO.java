package dam.persistencia;

/**
 * Patron Adaptador y Factoria Abstracta DAO. Para cada entidad persistente se
 * crean adaptadoresw que implementan una interfaz DAO.
 */
public abstract class FactoriaDAO {
	private static FactoriaDAO unicaInstancia;
	public static final String DAO_TDS = "dam.persistencia.TDSFactoriaDAO";

	/**
	 * Constructor de la clase
	 */
	protected FactoriaDAO() {
	}

	/**
	 * Crea un tipo de Factoria DAO. Solo existe el tipo TDSFactoriaDAO
	 * 
	 * @param tipo String del tipo
	 * @return Add objeto Singleton
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

	// Metodos factoria para obtener adaptadores

	public abstract IAdaptadorUsuarioDAO getUsuarioDAO(); // UsuarioDAO

	public abstract IAdaptadorCancionDAO getCancionDAO(); // CancionDAO

	public abstract IAdaptadorPlayListDAO getPlayListDAO(); // CancionDAO

}
