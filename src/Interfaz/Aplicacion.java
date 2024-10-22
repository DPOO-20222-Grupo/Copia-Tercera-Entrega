
package Interfaz;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Actividades.Actividad;
import Actividades.Encuesta;
import Actividades.Examen;
import Actividades.Quiz;
import Actividades.RevisarRecurso;
import Actividades.Tarea;
import LearningPath.LearningPath;
import Persistencia_Datos.PersistenciaLearningPaths;
import Persistencia_Datos.PersistenciaActividades;
import Persistencia_Datos.PersistenciaLearningPaths;
import Persistencia_Datos.PersistenciaPreguntas;
import Persistencia_Datos.PersistenciaUsuarios;
import Preguntas.Pregunta;
import Preguntas.PreguntaAbierta;
import Preguntas.PreguntaSeleccionMultiple;
import User.Estudiante;
import User.Profesor;
import User.Usuario;
import exceptions.ActividadYaExistenteException;
import exceptions.LearningPathYaExistenteException;
import exceptions.ModificarActividadesLearningPathException;
import exceptions.ModificarActividadesPreviasException;
import exceptions.ModificarActividadesSeguimientoException;
import exceptions.ModificarObjetivosException;
import exceptions.ModificarPreguntasAbiertasException;
import exceptions.ModificarPreguntasQuizException;
import exceptions.TipoInvalidoValorException;
import exceptions.UsuarioYaExistenteException;

public class Aplicacion {

	private HashMap<String, Estudiante> mapaEstudiantes;
	private HashMap<String, Profesor> mapaProfesores;
	private HashMap<String, LearningPath> mapaLearningPaths;
	private HashMap<String, Tarea> mapaTareas;
	private HashMap<String, RevisarRecurso> mapaRevisarRecurso;
	private HashMap<String, Encuesta> mapaEncuestas;
	private HashMap<String, Examen> mapaExamenes;
	private HashMap<String, Quiz> mapaQuices;
	private HashMap<String, PreguntaAbierta> mapaPreguntasAbiertas;
	private HashMap<String, PreguntaSeleccionMultiple> mapaPreguntasSeleccionMultiple;
	
	
	public Aplicacion () {
		this.mapaEstudiantes = new HashMap<String, Estudiante>();
		this.mapaProfesores = new HashMap<String,Profesor >();
		this.mapaLearningPaths = new HashMap<String, LearningPath>();
		this.mapaTareas = new HashMap <String, Tarea>();
		this.mapaRevisarRecurso = new HashMap <String, RevisarRecurso>();
		this.mapaEncuestas = new HashMap <String, Encuesta>();
		this.mapaExamenes = new HashMap <String, Examen>();
		this.mapaQuices = new HashMap <String, Quiz>();
		this.mapaPreguntasAbiertas = new HashMap <String, PreguntaAbierta>();
		this.mapaPreguntasSeleccionMultiple = new HashMap<String, PreguntaSeleccionMultiple>();
	
	}
	
	public Aplicacion (String archivoUsuarios, String archivoLP, String archivoPreguntas, String archivoActividades) {
		this.mapaEstudiantes = PersistenciaUsuarios.cargarEstudiantes(archivoUsuarios);
		this.mapaProfesores = PersistenciaUsuarios.cargarProfesor(archivoUsuarios);
		this.mapaLearningPaths = PersistenciaLearningPaths.cargarLP(archivoLP);
		this.mapaTareas = PersistenciaActividades.cargarTarea(archivoActividades);
		this.mapaRevisarRecurso = PersistenciaActividades.cargarRecurso(archivoActividades);
		this.mapaEncuestas = PersistenciaActividades.cargarEncuesta(archivoActividades);
		this.mapaExamenes = PersistenciaActividades.cargarExamen(archivoActividades);
		this.mapaQuices = PersistenciaActividades.cargarQuiz(archivoActividades);
		this.mapaPreguntasAbiertas = PersistenciaPreguntas.cargarAbiertas(archivoPreguntas);
		this.mapaPreguntasSeleccionMultiple = PersistenciaPreguntas.cargarCerradas(archivoPreguntas);
	}

