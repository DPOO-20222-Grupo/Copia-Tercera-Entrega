package Consolas;

import java.util.Scanner;

import exceptions.UsuarioYaExistenteException;
import interfaz.Aplicacion; 


public class ConsolaPrincipal {
	   
    private static Scanner scanner = new Scanner(System.in);
    private static Aplicacion aplicacion = new Aplicacion("usuarios.json", "lp.json", "preguntas.json", "actividades.json");  

    public static void main(String[] args) {
        
        
        mostrarMenuRegistro();
        
    }

    private static void mostrarMenuRegistro() {
        int opcion= 0;
        do {
        	System.out.println("\n== Menú Principal ==");
            System.out.println("1. Inscribirse como profesor");
            System.out.println("2. Inscribirse como estudiante");
            System.out.println("3. Iniciar sesión como profesor");
            System.out.println("4. Iniciar sesión como estudiante");
            System.out.println("5. Cerrar registro");
            System.out.print("Seleccione una opción: ");
            try {
            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    registroProfesor();
                    aplicacion.descargarDatos();
                    break;
                case 2:
                    registroEstudiante();
                    aplicacion.descargarDatos();
                    break;
                case 3:
                    loginProfesor();
                    aplicacion.descargarDatos();
                    break;
                case 4:
                    loginEstudiante();
                    aplicacion.descargarDatos();
                    break;
                
                case 5:
                    System.out.println("Sesión cerrada.");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
            }
            catch(NumberFormatException e) {
            	System.out.println("Opción no válida.");
            }
        } while (opcion != 5);
    }

    private static void registroProfesor() {
        String login;
        do {
            System.out.print("\nIngrese el login que desea: ");
            login = scanner.nextLine().trim();
            if (login.isEmpty()) {
                System.out.println("Error: El login no puede estar vacío.");
            }
        } while (login.isEmpty());

        String password;
        do {
            System.out.print("\nIngrese el password que desea: ");
            password = scanner.nextLine().trim();
            if (password.isEmpty()) {
                System.out.println("Error: El password no puede estar vacío.");
            }
        } while (password.isEmpty());

        String nombre;
        do {
            System.out.print("\nIngrese su nombre completo: ");
            nombre = scanner.nextLine().trim();
            if (nombre.isEmpty()) {
                System.out.println("Error: El nombre no puede estar vacío.");
            }
        } while (nombre.isEmpty());
        try {
        	aplicacion.revisarUsuarioRepetido(login, "Profesor");
	        aplicacion.crearProfesor(login, password, nombre);
	        System.out.println("Registro Exitoso");
        }
        catch (UsuarioYaExistenteException e) {
        	System.out.println(e.getMessage());
        }
        
    }

    private static void registroEstudiante() {
        String login;
        do {
            System.out.print("\nIngrese el login que desea: ");
            login = scanner.nextLine().trim();
            if (login.isEmpty()) {
                System.out.println("Error: El login no puede estar vacío.");
            }
        } while (login.isEmpty());

        String password;
        do {
            System.out.print("\nIngrese el password que desea: ");
            password = scanner.nextLine().trim();
            if (password.isEmpty()) {
                System.out.println("Error: El password no puede estar vacío.");
            }
        } while (password.isEmpty());

        String nombre;
        do {
            System.out.print("\nIngrese su nombre completo: ");
            nombre = scanner.nextLine().trim();
            if (nombre.isEmpty()) {
                System.out.println("Error: El nombre no puede estar vacío.");
            }
        } while (nombre.isEmpty());
        try {
        	aplicacion.revisarUsuarioRepetido(login, "Estudiante");
	        aplicacion.crearEstudiante(login, password, nombre);
	        System.out.println("Registro Exitoso");
        }
        catch (UsuarioYaExistenteException e) {
        	System.out.println(e.getMessage());
        }
        
    }
    
    private static void loginProfesor() {
    	ProfesorConsole consola = new ProfesorConsole(aplicacion);
    	consola.loginPlataforma();
    }
    
    private static void loginEstudiante() {
    	
    	EstudianteConsole consola = new EstudianteConsole(aplicacion);
    	consola.loginPlataforma();
    }
}
