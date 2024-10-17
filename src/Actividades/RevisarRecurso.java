package Actividades;

import java.util.Date;

public class RevisarRecurso extends Actividad {
    private String tipoRecurso;

    // Constructor
    public RevisarRecurso(String titulo, String descripcion, String objetivos, String nivelDificultad, int duracionMinutos, Date fechaLimite, boolean esObligatoria, String tipoRecurso) {
        super(titulo, descripcion, objetivos, nivelDificultad, duracionMinutos, fechaLimite, esObligatoria);
        this.tipoRecurso = tipoRecurso;
    }

    public String getTipoRecurso() {
        return tipoRecurso;
    }

    public void setTipoRecurso(String tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
    }
}