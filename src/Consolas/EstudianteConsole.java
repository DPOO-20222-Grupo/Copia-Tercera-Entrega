package Consolas;

import java.util.Scanner;
import actividades.Encuesta;
import actividades.Examen;
import actividades.Quiz;
import actividades.Tarea;
import exceptions.ModificarEstudianteLearningPathException;
import interfaz.Aplicacion;
import learningPath.LearningPath;
import preguntas.PreguntaAbierta;
import preguntas.PreguntaSeleccionMultiple;
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

    private static void mostrarMenuEstudiante(Estudiante estudiante) {
        int opcion;
        do {
            System.out.println("\n== Menú Estudiante ==");
            System.out.println("1. Inscribirse en un Learning Path");
            System.out.println("2. Enviar Tarea");
            System.out.println("3. Enviar Examen");
            System.out.println("4. Responder Pregunta de Examen");
            System.out.println("5. Responder Pregunta de Encuesta");
            System.out.println("6. Responder Pregunta de Quiz");
            System.out.println("7. Completar Encuesta Recurso");
            System.out.println("8. Ver Learning Paths Inscritos");
            System.out.println("9. Cerrar sesión");
            System.out.print("Seleccione una opción: ");
            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    inscribirLearningPath();
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
                	responderPreguntaQuiz();
                	break;
                case 8:
                    verLearningPaths(estudiante);
                    break;
                case 9:
                    estudiante.logout();
                    System.out.println("Sesión cerrada.");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (estudiante.isLoggedIn());
    }
    
    private static void inscribirLearningPath() {
        System.out.print("Ingrese el login del estudiante: ");
        String loginEstudiante = scanner.nextLine();

        System.out.print("Ingrese el id del Learning Path: ");
        String idLearningPath = scanner.nextLine();

        try {
            Estudiante estudiante = aplicacion.getEstudiante(loginEstudiante);
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
            Examen examen = aplicacion.getMapaExamenes().get(tituloExamen);
            
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

        System.out.print("Ingrese el id de la pregunta: ");
        String idPregunta = scanner.nextLine();

        System.out.print("Ingrese la respuesta: ");
        String respuesta = scanner.nextLine();

        try {
            Estudiante estudiante = aplicacion.getEstudiante(loginEstudiante);
            LearningPath learningPath = aplicacion.getLearningPath(tituloLearningPath);
            Examen examen = aplicacion.getMapaExamenes().get(idExamen);
            PreguntaAbierta pregunta = aplicacion.getMapaPreguntasAbiertas().get(idPregunta);
            
            aplicacion.responderPreguntaExamen(examen, estudiante, learningPath, pregunta, respuesta);
            System.out.println("Respuesta registrada exitosamente.");
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

        System.out.print("Ingrese el id de la pregunta: ");
        String idPregunta = scanner.nextLine();

        System.out.print("Ingrese la respuesta: ");
        String respuesta = scanner.nextLine();

        try {
            Estudiante estudiante = aplicacion.getEstudiante(loginEstudiante);
            LearningPath learningPath = aplicacion.getLearningPath(idLearningPath);
            Encuesta encuesta = aplicacion.getMapaEncuestas().get(idEncuesta);
            PreguntaAbierta pregunta = aplicacion.getMapaPreguntasAbiertas().get(idPregunta);
            
            aplicacion.responderPreguntaEncuesta(encuesta, estudiante, learningPath, pregunta, respuesta);
            System.out.println("Respuesta registrada exitosamente.");
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

        System.out.print("Ingrese el id de la pregunta: ");
        String idPregunta = scanner.nextLine();

        System.out.print("Ingrese la respuesta (número de opción): ");
        int respuesta = scanner.nextInt();
        scanner.nextLine();  

        try {
            Estudiante estudiante = aplicacion.getEstudiante(loginEstudiante);
            LearningPath learningPath = aplicacion.getLearningPath(idLearningPath);
            Quiz quiz = aplicacion.getMapaQuices().get(idQuiz);
            PreguntaSeleccionMultiple pregunta = aplicacion.getMapaPreguntasSeleccionMultiple().get(idPregunta);
            
            aplicacion.responderPreguntaQuiz(quiz, estudiante, learningPath, pregunta, respuesta);
            System.out.println("Respuesta registrada exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al registrar la respuesta: " + e.getMessage());
        }
    }

     private static void verLearningPaths(Estudiante estudiante) {
        System.out.println("== Learning Paths Inscritos ==");
        estudiante.getLearningPathsInscritos().forEach((id, seguimiento) -> {
            System.out.println("Learning Path: " + id);
        });
    }

}
