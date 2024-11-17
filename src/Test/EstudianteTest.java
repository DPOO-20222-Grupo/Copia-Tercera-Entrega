package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import user.Estudiante;

class EstudianteTest {

	private Estudiante estudiante;
	private 
	
	@BeforeEach
	void SetUp() {
		estudiante = new Estudiante("login", "password", "nombre");
	}
	
	@Test
	void testNombre() {
		assertEquals("nombre", estudiante.getNombre(), "El nombre obtenido no es igual al esperado");
		estudiante.setNombre("nombre2");
		assertEquals("nombre2", estudiante.getNombre(), "El nombre no fue seteado correctamente.");
	}
	
	@Test
	void testTipoProfesor() {
		assertEquals("Profesor", prof1.getTipo(), "El tipo obtenido no es Profesor");
	}
}
