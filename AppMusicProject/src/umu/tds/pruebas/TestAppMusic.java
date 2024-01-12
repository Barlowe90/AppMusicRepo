package umu.tds.pruebas;

import static org.junit.Assert.*;
import java.time.LocalDate;
import java.util.List;

import org.junit.Test;
import umu.tds.controlador.AppMusic;
import umu.tds.exceptions.UsuarioDuplicadoException;
import umu.tds.modelo.Cancion;
import umu.tds.modelo.Usuario;
import umu.tds.persistencia.DAOException;

public class TestAppMusic {

	AppMusic am = AppMusic.getUnicaInstancia();

	// TODO evitar que un usuario tenga espacio en su nick
	@Test
	public void testLoginUsuario() {
		boolean resultadoEspero = true;
		try {
			am.registrarUsuario("Login", "login", "login", LocalDate.now());
		} catch (UsuarioDuplicadoException e) {
			e.printStackTrace();
		}
		assertEquals("Resultado login usuario", resultadoEspero, am.loginUsuario("Login", "login"));
	}

//	@Test
//	public void testRegistrarUsuario() {
//		boolean resultadoEspero = true;
//		String nick = "";
//		String pw = "";
//		String email = "";
//		LocalDate fechaNacimiento = LocalDate.now();
//		assertEquals("Resultado registrar usuario", resultadoEspero,
//				am.registrarUsuario(nick, pw, email, fechaNacimiento));
//	}
	
	@Test
    public void testRegistrarUsuario() throws DAOException{
        String nick = "Registro";
        String password = "registro";
        String email = "registro";
        LocalDate fecha = LocalDate.of(2024, 1, 1);
        try {
			am.registrarUsuario(nick, password, email, fecha);
		} catch (UsuarioDuplicadoException e) {
			e.printStackTrace();
		}

        // Verificar si el usuario se ha registrado correctamente.
        List<Usuario> usuariosRegistrados = am.getUsuarios();
        assertTrue("El usuario debe haberse registrado", usuariosRegistrados.stream()
                .anyMatch(u -> u.getNick().equals(nick) && u.getPassword().equals(password)
                        && u.getEmail().equals(email) && u.getFechaNacimiento().equals(fecha)));
    }

	@Test
	public void testRegistrarCancion() {
		String titulo = "Cancion";
		String interprete = "Interprete";
		String estilo = "Estilo";
		String rutaCancion = "src/utilidades.canciones/FIFTY_FIFTY_Cupid.mp3";
		am.registrarCancion(titulo, interprete, estilo, rutaCancion);

		// Verificar si la canción se registra correctamente
		List<Cancion> cancionesRegistradas = null;
		try {
			cancionesRegistradas = am.getCanciones();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		assertTrue("La canción debe haberse registrado",
				cancionesRegistradas.stream()
						.anyMatch(c -> c.getTitulo().equals(titulo) && c.getInterprete().equals(interprete)
								&& c.getEstilo().equals(estilo) && c.getURL().equals(rutaCancion)));

		// Comprobar que una misma cancion no puede registrarse dos veces
		am.registrarCancion(titulo, interprete, estilo, rutaCancion);
		List<Cancion> cancionesDuplicado = null;
		try {
			cancionesDuplicado = am.getCanciones();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		assertEquals("No debería registrarse la misma canción nuevamente", cancionesRegistradas.size(),
				cancionesDuplicado.size());
	}

	@Test
	public void testBorrarUsuario() {
		boolean resultadoEspero = true;
		try {
			am.registrarUsuario("User", "user", "user", LocalDate.now());
		} catch (UsuarioDuplicadoException e) {
			e.printStackTrace();
		}
		assertEquals("Resultado borrar usuario", resultadoEspero, am.borrarUsuario("User"));
	}

//	@Test
//	public void testReproducirCancion() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testBuscarCancion() throws DAOException {
		String titulo = "Cancion";
		String interprete = "Interprete";
		String estilo = "Estilo";
		String rutaCancion = "src/utilidades.canciones/FIFTY_FIFTY_Cupid.mp3";
		am.registrarCancion(titulo, interprete, estilo, rutaCancion);

        // Búsqueda por título
        List<Cancion> resultadosTitulo = am.buscarCancion("Cancion", null, null);
        assertEquals("Debería encontrarse una canción con 'Cancion' en el título", 1, resultadosTitulo.size());

        // Búsqueda por intérprete
        List<Cancion> resultadosInterprete = am.buscarCancion(null, "Interprete", null);
        assertEquals("Debería encontrarse una canción con 'Interprete' como intérprete", 1, resultadosInterprete.size());

        // Búsqueda por estilo
        List<Cancion> resultadosEstilo = am.buscarCancion(null, null, "Estilo");
        assertEquals("Debería encontrarse una canción con 'Estilo' como estilo", 1, resultadosEstilo.size());

        // Búsqueda combinada
        List<Cancion> resultadosCombinados = am.buscarCancion("Cancion", "Interprete", "Estilo");
        assertEquals("Debería encontrarse una canción con los criterios de búsqueda combinados", 1, resultadosCombinados.size());
	}

	@Test
	public void testCargarCanciones() {
        String rutaXML = "xml/canciones.xml";
        am.cargarCanciones(rutaXML);

        List<Cancion> cancionesCargadas = null;
		try {
			cancionesCargadas = am.getCanciones();
		} catch (DAOException e) {
			e.printStackTrace();
		}
        assertTrue("Debe tener al menos una canción", !cancionesCargadas.isEmpty());
	}

	@Test
	public void testRegistrarUsuarioDuplicado() throws UsuarioDuplicadoException {

		am.registrarUsuario("UsuarioDuplicado", "Pass", "email", LocalDate.now());
		try {
			am.registrarUsuario("UsuarioDuplicado", "Pass2", "email2", LocalDate.now());
			fail("No ha salido error sobre usuario duplicado.");
		} catch (UsuarioDuplicadoException e) {
			// Si se llega a esta excepcion, el test está OK
		}
	}

}