	// Getters


	//Getters y Setters estructuras
	
	public HashMap<String, Estudiante> getMapaEstudiantes() {
		return mapaEstudiantes;
	}

	public HashMap<String, Profesor> getMapaProfesores() {
		return mapaProfesores;
	}

	public HashMap<String, LearningPath> getMapaLearningPaths() {
		return mapaLearningPaths;
	}

	public HashMap<String, Tarea> getMapaTareas() {
		return mapaTareas;
	}

	public HashMap<String, RevisarRecurso> getMapaRevisarRecurso() {
		return mapaRevisarRecurso;
	}

	public HashMap<String, Encuesta> getMapaEncuestas() {
		return mapaEncuestas;
	}

	public HashMap<String, Examen> getMapaExamenes() {
		return mapaExamenes;
	}

	public HashMap<String, Quiz> getMapaQuices() {
		return mapaQuices;
	}

	public HashMap<String, PreguntaAbierta> getMapaPreguntasAbiertas() {
		return mapaPreguntasAbiertas;
	}

	public HashMap<String, PreguntaSeleccionMultiple> getMapaPreguntasSeleccionMultiple() {
		return mapaPreguntasSeleccionMultiple;
	}



	//Registrar nuevas entradas a la aplicacion



	public void registrarUsuario(Usuario nuevoUsuario) {
		if (nuevoUsuario.getTipo().equals("Estudiante")) { 
				mapaEstudiantes.put(nuevoUsuario.getLogin(), (Estudiante) nuevoUsuario);
			}
		
		else {
				mapaProfesores.put(nuevoUsuario.getLogin(), (Profesor) nuevoUsuario);
			}

	}
	
	public void registrarLearningPath (LearningPath pLearningPath) {

		String llave = pLearningPath.getIdLearnginPath();
		
		mapaLearningPaths.put(llave, pLearningPath);
		
	}
	
	public  void registrarActividad(Actividad actividad) {
		
		String llave = actividad.getIdActividad();
		
		String tipoActividad = actividad.getTipoActividad();
		
		if (tipoActividad.equals("Encuesta")) {
			mapaEncuestas.put(llave, (Encuesta) actividad);
			
		}
		
		else if (tipoActividad.equals("Tarea")) {
			mapaTareas.put(llave, (Tarea) actividad);
			
		}
		
		else if (tipoActividad.equals("Quiz")) {
			mapaQuices.put(llave, (Quiz) actividad);
			
		}
		
		else if (tipoActividad.equals("Examen")) {
			mapaExamenes.put(llave, (Examen) actividad);
			
		}
		
		else if (tipoActividad.equals("Recurso")) {
			mapaRevisarRecurso.put(llave, (RevisarRecurso) actividad);
			
		}
		
	}
	
	public void registrarPregunta (Pregunta pregunta) {
		
		String llave =  pregunta.getIdPregunta();
		
		String tipo = pregunta.getTipo();
		
		if (tipo.equals("Abierta")) {
			
			mapaPreguntasAbiertas.put(llave, (PreguntaAbierta) pregunta);
			
		}
		
		else {
			
			mapaPreguntasSeleccionMultiple.put(llave, (PreguntaSeleccionMultiple) pregunta);
			
		}
		
	
	}
	
	public String generarLlaveLearningsActividades (String titulo, String login) {
		
		String llave = titulo + " - " + login;
		
		return llave;
	}
		
