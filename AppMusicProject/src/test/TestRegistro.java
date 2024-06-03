package test;

import static org.junit.Assert.*;
import java.time.LocalDate;
import org.junit.Test;

import dam.controlador.AppMusic;
import dam.exceptions.UsuarioDuplicadoException;

import org.junit.Before;

public class TestRegistro {

	private AppMusic appMusic;

	@Before
	public void setUp() {
		appMusic = AppMusic.getUnicaInstancia();
	}

	@Test
	public void test() {
		String nick = "testUsario";
		String pw = "pw";
		String email = "test@test.com";
		LocalDate fecha = LocalDate.of(2000, 1, 1);

		try {
			appMusic.registrarUsuario(nick, pw, email, fecha);
			assertTrue(appMusic.isUsuarioRegistrado(nick));
		} catch (Exception e) {
			fail("Exception should not be thrown");
		}
	}

	@Test
	public void testRegistrarUsuarioDuplicado() {
		String nick = "testUsario";
		String pw = "pw";
		String email = "test@test.com";
		LocalDate fecha = LocalDate.of(2000, 1, 1);

		try {
			appMusic.registrarUsuario(nick, pw, email, fecha);
			appMusic.registrarUsuario(nick, pw, email, fecha);
			fail("UsuarioDuplicadoException should be thrown");
		} catch (UsuarioDuplicadoException e) {
			// OK
		} catch (Exception e) {
			fail("Unexpected exception thrown");
		}
	}

	@Test
	public void testRegistrarUsuarioConFechaNula() {
		String nick = "testUsario";
		String pw = "pw";
		String email = "test@test.com";
		LocalDate fecha = null;

		try {
			appMusic.registrarUsuario(nick, pw, email, fecha);
			fail("Exception should be thrown for null date");
		} catch (Exception e) {
			// OK
		}
	}

}
