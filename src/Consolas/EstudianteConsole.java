package Consolas;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Scanner; 

import actividades.Actividad;
import actividades.Encuesta;
import actividades.Examen;
import actividades.Quiz;
import actividades.RevisarRecurso;
import actividades.Tarea;
import exceptions.ActividadYaCompletadaException;
import exceptions.EstudianteNoInscritoException;
import exceptions.ModificarEstudianteLearningPathException;
import interfaz.Aplicacion;
import learningPath.LearningPath;
import preguntas.PreguntaAbierta;
import preguntas.PreguntaCerrada;
import preguntas.PreguntaSeleccionMultiple;
import seguimientoEstudiantes.SeguimientoActividad;
import seguimientoEstudiantes.SeguimientoExamen;
import seguimientoEstudiantes.SeguimientoLearningPath;
import seguimientoEstudiantes.SeguimientoQuiz;
import seguimientoEstudiantes.SeguimientoTarea;
import user.Estudiante;

public class EstudianteConsole {
   
	private  Aplicacion aplicacion;  
    private static Scanner scanner = new Scanner(System.in);

    
    public EstudianteConsole (Aplicacion aplicacion) {
    	this.aplicacion = aplicacion;
    }
    public void loginPlataforma(){
        
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

    public  void mostrarMenuEstudiante(Estudiante estudiante) {
        int opcion = -1; 
        do {
            System.out.println("\n== Menú Estudiante ==");
            System.out.println("1. Inscribirse en un Learning Path");
            System.out.println("2. Hacer Tarea");
            System.out.println("3. Responder Examen");
            System.out.println("4. Responder Encuesta");
            System.out.println("5. Responder Quiz");
            System.out.println("6. Revisar un recurso");
            System.out.println("7. Ver Learning Paths Inscritos");
            System.out.println("8. Ver progreso de un Learning Path");
            System.out.println("9. Reseñar o calificar una actividad");
            System.out.println("10. Calificar un Learning Path");
            System.out.println("11. Cerrar sesión");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine()); 

                if (opcion < 1 || opcion > 11) {
                    System.out.println("Opción no válida. Por favor, seleccione una opción entre 1 y 10.");
                    continue;
                }

                switch (opcion) {
                    case 1:
                        inscribirLearningPath(estudiante);
                        break;
                    case 2:
                    	enviarTarea(estudiante);
                    	break;
                    case 3:
                        responderPreguntaExamen(estudiante);
                        break;
                    case 4:
                        responderPreguntaEncuesta(estudiante);
                        break;
                    case 5:
                        responderPreguntaQuiz(estudiante);
                        break;
                    case 6:
                        completarEncuestaRecurso(estudiante);
                        break;
                    case 7:
                        verLearningPaths(estudiante);
                        break;
                    case 8:
                    	verProgresoLearningPath(estudiante);
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


            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Por favor, ingrese un número.");
            }
        } while (opcion != 11);
    }

    
    private  void inscribirLearningPath(Estudiante estudiante) {
        String tituloLearningPath;
        String loginProfesor;

        do {
            System.out.print("Ingrese el título del Learning Path al que se quiere inscribir: ");
            tituloLearningPath = scanner.nextLine().trim(); 

            if (tituloLearningPath.isEmpty()) {
                System.out.println("La entrada no puede estar vacía. Por favor, ingrese un título válido.");
            }
        } while (tituloLearningPath.isEmpty()); 
        
        do {
            System.out.print("Ingrese el login del profesor creador del Learning Path al que se quiere inscribir: ");
            loginProfesor = scanner.nextLine().trim(); 

            if (loginProfesor.isEmpty()) {
                System.out.println("La entrada no puede estar vacía. Por favor, ingrese un login válido.");
            }
        } while (loginProfesor.isEmpty()); 

        try {
        	String idLearningPath = aplicacion.generarLlaveLearningsActividades(tituloLearningPath, loginProfesor);
            LearningPath learningPath = aplicacion.getLearningPath(idLearningPath);
            if (learningPath != null) {
            	
            	aplicacion.inscribirEstudianteLearningPath(estudiante, learningPath);
                System.out.println("Estudiante inscrito en el Learning Path exitosamente.");
            }	
            else {
            	System.out.println("Learning Path no encontrado.");	
            }
            
        } catch (ModificarEstudianteLearningPathException e) {

            System.out.println("Error al inscribir el estudiante en el Learning Path: " + e.getMessage());
        }
    }
    
