package umu.tds.modelo;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import javax.print.attribute.standard.Media;

public class Reproductor {

	private Cancion cancionActual;
	private static Reproductor unicaInstancia;

	private Reproductor() {
	}

	public static Reproductor getUnicaInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new Reproductor();
		return unicaInstancia;
	}

//	public void reproducirCancion(String url) {
//		com.sun.javafx.application.PlatformImpl.startup(() -> {
//		});
//
//		try {
//			URL uri = new URL(url);
//			String tempPath = System.getProperty("java.io.tmpdir");
//			Path mp3 = Files.createTempFile("now-playing", ".mp3");
//
//			try (InputStream stream = uri.openStream()) {
//				Files.copy(stream, mp3, StandardCopyOption.REPLACE_EXISTING);
//			}
//
//			System.out.println("finished-copy: " + mp3.getFileName());
//
//			Media media = new Media(mp3.toFile().toURI().toString());
//			mediaPlayer = new MediaPlayer(media);
//			mediaPlayer.play();
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

//	public void stopCancion() {
//		if (mediaPlayer != null)
//			mediaPlayer.stop();
//		File directorio = new File(tempPath);
//		String[] files = directorio.list();
//		for (String archivo : files) {
//			File fichero = new File(tempPath + File.separator + archivo);
//			fichero.delete();
//		}
//	}

	public Cancion getCancionActual() {
		return cancionActual;
	}

	public void setCancionActual(Cancion cancionActual) {
		this.cancionActual = cancionActual;
	}

	@Override
	public String toString() {
		return "Reproductor [cancionActual=" + cancionActual + "]";
	}

}
