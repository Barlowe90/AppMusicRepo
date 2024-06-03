package dam.exceptions;

public class EmailNoValidoException extends Exception {
	private static final long serialVersionUID = 1L;

	public EmailNoValidoException(String mensaje) {
		super(mensaje);
	}
}
