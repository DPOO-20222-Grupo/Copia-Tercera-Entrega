package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import actividades.Quiz;
import exceptions.ModificarPreguntasQuizException;
import preguntas.PreguntaBoolean;
import preguntas.PreguntaCerrada;
import preguntas.PreguntaSeleccionMultiple;
import user.Profesor;

class QuizTest {
	private Quiz quiz1;
	private PreguntaSeleccionMultiple pregunta1;
	private PreguntaBoolean pregunta2;
	
	@BeforeEach
	void setUp() {
		pregunta1 = new PreguntaSeleccionMultiple("Enunciado1", "Titulo1", "opcion1", "opcion2", "opcion3", "opcion4", 2);
		pregunta2 = new PreguntaBoolean("Enunciado2", "Titulo2", 1);		
		Profesor profesor = new Profesor("c.espinosag", "password", "Cesar Espinosa");
		List<PreguntaCerrada> preguntas = new ArrayList<PreguntaCerrada>();
		List<String> objetivos = new ArrayList<String>();
		preguntas.add(pregunta1);
		Calendar calendario = Calendar.getInstance();
		calendario.set(Calendar.YEAR, 2000);
		calendario.set(Calendar.MONTH, Calendar.DECEMBER);
		calendario.set(Calendar.DAY_OF_MONTH, 2);
		
		Date fecha = calendario.getTime();
		
		quiz1 = new Quiz("Quiz1", "Primer quiz del curso.", objetivos, "Bajo", 120, fecha, profesor, 3.0f, preguntas);
	}

	@Test
	void testPreguntas() throws ModificarPreguntasQuizException {
		assertEquals(1, quiz1.getNumPreguntas());
		
		List<PreguntaCerrada> p = new ArrayList<PreguntaCerrada>();
		p.add(pregunta1);
		assertEquals(p, quiz1.getPreguntas());
		
		quiz1.agregarPregunta(pregunta2);
		assertEquals(pregunta2, quiz1.getPreguntas().getLast());
		assertThrows(ModificarPreguntasQuizException.class, () -> quiz1.agregarPregunta(pregunta2));
		
		quiz1.eliminarPregunta(pregunta2);
		assertEquals(p, quiz1.getPreguntas());
		assertThrows(ModificarPreguntasQuizException.class, () -> quiz1.eliminarPregunta(pregunta2));
		
	}
	
	@Test
	void testCalificacionMinima() {
		assertEquals(3.0, quiz1.getCalificacionMinima());
		quiz1.setCalificacionMinima(2.5f);
		assertEquals(2.5, quiz1.getCalificacionMinima());
	}
	
	@Test
	void testGetTipoActividad() {
		assertEquals("Quiz", quiz1.getTipoActividad());
	}

}
 