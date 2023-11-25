package umu.tds.modelo;

public class DescuentoJovenes implements Descuento {
	// Dividimos directamente entre 100. Ahorramos a la CPU una división por ser
	// costosa
	public static final double descuentoFijo = 0.5; // 50 % de descuento

	@Override
	public double calcDescuento() {
		return precio - (precio * descuentoFijo);
	}

}
