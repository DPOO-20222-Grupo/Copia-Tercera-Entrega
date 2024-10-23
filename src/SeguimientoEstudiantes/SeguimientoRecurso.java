package SeguimientoEstudiantes;

import Actividades.RevisarRecurso;
import User.Estudiante;

public class SeguimientoRecurso extends SeguimientoActividad {
	
	private String recurso;
	
	public SeguimientoRecurso(RevisarRecurso recurso, Estudiante estudiante) {
		super(recurso, estudiante);
		this.recurso = recurso.getTipoRecurso();
	}
	

	public String getRecurso() {
		return recurso;
	}

	public void setRecurso(String recurso) {
		this.recurso = recurso;
	}
	
	
	

}
