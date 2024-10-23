package preguntas;

public class PreguntaAbierta extends Pregunta {
	
	private static int CONTADOR = 1;
	private static String TIPO = "Abierta";


    // Constructor
    public PreguntaAbierta(String enunciado, String titulo) {
        super(enunciado, titulo);
        this.id = CONTADOR;
        CONTADOR+=1;
    }
    
    // Getters and Setters

	
	public String getTipo() {
		return TIPO;
	}

	public int getId() {
		return id;
	}
	
	
      
}