    private  void enviarTarea(Estudiante estudiante) {
        String  tituloLearningPath, loginProfesorLP, tituloTarea, loginProfesorTarea;



        do {
            System.out.print("Ingrese el título del Learning Path en el que se encuentra la tarea que desea realizar: ");
            tituloLearningPath = scanner.nextLine().trim();
            if (tituloLearningPath.isEmpty()) {
                System.out.println("El título del Learning Path no puede estar vacío. Por favor, ingrese un valor válido.");
            }
        } while (tituloLearningPath.isEmpty());
        
        do {
            System.out.print("Ingrese el login del profesor creador del Learning Path: ");
            loginProfesorLP = scanner.nextLine().trim();
            if (loginProfesorLP.isEmpty()) {
                System.out.println("El login del profesor creador del Learning Path no puede estar vacío. Por favor, ingrese un valor válido.");
            }
        } while (loginProfesorLP.isEmpty());


        do {
            System.out.print("Ingrese el título de la tarea: ");
            tituloTarea = scanner.nextLine().trim();
            if (tituloTarea.isEmpty()) {
                System.out.println("El título de la tarea no puede estar vacío. Por favor, ingrese un valor válido.");
            }
        } while (tituloTarea.isEmpty());
        
        do {
            System.out.print("Ingrese el login del profesor creador de la Tarea: ");
            loginProfesorTarea = scanner.nextLine().trim();
            if (loginProfesorTarea.isEmpty()) {
                System.out.println("El login del profesor creador de la Tarea no puede estar vacío. Por favor, ingrese un valor válido.");
            }
        } while (loginProfesorTarea.isEmpty());

        
        	String idLearningPath = aplicacion.generarLlaveLearningsActividades(tituloLearningPath, loginProfesorLP);
        	
            LearningPath learningPath = aplicacion.getLearningPath(idLearningPath);
            if (learningPath!= null) {
	            String idTarea = aplicacion.generarLlaveLearningsActividades(tituloTarea, loginProfesorTarea);
	            Tarea tarea = (Tarea) aplicacion.getActividad(idTarea, "Tarea");
	            if (tarea != null) {
	            	try {
	            	boolean check = aplicacion.revisarActividadesPrevias(tarea, estudiante, learningPath);
		            String impresion = String.format("Tarea: %s\n"+
		            								 "Descripcion: %s \n" +
		            								 "Duración estimada: %d minutos\n"
		            								 , tarea.getTitulo(),tarea.getDescripcion(),tarea.getDuracionMinutos());
		            
		        	if (check == false) {
		        		System.out.println("Advertencia: No se cumplen los prerrequisitos en el Learning Path de la actividad");
		        	}
		            System.out.println(impresion);
		            
			            aplicacion.enviarTarea(tarea, estudiante, learningPath);
			            System.out.println("¿Aproximadamente cuánto tiempo, en minutos, le tomó realizar la actividad?: ");
			            
			            int duracion = Integer.parseInt(scanner.nextLine());
			            
			            System.out.println("¿Por cuál metodo envió esta tarea?: ");
			            String metodoEnvio = scanner.nextLine();
			            
			            aplicacion.actualizarDuracionDesarrolloActividad(estudiante, learningPath, tarea, duracion);
			            aplicacion.actualizarMetodoEnvioTarea(estudiante, learningPath, tarea, metodoEnvio);
			            
			            System.out.println("Tarea enviada exitosamente.");
			            
			            mostrarActividadesSeguimiento(tarea);
		            
		            }
		            
		            catch (Exception e) {
		            	System.out.println(e.getMessage());
		            }
	            }
	            else {
	            	System.out.println("Tarea no encontrada");
	            	return;
	            }
            }
            else {
            	System.out.println("Learning Path no encontrado");
            	return;
            }
        
    }
    
