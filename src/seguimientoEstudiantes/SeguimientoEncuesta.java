package seguimientoEstudiantes;

import java.util.HashMap;
import java.util.Map;

import actividades.Encuesta;
import preguntas.PreguntaAbierta;
import user.Estudiante;

public class SeguimientoEncuesta extends SeguimientoActividad {
	
	private Map<String, String> respuestas;
	
	public SeguimientoEncuesta(Encuesta encuesta, Estudiante estudiante) {
		super(encuesta, estudiante);
		this.respuestas = new HashMap<String, String>();
		
		for (PreguntaAbierta pregunta: encuesta.getPreguntas()) {
			this.respuestas.put(pregunta.getIdPregunta(), "");
		}
	}

	public Map<String, String> getRespuestas() {
		return respuestas;
	}
	
	
	public void registrarPregunta(PreguntaAbierta pregunta, String respuesta) {
		
		this.getRespuestas().replace(pregunta.getIdPregunta(), respuesta);
		
	}

}
