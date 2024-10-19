package LearningPath;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Actividades.Actividad;
import User.Estudiante;
import User.Profesor;

public class LearningPath {
    private String titulo;
    private String descripcion;
    private Profesor profesorCreador;
    private String objetivos;
    private String nivelDificultad;
    private int duracionMinutos;
    private float rating;
    private Date fechaCreacion;
    private Date fechaUltimaModificacion;
    private String version;
    private ArrayList<Estudiante> estudiantesInscritos;
    private ArrayList<Actividad> actividades;

    // Constructor
    public LearningPath(String titulo, String descripcion, String objetivos, String nivelDificultad, int duracionMinutos, 
    					float rating, Date fechaCreacion, Date fechaUltimaModificacion, String version, Profesor profesorCreador) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.profesorCreador = profesorCreador;
        this.objetivos = objetivos;
        this.nivelDificultad = nivelDificultad;
        this.duracionMinutos = duracionMinutos;
        this.rating = rating;
        this.fechaCreacion = fechaCreacion;
        this.fechaUltimaModificacion = fechaUltimaModificacion;
        this.version = version;
        this.estudiantesInscritos = new ArrayList<Estudiante>();
        this.actividades = new ArrayList<Actividad>();
    }
    
    // Constructor Número Dos
    public LearningPath(String titulo, String descripcion, String objetivos, String nivelDificultad, int duracionMinutos, 
    					Date fechaCreacion, List<Estudiante> estudiantesInscritos, List<Actividad> actividades) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.objetivos = objetivos;
        this.nivelDificultad = nivelDificultad;
        this.duracionMinutos = duracionMinutos;
        this.fechaCreacion = fechaCreacion;
        this.estudiantesInscritos = new ArrayList<>();
        this.actividades = new ArrayList<>();
    }
    
    // Getters y Setters

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getObjetivos() {
        return objetivos;
    }

    public void setObjetivos(String objetivos) {
        this.objetivos = objetivos;
    }

    public String getNivelDificultad() {
        return nivelDificultad;
    }

    public void setNivelDificultad(String nivelDificultad) {
        this.nivelDificultad = nivelDificultad;
    }

    public int getDuracionMinutos() {
        return duracionMinutos;
    }

    public void setDuracionMinutos(int duracionMinutos) {
        this.duracionMinutos = duracionMinutos;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaUltimaModificacion() {
        return fechaUltimaModificacion;
    }

    public void setFechaUltimaModificacion(Date fechaUltimaModificacion) {
        this.fechaUltimaModificacion = fechaUltimaModificacion;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public ArrayList<Estudiante> getEstudiantesInscritos() {
        return estudiantesInscritos;
    }

    public ArrayList<Actividad> getActividades() {
        return actividades;
    }
    
    public Profesor getProfesorCreador() {
 		return profesorCreador;
 	}

 	public void setProfesorCreador(Profesor profesorCreador) {
 		this.profesorCreador = profesorCreador;
 	}
    
    // Métodos Funcionales del Programa

 

	// Métodos para manejar estudiantes
    public void inscribirEstudiante(Estudiante estudiante) {
        if (!estudiantesInscritos.contains(estudiante)) {
            estudiantesInscritos.add(estudiante);
        }
    }

    public void eliminarEstudiante(Estudiante estudiante) {
        estudiantesInscritos.remove(estudiante);
    }
    
    // Métodos para manejar actividades
    public void agregarActividad(Actividad actividad) {
        if (!actividades.contains(actividad)) {
            actividades.add(actividad);
        }
    }

    public void eliminarActividad(Actividad actividad) {
        actividades.remove(actividad);
    }

    // Calcular la duración total de todas las actividades en el Learning Path
    public int calcularDuracionTotal() {
        int duracionTotal = 0;
        for (Actividad actividad : actividades) {
            duracionTotal += actividad.getDuracionMinutos();
        }
        return duracionTotal;
    }
}