    private  void responderPreguntaExamen(Estudiante estudiante) {
        String tituloLearningPath, loginLearningPath, tituloExamen, loginExamen;



        do {
            System.out.print("Ingrese el título del Learning Path del examen que quiere realizar: ");
            tituloLearningPath = scanner.nextLine().trim();
            if (tituloLearningPath.isEmpty()) {
                System.out.println("El título del Learning Path no puede estar vacío. Por favor, ingrese un valor válido.");
            }
        } while (tituloLearningPath.isEmpty());
        
        do {
            System.out.print("Ingrese el login del profesor creador del Learning Path: ");
            loginLearningPath = scanner.nextLine().trim();
            if (loginLearningPath.isEmpty()) {
                System.out.println("El profesor del Learning Path no puede estar vacío. Por favor, ingrese un valor válido.");
            }
        } while (loginLearningPath.isEmpty());

        do {
            System.out.print("Ingrese el título del examen: ");
            tituloExamen = scanner.nextLine().trim();
            if (tituloExamen.isEmpty()) {
                System.out.println("El título del examen no puede estar vacío. Por favor, ingrese un valor válido.");
            }
        } while (tituloExamen.isEmpty());
        
        do {
            System.out.print("Ingrese el login del profesor creador del examen: ");
            loginExamen = scanner.nextLine().trim();
            if (loginExamen.isEmpty()) {
                System.out.println("El profesor del examen no puede estar vacío. Por favor, ingrese un valor válido.");
            }
        } while (loginExamen.isEmpty());

        try {
        	
        	String idLearningPath = aplicacion.generarLlaveLearningsActividades(tituloLearningPath, loginLearningPath);
        	String idExamen = aplicacion.generarLlaveLearningsActividades(tituloExamen, loginExamen);
        	
            LearningPath learningPath = aplicacion.getLearningPath(idLearningPath);
            
            if (learningPath != null) {
	            Examen examen = (Examen) aplicacion.getActividad(idExamen, "Examen");
	            if (examen != null) {
	            	boolean check = aplicacion.revisarActividadesPrevias(examen, estudiante, learningPath);
	            	System.out.println("\nExamen: "+examen.getTitulo());
	            	System.out.println("\nDescripción: "+examen.getDescripcion());
	            	System.out.println(String.format("\nDuración estimada: %d minutos",examen.getDuracionMinutos()));
	            	
	            	if (check == false) {
	            		System.out.println("Advertencia: No se cumplen los prerrequisitos en el Learning Path de la actividad");
	            	}
		            for (PreguntaAbierta pregunta : examen.getPreguntas()) {
		                String respuesta;
		
		                do {
		                    System.out.println("\nPregunta: " + pregunta.getEnunciado());
		                    System.out.print("\nIngrese la respuesta: ");
		                    respuesta = scanner.nextLine().trim();
		                    if (respuesta.isEmpty()) {
		                        System.out.println("\nLa respuesta no puede estar vacía. Por favor, ingrese un valor válido.");
		                    }
		                } while (respuesta.isEmpty());
		
		                aplicacion.responderPreguntaExamen(examen, estudiante, learningPath, pregunta, respuesta);
		                System.out.println("Respuesta registrada exitosamente.\n");
		            }
		            System.out.println("¿Aproximadamente cuánto tiempo, en minutos, le tomó realizar la actividad?: ");
		            int duracion = Integer.parseInt(scanner.nextLine());
		            aplicacion.actualizarDuracionDesarrolloActividad(estudiante, learningPath, examen, duracion);
		            aplicacion.enviarExamen(examen, estudiante, learningPath);
		            System.out.println("Examen enviado exitosamente.");
		            mostrarActividadesSeguimiento(examen);
	            }
	            else {
	            	System.out.println("Examen no encontrado");
	            	return;
	            }
            }
            else{
            	System.out.println("Learning Path no encontrado");
            	return;
            }
            
        } catch (Exception e) {
            System.out.println("Error al registrar la respuesta: " + e.getMessage());
        }
    }

