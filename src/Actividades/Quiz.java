package Actividades;

import java.util.Date;
import java.util.List;

import Preguntas.PreguntaSeleccionMultiple;
import User.Profesor;
import exceptions.ModificarPreguntasQuizException;

public class Quiz extends Actividad {
	private static String TIPO = "Quiz";
    private float calificacionMinima;
    private List<PreguntaSeleccionMultiple> preguntas;
    private int numPreguntas;

    // Constructor
    public Quiz(String titulo, String descripcion, List<String> objetivos, String nivelDificultad, int duracionMinutos,
    		Date fechaLimite, Profesor profesorCreador, float calificacionMinima, List<PreguntaSeleccionMultiple> preguntas) 
    
    {
        super(titulo, descripcion, objetivos, nivelDificultad, duracionMinutos, fechaLimite, profesorCreador);
        this.calificacionMinima = calificacionMinima;
        this.preguntas = preguntas;
        this.numPreguntas = preguntas.size();
    }
    
    
    
    
    

    public int getNumPreguntas() {
		return numPreguntas;
	}

	public float getCalificacionMinima() {
        return calificacionMinima;
    }

    public void setCalificacionMinima(float calificacionMinima) {
        this.calificacionMinima = calificacionMinima;
    }
    
    @Override
    public String getTipoActividad() {
    	return TIPO;
    }

	public List<PreguntaSeleccionMultiple> getPreguntas() {
		return preguntas;
	}
	
	public void agregarPregunta(PreguntaSeleccionMultiple pregunta) throws ModificarPreguntasQuizException {
		
		if (preguntas.contains(pregunta)) {
			
			throw new ModificarPreguntasQuizException (pregunta, "Agregar");
			
		}
		
		else {
			preguntas.add(pregunta);
		}
		
	}
	
	public void eliminarPregunta(PreguntaSeleccionMultiple pregunta) throws ModificarPreguntasQuizException {
		
		if (!preguntas.contains(pregunta)) {
			
			throw new ModificarPreguntasQuizException (pregunta, "Eliminar");
			
		}
		
		else {
			preguntas.remove(pregunta);
		}
		
	}
    
    
    
}






