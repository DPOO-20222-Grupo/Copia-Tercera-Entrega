package seguimientoEstudiantes;

import actividades.RevisarRecurso;
import user.Estudiante;

public class SeguimientoRecurso extends SeguimientoActividad {
	
	private String recurso;
	
	public SeguimientoRecurso(RevisarRecurso recurso, Estudiante estudiante) {
		super(recurso, estudiante);
		this.recurso = recurso.getTipoRecurso();
	}
	

	public String getRecurso() {
		return recurso;
	}

}
