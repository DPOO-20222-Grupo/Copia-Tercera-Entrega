package Test;

import static org.junit.jupiter.api.Assertions.*;

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
import exceptions.UsuarioYaExistenteException;
import interfaz.Aplicacion;
import learningPath.LearningPath;
import preguntas.PreguntaAbierta;
import preguntas.PreguntaBoolean;
import preguntas.PreguntaCerrada;
import preguntas.PreguntaSeleccionMultiple;
import seguimientoEstudiantes.SeguimientoExamen;
import seguimientoEstudiantes.SeguimientoLearningPath;
import seguimientoEstudiantes.SeguimientoTarea;
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
        this.estudiante = new Estudiante("user123", "pass123", "nombre");
        this.profesor = new Profesor("prof123", "pass123", "nombre");
        
        this.encuesta = new Encuesta("Autoevaluación", "Autoevaluación acitividad #1", objetivos, "Bajo", 15, date,
				 profesor, preguntasAbiertas);
		this.quiz = new Quiz ("Quiz", "Primer Quiz", objetivos, "bajo", 45, date, profesor, (float) 3.5, preguntasCerradas);
		
		this.tarea = new Tarea("Tarea", "Tarea numero uno", objetivos, "bajo", 120, date, profesor);
		
		this.examen = new Examen("Parcial", "Examen Parcial", objetivos, "medio", 120, date, profesor, preguntasAbiertas);
		
		this.recurso  = new RevisarRecurso("Ver video", "Ver video segunda guerra mundial", objetivos, "bajo", 45,
				date, "video", profesor, "enlace");
		
		List<String> objetivosLP = new ArrayList<String>();
		
		LearningPath lp = new LearningPath(
                "Lógica Intermedia",
                "Desarrollar habilidades de lógica más avanzadas",
                objetivosLP,
                "Intermedio",
                profesor,
                new ArrayList<>(),
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
        List<String> objetivos = new ArrayList<String>();
        objetivos.add("Aprender lógica básica");
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
                new ArrayList<>(),
                "Intermedio",
                profesor,
                new ArrayList<>(),
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
        
        app.modificarActividad(tarea, "Nueva Descripción", "Descripcion", null, null, null);
        assertEquals("Nueva Descripción", tarea.getDescripcion(), "La descripción debería actualizarse.");

     
        app.modificarActividad(tarea, "Alto", "Dificultad", null, null, null);
        assertEquals("Alto", tarea.getNivelDificultad(), "El nivel de dificultad debería actualizarse.");

        app.modificarActividad(tarea, "Nuevo Objetivo", "Objetivos", "Agregar", null, null);
        assertTrue(tarea.getObjetivos().contains("Nuevo Objetivo"), "El objetivo debería ser agregado.");

        Date nuevaFecha = new Date(System.currentTimeMillis() + 86400000); // +1 día
        app.modificarActividad(tarea, null, "Fecha Limite", null, nuevaFecha, null);
        assertEquals(nuevaFecha, tarea.getFechaLimite(), "La fecha límite debería actualizarse.");

        app.modificarActividad(tarea, null, "Duracion", null, null, 180);
        assertEquals(180, tarea.getDuracionMinutos(), "La duración debería actualizarse.");

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
    void testRegistrarUsuarioRepetido() {
        app.registrarUsuario(estudiante);
        assertThrows(UsuarioYaExistenteException.class, 
                     () -> app.revisarUsuarioRepetido(estudiante.getLogin(), "Estudiante"));
    }
    
    @Test
    void testRegistrarPreguntaRepetida() {
        app.registrarPregunta(preguntaAbierta);
        assertNotNull(app.getPregunta(preguntaAbierta.getIdPregunta(), "Abierta"),
                      "La pregunta debería estar registrada.");
    }
    
    @Test
    void testModificarTituloPregunta() {
        app.registrarPregunta(preguntaAbierta);
        app.modificarTituloPregunta(preguntaAbierta, "Nuevo Título", profesor);

        assertEquals("Nuevo Título", preguntaAbierta.getTitulo(),
                     "El título de la pregunta debería actualizarse.");
    }

    @Test
    void testModificarEnunciadoPregunta() {
        app.registrarPregunta(preguntaSeleccion);
        app.modificarEnunciadoPregunta(preguntaSeleccion, "Nuevo Enunciado");

        assertEquals("Nuevo Enunciado", preguntaSeleccion.getEnunciado(),
                     "El enunciado de la pregunta debería actualizarse.");
    }
    
    @Test
    void testModificarAtributosLearningPath() throws Exception {
    	app.registrarUsuario(profesor); 
        app.registrarLearningPath(learningPath);

        app.modificarAtributosStringLearningPath(learningPath, "Titulo", "Nuevo Título", "");

        assertEquals("Nuevo Título", learningPath.getTitulo(),
                     "El título del Learning Path debería actualizarse.");

        app.modificarAtributosStringLearningPath(learningPath, "Descripcion", "Nueva Descripción", null);
        assertEquals("Nueva Descripción", learningPath.getDescripcion(), "La descripción debería actualizarse.");

        
        app.modificarAtributosStringLearningPath(learningPath, "Objetivos", "Nuevo Objetivo", "Agregar");
        assertTrue(learningPath.getObjetivos().contains("Nuevo Objetivo"), "El objetivo debería ser agregado.");

       
        app.modificarAtributosStringLearningPath(learningPath, "Objetivos", "Nuevo Objetivo", "Eliminar");
        assertFalse(learningPath.getObjetivos().contains("Nuevo Objetivo"), "El objetivo debería ser eliminado.");

    }
    
    @Test
    void testAgregarActividadLearningPath() throws Exception {
        app.registrarLearningPath(learningPath);
        app.modificarActividadesLearningPath(learningPath, tarea, true, "Agregar");

        assertTrue(learningPath.getActividades().contains(tarea),
                   "La actividad debería agregarse al Learning Path.");
    }
    
    @Test
    void testInscribirEstudianteEnLearningPath() throws Exception {
        app.registrarLearningPath(learningPath);
        app.inscribirEstudianteLearningPath(estudiante, learningPath);

        assertTrue(learningPath.getEstudiantesInscritos().containsKey(estudiante.getLogin()),
                   "El estudiante debería estar inscrito.");
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
    
    @Test
    void testGetLearningPath() {
        app.registrarLearningPath(learningPath);
        LearningPath lpObtenido = app.getLearningPath(learningPath.getIdLearnginPath());

        assertEquals(learningPath, lpObtenido, "Debería devolver el Learning Path registrado.");
    }
    
    @Test
    void testGetActividad() {
        app.registrarActividad(tarea);
        Actividad actividadObtenida = app.getActividad(tarea.getIdActividad(), "Tarea");

        assertEquals(tarea, actividadObtenida, "Debería devolver la actividad registrada.");
    }
    
    @Test
    void testActividadYaExistenteException() {
        app.registrarActividad(tarea);
        assertThrows(ActividadYaExistenteException.class,
                     () -> app.revisarActividadRepetida("Tarea", profesor.getLogin(), "Tarea"));
    }
    
    @Test
    void testPersistenciaUsuarios() {
        app.registrarUsuario(estudiante);
        app.registrarUsuario(profesor);

        app.descargarDatos();

        Aplicacion nuevaApp = new Aplicacion("usuarios.json", "lp.json", "preguntas.json", "actividades.json", "cifrasActividades.json");

        assertNotNull(nuevaApp.getEstudiante(estudiante.getLogin()), "El estudiante debería cargarse correctamente.");
        assertNotNull(nuevaApp.getProfesor(profesor.getLogin()), "El profesor debería cargarse correctamente.");
    }

    
    @Test
    void testEliminarEstudianteLearningPath() throws Exception {
        app.inscribirEstudianteLearningPath(estudiante, learningPath);
        assertTrue(learningPath.getEstudiantesInscritos().containsKey(estudiante.getLogin()),
                   "El estudiante debería estar inscrito inicialmente.");

        learningPath.eliminarEstudiante(estudiante);
        assertFalse(learningPath.getEstudiantesInscritos().containsKey(estudiante.getLogin()),
                    "El estudiante debería eliminarse del Learning Path.");
    }

    @Test
    void testPersistenciaLearningPaths() {
        app.registrarLearningPath(learningPath);
        app.descargarDatos();
        Aplicacion nuevaApp = new Aplicacion("usuarios.json", "lp.json", "preguntas.json", "actividades.json", "cifrasActividades.json");
        LearningPath lpCargado = nuevaApp.getLearningPath(learningPath.getIdLearnginPath());
        assertNotNull(lpCargado, "El Learning Path debería cargarse correctamente.");
        assertEquals(learningPath.getTitulo(), lpCargado.getTitulo(), "El título del Learning Path debería coincidir.");
    }
    
    @Test
    void testPersistenciaActividadesYPreguntas() {
        app.registrarActividad(tarea);
        app.registrarPregunta(preguntaAbierta);

        app.descargarDatos();

        Aplicacion nuevaApp = new Aplicacion("usuarios.json", "lp.json", "preguntas.json", "actividades.json", "cifrasActividades.json");

        assertNotNull(nuevaApp.getActividad(tarea.getIdActividad(), "Tarea"), "La actividad debería cargarse correctamente.");
        assertNotNull(nuevaApp.getPregunta(preguntaAbierta.getIdPregunta(), "Abierta"), "La pregunta debería cargarse correctamente.");
    }
    
    @Test
    void testModificarOpcionesPreguntaSeleccion() {
        app.crearPreguntaSeleccion("Pregunta?", "Titulo", "Opcion1", "Opcion2", "Opcion3", "Opcion4", 1, profesor);
        PreguntaSeleccionMultiple pregunta = app.getMapaPreguntasSeleccionMultiple().values().iterator().next();

        app.modificarOpcionesPreguntaSeleccion(pregunta, "Nueva Opcion", 2);

        assertEquals("Nueva Opcion", pregunta.getOpcion2(), "La opción debería ser actualizada.");
    }
    
    @Test
    void testModificarOpcionCorrectaPreguntaCerrada() {
        app.crearPreguntaSeleccion("Pregunta?", "Titulo", "Opcion1", "Opcion2", "Opcion3", "Opcion4", 1, profesor);
        PreguntaSeleccionMultiple pregunta = app.getMapaPreguntasSeleccionMultiple().values().iterator().next();

        app.modificarOpcionCorrectaPreguntaCerrada(pregunta, 3);

        assertEquals(3, pregunta.getOpcionCorrecta(), "La respuesta correcta debería ser actualizada.");
    }
    
    @Test
    void testCalificarLearningPath() {
        app.registrarLearningPath(learningPath);

        app.calificarLearningPath(learningPath, 4.5);

        assertEquals(4.5, learningPath.getRating(), "La calificación del Learning Path debería actualizarse.");
    }
    
    @Test
    void testResenarActividad() {
        app.registrarActividad(tarea);

        app.resenarActividad(tarea, "Buena actividad");

        assertTrue(tarea.getResenas().contains("Buena actividad"), "La reseña debería ser agregada a la actividad.");
    }

    @Test
    void testRevisarActividadesPrevias() throws Exception {
        Actividad actividadPrevia = new Tarea("Previa", "Descripcion", new ArrayList<>(), "Bajo", 60, new Date(), profesor);
        tarea.agregarActividadPrevia(actividadPrevia);

        app.registrarActividad(actividadPrevia);
        app.registrarActividad(tarea);

        learningPath.agregarActividad(actividadPrevia, true);
        learningPath.agregarActividad(tarea, true);

        app.registrarLearningPath(learningPath);
        app.inscribirEstudianteLearningPath(estudiante, learningPath);

        app.completarEncuestaRecurso(actividadPrevia, estudiante, learningPath);

        boolean cumple = app.revisarActividadesPrevias(tarea, estudiante, learningPath);

        assertTrue(cumple, "El estudiante debería cumplir los prerrequisitos.");
    }

    @Test
    void testModificarRecurso() {
        app.crearRevisarRecurso("Recurso", "Descripcion", new ArrayList<>(), "Bajo", 30, new Date(), "Video", profesor, "enlace");
        RevisarRecurso recurso = app.getMapaRevisarRecurso().values().iterator().next();

        app.modificarRecurso(recurso, "Nuevo Tipo", "Tipo");

        assertEquals("Nuevo Tipo", recurso.getTipoRecurso(), "El tipo del recurso debería actualizarse.");
    }
    
    @Test
    void testClonarActividad() {
        app.registrarActividad(tarea);

        Profesor otroProfesor = new Profesor("otroProf", "pass", "Otro Profesor");
        app.clonarActividad(tarea, otroProfesor);

        Actividad clonada = otroProfesor.getMapaTareasPropias().values().iterator().next();
        assertNotNull(clonada, "La actividad clonada debería ser registrada.");
        assertEquals("Tarea", clonada.getTitulo(), "El título de la actividad clonada debería coincidir.");
    }

    @Test
    void testModificarPreguntasQuiz() throws Exception {
        app.crearQuiz("Quiz", "Descripcion", new ArrayList<>(), "Medio", 30, new Date(), profesor, 4.0, new ArrayList<>());
        Quiz quiz = app.getMapaQuices().values().iterator().next();

        PreguntaSeleccionMultiple nuevaPregunta = new PreguntaSeleccionMultiple("Pregunta?", "Titulo", "Op1", "Op2", "Op3", "Op4", 1);
        app.modificarPreguntasQuiz(quiz, nuevaPregunta, "Agregar");

        assertTrue(quiz.getPreguntas().contains(nuevaPregunta), "La pregunta debería ser agregada al quiz.");
    }
    
    @Test
    void testClonarLearningPath() {
        Profesor otroProfesor = new Profesor("otroProf", "pass", "Otro Profesor");
        app.clonarLearningPath(learningPath, otroProfesor);

        LearningPath clon = otroProfesor.getLearningPathPropios().values().iterator().next();
        assertNotNull(clon, "El LearningPath clonado debería ser registrado.");
        assertEquals("Lógica Intermedia", clon.getTitulo(), "El título del LearningPath clonado debería coincidir.");
    }
    
    @Test
    void testEnviarTarea() throws Exception {
        app.registrarActividad(tarea);
        learningPath.agregarActividad(tarea, true);
        app.registrarLearningPath(learningPath);
        app.inscribirEstudianteLearningPath(estudiante, learningPath);

        app.enviarTarea(tarea, estudiante, learningPath);

        SeguimientoLearningPath seguimiento = estudiante.getLearningPathsInscritos().get(learningPath.getIdLearnginPath());
        SeguimientoTarea seguimientoTarea = (SeguimientoTarea) seguimiento.getMapaSeguimientoActividades().get(tarea.getIdActividad());

        assertEquals("Enviado", seguimientoTarea.getEstado(), "La tarea debería estar en estado 'Enviado'.");
    }

    @Test
    void testEnviarExamen() throws Exception {
        app.registrarActividad(examen);
        learningPath.agregarActividad(examen, true);
        app.registrarLearningPath(learningPath);
        app.inscribirEstudianteLearningPath(estudiante, learningPath);

        app.enviarExamen(examen, estudiante, learningPath);

        SeguimientoLearningPath seguimiento = estudiante.getLearningPathsInscritos().get(learningPath.getIdLearnginPath());
        SeguimientoExamen seguimientoExamen = (SeguimientoExamen) seguimiento.getMapaSeguimientoActividades().get(examen.getIdActividad());

        assertEquals("Enviado", seguimientoExamen.getEstado(), "El examen debería estar en estado 'Enviado'.");
    }

}


