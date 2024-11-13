package Consolas;

import java.util.Scanner;  

import interfaz.Aplicacion;
import learningPath.LearningPath;
import seguimientoEstudiantes.SeguimientoLearningPath;
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
            System.out.println("2. Ver Learning Paths Inscritos");
            System.out.println("3. Cerrar sesión");
            System.out.print("Seleccione una opción: ");
            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    inscribirLearningPath(estudiante);
                    break;
                case 2:
                    verLearningPaths(estudiante);
                    break;
                case 3:
                    estudiante.logout();
                    System.out.println("Sesión cerrada.");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (estudiante.isLoggedIn());
    }

    private static void inscribirLearningPath(Estudiante estudiante) {
        System.out.print("Ingrese el ID del Learning Path a inscribir: ");
        String idLearningPath = scanner.nextLine();
        
        LearningPath lp = new LearningPath(idLearningPath, idLearningPath, null, idLearningPath, null, null, null); // Suponiendo constructor sencillo
        SeguimientoLearningPath seguimiento = new SeguimientoLearningPath(lp, estudiante);
        estudiante.agregarSeguimientoLearningPath(seguimiento);
        System.out.println("Inscripción al Learning Path realizada con éxito.");
    }

    private static void verLearningPaths(Estudiante estudiante) {
        System.out.println("== Learning Paths Inscritos ==");
        estudiante.getLearningPathsInscritos().forEach((id, seguimiento) -> {
            System.out.println("Learning Path: " + id);
        });
    }

}
