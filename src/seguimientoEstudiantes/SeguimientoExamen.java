package seguimientoEstudiantes;

import java.util.HashMap;
import java.util.Map;

import actividades.Examen;
import preguntas.PreguntaAbierta;
import user.Estudiante;

public class SeguimientoExamen extends SeguimientoActividad{
	
	private float nota;
	private Map<String, String> respuestas;
	
	public SeguimientoExamen(Examen examen, Estudiante estudiante) {
		super(examen, estudiante);
		this.nota = -1;
		this.respuestas = new HashMap<String, String>();
		
		for(PreguntaAbierta pregunta: examen.getPreguntas()) {
			
			this.respuestas.put(pregunta.getIdPregunta(), "");
			
		}
		
		
		
	}

	public float getNota() {
		return nota;
	}
	

	public void setNota(float nota) {
		this.nota = nota;
	}
	
	public void actualizarEstadoEnviado() {
		this.setEstado("Enviado");
	}
	
	public void actualizarEstadoCompletado() {
		this.setEstado("Completado");
	}

	public Map<String, String> getRespuestas() {
		return respuestas;
	}
	
	public void registrarPregunta(PreguntaAbierta pregunta, String respuesta) {
		
		getRespuestas().replace(pregunta.getIdPregunta(), respuesta);
		
	}


	
	public void actualizarEstado(boolean esExitoso) {
		if (esExitoso) {
			this.setEstado("Exitoso");
		}
		
		else {
			this.setEstado("No exitoso");
		}
		
	}
		
}
