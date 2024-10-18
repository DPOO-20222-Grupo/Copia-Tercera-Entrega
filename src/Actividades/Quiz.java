package Actividades;

import java.util.Date;

import User.Profesor;

public class Quiz extends Actividad {
	private static String TIPO = "Quiz";
    private String calificacionMinima;

    // Constructor
    public Quiz(String titulo, String descripcion, String objetivos, String nivelDificultad, int duracionMinutos, Date fechaLimite, boolean esObligatoria, String calificacionMinima, Profesor profesorCreador) {
        super(titulo, descripcion, objetivos, nivelDificultad, duracionMinutos, fechaLimite, profesorCreador, esObligatoria);
        this.calificacionMinima = calificacionMinima;
    }

    public String getCalificacionMinima() {
        return calificacionMinima;
    }

    public void setCalificacionMinima(String calificacionMinima) {
        this.calificacionMinima = calificacionMinima;
    }
    
    public String getTipoActividad() {
    	return TIPO;
    }
}
