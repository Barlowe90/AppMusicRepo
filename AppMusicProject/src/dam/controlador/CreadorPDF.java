package dam.controlador;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import dam.modelo.*;

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

	public void crearPDF(Usuario usuario, List<PlayList> playlists) throws FileNotFoundException, DocumentException {
		String nick = usuario.getNick();
		String rutaArchivo = NOMBRE_ARCHIVO + File.separator + nick + FORMATO_ARCHIVO;

		// Comprobar si existe la carpeta
		File carpeta = new File(NOMBRE_ARCHIVO);
		if (!carpeta.exists()) {
			carpeta.mkdirs();
		}

		try (FileOutputStream archivo = new FileOutputStream(rutaArchivo)) {
			Document documento = new Document();
			PdfWriter.getInstance(documento, archivo);
			documento.open();
			documento.add(new Paragraph("Listado de las playlists del usuario " + nick));

			for (PlayList playlist : playlists) {
				documento.add(new Paragraph("\nNombre de la playlist: " + playlist.getNombre()));

				PdfPTable tabla = new PdfPTable(3);
				tabla.addCell("Titulo");
				tabla.addCell("Int�rprete");
				tabla.addCell("Estilo");

				for (Cancion cancion : playlist.getCanciones()) {
					tabla.addCell(cancion.getTitulo());
					tabla.addCell(cancion.getInterprete());
					tabla.addCell(cancion.getEstilo());
				}

				documento.add(tabla);
			}

			documento.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
