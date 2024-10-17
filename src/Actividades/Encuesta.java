package Actividades;

import java.util.Date;

import User.Profesor;


public class Encuesta extends Actividad {
	private static String TIPO = "Encuesta";
    private int numPreguntas;

    // Constructor
    public Encuesta(String titulo, String descripcion, String objetivos, String nivelDificultad, int duracionMinutos, Date fechaLimite, boolean esObligatoria, int numPreguntas, Profesor profesorCreador) {
        super(titulo, descripcion, objetivos, nivelDificultad, duracionMinutos, fechaLimite, profesorCreador, esObligatoria);
        this.numPreguntas = numPreguntas;
    }

    public int getNumPreguntas() {
        return numPreguntas;
    }

    public void setNumPreguntas(int numPreguntas) {
        this.numPreguntas = numPreguntas;
    }

	public String getTipoActividad() {
		return TIPO;
	}
    
    
}