package actividades;

import java.util.Date;
import java.util.List;

import user.Profesor;

public class RevisarRecurso extends Actividad {
	private static String TIPO = "Recurso";
    private String tipoRecurso;
    private String enlaceRecurso;

    // Constructor
    public RevisarRecurso(String titulo, String descripcion, List<String> objetivos, String nivelDificultad, int duracionMinutos, 
    						Date fechaLimite, String tipoRecurso, Profesor profesorCreador, String enlaceRecurso) {
        super(titulo, descripcion, objetivos, nivelDificultad, duracionMinutos, fechaLimite, profesorCreador);
        this.tipoRecurso = tipoRecurso;
        this.enlaceRecurso = enlaceRecurso;
    }

    public String getTipoRecurso() {
        return tipoRecurso;
    }

    public void setTipoRecurso(String tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
    }
    
    @Override
    public String getTipoActividad() {
    	return TIPO;
    }

	public String getEnlaceRecurso() {
		return enlaceRecurso;
	}

	public void setEnlaceRecurso(String enlaceRecurso) {
		this.enlaceRecurso = enlaceRecurso;
	}
    
    
}