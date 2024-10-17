package Preguntas;

public class PreguntaAbierta extends Pregunta {

	private String respuestaCorrecta; // Respuesta esperada
    private boolean esRespuestaCorrecta; // Estado de la respuesta según el profesor

    // Constructor
    public PreguntaAbierta(String enunciado) {
        super(enunciado);
        this.esRespuestaCorrecta = false; // Por defecto, se establece como incorrecta
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
      
}
