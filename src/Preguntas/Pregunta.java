package Preguntas;

public abstract class Pregunta {
    private String enunciado;
    private String titulo;
    protected int id;

    // Constructor
    public Pregunta(String enunciado, String titulo) {
        this.enunciado = enunciado;
        this.titulo = titulo;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }
    
    
    public String getTitulo() {
		return titulo;
	}

	public abstract String getTipo();
	
	public abstract int getId();
	
	public String getIdPregunta () {
		String titulo = this.getTitulo();
		int id = this.getId();
		String llave =  Integer.toString(id)+" - " + titulo;
		
		return llave;
	}
}

