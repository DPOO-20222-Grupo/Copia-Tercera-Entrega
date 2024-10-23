package exceptions;

import user.Estudiante;

public class ModificarEstudianteLearningPathException extends Exception {
	
	private Estudiante estudiante;
	private String tipo;
	
	
	public ModificarEstudianteLearningPathException (Estudiante estudiante, String tipo) {
		super();
		this.estudiante = estudiante;
		this.tipo = tipo;
		
	}
	
	@Override
	
	public String getMessage() {
		
		if (this.tipo.equals("Agregar")) {
		
		String mensaje = String.format("El estudiante %1$s (%2$s) ya se encuentra inscrito en el Learning Path", this.estudiante.getNombre(), this.estudiante.getLogin());
		return mensaje;
		}
		
		else {
			String mensaje = String.format("El estudiante %1$s (%2$s) no se encuentra inscrito en el Learning Path", this.estudiante.getNombre(), this.estudiante.getLogin());
			return mensaje;
		}
	}
}
