package umu.tds.pruebas;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;
import umu.tds.controlador.AppMusic;

public class TestAppMusic {

	AppMusic am = AppMusic.getUnicaInstancia();

	// TODO evitar que un usuario tenga espacio en su nick
	@Test
	public void testLoginUsuario() {
		boolean resultadoEspero = true;
		assertEquals("Resultado login usuario", resultadoEspero, am.loginUsuario("a a", "a"));
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
	public void testRegistrarCancion() {
		fail("Not yet implemented");
	}

	@Test
	public void testBorrarUsuario() {
		fail("Not yet implemented");
	}

	@Test
	public void testReproducirCancion() {
		fail("Not yet implemented");
	}

	@Test
	public void testBuscarCancion() {
		fail("Not yet implemented");
	}

	@Test
	public void testCargarCanciones() {
		fail("Not yet implemented");
	}

}
