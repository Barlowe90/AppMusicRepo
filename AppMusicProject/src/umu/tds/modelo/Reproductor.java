package umu.tds.modelo;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Reproductor {

	private String cancionActual;
	private static Reproductor unicaInstancia;

	private MediaPlayer mediaPlayer;
	private String tempPath;

	private List<MediaPlayer> reproductores = new ArrayList<>();

	private Reproductor() {
		mediaPlayer = null;
		tempPath = System.getProperty("user.dir") + "/temp";

		// Asegurar que el directorio exista, si no, intentar crearlo
		File directorio = new File(tempPath);
		if (!directorio.exists()) {
			boolean creado = directorio.mkdirs();
			if (!creado) {
				System.err.println("No se pudo crear el directorio: " + tempPath);
			}
		}
	}

	public static Reproductor getUnicaInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new Reproductor();
		return unicaInstancia;
	}

	public void playCancion(String url) {
		URL uri = null;
		try {
			com.sun.javafx.application.PlatformImpl.startup(() -> {
			});

			uri = new URL(url);

			System.setProperty("java.io.tmpdir", tempPath);
			Path mp3 = Files.createTempFile("now-playing", ".mp3");

			System.out.println(mp3.getFileName());
			try (InputStream stream = uri.openStream()) {
				Files.copy(stream, mp3, StandardCopyOption.REPLACE_EXISTING);
			}
			System.out.println("finished-copy: " + mp3.getFileName());

			Media media = new Media(mp3.toFile().toURI().toString());
			mediaPlayer = new MediaPlayer(media);

			mediaPlayer.play();

			reproductores.add(mediaPlayer);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void stopCancion() {
		if (mediaPlayer != null)
			mediaPlayer.stop();
		File directorio = new File(tempPath);
		String[] files = directorio.list();
		for (String archivo : files) {
			File fichero = new File(tempPath + File.separator + archivo);
			fichero.delete();
		}
	}

	public void pauseCancion() {
		if (mediaPlayer != null) {
			MediaPlayer.Status status = mediaPlayer.getStatus();

			if (status == MediaPlayer.Status.PLAYING) {
				mediaPlayer.pause();
			} else if (status == MediaPlayer.Status.PAUSED) {
				mediaPlayer.play();
			}
		}
	}

	public void stopAllCanciones() {
		for (MediaPlayer mediaPlayer : reproductores) {
			mediaPlayer.stop();
		}
		reproductores.clear();
	}

	public String getCancionActual() {
		return cancionActual;
	}

	public void setCancionActual(String cancionActual) {
		this.cancionActual = cancionActual;
	}

	@Override
	public String toString() {
		return "Reproductor [cancionActual=" + cancionActual + "]";
	}

}
