package Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import actividades.Actividad;
import actividades.Encuesta;
import actividades.Examen;
import actividades.Quiz;
import actividades.RevisarRecurso;
import actividades.Tarea;
import exceptions.ActividadYaExistenteException;
import exceptions.LearningPathYaExistenteException;
import exceptions.ModificarObjetivosException;
import interfaz.Aplicacion;
import learningPath.LearningPath;
import preguntas.PreguntaAbierta;
import preguntas.PreguntaBoolean;
import preguntas.PreguntaCerrada;
import preguntas.PreguntaSeleccionMultiple;
import user.Estudiante;
import user.Profesor;

public class AplicacionTest {
	
	private Estudiante estudiante;
	private Profesor profesor;
	private PreguntaAbierta preguntaAbierta;
	private PreguntaSeleccionMultiple preguntaSeleccion;
	private PreguntaBoolean preguntaBoolean;
	private Encuesta encuesta;
	private Quiz quiz;
	private Tarea tarea;
	private Examen examen;
	private RevisarRecurso recurso;
	private Aplicacion app;
	private LearningPath learningPath;
	
	@BeforeEach
    void setUp() {
		List<String> objetivos = new ArrayList<String>();
		objetivos.add("Objetivo 1");
		objetivos.add("Objetivo 2");
		
		PreguntaAbierta pregAbierta = new PreguntaAbierta("Ingrese su nombre: ", "Registro de Nombres");
		PreguntaSeleccionMultiple pregCerrada = new PreguntaSeleccionMultiple("¿Qué color no es primario?", "Colores", "Azul", "Rojo", "Amarillo", "Negro", 4);
		PreguntaBoolean pregBoolean = new PreguntaBoolean("¿El verde es la combinación del rojo y el azul?", "Verde, Rojo y Azul", 0);
		
		this.preguntaAbierta = pregAbierta;
		this.preguntaSeleccion = pregCerrada;
		this.preguntaBoolean = pregBoolean;
		List<PreguntaAbierta> preguntasAbiertas = new ArrayList<PreguntaAbierta>();
		preguntasAbiertas.add(pregAbierta);
		List<PreguntaCerrada> preguntasCerradas = new ArrayList<PreguntaCerrada>();
		preguntasCerradas.add(pregBoolean);
		preguntasCerradas.add(pregCerrada);
		
		Date date = new Date();
		

		
		
		
        this.app = new Aplicacion();
        this.estudiante = new Estudiante("user123", "pass123", "Juan Pérez");
        this.profesor = new Profesor("prof123", "pass123", "Dra. Gómez");
        
        this.encuesta = new Encuesta("Autoevaluación", "Autoevaluación acitividad #1", objetivos, "Bajo", 15, date,
				 profesor, preguntasAbiertas);
		this.quiz = new Quiz ("Quiz", "Primer Quiz", objetivos, "bajo", 45, date, profesor, (float) 3.5, preguntasCerradas);
		
		this.tarea = new Tarea("Tarea", "Tarea numero uno", objetivos, "bajo", 120, date, profesor);
		
		this.examen = new Examen("Parcial", "Examen Parcial", objetivos, "medio", 120, date, profesor, preguntasAbiertas);
		
		this.recurso  = new RevisarRecurso("Ver video", "Ver video segunda guerra mundial", objetivos, "bajo", 45,
				date, "video", profesor, "enlace");
		LearningPath lp = new LearningPath(
                "Lógica Intermedia",
                "Desarrollar habilidades de lógica más avanzadas",
                List.of("Resolver problemas complejos"),
                "Intermedio",
                profesor,
                List.of(),
                new HashMap<>());
		
		this.learningPath = lp;
    }

    @Test
    void testRegistrarUsuario() {
        
    	
		
        app.registrarUsuario(estudiante);
        app.registrarUsuario(profesor);

        assertNotNull(app.getEstudiante("user123"), "El estudiante debería estar registrado.");
        assertNotNull(app.getProfesor("prof123"), "El profesor debería estar registrado.");
    }

