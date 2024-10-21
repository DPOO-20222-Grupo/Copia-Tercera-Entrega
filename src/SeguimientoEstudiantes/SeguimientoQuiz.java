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
	private int numPreguntas;
	
	
	public SeguimientoQuiz (Quiz quiz, Estudiante estudiante) {
		 super(quiz, estudiante);
		 this.nota = -1;
		 this.respuestas = new HashMap<PreguntaSeleccionMultiple, Integer>();
		 this.numPreguntas = quiz.getNumPreguntas();
		 
		 for (PreguntaSeleccionMultiple pregunta: quiz.getPreguntas()) {
			 
			 respuestas.put(pregunta, -1);
			 
		 }
	}
	
	
	public float getNota() {
		return nota;
	}


	public int getNumPreguntas() {
		return numPreguntas;
	}


	private void setNota(float nota) {
		this.nota = nota;
	}
	
	public Map<PreguntaSeleccionMultiple, Integer> getRespuestas() {
		return respuestas;
	}



	public void agregarRespuestaPregunta (PreguntaSeleccionMultiple pregunta, int opcionEscogida) {
		this.respuestas.replace(pregunta, opcionEscogida);
	}
	
	public float calcularNota () {
		
		Map<PreguntaSeleccionMultiple, Integer> respuestas = this.getRespuestas();
		int numPreguntas = this.getNumPreguntas();
		
		float nota = 0;
		
		for (Map.Entry<PreguntaSeleccionMultiple, Integer> entry : respuestas.entrySet()) {
			PreguntaSeleccionMultiple pregunta = entry.getKey();
			int opcionEscogida = entry.getValue();
			
			if (pregunta.verificarRespuesta(opcionEscogida)== true) {
				nota+=(1/numPreguntas);
			}
		
			
		}
		
		return nota;
		
		
		
	}
	
	public void actualizarNota() {
		float nota = this.calcularNota();
		this.setNota(nota);
	}
	
	public void actualizarEstado() {
		this.setEstado("Completado");
	}



	
	
	
	
	
	

}
