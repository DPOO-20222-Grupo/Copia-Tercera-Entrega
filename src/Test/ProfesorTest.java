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
import preguntas.PreguntaBoolean;
import preguntas.PreguntaCerrada;
import preguntas.PreguntaSeleccionMultiple;
import user.Profesor;

class ProfesorTest {
	private Profesor profesor;
	private LearningPath lp;

	@BeforeEach
	void setUp() {
		
		profesor = new Profesor("login", "password", "nombre");
				
		ArrayList<String> objLearningPath = new ArrayList<String>();
		
		HashMap<String, Boolean> mapaObligatoriedad = new HashMap<String, Boolean>();
		
		ArrayList<Actividad> listaActividades = new ArrayList<Actividad>();
				
		lp = new LearningPath("titulo", "descripción", objLearningPath, "nivel de dificultad", profesor, listaActividades, mapaObligatoriedad);

	}
	
    @Test
    void testConstructor() {
        assertEquals("nombre", profesor.getNombre(), "El nombre no fue inicializado correctamente.");
        assertEquals("Profesor", profesor.getTipo(), "El tipo no fue inicializado correctamente.");
        assertNotNull(profesor.getMapaEncuestasPropias(), "El mapa de encuestas no fue inicializado.");
        assertNotNull(profesor.getMapaTareasPropias(), "El mapa de tareas no fue inicializado.");
        assertNotNull(profesor.getMapaExamenesPropios(), "El mapa de exámenes no fue inicializado.");
        assertNotNull(profesor.getMapaQuicesPropios(), "El mapa de quizzes no fue inicializado.");
        assertNotNull(profesor.getMapaRecursosPropios(), "El mapa de recursos no fue inicializado.");
        assertNotNull(profesor.getLearningPathPropios(), "El mapa de learning paths no fue inicializado.");
        assertNotNull(profesor.getPreguntasAbiertasPropias(), "El mapa de preguntas abiertas no fue inicializado.");
        assertNotNull(profesor.getPreguntasSeleccionPropias(), "El mapa de preguntas de selección múltiple no fue inicializado.");
        assertNotNull(profesor.getPreguntasBooleanPropias(), "El mapa de preguntas booleanas no fue inicializado.");
    }
	
	@Test
	void testSetNombre() {
		profesor.setNombre("nombre2");
		assertEquals("nombre2", profesor.getNombre());
	}

    @Test
    void testRegistrarActividad() {
    	Date fecha = new Date();
    	List<String> objetivos = new ArrayList<String>();
    	List<PreguntaAbierta> preguntasAbiertas = new ArrayList<PreguntaAbierta>();
    	List<PreguntaCerrada> preguntasCerradas = new ArrayList<PreguntaCerrada>();
		Encuesta encuesta = new Encuesta("encuesta1", "descripción", objetivos, "nivel de dificultad", 0, fecha, profesor, preguntasAbiertas);

        profesor.registrarActividad(encuesta);
        assertTrue(profesor.getMapaEncuestasPropias().containsKey(encuesta.getIdActividad()), "La encuesta no fue registrada correctamente.");
        
        Tarea tarea = new Tarea("tarea1", "descripción", objetivos, "nivel de dificultad", 0, fecha, profesor);
        profesor.registrarActividad(tarea);
        assertTrue(profesor.getMapaTareasPropias().containsKey(tarea.getIdActividad()), "La tarea no fue registrada correctamente.");
        
        Quiz quiz = new Quiz("quiz1", "descripción", objetivos, "nivel de dificultad", 0, fecha, profesor, 3.0f, preguntasCerradas);
        profesor.registrarActividad(quiz);
        assertTrue(profesor.getMapaQuicesPropios().containsKey(quiz.getIdActividad()), "El quiz no fue registrado correctamente.");
        
        Examen examen = new Examen("examen1", "descripción", objetivos, "nivel de dificultad", 0, fecha, profesor, preguntasAbiertas);
        profesor.registrarActividad(examen);
        assertTrue(profesor.getMapaExamenesPropios().containsKey(examen.getIdActividad()), "El examen no fue registrado correctamente.");
        
        RevisarRecurso recurso = new RevisarRecurso("recurso1", "descripción", objetivos, "nivel de dificultad", 0, fecha, "tipo de recurso", profesor, "enlace");
        profesor.registrarActividad(recurso);
        assertTrue(profesor.getMapaRecursosPropios().containsKey(recurso.getIdActividad()), "El recurso no fue registrado correctamente.");
    }
    
    @Test
    void testRegistrarPregunta() {
        PreguntaAbierta pAbierta = new PreguntaAbierta("enunciado", "pAbierta1");
        profesor.registrarPregunta(pAbierta);
        assertTrue(profesor.getPreguntasAbiertasPropias().containsKey(pAbierta.getIdPregunta()), "La pregunta abierta no fue registrada correctamente.");
        
        PreguntaSeleccionMultiple pSM = new PreguntaSeleccionMultiple("enunciado", "pSM1", "opcion1", "opcion2", "opcion3", "opcion4", 1);
        profesor.registrarPregunta(pSM);
        assertTrue(profesor.getPreguntasSeleccionPropias().containsKey(pSM.getIdPregunta()), "La pregunta de selección múltiple no fue registrada correctamente.");
        
        PreguntaBoolean pBool = new PreguntaBoolean("enunciado", "pB1", 1);
        profesor.registrarPregunta(pBool);
        assertTrue(profesor.getPreguntasBooleanPropias().containsKey(pBool.getIdPregunta()), "La pregunta booleana no fue registrada correctamente.");
    }
    
    @Test
    void testRegistrarLearningPath() {
        profesor.registrarLearningPath(lp);        
        assertTrue(profesor.getLearningPathPropios().containsKey(lp.getIdLearnginPath()), "El learning path no fue registrado correctamente.");
        assertEquals(lp, profesor.getLearningPathPropios().get(lp.getIdLearnginPath()), "El learning path registrado no coincide.");
    }
	

}
