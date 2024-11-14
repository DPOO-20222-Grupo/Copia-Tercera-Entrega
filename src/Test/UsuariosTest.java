package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import user.Estudiante;
import user.Profesor;

class UsuariosTest {
	
	private static Estudiante est1;
	private static Profesor prof1;
	
	@BeforeEach
	void SetUp() {
		est1 = new Estudiante("login", "password", "nombre");
		prof1 = new Profesor("login", "password", "nombre");
	}
	
	@Test
	void testgetNombreProfesor() {
		assertEquals("nombre", prof1.getNombre(), "El nombre obtenido no es igual al esperado");
	}

	@Test
	void testgetNombreEstudiante() {
		assertEquals("nombre", est1.getNombre(), "El nombre obtenido no es igual al esperado");
	}
	
	@Test
	void testTipoEstudiante() {
		assertEquals("Estudiante", est1.getTipo(), "El tipo obtenido no es Estudiante");
	}
	
	@Test
	void testTipoProfesor() {
		assertEquals("Profesor", prof1.getTipo(), "El tipo obtenido no es Profesor");
	}

	
}
