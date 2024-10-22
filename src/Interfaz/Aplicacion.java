
package Interfaz;

import java.util.HashMap; 
import java.util.Map;

import Actividades.Actividad;
import Actividades.Encuesta;
import Actividades.Examen;
import Actividades.Quiz;
import Actividades.RevisarRecurso;
import Actividades.Tarea;
import LearningPath.LearningPath;
import Preguntas.Pregunta;
import Preguntas.PreguntaAbierta;
import Preguntas.PreguntaSeleccionMultiple;
import User.Estudiante;
import User.Profesor;
import User.Usuario;
import exceptions.ActividadYaExistenteException;
import exceptions.LearningPathYaExistenteException;
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
		
}
	
	

