package dam.modelo;

/**
 * Patron Estrategia para aplicar descuentos
 */
public class FactoriaDescuento {
	private final static String DESCUENTO_FIJO = "DescuentoFijo";
	private final static String DESCUENTO_JOVEN = "DescuentoJovenes";

	public static Descuento obtenerDescuentoAutomatico(Usuario usuario) {
		if (usuario.isJoven()) {
			return new DescuentoJovenes();
		} else {
			return new DescuentoFijo();
		}
	}

	public static Descuento obtenerDescuento(String codigoDescuento) {
		if (codigoDescuento == null || codigoDescuento.isEmpty()) {
			return null;
		}

		switch (codigoDescuento) {
		case DESCUENTO_FIJO:
			return new DescuentoFijo();
		case DESCUENTO_JOVEN:
			return new DescuentoJovenes();
		default:
			throw new IllegalArgumentException("Codigo de descuento no valido: " + codigoDescuento);
		}
	}
}
