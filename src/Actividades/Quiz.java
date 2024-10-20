package Actividades;

import java.util.Date;
import java.util.List;

import Preguntas.PreguntaSeleccionMultiple;
import User.Profesor;

public class Quiz extends Actividad {
	private static String TIPO = "Quiz";
    private float calificacionMinima;
    private List<PreguntaSeleccionMultiple> preguntas;

    // Constructor
    public Quiz(String titulo, String descripcion, List<String> objetivos, String nivelDificultad, int duracionMinutos,
    		Date fechaLimite, Profesor profesorCreador, float calificacionMinima, List<PreguntaSeleccionMultiple> preguntas) 
    
    {
        super(titulo, descripcion, objetivos, nivelDificultad, duracionMinutos, fechaLimite, profesorCreador);
        this.calificacionMinima = calificacionMinima;
        this.preguntas = preguntas;
    }

    public float getCalificacionMinima() {
        return calificacionMinima;
    }

    public void setCalificacionMinima(float calificacionMinima) {
        this.calificacionMinima = calificacionMinima;
    }
    
    public String getTipoActividad() {
    	return TIPO;
    }

	public List<PreguntaSeleccionMultiple> getPreguntas() {
		return preguntas;
	}
	
	public void agregarPregunta(PreguntaSeleccionMultiple pregunta) {
		
		if (preguntas.contains(pregunta)) {
			
		}
		
	}
    
    
    
}






