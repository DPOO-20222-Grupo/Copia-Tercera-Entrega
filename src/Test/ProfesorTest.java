package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import user.Profesor;

class ProfesorTest {

	private static Profesor prof1;
	
	@BeforeEach
	void SetUp() {
		prof1 = new Profesor("login", "password", "nombre");
	}
	
	@Test
	void testgetNombreProfesor() {
		assertEquals("nombre", prof1.getNombre(), "El nombre obtenido no es igual al esperado");
	}
	
	@Test
	void testTipoProfesor() {
		assertEquals("Profesor", prof1.getTipo(), "El tipo obtenido no es Profesor");
	}
}
