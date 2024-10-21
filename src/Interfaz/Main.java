package Interfaz;

public class Main {
	public static String archivoUsuarios = "usuarios.json";
	public static String archivoLP = "lp.json";
	public static String archivoPreguntas = "preguntas.json";
	public static String archivoActividades = "actividades.json";
	
	
	public static void main(String[] args) {
		
		Aplicacion aplicacion = new Aplicacion();
		
		
		// Descarga de los Datos en Archivos JSON
		aplicacion.descargarDatos(aplicacion.getMapaExamenes(), aplicacion.getMapaEncuestas(),
				aplicacion.getMapaQuices(), aplicacion.getMapaRevisarRecurso(), aplicacion.getMapaTareas(),
				aplicacion.getMapaEstudiantes(), aplicacion.getMapaProfesores(), aplicacion.getMapaPreguntasAbiertas(),
				aplicacion.getMapaPreguntasSeleccionMultiple(), aplicacion.getMapaLearningPaths());
		
		// Carga de Archivos
		Aplicacion aplicacion2 = new Aplicacion(archivoUsuarios, archivoLP, archivoPreguntas, archivoActividades); 
	}
	
	
	
}
