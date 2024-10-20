package User;

import java.util.Date;
import java.util.List;

import Actividades.Actividad;
import Actividades.Examen;
import Actividades.Tarea;
import LearningPath.LearningPath;
import Preguntas.PreguntaAbierta;

public class Profesor extends Usuario {
    private static String TIPO = "Profesor";
	private String nombre;
	
    // Constructor
    public Profesor(String login, String password, String nombre) {
        super(login, password);
        this.nombre = nombre;
    }
    
    // Getters y Setters
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    

    // Métodos adicionales

    public LearningPath crearLearningPath(String titulo, String descripcion, String objetivos, String nivelDificultad, int duracionMinutos, Date fechaCreacion, List<Estudiante> estudiantesInscritos, List<Actividad> actividades) {
        return new LearningPath(titulo, descripcion, objetivos, nivelDificultad, duracionMinutos, fechaCreacion, estudiantesInscritos, actividades);
    }

    public void editarLearningPath(LearningPath path, String nuevoTitulo, String nuevaDescripcion, String nuevosObjetivos, String nuevoNivelDificultad, int nuevaDuracionMinutos, Date nuevaFechaCreacion) {
        path.setTitulo(nuevoTitulo);
        path.setDescripcion(nuevaDescripcion);
        path.setObjetivos(nuevosObjetivos);
        path.setNivelDificultad(nuevoNivelDificultad);
        path.setDuracionMinutos(nuevaDuracionMinutos);
        path.setFechaCreacion(nuevaFechaCreacion);
        
    }

    public LearningPath copiarLearningPath(LearningPath path) {
        return new LearningPath(
            path.getTitulo(), 
            path.getDescripcion(), 
            path.getObjetivos(), 
            path.getNivelDificultad(), 
            path.getDuracionMinutos(), 
            path.getFechaCreacion(), 
            path.getEstudiantesInscritos(),
            path.getActividades()
        );
    }
    
    // Método para establecer la respuesta correcta de una pregunta
    public void establecerRespuestaCorrecta(PreguntaAbierta pregunta, String respuesta) {
        pregunta.setRespuestaCorrecta(respuesta);
    }

    // Método para evaluar la respuesta de un estudiante
    public void evaluarRespuesta(PreguntaAbierta pregunta, boolean esCorrecta) {
        pregunta.setEsRespuestaCorrecta(esCorrecta);
    }

    public void agregarActividad(LearningPath path, Actividad actividad) {
        path.agregarActividad(actividad);
    }

    public void calificarTarea(Tarea tarea, String resultado) {
        tarea.setResultado(resultado);
    }

    public void calificarExamen(Examen examen, String resultado) {
        examen.setResultado(resultado);
    }

	public static String getTipo() {
		return TIPO;
	}

}
