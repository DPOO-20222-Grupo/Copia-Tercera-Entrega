package learningPath;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import actividades.Actividad;
import exceptions.ModificarActividadesLearningPathException;
import exceptions.ModificarEstudianteLearningPathException;
import exceptions.ModificarObjetivosException;
import seguimientoEstudiantes.SeguimientoLearningPath;
import user.Estudiante;
import user.Profesor;


public class LearningPath {
	public final static String BAJO = "Principiante";
	public final static String MEDIO = "Intermedio";
	public final static String ALTO = "Avanzado";
    private String titulo;
    private String descripcion;
    private String loginProfesorCreador;
    private String nombreProfesorCreador;
    private List<String> objetivos;
    private String nivelDificultad;
    private int duracionMinutos;
    private double rating;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaUltimaModificacion;
    private double version;
    private Map <String, SeguimientoLearningPath> estudiantesInscritos;
    private List<Actividad> actividades;
    private Map<String, Boolean> mapaActividadesObligatorias;
    private int contadorRatings;

    // Constructor
    
    public LearningPath(String titulo, String descripcion, List<String> objetivos, String nivelDificultad, 
    					Profesor profesorCreador,List <Actividad> listaActividades, Map<String, Boolean> mapaObligatoriedad) 
    {
    	this.titulo = titulo;
    	this.descripcion = descripcion;
    	this.objetivos = objetivos;
    	this.nivelDificultad = nivelDificultad;
    	this.loginProfesorCreador = profesorCreador.getLogin();
    	this.nombreProfesorCreador = profesorCreador.getNombre();
    	this.actividades = listaActividades;
    	this.mapaActividadesObligatorias = mapaObligatoriedad;
    	
    	this.duracionMinutos = calcularDuracionTotalLista (listaActividades);
    	
    	this.rating = 0;
    	this.fechaCreacion = LocalDateTime.now();
    	this.fechaUltimaModificacion = fechaCreacion;
    	this.version = 1;
    	
    	this.estudiantesInscritos = new HashMap<String, SeguimientoLearningPath>();
    	this.contadorRatings = 0;
    	
    }
    
    

    
    // GETTERS, SETTERS Y ACTUALIZACIONES ADECUADAS
    
    //Titulo
    public String getTitulo() {
		return titulo;
	}

	private void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public void modificarTitulo (String titulo) {
		this.setTitulo(titulo);
		this.actualizarVersion();
		this.actualizarFechaUltimaModificacion();
	}
	
	//Descripcion
	public String getDescripcion() {
		return descripcion;
	}


	private void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public void modificarDescripcion (String descripcion) {
		this.setDescripcion(descripcion);
		this.actualizarVersion();
		this.actualizarFechaUltimaModificacion();
	}
	
	//Objetivos
	public List<String> getObjetivos() {
		return objetivos;
	}


	private void setObjetivos(List<String> objetivos) {
		this.objetivos = objetivos;
	}
	
	public void modificarObjetivosLista(List<String> objetivos) {
		this.setObjetivos(objetivos);
		this.actualizarVersion();
		this.actualizarFechaUltimaModificacion();
	}
	
	public void agregarObjetivo(String objetivo) throws ModificarObjetivosException {
		List<String> objetivos = this.getObjetivos();
		if (objetivos.contains(objetivo)) {
			throw new ModificarObjetivosException(objetivo, "Agregar", "LearningPath");
		}
		
		else {
			objetivos.add(objetivo);
			this.modificarObjetivosLista(objetivos);
		}
		
	}
	
	public void eliminarObjetivo(String objetivo) throws ModificarObjetivosException {
		List<String> objetivos = this.getObjetivos();
		if (objetivos.contains(objetivo)) {
			objetivos.remove(objetivo);
			this.modificarObjetivosLista(objetivos);;
		}
		
		else {
			throw new ModificarObjetivosException(objetivo, "Eliminar", "LearningPath");
			
		}
	}

	//Nivel de Dificultad
	public String getNivelDificultad() {
		return nivelDificultad;
	}


	private void setNivelDificultad(String nivelDificultad) {
		this.nivelDificultad = nivelDificultad;
	}
	
	public void actualizarDificultad(String nivelDificultad) {
		this.setNivelDificultad(nivelDificultad);
		this.actualizarVersion();
		this.actualizarFechaUltimaModificacion();
	}
	
	//Duracion ( se modifica automaticamente cuando se añaden o eliminan actividades)

	public int getDuracionMinutos() {
		return duracionMinutos;
	}


	private void setDuracionMinutos(int duracionMinutos) {
		this.duracionMinutos = duracionMinutos;
	}
	
	//Rating
	public double getRating() {
		return rating;
	}


	private void setRating(double rating) {
		this.rating = rating;
	}
	
	public void actualizarRating(double nuevoRating) {
		int numRatings = getContadorRatings();
		double ratingActual = getRating();
		
		if (numRatings == 0) {
			actualizarContadorRatings();
			setRating(nuevoRating);
		}
		
		else {
			double numRatingsActualizado = numRatings+1;
			double ratingActualizado = ratingActual*((numRatings)/(numRatingsActualizado))+ nuevoRating*(1/numRatingsActualizado);
			this.actualizarContadorRatings();
			this.setRating(ratingActualizado);
		}
		
		
	}


