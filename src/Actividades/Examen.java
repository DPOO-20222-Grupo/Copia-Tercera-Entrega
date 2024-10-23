package Actividades;

import java.util.Date;
import java.util.List;

import Preguntas.PreguntaAbierta;
import User.Profesor;
import exceptions.ModificarPreguntasAbiertasException;


public class Examen extends Actividad {
	private static String TIPO = "Examen";
    private int numPreguntas;
    private List<PreguntaAbierta> preguntas;

    // Constructor
    public Examen(String titulo, String descripcion, List<String> objetivos, String nivelDificultad, 
    		int duracionMinutos, Date fechaLimite, Profesor profesorCreador, List<PreguntaAbierta> preguntas) 
    {
        super(titulo, descripcion, objetivos, nivelDificultad, duracionMinutos, fechaLimite, profesorCreador);
        this.preguntas = preguntas;
        this.numPreguntas = preguntas.size();
    }

    public int getNumPreguntas() {
        return numPreguntas;
    }

    public void setNumPreguntas(int numPreguntas) {
        this.numPreguntas = numPreguntas;
    }
    
    @Override
    public String getTipoActividad () {
    	return TIPO;
    }

	public List<PreguntaAbierta> getPreguntas() {
		return preguntas;
	}
	
	public void agregarPregunta (PreguntaAbierta pregunta) throws ModificarPreguntasAbiertasException {
		
		if (this.getPreguntas().contains(pregunta)) {
			
			throw new ModificarPreguntasAbiertasException(pregunta, "Agregar");
			
		}
		
		else {
			this.getPreguntas().add(pregunta);
		}
		
	}
	
	public void eliminarPregunta (PreguntaAbierta pregunta) throws ModificarPreguntasAbiertasException {
		
		if (this.getPreguntas().contains(pregunta) == false) {
			
			throw new ModificarPreguntasAbiertasException(pregunta, "Eliminar");
			
		}
		
		else {
			this.getPreguntas().remove(pregunta);
		}
		
	}
    
    
}