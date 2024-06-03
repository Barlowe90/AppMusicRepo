package dam.exceptions;

public class UsuarioDuplicadoException extends Exception {
	private static final long serialVersionUID = 1L;

	public UsuarioDuplicadoException(String mensaje) {
		super(mensaje);
	}
}
