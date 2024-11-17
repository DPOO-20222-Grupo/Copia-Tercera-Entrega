package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import preguntas.PreguntaAbierta;

class PreguntaTest {

	private PreguntaAbierta pregunta;

	@BeforeEach
	void setUp() {
	    pregunta = new PreguntaAbierta("enunciado", "titulo");
	}

	@Test
	void testConstructor() {
	    assertEquals("enunciado", pregunta.getEnunciado());
	    assertEquals("titulo", pregunta.getTitulo());
	}

	@Test
	void testSettersAndGetters() {
		pregunta.setEnunciado("enunciado2");
	    assertEquals("enunciado2", pregunta.getEnunciado());

	    pregunta.setTitulo("titulo2");
	    assertEquals("titulo2", pregunta.getTitulo());
	}

	@Test
	void testGetTipo() {
	    assertEquals("Abierta", pregunta.getTipo());
	}

	@Test
	void testGetIdPregunta() {
		assertEquals(Integer.toString(pregunta.getId())+ " - " + "titulo", pregunta.getIdPregunta());
	}

}
