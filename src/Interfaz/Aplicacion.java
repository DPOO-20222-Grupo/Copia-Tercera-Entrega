
package Interfaz;

import java.util.HashMap;
import java.util.Map;

import Actividades.Actividad;
import Actividades.Encuesta;
import Actividades.Examen;
import Actividades.Quiz;
import Actividades.RevisarRecurso;
import Actividades.Tarea;
import Execptions.UsuarioYaExistenteException;
import LearningPath.LearningPath;
import Preguntas.Pregunta;
import Preguntas.PreguntaAbierta;
import Preguntas.PreguntaSeleccionMultiple;
import User.Estudiante;
import User.Profesor;

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
	
	public void registrarUsuario(String login, String password, String nombre, boolean isEstudiante) throws UsuarioYaExistenteException {
		if (isEstudiante == true) {
			
			if (mapaEstudiantes.containsKey(login)) {
				throw new UsuarioYaExistenteException(login);
			}
			else {
				Estudiante nuevoEstudiante = new Estudiante(login, password, nombre);
				mapaEstudiantes.put(login, nuevoEstudiante);
			}
		}
		
		else {
			if (mapaEstudiantes.containsKey(login)) {
				throw new UsuarioYaExistenteException(login);
			}
			else {
				Profesor nuevoProfesor = new Profesor(login, password, nombre);
				mapaProfesores.put(login, nuevoProfesor);
			}
		}
	}
	
	public void registrarLearningPath (LearningPath pLearningPath) {
		String titulo = pLearningPath.getTitulo();
		String loginProfesor = pLearningPath.getProfesorCreador().getLogin();
		String llave = titulo + " - " + loginProfesor;
		
		mapaLearningPaths.put(llave, pLearningPath);
		
	}
	
	public  void registrarActividad(Actividad actividad) {
		
		String titulo = actividad.getTitulo();
		String loginProfesor = actividad.getProfesorCreador().getLogin();
		String llave = titulo + " - "+ loginProfesor;
		
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
		
		String titulo = pregunta.getTitulo();
		int id = pregunta.getId();
		String llave =  Integer.toString(id)+" - " + titulo;
		
		String tipo = pregunta.getTipo();
		
		if (tipo.equals("Abierta")) {
			
			mapaPreguntasAbiertas.put(llave, (PreguntaAbierta) pregunta);
			
		}
		
		else {
			
			mapaPreguntasSeleccionMultiple.put(llave, (PreguntaSeleccionMultiple) pregunta);
			
		}
		
		
		
		
	}
	

	
	
}

