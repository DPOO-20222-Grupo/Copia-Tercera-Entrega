package Consolas;

import java.util.Scanner;   

import actividades.Actividad;
import actividades.Examen;
import actividades.Quiz;
import actividades.RevisarRecurso;
import actividades.Tarea;
import exceptions.ActividadPreviaCiclicoException;
import exceptions.ActividadSeguimientoCiclicoException;
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
import seguimientoEstudiantes.SeguimientoActividad;
import seguimientoEstudiantes.SeguimientoExamen;
import seguimientoEstudiantes.SeguimientoLearningPath;
import seguimientoEstudiantes.SeguimientoQuiz;
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
    
	private  Aplicacion aplicacion ;  
    private static Scanner scanner = new Scanner(System.in);
    
    public ProfesorConsole(Aplicacion aplicacion) {
    	this.aplicacion = aplicacion;
    }

    public void loginPlataforma() {
        
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

    public  void mostrarMenuProfesor(Profesor profesor) {
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
            System.out.println("17. Ver mis Learning Paths");
            System.out.println("18. Ver el progreso de un estudiante en un Learning Path");
            System.out.println("19. Reseñar o calificar una actividad");
            System.out.println("20. Calificar un Learning Path");
            System.out.println("21. Cerrar sesión");
            System.out.print("Seleccione una opción: ");
            
            try {
                opcion = Integer.parseInt(scanner.nextLine()); 

                if (opcion < 1 || opcion > 21) {
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
                        revisarActividadRepetida(profesor);
                        
                        break;
                    case 15:
                        revisarLearningPathRepetido(profesor);
                        
                        break;
                    case 16:
                        verActividades(profesor);
                         
                        break;
                    case 17:
                        verLearningPaths(profesor);
                        
                        break;
                    case 18:
                        verProgresoLearningPathEstudiante();
                        
                        break;
                        
                    case 19:
                        calificarResenarActividad();
                         
                        break;
                    case 20:
                        calificarLearningPath();
                        
                        break;
                    case 21:
                        profesor.logout();
                        System.out.println("Sesión cerrada.");
                        
                        break;
                }

                

            } catch (NumberFormatException e) {
                
                System.out.println("Entrada no válida. Por favor, ingrese un número.");
            }

        } while (opcion != 21); 
    }


    private  void crearRevisarRecurso(Profesor profesor) {

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

        
        String dificultad;
        int opcion = 0;
        
        do {
        	System.out.print("Ingrese el nivel de dificultad del recurso a revisar");
        	System.out.print("\n1. Principiante");
        	System.out.print("\n2. Intermedio");
        	System.out.print("\n3. Avanzado");
        	System.out.print("\nOpción escogida: ");
        	
        	if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine();  
                if (opcion < 1 || opcion > 3) {
                    System.out.println("Opción inválida. Debe ser un número entre 1 y 3.");
                }
            } else {
                System.out.println("Por favor, ingrese un número válido.");
                scanner.nextLine();  
            }
            
        } while (opcion<1 || opcion>3);
        
        if (opcion ==1) {
        	dificultad = Actividad.BAJO;
        	
        }
        
        else if(opcion == 2) {
        	dificultad = Actividad.MEDIO;
        }
        
        else {
        	dificultad = Actividad.ALTO;
        }
        

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

    private  void crearTarea(Profesor profesor) {

        System.out.print("Ingrese el título de la tarea: ");
        String titulo;
        do {
            titulo = scanner.nextLine().trim();
            if (titulo.isEmpty()) {
                System.out.println("Error: El título no puede estar vacío.");
                System.out.print("Ingrese el título de la tarea: ");
            }
        } while (titulo.isEmpty());

        System.out.print("Ingrese la descripción de la tarea: ");
        String descripcion;
        do {
            descripcion = scanner.nextLine().trim();
            if (descripcion.isEmpty()) {
                System.out.println("Error: La descripción no puede estar vacía.");
                System.out.print("Ingrese la descripción de la tarea: ");
            }
        } while (descripcion.isEmpty());

        try {
            aplicacion.revisarActividadRepetida(titulo, profesor.getLogin(), "Tarea");
        } catch (ActividadYaExistenteException e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }

        System.out.print("Ingrese los objetivos de la tarea (separados por comas): ");
        List<String> objetivos = new ArrayList<>();
        while (objetivos.isEmpty()) {
            String objetivosInput = scanner.nextLine().trim();
            if (objetivosInput.isEmpty()) {
                System.out.println("Error: Debe ingresar al menos un objetivo.");
                System.out.print("Ingrese los objetivos de la tarea (separados por comas): ");
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

        String dificultad;
        int opcion = 0;
        
        do {
        	System.out.print("Ingrese el nivel de dificultad de la tarea");
        	System.out.print("\n1. Principiante");
        	System.out.print("\n2. Intermedio");
        	System.out.print("\n3. Avanzado");
        	System.out.print("\nOpción escogida: ");
        	
        	if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine();  
                if (opcion < 1 || opcion > 3) {
                    System.out.println("Opción inválida. Debe ser un número entre 1 y 3.");
                }
            } else {
                System.out.println("Por favor, ingrese un número válido.");
                scanner.nextLine();  
            }
            
        } while (opcion<1 || opcion>3);
        
        if (opcion ==1) {
        	dificultad = Actividad.BAJO;
        	
        }
        
        else if(opcion == 2) {
        	dificultad = Actividad.MEDIO;
        }
        
        else {
        	dificultad = Actividad.ALTO;
        }
        

        System.out.print("Ingrese la duración en minutos de la tarea: ");
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
                System.out.print("Ingrese la duración en minutos de la tarea: ");
            }
        }

        System.out.print("Ingrese la fecha límite de la tarea (formato: dd/MM/yyyy): ");
        Date fechaLimite = null;
        while (fechaLimite == null) {
            String fechaInput = scanner.nextLine().trim();
            if (fechaInput.isEmpty()) {
                System.out.println("Error: La fecha no puede estar vacía.");
                System.out.print("Ingrese la fecha límite de la tarea (formato: dd/MM/yyyy): ");
                continue;
            }
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                sdf.setLenient(false);
                fechaLimite = sdf.parse(fechaInput);
            } catch (Exception e) {
                System.out.println("Error: Formato de fecha incorrecto. Intente nuevamente.");
                System.out.print("Ingrese la fecha límite de la tarea (formato: dd/MM/yyyy): ");
            }
        }

        aplicacion.crearTarea(titulo, descripcion, objetivos, dificultad, duracion, fechaLimite, profesor);
        System.out.println("Tarea registrada exitosamente.");
    }

    private  void crearQuiz(Profesor profesor) {

        System.out.print("Ingrese el título del quiz: ");
        String titulo;
        do {
            titulo = scanner.nextLine().trim();
            if (titulo.isEmpty()) {
                System.out.println("El título del quiz no puede estar vacío.");
                System.out.print("Ingrese el título del quiz: ");
            }
        } while (titulo.isEmpty());

        System.out.print("Ingrese la descripción del quiz: ");
        String descripcion;
        do {
            descripcion = scanner.nextLine().trim();
            if (descripcion.isEmpty()) {
                System.out.println("La descripción del quiz no puede estar vacía.");
                System.out.print("Ingrese la descripción del quiz: ");
            }
        } while (descripcion.isEmpty());

        System.out.print("Ingrese los objetivos del quiz (separados por comas): ");
        List<String> objetivos = new ArrayList<>();
        do {
            String objetivosInput = scanner.nextLine().trim();
            if (objetivosInput.isEmpty()) {
                System.out.println("Los objetivos no pueden estar vacíos.");
                System.out.print("Ingrese los objetivos del quiz (separados por comas): ");
                continue;
            }
            for (String objetivo : objetivosInput.split(",")) {
                objetivos.add(objetivo.trim());
            }
            if (objetivos.isEmpty()) {
                System.out.println("Debe ingresar al menos un objetivo válido.");
            }
        } while (objetivos.isEmpty());

        String dificultad;
        int opcion = 0;
        
        do {
        	System.out.print("Ingrese el nivel de dificultad del quiz");
        	System.out.print("\n1. Principiante");
        	System.out.print("\n2. Intermedio");
        	System.out.print("\n3. Avanzado");
        	System.out.print("\nOpción escogida: ");
        	
        	if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine();  
                if (opcion < 1 || opcion > 3) {
                    System.out.println("Opción inválida. Debe ser un número entre 1 y 3.");
                }
            } else {
                System.out.println("Por favor, ingrese un número válido.");
                scanner.nextLine();  
            }
            
        } while (opcion<1 || opcion>3);
        
        if (opcion ==1) {
        	dificultad = Actividad.BAJO;
        	
        }
        
        else if(opcion == 2) {
        	dificultad = Actividad.MEDIO;
        }
        
        else {
        	dificultad = Actividad.ALTO;
        }
        

        System.out.print("Ingrese la duración en minutos del quiz: ");
        int duracion = -1;
        do {
            try {
                String duracionInput = scanner.nextLine().trim();
                if (duracionInput.isEmpty()) {
                    throw new NumberFormatException("Duración vacía.");
                }
                duracion = Integer.parseInt(duracionInput);
                if (duracion <= 0) {
                    System.out.println("La duración debe ser un número positivo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("La duración debe ser un número entero válido.");
                System.out.print("Ingrese la duración en minutos del quiz: ");
            }
        } while (duracion <= 0);

        System.out.print("Ingrese la fecha límite del quiz (formato: dd/MM/yyyy): ");
        Date fechaLimite = null;
        do {
            String fechaInput = scanner.nextLine().trim();
            if (fechaInput.isEmpty()) {
                System.out.println("La fecha límite no puede estar vacía.");
                System.out.print("Ingrese la fecha límite del quiz (formato: dd/MM/yyyy): ");
                continue;
            }
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                fechaLimite = sdf.parse(fechaInput);
                if (fechaLimite.before(new Date())) {
                    System.out.println("La fecha límite no puede ser una fecha pasada.");
                }
            } catch (Exception e) {
                System.out.println("Formato de fecha incorrecto. Intente nuevamente.");
            }
        } while (fechaLimite == null || fechaLimite.before(new Date()));

        double calificacionMinima = -1;
        do {
            System.out.print("Ingrese la calificación mínima para aprobar el quiz: ");
            try {
                String calificacionInput = scanner.nextLine().trim();
                calificacionMinima = Double.parseDouble(calificacionInput);
                if (calificacionMinima < 0 || calificacionMinima > 5) {
                    System.out.println("La calificación mínima debe estar entre 0 y 5.");
                }
            } catch (NumberFormatException e) {
                System.out.println("La calificación mínima debe ser un número válido.");
            }
        } while (calificacionMinima < 0 || calificacionMinima > 5);

        List<PreguntaCerrada> preguntas = new ArrayList<>();
        System.out.println("Ingrese las preguntas del examen en el formato 'título|enunciado': ");
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

            System.out.println("Ingrese las opciones para la pregunta '" + tituloPregunta + "' (opciones separadas por '|'): ");
            String opcionesInput = scanner.nextLine();
            String[] opciones = opcionesInput.split("\\|");
            if (opciones.length != 4) {
                System.out.println("Debe ingresar exactamente 4 opciones.");
                return; 
            }

            System.out.print("Ingrese la opción correcta (1, 2, 3, o 4): ");
            int opcionCorrecta = -1;
            do {
                try {
                    opcionCorrecta = Integer.parseInt(scanner.nextLine().trim());
                    if (opcionCorrecta < 1 || opcionCorrecta > 4) {
                        System.out.println("La opción correcta debe ser un número entre 1 y 4.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Debe ingresar un número entre 1 y 4.");
                }
            } while (opcionCorrecta < 1 || opcionCorrecta > 4);

            PreguntaCerrada pregunta = new PreguntaSeleccionMultiple(enunciado, tituloPregunta, opciones[0], opciones[1], opciones[2], opciones[3], opcionCorrecta);
            preguntas.add(pregunta);
        }

        aplicacion.crearQuiz(titulo, descripcion, objetivos, dificultad, duracion, fechaLimite, profesor, calificacionMinima, preguntas);
        System.out.println("Quiz registrado exitosamente.");
    }
	
    private  void crearExamen(Profesor profesor) {

        System.out.print("Ingrese el título del examen: ");
        String titulo;
        do {
            titulo = scanner.nextLine().trim();
            if (titulo.isEmpty()) {
                System.out.println("El título del examen no puede estar vacío.");
                System.out.print("Ingrese el título del examen: ");
            }
        } while (titulo.isEmpty());

        System.out.print("Ingrese la descripción del examen: ");
        String descripcion;
        do {
            descripcion = scanner.nextLine().trim();
            if (descripcion.isEmpty()) {
                System.out.println("La descripción del examen no puede estar vacía.");
                System.out.print("Ingrese la descripción del examen: ");
            }
        } while (descripcion.isEmpty());

        System.out.print("Ingrese los objetivos del examen (separados por comas): ");
        List<String> objetivos = new ArrayList<>();
        String objetivosInput;
        do {
            objetivosInput = scanner.nextLine().trim();
            if (objetivosInput.isEmpty()) {
                System.out.println("Los objetivos no pueden estar vacíos.");
                System.out.print("Ingrese los objetivos del examen (separados por comas): ");
                continue;
            }
            for (String objetivo : objetivosInput.split(",")) {
                objetivos.add(objetivo.trim());
            }
            if (objetivos.isEmpty()) {
                System.out.println("Debe ingresar al menos un objetivo válido.");
            }
        } while (objetivos.isEmpty());

        String dificultad;
        int opcion = 0;
        
        do {
        	System.out.print("Ingrese el nivel de dificultad del examen ");
        	System.out.print("\n1. Principiante");
        	System.out.print("\n2. Intermedio");
        	System.out.print("\n3. Avanzado");
        	System.out.print("\nOpción escogida: ");
        	
        	if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine();  
                if (opcion < 1 || opcion > 3) {
                    System.out.println("Opción inválida. Debe ser un número entre 1 y 3.");
                }
            } else {
                System.out.println("Por favor, ingrese un número válido.");
                scanner.nextLine();  
            }
            
        } while (opcion<1 || opcion>3);
        
        if (opcion ==1) {
        	dificultad = Actividad.BAJO;
        	
        }
        
        else if(opcion == 2) {
        	dificultad = Actividad.MEDIO;
        }
        
        else {
        	dificultad = Actividad.ALTO;
        }
        

        System.out.print("Ingrese la duración en minutos del examen: ");
        int duracion;
        do {
            try {
                String duracionInput = scanner.nextLine().trim();
                if (duracionInput.isEmpty()) {
                    throw new NumberFormatException("Duración vacía.");
                }
                duracion = Integer.parseInt(duracionInput);
                if (duracion <= 0) {
                    System.out.println("La duración debe ser un número positivo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("La duración debe ser un número entero válido.");
                System.out.print("Ingrese la duración en minutos del examen: ");
                duracion = 0;  
            }
        } while (duracion <= 0);

        System.out.print("Ingrese la fecha límite del examen (formato: dd/MM/yyyy): ");
        Date fechaLimite = null;
        do {
            String fechaInput = scanner.nextLine().trim();
            if (fechaInput.isEmpty()) {
                System.out.println("La fecha límite no puede estar vacía.");
                System.out.print("Ingrese la fecha límite del examen (formato: dd/MM/yyyy): ");
                continue;
            }
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                fechaLimite = sdf.parse(fechaInput);
                if (fechaLimite.before(new Date())) {
                    System.out.println("La fecha límite no puede ser una fecha pasada.");
                }
            } catch (Exception e) {
                System.out.println("Formato de fecha incorrecto. Intente nuevamente.");
            }
        } while (fechaLimite == null || fechaLimite.before(new Date()));

        List<PreguntaAbierta> preguntas = new ArrayList<>();
        System.out.println("Ingrese las preguntas del examen en el formato 'título|enunciado': ");
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

    private  void crearEncuesta(Profesor profesor) {

        String titulo;
        do {
            System.out.print("Ingrese el título de la encuesta: ");
            titulo = scanner.nextLine().trim();
            if (titulo.isEmpty()) {
                System.out.println("El título de la encuesta no puede estar vacío.");
            }
        } while (titulo.isEmpty());

        String descripcion;
        do {
            System.out.print("Ingrese la descripción de la encuesta: ");
            descripcion = scanner.nextLine().trim();
            if (descripcion.isEmpty()) {
                System.out.println("La descripción de la encuesta no puede estar vacía.");
            }
        } while (descripcion.isEmpty());

        List<String> objetivos = new ArrayList<>();
        String objetivosInput;
        do {
            System.out.print("Ingrese los objetivos de la encuesta (separados por comas): ");
            objetivosInput = scanner.nextLine().trim();
            if (objetivosInput.isEmpty()) {
                System.out.println("Los objetivos no pueden estar vacíos.");
                continue;
            }
            for (String objetivo : objetivosInput.split(",")) {
                objetivos.add(objetivo.trim());
            }
            if (objetivos.isEmpty()) {
                System.out.println("Debe ingresar al menos un objetivo válido.");
            }
        } while (objetivos.isEmpty());

        String dificultad;
        int opcion = 0;
        
        do {
        	System.out.print("Ingrese el nivel de dificultad de la encuesta ");
        	System.out.print("\n1. Principiante");
        	System.out.print("\n2. Intermedio");
        	System.out.print("\n3. Avanzado");
        	System.out.print("\nOpción escogida: ");
        	
        	if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine();  
                if (opcion < 1 || opcion > 3) {
                    System.out.println("Opción inválida. Debe ser un número entre 1 y 3.");
                }
            } else {
                System.out.println("Por favor, ingrese un número válido.");
                scanner.nextLine();  
            }
            
        } while (opcion<1 || opcion>3);
        
        if (opcion ==1) {
        	dificultad = Actividad.BAJO;
        	
        }
        
        else if(opcion == 2) {
        	dificultad = Actividad.MEDIO;
        }
        
        else {
        	dificultad = Actividad.ALTO;
        }
        

        int duracion;
        do {
            System.out.print("Ingrese la duración en minutos de la encuesta: ");
            try {
                String duracionInput = scanner.nextLine().trim();
                if (duracionInput.isEmpty()) {
                    throw new NumberFormatException("Duración vacía.");
                }
                duracion = Integer.parseInt(duracionInput);
                if (duracion <= 0) {
                    System.out.println("La duración debe ser un número positivo.");
                    duracion = 0; 
                }
            } catch (NumberFormatException e) {
                System.out.println("La duración debe ser un número entero válido.");
                duracion = 0; 
            }
        } while (duracion <= 0);

        Date fechaLimite = null;
        do {
            System.out.print("Ingrese la fecha límite de la encuesta (formato: dd/MM/yyyy): ");
            String fechaInput = scanner.nextLine().trim();
            if (fechaInput.isEmpty()) {
                System.out.println("La fecha límite no puede estar vacía.");
                continue;
            }
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                fechaLimite = sdf.parse(fechaInput);
                if (fechaLimite.before(new Date())) {
                    System.out.println("La fecha límite no puede ser una fecha pasada.");
                    fechaLimite = null; 
                }
            } catch (Exception e) {
                System.out.println("Formato de fecha incorrecto. Intente nuevamente.");
            }
        } while (fechaLimite == null || fechaLimite.before(new Date()));

        List<PreguntaAbierta> preguntas = new ArrayList<>();
        System.out.println("Ingrese las preguntas de la encuesta en el formato 'título|enunciado': ");
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

	private  void crearLearningPath(Profesor profesor) {
	    
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

	    String dificultad;
        int opcion = 0;
        
        do {
        	System.out.print("Ingrese el nivel de dificultad del Learning Path ");
        	System.out.print("\n1. Principiante");
        	System.out.print("\n2. Intermedio");
        	System.out.print("\n3. Avanzado");
        	System.out.print("\nOpción escogida: ");
        	
        	if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine();  
                if (opcion < 1 || opcion > 3) {
                    System.out.println("Opción inválida. Debe ser un número entre 1 y 3.");
                }
            } else {
                System.out.println("Por favor, ingrese un número válido.");
                scanner.nextLine();  
            }
            
        } while (opcion<1 || opcion>3);
        
        if (opcion ==1) {
        	dificultad = LearningPath.BAJO;
        	
        }
        
        else if(opcion == 2) {
        	dificultad = LearningPath.MEDIO;
        }
        
        else {
        	dificultad = LearningPath.ALTO;
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
    
    private  void clonarActividad(Profesor profesor) {
        
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

    
    private  void crearPregunta(Profesor profesor) {
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
        	
	private  void clonarLearningPath(Profesor profesor) {

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
	    System.out.println("Learning Path clonado con éxito para el profesor: " + profesor.getNombre());
	}

	
	private  void modificarLearningPath(Profesor profesor) {
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

	
	private  void modificarActividad(Profesor profesor) {
	    String msjTitulo = "Ingrese el título de la actividad a modificar: ";
	    String msjTipo = "Ingrese el tipo de la actividad a modificar: ";
	    
	    Actividad actividad = getActividad(msjTitulo, "", msjTipo, true, profesor);
	    

	    if (actividad != null) {
	    	String tipo = actividad.getTipoActividad();
	    	
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
	    	System.out.println("Actividad no encontrada");
	    	return;
	    }
	    
	}
	
	private  void modificarActividadGeneral(Actividad actividad, int opcion) {
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
	
	private  void modificarPrevSegActividad(Actividad actividadPrincipal, int opcion) {
			
			String tipo;
			String msjTitulo;
			String msjProfesor;
			String msjTipo;
			String msjMismaAct;
			
			if (opcion == 7) {
				tipo = "Previa";
				msjTitulo = "Ingrese el título de la actividad a agregar/eliminar de las actividades previas: ";
				msjProfesor = "Ingrese el login del profesor creador de la actividad a agregar/eliminar de las actividades previas: ";
				msjTipo = "Ingrese el tipo de la actividad a agregar/eliminar de las actividades previas: ";
				msjMismaAct = "La actividad a agregar en actividades previas no puede ser la misma que la actividad principal";
			}
			else {
				tipo = "Seguimiento";
				msjTitulo = "Ingrese el título de la actividad a agregar/eliminar de las actividades de seguimiento: ";
				msjProfesor = "Ingrese el login del profesor creador de la actividad a agregar/eliminar de las actividades de seguimiento: ";
				msjTipo = "Ingrese el tipo de la actividad a agregar/eliminar de las actividades de seguimiento: ";
				msjMismaAct = "La actividad a agregar en actividades de seguimiento no puede ser la misma que la  actividad principal";
			}
			
			
		
			
		    
		
		    
		    Actividad actividadOperacion = getActividad(msjTitulo, msjProfesor, msjTipo, false, null);
		    if (actividadOperacion != null) {
		    	if (actividadPrincipal.equals(actividadOperacion)) {
		    		System.out.println(msjMismaAct);
		    		return;
		    	}
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
					
					catch (ModificarActividadesPreviasException | ModificarActividadesSeguimientoException | ActividadPreviaCiclicoException | ActividadSeguimientoCiclicoException e) {
						System.out.println("Error al modificar actividad: " + e.getMessage());
					}
					
				}	
			
		    }
		    
		    else {
		    	return;
		    }
	
	}

	private  void modificarPregunta (Profesor profesor) {
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
	
	private  void calificarActividad(Profesor profesor) {

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
	
	private  void revisarActividadRepetida(Profesor profesor) {
	    System.out.print("Ingrese el título de la actividad: ");
	    String titulo = scanner.nextLine();

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
	        aplicacion.revisarActividadRepetida(titulo, profesor.getLogin(), tipo);
	        System.out.println("La actividad no existe bajo su login, puede ser creada.");
	    } catch (ActividadYaExistenteException e) {
	        System.out.println(e.getMessage());
	    }
	}
	
	private  void revisarLearningPathRepetido(Profesor profesor) {
		System.out.print("Ingrese el título del Learning Path: ");
	    String titulo = scanner.nextLine();


	    try {
	        aplicacion.revisarLearningPathRepetido(titulo, profesor.getLogin());
	        System.out.println("El learning Path no existe, puede ser creado.");
	    } catch (LearningPathYaExistenteException e) {
	        System.out.println(e.getMessage());
	    }
		
	}

	
    private  void verActividades(Profesor profesor) {
        System.out.println("== Mis Actividades ==");
        profesor.getMapaRecursosPropios().forEach((id, recurso) -> System.out.println("Recurso: " + id));
        profesor.getMapaTareasPropias().forEach((id, tarea) -> System.out.println("Tarea: " + id));
        profesor.getMapaExamenesPropios().forEach((id, examen) -> System.out.println("Examen: " + id));
        profesor.getMapaQuicesPropios().forEach((id, quiz) -> System.out.println("Quiz: " + id));
        profesor.getMapaEncuestasPropias().forEach((id, encuesta) -> System.out.println("Encuesta: " + id));
        
    }
    
    private void verLearningPaths(Profesor profesor) {
    	
    	if (profesor.getLearningPathPropios().size()>0) {
    		System.out.println("\n--- Mis Learning Paths ---");
	    	int i = 1;
	    	for(LearningPath lp: profesor.getLearningPathPropios().values()) {
	    		System.out.println(String.format("%d. %s", i, lp.getTitulo()));
	    	}
    	}
    	else {
    		System.out.println("\nNo tiene Learning Paths propios");
    	}
    }
    
    
    private  void calificarResenarActividad() {
    	
    	
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
    
    private  void calificarLearningPath() {
    	String msjTitulo = "Indique el título del Learning Path que desea calificar: ";
    	String msjProfesor = "Indique el login del profesor creador del Learning Path que desea calificar";
    	
    	LearningPath learningPath = getLearningPath(msjTitulo, msjProfesor, false, null);
    	
    	if (learningPath != null) {
    		System.out.println("Ingrese la calificación que desea dejar al Learning Path: ");
			double calificacion = Double.parseDouble(scanner.nextLine());
			aplicacion.calificarLearningPath(learningPath, calificacion);
    	}
    }
    
    private  Actividad getActividad(String msjTitulo, String msjProfesor, String msjTipo, boolean actividadPropia, Profesor profesor) {
    	String titulo;
    	do {
	    	System.out.println(msjTitulo);
			titulo = scanner.nextLine();
			if (titulo.isEmpty()) {
				System.out.println("El título de la actividad no puede estar vacío, inténtelo de nuevo\n");
			}
    	}
    	while(titulo.isEmpty());
		
		String login;
		
    	if (actividadPropia == true) {
    		login = profesor.getLogin();
    	}
    	
    	else {
    		do {
	    		System.out.println(msjProfesor);
	    		login = scanner.nextLine();
	    		if (login.isEmpty()) {
					System.out.println("El profesor de la actividad no puede estar vacío, inténtelo de nuevo\n");
				}
    		}
    		while(login.isEmpty());
    	}
    	
    	
		String tipo;
		do {
			System.out.println(msjTipo);
			tipo = scanner.nextLine();
			if (tipo.isEmpty()) {
				System.out.println("El tipo de la actividad no puede estar vacío, inténtelo de nuevo\n");
			}
		}
		while(tipo.isEmpty());
		
    	
		String idActividad = aplicacion.generarLlaveLearningsActividades(titulo, login);
		
		Actividad actividad = aplicacion.getActividad(idActividad, tipo);
		
		return actividad;

    }
    
    private  LearningPath getLearningPath(String msjTitulo, String msjProfesor, boolean learningPathPropio, Profesor profesor) {
    	String titulo, login;
    	do {
	    	System.out.println(msjTitulo);
			titulo = scanner.nextLine();
			if(titulo.isEmpty()){
				System.out.println("El título del Learning Path no puede ser vacío, inténtelo de nuevo.");
			}
    	}
    	while(titulo.isEmpty());

    	if (learningPathPropio == true) {
    		login = profesor.getLogin();
    	}
    	
    	else {
    		do {
	    		System.out.println(msjProfesor);
	    		login = scanner.nextLine();
	    		if(login.isEmpty()){
					System.out.println("El profesor del Learning Path no puede ser vacío, inténtelo de nuevo.");
				}
    		}
    		while(login.isEmpty());
    	}
    	
    	
		String idLearningPath = aplicacion.generarLlaveLearningsActividades(titulo, login);
		
		LearningPath learningPath = aplicacion.getLearningPath(idLearningPath);
		
		return learningPath;
    }
    
    private void verProgresoLearningPathEstudiante() {
    	String login;
    	do {
	    	System.out.println("\nIngrese el login del estudiante para el cual quiere ver el progreso: ");
	    	login = scanner.nextLine();
	    	if (login.isEmpty()) {
	    		System.out.println("El login del estudiante no puede estar vacío. Intende de nuevo.");
	    	}
    	}
    	while(login.isEmpty());
    	
    	Estudiante estudiante = aplicacion.getEstudiante(login);
    	
    	if (estudiante!= null) {
    		String msjTitulo = "Ingrese el título del Learning Path para el cual quiere ver el progreso del estudiante: ";
    		String msjProfesor = "Ingrese el login del profesor creador del Learning Path: ";
    		
    		LearningPath learningPath = getLearningPath(msjTitulo, msjProfesor, false, null);
    		
    		if (learningPath != null) {
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
    	    			 
    	    			 //hola
    	    			 
    	    			 
    	    			 if (tipo.equals("Quiz")|| tipo.equals("Examen"))
    	    			 
    	    			 {
    	    				 if (tipo.equals("Quiz")) {
    	    					 SeguimientoQuiz segQuiz = (SeguimientoQuiz) actividad;
    	        				 System.out.println(String.format("Calificación: %.2f", segQuiz.getNota()));
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
    		else {
    			System.out.println("Learning Path no encontrado");
    		}
    	}
    	
    	else {
    		System.out.println("Estudiante no encontrado");
    	}
    	
    }

}

