package umu.tds.componente;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class MapperCancionesXMLtoJava {

	public static Canciones cargarCanciones(String fichero) throws URISyntaxException {

		JAXBContext jc;
		Canciones canciones = null;

		try {
			jc = JAXBContext.newInstance("umu.tds.componente");
			Unmarshaller u = jc.createUnmarshaller();
			URL resourceUrl = MapperCancionesXMLtoJava.class.getClassLoader().getResource(fichero);
			Path path = Paths.get(resourceUrl.toURI());
			File file = path.toFile();
			canciones = (Canciones) u.unmarshal(file);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return canciones;
	}
}