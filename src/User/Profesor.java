package User;

import java.util.Date;  

import Activities.Actividad;
import Activities.Examen;
import Activities.Tarea;
import LearningPath.LearningPath;

public class Profesor extends Usuario {

    // Constructor
    public Profesor(String login, String password) {
        super(login, password);
    }

    // Métodos adicionales

    public LearningPath crearLearningPath(String titulo, String descripcion, String objetivos, String nivelDificultad, int duracionMinutos, Date fechaCreacion) {
        return new LearningPath(titulo, descripcion, objetivos, nivelDificultad, duracionMinutos, fechaCreacion);
    }

    public void editarLearningPath(LearningPath path, String nuevoTitulo, String nuevaDescripcion, String nuevosObjetivos, String nuevoNivelDificultad, int nuevaDuracionMinutos, Date nuevaFechaCreacion) {
        path.setTitulo(nuevoTitulo);
        path.setDescripcion(nuevaDescripcion);
        path.setObjetivos(nuevosObjetivos);
        path.setNivelDificultad(nuevoNivelDificultad);
        path.setDuracionMinutos(nuevaDuracionMinutos);
        path.setFechaCreacion(nuevaFechaCreacion);
    }

    public LearningPath copiarLearningPath(LearningPath path) {
        return new LearningPath(
            path.getTitulo(), 
            path.getDescripcion(), 
            path.getObjetivos(), 
            path.getNivelDificultad(), 
            path.getDuracionMinutos(), 
            path.getFechaCreacion()
        );
    }

    public void agregarActividad(LearningPath path, Actividad actividad) {
        path.agregarActividad(actividad);
    }

    public void calificarTarea(Tarea tarea, String resultado) {
        tarea.setResultado(resultado);
    }

    public void calificarExamen(Examen examen, String resultado) {
        examen.setResultado(resultado);
    }
}