	public void revisarActividadRepetida (String titulo, String login, String tipo) throws ActividadYaExistenteException {
		
		String llave = generarLlaveLearningsActividades (titulo, login);
		
		if (tipo == "Tarea") {
			if (mapaTareas.containsKey(llave)) {
				throw new ActividadYaExistenteException (titulo, tipo);
			}
		}
		else if (tipo == "Quiz") {
			
			if (mapaQuices.containsKey(llave)) {
				throw new ActividadYaExistenteException (titulo, tipo);
			}
			
		}
		else if (tipo == "Recurso") {
			
			if (mapaRevisarRecurso.containsKey(llave)){
				throw new ActividadYaExistenteException (titulo, tipo);
			}
			
		}
		else if (tipo == "Examen") {
			
			if (mapaExamenes.containsKey(llave)) {
				throw new ActividadYaExistenteException (titulo, tipo);
			}
			
		}
		
		else {
			if (mapaEncuestas.containsKey(llave)) {
				throw new ActividadYaExistenteException (titulo, tipo);
			}
		}
		
		
	}
	
	public void revisarLearningPathRepetido (String titulo, String login) throws LearningPathYaExistenteException {
		
		String llave = generarLlaveLearningsActividades(titulo, login);
		
		if (mapaLearningPaths.containsKey(llave)) {
			throw new LearningPathYaExistenteException(titulo);
		}
		
	}
	
	
	// Requerimientos funcionales
	
	//Crear Learning Paths y registrarlos correctamente
	public void crearLearningPath(String titulo, String descripcion, List<String> objetivos, String nivelDificultad, 
    					Profesor profesorCreador,List <Actividad> listaActividades, Map<String, Boolean> mapaObligatoriedad)
	{
		LearningPath nuevoLearningPath = new LearningPath(titulo, descripcion, objetivos, nivelDificultad, profesorCreador, listaActividades, mapaObligatoriedad);
		this.registrarLearningPath(nuevoLearningPath);
		profesorCreador.registrarLearningPath(nuevoLearningPath);
		
	}
	
	public void crearEstudiante(String login, String password, String nombre) {
		Estudiante nuevoEstudiante = new Estudiante (login, password, nombre);
		this.registrarUsuario(nuevoEstudiante);
		
	}
	
	public void crearProfesor(String login, String password, String nombre) {
		Profesor nuevoProfesor = new Profesor(login, password, nombre);
		this.registrarUsuario(nuevoProfesor);
		
	}
	
	public void crearRevisarRecurso(String titulo, String descripcion, List<String> objetivos, String nivelDificultad, int duracionMinutos, 
    						Date fechaLimite, String tipoRecurso, Profesor profesorCreador, String enlaceRecurso) {
		RevisarRecurso nuevoRecurso = new RevisarRecurso(titulo, descripcion, objetivos, nivelDificultad, duracionMinutos, 
														fechaLimite, tipoRecurso, profesorCreador, enlaceRecurso);
		this.registrarActividad(nuevoRecurso);
		profesorCreador.registrarActividad(nuevoRecurso);
		
	}
	
	public void crearTarea (String titulo, String descripcion, List<String> objetivos, String nivelDificultad, 
							int duracionMinutos, Date fechaLimite,  Profesor profesorCreador) 
	
	{
		Tarea nuevaTarea = new Tarea(titulo, descripcion, objetivos, nivelDificultad, duracionMinutos, fechaLimite, profesorCreador);
		this.registrarActividad(nuevaTarea);
		profesorCreador.registrarActividad(nuevaTarea);
		
	}
	
	public void crearQuiz (String titulo, String descripcion, List<String> objetivos, String nivelDificultad, int duracionMinutos,
    		Date fechaLimite, Profesor profesorCreador, float calificacionMinima, List<PreguntaSeleccionMultiple> preguntas) {
		
		Quiz nuevoQuiz = new Quiz (titulo, descripcion, objetivos, nivelDificultad, duracionMinutos, fechaLimite, profesorCreador, calificacionMinima, preguntas);
		this.registrarActividad(nuevoQuiz);
		profesorCreador.registrarActividad(nuevoQuiz);
		
	}
	
