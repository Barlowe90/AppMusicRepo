package umu.tds.modelo;

public class CreadorPDF {

	private static CreadorPDF unicaInstancia;

	private CreadorPDF() {

	}

	public static CreadorPDF getUnicaInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new CreadorPDF();
		return unicaInstancia;
	}

	// TODO
	// crearPDF()

}