	public LocalDateTime getFechaUltimaModificacion() {
		return fechaUltimaModificacion;
	}


	private void setFechaUltimaModificacion(LocalDateTime fechaUltimaModificacion) {
		this.fechaUltimaModificacion = fechaUltimaModificacion;
	}
	
	private void actualizarFechaUltimaModificacion() {
		LocalDateTime fechaAhora = LocalDateTime.now();
		this.setFechaUltimaModificacion(fechaAhora);
	}

	// Version
	public double getVersion() {
		return version;
	}

	private void setVersion(double version) {
		this.version = version;
	}
	
	private void actualizarVersion () {
		double versionActual = this.getVersion();
		this.setVersion(versionActual+0.1);
	}
	
	//Profesor Creador
	public String getLoginProfesorCreador() {
		return loginProfesorCreador;
	}
	
	public String getNombreProfesorCreador() {
		return nombreProfesorCreador;
	}
	
	//Fecha Creacion
	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}
	
	//Actividades
	public List<Actividad> getActividades() {
		return actividades;
	}

    public void agregarActividad(Actividad actividad, boolean obligatoriedad) throws ModificarActividadesLearningPathException {
        if (!actividades.contains(actividad)) {
            actividades.add(actividad);
            mapaActividadesObligatorias.put(actividad.getIdActividad(), obligatoriedad);
            
            int nuevaDuracion = this.calcularDuracionTotal();
            this.setDuracionMinutos(nuevaDuracion);
            
            this.actualizarFechaUltimaModificacion();
            this.actualizarVersion();
        }
        else {
        	throw new ModificarActividadesLearningPathException(actividad, "Agregar");
        }
    }

    public void eliminarActividad(Actividad actividad) throws ModificarActividadesLearningPathException{
    	if (actividades.contains(actividad)) {
    		
    		actividades.remove(actividad);
    		mapaActividadesObligatorias.remove(actividad.getIdActividad());
    		int nuevaDuracion = this.calcularDuracionTotal();
    		this.setDuracionMinutos(nuevaDuracion);
    		
    		this.actualizarFechaUltimaModificacion();
    		this.actualizarVersion();
    		
        
    	}
    	
    	else {
    		throw new  ModificarActividadesLearningPathException(actividad, "Eliminar");
    	}
    }
    
    
    //Mapa Obligatoriedad Actividades
	public Map<String, Boolean> getMapaActividadesObligatorias() {
		return mapaActividadesObligatorias;
	}
	
	public void modificarObligatoriedadActividad(Actividad actividad) {
		String llave = actividad.getIdActividad();
		boolean valorAnterior = mapaActividadesObligatorias.get(llave);
		
		mapaActividadesObligatorias.replace(llave, !valorAnterior);
	}
	
	//Contador Ratings
	public int getContadorRatings() {
		return contadorRatings;
	}

	private void setContadorRatings(int contadorRatings) {
		this.contadorRatings = contadorRatings;
	}
	
	private void actualizarContadorRatings() {
		setContadorRatings(contadorRatings+1);
	}
	
	//Estudiantes Inscritos
		public Map<String, SeguimientoLearningPath> getEstudiantesInscritos() {
			return estudiantesInscritos;
		}

		
    public void inscribirEstudiante(Estudiante estudiante, SeguimientoLearningPath seguimiento) throws ModificarEstudianteLearningPathException {
        if (!estudiantesInscritos.containsKey(estudiante.getLogin())) {
        	
            estudiantesInscritos.put(estudiante.getLogin(), seguimiento);
        }
        
        else {
        	throw new ModificarEstudianteLearningPathException (estudiante, "Agregar");
        }
    }

	public void eliminarEstudiante(Estudiante estudiante) throws ModificarEstudianteLearningPathException {
		if (estudiantesInscritos.containsKey(estudiante.getLogin())) {
			estudiantesInscritos.remove(estudiante.getLogin());
        
		}
		
		else {
			throw new ModificarEstudianteLearningPathException (estudiante, "Eliminar");
		}
    }
    

    // Calcular la duración total de todas las actividades en el Learning Path
    public int calcularDuracionTotal() {
        int duracionTotal = 0;
        for (Actividad actividad : actividades) {
            duracionTotal += actividad.getDuracionMinutos();
        }
        return duracionTotal;
    }
    
    public String getIdLearnginPath () {
    	String titulo = this.getTitulo();
    	String loginProfesor = this.getLoginProfesorCreador();
    	
    	String id = titulo + " - " + loginProfesor;
    	
    	return id;
    }
    
    public int calcularDuracionTotalLista (List<Actividad> actividades) {
        int duracionTotal = 0;
        for (Actividad actividad : actividades) {
            duracionTotal += actividad.getDuracionMinutos();
        }
        return duracionTotal;
    }
    
}

