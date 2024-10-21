package Interfaz;

import LearningPath.LearningPath;
import User.Estudiante;
import User.Profesor;

public class Main {
	public static String archivoUsuarios = "usuarios.json";
	public static String archivoLP = "lp.json";
	public static String archivoPreguntas = "preguntas.json";
	public static String archivoActividades = "actividades.json";
	
	
	public static void main(String[] args) {
		
		Aplicacion aplicacion = new Aplicacion();
		
		// Pruebas de Registro de Usuarios
		
		Estudiante stud1 = new Estudiante("d.martinezf", "Banano123", "Diego Martinez");
		Profesor prof1 = new Profesor("l.munera", "Tristeza30", "Lina Munera");
		
		aplicacion.registrarUsuario(prof1);
		aplicacion.registrarUsuario(stud1);
		
		// Pruebas de Registro de LearningPaths
		
		LearningPath lp = new LearningPath("Introducción a Listas", "Listas en Java"); 
		
		
		// Descarga de los Datos en Archivos JSON
		aplicacion.descargarDatos(aplicacion.getMapaExamenes(), aplicacion.getMapaEncuestas(),
				aplicacion.getMapaQuices(), aplicacion.getMapaRevisarRecurso(), aplicacion.getMapaTareas(),
				aplicacion.getMapaEstudiantes(), aplicacion.getMapaProfesores(), aplicacion.getMapaPreguntasAbiertas(),
				aplicacion.getMapaPreguntasSeleccionMultiple(), aplicacion.getMapaLearningPaths());
		
		// Carga de Archivos
		Aplicacion aplicacion2 = new Aplicacion(archivoUsuarios, archivoLP, archivoPreguntas, archivoActividades); 
	}
	
	
	
}
