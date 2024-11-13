package Consolas;

import user.Estudiante;
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
            System.out.println("3. Cerrar sesión");
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
		System.out.print("Ingrese el login que desea: ");
        String login = scanner.nextLine();
        System.out.print("Ingrese el password que desea: ");
        String password = scanner.nextLine();
        System.out.print("Ingrese su nombre completo: ");
        String nombre = scanner.nextLine();
        aplicacion.crearProfesor(login, password, nombre);
		
	}

	private static void registroEstudiante() {
		System.out.print("Ingrese el login que desea: ");
        String login = scanner.nextLine();
        System.out.print("Ingrese el password que desea: ");
        String password = scanner.nextLine();
        System.out.print("Ingrese su nombre completo: ");
        String nombre = scanner.nextLine();
        aplicacion.crearEstudiante(login, password, nombre);
        	
	}


}