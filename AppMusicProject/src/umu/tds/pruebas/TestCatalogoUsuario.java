package umu.tds.pruebas;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import umu.tds.modelo.CatalogoUsuarios;
import umu.tds.modelo.Usuario;
import umu.tds.persistencia.DAOException;

public class TestCatalogoUsuario {

	CatalogoUsuarios cu = CatalogoUsuarios.getUnicaInstancia();

//	@Before
//	public void setUp() {
//		cu = CatalogoUsuarios.getUnicaInstancia();
//		// Vaciamos el catálogo en caso de estar lleno
//		try {
//			cu.getAllUsuarios().clear();
//		} catch (DAOException e) {
//			e.printStackTrace();
//		}
//	}

	@Test
	public void testAddUsuario() {
		Usuario user = new Usuario("User", "user", "user", LocalDate.now());

		cu.addUsuario(user);

		Usuario userCatalogo = cu.getUsuario("User");
		assertNotNull("El usuario debe estar dentro del catálogo", userCatalogo);
		assertEquals("Ambos usuarios deben ser iguales", user, userCatalogo);
	}

	@Test
	public void testRemoveUsuario() {
		Usuario user = new Usuario("User", "user", "user", LocalDate.now());

		cu.addUsuario(user);

		// Comprobamos que antes de eliminar se ha podido añadir sin problema
		Usuario userCatalogo = cu.getUsuario("User");
		assertNotNull("El usuario debe estar dentro del catálogo", userCatalogo);

		cu.removeUsuario(user);

		Usuario userEliminado = cu.getUsuario("User");
		assertNull("El usuario no debe estar dentro del catálogo", userEliminado);
	}

	@Test
	public void testGetAllUsuarios() throws DAOException {
		int resultadoEsperado = 2;
		Usuario user1 = new Usuario("User1", "user1", "user", LocalDate.now());
		Usuario user2 = new Usuario("User2", "user2", "user", LocalDate.now());

		cu.addUsuario(user1);
		cu.addUsuario(user2);

		List<Usuario> listaUsuarios = cu.getAllUsuarios();

		assertEquals("Ambos usuarios deben estar presentes en la lista", resultadoEsperado, listaUsuarios.size());
	}
	
	@Test
	public void testAltaPremium() {
		Usuario user = new Usuario("User", "user", "user", LocalDate.now());

		cu.addUsuario(user);
		
		assertFalse("Un usuario recien creado no debe ser premium", user.isPremium());
		
		cu.altaPremium(user);
		
		assertTrue("El usuario debe ser premium", user.isPremium());
	}
	
	@Test
	public void testCompleto() throws DAOException {
		Usuario user1 = new Usuario("User1", "user1", "user", LocalDate.now());
		Usuario user2 = new Usuario("User2", "user2", "user", LocalDate.now());

		cu.addUsuario(user1);
		cu.addUsuario(user2);
		
		cu.altaPremium(user1);
		cu.altaPremium(user2);
		
		List<Usuario> listaUsuarios = cu.getAllUsuarios();
		
		for (Usuario usuario : listaUsuarios) {
			assertTrue("El usuario debe ser premium", usuario.isPremium());
		}
		
		cu.removeUsuario(user1);
		cu.removeUsuario(user2);
		
		Usuario user1Eliminado = cu.getUsuario("User1");
		Usuario user2Eliminado = cu.getUsuario("User2");
		
		assertNull("El usuario no debe estar dentro del catálogo", user1Eliminado);
		assertNull("El usuario no debe estar dentro del catálogo", user2Eliminado);
	}
}
