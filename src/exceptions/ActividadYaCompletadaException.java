package exceptions;

import actividades.Actividad;
import learningPath.LearningPath;

@SuppressWarnings("serial")
public class ActividadYaCompletadaException extends Exception {
	
	private Actividad actividad;
	private LearningPath learningPath;

	public ActividadYaCompletadaException (Actividad actividad, LearningPath learningPath) {
		
		this.actividad = actividad;
		this.learningPath = learningPath;
	}
	
	public String getMessage() {
		String msj = String.format("Usted ya completó la actividad '%s' del profesor '%s' (%s) y de tipo '%s' en el Learning Path '%s' del profesor '%s' (%s)", 
				actividad.getTitulo(), actividad.getNombreProfesorCreador(), actividad.getLoginProfesorCreador(), 
				actividad.getTipoActividad(), learningPath.getTitulo(), learningPath.getNombreProfesorCreador(), learningPath.getLoginProfesorCreador());
		return msj;
	}
}
