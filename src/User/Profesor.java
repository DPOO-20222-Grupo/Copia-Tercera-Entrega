package User;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Actividades.Actividad;
import Actividades.Encuesta;
import Actividades.Examen;
import Actividades.Quiz;
import Actividades.RevisarRecurso;
import Actividades.Tarea;
import LearningPath.LearningPath;
import Preguntas.PreguntaAbierta;
import Preguntas.PreguntaSeleccionMultiple;
import exceptions.ModificarActividadesLearningPathException;

public class Profesor extends Usuario {
    private static String TIPO = "Profesor";
	private String nombre;
	private Map <String, RevisarRecurso> mapaRecursosPropios;
	private Map <String, Tarea> mapaTareasPropias;
	private Map <String, Examen> mapaExamenesPropios;
	private Map <String, Encuesta> mapaEncuestasPropias;
	private Map <String, Quiz> mapaQuicesPropios;
	private Map <String, LearningPath> learningPathPropios;
	private Map <String, PreguntaAbierta> preguntasAbiertasPropias;
	private Map <String, PreguntaSeleccionMultiple> preguntasSeleccionPropias;
	
	
    // Constructor
    public Profesor(String login, String password, String nombre) {
        super(login, password);
        this.nombre = nombre;
        this.mapaEncuestasPropias = new HashMap<String, Encuesta>();
        this.mapaTareasPropias = new HashMap<String, Tarea>();
        this.mapaExamenesPropios = new HashMap<String, Examen>();
        this.mapaQuicesPropios = new HashMap<String, Quiz>();
        this.mapaRecursosPropios = new HashMap<String, RevisarRecurso>();
        this.learningPathPropios = new HashMap<String, LearningPath>();
        this.preguntasSeleccionPropias = new HashMap <String, PreguntaSeleccionMultiple>();
        this.preguntasAbiertasPropias = new HashMap < String, PreguntaAbierta>();
        
        
    }
    
    // Getters y Setters
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    

    // Métodos adicionales

    public void registrarLearningPath (LearningPath learningPath) {
    	
    	String llave = learningPath.getIdLearnginPath();
    	this.getLearningPathPropios().put(llave, learningPath);
    	
    }
    
    public void registrarActividad(Actividad actividad) {
    	String llave = actividad.getIdActividad();
		
		String tipoActividad = actividad.getTipoActividad();
		
		if (tipoActividad.equals("Encuesta")) {
			mapaEncuestasPropias.put(llave, (Encuesta) actividad);
			
		}
		
		else if (tipoActividad.equals("Tarea")) {
			mapaTareasPropias.put(llave, (Tarea) actividad);
			
		}
		
		else if (tipoActividad.equals("Quiz")) {
			mapaQuicesPropios.put(llave, (Quiz) actividad);
			
		}
		
		else if (tipoActividad.equals("Examen")) {
			mapaExamenesPropios.put(llave, (Examen) actividad);
			
		}
		
		else if (tipoActividad.equals("Recurso")) {
			mapaRecursosPropios.put(llave, (RevisarRecurso) actividad);
			
		}
    	
    	
    }
    
    

	public Map<String, RevisarRecurso> getMapaRecursosPropios() {
		return mapaRecursosPropios;
	}

	public Map<String, Tarea> getMapaTareasPropias() {
		return mapaTareasPropias;
	}

	public Map<String, Examen> getMapaExamenesPropios() {
		return mapaExamenesPropios;
	}

	public Map<String, Encuesta> getMapaEncuestasPropias() {
		return mapaEncuestasPropias;
	}

	public Map<String, Quiz> getMapaQuicesPropios() {
		return mapaQuicesPropios;
	}

	public Map<String, LearningPath> getLearningPathPropios() {
		return learningPathPropios;
	}

	public Map<String, PreguntaAbierta> getPreguntasAbiertasPropias() {
		return preguntasAbiertasPropias;
	}

	public Map<String, PreguntaSeleccionMultiple> getPreguntasSeleccionPropias() {
		return preguntasSeleccionPropias;
	}

}
