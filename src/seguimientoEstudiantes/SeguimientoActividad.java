package seguimientoEstudiantes;

import actividades.Actividad;
import user.Estudiante;

public  class SeguimientoActividad {

	private Actividad actividad;
	private String estado;
	private String loginEstudianteSeguimiento;
	private String nombreEstudianteSeguimiento;
	private int tiempoTotal;
	
	public SeguimientoActividad() {
	    this.estado = "Incompleta";
	    this.tiempoTotal = 0;
	}
	
	public SeguimientoActividad( Actividad actividad, Estudiante estudiante) {
		
		this.actividad = actividad;
		this.loginEstudianteSeguimiento = estudiante.getLogin();
		this.nombreEstudianteSeguimiento = estudiante.getNombre();
		this.estado = "Incompleta";
	}
	
	
	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Actividad getActividadSeguimiento() {
		return actividad;
	}
	public String getEstado() {
		return estado;
	}
	public String getLoginEstudianteSeguimiento() {
		return loginEstudianteSeguimiento;
	}
	public String getNombreEstudianteSeguimiento() {
		return nombreEstudianteSeguimiento;
	}


	public int getTiempoTotal() {
		return tiempoTotal;
	}


	public void setTiempoTotal(int tiempoTotal) {
		this.tiempoTotal = tiempoTotal;
	}
	
	public void actualizarEstadoCompletado () {
		this.setEstado("Completado");
	}
}
	
