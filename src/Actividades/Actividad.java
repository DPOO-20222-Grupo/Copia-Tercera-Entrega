package Actividades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import User.Profesor;

public abstract class Actividad {
    private String titulo;
    private String descripcion;
    private String objetivos;
    private String nivelDificultad;
    private Profesor profesorCreador;
    private int duracionMinutos;
    private Date fechaLimite;
    private String resultado;
    private boolean esObligatoria;
    private String resenas; 
    private float rating; 
    private int contadorRatings; 
    private List<Actividad> actividadesPrevias;
    private List<Actividad> actividadesSeguimiento;

    // Constructor
    public Actividad(String titulo, String descripcion, String objetivos, String nivelDificultad, int duracionMinutos, Date fechaLimite, Profesor profesorCreador, boolean esObligatoria) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.objetivos = objetivos;
        this.nivelDificultad = nivelDificultad;
        this.profesorCreador = profesorCreador;
        this.duracionMinutos = duracionMinutos;
        this.fechaLimite = fechaLimite;
        this.esObligatoria = esObligatoria;
        this.rating = 0.0f; 
        this.resenas = ""; 
        this.contadorRatings = 0;
        this.actividadesPrevias = new ArrayList<>(); 
        this.actividadesSeguimiento = new ArrayList<>();
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

    public Date getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(Date fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public boolean isEsObligatoria() {
        return esObligatoria;
    }

    public void setEsObligatoria(boolean esObligatoria) {
        this.esObligatoria = esObligatoria;
    }

    public String getResenas() {
        return resenas;
    }

    public void setResenas(String resenas) {
        this.resenas = resenas;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
    
    public Profesor getProfesorCreador() {
 		return profesorCreador;
 	}

 	public void setProfesorCreador(Profesor profesorCreador) {
 		this.profesorCreador = profesorCreador;
 	}
    
    
    // Métodos para gestionar actividades previas y de seguimiento

 

	public void agregarActividadPrevia(Actividad actividad) {
        if (!actividadesPrevias.contains(actividad)) {
            actividadesPrevias.add(actividad);
        }
    }

    public void eliminarActividadPrevia(Actividad actividad) {
        actividadesPrevias.remove(actividad);
    }

    public List<Actividad> getActividadesPrevias() {
        return actividadesPrevias;
    }

    public void agregarActividadSeguimiento(Actividad actividad) {
        if (!actividadesSeguimiento.contains(actividad)) {
            actividadesSeguimiento.add(actividad);
        }
    }

    public void eliminarActividadSeguimiento(Actividad actividad) {
        actividadesSeguimiento.remove(actividad);
    }

    public List<Actividad> getActividadesSeguimiento() {
        return actividadesSeguimiento;
    }

    // Métodos Adicionales

    public void completar() {
        System.out.println("La actividad " + titulo + " ha sido completada.");
    }

    public void dejarFeedback(String reseña, float rating) {
        this.resenas += reseña + "\n"; 
        contadorRatings++; 
        this.rating = ((this.rating * (contadorRatings - 1)) + rating) / contadorRatings; 
    }
    
    public abstract String getTipoActividad();
}
