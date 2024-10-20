package exceptions;

import Actividades.Actividad;

public class ModificarActividadesLearningPathException extends Exception{
	
	private Actividad actividad;
	private String tipo;
	
	
	public ModificarActividadesLearningPathException(Actividad actividad, String tipo) {
		super();
		this.actividad = actividad;
		this.tipo = tipo;
	}
	
	@Override
	
	public String getMessage() {
		if (this.tipo.equals("Agregar")) {
			String mensaje = String.format("La actividad '%1$s' del profesor %2$s (%3$s) ya se encuentra en el Learning Path",
					this.actividad.getTitulo(), this.actividad.getProfesorCreador().getNombre(), this.actividad.getProfesorCreador().getLogin());
			return mensaje;
		}
		else {
			String mensaje = String.format("La actividad '%1$s' del profesor %2$s (%3$s) no se encuentra en el Learning Path",
					this.actividad.getTitulo(), this.actividad.getProfesorCreador().getNombre(), this.actividad.getProfesorCreador().getLogin());
			return mensaje;
		}
	}
}
