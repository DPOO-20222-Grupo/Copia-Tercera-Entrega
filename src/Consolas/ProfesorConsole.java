package Consolas;

import java.util.Scanner;  

import actividades.Actividad;
import actividades.Encuesta;
import actividades.Examen;
import actividades.Quiz;
import actividades.RevisarRecurso;
import actividades.Tarea;
import exceptions.ModificarObjetivosException;
import exceptions.TipoInvalidoValorException;
import interfaz.Aplicacion;
import learningPath.LearningPath;
import preguntas.PreguntaAbierta;
import user.Estudiante;
import user.Profesor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class ProfesorConsole {
    
	private static Aplicacion aplicacion = new Aplicacion("usuarios.json", "lp.json", "preguntas.json", "actividades.json");  
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        
        System.out.println("== Sistema de Profesores ==");
        System.out.print("Ingrese su login: ");
        String login = scanner.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String password = scanner.nextLine();

        Profesor profesor = aplicacion.getMapaProfesores().get(login); 
        if (profesor != null && profesor.login(login, password)) {
            System.out.println("Autenticación exitosa. Bienvenido, Profesor " + profesor.getNombre());
            mostrarMenuProfesor(profesor);
        } else {
            System.out.println("Credenciales incorrectas.");
        }
        
    }

    private static void mostrarMenuProfesor(Profesor profesor) {
        int opcion;
        do {
            System.out.println("\n== Menú Profesor ==");
            System.out.println("1. Crear una actividad para revisar un recurso");
            System.out.println("2. Crear una tarea");
            System.out.println("3. Crear un quiz");
            System.out.println("4. Crear un examen");
            System.out.println("5. Crear una encuesta");
            System.out.println("6. Registrar un Learning Path");
            System.out.println("7. Clonar Actividad");
            System.out.println("8. Clonar Learning Path");
            System.out.println("9. Modificar Learning Path");
            System.out.println("10. Modificar Actividad");
            System.out.println("11. Calificar Actividad");
            System.out.println("12. Ver mis actividades");
            System.out.println("13. Cerrar sesión");
            System.out.print("Seleccione una opción: ");
            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    crearRevisarRecurso(profesor);
                    break;
                case 2:
                	crearTarea(profesor);
                	break;
                case 3:
                	crearQuiz(profesor);
                	break;
                case 4:
                	crearExamen(profesor);
                	break;
                case 5:
                	crearEncuesta(profesor);
                	break;
                case 6:
                    registrarLearningPath(profesor);
                    break;
                case 7:
                	clonarActividad();
                	break;
                case 8:
                	clonarLearningPath(profesor);
                	break;
                case 9:
                	modificarLearningPath(profesor);
                	break;
                case 10:
                	modificarActividad(profesor);
                	break;
                case 11:
                	calificarActividad(profesor);
                	break;
                case 12:
                    verActividades(profesor);
                    break;
                case 13:
                    profesor.logout();
                    System.out.println("Sesión cerrada.");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (profesor.isLoggedIn());
    }

	private static void crearRevisarRecurso(Profesor profesor) {
        
    	System.out.print("Ingrese el titulo del recurso: ");
        String titulo = scanner.nextLine();
        
        System.out.print("Ingrese la descripcion del recurso: ");
        String descripcion = scanner.nextLine();
        
        System.out.print("Ingrese los objetivos de revisar el recurso (separados por comas): ");
        String objetivosInput = scanner.nextLine();
        List<String> objetivos = new ArrayList<>();
        for (String objetivo : objetivosInput.split(",")) {
            objetivos.add(objetivo.trim());
        }
        
        System.out.print("Ingrese el nivel de dificultad del recurso a revisar: ");
        String dificultad = scanner.nextLine();
        
        System.out.print("Ingrese la duracion en minutos que le tomara al estudiante revisar el recurso: ");
        int duracion = Integer.parseInt(scanner.nextLine());
        
        System.out.print("Ingrese la fecha limite para revisar el recurso (formato: dd/MM/yyyy): ");
        String fechaInput = scanner.nextLine();
        Date fechaLimite = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            fechaLimite = sdf.parse(fechaInput); 
        } catch (Exception e) {
            System.out.println("Formato de fecha incorrecto. Utilizando fecha actual.");
            fechaLimite = new Date(); 
        }
        
        System.out.print("Ingrese el tipo de recurso: ");
        String tipoRecurso = scanner.nextLine();
        
        System.out.print("Ingrese el enlace del recurso: ");
        String enlace = scanner.nextLine();

        aplicacion.crearRevisarRecurso(titulo, descripcion, objetivos, dificultad, duracion, fechaLimite, tipoRecurso, profesor, enlace);
        System.out.println("Actividad de revisar recurso creada exitosamente.");
    }
	
	private static void crearTarea(Profesor profesor) {
	        
	    	System.out.print("Ingrese el titulo de la tarea: ");
	        String titulo = scanner.nextLine();
	        
	        System.out.print("Ingrese la descripcion de la tarea: ");
	        String descripcion = scanner.nextLine();
	        
	        System.out.print("Ingrese los objetivos de la tarea (separados por comas): ");
	        String objetivosInput = scanner.nextLine();
	        List<String> objetivos = new ArrayList<>();
	        for (String objetivo : objetivosInput.split(",")) {
	            objetivos.add(objetivo.trim());
	        }
	        
	        System.out.print("Ingrese el nivel de dificultad de la tarea: ");
	        String dificultad = scanner.nextLine();
	        
	        System.out.print("Ingrese la duracion en minutos de la tarea: ");
	        int duracion = Integer.parseInt(scanner.nextLine());
	        
	        System.out.print("Ingrese la fecha limite de la tarea (formato: dd/MM/yyyy): ");
	        String fechaInput = scanner.nextLine();
	        Date fechaLimite = null;
	        try {
	            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	            fechaLimite = sdf.parse(fechaInput); 
	        } catch (Exception e) {
	            System.out.println("Formato de fecha incorrecto. Utilizando fecha actual.");
	            fechaLimite = new Date(); 
	        }
	
	        aplicacion.crearTarea(titulo, descripcion, objetivos, dificultad, duracion, fechaLimite, profesor);
	        System.out.println("Tarea registrada exitosamente.");
	    }
	
	private static void crearQuiz(Profesor profesor) {
	    
		System.out.print("Ingrese el titulo del quiz: ");
	    String titulo = scanner.nextLine();
	    
	    System.out.print("Ingrese la descripcion del quiz: ");
	    String descripcion = scanner.nextLine();
	    
	    System.out.print("Ingrese los objetivos del quiz (separados por comas): ");
	    String objetivosInput = scanner.nextLine();
	    List<String> objetivos = new ArrayList<>();
	    for (String objetivo : objetivosInput.split(",")) {
	        objetivos.add(objetivo.trim());
	    }
	    
	    System.out.print("Ingrese el nivel de dificultad del quiz: ");
	    String dificultad = scanner.nextLine();
	    
	    System.out.print("Ingrese la duracion en minutos del quiz: ");
	    int duracion = Integer.parseInt(scanner.nextLine());
	    
	    System.out.print("Ingrese la fecha limite del quiz (formato: dd/MM/yyyy): ");
	    String fechaInput = scanner.nextLine();
	    Date fechaLimite = null;
	    try {
	        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	        fechaLimite = sdf.parse(fechaInput); 
	    } catch (Exception e) {
	        System.out.println("Formato de fecha incorrecto. Utilizando fecha actual.");
	        fechaLimite = new Date(); 
	    }
	    
	    System.out.print("Ingrese la calificacion minima del quiz: ");
	    float calificacionMinima = Float.parseFloat(scanner.nextLine());
	    
	    
	    
	    
	    aplicacion.crearQuiz(titulo, descripcion, objetivos, dificultad, duracion, fechaLimite, profesor, calificacionMinima, null);
	    System.out.println("Quiz registrado exitosamente.");
	}
	
	private static void crearExamen(Profesor profesor) {
	    
		System.out.print("Ingrese el titulo del examen: ");
	    String titulo = scanner.nextLine();
	    
	    System.out.print("Ingrese la descripcion del examen: ");
	    String descripcion = scanner.nextLine();
	    
	    System.out.print("Ingrese los objetivos del examen (separados por comas): ");
	    String objetivosInput = scanner.nextLine();
	    List<String> objetivos = new ArrayList<>();
	    for (String objetivo : objetivosInput.split(",")) {
	        objetivos.add(objetivo.trim());
	    }
	    
	    System.out.print("Ingrese el nivel de dificultad del examen: ");
	    String dificultad = scanner.nextLine();
	    
	    System.out.print("Ingrese la duracion en minutos del examen: ");
	    int duracion = Integer.parseInt(scanner.nextLine());
	    
	    System.out.print("Ingrese la fecha limite del examen (formato: dd/MM/yyyy): ");
	    String fechaInput = scanner.nextLine();
	    Date fechaLimite = null;
	    try {
	        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	        fechaLimite = sdf.parse(fechaInput); 
	    } catch (Exception e) {
	        System.out.println("Formato de fecha incorrecto. Utilizando fecha actual.");
	        fechaLimite = new Date(); 
	    }
	    
	    List<PreguntaAbierta> preguntas = new ArrayList<>();
	    System.out.println("Ingrese las preguntas del examen en el formato 'título|enunciado', separadas por ';': ");
	    String preguntasInput = scanner.nextLine();
	    
	    for (String preguntaData : preguntasInput.split(";")) {
	        String[] partes = preguntaData.split("\\|");
	        String tituloPregunta = partes[0].trim();
	        String enunciado = partes[1].trim();
	        
	        PreguntaAbierta pregunta = new PreguntaAbierta(enunciado, tituloPregunta);
	        preguntas.add(pregunta);
	    }
	    
	    aplicacion.crearExamen(titulo, descripcion, objetivos, dificultad, duracion, fechaLimite, profesor, preguntas);
	    System.out.println("Examen registrado exitosamente.");
	}
	
	private static void crearEncuesta(Profesor profesor) {
	    
		System.out.print("Ingrese el titulo de la encuesta: ");
	    String titulo = scanner.nextLine();
	    
	    System.out.print("Ingrese la descripcion de la encuesta: ");
	    String descripcion = scanner.nextLine();
	    
	    System.out.print("Ingrese los objetivos de la encuesta (separados por comas): ");
	    String objetivosInput = scanner.nextLine();
	    List<String> objetivos = new ArrayList<>();
	    for (String objetivo : objetivosInput.split(",")) {
	        objetivos.add(objetivo.trim());
	    }
	    
	    System.out.print("Ingrese el nivel de dificultad de la encuesta: ");
	    String dificultad = scanner.nextLine();
	    
	    System.out.print("Ingrese la duracion en minutos de la encuesta: ");
	    int duracion = Integer.parseInt(scanner.nextLine());
	    
	    System.out.print("Ingrese la fecha limite de la encuesta (formato: dd/MM/yyyy): ");
	    String fechaInput = scanner.nextLine();
	    Date fechaLimite = null;
	    try {
	        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	        fechaLimite = sdf.parse(fechaInput); 
	    } catch (Exception e) {
	        System.out.println("Formato de fecha incorrecto. Utilizando fecha actual.");
	        fechaLimite = new Date(); 
	    }
	    
	    List<PreguntaAbierta> preguntas = new ArrayList<>();
	    System.out.println("Ingrese las preguntas de la encuesta en el formato 'título|enunciado', separadas por ';': ");
	    String preguntasInput = scanner.nextLine();
	    
	    for (String preguntaData : preguntasInput.split(";")) {
	        String[] partes = preguntaData.split("\\|");
	        String tituloPregunta = partes[0].trim();
	        String enunciado = partes[1].trim();
	        
	        PreguntaAbierta pregunta = new PreguntaAbierta(enunciado, tituloPregunta);
	        preguntas.add(pregunta);
	    }
	
	    aplicacion.crearEncuesta(titulo, descripcion, objetivos, dificultad, duracion, fechaLimite, profesor, preguntas);
	    System.out.println("Encuesta registrada exitosamente.");
	}

    private static void registrarLearningPath(Profesor profesor) {
    	
    	System.out.print("Ingrese el titulo del learning path: ");
        String titulo = scanner.nextLine();
        
        System.out.print("Ingrese la descripcion del learning path: ");
        String descripcion = scanner.nextLine();
        
        System.out.print("Ingrese los objetivos del learning path (separados por comas): ");
        String objetivosInput = scanner.nextLine();
        List<String> objetivos = new ArrayList<>();
        for (String objetivo : objetivosInput.split(",")) {
            objetivos.add(objetivo.trim());
        }
        
        System.out.print("Ingrese el nivel de dificultad de la actividad: ");
        String dificultad = scanner.nextLine();
        
        LearningPath lp = new LearningPath(titulo, descripcion, objetivos, dificultad, profesor, null, null); 
        aplicacion.registrarLearningPath(lp);
        System.out.println("Learning Path creado exitosamente.");
    }

	private static void clonarActividad() {
		
		System.out.print("Ingrese el id de la actividad que desea clonar: ");
        String id = scanner.nextLine();
        
        System.out.print("Ingrese el tipo de la actividad que desea clonar: ");
        String tipo = scanner.nextLine();
		
		Actividad actividadOriginal = aplicacion.getActividad(id, tipo);
		aplicacion.clonarActividad(actividadOriginal, actividadOriginal.getProfesorCreador());
		
	}
	
	private static void clonarLearningPath(Profesor profesor) {
		
		System.out.print("Ingrese el ID del Learning Path que desea clonar: ");
	    String idLearningPathOriginal = scanner.nextLine();
	   
	    HashMap<String, LearningPath> mapaLearningPaths = aplicacion.getMapaLearningPaths(); 
	    LearningPath learningPathOriginal = mapaLearningPaths.get(idLearningPathOriginal);
	    
	    if (learningPathOriginal == null) {
	        System.out.println("Learning Path no encontrado. Verifique el ID e intente de nuevo.");
	        return;
	    }
	    
	    aplicacion.clonarLearningPath(learningPathOriginal, learningPathOriginal.getProfesorCreador());
	    System.out.println("Learning Path clonado con éxito para el profesor: " + profesor.getNombre());
	}
	
	private static void modificarLearningPath(Profesor profesor) {
	    System.out.print("Ingrese el ID del Learning Path a modificar: ");
	    String idLP = scanner.nextLine();
	    LearningPath learningPath = profesor.getLearningPathPropios().get(idLP);

	    if (learningPath != null) {
	        System.out.println("Seleccione el atributo a modificar:");
	        System.out.println("1. Titulo");
	        System.out.println("2. Descripcion");
	        System.out.println("3. Dificultad");
	        System.out.println("4. Objetivos");
	        int opcion = Integer.parseInt(scanner.nextLine());
	        
	        String atributoModificar = "";
	        String valor = "";
	        String accion = "";

	        switch (opcion) {
	            case 1:
	                atributoModificar = "Titulo";
	                System.out.print("Ingrese el nuevo titulo: ");
	                valor = scanner.nextLine();
	                break;
	            case 2:
	                atributoModificar = "Descripcion";
	                System.out.print("Ingrese la nueva descripcion: ");
	                valor = scanner.nextLine();
	                break;
	            case 3:
	                atributoModificar = "Dificultad";
	                System.out.print("Ingrese la nueva dificultad: ");
	                valor = scanner.nextLine();
	                break;
	            case 4:
	                atributoModificar = "Objetivos";
	                System.out.print("Seleccione la acción (Agregar/Eliminar): ");
	                accion = scanner.nextLine();
	                System.out.print("Ingrese el objetivo: ");
	                valor = scanner.nextLine();
	                break;
	            default:
	                System.out.println("Opción no válida.");
	                return;
	        }

	        try {
	            aplicacion.modificarAtributosStringLearningPath(learningPath, atributoModificar, valor, accion);
	            System.out.println("Atributo modificado exitosamente.");
	        } catch (TipoInvalidoValorException | ModificarObjetivosException e) {
	            System.out.println("Error al modificar el atributo: " + e.getMessage());
	        }
	    } else {
	        System.out.println("Learning Path no encontrado.");
	    }
	}
	
	private static void modificarActividad(Profesor profesor) {

        System.out.println("Ingrese el ID de la actividad a modificar:");
        String idActividad = scanner.nextLine();
        
        System.out.println("Ingrese el tipo de la actividad a modificar:");
        String tipo = scanner.nextLine();

        System.out.println("¿Qué atributo desea modificar? (Titulo, Descripcion, Dificultad, Objetivos):");
        String atributo = scanner.nextLine();

        System.out.println("Ingrese el nuevo valor para " + atributo + ":");
        String valor = scanner.nextLine();

        String accion = null;
        if (atributo.equals("Objetivos")) {
            System.out.println("¿Desea Agregar o Eliminar el objetivo? (Ingrese 'Agregar' o 'Eliminar'):");
            accion = scanner.nextLine();
        }

        Actividad actividad = aplicacion.getActividad(idActividad, tipo);
        if (actividad != null) {            
            try {
                if (atributo.equals("Titulo")) {
                    switch (tipo) {
                        case "Encuesta":
                            HashMap<String, Encuesta> mapaEncuesta = aplicacion.getMapaEncuestas();
                            mapaEncuesta.remove(idActividad);
                            actividad.setTitulo(valor);
                            mapaEncuesta.put(idActividad, (Encuesta) actividad);
                            System.out.println("El título de la actividad ha sido actualizado.");
                            break;
                        case "Tarea":
                        	HashMap<String, Tarea> mapaTareas = aplicacion.getMapaTareas();
                        	mapaTareas.remove(idActividad);
                            actividad.setTitulo(valor);
                            mapaTareas.put(idActividad, (Tarea) actividad);
                            System.out.println("El título de la actividad ha sido actualizado.");
                            break;
                        case "Quiz":
                        	HashMap<String, Quiz> mapaQuiz = aplicacion.getMapaQuices();
                        	mapaQuiz.remove(idActividad);
                            actividad.setTitulo(valor);
                            mapaQuiz.put(idActividad, (Quiz) actividad);
                            System.out.println("El título de la actividad ha sido actualizado.");
                            break;
                        case "Examen":
                        	HashMap<String, Examen> mapaExamenes = aplicacion.getMapaExamenes();
                        	mapaExamenes.remove(idActividad);
                            actividad.setTitulo(valor);
                            mapaExamenes.put(idActividad, (Examen) actividad);
                            System.out.println("El título de la actividad ha sido actualizado.");
                            break;
                        case "Recurso":
                        	HashMap<String, RevisarRecurso> mapaRecursos = aplicacion.getMapaRevisarRecurso();
                        	mapaRecursos.remove(idActividad);
                            actividad.setTitulo(valor);
                            mapaRecursos.put(idActividad, (RevisarRecurso) actividad);
                            System.out.println("El título de la actividad ha sido actualizado.");
                            break;
                        default:
                            System.out.println("Tipo de actividad no reconocido.");
                            return;
                    }

                } else {
                    switch (atributo) {
                        case "Descripcion":
                            actividad.setDescripcion(valor);
                            System.out.println("La descripción de la actividad ha sido actualizada.");
                            break;
                        case "Dificultad":
                            actividad.setNivelDificultad(valor);
                            System.out.println("La dificultad de la actividad ha sido actualizada.");
                            break;
                        case "Objetivos":
                            if ("Agregar".equalsIgnoreCase(accion)) {
                                actividad.agregarObjetivo(valor);
                                System.out.println("Objetivo agregado a la actividad.");
                            } else if ("Eliminar".equalsIgnoreCase(accion)) {
                                actividad.eliminarObjetivo(valor);
                                System.out.println("Objetivo eliminado de la actividad.");
                            } else {
                                System.out.println("Acción no válida para objetivos.");
                            }
                            break;
                        default:
                            System.out.println("Atributo no reconocido.");
                    }
                }
            } catch (ModificarObjetivosException e) {
                System.out.println("Error al modificar objetivos: " + e.getMessage());
            }
        } else {
            System.out.println("Actividad no encontrada con el ID proporcionado.");
        }
    }
	
	private static void calificarActividad(Profesor profesor) {

        System.out.println("Ingrese el tipo de actividad a calificar (Examen/Tarea): ");
        String tipoActividad = scanner.nextLine();

        System.out.println("Ingrese el id del Learning Path: ");
        String id = scanner.nextLine();
        LearningPath learningPath = aplicacion.getLearningPath(id);

        System.out.println("Ingrese el login del estudiante: ");
        String loginEstudiante = scanner.nextLine();
        Estudiante estudiante = aplicacion.getEstudiante(loginEstudiante);

        if (tipoActividad.equalsIgnoreCase("Examen")) {
            System.out.println("Ingrese el nombre del examen: ");
            String nombreExamen = scanner.nextLine();
            Examen examen = aplicacion.getMapaExamenes().get(nombreExamen);

            System.out.println("Ingrese la nota del examen: ");
            float nota = scanner.nextFloat();

            aplicacion.calificarExamen(examen, estudiante, learningPath, nota);
            System.out.println("Examen calificado con éxito.");
            
        } else if (tipoActividad.equalsIgnoreCase("Tarea")) {
            System.out.println("Ingrese el nombre de la tarea: ");
            String nombreTarea = scanner.nextLine();
            Tarea tarea = aplicacion.getMapaTareas().get(nombreTarea);

            System.out.println("¿La tarea fue exitosa? (true/false): ");
            boolean esExitoso = scanner.nextBoolean();

            aplicacion.calificarTarea(tarea, estudiante, learningPath, esExitoso);
            System.out.println("Tarea calificada con éxito.");
        } else {
            System.out.println("Tipo de actividad no válido.");
        }
        
    }
	
	

    private static void verActividades(Profesor profesor) {
        System.out.println("== Mis Actividades ==");
        profesor.getMapaRecursosPropios().forEach((id, recurso) -> System.out.println("Recurso: " + id));
        profesor.getMapaTareasPropias().forEach((id, tarea) -> System.out.println("Tarea: " + id));
        profesor.getMapaExamenesPropios().forEach((id, examen) -> System.out.println("Examen: " + id));
        profesor.getMapaQuicesPropios().forEach((id, quiz) -> System.out.println("Quiz: " + id));
        profesor.getMapaEncuestasPropias().forEach((id, encuesta) -> System.out.println("Encuesta: " + id));
        
    }

}
