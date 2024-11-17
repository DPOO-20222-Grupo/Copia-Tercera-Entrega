package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import actividades.Encuesta;
import exceptions.ModificarPreguntasAbiertasException;
import preguntas.PreguntaAbierta;
import user.Profesor;

class EncuestaTest {
	private Encuesta encuesta;
	private List<PreguntaAbierta> preguntas;
	private PreguntaAbierta pregunta1;
	private PreguntaAbierta pregunta2;
	
	
	@BeforeEach
	void setUp() {
		
		Calendar calendario = Calendar.getInstance();
		calendario.set(Calendar.YEAR, 2000);
		calendario.set(Calendar.MONTH, Calendar.DECEMBER);
		calendario.set(Calendar.DAY_OF_MONTH, 2);
		
		Date fecha = calendario.getTime();
		
		preguntas = new ArrayList<PreguntaAbierta>();
		
		pregunta1 = new PreguntaAbierta("enunciado1", "titulo1");
		pregunta2 = new PreguntaAbierta("enunciado2", "titulo2");
		
		preguntas.add(pregunta1);
		
		Profesor profesor = new Profesor("login", "password", "nombre");
		
		List<String> objetivos = new ArrayList<String>();
		encuesta = new Encuesta("Coevaluación", "Coevaluación taller 3.", objetivos, "Ninguno", 120,
				fecha, profesor, preguntas);
	}

	@Test
	void testPreguntas() throws ModificarPreguntasAbiertasException {
		assertEquals(1, encuesta.getNumPreguntas());
		encuesta.agregarPregunta(pregunta2);
		assertTrue(encuesta.getPreguntas().contains(pregunta2));
		assertThrows(ModificarPreguntasAbiertasException.class, ()-> encuesta.agregarPregunta(pregunta2));
		encuesta.eliminarPregunta(pregunta2);
		assertFalse(encuesta.getPreguntas().contains(pregunta2));
		assertThrows(ModificarPreguntasAbiertasException.class, ()-> encuesta.eliminarPregunta(pregunta2));

	}

}
