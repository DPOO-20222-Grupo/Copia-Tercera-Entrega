package Actividades;

import java.util.Date;


public class Encuesta extends Actividad {
    private int numPreguntas;

    // Constructor
    public Encuesta(String titulo, String descripcion, String objetivos, String nivelDificultad, int duracionMinutos, Date fechaLimite, boolean esObligatoria, int numPreguntas) {
        super(titulo, descripcion, objetivos, nivelDificultad, duracionMinutos, fechaLimite, esObligatoria);
        this.numPreguntas = numPreguntas;
    }

    public int getNumPreguntas() {
        return numPreguntas;
    }

    public void setNumPreguntas(int numPreguntas) {
        this.numPreguntas = numPreguntas;
    }
}