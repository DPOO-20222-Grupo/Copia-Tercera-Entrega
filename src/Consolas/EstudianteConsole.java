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
import preguntas.Pregunta;
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

    public static void mostrarMenuEstudiante(Estudiante estudiante) {
        int opcion = -1; 
        do {
            System.out.println("\n== Menú Estudiante ==");
            System.out.println("1. Inscribirse en un Learning Path");
            System.out.println("2. Enviar Tarea");
            System.out.println("3. Enviar Examen");
            System.out.println("4. Responder Pregunta de Examen");
            System.out.println("5. Responder Pregunta de Encuesta");
            System.out.println("6. Responder Pregunta de Quiz");
            System.out.println("7. Registrar que se completó una encuesta o recurso");
            System.out.println("8. Ver Learning Paths Inscritos");
            System.out.println("9. Reseñar o calificar una actividad");
            System.out.println("10. Calificar un Learning Path");
            System.out.println("11. Cerrar sesión");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine()); 

                if (opcion < 1 || opcion > 11) {
                    System.out.println("Opción no válida. Por favor, seleccione una opción entre 1 y 11.");
                    continue;
                }

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
                        break;
                    case 10:
                        calificarLearningPath();
                        break;
                    case 11:
                        estudiante.logout();
                        System.out.println("Sesión cerrada.");
                        break;
                }

                aplicacion.descargarDatos();

            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Por favor, ingrese un número.");
            }
        } while (opcion != 11);
    }

    
    private static void inscribirLearningPath(Estudiante estudiante) {
        String idLearningPath;

        do {
            System.out.print("Ingrese el id del Learning Path: ");
            idLearningPath = scanner.nextLine().trim(); 

            if (idLearningPath.isEmpty()) {
                System.out.println("La entrada no puede estar vacía. Por favor, ingrese un ID válido.");
            }
        } while (idLearningPath.isEmpty()); 

        try {
            LearningPath learningPath = aplicacion.getLearningPath(idLearningPath);
            aplicacion.inscribirEstudianteLearningPath(estudiante, learningPath);
            System.out.println("Estudiante inscrito en el Learning Path exitosamente.");
        } catch (ModificarEstudianteLearningPathException e) {
            System.out.println("Error al inscribir el estudiante en el Learning Path: " + e.getMessage());
        }
    }

    private static void enviarTarea() {
        String loginEstudiante, idLearningPath, tituloTarea;

        do {
            System.out.print("Ingrese el login del estudiante: ");
            loginEstudiante = scanner.nextLine().trim();
            if (loginEstudiante.isEmpty()) {
                System.out.println("El login del estudiante no puede estar vacío. Por favor, ingrese un valor válido.");
            }
        } while (loginEstudiante.isEmpty());

        do {
            System.out.print("Ingrese el id del Learning Path: ");
            idLearningPath = scanner.nextLine().trim();
            if (idLearningPath.isEmpty()) {
                System.out.println("El ID del Learning Path no puede estar vacío. Por favor, ingrese un valor válido.");
            }
        } while (idLearningPath.isEmpty());

        do {
            System.out.print("Ingrese el título de la tarea: ");
            tituloTarea = scanner.nextLine().trim();
            if (tituloTarea.isEmpty()) {
                System.out.println("El título de la tarea no puede estar vacío. Por favor, ingrese un valor válido.");
            }
        } while (tituloTarea.isEmpty());

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
        String loginEstudiante, idLearningPath, tituloExamen;

        do {
            System.out.print("Ingrese el login del estudiante: ");
            loginEstudiante = scanner.nextLine().trim();
            if (loginEstudiante.isEmpty()) {
                System.out.println("El login del estudiante no puede estar vacío. Por favor, ingrese un valor válido.");
            }
        } while (loginEstudiante.isEmpty());

        do {
            System.out.print("Ingrese el id del Learning Path: ");
            idLearningPath = scanner.nextLine().trim();
            if (idLearningPath.isEmpty()) {
                System.out.println("El ID del Learning Path no puede estar vacío. Por favor, ingrese un valor válido.");
            }
        } while (idLearningPath.isEmpty());

        do {
            System.out.print("Ingrese el título del examen: ");
            tituloExamen = scanner.nextLine().trim();
            if (tituloExamen.isEmpty()) {
                System.out.println("El título del examen no puede estar vacío. Por favor, ingrese un valor válido.");
            }
        } while (tituloExamen.isEmpty());

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
        String loginEstudiante, tituloLearningPath, idExamen;

        do {
            System.out.print("Ingrese el login del estudiante: ");
            loginEstudiante = scanner.nextLine().trim();
            if (loginEstudiante.isEmpty()) {
                System.out.println("El login del estudiante no puede estar vacío. Por favor, ingrese un valor válido.");
            }
        } while (loginEstudiante.isEmpty());

        do {
            System.out.print("Ingrese el id del Learning Path: ");
            tituloLearningPath = scanner.nextLine().trim();
            if (tituloLearningPath.isEmpty()) {
                System.out.println("El ID del Learning Path no puede estar vacío. Por favor, ingrese un valor válido.");
            }
        } while (tituloLearningPath.isEmpty());

        do {
            System.out.print("Ingrese el id del examen: ");
            idExamen = scanner.nextLine().trim();
            if (idExamen.isEmpty()) {
                System.out.println("El ID del examen no puede estar vacío. Por favor, ingrese un valor válido.");
            }
        } while (idExamen.isEmpty());

        try {
            Estudiante estudiante = aplicacion.getEstudiante(loginEstudiante);
            LearningPath learningPath = aplicacion.getLearningPath(tituloLearningPath);
            Examen examen = (Examen) aplicacion.getActividad(idExamen, "Examen");

            for (PreguntaAbierta pregunta : examen.getPreguntas()) {
                String respuesta;

                do {
                    System.out.println("Pregunta: " + pregunta.getEnunciado());
                    System.out.print("Ingrese la respuesta: ");
                    respuesta = scanner.nextLine().trim();
                    if (respuesta.isEmpty()) {
                        System.out.println("La respuesta no puede estar vacía. Por favor, ingrese un valor válido.");
                    }
                } while (respuesta.isEmpty());

                aplicacion.responderPreguntaExamen(examen, estudiante, learningPath, pregunta, respuesta);
                System.out.println("Respuesta registrada exitosamente.\n");
            }
        } catch (Exception e) {
            System.out.println("Error al registrar la respuesta: " + e.getMessage());
        }
    }

    private static void responderPreguntaEncuesta() {
        String loginEstudiante, idLearningPath, idEncuesta;

        do {
            System.out.print("Ingrese el login del estudiante: ");
            loginEstudiante = scanner.nextLine().trim();
            if (loginEstudiante.isEmpty()) {
                System.out.println("El login del estudiante no puede estar vacío. Por favor, ingrese un valor válido.");
            }
        } while (loginEstudiante.isEmpty());

        do {
            System.out.print("Ingrese el id del Learning Path: ");
            idLearningPath = scanner.nextLine().trim();
            if (idLearningPath.isEmpty()) {
                System.out.println("El ID del Learning Path no puede estar vacío. Por favor, ingrese un valor válido.");
            }
        } while (idLearningPath.isEmpty());

        do {
            System.out.print("Ingrese el id de la encuesta: ");
            idEncuesta = scanner.nextLine().trim();
            if (idEncuesta.isEmpty()) {
                System.out.println("El ID de la encuesta no puede estar vacío. Por favor, ingrese un valor válido.");
            }
        } while (idEncuesta.isEmpty());

        try {
            Estudiante estudiante = aplicacion.getEstudiante(loginEstudiante);
            LearningPath learningPath = aplicacion.getLearningPath(idLearningPath);
            Encuesta encuesta = aplicacion.getMapaEncuestas().get(idEncuesta);

            if (encuesta == null) {
                throw new Exception("Encuesta no encontrada con el ID proporcionado.");
            }

            for (PreguntaAbierta pregunta : encuesta.getPreguntas()) {
                String respuesta;

                do {
                    System.out.println("Pregunta: " + pregunta.getEnunciado());
                    System.out.print("Ingrese la respuesta: ");
                    respuesta = scanner.nextLine().trim();
                    if (respuesta.isEmpty()) {
                        System.out.println("La respuesta no puede estar vacía. Por favor, ingrese un valor válido.");
                    }
                } while (respuesta.isEmpty());

                aplicacion.responderPreguntaEncuesta(encuesta, estudiante, learningPath, pregunta, respuesta);
                System.out.println("Respuesta registrada exitosamente.\n");
            }
        } catch (Exception e) {
            System.out.println("Error al registrar la respuesta: " + e.getMessage());
        }
    }

    private static void responderPreguntaQuiz() {
        String loginEstudiante, idLearningPath, idQuiz;

        do {
            System.out.print("Ingrese el login del estudiante: ");
            loginEstudiante = scanner.nextLine().trim();
            if (loginEstudiante.isEmpty()) {
                System.out.println("El login del estudiante no puede estar vacío. Por favor, ingrese un valor válido.");
            }
        } while (loginEstudiante.isEmpty());

        do {
            System.out.print("Ingrese el id del Learning Path: ");
            idLearningPath = scanner.nextLine().trim();
            if (idLearningPath.isEmpty()) {
                System.out.println("El ID del Learning Path no puede estar vacío. Por favor, ingrese un valor válido.");
            }
        } while (idLearningPath.isEmpty());

        do {
            System.out.print("Ingrese el id del quiz: ");
            idQuiz = scanner.nextLine().trim();
            if (idQuiz.isEmpty()) {
                System.out.println("El ID del quiz no puede estar vacío. Por favor, ingrese un valor válido.");
            }
        } while (idQuiz.isEmpty());

        try {
            Estudiante estudiante = aplicacion.getEstudiante(loginEstudiante);
            LearningPath learningPath = aplicacion.getLearningPath(idLearningPath);
            Quiz quiz = (Quiz) aplicacion.getActividad(idQuiz, "Quiz");

            if (quiz == null) {
                throw new Exception("Quiz no encontrado con el ID proporcionado.");
            }

            for (Pregunta pregunta : quiz.getPreguntas()) {
                if (pregunta instanceof PreguntaSeleccionMultiple) {
                    PreguntaSeleccionMultiple preguntaSeleccionMultiple = (PreguntaSeleccionMultiple) pregunta;

                    System.out.println("Pregunta: " + preguntaSeleccionMultiple.getEnunciado());
                    System.out.println("1. " + preguntaSeleccionMultiple.getOpcion1());
                    System.out.println("2. " + preguntaSeleccionMultiple.getOpcion2());
                    System.out.println("3. " + preguntaSeleccionMultiple.getOpcion3());
                    System.out.println("4. " + preguntaSeleccionMultiple.getOpcion4());

                    int respuesta = -1;
                    do {
                        System.out.print("Ingrese el número de opción (1-4): ");
                        if (scanner.hasNextInt()) {
                            respuesta = scanner.nextInt();
                            scanner.nextLine();  
                            if (respuesta < 1 || respuesta > 4) {
                                System.out.println("Opción inválida. Debe ser un número entre 1 y 4.");
                            }
                        } else {
                            System.out.println("Por favor, ingrese un número válido.");
                            scanner.nextLine();  
                        }
                    } while (respuesta < 1 || respuesta > 4);

                    aplicacion.responderPreguntaQuiz(quiz, estudiante, learningPath, preguntaSeleccionMultiple, respuesta);
                } else {
                    System.out.println("Pregunta no es de tipo Selección Múltiple.");
                }
            }

            System.out.println("Respuestas registradas exitosamente.");

        } catch (Exception e) {
            System.out.println("Error al registrar las respuestas: " + e.getMessage());
        }
    }
  
    public static void completarEncuestaRecurso(Estudiante estudiante) {
        String idActividad, tipo, idLearningPath;

        do {
            System.out.print("Ingrese el ID de la actividad (encuesta o recurso): ");
            idActividad = scanner.nextLine().trim();
            if (idActividad.isEmpty()) {
                System.out.println("El ID de la actividad no puede estar vacío. Por favor, ingrese un valor válido.");
            }
        } while (idActividad.isEmpty());

        do {
            System.out.print("Ingrese el tipo de la actividad (Encuesta o Recurso): ");
            tipo = scanner.nextLine().trim();
            if (tipo.isEmpty()) {
                System.out.println("El tipo de la actividad no puede estar vacío. Por favor, ingrese un valor válido.");
            } else if (!tipo.equals("Encuesta") && !tipo.equals("Recurso")) {
                System.out.println("El tipo de actividad debe ser 'Encuesta' o 'Recurso'.");
            }
        } while (tipo.isEmpty() || (!tipo.equals("Encuesta") && !tipo.equals("Recurso")));

        Actividad actividad = aplicacion.getActividad(idActividad, tipo);
        if (actividad == null) {
            System.out.println("Actividad no encontrada.");
            return;
        }

        do {
            System.out.print("Ingrese el id del LearningPath al que pertenece la actividad: ");
            idLearningPath = scanner.nextLine().trim();
            if (idLearningPath.isEmpty()) {
                System.out.println("El ID del LearningPath no puede estar vacío. Por favor, ingrese un valor válido.");
            }
        } while (idLearningPath.isEmpty());

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
    	    String idActividad, tipo;

    	    do {
    	        System.out.println("Indique el ID de la actividad que desea reseñar o calificar: ");
    	        idActividad = scanner.nextLine().trim();
    	        if (idActividad.isEmpty()) {
    	            System.out.println("El ID de la actividad no puede estar vacío. Por favor, ingrese un valor válido.");
    	        }
    	    } while (idActividad.isEmpty());

    	    do {
    	        System.out.println("Indique el tipo de la actividad: ");
    	        tipo = scanner.nextLine().trim();
    	        if (tipo.isEmpty()) {
    	            System.out.println("El tipo de la actividad no puede estar vacío. Por favor, ingrese un valor válido.");
    	        } else if (!tipo.equals("Encuesta") && !tipo.equals("recurso") && !tipo.equals("Examen") && !tipo.equals("Quiz")) {
    	            System.out.println("El tipo de actividad debe ser 'Encuesta', 'Recurso', 'Examen' o 'Quiz'.");
    	        }
    	    } while (tipo.isEmpty() || (!tipo.equals("Encuesta") && !tipo.equals("Recurso") && !tipo.equals("Examen") && !tipo.equals("Quiz")));

    	    Actividad actividad = aplicacion.getActividad(idActividad, tipo);
    	    if (actividad == null) {
    	        System.out.println("Actividad no encontrada.");
    	        return;
    	    }

    	    int opcion;
    	    do {
    	        System.out.println("Seleccione la acción que desea realizar: ");
    	        System.out.println("1. Reseñar");
    	        System.out.println("2. Calificar");
    	        System.out.print("Opción escogida: ");
    	        try {
    	            opcion = Integer.parseInt(scanner.nextLine());
    	            if (opcion < 1 || opcion > 2) {
    	                System.out.println("Opción inválida. Por favor, elija 1 o 2.");
    	            }
    	        } catch (NumberFormatException e) {
    	            System.out.println("Entrada inválida. Por favor ingrese un número válido.");
    	            opcion = 0;  
    	        }
    	    } while (opcion < 1 || opcion > 2);


    	    if (opcion == 1) {

    	        System.out.println("Ingrese la reseña que desea dejar a la actividad:\n");
    	        String resena = scanner.nextLine().trim();
    	        if (resena.isEmpty()) {
    	            System.out.println("La reseña no puede estar vacía.");
    	        } else {
    	            aplicacion.resenarActividad(actividad, resena);
    	            System.out.println("Reseña enviada exitosamente.");
    	        }
    	    } else if (opcion == 2) {
    	        double calificacion;
    	        do {
    	            System.out.println("Ingrese la calificación que desea dejar a la actividad (0-5): ");
    	            try {
    	                calificacion = Double.parseDouble(scanner.nextLine());
    	                if (calificacion < 0 || calificacion > 5) {
    	                    System.out.println("La calificación debe estar entre 0 y 5.");
    	                    calificacion = -1; 
    	                }
    	            } catch (NumberFormatException e) {
    	                System.out.println("Entrada inválida. Por favor ingrese un número válido entre 0 y 5.");
    	                calificacion = -1; 
    	            }
    	        } while (calificacion < 0 || calificacion > 5);
    	        
    	        aplicacion.calificarActividad(actividad, calificacion);
    	        System.out.println("Calificación registrada exitosamente.");
    	    }
    	}

     
     private static void calificarLearningPath() {
    	    String idLP;
    	    double calificacion;

    	    do {
    	        System.out.println("Indique el ID del Learning Path que desea calificar: ");
    	        idLP = scanner.nextLine().trim();
    	        if (idLP.isEmpty()) {
    	            System.out.println("El ID del Learning Path no puede estar vacío. Por favor, ingrese un valor válido.");
    	        }
    	    } while (idLP.isEmpty());

    	    LearningPath learningPath = aplicacion.getLearningPath(idLP);
    	    if (learningPath == null) {
    	        System.out.println("Learning Path no encontrado.");
    	        return;
    	    }

    	    do {
    	        System.out.println("Ingrese la calificación que desea dejar al Learning Path (0-5): ");
    	        try {
    	            calificacion = Double.parseDouble(scanner.nextLine());
    	            if (calificacion < 0 || calificacion > 5) {
    	                System.out.println("La calificación debe estar entre 0 y 5. Por favor, ingrese un valor válido.");
    	                calificacion = -1; 
    	            }
    	        } catch (NumberFormatException e) {
    	            System.out.println("Entrada inválida. Por favor ingrese un número válido entre 0 y 5.");
    	            calificacion = -1; 
    	        }
    	    } while (calificacion < 0 || calificacion > 5);

    	    aplicacion.calificarLearningPath(learningPath, calificacion);
    	    System.out.println("Learning Path calificado exitosamente.");
    	}
}
