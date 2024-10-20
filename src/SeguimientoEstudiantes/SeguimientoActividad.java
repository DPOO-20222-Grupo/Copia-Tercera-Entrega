package SeguimientoEstudiantes;

import java.util.HashMap;
import java.util.Map;

import Actividades.Actividad;
import Preguntas.PreguntaAbierta;
import User.Estudiante;

public class SeguimientoActividad {

	private Actividad actividadSeguimiento;
	private String estado;
	private Estudiante estudianteSeguimiento;
	private int nota;
	private String metodoEnvio;
	private Map<String, String> respuestaPreguntas;
	
	
	public SeguimientoActividad( Actividad pActividad, Estudiante pEstudiante) {
		
		this.actividadSeguimiento = pActividad;
		this.estudianteSeguimiento = pEstudiante;
		
		if (pActividad.getTipoActividad().equals("Quiz") | pActividad.getTipoActividad().equals("Examen")) {
			this.nota = -1;
		}
		
		else {
			this.nota = -2;
		}
		
		if (pActividad.getTipoActividad().equals("Tarea")) {
			this.metodoEnvio = "";
		
		}
		
		else {
			this.metodoEnvio = "N/A";
			
		}
		
		this.respuestaPreguntas = new HashMap<String, String>();
	}
	
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public void setNota(int nota) {
		this.nota = nota;
	}
	public void setMetodoEnvio(String metodoEnvio) {
		this.metodoEnvio = metodoEnvio;
	}
	public Actividad getActividadSeguimiento() {
		return actividadSeguimiento;
	}
	public String getEstado() {
		return estado;
	}
	public Estudiante getEstudianteSeguimiento() {
		return estudianteSeguimiento;
	}
	public int getNota() {
		return nota;
	}
	public String getMetodoEnvio() {
		return metodoEnvio;
	}
	public Map<String, String> getRespuestaPreguntas() {
		return respuestaPreguntas;
	}
	
	public void agregarRespuestaPregunta (PreguntaAbierta pregunta, String respuesta) {
		String llave = pregunta.getIdPregunta();
		
		respuestaPreguntas.put(llave, respuesta);
	}
	
}
