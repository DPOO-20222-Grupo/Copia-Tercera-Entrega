package seguimientoEstudiantes;

import java.util.HashMap;
import java.util.List;

import actividades.Actividad;
import actividades.Encuesta;
import actividades.Examen;
import actividades.Quiz;
import actividades.RevisarRecurso;
import actividades.Tarea;
import learningPath.LearningPath;
import user.Estudiante;

public class SeguimientoLearningPath {
	
	private HashMap<String, SeguimientoActividad> mapaSeguimientoActividades;
	private String loginEstudiante;
	private String nombreEstudiante;
	private String idLearningPath;
    private float progreso; //proporcion de actividades completadas
    private int totalTiempo;
    private float tasaExito;
    private float tasaFracaso;

    // Constructor
    public SeguimientoLearningPath(LearningPath learningPath, Estudiante estudiante) {
        this.progreso = 0;
        this.totalTiempo = 0;
        this.tasaExito = 0;
        this.tasaFracaso = 0;
        this.idLearningPath = learningPath.getIdLearnginPath();
        this.loginEstudiante = estudiante.getLogin();
        this.nombreEstudiante = estudiante.getNombre();
        
        HashMap<String, SeguimientoActividad> seguimientoActividades = new HashMap<String, SeguimientoActividad>();
        
        List<Actividad> actividadesLearningPath = learningPath.getActividades();
        
        for(Actividad actividad: actividadesLearningPath) {
        	String tipoActividad = actividad.getTipoActividad();

    		
    		if (tipoActividad.equals("Encuesta")) {

    			SeguimientoEncuesta seguimiento = new SeguimientoEncuesta((Encuesta) actividad, estudiante );
    			seguimientoActividades.put(actividad.getIdActividad(), seguimiento);
    			
    		}
    		
    		else if (tipoActividad.equals("Tarea")) {
    			SeguimientoTarea seguimiento = new SeguimientoTarea((Tarea) actividad, estudiante );
    			seguimientoActividades.put(actividad.getIdActividad(), seguimiento);
    			
    		}
    		
    		else if (tipoActividad.equals("Quiz")) {
    			SeguimientoQuiz seguimiento = new SeguimientoQuiz((Quiz) actividad, estudiante );
    			seguimientoActividades.put(actividad.getIdActividad(), seguimiento);
    			
    		}
    		
    		else if (tipoActividad.equals("Examen")) {
    			SeguimientoExamen seguimiento = new SeguimientoExamen((Examen) actividad, estudiante );
    			seguimientoActividades.put(actividad.getIdActividad(), seguimiento);
    			
    		}
    		
    		else if (tipoActividad.equals("Recurso")) {
    			SeguimientoRecurso seguimiento = new SeguimientoRecurso((RevisarRecurso) actividad, estudiante );
    			seguimientoActividades.put(actividad.getIdActividad(), seguimiento);
    			
    		}


			
        }
        	this.mapaSeguimientoActividades = seguimientoActividades;
        
    
    }

    public float getProgreso() {
        return progreso;
    }

    private void setProgreso(float progreso) {
        this.progreso = progreso;
    }

    public int getTotalTiempo() {
        return totalTiempo;
    }

    private void setTotalTiempo(int totalTiempo) {
        this.totalTiempo = totalTiempo;
    }

    public float getTasaExito() {
        return tasaExito;
    }

    private void setTasaExito(float tasaExito) {
        this.tasaExito = tasaExito;
    }

    public float getTasaFracaso() {
        return tasaFracaso;
    }

    private void setTasaFracaso(float tasaFracaso) {
        this.tasaFracaso = tasaFracaso;
    }
    
    public HashMap<String, SeguimientoActividad> getMapaSeguimientoActividades() {
 		return mapaSeguimientoActividades;
 	}

 	public String getLoginEstudiante() {
 		return loginEstudiante;
 	}
 	public String getNombreEstudiante() {
 		return nombreEstudiante;
 	}

 	public String getIdLearningPath() {
 		return idLearningPath;
 	}

    
    //Metodos adicionales
    
 
	// Calcula el porcentaje de progreso
    public float calcularPorcentajeProgreso() {
        return progreso * 100;
    }

    public float calcularTasaExito() {
    	
    	int numActividadesExitosas = 0;
    	int totalActividades = this.getMapaSeguimientoActividades().size();
    	for (SeguimientoActividad seguimiento: this.getMapaSeguimientoActividades().values()) {
    		String estado = seguimiento.getEstado();
    		if (estado.equals("Exitoso") | estado.equals("Completado")) {
    			numActividadesExitosas++;
    		}
    	}
    	
    	float tasaExito = numActividadesExitosas/totalActividades;
    	
    	return tasaExito;
    	
    }
    
    public void actualizarTasaExito() {
    	float tasaExito = this.calcularTasaExito();
    	this.setTasaExito(tasaExito);
    	this.setTasaFracaso(1-tasaExito);
    }

    // Calcula el tiempo promedio por actividad 
    public float calcularTiempoPromedioPorActividad() {
        if (progreso == 0) {
            return 0; // Evita la división por cero si no hay progreso registrado.
        }
        return totalTiempo / (progreso*mapaSeguimientoActividades.size());
    }

    // Método para verificar si el estudiante está en riesgo 
    public boolean estaEnRiesgo() {
        return tasaFracaso > 0.6;
    }

    // Método para actualizar el progreso al completar una actividad
    public void actualizarProgreso() {
        float nuevoProgreso = this.getProgreso()+ (1/this.getMapaSeguimientoActividades().size());
        this.setProgreso(nuevoProgreso);
    }
    
    public void actualizarTiempoTotal(int tiempoAdicional) {
    	int nuevoTiempo = this.getTotalTiempo()+tiempoAdicional;
    	this.setTotalTiempo(nuevoTiempo);
    }
    
    //hola

}

