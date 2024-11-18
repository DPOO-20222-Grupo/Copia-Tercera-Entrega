package Consolas;

import java.util.Scanner;   

import actividades.Actividad;
import actividades.Examen;
import actividades.Quiz;
import actividades.RevisarRecurso;
import actividades.Tarea;
import exceptions.ActividadYaExistenteException;
import exceptions.LearningPathYaExistenteException;
import exceptions.ModificarActividadesLearningPathException;
import exceptions.ModificarActividadesPreviasException;
import exceptions.ModificarActividadesSeguimientoException;
import exceptions.ModificarObjetivosException;
import exceptions.ModificarPreguntasAbiertasException;
import exceptions.ModificarPreguntasQuizException;
import exceptions.TipoInvalidoValorException;
import interfaz.Aplicacion;
import learningPath.LearningPath;
import preguntas.Pregunta;
import preguntas.PreguntaAbierta;
import preguntas.PreguntaCerrada;
import preguntas.PreguntaSeleccionMultiple;
import user.Estudiante;
import user.Profesor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public static void mostrarMenuProfesor(Profesor profesor) {
        int opcion = -1; 
        do {
            System.out.println("\n== Menú Profesor ==");
            System.out.println("1. Crear una actividad para revisar un recurso");
            System.out.println("2. Crear una tarea");
            System.out.println("3. Crear un quiz");
            System.out.println("4. Crear un examen");
            System.out.println("5. Crear una encuesta");
            System.out.println("6. Crear un Learning Path");
            System.out.println("7. Crear una pregunta");
            System.out.println("8. Clonar Actividad");
            System.out.println("9. Clonar Learning Path");
            System.out.println("10. Modificar Learning Path");
            System.out.println("11. Modificar Actividad");
            System.out.println("12. Calificar Actividad");
            System.out.println("13. Modificar una pregunta");
            System.out.println("14. Revisar si una actividad ya existe");
            System.out.println("15. Revisar si un Learning Path ya existe");
            System.out.println("16. Ver mis actividades");
            System.out.println("17. Reseñar o calificar una actividad");
            System.out.println("18. Calificar un Learning Path");
            System.out.println("19. Cerrar sesión");
            System.out.print("Seleccione una opción: ");
            
            try {
                opcion = Integer.parseInt(scanner.nextLine()); 

                if (opcion < 1 || opcion > 19) {
                    System.out.println("Opción no válida. Por favor, seleccione una opción entre 1 y 19.");
                    continue;
                }

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
                        crearLearningPath(profesor);
                        break;
                    case 7:
                        crearPregunta(profesor);
                        break;
                    case 8:
                        clonarActividad(profesor);
                        break;
                    case 9:
                        clonarLearningPath(profesor);
                        break;
                    case 10:
                        modificarLearningPath(profesor);
                        break;
                    case 11:
                        modificarActividad(profesor);
                        break;
                    case 12:
                        calificarActividad(profesor);
                        break;
                    case 13:
                        modificarPregunta(profesor);
                        break;
                    case 14:
                        revisarActividadRepetida();
                        break;
                    case 15:
                        revisarLearningPathRepetido();
                        break;
                    case 16:
                        verActividades(profesor);
                        break;
                    case 17:
                        calificarResenarActividad();
                        break;
                    case 18:
                        calificarLearningPath();
                        break;
                    case 19:
                        profesor.logout();
                        System.out.println("Sesión cerrada.");
                        break;
                }

                aplicacion.descargarDatos(); 

            } catch (NumberFormatException e) {
                
                System.out.println("Entrada no válida. Por favor, ingrese un número.");
            }

        } while (opcion != 19); 
    }


    private static void crearRevisarRecurso(Profesor profesor) {

        System.out.print("Ingrese el título del recurso: ");
        String titulo;
        do {
            titulo = scanner.nextLine().trim();
            if (titulo.isEmpty()) {
                System.out.println("Error: El título no puede estar vacío.");
                System.out.print("Ingrese el título del recurso: ");
            }
        } while (titulo.isEmpty());

        System.out.print("Ingrese la descripción del recurso: ");
        String descripcion;
        do {
            descripcion = scanner.nextLine().trim();
            if (descripcion.isEmpty()) {
                System.out.println("Error: La descripción no puede estar vacía.");
                System.out.print("Ingrese la descripción del recurso: ");
            }
        } while (descripcion.isEmpty());

        try {
            aplicacion.revisarActividadRepetida(titulo, profesor.getLogin(), "Recurso");
        } catch (ActividadYaExistenteException e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }

        System.out.print("Ingrese los objetivos de revisar el recurso (separados por comas): ");
        List<String> objetivos = new ArrayList<>();
        while (objetivos.isEmpty()) {
            String objetivosInput = scanner.nextLine().trim();
            if (objetivosInput.isEmpty()) {
                System.out.println("Error: Debe ingresar al menos un objetivo.");
                System.out.print("Ingrese los objetivos de revisar el recurso (separados por comas): ");
                continue;
            }
            for (String objetivo : objetivosInput.split(",")) {
                String objetivoLimpio = objetivo.trim();
                if (!objetivoLimpio.isEmpty()) {
                    objetivos.add(objetivoLimpio);
                }
            }
            if (objetivos.isEmpty()) {
                System.out.println("Error: Ningún objetivo válido fue ingresado.");
            }
        }

        System.out.print("Ingrese el nivel de dificultad del recurso a revisar: ");
        String dificultad;
        do {
            dificultad = scanner.nextLine().trim();
            if (dificultad.isEmpty()) {
                System.out.println("Error: La dificultad no puede estar vacía.");
                System.out.print("Ingrese el nivel de dificultad del recurso a revisar: ");
            }
        } while (dificultad.isEmpty());

        System.out.print("Ingrese la duración en minutos que le tomará al estudiante revisar el recurso: ");
        int duracion = -1;
        while (duracion <= 0) {
            try {
                String duracionInput = scanner.nextLine().trim();
                if (duracionInput.isEmpty()) {
                    throw new NumberFormatException("Duración vacía.");
                }
                duracion = Integer.parseInt(duracionInput);
                if (duracion <= 0) {
                    System.out.println("Error: La duración debe ser un número positivo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número válido.");
                System.out.print("Ingrese la duración en minutos que le tomará al estudiante revisar el recurso: ");
            }
        }

        System.out.print("Ingrese la fecha límite para revisar el recurso (formato: dd/MM/yyyy): ");
        Date fechaLimite = null;
        while (fechaLimite == null) {
            String fechaInput = scanner.nextLine().trim();
            if (fechaInput.isEmpty()) {
                System.out.println("Error: La fecha no puede estar vacía.");
                System.out.print("Ingrese la fecha límite para revisar el recurso (formato: dd/MM/yyyy): ");
                continue;
            }
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                sdf.setLenient(false);
                fechaLimite = sdf.parse(fechaInput);
            } catch (Exception e) {
                System.out.println("Error: Formato de fecha incorrecto. Intente nuevamente.");
                System.out.print("Ingrese la fecha límite para revisar el recurso (formato: dd/MM/yyyy): ");
            }
        }

        System.out.print("Ingrese el tipo de recurso: ");
        String tipoRecurso;
        do {
            tipoRecurso = scanner.nextLine().trim();
            if (tipoRecurso.isEmpty()) {
                System.out.println("Error: El tipo de recurso no puede estar vacío.");
                System.out.print("Ingrese el tipo de recurso: ");
            }
        } while (tipoRecurso.isEmpty());

        System.out.print("Ingrese el enlace del recurso: ");
        String enlace;
        do {
            enlace = scanner.nextLine().trim();
            if (enlace.isEmpty()) {
                System.out.println("Error: El enlace no puede estar vacío.");
                System.out.print("Ingrese el enlace del recurso: ");
            }
        } while (enlace.isEmpty());

        aplicacion.crearRevisarRecurso(titulo, descripcion, objetivos, dificultad, duracion, fechaLimite, tipoRecurso, profesor, enlace);
        System.out.println("Actividad de revisar recurso creada exitosamente.");
    }

	private static void crearTarea(Profesor profesor) {        
	    	System.out.print("Ingrese el titulo de la tarea: ");
	        String titulo = scanner.nextLine();
	        
	        System.out.print("Ingrese la descripcion de la tarea: ");
	        String descripcion = scanner.nextLine();
	        
	        try {
	        	
	        aplicacion.revisarActividadRepetida(titulo, profesor.getLogin(), "Tarea");
	        
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
	        
	        catch (ActividadYaExistenteException e){
	        	System.out.println(e.getMessage());
	        }
	    }
	
	private static void crearQuiz(Profesor profesor) {

	    
		System.out.print("Ingrese el titulo de la tarea: ");
	    String titulo = scanner.nextLine();
	    if (titulo.isEmpty()) {
	        System.out.println("El título de la tarea no puede estar vacío.");
	        return; 
	    }
	    
	    System.out.print("Ingrese la descripcion de la tarea: ");
	    String descripcion = scanner.nextLine();

	    if (descripcion.isEmpty()) {
	        System.out.println("La descripción de la tarea no puede estar vacía.");
	        return; 
	    }

	    System.out.print("Ingrese los objetivos de la tarea (separados por comas): ");
	    String objetivosInput = scanner.nextLine();
	    List<String> objetivos = new ArrayList<>();
	    if (objetivosInput.isEmpty()) {
	        System.out.println("Los objetivos no pueden estar vacíos.");
	        return; 
	    }
	    for (String objetivo : objetivosInput.split(",")) {
	        objetivos.add(objetivo.trim());
	    }

	    System.out.print("Ingrese el nivel de dificultad de la tarea: ");
	    String dificultad = scanner.nextLine();
	    if (dificultad.isEmpty()) {
	        System.out.println("El nivel de dificultad no puede estar vacío.");
	        return; 
	    }

	    System.out.print("Ingrese la duracion en minutos de la tarea: ");
	    int duracion = 0;
	    try {
	        duracion = Integer.parseInt(scanner.nextLine());
	        if (duracion <= 0) {
	            System.out.println("La duración debe ser un número positivo.");
	            return; 
	        }
	    } catch (NumberFormatException e) {
	        System.out.println("La duración debe ser un número entero.");
	        return; 
	    }

	    System.out.print("Ingrese la fecha limite de la tarea (formato: dd/MM/yyyy): ");
	    String fechaInput = scanner.nextLine();
	    Date fechaLimite = null;
	    try {
	        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	        fechaLimite = sdf.parse(fechaInput);
	        if (fechaLimite.before(new Date())) {
	            System.out.println("La fecha límite no puede ser una fecha pasada.");
	            return; 
	        }
	    } catch (Exception e) {
	        System.out.println("Formato de fecha incorrecto. Utilizando fecha actual.");
	        fechaLimite = new Date();
	    }

	    aplicacion.crearTarea(titulo, descripcion, objetivos, dificultad, duracion, fechaLimite, profesor);
	    System.out.println("Tarea registrada exitosamente.");
	}
	
	private static void crearExamen(Profesor profesor) {

	    System.out.print("Ingrese el titulo del examen: ");
	    String titulo = scanner.nextLine();
	    if (titulo.isEmpty()) {
	        System.out.println("El título del examen no puede estar vacío.");
	        return; 
	    }

	    System.out.print("Ingrese la descripcion del examen: ");
	    String descripcion = scanner.nextLine();
	    if (descripcion.isEmpty()) {
	        System.out.println("La descripción del examen no puede estar vacía.");
	        return; 
	    }

	    System.out.print("Ingrese los objetivos del examen (separados por comas): ");
	    String objetivosInput = scanner.nextLine();
	    List<String> objetivos = new ArrayList<>();
	    if (objetivosInput.isEmpty()) {
	        System.out.println("Los objetivos no pueden estar vacíos.");
	        return;
	    }
	    for (String objetivo : objetivosInput.split(",")) {
	        objetivos.add(objetivo.trim());
	    }

	    System.out.print("Ingrese el nivel de dificultad del examen: ");
	    String dificultad = scanner.nextLine();
	    if (dificultad.isEmpty()) {
	        System.out.println("El nivel de dificultad no puede estar vacío.");
	        return; 
	    }

	    System.out.print("Ingrese la duracion en minutos del examen: ");
	    int duracion = 0;
	    try {
	        duracion = Integer.parseInt(scanner.nextLine());
	        if (duracion <= 0) {
	            System.out.println("La duración debe ser un número positivo.");
	            return;
	        }
	    } catch (NumberFormatException e) {
	        System.out.println("La duración debe ser un número entero.");
	        return; 
	    }

	    System.out.print("Ingrese la fecha limite del examen (formato: dd/MM/yyyy): ");
	    String fechaInput = scanner.nextLine();
	    Date fechaLimite = null;
	    try {
	        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	        fechaLimite = sdf.parse(fechaInput);
	        if (fechaLimite.before(new Date())) {
	            System.out.println("La fecha límite no puede ser una fecha pasada.");
	            return; 
	        }
	    } catch (Exception e) {
	        System.out.println("Formato de fecha incorrecto. Utilizando fecha actual.");
	        fechaLimite = new Date();
	    }

	    List<PreguntaAbierta> preguntas = new ArrayList<>();
	    System.out.println("Ingrese las preguntas del examen en el formato 'título|enunciado', separadas por ';': ");
	    String preguntasInput = scanner.nextLine();
	    if (preguntasInput.isEmpty()) {
	        System.out.println("Las preguntas no pueden estar vacías.");
	        return; 
	    }
	    for (String preguntaData : preguntasInput.split(";")) {
	        String[] partes = preguntaData.split("\\|");
	        if (partes.length != 2) {
	            System.out.println("Formato de pregunta inválido. Asegúrese de usar 'título|enunciado'.");
	            return; 
	        }
	        String tituloPregunta = partes[0].trim();
	        String enunciado = partes[1].trim();

	        if (tituloPregunta.isEmpty() || enunciado.isEmpty()) {
	            System.out.println("El título y el enunciado de cada pregunta no pueden estar vacíos.");
	            return; 
	        }

	        PreguntaAbierta pregunta = new PreguntaAbierta(enunciado, tituloPregunta);
	        preguntas.add(pregunta);
	    }

	    aplicacion.crearExamen(titulo, descripcion, objetivos, dificultad, duracion, fechaLimite, profesor, preguntas);
	    System.out.println("Examen registrado exitosamente.");
	}

	private static void crearEncuesta(Profesor profesor) {

	    System.out.print("Ingrese el titulo de la encuesta: ");
	    String titulo = scanner.nextLine();
	    if (titulo.isEmpty()) {
	        System.out.println("El título de la encuesta no puede estar vacío.");
	        return; 
	    }

	
	    System.out.print("Ingrese la descripcion de la encuesta: ");
	    String descripcion = scanner.nextLine();
	    if (descripcion.isEmpty()) {
	        System.out.println("La descripción de la encuesta no puede estar vacía.");
	        return; 
	    }

	    System.out.print("Ingrese los objetivos de la encuesta (separados por comas): ");
	    String objetivosInput = scanner.nextLine();
	    List<String> objetivos = new ArrayList<>();
	    if (objetivosInput.isEmpty()) {
	        System.out.println("Los objetivos no pueden estar vacíos.");
	        return; 
	    }
	    for (String objetivo : objetivosInput.split(",")) {
	        objetivos.add(objetivo.trim());
	    }

	    System.out.print("Ingrese el nivel de dificultad de la encuesta: ");
	    String dificultad = scanner.nextLine();
	    if (dificultad.isEmpty()) {
	        System.out.println("El nivel de dificultad no puede estar vacío.");
	        return; 
	    }


	    System.out.print("Ingrese la duracion en minutos de la encuesta: ");
	    int duracion = 0;
	    try {
	        duracion = Integer.parseInt(scanner.nextLine());
	        if (duracion <= 0) {
	            System.out.println("La duración debe ser un número positivo.");
	            return; 
	        }
	    } catch (NumberFormatException e) {
	        System.out.println("La duración debe ser un número entero.");
	        return; 
	    }

	    System.out.print("Ingrese la fecha limite de la encuesta (formato: dd/MM/yyyy): ");
	    String fechaInput = scanner.nextLine();
	    Date fechaLimite = null;
	    try {
	        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	        fechaLimite = sdf.parse(fechaInput);
	        if (fechaLimite.before(new Date())) {
	            System.out.println("La fecha límite no puede ser una fecha pasada.");
	            return; 
	        }
	    } catch (Exception e) {
	        System.out.println("Formato de fecha incorrecto. Utilizando fecha actual.");
	        fechaLimite = new Date();
	    }

	    List<PreguntaAbierta> preguntas = new ArrayList<>();
	    System.out.println("Ingrese las preguntas de la encuesta en el formato 'título|enunciado', separadas por ';': ");
	    String preguntasInput = scanner.nextLine();
	    if (preguntasInput.isEmpty()) {
	        System.out.println("Las preguntas no pueden estar vacías.");
	        return; 
	    }
	    for (String preguntaData : preguntasInput.split(";")) {
	        String[] partes = preguntaData.split("\\|");
	        if (partes.length != 2) {
	            System.out.println("Formato de pregunta inválido. Asegúrese de usar 'título|enunciado'.");
	            return; 
	        }
	        String tituloPregunta = partes[0].trim();
	        String enunciado = partes[1].trim();

	        if (tituloPregunta.isEmpty() || enunciado.isEmpty()) {
	            System.out.println("El título y el enunciado de cada pregunta no pueden estar vacíos.");
	            return;
	        }

	        PreguntaAbierta pregunta = new PreguntaAbierta(enunciado, tituloPregunta);
	        preguntas.add(pregunta);
	    }

	    aplicacion.crearEncuesta(titulo, descripcion, objetivos, dificultad, duracion, fechaLimite, profesor, preguntas);
	    System.out.println("Encuesta registrada exitosamente.");
	}


	private static void crearLearningPath(Profesor profesor) {
	    
	    System.out.print("Ingrese el titulo del learning path: ");
	    String titulo = scanner.nextLine();
	    if (titulo.isEmpty()) {
	        System.out.println("El título del learning path no puede estar vacío.");
	        return; 
	    }

	    System.out.print("Ingrese la descripcion del learning path: ");
	    String descripcion = scanner.nextLine();
	    if (descripcion.isEmpty()) {
	        System.out.println("La descripción del learning path no puede estar vacía.");
	        return; 
	    }

	    System.out.print("Ingrese los objetivos del learning path (separados por comas): ");
	    String objetivosInput = scanner.nextLine();
	    List<String> objetivos = new ArrayList<>();
	    if (objetivosInput.isEmpty()) {
	        System.out.println("Los objetivos no pueden estar vacíos.");
	        return; 
	    }
	    for (String objetivo : objetivosInput.split(",")) {
	        objetivos.add(objetivo.trim());
	    }

	    System.out.print("Ingrese el nivel de dificultad de la actividad: ");
	    String dificultad = scanner.nextLine();
	    if (dificultad.isEmpty()) {
	        System.out.println("El nivel de dificultad no puede estar vacío.");
	        return; 
	    }

	    List<Actividad> actividades = new ArrayList<>();
	    String continuar;
	    do {
	        System.out.print("Ingrese el ID de la actividad: ");
	        String idActividad = scanner.nextLine();
	        if (idActividad.isEmpty()) {
	            System.out.println("El ID de la actividad no puede estar vacío.");
	            return; 
	        }

	        System.out.print("Ingrese el tipo de la actividad: ");
	        String tipo = scanner.nextLine();
	        if (tipo.isEmpty()) {
	            System.out.println("El tipo de la actividad no puede estar vacío.");
	            return; 
	        }

	        Actividad actividad = aplicacion.getActividad(idActividad, tipo);
	        if (actividad != null) {
	            actividades.add(actividad);
	        } else {
	            System.out.println("Actividad no encontrada. Intente nuevamente.");
	        }

	        System.out.print("¿Desea agregar otra actividad? (si/no): ");
	        continuar = scanner.nextLine();
	    } while (continuar.equalsIgnoreCase("si"));

	    Map<String, Boolean> mapaObligatoriedad = new HashMap<>();
	    for (Actividad actividad : actividades) {
	        System.out.print("¿La actividad '" + actividad.getTitulo() + "' es obligatoria? (true/false): ");
	        String esObligatoria = scanner.nextLine();
	        if (!esObligatoria.equalsIgnoreCase("true") && !esObligatoria.equalsIgnoreCase("false")) {
	            System.out.println("La respuesta debe ser 'true' o 'false'.");
	            return; 
	        }
	        mapaObligatoriedad.put(actividad.getTitulo(), esObligatoria.equalsIgnoreCase("true"));
	    }

	    aplicacion.crearLearningPath(titulo, descripcion, objetivos, dificultad, profesor, actividades, mapaObligatoriedad);
	    System.out.println("Learning Path creado exitosamente.");
	}
    
    private static void clonarActividad(Profesor profesor) {
        
        System.out.print("Ingrese el id de la actividad que desea clonar: ");
        String id = scanner.nextLine();
        if (id.isEmpty()) {
            System.out.println("El ID de la actividad no puede estar vacío.");
            return; 
        }

        System.out.print("Ingrese el tipo de la actividad que desea clonar: ");
        String tipo = scanner.nextLine();
        if (tipo.isEmpty()) {
            System.out.println("El tipo de la actividad no puede estar vacío.");
            return; 
        }

        Actividad actividadOriginal = aplicacion.getActividad(id, tipo);
        if (actividadOriginal == null) {
            System.out.println("No se encontró una actividad con el ID y tipo proporcionados.");
            return; 
        }

        aplicacion.clonarActividad(actividadOriginal,profesor);
        System.out.println("Actividad clonada exitosamente.");
    }

    
    private static void crearPregunta(Profesor profesor) {
    	System.out.print("Ingrese el titulo que le desea dar a la pregunta: ");
        String titulo = scanner.nextLine();
        System.out.print("Ingrese el enunciado de la pregunta: ");
        String enunciado = scanner.nextLine();

        int tipo = 0;
        while (tipo > 3 && tipo <1) {
        	
	        System.out.print("Escoga el tipo de pregunta que desea crear:");
	        System.out.println("1. Selección Múltiple");
	        System.out.println("2. Verdadero o Falso");
	        System.out.println("3. Abierta");
	        System.out.println("Opción escogida: ");
	        
	        tipo = Integer.parseInt(scanner.nextLine());
	        
	        if (tipo > 3 && tipo <1) {
	        	System.out.println("Opción no válida, intente de nuevo");
	        }
        }
        
        if (tipo == 1) {
        	System.out.println("Ingrese la primera opción:\n");
        	String opcion1 = scanner.nextLine();
        	System.out.println("Ingrese la segunda opción:\n");
        	String opcion2 = scanner.nextLine();
        	System.out.println("Ingrese la tercera opción:\n");
        	String opcion3 = scanner.nextLine();
        	System.out.println("Ingrese la cuarta opción:\n");
        	String opcion4 = scanner.nextLine();
        	
        	System.out.println("Ingrese el número de la opción correcta:\n");
        	int opcionCorrecta = Integer.parseInt(scanner.nextLine());
        	
        	aplicacion.crearPreguntaSeleccion(enunciado, titulo, opcion1, opcion2, opcion3, opcion4, opcionCorrecta, profesor);
        }
        
        else if (tipo == 2) {
        	System.out.println("Indique con un '0' si la afirmacion en el enunciada es falsa o con un '1' si es verdadera:\n");
        	int opcionCorrecta = Integer.parseInt(scanner.nextLine());
        	aplicacion.crearPreguntaBoolean(enunciado, titulo, opcionCorrecta, profesor);
        	
        }
        
        else if (tipo == 3) {
        	aplicacion.crearPreguntaAbierta(enunciado, titulo, profesor);
        }
        
        
        
        
    	
    	
    }
        	
	private static void clonarLearningPath(Profesor profesor) {

	    System.out.print("Ingrese el ID del Learning Path que desea clonar: ");
	    String idLearningPathOriginal = scanner.nextLine();
	    if (idLearningPathOriginal.isEmpty()) {
	        System.out.println("El ID del Learning Path no puede estar vacío.");
	        return; 
	    }

	    HashMap<String, LearningPath> mapaLearningPaths = aplicacion.getMapaLearningPaths(); 
	    LearningPath learningPathOriginal = mapaLearningPaths.get(idLearningPathOriginal);
	    
	    if (learningPathOriginal == null) {
	        System.out.println("Learning Path no encontrado. Verifique el ID e intente de nuevo.");
	        return; 
	    }

	    
	    aplicacion.clonarLearningPath(learningPathOriginal, profesor);


	    aplicacion.clonarLearningPath(learningPathOriginal, profesor);
	    System.out.println("Learning Path clonado con éxito para el profesor: " + profesor.getNombre());
	}

	
	private static void modificarLearningPath(Profesor profesor) {
	    System.out.print("Ingrese el ID del Learning Path a modificar: ");
	    String idLP = scanner.nextLine();
	    if (idLP.isEmpty()) {
	        System.out.println("El ID del Learning Path no puede estar vacío.");
	        return; 
	    }

	    // Obtener el Learning Path
	    LearningPath learningPath = profesor.getLearningPathPropios().get(idLP);
	    
	    if (learningPath != null) {

	        System.out.println("Seleccione el atributo a modificar:");
	        System.out.println("1. Titulo");
	        System.out.println("2. Descripcion");
	        System.out.println("3. Dificultad");
	        System.out.println("4. Objetivos");
	        System.out.println("5. Actividades");
	        
	        int opcion = 0;
	        try {
	            opcion = Integer.parseInt(scanner.nextLine());
	        } catch (NumberFormatException e) {
	            System.out.println("Opción no válida. Debe ingresar un número.");
	            return;
	        }
	        
	        if (opcion == 5) {
	    
	            System.out.print("Ingrese el ID de la actividad a modificar: ");
	            String idActividad = scanner.nextLine();
	            if (idActividad.isEmpty()) {
	                System.out.println("El ID de la actividad no puede estar vacío.");
	                return; 
	            }

	            System.out.print("Ingrese el tipo de la actividad a modificar: ");
	            String tipoActividad = scanner.nextLine();
	            if (tipoActividad.isEmpty()) {
	                System.out.println("El tipo de actividad no puede estar vacío.");
	                return; 
	            }

	            Actividad actividad = aplicacion.getActividad(idActividad, tipoActividad);
	            
	            if (actividad != null) {
	                System.out.println("Seleccione la acción que desea realizar:");
	                System.out.println("1. Agregar");
	                System.out.println("2. Eliminar");
	                System.out.println("3. Modificar obligatoriedad");
	                
	                int opcionActividad = 0;
	                try {
	                    opcionActividad = Integer.parseInt(scanner.nextLine());
	                } catch (NumberFormatException e) {
	                    System.out.println("Opción no válida. Debe ingresar un número.");
	                    return;
	                }

	                String accion = "";
	                switch (opcionActividad) {
	                    case 1:
	                        accion = "Agregar";
	                        boolean obligatoriedad;
	                        System.out.println("Indique si la actividad será obligatoria o no:");
	                        System.out.println("1. Obligatoria");
	                        System.out.println("2. No obligatoria");
	                        int opcionObligatoriedad = 0;
	                        try {
	                            opcionObligatoriedad = Integer.parseInt(scanner.nextLine());
	                        } catch (NumberFormatException e) {
	                            System.out.println("Opción no válida. Debe ingresar un número.");
	                            return;
	                        }

	                        if (opcionObligatoriedad == 1) {
	                            obligatoriedad = true;
	                        } else if (opcionObligatoriedad == 2) {
	                            obligatoriedad = false;
	                        } else {
	                            System.out.println("Opción no válida.");
	                            return;
	                        }

	                        try {
	                            aplicacion.modificarActividadesLearningPath(learningPath, actividad, obligatoriedad, accion);
	                        } catch (ModificarActividadesLearningPathException e) {
	                            System.out.println("Error al modificar el atributo: " + e.getMessage());
	                        }
	                        break;

	                    case 2:
	                        accion = "Eliminar";
	                        try {
	                            aplicacion.modificarActividadesLearningPath(learningPath, actividad, false, accion);
	                        } catch (ModificarActividadesLearningPathException e) {
	                            System.out.println("Error al modificar el atributo: " + e.getMessage());
	                        }
	                        break;

	                    case 3:
	                        accion = "Obligatoriedad";
	                        try {
	                            aplicacion.modificarActividadesLearningPath(learningPath, actividad, false, accion);
	                        } catch (ModificarActividadesLearningPathException e) {
	                            System.out.println("Error al modificar el atributo: " + e.getMessage());
	                        }
	                        break;

	                    default:
	                        System.out.println("Opción no válida.");
	                        return;
	                }

	            } else {
	                System.out.println("Actividad no encontrada en la base de datos");
	                return;
	            }

	        } else {
	            String atributoModificar = "";
	            String valor = "";
	            String accion = "";

	            switch (opcion) {
	                case 1:
	                    atributoModificar = "Titulo";
	                    System.out.print("Ingrese el nuevo titulo: ");
	                    valor = scanner.nextLine();
	                    if (valor.isEmpty()) {
	                        System.out.println("El título no puede estar vacío.");
	                        return;
	                    }
	                    break;
	                case 2:
	                    atributoModificar = "Descripcion";
	                    System.out.print("Ingrese la nueva descripcion: ");
	                    valor = scanner.nextLine();
	                    if (valor.isEmpty()) {
	                        System.out.println("La descripción no puede estar vacía.");
	                        return;
	                    }
	                    break;
	                case 3:
	                    atributoModificar = "Dificultad";
	                    System.out.print("Ingrese la nueva dificultad: ");
	                    valor = scanner.nextLine();
	                    if (valor.isEmpty()) {
	                        System.out.println("La dificultad no puede estar vacía.");
	                        return;
	                    }
	                    break;
	                case 4:
	                    atributoModificar = "Objetivos";
	                    System.out.print("Seleccione la acción (Agregar/Eliminar): ");
	                    accion = scanner.nextLine();
	                    if (accion.isEmpty()) {
	                        System.out.println("La acción no puede estar vacía.");
	                        return;
	                    }
	                    System.out.print("Ingrese el objetivo: ");
	                    valor = scanner.nextLine();
	                    if (valor.isEmpty()) {
	                        System.out.println("El objetivo no puede estar vacío.");
	                        return;
	                    }
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

	        }
	    } else {
	        System.out.println("Learning Path no encontrado.");
	    }
	}

	
	private static void modificarActividad(Profesor profesor) {
	    String msjTitulo = "Ingrese el título de la actividad a modificar: ";
	    String msjTipo = "Ingrese el tipo de la actividad a modificar: ";
	    
	    Actividad actividad = getActividad(msjTitulo, "", msjTipo, true, profesor);
	    String tipo = actividad.getTipoActividad();

	    if (actividad != null) {
	    	
	    	String menuGeneral = "Seleccione el atributo a modificar:\n" +
					 "1. Titulo\n"+
					 "2. Descripcion\n"+
					 "3. Dificultad\n"+
					 "4. Objetivos\n"+
					 "5. Fecha Limite\n"+
					 "6. Duracion\n"+
					 "7. Actividades Previas\n"+
					 "8. Actividades de Seguimiento"
					 ;
	        
	    	System.out.println(menuGeneral);
	    	
	    	if (tipo == "Recurso") {
	    		System.out.println("9. Tipo de Recurso");
	    		System.out.println("10. Enlace de Recurso");
	    		
		        
	    	}
	    	
	    	else if (tipo =="Examen"|tipo =="Encuesta") {
	    		System.out.println("9. Agregar Pregunta");
	    		System.out.println("10. Eliminar Pregunta");
	    	}
	    	
	    	else if (tipo == "Quiz") {
	    		System.out.println("9. Agregar Pregunta");
	    		System.out.println("10. Eliminar Pregunta");
	    		System.out.println("11. Cambiar Calificacion Mínima");
	    	
	    	}
	    	
	    	int opcion = Integer.parseInt(scanner.nextLine());
	    	
	    	if (opcion == 7 | opcion == 8) {
	        	modificarPrevSegActividad(actividad, opcion);
	        }
	        
	        else if (opcion == 9) {
	        	
	        	if (tipo == "Recurso") {
		        	System.out.println("Ingrese el nuevo tipo de recurso: ");
		        	String nuevoTipo = scanner.nextLine();
		        	
		        	aplicacion.modificarRecurso((RevisarRecurso) actividad, nuevoTipo, "Tipo");	
	        	}
	        	
	        	else if(tipo == "Examen"|tipo =="Encuesta") {
	        		
	        		System.out.print("Ingrese el ID de la pregunta que quiere agregar a la actividad: ");
	        	    String idPregunta = scanner.nextLine();
	        	    
	        	    PreguntaAbierta pregunta = (PreguntaAbierta) aplicacion.getPregunta(idPregunta, "Abierta");
	        	    if (pregunta != null) {
		        	    try {
		        	    	aplicacion.modificarPreguntasExamenEncuesta(actividad, pregunta, "Agregar");
		        	    }
		        	    catch (ModificarPreguntasAbiertasException e) {
		        	    	System.out.println("Error al agregar la pregunta: " + e.getMessage());
		        	    }
	        	    }
	        	    else {
	        	    	System.out.println("La pregunta especificada no existe");
	        	    	return;
	        	    }
	        		
	        	}
	        	
	        	else if (tipo == "Quiz") {
	        		System.out.print("Ingrese el ID de la pregunta que quiere agregar a la actividad: ");
	        	    String idPregunta = scanner.nextLine();
	        	    
	        	    System.out.println("Ingrese el tipo de pregunta que quiere agregar (Seleccion/Verdadero o Falso)");
	        	    String tipoPregunta = scanner.nextLine();
	        	    
	        	    PreguntaCerrada pregunta = (PreguntaCerrada) aplicacion.getPregunta(idPregunta, tipoPregunta);
	        	    
	        	    if (pregunta != null) {
	        	    	
	        	    	try {
	        	    		aplicacion.modificarPreguntasQuiz((Quiz) actividad, pregunta, "Agregar");
	        	    	}
	        	    	catch (ModificarPreguntasQuizException e) {
	        	    		System.out.println("Error al modificar la actividad: "+ e.getMessage());
	        	    	}
	        	    	
	        	    }
	        	    
	        	    else {
	        	    	System.out.println("La pregunta especificada no existe");
	        	    	return;
	        	    }
	        	    
	        		
	        	}
	        	
	        }
	        
	        else if (opcion == 10) {
	        	
	        	if( tipo == "Recurso") {
		        	System.out.println("Ingrese el nuevo enlace del recurso: ");
		        	String nuevoTipo = scanner.nextLine();
		        	
		        	aplicacion.modificarRecurso((RevisarRecurso) actividad, nuevoTipo, "Enlace");	
	        	}
	        	
	        	else if(tipo == "Examen"|tipo =="Encuesta") {
	        		
	        		System.out.print("Ingrese el ID de la pregunta que quiere eliminar de la actividad: ");
	        	    String idPregunta = scanner.nextLine();
	        	    
	        	    PreguntaAbierta pregunta = (PreguntaAbierta) aplicacion.getPregunta(idPregunta, "Abierta");
	        	    if (pregunta != null) {
		        	    try {
		        	    	aplicacion.modificarPreguntasExamenEncuesta(actividad, pregunta, "Eliminar");
		        	    }
		        	    catch (ModificarPreguntasAbiertasException e) {
		        	    	System.out.println("Error al modificar la actividad: " + e.getMessage());
		        	    }
	        	    }
	        	    else {
	        	    	System.out.println("La pregunta especificada no existe");
	        	    	return;
	        	    }
	        		
	        	}
	        	
	        	else if (tipo == "Quiz") {
	        		System.out.print("Ingrese el ID de la pregunta que quiere eliminar de la actividad: ");
	        	    String idPregunta = scanner.nextLine();
	        	    
	        	    System.out.println("Ingrese el tipo de pregunta que quiere eliminar (Cerrada/Verdadero o Falso)");
	        	    String tipoPregunta = scanner.nextLine();
	        	    
	        	    PreguntaCerrada pregunta = (PreguntaCerrada) aplicacion.getPregunta(idPregunta, tipoPregunta);
	        	    
	        	    if (pregunta != null) {
	        	    	
	        	    	try {
	        	    		aplicacion.modificarPreguntasQuiz((Quiz) actividad, pregunta, "Eliminar");
	        	    	}
	        	    	catch (ModificarPreguntasQuizException e) {
	        	    		System.out.println("Error al modificar la actividad: "+ e.getMessage());
	        	    	}
	        	    	
	        	    }
	        	    
	        	    else {
	        	    	System.out.println("La pregunta especificada no existe");
	        	    	return;
	        	    }
	        	}
	        }
	    	
	        else if (opcion == 11) {
	        	System.out.print("Ingrese la nueva calificacion mínima del quiz: ");
        	    double nuevaCalificacion = Double.parseDouble(scanner.nextLine());
        	    
        	    aplicacion.modificarCalificacionMinimaQuiz((Quiz) actividad, nuevaCalificacion);
        	    
	        }
	        
	        else {
	        modificarActividadGeneral(actividad, opcion);
	        }
	    	
	         
	    } 
	    
	    else {
	        System.out.println("Actividad no encontrada.");
	    }
	}
	
	private static void modificarActividadGeneral(Actividad actividad, int opcion) {
		String atributoModificar = null;
        String valor = null;
        String accion = null;
        Date fecha = null;
        Integer duracion = null;

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

            case 5:
                atributoModificar = "Fecha Limite";
                System.out.print("Ingrese la nueva fecha limite (yyyy-MM-dd): ");
                try {
                    fecha = new SimpleDateFormat("yyyy-MM-dd").parse(scanner.nextLine());
                } catch (ParseException e) {
                    System.out.println("Formato de fecha incorrecto.");
                    return;
                }
                break;

            case 6:
                atributoModificar = "Duracion";
                System.out.print("Ingrese la nueva duración en minutos: ");
                duracion = Integer.parseInt(scanner.nextLine());
                break;
                
            default:
                System.out.println("Opción no válida.");
                return;
        }

        try {
        	
            aplicacion.modificarActividad(actividad, valor, atributoModificar, accion, fecha, duracion);
            System.out.println("Atributo modificado exitosamente.");          
            
            
        } catch (ModificarObjetivosException e) {
            System.out.println("Error al modificar el atributo: " + e.getMessage());
        }
	}
	
	private static void modificarPrevSegActividad(Actividad actividadPrincipal, int opcion) {
			
			String tipo;
			String msjTitulo;
			String msjProfesor;
			String msjTipo;
			
			if (opcion == 7) {
				tipo = "Previa";
				msjTitulo = "Ingrese el título de la actividad a agregar/eliminar de las actividades previas: ";
				msjProfesor = "Ingrese el login del profesor creador de la actividad a agregar/eliminar de las actividades previas: ";
				msjTipo = "Ingrese el tipo de la actividad a agregar/eliminar de las actividades previas: ";
			}
			else {
				tipo = "Seguimiento";
				msjTitulo = "Ingrese el título de la actividad a agregar/eliminar de las actividades de seguimiento: ";
				msjProfesor = "Ingrese el login del profesor creador de la actividad a agregar/eliminar de las actividades de seguimiento: ";
				msjTipo = "Ingrese el tipo de la actividad a agregar/eliminar de las actividades de seguimiento: ";
			}
			
			
		
			
		    
		
		    
		    Actividad actividadOperacion = getActividad(msjTitulo, msjProfesor, msjTipo, false, null);
			
		    if (actividadOperacion != null) {
				System.out.println("Indique la accion que quiere realizar:");
				System.out.println("1. Agregar");
				System.out.println("2. Eliminar");
				System.out.println("Opcion escogida: ");
				
				int accion = Integer.parseInt(scanner.nextLine());
			
				if (accion == 1 | accion == 2) {
					
					try {
						aplicacion.modificarSeguimientoPrevioActividad(actividadPrincipal, actividadOperacion, tipo, accion);
						System.out.println("Actividad modificada exitosamente");
					}
					
					catch (ModificarActividadesPreviasException | ModificarActividadesSeguimientoException e) {
						System.out.println("Error al modificar actividad: " + e.getMessage());
					}
					
				}	
			
		    }
		    
		    else {
		    	return;
		    }
	
	}

	private static void modificarPregunta (Profesor profesor) {
		System.out.println("Ingrese el ID de la pregunta que desea modificar: ");
		String idPregunta = scanner.nextLine();
		
		System.out.println("Escoga el tipo de pregunta que desea modificar");
		System.out.println("1. Selección Múltiple");
		System.out.println("2. Verdadero o Falso");
		System.out.println("3. Abierta");
		System.out.println("Opcion escogida: ");
		
		int tipo = Integer.parseInt(scanner.nextLine());
		String tipoString = "";
		
		if (tipo == 1) {
			tipoString = "Cerrada";
			
		}
		else if (tipo == 2) {
			tipoString = "Verdadero o Falso";
			
		}	
		
		else if (tipo == 3) {
			tipoString = "Abierta";
		}
		
		
		Pregunta pregunta = aplicacion.getPregunta(idPregunta, tipoString);
		
		if (pregunta != null) {
			
			String menuGeneral = "Escoga el atributo (número) que desea modificar\n" +
								 "1. Titulo\n"+
								 "2. Enunciado";
			
			if (tipo ==1) {
				
				System.out.println(menuGeneral);
				System.out.println("3. Opción correcta");
				System.out.println("4. Opciones posibles");
				
			}
			if (tipo ==2) {
				
				System.out.println(menuGeneral);
				System.out.println("3. Opción correcta");
			}
			
			System.out.println("Atributo escogido: ");
			int atributo = Integer.parseInt(scanner.nextLine());
			
			if (atributo == 1) {
				
				System.out.println("Ingrese el nuevo título para la pregunta: ");
				String nuevoTitulo = scanner.nextLine();
				
				aplicacion.modificarTituloPregunta(pregunta, nuevoTitulo, profesor);
				
			}
			else if (atributo == 2) {
				
				System.out.println("Ingrese el nuevo enunciado para la pregunta: ");
				String nuevoEnunciado = scanner.nextLine();
				
				aplicacion.modificarEnunciadoPregunta(pregunta, nuevoEnunciado);
				
			}
			
			else if (atributo == 3) {
				
				if (tipo == 2) {
					PreguntaCerrada preguntaMod = (PreguntaCerrada) pregunta;
					int opcionActual = preguntaMod.getOpcionCorrecta();
					if (opcionActual == 0) {
						aplicacion.modificarOpcionCorrectaPreguntaCerrada((PreguntaCerrada) pregunta, 1);
						System.out.println("Opcion correcta modificada exitosamente de 'Falso' a 'Verdadero'.");
					}
					else {
						aplicacion.modificarOpcionCorrectaPreguntaCerrada((PreguntaCerrada) pregunta, 0);
						System.out.println("Opción correcta modificada exitosamente de 'Verdadero' a 'Falso'.");
					}
				}
				
				else if (tipo == 1) {
					
					System.out.println("Ingrese el número de la opción que desea que sea la opción correcta: ");
					int numOpcion = Integer.parseInt(scanner.nextLine());
					
					if (numOpcion>4 & numOpcion<1) {
						System.out.println("Opción invalida");
					}
					else {
						aplicacion.modificarOpcionCorrectaPreguntaCerrada((PreguntaCerrada) pregunta, numOpcion);
					}
					
				}
				
			}
			
			else if (atributo == 4) {
				System.out.println("Ingrese el número de la opción que desea que sea la opción correcta: ");
				int numOpcion = Integer.parseInt(scanner.nextLine());
				
				System.out.println("Ingrese el nuevo texto de la opción que desea modificar: ");
				String nuevaOpcion = (scanner.nextLine());
				
				aplicacion.modificarOpcionesPreguntaSeleccion((PreguntaSeleccionMultiple) pregunta, nuevaOpcion, numOpcion);
			}
			
			else {
				System.out.println("Opción invalida.");
				return;
			}
			
		}
		
		else {
			System.out.println("Pregunta no encontrada");
			return;
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
            System.out.println("Ingrese el id del examen: ");
            String idExamen = scanner.nextLine();
            Examen examen = aplicacion.getMapaExamenes().get(idExamen);

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
	
	private static void revisarActividadRepetida() {
	    System.out.print("Ingrese el título de la actividad: ");
	    String titulo = scanner.nextLine();

	    System.out.print("Ingrese el login del profesor: ");
	    String login = scanner.nextLine();

	    System.out.println("Seleccione el tipo de actividad:");
	    System.out.println("1. Tarea");
	    System.out.println("2. Quiz");
	    System.out.println("3. Recurso");
	    System.out.println("4. Examen");
	    System.out.println("5. Encuesta");
	    int tipoOpcion = Integer.parseInt(scanner.nextLine());
	    String tipo = "";

	    switch (tipoOpcion) {
	        case 1:
	            tipo = "Tarea";
	            break;
	        case 2:
	            tipo = "Quiz";
	            break;
	        case 3:
	            tipo = "Recurso";
	            break;
	        case 4:
	            tipo = "Examen";
	            break;
	        case 5:
	            tipo = "Encuesta";
	            break;
	        default:
	            System.out.println("Opción no válida.");
	            return;
	    }

	    try {
	        aplicacion.revisarActividadRepetida(titulo, login, tipo);
	        System.out.println("La actividad no existe, puede ser creada.");
	    } catch (ActividadYaExistenteException e) {
	        System.out.println("La actividad '" + titulo + "' de tipo '" + tipo + "' ya existe.");
	    }
	}
	
	private static void revisarLearningPathRepetido() {
		System.out.print("Ingrese el título de la actividad: ");
	    String titulo = scanner.nextLine();

	    System.out.print("Ingrese el login del profesor: ");
	    String login = scanner.nextLine();

	    try {
	        aplicacion.revisarLearningPathRepetido(titulo, login);
	        System.out.println("El learning Path no existe, puede ser creado.");
	    } catch (LearningPathYaExistenteException e) {
	        System.out.println("El learning Path " + titulo + "ya existe.");
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
    
    
    private static void calificarResenarActividad() {
    	
    	
    	String msjTitulo = "Indique el titulo de la actividad que desea reseñar o calificar";
    	String msjProfesor = "Indique el login del profesor creador de la actividad que desea reseñar o calificar";
    	String msjTipo = "Indique el tipo de actividad que desea reseñar o calificar";
    	Actividad actividad = getActividad(msjTitulo, msjProfesor, msjTipo, false, null);
    	
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
    	String msjTitulo = "Indique el título del Learning Path que desea calificar: ";
    	String msjProfesor = "Indique el login del profesor creador del Learning Path que desea calificar";
    	
    	LearningPath learningPath = getLearningPath(msjTitulo, msjProfesor, false, null);
    	
    	if (learningPath != null) {
    		System.out.println("Ingrese la calificación que desea dejar al Learning Path: ");
			double calificacion = Double.parseDouble(scanner.nextLine());
			aplicacion.calificarLearningPath(learningPath, calificacion);
    	}
    }
    
    private static Actividad getActividad(String msjTitulo, String msjProfesor, String msjTipo, boolean actividadPropia, Profesor profesor) {
    	System.out.println(msjTitulo);
		String titulo = scanner.nextLine();
		String login;
    	if (actividadPropia == true) {
    		login = profesor.getLogin();
    	}
    	
    	else {
    		System.out.println(msjProfesor);
    		login = scanner.nextLine();
    	}
    	System.out.println(msjTipo);
		String tipo = scanner.nextLine();
    	
		String idActividad = aplicacion.generarLlaveLearningsActividades(titulo, login);
		
		Actividad actividad = aplicacion.getActividad(idActividad, tipo);
		
		return actividad;

    }
    
    private static LearningPath getLearningPath(String msjTitulo, String msjProfesor, boolean learningPathPropio, Profesor profesor) {
    	System.out.println(msjTitulo);
		String titulo = scanner.nextLine();
		String login;
    	if (learningPathPropio == true) {
    		login = profesor.getLogin();
    	}
    	
    	else {
    		System.out.println(msjProfesor);
    		login = scanner.nextLine();
    	}
    	
    	
		String idLearningPath = aplicacion.generarLlaveLearningsActividades(titulo, login);
		
		LearningPath learningPath = aplicacion.getLearningPath(idLearningPath);
		
		return learningPath;
    }

}
