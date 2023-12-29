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
}
