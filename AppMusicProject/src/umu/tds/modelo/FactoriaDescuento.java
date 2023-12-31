package umu.tds.modelo;

/**
 * Patrón Estrategia para aplicar descuentos
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
		case "Fijo":
			return new DescuentoFijo();
		case "Jovenes":
			return new DescuentoJovenes();
		default:
			throw new IllegalArgumentException("Código de descuento no válido: " + codigoDescuento);
		}
	}
}
