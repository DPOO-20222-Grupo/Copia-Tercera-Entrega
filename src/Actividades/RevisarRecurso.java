package Actividades;

import java.util.Date;

import User.Profesor;

public class RevisarRecurso extends Actividad {
	private static String TIPO = "Recurso";
    private String tipoRecurso;

    // Constructor
    public RevisarRecurso(String titulo, String descripcion, String objetivos, String nivelDificultad, int duracionMinutos, Date fechaLimite, boolean esObligatoria, String tipoRecurso, Profesor profesorCreador) {
        super(titulo, descripcion, objetivos, nivelDificultad, duracionMinutos, fechaLimite, profesorCreador, esObligatoria);
        this.tipoRecurso = tipoRecurso;
    }

    public String getTipoRecurso() {
        return tipoRecurso;
    }

    public void setTipoRecurso(String tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
    }
    
    public String getTipoActividad() {
    	return TIPO;
    }
}