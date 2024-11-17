package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import actividades.Actividad;
import actividades.Encuesta;
import actividades.Examen;
import actividades.Quiz;
import actividades.RevisarRecurso;
import actividades.Tarea;
import learningPath.LearningPath;
import preguntas.PreguntaAbierta;
import preguntas.PreguntaCerrada;
import seguimientoEstudiantes.SeguimientoLearningPath;
import user.Estudiante;
import user.Profesor;

class SeguimientoLearningPathTest {
	
	private SeguimientoLearningPath seg;
	private Quiz quiz;
	private RevisarRecurso recurso;
	private Tarea tarea;
	private Examen examen;
	private Encuesta encuesta;
	
	
	@BeforeEach
	void setUp() {
		
		Profesor profesor = new Profesor("login", "password", "nombre");
		List<PreguntaCerrada> preguntasCerradas = new ArrayList<PreguntaCerrada>();
		List<PreguntaAbierta> preguntasAbiertas = new ArrayList<PreguntaAbierta>();
		List<String> objetivos = new ArrayList<String>();
		
		Date fecha = new Date();
		
		quiz = new Quiz("titulo", "descripción", objetivos, "nivel de dificultad", 0, fecha, profesor, 3.0f, preguntasCerradas);
		recurso = new RevisarRecurso("titulo", "descripción", objetivos, "nivel de dificultad", 0, fecha, "tipo de recurso",
				profesor, "enlace");
		examen = new Examen("titulo", "descripción", objetivos, "nivel de dificultad", 0, fecha, profesor, preguntasAbiertas);
		encuesta = new Encuesta("titulo", "descripción", objetivos, "nivel de dificultad", 0, fecha, profesor, preguntasAbiertas);
		tarea = new Tarea("titulo", "descripción", objetivos, "nivel de dificultad", 0, fecha, profesor);
		
		ArrayList<String> objLearningPath = new ArrayList<String>();
		
		HashMap<String, Boolean> mapaObligatoriedad = new HashMap<String, Boolean>();
		
		ArrayList<Actividad> listaActividades = new ArrayList<Actividad>();
		listaActividades.add(quiz);
		listaActividades.add(recurso);
		listaActividades.add(examen);
		listaActividades.add(encuesta);
		listaActividades.add(tarea);
				
		LearningPath learningPath = new LearningPath("titulo", "descripción", objLearningPath, "nivel de dificultad", profesor, listaActividades, mapaObligatoriedad);
		
		Estudiante estudiante = new Estudiante("login", "password", "descripción");
		
		seg = new SeguimientoLearningPath(learningPath, estudiante);
	}
	
	@Test
	void test() {
	}

}
