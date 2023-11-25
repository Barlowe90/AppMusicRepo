package umu.tds.modelo;

public class DescuentoFijo implements Descuento {
	// Dividimos directamente entre 100. Ahorramos a la CPU una división por ser
	// costosa
	public static final double descuentoFijo = 0.2; // 20 % de descuento

	@Override
	public double calcDescuento() {
		return precio - (precio * descuentoFijo);
	}

}
