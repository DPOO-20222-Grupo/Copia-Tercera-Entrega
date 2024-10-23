package exceptions;

@SuppressWarnings("serial")
public class ModificarObjetivosException extends Exception {
	
	private String objetivo;
	private String accion;
	private String tipo;
	
	public ModificarObjetivosException(String objetivo, String accion, String tipo) {
		super();
		this.objetivo = objetivo;
		this.accion = accion;
		this.tipo = tipo;
				
	}
	@Override
	public String getMessage() {
		
		if (this.tipo.equals("LearningPath")) {
			
			if (this.accion.equals("Agregar")){
			
				String mensaje = "El objetivo '"+this.objetivo+"' ya está en los objetivos del Learning Path";
				return mensaje;
				
			}
			
			else {
				String mensaje = "El objetivo '"+this.objetivo+"' no está en los objetivos del Learning Path";
				return mensaje;
				
			}
			
				
		}
		
		else {
			if (this.accion.equals("Agregar")){
				
				String mensaje = "El objetivo '"+this.objetivo+"' ya está en los objetivos de la Actividad";
				return mensaje;
				
			}
			
			else {
				
				String mensaje = "El objetivo '"+this.objetivo+"' no está en los objetivos de la Actividad";
				return mensaje;
				
			}
		}
	}
	

}
