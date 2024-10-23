package SeguimientoEstudiantes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Actividades.Actividad;
import Actividades.Encuesta;
import Actividades.Examen;
import Actividades.Quiz;
import Actividades.RevisarRecurso;
import Actividades.Tarea;
import LearningPath.LearningPath;
import User.Estudiante;

public class SeguimientoLearningPath {
	
	private HashMap<Actividad, SeguimientoActividad> mapaSeguimientoActividades;
	private Estudiante estudiante;
	private LearningPath learningPath;
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
        this.learningPath = learningPath;
        this.estudiante = estudiante;
        
        this.mapaSeguimientoActividades = new HashMap<Actividad, SeguimientoActividad>();
        
        List<Actividad> actividadesLearningPath = learningPath.getActividades();
        HashMap<Actividad, Boolean> actividadesCompletadas = new HashMap<>();
        
        for(Actividad actividad: actividadesLearningPath) {
        	
<<<<<<< HEAD
        	String tipoActividad = actividad.getTipoActividad();
    		
    		if (tipoActividad.equals("Encuesta")) {
    			SeguimientoEncuesta seguimiento = new SeguimientoEncuesta((Encuesta) actividad, estudiante );
    			mapaSeguimientoActividades.put(actividad, seguimiento);
    			
    		}
    		
    		else if (tipoActividad.equals("Tarea")) {
    			SeguimientoTarea seguimiento = new SeguimientoTarea((Tarea) actividad, estudiante );
    			mapaSeguimientoActividades.put(actividad, seguimiento);
    			
    		}
    		
    		else if (tipoActividad.equals("Quiz")) {
    			SeguimientoQuiz seguimiento = new SeguimientoQuiz((Quiz) actividad, estudiante );
    			mapaSeguimientoActividades.put(actividad, seguimiento);
    			
    		}
    		
    		else if (tipoActividad.equals("Examen")) {
    			SeguimientoExamen seguimiento = new SeguimientoExamen((Examen) actividad, estudiante );
    			mapaSeguimientoActividades.put(actividad, seguimiento);
    			
    		}
    		
    		else if (tipoActividad.equals("Recurso")) {
    			SeguimientoRecurso seguimiento = new SeguimientoRecurso((RevisarRecurso) actividad, estudiante );
    			mapaSeguimientoActividades.put(actividad, seguimiento);
    			
    		}
=======
			actividadesCompletadas.put(actividad, false);
>>>>>>> refs/remotes/origin/main
        	
        }
        
    
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
    
    public HashMap<Actividad, SeguimientoActividad> getMapaSeguimientoActividades() {
 		return mapaSeguimientoActividades;
 	}

 	public Estudiante getEstudiante() {
 		return estudiante;
 	}

 	public LearningPath getLearningPath() {
 		return learningPath;
 	}

    
    //Metodos adicionales
    
 
	// Calcula el porcentaje de progreso
    public float calcularPorcentajeProgreso() {
        return progreso * 100;
    }

    // Calcula la relación de éxito a fracaso
    public float calcularRelacionExitoFracaso() {
        if (tasaFracaso == 0) {
            return tasaExito; // Evita la división por cero si no hay fracasos.
        }
        return tasaExito / tasaFracaso;
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

    // Método para actualizar el progreso
    public void actualizarProgreso() {
        float nuevoProgreso = this.getProgreso()+ (1/this.getMapaSeguimientoActividades().size());
        this.setProgreso(nuevoProgreso);
    }
    
    public void actualizarTiempoTotal(int tiempoAdicional) {
    	int nuevoTiempo = this.getTotalTiempo()+tiempoAdicional;
    	this.setTotalTiempo(nuevoTiempo);
    }
    
    

}

