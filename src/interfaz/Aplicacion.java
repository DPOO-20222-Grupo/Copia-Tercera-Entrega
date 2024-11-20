package interfaz;


import java.util.HashMap;  
import java.util.Map;

import java.util.Date;

import java.util.List;

import actividades.Actividad;
import actividades.Encuesta;
import actividades.Examen;
import actividades.Quiz;
import actividades.RevisarRecurso;
import actividades.Tarea;
import exceptions.ActividadPreviaCiclicoException;
import exceptions.ActividadSeguimientoCiclicoException;
import exceptions.ActividadYaCompletadaException;
import exceptions.ActividadYaExistenteException;
import exceptions.EstudianteNoInscritoException;
import exceptions.LearningPathYaExistenteException;
import exceptions.ModificarActividadesLearningPathException;
import exceptions.ModificarActividadesPreviasException;
import exceptions.ModificarActividadesSeguimientoException;
import exceptions.ModificarEstudianteLearningPathException;
import exceptions.ModificarObjetivosException;
import exceptions.ModificarPreguntasAbiertasException;
import exceptions.ModificarPreguntasQuizException;
import exceptions.TipoInvalidoValorException;
import exceptions.UsuarioYaExistenteException;
import learningPath.LearningPath;
import persistenciaDatos.PersistenciaActividades;
import persistenciaDatos.PersistenciaLearningPaths;
import persistenciaDatos.PersistenciaPreguntas;
import persistenciaDatos.PersistenciaUsuarios;
import preguntas.Pregunta;
import preguntas.PreguntaAbierta;
import preguntas.PreguntaBoolean;
import preguntas.PreguntaCerrada;
import preguntas.PreguntaSeleccionMultiple;
import seguimientoEstudiantes.SeguimientoActividad;
import seguimientoEstudiantes.SeguimientoEncuesta;
import seguimientoEstudiantes.SeguimientoExamen;
import seguimientoEstudiantes.SeguimientoLearningPath;
import seguimientoEstudiantes.SeguimientoQuiz;
import seguimientoEstudiantes.SeguimientoTarea;
import user.Estudiante;
import user.Profesor;
import user.Usuario;

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
	private HashMap<String, PreguntaBoolean> mapaPreguntasBoolean;
	
	
	public Aplicacion () {
		this.mapaEstudiantes = new HashMap<String, Estudiante>();
		this.mapaProfesores = new HashMap<String,Profesor >();
		this.mapaTareas = new HashMap <String, Tarea>();
		this.mapaRevisarRecurso = new HashMap <String, RevisarRecurso>();
		this.mapaEncuestas = new HashMap <String, Encuesta>();
		this.mapaExamenes = new HashMap <String, Examen>();
		this.mapaQuices = new HashMap <String, Quiz>();
		this.mapaPreguntasAbiertas = new HashMap <String, PreguntaAbierta>();
		this.mapaPreguntasSeleccionMultiple = new HashMap<String, PreguntaSeleccionMultiple>();
		this.mapaLearningPaths = new HashMap<String, LearningPath>();
		this.mapaPreguntasBoolean = new HashMap<String, PreguntaBoolean>();
	
	}
	
	public Aplicacion (String archivoUsuarios, String archivoLP, String archivoPreguntas, String archivoActividades) {
		this.mapaEstudiantes = PersistenciaUsuarios.cargarEstudiantes(archivoUsuarios);
		this.mapaProfesores = PersistenciaUsuarios.cargarProfesores(archivoUsuarios);
		this.mapaLearningPaths = PersistenciaLearningPaths.cargarLP(archivoLP);
		this.mapaTareas = PersistenciaActividades.cargarTarea(archivoActividades);
		this.mapaRevisarRecurso = PersistenciaActividades.cargarRecurso(archivoActividades);
		this.mapaEncuestas = PersistenciaActividades.cargarEncuesta(archivoActividades);
		this.mapaExamenes = PersistenciaActividades.cargarExamen(archivoActividades);
		this.mapaQuices = PersistenciaActividades.cargarQuiz(archivoActividades);
		this.mapaPreguntasAbiertas = PersistenciaPreguntas.cargarAbiertas(archivoPreguntas);
		this.mapaPreguntasSeleccionMultiple = PersistenciaPreguntas.cargarSM(archivoPreguntas);
		this.mapaPreguntasBoolean = PersistenciaPreguntas.cargarBooleanas(archivoPreguntas);
	}

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
	
	public HashMap<String, PreguntaBoolean> getMapaPreguntasBoolean() {
		return mapaPreguntasBoolean;
	}

	
	//Registrar nuevas entradas a la aplicacion

	//Usuarios
	
	public void registrarUsuario(Usuario nuevoUsuario) {
		if (nuevoUsuario.getTipo().equals("Estudiante")) { 
				mapaEstudiantes.put(nuevoUsuario.getLogin(), (Estudiante) nuevoUsuario);
			}
		else {
				mapaProfesores.put(nuevoUsuario.getLogin(), (Profesor) nuevoUsuario);
			}
	}
	
	//LearningPaths
	
	public void registrarLearningPath (LearningPath pLearningPath) {
		String llave = pLearningPath.getIdLearnginPath();
		mapaLearningPaths.put(llave, pLearningPath);
	}
	
	//Actividades
	
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
		
		else if (tipo.equals("Cerrada")){
			mapaPreguntasSeleccionMultiple.put(llave, (PreguntaSeleccionMultiple) pregunta);
		}
		
		else {
			mapaPreguntasBoolean.put(llave, (PreguntaBoolean) pregunta);
		}
	}
	
	public String generarLlaveLearningsActividades (String titulo, String login) {
		String llave = titulo + " - " + login;
		return llave;
	}
		
	public void revisarUsuarioRepetido(String login, String tipo) throws UsuarioYaExistenteException{
		if (tipo.equals("Estudiante") ) {
			if (mapaEstudiantes.containsKey(login)) {
				throw new UsuarioYaExistenteException(login, tipo);
			}
		}
		else if (tipo.equals("Profesor") ) {
			if (mapaProfesores.containsKey(login)) {
				throw new UsuarioYaExistenteException(login, tipo);
			}
		}
	}
	public void revisarActividadRepetida (String titulo, String login, String tipo) throws ActividadYaExistenteException {
		
		String llave = generarLlaveLearningsActividades (titulo, login);
		
		if (tipo.equals("Tarea")) {
			if (mapaTareas.containsKey(llave)) {
				throw new ActividadYaExistenteException (titulo, tipo);
			}
		}
		else if (tipo.equals("Quiz")) {
			
			if (mapaQuices.containsKey(llave)) {
				throw new ActividadYaExistenteException (titulo, tipo);
			}
			
		}
		else if (tipo.equals( "Recurso")) {
			
			if (mapaRevisarRecurso.containsKey(llave)){
				throw new ActividadYaExistenteException (titulo, tipo);
			}
			
		}
		else if (tipo.equals("Examen")) {
			
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
	//Crear usuario y registrarlo
	public void crearEstudiante(String login, String password, String nombre) {
		Estudiante nuevoEstudiante = new Estudiante (login, password, nombre);
		this.registrarUsuario(nuevoEstudiante);
		
	}
	//Crear profesor y registrarlo
	public void crearProfesor(String login, String password, String nombre) {
		Profesor nuevoProfesor = new Profesor(login, password, nombre);
		this.registrarUsuario(nuevoProfesor);
		
	}
	//Crear cada tipo de actividad y registrarla en base de datos central y en registros del profesor
	//Revisar recurso
	public void crearRevisarRecurso(String titulo, String descripcion, List<String> objetivos, String nivelDificultad, int duracionMinutos, 
    						Date fechaLimite, String tipoRecurso, Profesor profesorCreador, String enlaceRecurso) {
		RevisarRecurso nuevoRecurso = new RevisarRecurso(titulo, descripcion, objetivos, nivelDificultad, duracionMinutos, 
														fechaLimite, tipoRecurso, profesorCreador, enlaceRecurso);
		this.registrarActividad(nuevoRecurso);
		profesorCreador.registrarActividad(nuevoRecurso);
		
	}
	//Tarea
	public void crearTarea (String titulo, String descripcion, List<String> objetivos, String nivelDificultad, 
							int duracionMinutos, Date fechaLimite,  Profesor profesorCreador) 
	
	{
		Tarea nuevaTarea = new Tarea(titulo, descripcion, objetivos, nivelDificultad, duracionMinutos, fechaLimite, profesorCreador);
		this.registrarActividad(nuevaTarea);
		profesorCreador.registrarActividad(nuevaTarea);
		
	}
	
	//Quiz
	public void crearQuiz (String titulo, String descripcion, List<String> objetivos, String nivelDificultad, int duracionMinutos,
    		Date fechaLimite, Profesor profesorCreador, double calificacionMinima, List<PreguntaCerrada> preguntas) {
		
		Quiz nuevoQuiz = new Quiz (titulo, descripcion, objetivos, nivelDificultad, duracionMinutos, fechaLimite, profesorCreador, calificacionMinima, preguntas);
		this.registrarActividad(nuevoQuiz);
		profesorCreador.registrarActividad(nuevoQuiz);
		
	}
		//Examen
	public void crearExamen (String titulo, String descripcion, List<String> objetivos, String nivelDificultad, 
    		int duracionMinutos, Date fechaLimite, Profesor profesorCreador, List<PreguntaAbierta> preguntas) 
	
	{
		
		Examen nuevoExamen = new Examen(titulo, descripcion, objetivos, nivelDificultad, duracionMinutos, 
										fechaLimite, profesorCreador, preguntas);
		this.registrarActividad(nuevoExamen);
		profesorCreador.registrarActividad(nuevoExamen);
		
	}
		//Encuesta
	public void crearEncuesta (String titulo, String descripcion, List<String> objetivos, String nivelDificultad, int duracionMinutos, 
								Date fechaLimite,  Profesor profesorCreador, List<PreguntaAbierta> preguntas)
	{
		
		Encuesta nuevaEncuesta = new Encuesta (titulo, descripcion, objetivos, nivelDificultad, duracionMinutos, fechaLimite, profesorCreador, preguntas);
		this.registrarActividad(nuevaEncuesta);
		profesorCreador.registrarActividad(nuevaEncuesta);
	}
	
	/**
	 * Metodo para modificar los atributos de tipo "String" de un LearningPath
	 * @param learningPath learningPath a modificar
	 * @param atributoModificar nombre del atributo a modificar
	 * @param valor Nuevo valor para el atributo
	 * @param accion Parametro para modificar objetivos, ya sea agregar o eliminar
	 * @throws TipoInvalidoValorException
	 * @throws ModificarObjetivosException
	 */
	public void modificarAtributosStringLearningPath(LearningPath learningPath, String atributoModificar, String valor, String accion)
				throws TipoInvalidoValorException, ModificarObjetivosException
	{
		
		
		if (atributoModificar.equals("Titulo")) {
			
				String idProfesor = learningPath.getLoginProfesorCreador();
				Profesor profesor = this.getProfesor(idProfesor);
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
	
	/**
	 * Metodo para modificar las actividades de un Learning Path 
	 * @param learningPath Learning Path a modificar
	 * @param actividad  Actividad que se quiere modificar
	 * @param obligatoriedad Si la actividad se va a agregar, determina si es obligatoria o no en el LP
	 * @param accion Agregar o eliminar del LP
	 * @throws ModificarActividadesLearningPathException
	 */
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
	
	
	/**
	 * Metodo para modificar los atributos de una actividad
	 * 
	 * @param actividad Actividad a modificar
	 * @param valor Nuevo valor para el atributo
	 * @param atributo Nombre del atributo a modificar
	 * @param accion Agregar o eliminar, unicamente para objetivos (puede ser null si no aplica)
	 * @param fecha Nuevo valor para fecha (puede ser null si no aplica)
	 * @param duracion Nuevo valor para duracion (puede ser null si no aplica)
	 * @throws ModificarObjetivosException Lanza excepcion si, al agregar (eliminar) un objetivo, el objetivo ya se encontraba a
	 * (no se encontraba) en la lista de objetivos.
	 */
	public void modificarActividad(Actividad actividad, String valor, String atributo, String accion, Date fecha, Integer duracion)
	        throws ModificarObjetivosException {

	    if (atributo.equals("Titulo")) {
	        String tipoActividad = actividad.getTipoActividad();
	        String idProfesor = actividad.getLoginProfesorCreador();
	        String idActividad = actividad.getIdActividad();
	        Profesor profesor = getProfesor(idProfesor);

	        switch (tipoActividad) {
	            case "Encuesta":
	                HashMap<String, Encuesta> mapaEncuestas = this.getMapaEncuestas();
	                mapaEncuestas.remove(idActividad);
	                profesor.getMapaEncuestasPropias().remove(idActividad);
	                actividad.setTitulo(valor);
	                String idNuevo = actividad.getIdActividad();
	                mapaEncuestas.put(idNuevo, (Encuesta) actividad);
	                profesor.getMapaEncuestasPropias().put(idNuevo, (Encuesta) actividad);
	                break;
	                
	            case "Tarea":
	                HashMap<String, Tarea> mapaTareas = this.getMapaTareas();
	                mapaTareas.remove(idActividad);
	                profesor.getMapaTareasPropias().remove(idActividad);
	                actividad.setTitulo(valor);
	                String idNuevo1 = actividad.getIdActividad();
	                mapaTareas.put(idNuevo1, (Tarea) actividad);
	                profesor.getMapaTareasPropias().put(idNuevo1, (Tarea) actividad);
	                break;
	                
	            case "Quiz":
	                HashMap<String, Quiz> mapaQuices = this.getMapaQuices();
	                mapaQuices.remove(idActividad);
	                profesor.getMapaQuicesPropios().remove(idActividad);
	                actividad.setTitulo(valor);
	                String idNuevo2 = actividad.getIdActividad();
	                mapaQuices.put(idNuevo2, (Quiz) actividad);
	                profesor.getMapaQuicesPropios().put(idNuevo2, (Quiz) actividad);
	                break;
	                
	            case "Examen":
	                HashMap<String, Examen> mapaExamenes = this.getMapaExamenes();
	                mapaExamenes.remove(idActividad);
	                profesor.getMapaExamenesPropios().remove(idActividad);
	                actividad.setTitulo(valor);
	                String idNuevo3 = actividad.getIdActividad();
	                mapaExamenes.put(idNuevo3, (Examen) actividad);
	                profesor.getMapaExamenesPropios().put(idNuevo3, (Examen) actividad);
	                break;
	                
	            case "Recurso":
	                HashMap<String, RevisarRecurso> mapaRecursos = this.getMapaRevisarRecurso();
	                mapaRecursos.remove(idActividad);
	                profesor.getMapaRecursosPropios().remove(idActividad);
	                actividad.setTitulo(valor);
	                String idNuevo4 = actividad.getIdActividad();
	                mapaRecursos.put(idNuevo4, (RevisarRecurso) actividad);
	                profesor.getMapaRecursosPropios().put(idNuevo4, (RevisarRecurso) actividad);
	                break;
	        }
	    } 
	    
	    else if (atributo.equals("Descripcion")) {
	        actividad.setDescripcion(valor);
	    } 
	    else if (atributo.equals("Dificultad")) {
	        actividad.setNivelDificultad(valor);
	    } 
	    else if (atributo.equals("Objetivos")) {
	        if (accion.equals("Agregar")) {
	            actividad.agregarObjetivo(valor);
	        } else {
	            actividad.eliminarObjetivo(valor);
	        }
	    }
	    else if (atributo.equals("Fecha Limite")) {
	        actividad.setFechaLimite(fecha);
	    } 
	    else if (atributo.equals("Duracion")) {
	        actividad.setDuracionMinutos(duracion);
	    }    
	} 
	
	/** Metodo para modificar las actividades previas o de seguimiento de una actividad
	 * @param actividadPrincipal Actividad a modificar sus actividades de seguimiento o previas
	 * @param actividadOperacion Actividad previa o de seguimiento que se quiere modificar
	 * @param tipo Indica si es previa o de seguimiento
	 * @param accion Indica si se quiere agregar o eliminar
	 * tira excepciones si la actividad a agregar (eliminar) ya se encontraba (no se encontraba) en la lista correspondiente
	 * @throws ModificarActividadesPreviasException 
	 * @throws ModificarActividadesSeguimientoException
	 * @throws ActividadPreviaCiclicoException 
	 * @throws ActividadSeguimientoCiclicoException 
	 */
	public void modificarSeguimientoPrevioActividad (Actividad actividadPrincipal, Actividad actividadOperacion, String tipo, int accion)
				throws ModificarActividadesPreviasException, ModificarActividadesSeguimientoException, ActividadPreviaCiclicoException, ActividadSeguimientoCiclicoException
	{
		
		if (tipo.equals("Previa")) {
			
			if (accion == 1) {
				
				actividadPrincipal.agregarActividadPrevia(actividadOperacion);
			}
			
			else {
				actividadPrincipal.eliminarActividadPrevia(actividadOperacion);
			}
			
		}
		
		else {
			
			if (accion == 1) {
				actividadPrincipal.agregarActividadSeguimiento(actividadOperacion);
			}
			
			else {
				actividadPrincipal.eliminarActividadSeguimiento(actividadOperacion);
			}
			
		}
		
	}
	
	//Funciones para modificar los atributos particulares de cada tipo de actividad 
	
	
	/**
	 * @param recurso Recurso a modificar
	 * @param valor Nuevo valor del atributo
	 * @param atributo Nombre del atributo a modificar.
	 */
	public void modificarRecurso (RevisarRecurso recurso, String valor, String atributo) {
		
		if (atributo.equals("Tipo")) {
			recurso.setTipoRecurso(valor);
		}
		else {
			recurso.setEnlaceRecurso(valor);
		}
	}
	
	/**
	 * Permite modificar las preguntas de una encuesta o de un examen
	 * @param actividad Actividad que se quiere modificar
	 * @param pregunta Pregunta que se va a agregar o eliminar
	 * @param accion Indica si se debe agregar o eliminar la pregunta
	 * @throws ModificarPreguntasAbiertasException
	 */
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

	
	/**
	 * Permite modificar las preguntas de un quiz
	 * @param quiz Quiz a modificar
	 * @param pregunta Pregunta a agregar o eliminar 
	 * @param accion Indica si se debe agregar o eliminar la pregunta
	 * @throws ModificarPreguntasQuizException
	 */
	public void modificarPreguntasQuiz(Quiz quiz, PreguntaCerrada pregunta, String accion)
	throws ModificarPreguntasQuizException
	
	{
		if (accion.equals("Agregar")) {
			quiz.agregarPregunta(pregunta);
		}
		
		else if (accion.equals("Eliminar")){
			quiz.eliminarPregunta(pregunta);
		}
		
		
	}
	
	//Permite modificar Calificacion Minima quiz
	public void modificarCalificacionMinimaQuiz (Quiz quiz, double nuevaCalificacion) {
		quiz.setCalificacionMinima(nuevaCalificacion);
	}
	
	//Metodos para crear cada tipo de pregunta
	
	public void crearPreguntaAbierta(String enunciado, String titulo, Profesor profesorCreador) {
		PreguntaAbierta pregunta = new PreguntaAbierta (enunciado, titulo);
		String llave = pregunta.getIdPregunta();
		this.getMapaPreguntasAbiertas().put(llave, pregunta);
		profesorCreador.registrarPregunta(pregunta);
	}
	
	public void crearPreguntaSeleccion (String  enunciado, String titulo, String opcion1, String opcion2, String opcion3, String opcion4, int opcionCorrecta, Profesor profesorCreador) {
		
		PreguntaSeleccionMultiple pregunta = new PreguntaSeleccionMultiple(enunciado, titulo, opcion1, opcion2, opcion3, opcion4, opcionCorrecta);
		String llave = pregunta.getIdPregunta();
		
		this.getMapaPreguntasSeleccionMultiple().put(llave, pregunta);
		profesorCreador.registrarPregunta(pregunta);
		
	}
	
	public void crearPreguntaBoolean(String enunciado, String titulo, int opcionCorrecta, Profesor profesorCreador) {
		PreguntaBoolean pregunta = new PreguntaBoolean(enunciado, titulo, opcionCorrecta);
		String llave = pregunta.getIdPregunta();
		
		this.getMapaPreguntasBoolean().put(llave, pregunta);
		profesorCreador.registrarPregunta(pregunta);
	}
	
	//permite modificar el titulo de una pregunta
	public void modificarTituloPregunta (Pregunta pregunta, String titulo, Profesor profesorCreador) {
		
		String tipoPregunta = pregunta.getTipo();
		String llave = pregunta.getIdPregunta();
		
		if (tipoPregunta.equals("Abierta")) {
			this.getMapaPreguntasAbiertas().remove(llave);
			profesorCreador.getPreguntasAbiertasPropias().remove(llave);
			
			pregunta.setTitulo(titulo);
			
			this.getMapaPreguntasAbiertas().put(llave, (PreguntaAbierta) pregunta);
			profesorCreador.getPreguntasAbiertasPropias().put(llave, (PreguntaAbierta) pregunta);
			
		}
		
		else if (tipoPregunta.equals("Cerrada")){
			this.getMapaPreguntasSeleccionMultiple().remove(llave);
			profesorCreador.getPreguntasSeleccionPropias().remove(llave);
			
			pregunta.setTitulo(titulo);
			
			this.getMapaPreguntasSeleccionMultiple().put(llave, (PreguntaSeleccionMultiple) pregunta);
			profesorCreador.getPreguntasSeleccionPropias().put(llave, (PreguntaSeleccionMultiple) pregunta);
			
		}
		
		else {
			
			this.getMapaPreguntasBoolean().remove(llave);
			profesorCreador.getPreguntasBooleanPropias().remove(llave);
			
			pregunta.setTitulo(titulo);
			
			this.registrarPregunta(pregunta);
			profesorCreador.registrarPregunta(pregunta);
			
		}
	}
	
	//Permite modificar el enunciado de una pregunta
	public void modificarEnunciadoPregunta(Pregunta pregunta, String enunciado) {
		pregunta.setEnunciado(enunciado);
		
	}
	
	
	/**
	 * Permite modificar una de las opciones de una pregunta de Seleccion Multiple
	 * @param pregunta Pregunta a modificar
	 * @param nuevaOpcion Nuevo enunciado para la opcion a modificar
	 * @param opcionModificar Numero de la opcion a modificar
	 */
	public void modificarOpcionesPreguntaSeleccion (PreguntaSeleccionMultiple pregunta, String nuevaOpcion, int opcionModificar) {
		
		if (opcionModificar == 1) {
			pregunta.setOpcion1(nuevaOpcion);
		}
		
		else if (opcionModificar == 2) {
			pregunta.setOpcion2(nuevaOpcion);
		}
		
		else if (opcionModificar == 3) {
			pregunta.setOpcion3(nuevaOpcion);
		}
		
		else if (opcionModificar == 4) {
			pregunta.setOpcion4(nuevaOpcion);
		}
		
	}
	//Permite modificar la respuesta correcta de preguntas cerradas
	public void modificarOpcionCorrectaPreguntaCerrada (PreguntaCerrada pregunta, int nuevaOpcion) {
		pregunta.setOpcionCorrecta(nuevaOpcion);
	}
	
	
	//Metodos que permiten a profesores calificar actividades de estudiantes en un learning path 
	
	public void calificarExamen(Examen examen, Estudiante estudiante, LearningPath learningPath, float nota) {
		
		Map <String, SeguimientoLearningPath> mapaEstudiantes = learningPath.getEstudiantesInscritos();
		
		SeguimientoLearningPath seguimientoEstudiante = mapaEstudiantes.get(estudiante.getLogin());
		
		Map <String, SeguimientoActividad> mapaSeguimientosActividades = seguimientoEstudiante.getMapaSeguimientoActividades();
		
		SeguimientoExamen seguimientoExamen = (SeguimientoExamen) mapaSeguimientosActividades.get(examen.getIdActividad());
		
		seguimientoExamen.setNota(nota);
		seguimientoExamen.actualizarEstadoCompletado();	
		
	}
	
	public void calificarTarea(Tarea tarea, Estudiante estudiante, LearningPath learningPath, boolean esExitoso) {
		
		Map <String, SeguimientoLearningPath> mapaEstudiantes = learningPath.getEstudiantesInscritos();
		
		SeguimientoLearningPath seguimientoEstudiante = mapaEstudiantes.get(estudiante.getLogin());
		
		Map <String, SeguimientoActividad> mapaSeguimientosActividades = seguimientoEstudiante.getMapaSeguimientoActividades();
		
		SeguimientoTarea seguimientoTarea = (SeguimientoTarea) mapaSeguimientosActividades.get(tarea.getIdActividad());
		
		seguimientoTarea.actualizarEstadoFinal(esExitoso);
		
		
	}
	
	/**
	 * Permite a un profesor clonar una actividad de otro profesor para poder editarla
	 * @param actividadOriginal Actividad original que se quiere duplicar
	 * @param profesorClonando Profesor que esta clonando la actividad
	 */
	public void clonarActividad (Actividad actividadOriginal, Profesor profesorClonando) {
			
			String tipoActividad = actividadOriginal.getTipoActividad();
			
			if (tipoActividad.equals("Encuesta")) {
				
				Encuesta encuesta = (Encuesta) actividadOriginal;
				
				this.crearEncuesta(
						encuesta.getTitulo(),
						encuesta.getDescripcion(),
						encuesta.getObjetivos(),
						encuesta.getNivelDificultad(),
						encuesta.getDuracionMinutos(), 
						encuesta.getFechaLimite(), 
						profesorClonando,
						encuesta.getPreguntas()
						);
			}
			
			else if (tipoActividad.equals("Tarea")) {
				
				Tarea tareaOriginal = (Tarea) actividadOriginal;
				
				this.crearTarea(tareaOriginal.getTitulo(), 
								tareaOriginal.getDescripcion(), 
								tareaOriginal.getObjetivos(), 
								tareaOriginal.getNivelDificultad(), 
								tareaOriginal.getDuracionMinutos(), 
								tareaOriginal.getFechaLimite(), 
								profesorClonando);
			}
			
			else if (tipoActividad.equals("Quiz")) {
				
				Quiz quizOriginal = (Quiz) actividadOriginal;
				
				this.crearQuiz(quizOriginal.getTitulo(), 
							   quizOriginal.getDescripcion(), 
							   quizOriginal.getObjetivos(), 
							   quizOriginal.getNivelDificultad(), 
							   quizOriginal.getDuracionMinutos(), 
							   quizOriginal.getFechaLimite(), 
							   profesorClonando, 
							   quizOriginal.getCalificacionMinima(),
							   quizOriginal.getPreguntas());	
			}
			
			else if (tipoActividad.equals("Examen")) {
				
				Examen examen = (Examen) actividadOriginal;
				
				this.crearExamen(examen.getTitulo(), 
								 examen.getDescripcion(), 
								 examen.getObjetivos(), 
								 examen.getNivelDificultad(), 
								 examen.getDuracionMinutos(),
								 examen.getFechaLimite(), 
								 profesorClonando, 
								 examen.getPreguntas());
				
			}
			
			else if (tipoActividad.equals("Recurso")) {
				
				RevisarRecurso recurso = (RevisarRecurso) actividadOriginal;
				
				this.crearRevisarRecurso(recurso.getTitulo(), 
										 recurso.getDescripcion(), 
										 recurso.getObjetivos(), 
										 recurso.getNivelDificultad(), 
										 recurso.getDuracionMinutos(), 
										 recurso.getFechaLimite(), 
										 recurso.getTipoRecurso(), 
										 profesorClonando, 
										 recurso.getEnlaceRecurso());
				
			}
	}
	
	//Permite duplicar un learning Path de otro profesor
	public void clonarLearningPath (LearningPath learningPathOriginal, Profesor profesorClonando) {
		
		this.crearLearningPath(learningPathOriginal.getTitulo(), 
							   learningPathOriginal.getDescripcion(), 
							   learningPathOriginal.getObjetivos(), 
							   learningPathOriginal.getNivelDificultad(),
							   profesorClonando, 
							   learningPathOriginal.getActividades(), 
							   learningPathOriginal.getMapaActividadesObligatorias());
	}
	
	//Permite inscribir un estudiante a un learning path
	public void inscribirEstudianteLearningPath (Estudiante estudiante, LearningPath learningPath) throws ModificarEstudianteLearningPathException {
		
		SeguimientoLearningPath seguimiento = new SeguimientoLearningPath(learningPath, estudiante);
		
		learningPath.inscribirEstudiante(estudiante, seguimiento);
		
		estudiante.agregarSeguimientoLearningPath(seguimiento);
		
	}
	
	//Permite a un estudiante enviar una tarea
	public void enviarTarea (Tarea tarea, Estudiante estudiante, LearningPath learningPath) throws EstudianteNoInscritoException, ActividadYaCompletadaException {
		
		SeguimientoLearningPath seguimientoEstudiante = learningPath.getEstudiantesInscritos().get(estudiante.getLogin());
		if (seguimientoEstudiante == null) {
			throw new EstudianteNoInscritoException(learningPath);
		}
		
		else {
		SeguimientoTarea seguimientoTarea = (SeguimientoTarea) seguimientoEstudiante.getMapaSeguimientoActividades().get(tarea.getIdActividad());
			if(seguimientoTarea.getEstado().equals("Incompleta")) {
				seguimientoTarea.actualizarEstadoEnviado();
			}
			
			else {
				throw new ActividadYaCompletadaException(tarea, learningPath);
			}
		}
		
	}
	//Permite actualizar el estado de un examen a enviado
	public void enviarExamen (Examen examen, Estudiante estudiante, LearningPath learningPath) throws EstudianteNoInscritoException, ActividadYaCompletadaException {
		
		SeguimientoLearningPath seguimientoEstudiante = learningPath.getEstudiantesInscritos().get(estudiante.getLogin());
		
		if (seguimientoEstudiante == null) {
			throw new EstudianteNoInscritoException(learningPath);
		}
		
		else {

		SeguimientoExamen seguimientoExamen = (SeguimientoExamen) seguimientoEstudiante.getMapaSeguimientoActividades().get(examen.getIdActividad());
		
		
		if(seguimientoExamen.getEstado().equals("Incompleta")) {
			seguimientoExamen.actualizarEstadoEnviado();
		}
		
		else {
			throw new ActividadYaCompletadaException(examen, learningPath);
		}
	}
		
		
	}
	//Permite a un estudiante registrar su respuesta a una pregunta de un examen de un learning path
	public void responderPreguntaExamen (Examen examen, Estudiante estudiante, LearningPath learningPath, PreguntaAbierta pregunta, String respuesta) throws EstudianteNoInscritoException, ActividadYaCompletadaException {
		
		SeguimientoLearningPath seguimientoEstudiante = learningPath.getEstudiantesInscritos().get(estudiante.getLogin());
		
		if (seguimientoEstudiante == null) {
			throw new EstudianteNoInscritoException(learningPath);
		}
		
		else {
		
		SeguimientoExamen seguimientoExamen = (SeguimientoExamen) seguimientoEstudiante.getMapaSeguimientoActividades().get(examen.getIdActividad());
		
		if(seguimientoExamen.getEstado().equals("Incompleta")) {
			seguimientoExamen.registrarPregunta(pregunta, respuesta);;
		}
		
		else {
			throw new ActividadYaCompletadaException(examen, learningPath);
		}
	
		}
		
	}
	
	//Permite a un estudiante registrar su respuesta a una pregunta de una encuesta
	public void responderPreguntaEncuesta (Encuesta encuesta, Estudiante estudiante, LearningPath learningPath, PreguntaAbierta pregunta, String respuesta) throws EstudianteNoInscritoException, ActividadYaCompletadaException {
		
		SeguimientoLearningPath seguimientoEstudiante = learningPath.getEstudiantesInscritos().get(estudiante.getLogin());
		
		if (seguimientoEstudiante == null) {
			throw new EstudianteNoInscritoException(learningPath);
		}
		
		else {
		
		SeguimientoEncuesta seguimientoEncuesta = (SeguimientoEncuesta) seguimientoEstudiante.getMapaSeguimientoActividades().get(encuesta.getIdActividad());
		
		if(seguimientoEncuesta.getEstado().equals("Incompleta")) {
			seguimientoEncuesta.registrarPregunta(pregunta, respuesta);;
		}
		
		else {
			throw new ActividadYaCompletadaException(encuesta, learningPath);
		}
	
		
		}
		
	}
	
	//Permite a un estudiante registrar su respuesta a una pregunta de un quiz
	
	public void responderPreguntaQuiz (Quiz quiz, Estudiante estudiante, LearningPath learningPath, PreguntaCerrada pregunta, int respuesta) throws EstudianteNoInscritoException {
		
		SeguimientoLearningPath seguimientoEstudiante = learningPath.getEstudiantesInscritos().get(estudiante.getLogin());
		
		if (seguimientoEstudiante == null) {
			throw new EstudianteNoInscritoException(learningPath);
		}
		
		else {
		
		SeguimientoQuiz seguimientoQuiz = (SeguimientoQuiz) seguimientoEstudiante.getMapaSeguimientoActividades().get(quiz.getIdActividad());
		
		seguimientoQuiz.agregarRespuestaPregunta(pregunta, respuesta);
		}
	}
	
	//Permite registrar que se completo una encuesta o recurso
	
	public void completarEncuestaRecurso (Actividad actividad, Estudiante estudiante, LearningPath learningPath) throws EstudianteNoInscritoException, ActividadYaCompletadaException {
		
		SeguimientoLearningPath seguimientoEstudiante = learningPath.getEstudiantesInscritos().get(estudiante.getLogin());
		if (seguimientoEstudiante == null) {
			throw new EstudianteNoInscritoException(learningPath);
		}
		
		else {
		
		SeguimientoActividad seguimientoActividad = seguimientoEstudiante.getMapaSeguimientoActividades().get(actividad.getIdActividad());
		if(seguimientoActividad.getEstado().equals("Incompleta")) {
			seguimientoActividad.actualizarEstadoCompletado();
		}
		
		else {
			throw new ActividadYaCompletadaException(actividad, learningPath);
		}
	
		
		
		}
		
		
	}
	
	/**
	 * Permite saber si un estudiante cumple con los prerrequisitos recomendados de una actividad
	 * Se revisan las actividades previas de una actividad que tambien estan en el learning Path dado
	 * @param actividad Actividad para la que se quieren revisar los prerrequisitos
	 * @param estudiante Estudiante que quiere realizar "actividad"
	 * @param learningPath Learning Path en el que se encuentra "actividad"
	 * @return
	 * @throws EstudianteNoInscritoException 
	 */
	public boolean revisarActividadesPrevias(Actividad actividad, Estudiante estudiante, LearningPath learningPath) throws EstudianteNoInscritoException {
		
		List<Actividad> prerrequisitos = actividad.getActividadesPrevias();
		
		String idLP = learningPath.getIdLearnginPath();
		
		SeguimientoLearningPath seguimiento = estudiante.getLearningPathsInscritos().get(idLP);
		
		if (seguimiento != null) {
		Map<String, SeguimientoActividad> seguimientoActividades = seguimiento.getMapaSeguimientoActividades();
		
		boolean cumplePrerrequisitos = true;
		
		for (Actividad actividadPrevia: prerrequisitos) {
			
			if (seguimientoActividades.containsKey(actividadPrevia.getIdActividad())) {
				SeguimientoActividad seguimientoActividadPrevia = seguimientoActividades.get(actividadPrevia.getIdActividad());
				String estado = seguimientoActividadPrevia.getEstado();
				
				if (estado.equals("Incompleto")) {
					cumplePrerrequisitos = false;
				}
				
			}
			
		}
		
		return cumplePrerrequisitos;
		}
		
		else {
			throw new EstudianteNoInscritoException(learningPath);
		}
		
		
	}
	
	//Funciones que permiten a los usuarios calificar LearningPath y Actividades y resenar actividades
	public void calificarLearningPath (LearningPath learningPath, double rating) {
		learningPath.actualizarRating(rating);
	}
	
	public void resenarActividad(Actividad actividad, String resena) {
		actividad.agregarResena(resena);
	}
	
	public void calificarActividad (Actividad actividad, double rating) {
		actividad.actualizarRating(rating);
	}
	
	//Funcion que permite descargar los datos de la aplicacion a un archivo JSON
	public void descargarDatos () {
		PersistenciaActividades.persistirActividades(mapaExamenes, mapaEncuestas, mapaQuices, mapaRevisarRecurso, mapaTareas, "actividades.json");
		PersistenciaUsuarios.persistirUsuarios(mapaEstudiantes, mapaProfesores, "usuarios.json");
		PersistenciaPreguntas.persistirPreguntas(mapaPreguntasAbiertas, mapaPreguntasSeleccionMultiple, mapaPreguntasBoolean,"preguntas.json");
		PersistenciaLearningPaths.persistirLearningPaths(mapaLearningPaths, "lp.json");
	}
	
	
	//Funciones para acceder a usuarios dado su login
	public Estudiante getEstudiante (String login) {
		Estudiante estudiante = this.getMapaEstudiantes().get(login);
		return estudiante;
	}

	public Profesor getProfesor (String login) {
		Profesor profesor = this.getMapaProfesores().get(login);
		return profesor;
	}
	
	//Permite acceder a una actividad dado su identificador y su tipo
	public Actividad getActividad (String id, String tipo) {
		
		if (tipo.equals("Examen")) {
			Examen actividad = this.getMapaExamenes().get(id);
			return actividad;
		}
		
		else if (tipo.equals("Encuesta")) {
			Encuesta actividad = this.getMapaEncuestas().get(id);
			return actividad;
		}
		
		else if (tipo.equals("Quiz")) {
			Quiz actividad = this.getMapaQuices().get(id);
			return actividad;
		}
		
		else if (tipo.equals("Tarea")) {
			Tarea actividad = this.getMapaTareas().get(id);
			return actividad;
		}
		
		else if (tipo.equals("Recurso")) {
			RevisarRecurso actividad = this.getMapaRevisarRecurso().get(id);
			return actividad;
		}
		
		else {
			return null;
		}
		
	}
	
	//Permite acceder a un learning path dado su identificador
	public LearningPath getLearningPath(String id) {
		LearningPath learningPath = this.getMapaLearningPaths().get(id);
		return learningPath;
	}
	
	//Permite acceder a una pregunta dado su identificador
	public Pregunta getPregunta (String id, String tipo) {
		
		if (tipo.equals("Abierta")) {
			PreguntaAbierta pregunta = this.getMapaPreguntasAbiertas().get(id);
			return pregunta;
			
		}
		
		else if (tipo.equals("Cerrada")){
			PreguntaSeleccionMultiple pregunta = this.getMapaPreguntasSeleccionMultiple().get(id);
			return pregunta;
		}
		
		else if (tipo.equals("Verdadero o Falso")){
			PreguntaBoolean pregunta = this.getMapaPreguntasBoolean().get(id);
			return pregunta;
		}
		
		else {
			return null;
		}
	}
	
	public void actualizarDuracionDesarrolloActividad (Estudiante estudiante, LearningPath learningPath, Actividad actividad, int duracion) {
		SeguimientoLearningPath seguimientoEstudiante = learningPath.getEstudiantesInscritos().get(estudiante.getLogin());
		SeguimientoActividad seguimientoActividad = seguimientoEstudiante.getMapaSeguimientoActividades().get(actividad.getIdActividad());
		seguimientoActividad.setTiempoTotal(duracion);
		seguimientoEstudiante.actualizarTiempoTotal(duracion);
	}

	
}
	
	

