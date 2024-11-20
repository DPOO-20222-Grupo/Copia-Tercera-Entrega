package exceptions;

import learningPath.LearningPath;
import user.Estudiante;

public class EstudianteNoInscritoException extends Exception{

	private LearningPath learningPath;
	
	public EstudianteNoInscritoException(LearningPath lp) {
		this.learningPath = lp;
	}
	
	public String getMessage() {
		String msj = String.format("Usted no se encuentra inscrito al Learning Path '%s' del profesor '%s'", learningPath.getTitulo(), learningPath.getNombreProfesorCreador());
		return msj;
	}
	
}