	public void crearExamen (String titulo, String descripcion, List<String> objetivos, String nivelDificultad, 
    		int duracionMinutos, Date fechaLimite, Profesor profesorCreador, List<PreguntaAbierta> preguntas) 
	
	{
		
		Examen nuevoExamen = new Examen(titulo, descripcion, objetivos, nivelDificultad, duracionMinutos, 
										fechaLimite, profesorCreador, preguntas);
		this.registrarActividad(nuevoExamen);
		profesorCreador.registrarActividad(nuevoExamen);
		
	}
	
	public void crearEncuesta (String titulo, String descripcion, List<String> objetivos, String nivelDificultad, int duracionMinutos, 
								Date fechaLimite,  Profesor profesorCreador, List<PreguntaAbierta> preguntas)
	{
		
		Encuesta nuevaEncuesta = new Encuesta (titulo, descripcion, objetivos, nivelDificultad, duracionMinutos, fechaLimite, profesorCreador, preguntas);
		this.registrarActividad(nuevaEncuesta);
		profesorCreador.registrarActividad(nuevaEncuesta);
	}
	
	public void modificarAtributosStringLearningPath(LearningPath learningPath, String atributoModificar, String valor, String accion)
				throws TipoInvalidoValorException, ModificarObjetivosException
	{
		
		
		if (atributoModificar.equals("Titulo")) {
			
				Profesor profesor = learningPath.getProfesorCreador();
				String idLP = learningPath.getIdLearnginPath();
				HashMap<String, LearningPath> mapaGlobal = this.getMapaLearningPaths();
				
				mapaGlobal.remove(idLP);
				profesor.getLearningPathPropios().remove(idLP);
				 
				learningPath.modificarTitulo(valor);
				mapaGlobal.put(idLP, learningPath);
				profesor.getLearningPathPropios().put(idLP, learningPath);
				
			}
		
		else if (atributoModificar.equals("Descripcion")) {
			
				 learningPath.modificarDescripcion(valor);
			
		}
		
		else if (atributoModificar.equals("Dificultad")) {
			
				 learningPath.actualizarDificultad((String) valor);

		}
		
		
		else if (atributoModificar.equals("Objetivos")) {
			
				
				if (accion.equals("Agregar")) {
					
					learningPath.agregarObjetivo((String) valor);
					
				}
				
				else {
					learningPath.eliminarObjetivo((String) valor);
				}
		}
		
	}
	
	public void modificarActividadesLearningPath (LearningPath learningPath, Actividad actividad, boolean obligatoriedad, String accion) 
			throws ModificarActividadesLearningPathException
	
	{
		
		if (accion.equals("Agregar")) {
			
			learningPath.agregarActividad(actividad, obligatoriedad);
			
		}
		
		else if (accion.equals("Obligatoriedad")) {
			learningPath.modificarObligatoriedadActividad(actividad);
		}
		
		else if (accion.equals("Eliminar"))
		
		{
			learningPath.eliminarActividad(actividad);
		}
		
		
	}
	
	
		
	
	
