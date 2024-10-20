package Actividades;

import java.util.Date;
import java.util.List;

import User.Profesor;

public class Tarea extends Actividad {
	private static String TIPO = "Tarea";
    private String estado;
    private String metodoEnvio;

    // Constructor
    public Tarea(String titulo, String descripcion, List<String> objetivos, String nivelDificultad, int duracionMinutos, Date fechaLimite,  Profesor profesorCreador) {
        super(titulo, descripcion, objetivos, nivelDificultad, duracionMinutos, fechaLimite, profesorCreador);
        this.estado = estado;
        this.metodoEnvio = metodoEnvio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMetodoEnvio() {
        return metodoEnvio;
    }

    public void setMetodoEnvio(String metodoEnvio) {
        this.metodoEnvio = metodoEnvio;
    }
    
    public String getTipoActividad () {
    	return TIPO;
    }
}