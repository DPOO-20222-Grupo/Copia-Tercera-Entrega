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
}
