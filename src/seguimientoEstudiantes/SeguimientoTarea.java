package seguimientoEstudiantes;

import actividades.Tarea;
import user.Estudiante;

public class SeguimientoTarea extends SeguimientoActividad {
	
	private String metodoEnvio;
	
	public SeguimientoTarea(Tarea tarea, Estudiante estudiante) {
		super(tarea, estudiante);
		this.metodoEnvio = "";
	}
	
	public SeguimientoTarea() {
	    super();
	    this.metodoEnvio = "";
	}

	public String getMetodoEnvio() {
		return metodoEnvio;
	}

	public void setMetodoEnvio(String metodoEnvio) {
		this.metodoEnvio = metodoEnvio;
	}
	
	
	
	
	public void actualizarEstadoEnviado() {
		this.setEstado("Enviado");
	}
	
	public void actualizarEstadoFinal(boolean estado) {
		if (estado == true) {
			this.setEstado("Exitoso");
		}
		
		else {
			this.setEstado("No exitoso");
		}
	}
	
	
	
	
}
