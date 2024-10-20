package User;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Actividades.Actividad;
import Actividades.Examen;
import Actividades.Tarea;
import LearningPath.LearningPath;
import Preguntas.PreguntaAbierta;
import exceptions.ModificarActividadesLearningPathException;

public class Profesor extends Usuario {
    private static String tipo = "Profesor";
	private String nombre;
	private Map <String, Actividad> actividadesPropias;
	private Map<String, LearningPath> learningPathPropios;
	
	
    // Constructor
    public Profesor(String login, String password, String nombre) {
        super(login, password);
        this.nombre = nombre;
        this.actividadesPropias = new HashMap<String, Actividad>();
        this.learningPathPropios = new HashMap<String, LearningPath>();
    }
    
    // Getters y Setters
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    

    // Métodos adicionales

    public LearningPath crearLearningPath(String titulo, String descripcion, List<String> objetivos, 
    		String nivelDificultad, List<Actividad> actividades, Map<String, Boolean> obligatoriedadActividades) {
        return new LearningPath(titulo, descripcion, objetivos, nivelDificultad, this, actividades, obligatoriedadActividades);
        
    }
    
    // Método para establecer la respuesta correcta de una pregunta
    public void establecerRespuestaCorrecta(PreguntaAbierta pregunta, String respuesta) {
        pregunta.setRespuestaCorrecta(respuesta);
    }

    // Método para evaluar la respuesta de un estudiante
    public void evaluarRespuesta(PreguntaAbierta pregunta, boolean esCorrecta) {
        pregunta.setEsRespuestaCorrecta(esCorrecta);
    }

    public void agregarActividad(LearningPath path, Actividad actividad, boolean obligatoriedad) throws ModificarActividadesLearningPathException{
        path.agregarActividad(actividad, obligatoriedad);
    }

    public void calificarTarea(Tarea tarea, String resultado) {
        tarea.setResultado(resultado);
    }

    public void calificarExamen(Examen examen, String resultado) {
        examen.setResultado(resultado);
    }

	public String getTipo() {
		return tipo;
	}

}
