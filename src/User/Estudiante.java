package User;

import Actividades.Actividad;
import LearningPath.LearningPath;

public class Estudiante extends Usuario {
    private static String TIPO = "Estudiante";
	private String nombre;

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

	public String getTipo() {
		return TIPO;
	}
}
