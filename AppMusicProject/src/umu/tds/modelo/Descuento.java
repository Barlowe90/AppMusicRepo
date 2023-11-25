package umu.tds.modelo;

public interface Descuento {
	// Precio en Euros
	public static final double precio = 9.99;

	/**
	 * Método que devuelve el precio aplicando un descuento
	 * 
	 * @return doubule. Precio con el descuento aplicado
	 */
	public double calcDescuento();
}
