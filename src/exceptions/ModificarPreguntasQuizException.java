package exceptions;

import Preguntas.PreguntaSeleccionMultiple;

public class ModificarPreguntasQuizException extends Exception {
	
	private PreguntaSeleccionMultiple pregunta;
	private String accion;
	
	public ModificarPreguntasQuizException(PreguntaSeleccionMultiple pregunta, String accion) {
		super();
		this.pregunta = pregunta;
		this.accion = accion;
	}
	
	@Override
	
	public String getMessage() {
		
		if(this.accion.equals("Agregar")) {
			String mensaje = String.format("La  pregunta '%1$s' ya está en la lista de preguntas.", pregunta.getTitulo());
			return mensaje;
		}
		else {
			String mensaje = String.format("La  pregunta '%1$s' no está en la lista de preguntas.", pregunta.getTitulo());
			return mensaje;
		}
	}
}
