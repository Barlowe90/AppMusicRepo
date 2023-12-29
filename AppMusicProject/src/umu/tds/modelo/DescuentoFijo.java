package umu.tds.modelo;

public class DescuentoFijo implements Descuento {

	public static final double descuentoFijo = 2.0; // 2 € de descuento

	public DescuentoFijo() {
	}

	@Override
	public double calcularDescuento(double precio) {
		return precio - descuentoFijo;
	}

	@Override
	public double getDescuento() {
		return descuentoFijo;
	}

}
