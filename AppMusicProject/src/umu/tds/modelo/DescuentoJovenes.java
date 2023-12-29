package umu.tds.modelo;

public class DescuentoJovenes implements Descuento {

	public static final double descuentoJoven = 0.2; // 20 % de descuento

	public DescuentoJovenes() {
	}

	@Override
	public double calcularDescuento(double precio) {
		return precio - (precio * descuentoJoven);
	}

	@Override
	public double getDescuento() {
		return descuentoJoven;
	}
}
