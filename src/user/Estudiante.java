package user;

import java.util.HashMap;
import java.util.Map;

import seguimientoEstudiantes.SeguimientoLearningPath;

public class Estudiante extends Usuario {
    private static String TIPO = "Estudiante";
	private String nombre;
	private Map<String, SeguimientoLearningPath> learningPathsInscritos;

    // Constructor
    public Estudiante(String login, String password, String nombre) {
        super(login, password);
        this.nombre = nombre;
        this.learningPathsInscritos = new HashMap<String, SeguimientoLearningPath>();
    }
    
    // Getter and Setter
    
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Métodos adicionales 


	public String getTipo() {
		return TIPO;
	}

	public Map<String, SeguimientoLearningPath> getLearningPathsInscritos() {
		return learningPathsInscritos;
	}
	
	public void agregarSeguimientoLearningPath (SeguimientoLearningPath seguimiento) {
		
		String llave = seguimiento.getIdLearningPath();
		
		learningPathsInscritos.put(llave, seguimiento);
		
	}
	
}
