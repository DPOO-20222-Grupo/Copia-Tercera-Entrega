package User;

import java.util.Map;

import Actividades.Actividad;
import LearningPath.LearningPath;
import SeguimientoEstudiantes.SeguimientoLearningPath;

public class Estudiante extends Usuario {
    private static String TIPO = "Estudiante";
	private String nombre;
	private Map<String, SeguimientoLearningPath> learningPathsInscritos;

    // Constructor
    public Estudiante(String login, String password, String nombre) {
        super(login, password);
        this.nombre = nombre;
    }
    
    // Getter and Setter
    
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Métodos adicionales 

	public String getTipo() {
		return TIPO;
	}
}