    @Test
    void testRegistrarActividad() {

        app.registrarActividad(tarea);
        app.registrarActividad(encuesta);
        app.registrarActividad(examen);
        app.registrarActividad(quiz);
        app.registrarActividad(recurso);

        assertEquals(tarea, app.getActividad(tarea.getIdActividad(), "Tarea"),
                "La tarea debería estar registrada en el sistema.");
        assertEquals(encuesta, app.getActividad(encuesta.getIdActividad(), "Encuesta"),
                "La tarea debería estar registrada en el sistema.");
        assertEquals(examen, app.getActividad(examen.getIdActividad(), "Examen"),
                "La tarea debería estar registrada en el sistema.");
        assertEquals(quiz, app.getActividad(quiz.getIdActividad(), "Quiz"),
                "La tarea debería estar registrada en el sistema.");
        assertEquals(recurso, app.getActividad(recurso.getIdActividad(), "Recurso"),
                "La tarea debería estar registrada en el sistema.");
        
    }

    @Test
    void testCrearLearningPath() {
        Profesor profesor = new Profesor("profLP123", "passLP", "Dra. López");
        List<String> objetivos = List.of("Aprender lógica básica");
        List<Actividad> actividades = new ArrayList<>();

        actividades.add(tarea);

        Map<String, Boolean> mapaObligatoriedad = new HashMap<>();
        mapaObligatoriedad.put(tarea.getIdActividad(), true);

        app.crearLearningPath(
                "Lógica para principiantes",
                "Aprender los fundamentos de la lógica",
                objetivos,
                "Básico",
                profesor,
                actividades,
                mapaObligatoriedad
        );

        LearningPath lp = app.getLearningPath("Lógica para principiantes - profLP123");
        assertNotNull(lp, "El Learning Path debería estar registrado.");
        assertEquals("Lógica para principiantes", lp.getTitulo(), "El título del Learning Path debería coincidir.");
        assertEquals(tarea, lp.getActividades().get(0), "El Learning Path deberia tener la actividad 'Tarea'");
    }
    
    @Test
    public void testRegistrarPregunta() {
    	
    	app.registrarPregunta(preguntaAbierta);
    	app.registrarPregunta(preguntaSeleccion);
    	app.registrarPregunta(preguntaBoolean);
    	
    	assertEquals(preguntaAbierta, app.getPregunta(preguntaAbierta.getIdPregunta(), "Abierta"), "La pregunta deberia guardarse en la aplicacion");
    	assertEquals(preguntaSeleccion, app.getPregunta(preguntaSeleccion.getIdPregunta(), "Cerrada"), "La pregunta deberia guardarse en la aplicacion");
    	assertEquals(preguntaBoolean, app.getPregunta(preguntaBoolean.getIdPregunta(), "Verdadero o Falso"), "La pregunta deberia guardarse en la aplicacion");
    }

    @Test
    void testInscribirEstudianteLearningPath() throws Exception {
        Estudiante estudiante = new Estudiante("userLP123", "pass123", "Juan Pérez");
        Profesor profesor = new Profesor("profLP456", "pass456", "Dra. Gómez");
        LearningPath lp = new LearningPath(
                "Lógica Intermedia",
                "Desarrollar habilidades de lógica más avanzadas",
                List.of("Resolver problemas complejos"),
                "Intermedio",
                profesor,
                List.of(),
                new HashMap<>()
        );

        app.registrarUsuario(estudiante);
        app.registrarLearningPath(lp);

        app.inscribirEstudianteLearningPath(estudiante, lp);

        assertTrue(lp.getEstudiantesInscritos().containsKey(estudiante.getLogin()),
                "El estudiante debería estar inscrito en el Learning Path.");
        assertTrue(estudiante.getLearningPathsInscritos().containsKey(lp.getIdLearnginPath()),
                "El estudiante debería tener el seguimiento del Learning Path.");
    }

    @Test
    void testModificarTituloActividad() throws ModificarObjetivosException {

    	app.registrarUsuario(profesor);
        app.registrarActividad(tarea);

        app.modificarActividad(tarea, "Tarea Modificada", "Titulo", null, null, null);

        assertEquals("Tarea Modificada", tarea.getTitulo(),
                "El título de la actividad debería actualizarse.");
    }
    
