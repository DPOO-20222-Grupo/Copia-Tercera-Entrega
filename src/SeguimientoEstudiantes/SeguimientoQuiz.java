package SeguimientoEstudiantes;

import java.util.HashMap;
import java.util.Map;

import Actividades.Actividad;
import Actividades.Quiz;
import Preguntas.PreguntaSeleccionMultiple;
import User.Estudiante;

public class SeguimientoQuiz extends SeguimientoActividad{
	
	private float nota;
	private Map<PreguntaSeleccionMultiple, Integer> respuestas;
	
	
	public SeguimientoQuiz (Actividad actividad, Estudiante estudiante) {
		 super(actividad, estudiante);
		 this.nota = -1;
		 this.respuestas = new HashMap<PreguntaSeleccionMultiple, Integer>();
		 
		 Quiz quiz = (Quiz) actividad;
		 
		 
	
		
	}
	
	
	
	public float getNota() {
		return nota;
	}



	private void setNota(float nota) {
		this.nota = nota;
	}
	
	public Map<PreguntaSeleccionMultiple, Integer> getRespuestas() {
		return respuestas;
	}



	public void agregarRespuestaPregunta (PreguntaSeleccionMultiple pregunta, int opcionEscogida) {
		this.respuestas.put(pregunta, opcionEscogida);
	}
	
	public void calcularNota () {
		
		
		
	}



	
	
	
	
	
	

}
