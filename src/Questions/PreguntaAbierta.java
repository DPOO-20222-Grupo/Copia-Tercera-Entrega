package Questions;

public class PreguntaAbierta extends Pregunta {
    private String respuesta;

    // Constructor
    public PreguntaAbierta(String enunciado, String respuesta) {
        super(enunciado);
        this.respuesta = respuesta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
    
    // Metodos Adicionales
    
    // Método para verificar si la respuesta es correcta
    public boolean verificarRespuesta(String respuestaEsperada) {
        return this.respuesta.trim().equalsIgnoreCase(respuestaEsperada.trim());
    }

    // Método para limpiar espacios en blanco de la respuesta
    public void limpiarEspaciosRespuesta() {
        this.respuesta = this.respuesta.trim();
    }
}
