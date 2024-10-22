package SeguimientoEstudiantes;

import java.util.HashMap;
import java.util.Map;

import Actividades.Actividad;
import Preguntas.PreguntaAbierta;
import User.Estudiante;

public abstract class SeguimientoActividad {

	private Actividad actividad;
	private String estado;
	private Estudiante estudianteSeguimiento;
	private int tiempoTotal;
	
	
	public SeguimientoActividad( Actividad pActividad, Estudiante pEstudiante) {
		
		this.actividad = pActividad;
		this.estudianteSeguimiento = pEstudiante;
		this.estado = "Incompleta";
	}
	
	
	protected void setEstado(String estado) {
		this.estado = estado;
	}

	public Actividad getActividadSeguimiento() {
		return actividad;
	}
	public String getEstado() {
		return estado;
	}
	public Estudiante getEstudianteSeguimiento() {
		return estudianteSeguimiento;
	}


	public int getTiempoTotal() {
		return tiempoTotal;
	}


	public void setTiempoTotal(int tiempoTotal) {
		this.tiempoTotal = tiempoTotal;
	}
	
	


}
	
