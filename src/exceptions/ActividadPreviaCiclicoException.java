package exceptions;

import actividades.Actividad;

@SuppressWarnings("serial")
public class ActividadPreviaCiclicoException extends Exception{
	
	private Actividad actividadModificada;
	private Actividad actividadAgregar;
	
	public ActividadPreviaCiclicoException (Actividad actMod, Actividad actAdd) {
		super();
		this.actividadModificada = actMod;
		this.actividadAgregar = actAdd;
	}
	
	public String getMessage() {
		String mensaje = String.format("La actividad '%s' es un prerrequisito de la actividad '%s', no puede generar una referencia cíclica.", actividadModificada.getIdActividad(), actividadAgregar.getIdActividad());
		return mensaje;
	}

}
