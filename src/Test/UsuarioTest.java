package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import user.Estudiante;

class UsuarioTest {
	
	private static Estudiante est1;
	
	@BeforeEach
	void SetUp() {
		est1 = new Estudiante("login", "password", "nombre");
	}
	
	@Test
	void testGetsAndSets() {
		assertEquals("login", est1.getLogin(), "El login obtenido no es igual al esperado.");
		est1.setLogin("login2");
		assertEquals("login2", est1.getLogin(), "El login no fue seteado correctamente.");

		assertEquals("password", est1.getPassword(), "La contraseña obtenida no es igual a la esperada");
		est1.setPassword("password2");
		assertEquals("login2", est1.getLogin(), "El login no fue seteado correctamente.");

	}

	@Test
	void testLogIn() {
		assertFalse(est1.login("login", "wrong password"));
		assertFalse(est1.login("wrong login", "password"));
		assertTrue(est1.login("login", "password"));
		assertTrue(est1.isLoggedIn());

	}
	
	@Test
	void testLogOutWhenLogInTrue() {
		est1.login("login", "password");

		ByteArrayOutputStream salida = new ByteArrayOutputStream();
		PrintStream stream = System.out;
		System.setOut(new PrintStream(salida));
		
		est1.logout();
		assertFalse(est1.isLoggedIn(), "La sesión no ha sido cerrada con éxito.");
		
		assertEquals("Sesión cerrada exitosamente." + System.lineSeparator(), salida.toString(), 
		             "El mensaje de salida al cerrar sesión no es el esperado.");
		
		System.setOut(stream);
		
	}
	
	@Test
	void testLogOutWhenLogInFalse() {

		ByteArrayOutputStream salida = new ByteArrayOutputStream();
		PrintStream stream = System.out;
		System.setOut(new PrintStream(salida));
		
		est1.logout();
		assertFalse(est1.isLoggedIn(), "La sesión no debería estar abierta.");
		
		assertEquals("No hay una sesión activa." + System.lineSeparator(), salida.toString(), 
		             "El mensaje de salida al cerrar sesión, sin tener una sesión abierta, no es el esperado.");
		
		System.setOut(stream);
		
	}
	
}
