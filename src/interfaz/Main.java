package interfaz;

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
import learningPath.LearningPath;
import persistenciaDatos.LocalDateTimeAdapter;
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

	
	public static void correrApp() {
		try {
		
		Aplicacion aplicacion = new Aplicacion();
		
		// Pruebas de Registro de Usuarios
		
		Estudiante stud1 = new Estudiante("d.martinezf", "Banano123", "Diego Martinez");
		Estudiante stud2 = new Estudiante("a.linares", "Banano123", "Andres Linares");
		Profesor prof1 = new Profesor("l.munera", "Tristeza30", "Lina Munera");
		
		aplicacion.registrarUsuario(prof1);
		aplicacion.registrarUsuario(stud1);
		aplicacion.registrarUsuario(stud2);
		
		// Pruebas de Registro de Preguntas
		
		PreguntaAbierta pregAbierta = new PreguntaAbierta("Ingrese su nombre: ", "Registro de Nombres");
		PreguntaSeleccionMultiple pregCerrada = new PreguntaSeleccionMultiple("¿Qué color no es primario?", "Colores", "Azul", "Rojo", "Amarillo", "Negro", 4);
		
		aplicacion.registrarPregunta(pregAbierta);
		aplicacion.registrarPregunta(pregCerrada);
		
		// Pruebas de Registro de Actividades
		
		
		List<PreguntaAbierta> preguntasAbiertas = new ArrayList<PreguntaAbierta>();
		preguntasAbiertas.add(pregAbierta);
		List<PreguntaCerrada> preguntasCerradas = new ArrayList<PreguntaCerrada>();
		List<String> objetivos = new ArrayList<String>();
		objetivos.add("Objetivo 1");
		objetivos.add("Objetivo 2");
		String fecha = "23-10-2024";
		SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");		
		Date date = formateador.parse(fecha);
		
		Encuesta en = new Encuesta("Autoevaluación", "Autoevaluación acitividad #1", objetivos, "Bajo", 15, date,
				 prof1, preguntasAbiertas);
		Quiz qu = new Quiz("Quiz1", "Primer Quiz", objetivos, "bajo", 45, date, prof1, (float) 3.5, preguntasCerradas);
		
		Tarea ta = new Tarea("Tarea1", "Tarea numero uno", objetivos, "bajo", 120, date, prof1);
		
		Examen ex = new Examen("Parcial", "Examen Parcial", objetivos, "medio", 120, date, prof1, preguntasAbiertas);
		
		RevisarRecurso rr = new RevisarRecurso("Ver video", "Ver video segunda guerra mundial", objetivos, "bajo", 45,
				date, "video", prof1, "enlace");
		
		aplicacion.registrarActividad(en);
		aplicacion.registrarActividad(qu);
		aplicacion.registrarActividad(ta);
		aplicacion.registrarActividad(ex);
		aplicacion.registrarActividad(rr);
		
		// Pruebas de Registro de LearningPaths
		
		List<Actividad> actividades = new ArrayList<Actividad>();
		actividades.add(en);
		actividades.add(ex);
		actividades.add(rr);
		actividades.add(qu);
		actividades.add(ta);
		
		HashMap<String, Boolean> mapaObligatorio = new HashMap<String, Boolean>();
		
		mapaObligatorio.put("Actividad1", true);
		
		LearningPath lp = new LearningPath("Introducción a las Pruebas", "Pruebas", objetivos, "mid",
				prof1, actividades, mapaObligatorio);
		
		aplicacion.registrarLearningPath(lp);		
		
		// Descarga de los Datos en Archivos JSON
		aplicacion.descargarDatos();
		
		// Re-Carga de Archivos
		Aplicacion aplicacion_recargada = new Aplicacion(archivoUsuarios, archivoLP, archivoPreguntas, archivoActividades);
		
		// Pruebas para asegurar que la Re-Carga está bien hecha
		
		Estudiante estudiante = aplicacion_recargada.getEstudiante("d.martinezf");
		Profesor profesor = aplicacion_recargada.getProfesor("l.munera");
		
		Quiz quiz = (Quiz) aplicacion_recargada.getActividad(aplicacion_recargada.generarLlaveLearningsActividades("Quiz1", "l.munera"), "Quiz");
		
		Examen examen = (Examen) aplicacion_recargada.getActividad(aplicacion_recargada.generarLlaveLearningsActividades("Parcial", "l.munera"), "Examen");

		Tarea tarea = (Tarea) aplicacion_recargada.getActividad(aplicacion_recargada.generarLlaveLearningsActividades("Tarea1", "l.munera"), "Tarea");

		RevisarRecurso revisarRecurso = (RevisarRecurso) aplicacion_recargada.getActividad(aplicacion_recargada.generarLlaveLearningsActividades("Ver video", "l.munera"), "RevisarRecurso");

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
				+ String.format("Login Profesor Creador: %s\n", printLp.getProfesorCreador().getLogin())
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
		correrApp();
		
	}
}
