package Activities;

import java.util.Date;


public class Examen extends Actividad {
    private String estado;
    private int numPreguntas;

    // Constructor
    public Examen(String titulo, String descripcion, String objetivos, String nivelDificultad, int duracionMinutos, Date fechaLimite, boolean esObligatoria, String estado, int numPreguntas) {
        super(titulo, descripcion, objetivos, nivelDificultad, duracionMinutos, fechaLimite, esObligatoria);
        this.estado = estado;
        this.numPreguntas = numPreguntas;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getNumPreguntas() {
        return numPreguntas;
    }

    public void setNumPreguntas(int numPreguntas) {
        this.numPreguntas = numPreguntas;
    }
}