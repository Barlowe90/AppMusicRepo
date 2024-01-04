package umu.tds.componente;

import java.io.File;

import org.eclipse.persistence.jaxb.JAXBContext;
import org.eclipse.persistence.exceptions.JAXBException;
import org.eclipse.persistence.internal.oxm.Unmarshaller;

public class MapperCancionesXMLtoJava {
	public static Canciones cargarCanciones(String fichero) {
		JAXBContext jc;
		Canciones canciones = null;
		try {
			jc = JAXBContext.newInstance("umu.tds.componente");
			Unmarshaller u = jc.createUnmarshaller();
			File file = new File(fichero);
			canciones = (Canciones) u.unmarshal(file);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
		return canciones;
	}
}
