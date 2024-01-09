package umu.tds.modelo;

public interface Descuento {
	double precio = 9.99;

	/**
	 * Metodo que devuelve el precio aplicando un descuento
	 * 
	 * @return double. Precio con el descuento aplicado
	 */
	double calcularDescuento();

	double getDescuento();

	default double getPrecio() {
		return precio;
	}

}
