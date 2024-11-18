package Consolas;

import java.util.Scanner;
import interfaz.Aplicacion; 


public class RegistroConsole {
	   
    private static Scanner scanner = new Scanner(System.in);
    private static Aplicacion aplicacion = new Aplicacion("usuarios.json", "lp.json", "preguntas.json", "actividades.json");  

    public static void main(String[] args) {
        
        System.out.println("== Sistema de Registro ==");
        mostrarMenuRegistro();
        
    }

    private static void mostrarMenuRegistro() {
        int opcion;
        do {
            System.out.println("\n== Menú Registro ==");
            System.out.println("1. Inscribirse como profesor");
            System.out.println("2. Inscribirse como estudiante");
            System.out.println("3. Cerrar registro");
            System.out.print("Seleccione una opción: ");
            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    registroProfesor();
                    break;
                case 2:
                    registroEstudiante();
                    break;
                case 3:
                    System.out.println("Sesión cerrada.");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 3);
    }

    private static void registroProfesor() {
        String login;
        do {
            System.out.print("Ingrese el login que desea: ");
            login = scanner.nextLine().trim();
            if (login.isEmpty()) {
                System.out.println("Error: El login no puede estar vacío.");
            }
        } while (login.isEmpty());

        String password;
        do {
            System.out.print("Ingrese el password que desea: ");
            password = scanner.nextLine().trim();
            if (password.isEmpty()) {
                System.out.println("Error: El password no puede estar vacío.");
            }
        } while (password.isEmpty());

        String nombre;
        do {
            System.out.print("Ingrese su nombre completo: ");
            nombre = scanner.nextLine().trim();
            if (nombre.isEmpty()) {
                System.out.println("Error: El nombre no puede estar vacío.");
            }
        } while (nombre.isEmpty());

        aplicacion.crearProfesor(login, password, nombre);
        aplicacion.descargarDatos();
        ProfesorConsole.mostrarMenuProfesor(aplicacion.getMapaProfesores().get(login));
    }

    private static void registroEstudiante() {
        String login;
        do {
            System.out.print("Ingrese el login que desea: ");
            login = scanner.nextLine().trim();
            if (login.isEmpty()) {
                System.out.println("Error: El login no puede estar vacío.");
            }
        } while (login.isEmpty());

        String password;
        do {
            System.out.print("Ingrese el password que desea: ");
            password = scanner.nextLine().trim();
            if (password.isEmpty()) {
                System.out.println("Error: El password no puede estar vacío.");
            }
        } while (password.isEmpty());

        String nombre;
        do {
            System.out.print("Ingrese su nombre completo: ");
            nombre = scanner.nextLine().trim();
            if (nombre.isEmpty()) {
                System.out.println("Error: El nombre no puede estar vacío.");
            }
        } while (nombre.isEmpty());

        aplicacion.crearEstudiante(login, password, nombre);
        aplicacion.descargarDatos();
        EstudianteConsole.mostrarMenuEstudiante(aplicacion.getMapaEstudiantes().get(login));
    }
}
