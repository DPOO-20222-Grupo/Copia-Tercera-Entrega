package User;

import Actividades.Actividad;
import LearningPath.LearningPath;

public class Estudiante extends Usuario {
    private static String tipo = "Estudiante";
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

    // Métodos adicionales 
    
    public void inscribirEstudiante(LearningPath path) {
        path.inscribirEstudiante(this);
    }

    public void completarActividad(Actividad actividad) {
        actividad.completar();
    }

    public void dejarReseña(Actividad actividad, String reseña, float rating) {
        actividad.dejarFeedback(reseña, rating);
    }

	public static String getTipo() {
		return tipo;
	}
}
