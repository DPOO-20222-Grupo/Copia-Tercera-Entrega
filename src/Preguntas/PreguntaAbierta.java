package Preguntas;

public class PreguntaAbierta extends Pregunta {
	
	private static int CONTADOR = 1;
	private static String TIPO = "Abierta";
	

	private String respuestaCorrecta; // Respuesta esperada
    private boolean esRespuestaCorrecta; // Estado de la respuesta según el profesor

    // Constructor
    public PreguntaAbierta(String enunciado, String titulo) {
        super(enunciado, titulo);
        this.esRespuestaCorrecta = false; // Por defecto, se establece como incorrecta
        this.id = CONTADOR;
        CONTADOR+=1;
    }
    
    // Getters and Setters

	public String getRespuestaCorrecta() {
		return respuestaCorrecta;
	}

	public void setRespuestaCorrecta(String respuestaCorrecta) {
		this.respuestaCorrecta = respuestaCorrecta;
	}

	public boolean isEsRespuestaCorrecta() {
		return esRespuestaCorrecta;
	}

	public void setEsRespuestaCorrecta(boolean esRespuestaCorrecta) {
		this.esRespuestaCorrecta = esRespuestaCorrecta;
	}
	
	public String getTipo() {
		return TIPO;
	}

	public int getId() {
		return id;
	}
	
	
      
}