	public void modificarTituloActividad (Actividad actividad, String titulo) {
		
		String tipoActividad = actividad.getTipoActividad();
		
		if (tipoActividad.equals("Encuesta")) {
			Profesor profesor = actividad.getProfesorCreador();
			String idActividad = actividad.getIdActividad();
			
			HashMap<String, Encuesta> mapaGlobal = this.getMapaEncuestas();
			
			mapaGlobal.remove(idActividad);
			profesor.getMapaEncuestasPropias().remove(idActividad);
			
			actividad.setTitulo(titulo);
			
			mapaGlobal.put(idActividad, (Encuesta) actividad);
			profesor.getMapaEncuestasPropias().put(idActividad, (Encuesta) actividad);
			
		}
		
		else if (tipoActividad.equals("Tarea")) {
			
			Profesor profesor = actividad.getProfesorCreador();
			String idActividad = actividad.getIdActividad();
			
			HashMap<String, Tarea> mapaGlobal = this.getMapaTareas();
			
			mapaGlobal.remove(idActividad);
			profesor.getMapaTareasPropias().remove(idActividad);
			
			actividad.setTitulo(titulo);
			
			mapaGlobal.put(idActividad, (Tarea) actividad);
			profesor.getMapaTareasPropias().put(idActividad, (Tarea) actividad);
			
		}
		
		else if (tipoActividad.equals("Quiz")) {
			
			Profesor profesor = actividad.getProfesorCreador();
			String idActividad = actividad.getIdActividad();
			
			HashMap<String, Quiz> mapaGlobal = this.getMapaQuices();
			
			mapaGlobal.remove(idActividad);
			profesor.getMapaQuicesPropios().remove(idActividad);
			
			actividad.setTitulo(titulo);
			
			mapaGlobal.put(idActividad, (Quiz) actividad);
			profesor.getMapaQuicesPropios().put(idActividad, (Quiz) actividad);
			
		}
		
		else if (tipoActividad.equals("Examen")) {
			
			Profesor profesor = actividad.getProfesorCreador();
			String idActividad = actividad.getIdActividad();
			
			HashMap<String, Examen> mapaGlobal = this.getMapaExamenes();
			
			mapaGlobal.remove(idActividad);
			profesor.getMapaExamenesPropios().remove(idActividad);
			
			actividad.setTitulo(titulo);
			
			mapaGlobal.put(idActividad, (Examen) actividad);
			profesor.getMapaExamenesPropios().put(idActividad, (Examen) actividad);
			
		}
		
		else if (tipoActividad.equals("Recurso")) {
			
			Profesor profesor = actividad.getProfesorCreador();
			String idActividad = actividad.getIdActividad();
			
			HashMap<String, RevisarRecurso> mapaGlobal = this.getMapaRevisarRecurso();
			
			mapaGlobal.remove(idActividad);
			profesor.getMapaRecursosPropios().remove(idActividad);
			
			actividad.setTitulo(titulo);
			
			mapaGlobal.put(idActividad, (RevisarRecurso) actividad);
			profesor.getMapaRecursosPropios().put(idActividad, (RevisarRecurso) actividad);
			
		}
		
		
	}

	
	public void modificarAtributosStringActividad (Actividad actividad, String valor, String atributo, String accion) 
			throws ModificarObjetivosException {
		
		if (atributo.equals("Descripcion")) {
			actividad.setDescripcion(valor);
		}
		
		else if (atributo.equals("Dificultad")) {
			actividad.setNivelDificultad(valor);
		}
		
		else if (atributo.equals("Objetivos")) {
			if (accion.equals("Agregar")) {
				
				actividad.agregarObjetivo((String) valor);
				
			}
			
			else {
				actividad.eliminarObjetivo((String) valor);
			}
			
		}
		
	}
	
	public void modificarFechaLimiteActividad (Actividad actividad, Date fecha) {
		
		actividad.setFechaLimite(fecha);
		
	}
	
	public void modificarDuracionActividad (Actividad actividad, int duracion) {
		
		actividad.setDuracionMinutos(duracion);
	}
	
	public void modificarSeguimientoPrevioActividad (Actividad actividadPrincipal, Actividad actividadOperacion, String tipo, String accion)
				throws ModificarActividadesPreviasException, ModificarActividadesSeguimientoException
	{
		
		if (tipo.equals("Previa")) {
			
			if (accion.equals("Agregar")) {
				
				actividadPrincipal.agregarActividadPrevia(actividadOperacion);
			}
			
			else {
				actividadPrincipal.eliminarActividadPrevia(actividadOperacion);
			}
			
		}
		
		else {
			
			if (accion.equals("Agregar")) {
				actividadPrincipal.agregarActividadSeguimiento(actividadOperacion);
			}
			
			else {
				actividadPrincipal.eliminarActividadSeguimiento(actividadOperacion);
			}
			
		}
		
	}
	
	
	public void modificarRecurso (RevisarRecurso recurso, String valor, String atributo) {
		
		if (atributo.equals("Tipo")) {
			
			recurso.setTipoRecurso(valor);
		}
		
		else {
			recurso.setEnlaceRecurso(valor);
		}
		
	}
	
