package exceptions;

import actividades.Actividad;

@SuppressWarnings("serial")
public class ActividadSeguimientoCiclicoException extends Exception{
	
	private Actividad actividadModificada;
	private Actividad actividadAgregar;
	
	public ActividadSeguimientoCiclicoException (Actividad actMod, Actividad actAdd) {
		super();
		this.actividadModificada = actMod;
		this.actividadAgregar = actAdd;
	}
	
	public String getMessage() {
		String mensaje = String.format("La actividad '%s' es un seguimiento de la actividad '%s', no puede generar una referencia cíclica.", actividadModificada.getIdActividad(), actividadAgregar.getIdActividad());
		return mensaje;
	}

}
