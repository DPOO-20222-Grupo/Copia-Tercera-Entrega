package Actividades;

import java.util.Date;
import java.util.List;

import User.Profesor;

public class Tarea extends Actividad {
	private static String TIPO = "Tarea";

    // Constructor
    public Tarea(String titulo, String descripcion, List<String> objetivos, String nivelDificultad, int duracionMinutos, Date fechaLimite,  Profesor profesorCreador) {
        super(titulo, descripcion, objetivos, nivelDificultad, duracionMinutos, fechaLimite, profesorCreador);
    }
    
    @Override
    public String getTipoActividad () {
    	return TIPO;
    }
    
    
    
}