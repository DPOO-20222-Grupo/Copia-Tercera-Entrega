package User;


import Activities.Actividad; 
import LearningPath.LearningPath;

public class Estudiante extends Usuario {

    // Constructor
    public Estudiante(String login, String password) {
        super(login, password);
    }

    // Métodos adicionales 
    
    public void inscribirseEnLearningPath(LearningPath path) {
        path.inscribirAlumno(this);
    }

    public void completarActividad(Actividad actividad) {
        actividad.completar();
    }

    public void dejarReseña(Actividad actividad, String reseña, float rating) {
        actividad.dejarFeedback(reseña, rating);
    }
}