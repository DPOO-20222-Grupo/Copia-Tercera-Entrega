package Consolas;

import java.util.Scanner;

import actividades.Actividad;
import actividades.Encuesta;
import actividades.Examen;
import actividades.Quiz;
import actividades.Tarea;
import exceptions.ModificarEstudianteLearningPathException;
import interfaz.Aplicacion;
import learningPath.LearningPath;
import preguntas.PreguntaAbierta;
import preguntas.PreguntaCerrada;
import user.Estudiante;

public class EstudianteConsole {
   
	private static Aplicacion aplicacion = new Aplicacion("usuarios.json", "lp.json", "preguntas.json", "actividades.json");  
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        
        System.out.println("== Sistema de Estudiantes ==");
        System.out.print("Ingrese su login: ");
        String login = scanner.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String password = scanner.nextLine();

        Estudiante estudiante = aplicacion.getMapaEstudiantes().get(login); 
        if (estudiante != null && estudiante.login(login, password)) {
            System.out.println("Autenticación exitosa. Bienvenido, " + estudiante.getNombre());
            mostrarMenuEstudiante(estudiante);
        } else {
            System.out.println("Credenciales incorrectas.");
        }
        
    }

    public static void mostrarMenuEstudiante(Estudiante estudiante) {
        int opcion;
        do {
            System.out.println("\n== Menú Estudiante ==");
            System.out.println("1. Inscribirse en un Learning Path");
            System.out.println("2. Enviar Tarea");
            System.out.println("3. Enviar Examen");
            System.out.println("4. Responder Pregunta de Examen");
            System.out.println("5. Responder Pregunta de Encuesta");
            System.out.println("6. Responder Pregunta de Quiz");
            System.out.println("7. Rgistar que se completo una encuesta o recurso");
            System.out.println("8. Ver Learning Paths Inscritos");
            System.out.println("9. Reseñar o calificar una actividad");
            System.out.println("10. Calificar un Learning Path");
            System.out.println("11. Cerrar sesión");
            System.out.print("Seleccione una opción: ");
            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    inscribirLearningPath(estudiante);
                    break;
                case 2:
                	enviarTarea();
                	break;
                case 3:
                	enviarExamen();
                	break;
                case 4:
                	responderPreguntaExamen();
                	break;
                case 5:
                	responderPreguntaEncuesta();
                	break;
                case 6:
                	responderPreguntaQuiz();
                	break;
                case 7:
                	completarEncuestaRecurso(estudiante);
                	break;
                case 8:
                    verLearningPaths(estudiante);
                    break;
                case 9:
                	calificarResenarActividad();
                case 10:
                	calificarLearningPath();
                case 11:
                    estudiante.logout();
                    System.out.println("Sesión cerrada.");
                    aplicacion.descargarDatos();
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion!= 11);
    }
    
	private static void inscribirLearningPath(Estudiante estudiante) {

        System.out.print("Ingrese el id del Learning Path: ");
        String idLearningPath = scanner.nextLine();

        try {
            LearningPath learningPath = aplicacion.getLearningPath(idLearningPath);
            aplicacion.inscribirEstudianteLearningPath(estudiante, learningPath);
            System.out.println("Estudiante inscrito en el Learning Path exitosamente.");
        } catch (ModificarEstudianteLearningPathException e) {
            System.out.println("Error al inscribir el estudiante en el Learning Path: " + e.getMessage());
        }
    }

    private static void enviarTarea() {
        System.out.print("Ingrese el login del estudiante: ");
        String loginEstudiante = scanner.nextLine();

        System.out.print("Ingrese el id del Learning Path: ");
        String idLearningPath = scanner.nextLine();

        System.out.print("Ingrese el título de la tarea: ");
        String tituloTarea = scanner.nextLine();

        try {
            Estudiante estudiante = aplicacion.getEstudiante(loginEstudiante);
            LearningPath learningPath = aplicacion.getLearningPath(idLearningPath);
            Tarea tarea = aplicacion.getMapaTareas().get(tituloTarea);
            
            aplicacion.enviarTarea(tarea, estudiante, learningPath);
            System.out.println("Tarea enviada exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al enviar la tarea: " + e.getMessage());
        }
    }

    private static void enviarExamen() {
        System.out.print("Ingrese el login del estudiante: ");
        String loginEstudiante = scanner.nextLine();

        System.out.print("Ingrese el id del Learning Path: ");
        String idLearningPath = scanner.nextLine();

        System.out.print("Ingrese el título del examen: ");
        String tituloExamen = scanner.nextLine();

        try {
            Estudiante estudiante = aplicacion.getEstudiante(loginEstudiante);
            LearningPath learningPath = aplicacion.getLearningPath(idLearningPath);
            Examen examen = (Examen) aplicacion.getActividad(tituloExamen, "Examen");
            
            aplicacion.enviarExamen(examen, estudiante, learningPath);
            System.out.println("Examen enviado exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al enviar el examen: " + e.getMessage());
        }
    }   
    
    private static void responderPreguntaExamen() {
        System.out.print("Ingrese el login del estudiante: ");
        String loginEstudiante = scanner.nextLine();

        System.out.print("Ingrese el id del Learning Path: ");
        String tituloLearningPath = scanner.nextLine();

        System.out.print("Ingrese el id del examen: ");
        String idExamen = scanner.nextLine();

        try {
            // Obtén los objetos necesarios
            Estudiante estudiante = aplicacion.getEstudiante(loginEstudiante);
            LearningPath learningPath = aplicacion.getLearningPath(tituloLearningPath);
            Examen examen = (Examen) aplicacion.getActividad(idExamen, "Examen");

            // Recorre todas las preguntas del examen
            for (PreguntaAbierta pregunta : examen.getPreguntas()) {
                // Muestra la pregunta y solicita la respuesta
                System.out.println("Pregunta: " + pregunta.getEnunciado());
                System.out.print("Ingrese la respuesta: ");
                String respuesta = scanner.nextLine();

                // Registra la respuesta
                aplicacion.responderPreguntaExamen(examen, estudiante, learningPath, pregunta, respuesta);
                System.out.println("Respuesta registrada exitosamente.\n");
            }
        } catch (Exception e) {
            System.out.println("Error al registrar la respuesta: " + e.getMessage());
        }
    }


    private static void responderPreguntaEncuesta() {
        System.out.print("Ingrese el login del estudiante: ");
        String loginEstudiante = scanner.nextLine();

        System.out.print("Ingrese el id del Learning Path: ");
        String idLearningPath = scanner.nextLine();

        System.out.print("Ingrese el id de la encuesta: ");
        String idEncuesta = scanner.nextLine();

        try {
            // Obtén los objetos necesarios
            Estudiante estudiante = aplicacion.getEstudiante(loginEstudiante);
            LearningPath learningPath = aplicacion.getLearningPath(idLearningPath);
            Encuesta encuesta = aplicacion.getMapaEncuestas().get(idEncuesta);

            // Recorre todas las preguntas de la encuesta
            for (PreguntaAbierta pregunta : encuesta.getPreguntas()) {
                // Muestra la pregunta y solicita la respuesta
                System.out.println("Pregunta: " + pregunta.getEnunciado());
                System.out.print("Ingrese la respuesta: ");
                String respuesta = scanner.nextLine();

                // Registra la respuesta
                aplicacion.responderPreguntaEncuesta(encuesta, estudiante, learningPath, pregunta, respuesta);
                System.out.println("Respuesta registrada exitosamente.\n");
            }
        } catch (Exception e) {
            System.out.println("Error al registrar la respuesta: " + e.getMessage());
        }
    }


    private static void responderPreguntaQuiz() {
        System.out.print("Ingrese el login del estudiante: ");
        String loginEstudiante = scanner.nextLine();

        System.out.print("Ingrese el id del Learning Path: ");
        String idLearningPath = scanner.nextLine();

        System.out.print("Ingrese el id del quiz: ");
        String idQuiz = scanner.nextLine();

        try {
            Estudiante estudiante = aplicacion.getEstudiante(loginEstudiante);
            LearningPath learningPath = aplicacion.getLearningPath(idLearningPath);
            Quiz quiz = aplicacion.getMapaQuices().get(idQuiz);

            // Recorrer las preguntas del quiz
            for (PreguntaCerrada pregunta : quiz.getPreguntas()) {
                System.out.println("Pregunta: " + pregunta.getEnunciado());
                
                System.out.print("Ingrese el número de opción (1-4): ");
                int respuesta = scanner.nextInt();
                scanner.nextLine();  

                aplicacion.responderPreguntaQuiz(quiz, estudiante, learningPath, pregunta, respuesta);
            }

            System.out.println("Respuestas registradas exitosamente.");

        } catch (Exception e) {
            System.out.println("Error al registrar las respuestas: " + e.getMessage());
        }
    }

    
    public static void completarEncuestaRecurso(Estudiante estudiante) {
        
        System.out.print("Ingrese el ID de la actividad (encuesta o recurso): ");
        String idActividad = scanner.nextLine();
        System.out.print("Ingrese el tipo de la actividad (encuesta o recurso): ");
        String tipo = scanner.nextLine();
        
        Actividad actividad = aplicacion.getActividad(idActividad,tipo);
        if (actividad == null) {
            System.out.println("Actividad no encontrada.");
            return;
        }
        
        System.out.print("Ingrese el id del LearningPath al que pertenece la actividad: ");
        String idLearningPath = scanner.nextLine();
        

        LearningPath learningPath = aplicacion.getLearningPath(idLearningPath);
        if (learningPath == null) {
            System.out.println("LearningPath no encontrado.");
            return;
        }
        
        aplicacion.completarEncuestaRecurso(actividad, estudiante, learningPath);
        
        System.out.println("Actividad completada exitosamente.");
    }


     private static void verLearningPaths(Estudiante estudiante) {
        System.out.println("== Learning Paths Inscritos ==");
        estudiante.getLearningPathsInscritos().forEach((id, seguimiento) -> {
            System.out.println("Learning Path: " + id);
        });
    }
    
     private static void calificarResenarActividad() {
     	System.out.println("Indique el ID de la actividad que desea reseñar o calificar: ");
     	String idActividad = scanner.nextLine();
     	System.out.println("Indique el tipo de la actividad: ");
     	String tipo = scanner.nextLine();
     	
     	Actividad actividad = aplicacion.getActividad(idActividad, tipo);
     	
     	if (actividad != null) {
     		
     		System.out.println("Seleccione la acción que desea realizar: ");
     		System.out.println("1. Reseñar");
     		System.out.println("2. Calificar");
     		System.out.println("Opción escogida: ");
     		
     		int opcion = Integer.parseInt(scanner.nextLine());
     		
     		if (opcion == 1) {
     			System.out.println("Ingrese la reseña que desea dejar a la actividad:\n");
     			String resena = scanner.nextLine();
     			aplicacion.resenarActividad(actividad, resena);
     		}
     		else if (opcion == 2) {
     			System.out.println("Ingrese la calificación que desea dejar a la actividad: ");
     			double calificacion = Double.parseDouble(scanner.nextLine());
     			aplicacion.calificarActividad(actividad, calificacion);
     		}
     		
     		else {
     			System.out.println("Opción invalida");
     			return;
     		}
     	}
     	
     	else {
     		System.out.println("Actividad no encontrada");
     		return;
     	}
     }
     
     private static void calificarLearningPath() {
     	System.out.println("Indique el ID del Learning Path que desea calificar: ");
     	String idLP = scanner.nextLine();
     	
     	LearningPath learningPath = aplicacion.getLearningPath(idLP);
     	
     	if (learningPath != null) {
     		System.out.println("Ingrese la calificación que desea dejar al Learning Path: ");
 			double calificacion = Double.parseDouble(scanner.nextLine());
 			aplicacion.calificarLearningPath(learningPath, calificacion);
     	}
     }
     

}
