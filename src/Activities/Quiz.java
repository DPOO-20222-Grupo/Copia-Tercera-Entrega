package Activities;

import java.util.Date;

public class Quiz extends Actividad {
    private String calificacionMinima;

    // Constructor
    public Quiz(String titulo, String descripcion, String objetivos, String nivelDificultad, int duracionMinutos, Date fechaLimite, boolean esObligatoria, String calificacionMinima) {
        super(titulo, descripcion, objetivos, nivelDificultad, duracionMinutos, fechaLimite, esObligatoria);
        this.calificacionMinima = calificacionMinima;
    }

    public String getCalificacionMinima() {
        return calificacionMinima;
    }

    public void setCalificacionMinima(String calificacionMinima) {
        this.calificacionMinima = calificacionMinima;
    }
}