    @Test
    public void testActividadRepetida() {
    	app.registrarActividad(tarea);
        app.registrarActividad(encuesta);
        app.registrarActividad(examen);
        app.registrarActividad(quiz);
        app.registrarActividad(recurso);
        
        assertThrows(ActividadYaExistenteException.class, ()-> app.revisarActividadRepetida("Tarea", "prof123", "Tarea"));
        assertThrows(ActividadYaExistenteException.class, ()-> app.revisarActividadRepetida("Quiz", "prof123", "Quiz"));
        assertThrows(ActividadYaExistenteException.class, ()-> app.revisarActividadRepetida("Parcial", "prof123", "Examen"));
        assertThrows(ActividadYaExistenteException.class, ()-> app.revisarActividadRepetida("Ver video", "prof123", "Recurso"));
        assertThrows(ActividadYaExistenteException.class, ()-> app.revisarActividadRepetida("Autoevaluación", "prof123", "Encuesta"));
        
    }
    
    @Test
    public void testLPRepetido() {
    	app.registrarLearningPath(learningPath);
    	assertThrows(LearningPathYaExistenteException.class, ()-> app.revisarLearningPathRepetido("Lógica Intermedia", "prof123"));
    }
    
    @Test
    public void testCrearUsuario() {
    	
    	
    	app.crearEstudiante(estudiante.getLogin(),estudiante.getPassword(), estudiante.getNombre());
    	app.crearProfesor(profesor.getLogin(), profesor.getPassword(), profesor.getNombre());
    	
    	assertNotNull(app.getProfesor(profesor.getLogin()));
    	assertNotNull(app.getEstudiante(estudiante.getLogin()));
    	
    }
    
    @Test 
    public void testCrearActividad() {
    	app.crearRevisarRecurso(recurso.getTitulo(), recurso.getDescripcion(), recurso.getObjetivos(), recurso.getNivelDificultad(), recurso.getDuracionMinutos(), recurso.getFechaLimite(), recurso.getTipoRecurso(), profesor, recurso.getEnlaceRecurso());
    	app.crearEncuesta(encuesta.getTitulo(), encuesta.getDescripcion(), encuesta.getObjetivos(), encuesta.getNivelDificultad(), encuesta.getDuracionMinutos(), encuesta.getFechaLimite(), profesor, encuesta.getPreguntas());
    	app.crearExamen(examen.getTitulo(), examen.getDescripcion(), examen.getObjetivos(), examen.getNivelDificultad(), examen.getDuracionMinutos(), examen.getFechaLimite(), profesor, examen.getPreguntas());
    	app.crearTarea(tarea.getTitulo(), tarea.getDescripcion(), tarea.getObjetivos(), tarea.getNivelDificultad(), tarea.getDuracionMinutos(), tarea.getFechaLimite(), profesor);
    	app.crearQuiz(quiz.getTitulo(), quiz.getDescripcion(), quiz.getObjetivos(), quiz.getNivelDificultad(), quiz.getDuracionMinutos(), quiz.getFechaLimite(), profesor,quiz.getCalificacionMinima(),quiz.getPreguntas());
    	
    	assertNotNull(app.getActividad(recurso.getIdActividad(), "Recurso"), "Actividad no fue registrada correctamente");
    	assertNotNull(profesor.getMapaRecursosPropios().get(recurso.getIdActividad()), "Actividad no fue registrada correctamente" );
    	
    	assertNotNull(app.getActividad(encuesta.getIdActividad(), "Encuesta"), "Actividad no fue registrada correctamente");
    	assertNotNull(profesor.getMapaEncuestasPropias().get(encuesta.getIdActividad()), "Actividad no fue registrada correctamente" );
    	
    	assertNotNull(app.getActividad(quiz.getIdActividad(), "Quiz"), "Actividad no fue registrada correctamente");
    	assertNotNull(profesor.getMapaQuicesPropios().get(quiz.getIdActividad()), "Actividad no fue registrada correctamente" );
    	
    	assertNotNull(app.getActividad(examen.getIdActividad(), "Examen"), "Actividad no fue registrada correctamente");
    	assertNotNull(profesor.getMapaExamenesPropios().get(examen.getIdActividad()), "Actividad no fue registrada correctamente" );
    	
    	assertNotNull(app.getActividad(tarea.getIdActividad(), "Tarea"), "Actividad no fue registrada correctamente");
    	assertNotNull(profesor.getMapaTareasPropias().get(tarea.getIdActividad()), "Actividad no fue registrada correctamente" );
    	
    }
}


