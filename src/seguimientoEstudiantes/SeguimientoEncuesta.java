package seguimientoEstudiantes;

import java.util.HashMap;
import java.util.Map;

import actividades.Encuesta;
import preguntas.PreguntaAbierta;
import user.Estudiante;

public class SeguimientoEncuesta extends SeguimientoActividad {
	
	private Map<PreguntaAbierta, String> respuestas;
	
	public SeguimientoEncuesta(Encuesta encuesta, Estudiante estudiante) {
		super(encuesta, estudiante);
		this.respuestas = new HashMap<PreguntaAbierta, String>();
		
		for (PreguntaAbierta pregunta: encuesta.getPreguntas()) {
			this.respuestas.put(pregunta, "");
		}
	}

	public Map<PreguntaAbierta, String> getRespuestas() {
		return respuestas;
	}
	
	
	public void registrarPregunta(PreguntaAbierta pregunta, String respuesta) {
		
		this.getRespuestas().replace(pregunta, respuesta);
		
	}

}
