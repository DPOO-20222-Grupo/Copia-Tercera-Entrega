package Interfaz;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import Actividades.Actividad;
import Actividades.Encuesta;
import Actividades.Examen;
import Actividades.Quiz;
import Actividades.RevisarRecurso;
import Actividades.Tarea;
import LearningPath.LearningPath;
import Preguntas.PreguntaAbierta;
import Preguntas.PreguntaSeleccionMultiple;
import User.Estudiante;
import User.Profesor;

public class Main extends Aplicacion {
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
		List<PreguntaSeleccionMultiple> preguntasCerradas = new ArrayList<PreguntaSeleccionMultiple>();
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
		aplicacion.descargarDatos(aplicacion.getMapaExamenes(), aplicacion.getMapaEncuestas(),
				aplicacion.getMapaQuices(), aplicacion.getMapaRevisarRecurso(), aplicacion.getMapaTareas(),
				aplicacion.getMapaEstudiantes(), aplicacion.getMapaProfesores(), aplicacion.getMapaPreguntasAbiertas(),
				aplicacion.getMapaPreguntasSeleccionMultiple(), aplicacion.getMapaLearningPaths());
		
		// Re-Carga de Archivos
		Aplicacion aplicacion_recargada = new Aplicacion(archivoUsuarios, archivoLP, archivoPreguntas, archivoActividades);
		
		// Pruebas para que la Re-Carga está bien hecha
		
		} catch(ParseException e) {
			System.out.println("Error al convertir la fecha: " + e.getMessage());
		}
		
		}
	
	public static void main(String[] args) {
		correrApp();
		
	}
	
	
	
}
