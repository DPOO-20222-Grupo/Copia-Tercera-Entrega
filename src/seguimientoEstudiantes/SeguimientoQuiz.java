package seguimientoEstudiantes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import actividades.Quiz;
import preguntas.PreguntaCerrada;
import user.Estudiante;

public class SeguimientoQuiz extends SeguimientoActividad{
	
	private double nota;
	private Map<String, PreguntaCerrada> preguntas;
	private Map<String, Integer> respuestas;
	private int numPreguntas;
	
	public SeguimientoQuiz (Quiz quiz, Estudiante estudiante) {
		 super(quiz, estudiante);
		 this.nota = -1;
		 this.respuestas = new HashMap<String, Integer>();
		 this.numPreguntas = quiz.getNumPreguntas();
		 
		 for (PreguntaCerrada pregunta: quiz.getPreguntas()) {
			 
			 respuestas.put(pregunta.getIdPregunta(), -1);
			 preguntas.put(pregunta.getIdPregunta(), pregunta);
		 }
	}
	
	
	public double getNota() {
		return nota;
	}


	public int getNumPreguntas() {
		return numPreguntas;
	}


	private void setNota(double nota) {
		this.nota = nota;
	}
	
	public Map<String, Integer> getRespuestas() {
		return respuestas;
	}
	


	public void agregarRespuestaPregunta (PreguntaCerrada pregunta, int opcionEscogida) {
		this.respuestas.replace(pregunta.getIdPregunta(), opcionEscogida);
	}
	
	public double calcularNota () {
		
		Map<String, Integer> respuestas = this.getRespuestas();
		int numPreguntas = this.getNumPreguntas();
		
		float nota = 0;
		
		for (Map.Entry<String, Integer> entry : respuestas.entrySet()) {
			
			PreguntaCerrada pregunta = preguntas.get(entry.getKey());
			int opcionEscogida = entry.getValue();
			
			if (pregunta.verificarOpcionCorrecta(opcionEscogida)== true) {
				nota+=(1.0f/numPreguntas);
			}
		
			
		}
		
		return nota;
		
		
		
	}
	
	public void actualizarNota() {
		double nota = this.calcularNota();
		this.setNota(nota);
		
		Quiz quiz = (Quiz) this.getActividadSeguimiento();
		
		if (nota>=quiz.getCalificacionMinima()) {
			this.setEstado("Exitoso");
		}
		
		else {
			this.setEstado("No Exitoso");
		}
	}	

}
