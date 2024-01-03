package umu.tds.modelo;

/**
 * Patron Estrategia para aplicar descuentos
 */
public class FactoriaDescuento {
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
		case "DescuentoFijo":
			return new DescuentoFijo();
		case "DescuentoJovenes":
			return new DescuentoJovenes();
		default:
			throw new IllegalArgumentException("Codigo de descuento no valido: " + codigoDescuento);
		}
	}
}