    private  void responderPreguntaEncuesta(Estudiante estudiante) {
    	String tituloLearningPath, loginLearningPath, tituloEncuesta, loginEncuesta;



        do {
            System.out.print("Ingrese el título del Learning Path de la encuesta que quiere realizar: ");
            tituloLearningPath = scanner.nextLine().trim();
            if (tituloLearningPath.isEmpty()) {
                System.out.println("El título del Learning Path no puede estar vacío. Por favor, ingrese un valor válido.");
            }
        } while (tituloLearningPath.isEmpty());
        
        do {
            System.out.print("Ingrese el login del profesor creador del Learning Path: ");
            loginLearningPath = scanner.nextLine().trim();
            if (loginLearningPath.isEmpty()) {
                System.out.println("El profesor del Learning Path no puede estar vacío. Por favor, ingrese un valor válido.");
            }
        } while (loginLearningPath.isEmpty());

        do {
            System.out.print("Ingrese el título de la encuesta: ");
            tituloEncuesta = scanner.nextLine().trim();
            if (tituloEncuesta.isEmpty()) {
                System.out.println("El título de la encuesta no puede estar vacío. Por favor, ingrese un valor válido.");
            }
        } while (tituloEncuesta.isEmpty());
        
        do {
            System.out.print("Ingrese el login del profesor creador de la encuesta: ");
            loginEncuesta = scanner.nextLine().trim();
            if (loginEncuesta.isEmpty()) {
                System.out.println("El profesor de la encuesta no puede estar vacío. Por favor, ingrese un valor válido.");
            }
        } while (loginEncuesta.isEmpty());

        try {
        	
        	String idLearningPath = aplicacion.generarLlaveLearningsActividades(tituloLearningPath, loginLearningPath);
        	String idEncuesta = aplicacion.generarLlaveLearningsActividades(tituloEncuesta, loginEncuesta);
        	
            LearningPath learningPath = aplicacion.getLearningPath(idLearningPath);
            
            if (learningPath != null) {
	            Encuesta encuesta = (Encuesta) aplicacion.getActividad(idEncuesta, "Encuesta");
	            if (encuesta != null) {
	            	boolean check = aplicacion.revisarActividadesPrevias(encuesta, estudiante, learningPath);
	            	System.out.println("\nEncuesta: "+encuesta.getTitulo());
	            	System.out.println("\nDescripción: "+encuesta.getDescripcion());
	            	System.out.println(String.format("\nDuración estimada: %d minutos",encuesta.getDuracionMinutos()));
	            	
	            	if (check == false) {
	            		System.out.println("Advertencia: No se cumplen los prerrequisitos en el Learning Path de la actividad");
	            	}
		            for (PreguntaAbierta pregunta : encuesta.getPreguntas()) {
		                String respuesta;
		
		                do {
		                    System.out.println("\nPregunta: " + pregunta.getEnunciado());
		                    System.out.print("\nIngrese la respuesta: ");
		                    respuesta = scanner.nextLine().trim();
		                    if (respuesta.isEmpty()) {
		                        System.out.println("\nLa respuesta no puede estar vacía. Por favor, ingrese un valor válido.");
		                    }
		                } while (respuesta.isEmpty());
		
		                aplicacion.responderPreguntaEncuesta(encuesta, estudiante, learningPath, pregunta, respuesta);
		                System.out.println("Respuesta registrada exitosamente.\n");
		            }
		            System.out.println("¿Aproximadamente cuánto tiempo, en minutos, le tomó realizar la actividad?: ");
		            int duracion = Integer.parseInt(scanner.nextLine());
		            aplicacion.actualizarDuracionDesarrolloActividad(estudiante, learningPath, encuesta, duracion);
		            aplicacion.completarEncuestaRecurso(encuesta, estudiante, learningPath);
		            System.out.println("Encuesta enviada exitosamente.");
		            
		            mostrarActividadesSeguimiento(encuesta);
	            }
	            else {
	            	System.out.println("Encuesta no encontrada");
	            	return;
	            }
            }
            else{
            	System.out.println("Learning Path no encontrado");
            	return;
            }
            
        } catch (Exception e) {
            System.out.println("Error al registrar la respuesta: " + e.getMessage());
        }
    }

