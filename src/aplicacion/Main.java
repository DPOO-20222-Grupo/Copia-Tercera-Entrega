package aplicacion;

import java.util.ArrayList; 
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.text.ParseException;

import actividades.Actividad;
import actividades.Encuesta;
import actividades.Examen;
import actividades.Quiz;
import actividades.RevisarRecurso;
import actividades.Tarea;
import exceptions.ActividadPreviaCiclicoException;
import exceptions.ActividadYaCompletadaException;
import exceptions.EstudianteNoInscritoException;
import exceptions.ModificarActividadesPreviasException;
import exceptions.ModificarEstudianteLearningPathException;
import interfazEstudiante.VentanaEstudiante;
import learningPath.LearningPath;
import preguntas.PreguntaAbierta;
import preguntas.PreguntaCerrada;
import preguntas.PreguntaSeleccionMultiple;
import user.Estudiante;
import user.Profesor;

public class Main {
	public static String archivoUsuarios = "usuarios.json";
	public static String archivoLP = "lp.json";
	public static String archivoPreguntas = "preguntas.json";
	public static String archivoActividades = "actividades.json";
	public static String archivoConteo = "cifrasActividades.json";

	
	public static void correrApp() throws ModificarEstudianteLearningPathException, ModificarActividadesPreviasException, ActividadPreviaCiclicoException, EstudianteNoInscritoException, ActividadYaCompletadaException {
		try {
		
		Aplicacion aplicacion = new Aplicacion();
		
		// Pruebas de Registro de Usuarios
		
		aplicacion.crearEstudiante("d.martinezf", "Banano123", "Diego Martinez");
		aplicacion.crearEstudiante("a.linares", "Banano123", "Andres Linares");
		aplicacion.crearProfesor("l.munera", "Tristeza30", "Lina Munera");
		
		Estudiante stud1 = aplicacion.getEstudiante("d.martinezf");
		Estudiante stud2 = aplicacion.getEstudiante("a.linares");
		Profesor prof1 = aplicacion.getProfesor("l.munera");
		
		// Pruebas de Registro de Preguntas
		
		aplicacion.crearPreguntaAbierta("Ingrese su nombre: ", "Registro de Nombres", prof1);
		aplicacion.crearPreguntaSeleccion("¿Qué color no es primario?", "Colores", "Azul", "Rojo", "Amarillo", "Negro", 4, prof1);
		
		PreguntaAbierta pregAbierta = (PreguntaAbierta) aplicacion.getPregunta("1 - Registro de Nombres", "Abierta");
		PreguntaSeleccionMultiple pregCerrada = (PreguntaSeleccionMultiple) aplicacion.getPregunta("1 - Colores", "Cerrada");
		
		// Pruebas de Registro de Actividades
		
		
		List<PreguntaAbierta> preguntasAbiertas = new ArrayList<PreguntaAbierta>();
		preguntasAbiertas.add(pregAbierta);
		List<PreguntaCerrada> preguntasCerradas = new ArrayList<PreguntaCerrada>();
		preguntasCerradas.add(pregCerrada);
		List<String> objetivos = new ArrayList<String>();
		objetivos.add("Objetivo 1");
		objetivos.add("Objetivo 2");
		String fecha = "23-10-2024";
		SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");		
		Date date = formateador.parse(fecha);
		
		aplicacion.crearEncuesta("Autoevaluación", "Autoevaluación acitividad #1", objetivos, "Bajo", 15, date,
				 prof1, preguntasAbiertas);
		
		Encuesta en = (Encuesta) aplicacion.getActividad("Autoevaluación - l.munera", "Encuesta");
		
		aplicacion.crearQuiz("Quiz1", "Primer Quiz", objetivos, "bajo", 45, date, prof1, 0.7, preguntasCerradas);
		
		Quiz qu = (Quiz) aplicacion.getActividad("Quiz1 - l.munera", "Quiz");
		
		aplicacion.crearTarea("Tarea1", "Tarea numero uno", objetivos, "bajo", 120, date, prof1);
		
		Tarea ta =  (Tarea) aplicacion.getActividad("Tarea1 - l.munera", "Tarea");
		
		aplicacion.crearExamen("Parcial", "Examen Parcial", objetivos, "medio", 120, date, prof1, preguntasAbiertas);
		
		Examen ex = (Examen) aplicacion.getActividad("Parcial - l.munera", "Examen" );
		
		aplicacion.crearRevisarRecurso("Ver video", "Ver video segunda guerra mundial", objetivos, "bajo", 45,
				date, "video", prof1, "enlace");
		
		RevisarRecurso rr = (RevisarRecurso) aplicacion.getActividad("Ver video - l.munera", "Recurso");
		
		
		
		// Pruebas de Registro de LearningPaths
		
		List<Actividad> actividades = new ArrayList<Actividad>();
		actividades.add(en);
		actividades.add(ex);
		actividades.add(rr);
		actividades.add(ta);
		actividades.add(qu);
		
		
		HashMap<String, Boolean> mapaObligatorio = new HashMap<String, Boolean>();
		
		mapaObligatorio.put(en.getIdActividad(), true);
		mapaObligatorio.put(ex.getIdActividad(), false);
		mapaObligatorio.put(rr.getIdActividad(), true);
		mapaObligatorio.put(ta.getIdActividad(), false);
		mapaObligatorio.put(qu.getIdActividad(), true);
		
		
		aplicacion.crearLearningPath("Introducción a las Pruebas", "Pruebas", objetivos, "mid",
				prof1, actividades, mapaObligatorio);
		
		LearningPath learningPath = aplicacion.getLearningPath("Introducción a las Pruebas - l.munera");
		
		aplicacion.inscribirEstudianteLearningPath(stud2, learningPath);
		aplicacion.enviarExamen(ex, stud2, learningPath);
		aplicacion.enviarTarea(ta, stud2, learningPath);
		aplicacion.completarEncuestaRecurso(rr, stud2, learningPath);
		
		// Descarga de los Datos en Archivos JSON
		aplicacion.descargarDatos();
		
		// Re-Carga de Archivos
		Aplicacion aplicacion_recargada = new Aplicacion(archivoUsuarios, archivoLP, archivoPreguntas, archivoActividades, archivoConteo);
		
		// Pruebas para asegurar que la Re-Carga está bien hecha
		
		Estudiante estudiante = aplicacion_recargada.getEstudiante("d.martinezf");
		Profesor profesor = aplicacion_recargada.getProfesor("l.munera");
		
		Quiz quiz = (Quiz) aplicacion_recargada.getActividad(aplicacion_recargada.generarLlaveLearningsActividades("Quiz1", "l.munera"), "Quiz");
		
		Examen examen = (Examen) aplicacion_recargada.getActividad(aplicacion_recargada.generarLlaveLearningsActividades("Parcial", "l.munera"), "Examen");

		Tarea tarea = (Tarea) aplicacion_recargada.getActividad(aplicacion_recargada.generarLlaveLearningsActividades("Tarea1", "l.munera"), "Tarea");

		RevisarRecurso revisarRecurso = (RevisarRecurso) aplicacion_recargada.getActividad(aplicacion_recargada.generarLlaveLearningsActividades("Ver video", "l.munera"), "Recurso");

		Encuesta encuesta = (Encuesta) aplicacion_recargada.getActividad(aplicacion_recargada.generarLlaveLearningsActividades("Autoevaluación", "l.munera"), "Encuesta");
		
		
		
		System.out.println(
				"\nEstudiante 1. \n"
				+ String.format("Nombre: %s \n", estudiante.getNombre())
				+ String.format("Login: %s \n", estudiante.getLogin() )
				
				);
		
		System.out.println(
				"Profesor 1. \n"
				+ String.format("Nombre: %s \n", profesor.getNombre())
				+ String.format("Login: %s \n", profesor.getLogin() )
				
				);
		
		System.out.println(
				"Actividades Registradas: \n"
				+ String.format("1. %s \n", quiz.getTitulo())
				+ String.format("--- Descripcion: %s \n",quiz.getDescripcion())
				+ "--- Objetivos: \n"
				+ String.format("     * : %s\n", quiz.getObjetivos().get(0))
				+ String.format("     * : %s\n", quiz.getObjetivos().get(1))
				+ String.format("--- Dificultad: %s \n",quiz.getNivelDificultad())
				+ String.format("--- Duracion Estimada: %d minutos \n",quiz.getDuracionMinutos())
				
				
				+ String.format("2. %s \n", examen.getTitulo())
				+ String.format("--- Descripcion: %s \n",examen.getDescripcion())
				+ "--- Objetivos: \n"
				+ String.format("     * : %s\n", examen.getObjetivos().get(0))
				+ String.format("     * : %s\n", examen.getObjetivos().get(1))
				+ String.format("--- Dificultad: %s \n",examen.getNivelDificultad())
				+ String.format("--- Duracion Estimada: %d minutos \n",examen.getDuracionMinutos())
				
				+ String.format("3. %s \n", tarea.getTitulo())
				+ String.format("--- Descripcion: %s \n",tarea.getDescripcion())
				+ "--- Objetivos:  \n"
				+ String.format("     * : %s\n", tarea.getObjetivos().get(0))
				+ String.format("     * : %s\n", tarea.getObjetivos().get(1))
				+ String.format("--- Dificultad: %s \n",tarea.getNivelDificultad())
				+ String.format("--- Duracion Estimada: %d minutos \n",tarea.getDuracionMinutos())
				
				+ String.format("4. %s \n", encuesta.getTitulo())
				+ String.format("--- Descripcion: %s \n",encuesta.getDescripcion())
				+ "--- Objetivos:  \n"
				+ String.format("     * : %s\n", encuesta.getObjetivos().get(0))
				+ String.format("     * : %s\n", encuesta.getObjetivos().get(1))
				+ String.format("--- Dificultad: %s \n",encuesta.getNivelDificultad())
				+ String.format("--- Duracion Estimada: %d minutos \n",encuesta.getDuracionMinutos())
				
				+ String.format("5. %s \n", revisarRecurso.getTitulo())
				+ String.format("--- Descripcion: %s \n",revisarRecurso.getDescripcion())
				+ "--- Objetivos: \n"
				+ String.format("     * : %s\n", revisarRecurso.getObjetivos().get(0))
				+ String.format("     * : %s\n", revisarRecurso.getObjetivos().get(1))
				+ String.format("--- Dificultad: %s \n", revisarRecurso.getNivelDificultad())
				+ String.format("--- Duracion Estimada: %d minutos \n", revisarRecurso.getDuracionMinutos())
			
		
				
				
				);
		
		LearningPath printLp = aplicacion_recargada.getLearningPath("Introducción a las Pruebas - l.munera");
		LocalDateTime fechaCreacion = printLp.getFechaCreacion();
		LocalDateTime fechaModificacion = printLp.getFechaUltimaModificacion();
		String fechaFormateada = String.format("%d/%d/%d", fechaCreacion.getDayOfMonth(), fechaCreacion.getMonthValue(), fechaCreacion.getYear() );
		String fechaMod = String.format("%1$d/%2$d/%3$d", fechaModificacion.getDayOfMonth(), fechaModificacion.getMonthValue(), fechaModificacion.getYear() );
		
		System.out.println(
				"\nLearning Path:\n"
				+ String.format("Titulo: %s\n", printLp.getTitulo())
				+ String.format("Version: %.2f\n", printLp.getVersion())
				+ String.format("Descripción: %s\n", printLp.getDescripcion())
				+ String.format("Duración (m):  %d\n", printLp.getDuracionMinutos())
				+ String.format("Fecha de Creación: %s\n", fechaFormateada)
				+ String.format("Fecha de Modificación: %s\n", fechaMod)
				+ String.format("Login Profesor Creador: %s\n", printLp.getLoginProfesorCreador())
				+ String.format("Nivel de Dificultad: %s\n", printLp.getNivelDificultad())
				+ "Objetivos: \n" 
				+ String.format("  * : %s\n", printLp.getObjetivos().get(0))
				+ String.format("  * : %s\n", printLp.getObjetivos().get(1))
				+ "Actividades: \n"
				+ String.format("  * : %s\n", printLp.getActividades().get(0).getTitulo())
				+ String.format("  * : %s\n", printLp.getActividades().get(1).getTitulo())
				+ String.format("  * : %s\n", printLp.getActividades().get(2).getTitulo())
				+ String.format("  * : %s\n", printLp.getActividades().get(3).getTitulo())
				+ String.format("  * : %s\n", printLp.getActividades().get(4).getTitulo())
				+ "\n"
				);
		

		} catch(ParseException e) {
			System.out.println("Error al convertir la fecha: " + e.getMessage());
		}
		
		}
	
	
	public static void main(String[] args) {
		
		try {
			correrApp();
		} catch (ModificarEstudianteLearningPathException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ModificarActividadesPreviasException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (ActividadPreviaCiclicoException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());;
		} catch (EstudianteNoInscritoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ActividadYaCompletadaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