	public void modificarPreguntasExamenEncuesta (Actividad actividad, PreguntaAbierta pregunta, String accion)
	throws ModificarPreguntasAbiertasException
	
	{
		if (actividad.getTipoActividad().equals("Encuesta"))
		
		{
			Encuesta encuesta = (Encuesta) actividad;
			if (accion.equals("Agregar")) {
				encuesta.agregarPregunta(pregunta);
			}
			
			else {
				encuesta.eliminarPregunta(pregunta);
			}
		
		}
		
		else {
			
			Examen examen = (Examen) actividad;
			if (accion.equals("Agregar")) {
				examen.agregarPregunta(pregunta);
			}
			
			else {
				examen.eliminarPregunta(pregunta);
			}
		}
	}

	
	public void modificarPreguntasQuiz(Quiz quiz, PreguntaSeleccionMultiple pregunta, String accion)
	throws ModificarPreguntasQuizException
	
	{
		if (accion.equals("Agregar")) {
			quiz.agregarPregunta(pregunta);
		}
		
		else {
			quiz.eliminarPregunta(pregunta);
		}
		
		
	}
	
	public void modificarCalificacionMinimaQuiz (Quiz quiz, float nuevaCalificacion) {
		quiz.setCalificacionMinima(nuevaCalificacion);
	}
	
	public void crearPreguntaAbierta(String enunciado, String titulo, Profesor profesorCreador) {
		PreguntaAbierta pregunta = new PreguntaAbierta (enunciado, titulo);
		String llave = pregunta.getIdPregunta();
		this.getMapaPreguntasAbiertas().put(llave, pregunta);
		profesorCreador.getPreguntasAbiertasPropias().put(llave, pregunta);
	}
	
	public void crearPreguntaSeleccion (String  enunciado, String titulo, String opcion1, String opcion2, String opcion3, String opcion4, int opcionCorrecta, Profesor profesorCreador) {
		
		PreguntaSeleccionMultiple pregunta = new PreguntaSeleccionMultiple(enunciado, titulo, opcion1, opcion2, opcion3, opcion4, opcionCorrecta);
		String llave = pregunta.getIdPregunta();
		
		this.getMapaPreguntasSeleccionMultiple().put(llave, pregunta);
		profesorCreador.getPreguntasSeleccionPropias().put(llave, pregunta);
		
		
	}
        
	


	public void descargarDatos (HashMap<String, Examen> mapaExamenes, 
			HashMap<String, Encuesta> mapaEncuestas, HashMap<String, Quiz> mapaQuices,
			HashMap<String, RevisarRecurso> mapaRevisarRecurso, HashMap<String, Tarea> mapaTareas,
			HashMap<String, Estudiante> mapaEstudiantes, HashMap<String, Profesor> profMap,
			HashMap<String, PreguntaAbierta> abiertaMap, HashMap<String, PreguntaSeleccionMultiple> cerradaMap,
			HashMap<String, LearningPath> mapaLearningPaths) {
		PersistenciaActividades.persistirActividades(mapaExamenes, mapaEncuestas, mapaQuices, mapaRevisarRecurso, mapaTareas, "actividades.json");
		PersistenciaUsuarios.persistirUsuarios(mapaEstudiantes, profMap, "usuarios.json");
		PersistenciaPreguntas.persistirPreguntas(abiertaMap, cerradaMap, "preguntas.json");
		PersistenciaLearningPaths.persistirLearningPaths(mapaLearningPaths, "lp.json");
	}

	
}
	
	