    private  void responderPreguntaQuiz(Estudiante estudiante) {
        String loginLearningPath, tituloLearningPath, tituloQuiz, loginQuiz;


        do {
            System.out.print("Ingrese el título del Learning Path del quiz que quiere responder: ");
            tituloLearningPath = scanner.nextLine().trim();
            if (tituloLearningPath.isEmpty()) {
                System.out.println("El título del Learning Path no puede estar vacío. Por favor, ingrese un valor válido.");
            }
        } while (tituloLearningPath.isEmpty());
        
        do {
            System.out.print("Ingrese el login del profesor creador del Learning Path: ");
            loginLearningPath = scanner.nextLine().trim();
            if (loginLearningPath.isEmpty()) {
                System.out.println("El profesor del Learning Path no puede estar vacío. Por favor, ingrese un valor válido.");
            }
        } while (loginLearningPath.isEmpty());


        do {
            System.out.print("Ingrese el título del quiz que quiere responder: ");
            tituloQuiz = scanner.nextLine().trim();
            if (tituloQuiz.isEmpty()) {
                System.out.println("El título del quiz no puede estar vacío. Por favor, ingrese un valor válido.");
            }
        } while (tituloQuiz.isEmpty());
        
        do {
            System.out.print("Ingrese el login del profesor creador del quiz que quiere responder: ");
            loginQuiz = scanner.nextLine().trim();
            if (loginQuiz.isEmpty()) {
                System.out.println("El profesor del quiz no puede estar vacío. Por favor, ingrese un valor válido.");
            }
        } while (loginQuiz.isEmpty());


        try {
        	
        	String idLearningPath = aplicacion.generarLlaveLearningsActividades(tituloLearningPath, loginLearningPath);
            LearningPath learningPath = aplicacion.getLearningPath(idLearningPath);
            
            if (learningPath == null) {
            	System.out.println("Learning Path no encontrado");
            	return;
            }
            
            String idQuiz = aplicacion.generarLlaveLearningsActividades(tituloQuiz, loginQuiz);
            Quiz quiz = (Quiz) aplicacion.getActividad(idQuiz, "Quiz");

            if (quiz == null) {
            	System.out.println("Quiz no encontrado");
            	return;
            }
            boolean check = aplicacion.revisarActividadesPrevias(quiz, estudiante, learningPath);
            System.out.println("\nQuiz: "+quiz.getTitulo());
        	System.out.println("\nDescripción: "+quiz.getDescripcion());
        	System.out.println(String.format("\nDuración estimada: %d minutos",quiz.getDuracionMinutos()));
        	
        	if (check == false) {
        		System.out.println("Advertencia: No se cumplen los prerrequisitos en el Learning Path de la actividad");
        	}

            for (PreguntaCerrada pregunta : quiz.getPreguntas()) {
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
                	
                	System.out.println("Pregunta: " + pregunta.getEnunciado());
                    System.out.println("Indique si el enunciado es verdadero o falso: ");
                    System.out.println("1. Verdadero");
                    System.out.println("2. Falso");
                    
                    int opcion = 0;
                    
                    do {
                        System.out.print("Ingrese el número de opción (1-2): ");
                        if (scanner.hasNextInt()) {
                            opcion = scanner.nextInt();
                            scanner.nextLine();  
                            if (opcion < 1 || opcion > 2) {
                                System.out.println("Opción inválida. Debe ser un número entre 1 y 4.");
                            }
                        } else {
                            System.out.println("Por favor, ingrese un número válido.");
                            scanner.nextLine();  
                        }
                    } while (opcion < 1 || opcion > 4);
                    
                    int respuesta;
                    
                    if(opcion == 1) {
                    	respuesta = 1;
                    }
                    
                    else {
                    	respuesta = 0;
                    }
                    
                    aplicacion.responderPreguntaQuiz(quiz, estudiante, learningPath, pregunta, respuesta);
                    
                }
            }

            System.out.println("Respuestas registradas exitosamente.");
            
            learningPath.getEstudiantesInscritos().get(estudiante.getLogin()).actualizarProgreso();
            learningPath.getEstudiantesInscritos().get(estudiante.getLogin()).actualizarTasaExito();
            
            System.out.println("¿Aproximadamente cuánto tiempo, en minutos, le tomó realizar la actividad?: ");
            int duracion = Integer.parseInt(scanner.nextLine());
            aplicacion.actualizarDuracionDesarrolloActividad(estudiante, learningPath, quiz, duracion);
            
            mostrarActividadesSeguimiento(quiz);
        } catch (Exception e) {
            System.out.println("Error al registrar las respuestas: " + e.getMessage());
        }
    }
  
    public  void completarEncuestaRecurso(Estudiante estudiante) {
        String tituloRecurso, loginRecurso , tituloLearningPath, loginLearningPath;
        
        do {
            System.out.print("Ingrese el título del LearningPath al que pertenece el recurso que quiere revisar: ");
            tituloLearningPath = scanner.nextLine().trim();
            if (tituloLearningPath.isEmpty()) {
                System.out.println("El título del LearningPath no puede estar vacío. Por favor, ingrese un valor válido.");
            }
        } while (tituloLearningPath.isEmpty());
        
        do {
            System.out.print("Ingrese el login del profesor creador del LearningPath al que pertenece la actividad: ");
            loginLearningPath = scanner.nextLine().trim();
            if (loginLearningPath.isEmpty()) {
                System.out.println("El profesor del LearningPath no puede estar vacío. Por favor, ingrese un valor válido.");
            }
        } while (loginLearningPath.isEmpty());
        
        
        String idLearningPath = aplicacion.generarLlaveLearningsActividades(tituloLearningPath, loginLearningPath);
        LearningPath learningPath = aplicacion.getLearningPath(idLearningPath);
        if (learningPath == null) {
            System.out.println("LearningPath no encontrado.");
            return;
        }
        
        do {
            System.out.print("Ingrese el título del recurso que quiere revisar: ");
            tituloRecurso = scanner.nextLine().trim();
            if (tituloRecurso.isEmpty()) {
                System.out.println("El título del recurso no puede estar vacío. Por favor, ingrese un valor válido.");
            }
        } while (tituloRecurso.isEmpty());

        do {
            System.out.print("Ingrese el login del profesor creador del recurso que quiere revisar: ");
            loginRecurso = scanner.nextLine().trim();
            if (loginRecurso.isEmpty()) {
                System.out.println("El profesor del recurso no puede estar vacío. Por favor, ingrese un valor válido.");
            }
        } while (loginRecurso.isEmpty());
        
        String idActividad = aplicacion.generarLlaveLearningsActividades(tituloRecurso, loginRecurso);
        RevisarRecurso recurso = (RevisarRecurso) aplicacion.getActividad(idActividad, "Recurso");
        if (recurso == null) {
            System.out.println("Actividad no encontrada.");
            return;
        }
        try {
        boolean check = aplicacion.revisarActividadesPrevias(recurso, estudiante, learningPath);
        System.out.println("\nRecurso: "+recurso.getTitulo());
        System.out.println("\nDescripción: "+recurso.getDescripcion());
    	System.out.println(String.format("\nDuración estimada: %d minutos",recurso.getDuracionMinutos()));
    	System.out.println("\nTipo de Recurso: "+recurso.getTipoRecurso());
    	System.out.println("\nEnlace de Recurso: "+recurso.getEnlaceRecurso());
    	if (check == false) {
    		System.out.println("Advertencia: No se cumplen los prerrequisitos en el Learning Path de la actividad");
    	}
    	
        
        	System.out.println("¿Aproximadamente cuánto tiempo, en minutos, le tomó realizar la actividad?: ");
            int duracion = Integer.parseInt(scanner.nextLine());
            aplicacion.actualizarDuracionDesarrolloActividad(estudiante, learningPath, recurso, duracion);
			aplicacion.completarEncuestaRecurso(recurso, estudiante, learningPath);
			System.out.println("Actividad completada exitosamente.");
			
			mostrarActividadesSeguimiento(recurso);
		} catch (EstudianteNoInscritoException | ActividadYaCompletadaException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
        
    }

     private  void verLearningPaths(Estudiante estudiante) {
        System.out.println("== Learning Paths Inscritos ==");
        estudiante.getLearningPathsInscritos().forEach((id, seguimiento) -> {
            System.out.println("Learning Path: " + id);
        });
    }
    
     private  void calificarResenarActividad() {
    	    String tituloActividad, loginActividad, tipo;

    	    do {
    	        System.out.println("Indique el título de la actividad que desea reseñar o calificar: ");
    	        tituloActividad = scanner.nextLine().trim();
    	        if (tituloActividad.isEmpty()) {
    	            System.out.println("El título de la actividad no puede estar vacío. Por favor, ingrese un valor válido.");
    	        }
    	    } while (tituloActividad.isEmpty());
    	    
    	    do {
    	        System.out.println("Indique el login del profesor creador de la actividad que desea reseñar o calificar: ");
    	        loginActividad = scanner.nextLine().trim();
    	        if (loginActividad.isEmpty()) {
    	            System.out.println("El profesor de la actividad no puede estar vacío. Por favor, ingrese un valor válido.");
    	        }
    	    } while (loginActividad.isEmpty());

    	    do {
    	        System.out.println("Indique el tipo de la actividad: ");
    	        tipo = scanner.nextLine().trim();
    	        if (tipo.isEmpty()) {
    	            System.out.println("El tipo de la actividad no puede estar vacío. Por favor, ingrese un valor válido.");
    	        } else if (!tipo.equals("Encuesta") && !tipo.equals("recurso") && !tipo.equals("Examen") && !tipo.equals("Quiz") && !tipo.equals("Tarea")) {
    	            System.out.println("El tipo de actividad debe ser 'Encuesta', 'Recurso', 'Examen', 'Tarea' o 'Quiz'.");
    	        }
    	    } while (tipo.isEmpty() || (!tipo.equals("Encuesta") && !tipo.equals("Recurso") && !tipo.equals("Examen") && !tipo.equals("Quiz")));
    	    
    	    String idActividad = aplicacion.generarLlaveLearningsActividades(tituloActividad, loginActividad);
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

     
     private  void calificarLearningPath() {
    	    String tituloLP, loginLP;
    	    double calificacion;

    	    do {
    	        System.out.println("Indique el título del Learning Path que desea calificar: ");
    	        tituloLP = scanner.nextLine().trim();
    	        if (tituloLP.isEmpty()) {
    	            System.out.println("El título del Learning Path no puede estar vacío. Por favor, ingrese un valor válido.");
    	        }
    	    } while (tituloLP.isEmpty());
    	    
    	    do {
    	        System.out.println("Indique el login del profesor creador del Learning Path que desea calificar: ");
    	        loginLP = scanner.nextLine().trim();
    	        if (loginLP.isEmpty()) {
    	            System.out.println("El profesor del Learning Path no puede estar vacío. Por favor, ingrese un valor válido.");
    	        }
    	    } while (loginLP.isEmpty());
    	    
    	    String idLP = aplicacion.generarLlaveLearningsActividades(tituloLP, loginLP);
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
     
     private void verProgresoLearningPath(Estudiante estudiante) {
    	 
    	 String msjTitulo = "Ingrese el título del Learning Path para el cual quiere ver su progreso";
    	 String msjProfesor = "Ingrese el login del profesor creador del Learning Path";
    	 LearningPath learningPath = getLearningPath(msjTitulo, msjProfesor);
    	 
    	 SeguimientoLearningPath seguimientoEstudiante = estudiante.getLearningPathsInscritos().get(learningPath.getIdLearnginPath());
    	 if (seguimientoEstudiante != null) {
    		 
    		 System.out.println("\n---Progreso de Learning Path---");
    		 System.out.println("Título: "+learningPath.getTitulo());
    		 System.out.println("Descripción: "+learningPath.getDescripcion());
    		 System.out.println(String.format("Progreso: %.2f", seguimientoEstudiante.getProgreso()*100));
    		 System.out.println(String.format("Porcentaje de Éxito: %.2f", seguimientoEstudiante.getTasaExito()));
    		 
    		 HashMap<String, SeguimientoActividad> mapaSeguimientos = seguimientoEstudiante.getMapaSeguimientoActividades();
    		 System.out.println("\n--Actividades--");
    		 for(SeguimientoActividad actividad: mapaSeguimientos.values()){
    			 
    			 String tipo = actividad.getActividadSeguimiento().getTipoActividad();
    			 
    			 System.out.println("\nTítulo: "+ actividad.getActividadSeguimiento().getTitulo());
    			 System.out.println("Tipo: "+ tipo);
    			 System.out.println("Estado: "+ actividad.getEstado());
    			 SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    			 String fecha = formatter.format(actividad.getActividadSeguimiento().getFechaLimite());
    			 System.out.println("Fecha: "+ fecha);
    			 System.out.println(String.format("Obligatoria: %B", learningPath.getMapaActividadesObligatorias().get(actividad.getActividadSeguimiento().getIdActividad())));
    			 
    			 //hola
    			 
    			 
    			 if (tipo.equals("Quiz")|| tipo.equals("Examen") || tipo.equals("Tarea"))
    			 
    			 {
    				 if (tipo.equals("Quiz")) {
    					 SeguimientoQuiz segQuiz = (SeguimientoQuiz) actividad;
        				 System.out.println(String.format("Calificación: %.2f", segQuiz.getNota()));
    				 }
    				 
    				 else if(tipo.equals("Tarea")) {
    					 SeguimientoTarea segTar = (SeguimientoTarea) actividad;
    					 System.out.println(String.format("Metodo de envío: %s", segTar.getMetodoEnvio()));
    				 }
    				 
    				 else {
    				 SeguimientoExamen segExa = (SeguimientoExamen) actividad;
    				 System.out.println(String.format("Calificación: %.2f", segExa.getNota()));
    				 }
    			 }
    			 
    			 
    		 }
    		 
    	 }
    	 
    	 else {
    		 System.out.println("Learning Path no inscrito");
    	 }
    	 
     }
     
     private  LearningPath getLearningPath(String msjTitulo, String msjProfesor) {
     	String titulo, login;
     	do {
 	    	System.out.println(msjTitulo);
 			titulo = scanner.nextLine();
 			if(titulo.isEmpty()){
 				System.out.println("El título del Learning Path no puede ser vacío, inténtelo de nuevo.");
 			}
     	}
     	while(titulo.isEmpty());

     	
 		do {
    		System.out.println(msjProfesor);
    		login = scanner.nextLine();
    		if(login.isEmpty()){
				System.out.println("El profesor del Learning Path no puede ser vacío, inténtelo de nuevo.");
			}
 		}
 		while(login.isEmpty());
     	
     String idLearningPath = aplicacion.generarLlaveLearningsActividades(titulo, login);
		
     LearningPath learningPath = aplicacion.getLearningPath(idLearningPath);
		
		return learningPath;
     }
     
     private void mostrarActividadesSeguimiento(Actividad actividad) {
    	 System.out.println("\nLas siguientes son las actividades de seguimiento recomendadas por el profesor: ");
    	 int i = 1;
    	 for(Actividad actSeguimiento: actividad.getActividadesSeguimiento()) {
    		 System.out.println(String.format("\n%d. %s", i, actSeguimiento.getTitulo()));
    		 System.out.println(String.format("---Profesor: %s (%s)", i, actSeguimiento.getNombreProfesorCreador(), actSeguimiento.getLoginProfesorCreador()));
    		 System.out.println(String.format("---Tipo: %s", i, actSeguimiento.getTipoActividad()));
    		 i++;
    	 }
     }
}
