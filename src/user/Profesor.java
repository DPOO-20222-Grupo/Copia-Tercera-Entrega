package user;

import java.util.HashMap;
import java.util.Map;

import actividades.Actividad;
import actividades.Encuesta;
import actividades.Examen;
import actividades.Quiz;
import actividades.RevisarRecurso;
import actividades.Tarea;
import learningPath.LearningPath;
import preguntas.Pregunta;
import preguntas.PreguntaAbierta;
import preguntas.PreguntaBoolean;
import preguntas.PreguntaSeleccionMultiple;

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
	private Map <String, PreguntaBoolean> preguntasBooleanPropias;
	
	
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
        this.preguntasBooleanPropias = new HashMap <String, PreguntaBoolean>();
        
        
    }
    
    // Getters y Setters
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getTipo(){
    	return this.TIPO;
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
    
    public void registrarPregunta(Pregunta pregunta) {
    	String llave = pregunta.getIdPregunta();
    	
    	String tipoPregunta = pregunta.getTipo();
    	
    	if (tipoPregunta.equals("Cerrada")) {
    		preguntasSeleccionPropias.put(llave, (PreguntaSeleccionMultiple) pregunta);
    	}
    	else if(tipoPregunta.equals("Abierta")) {
    		preguntasAbiertasPropias.put(llave, (PreguntaAbierta) pregunta);   		
    	} 	
    	else {
    		preguntasBooleanPropias.put(llave, (PreguntaBoolean) pregunta);
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

	public Map<String, PreguntaBoolean> getPreguntasBooleanPropias() {
		return preguntasBooleanPropias;
	}
	
	

}
