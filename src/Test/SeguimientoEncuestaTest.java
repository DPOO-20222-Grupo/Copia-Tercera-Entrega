package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import actividades.Encuesta;
import preguntas.PreguntaAbierta;
import seguimientoEstudiantes.SeguimientoEncuesta;
import user.Estudiante;
import user.Profesor;

class SeguimientoEncuestaTest {
	private PreguntaAbierta pregunta1;
	private SeguimientoEncuesta seg;
	
	@BeforeEach
	void setUp() {
		
		Date fecha = new Date();
		
		List<PreguntaAbierta> preguntas = new ArrayList<PreguntaAbierta>();
		
		pregunta1 = new PreguntaAbierta("enunciado1", "titulo1");
		
		preguntas.add(pregunta1);
		
		Profesor profesor = new Profesor("login", "password", "nombre");
		
		List<String> objetivos = new ArrayList<String>();
		
		Encuesta encuesta = new Encuesta("titulo", "descripción", objetivos, "nivel de dificultad", 0,
				fecha, profesor, preguntas);
		
		Estudiante estudiante = new Estudiante("login", "password", "nombre");

		seg = new SeguimientoEncuesta(encuesta, estudiante);
	}
	
	@Test
	void testRegistrarPregunta() {
		seg.registrarPregunta(pregunta1, "respuesta");
		assertEquals("respuesta", seg.getRespuestas().get(pregunta1.getIdPregunta()));
	}

}
