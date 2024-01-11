package umu.tds.modelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class CreadorPDF {

	private static CreadorPDF unicaInstancia;
	private static String NOMBRE_ARCHIVO = "user_pdfs";
	private static String FORMATO_ARCHIVO = ".pdf";

	private CreadorPDF() {

	}

	public static CreadorPDF getUnicaInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new CreadorPDF();
		return unicaInstancia;
	}

	public void crearPDF(Usuario usuario, List<PlayList> playlist) throws FileNotFoundException, DocumentException {
		String nick = usuario.getNick();
		String rutaArchivo = NOMBRE_ARCHIVO + File.separator + nick + FORMATO_ARCHIVO;

		// Comprobar si existe la carpeta
		File carpeta = new File(NOMBRE_ARCHIVO);
		if (!carpeta.exists()) {
			carpeta.mkdirs();
		}

		FileOutputStream archivo = new FileOutputStream(rutaArchivo);
		Document documento = new Document();
		PdfWriter.getInstance(documento, archivo);
		documento.open();
		documento.add(new Paragraph("Listado de las playlist del usuario " + nick));
		documento.add(new Paragraph("Nombre de la playlist "));
		documento.add(new Paragraph("Titulo"));
		documento.add(new Paragraph("Intérprete"));
		documento.add(new Paragraph("Estilo"));
		documento.close();
	}

}
