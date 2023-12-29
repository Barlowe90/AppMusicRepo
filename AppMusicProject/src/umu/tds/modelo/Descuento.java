package umu.tds.modelo;

public interface Descuento {
	double precio = 9.99;

	/**
	 * Método que devuelve el precio aplicando un descuento
	 * 
	 * @return double. Precio con el descuento aplicado
	 */
	double calcularDescuento(double precio);

	double getDescuento();
}
