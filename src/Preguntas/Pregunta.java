package Preguntas;

public class Pregunta {
    private String enunciado;

    // Constructor
    public Pregunta(String enunciado) {
        this.enunciado = enunciado;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }
    
    // Métodos adicionales
    
    // Valida si el enunciado es válido (no vacío y no nulo)
    public boolean validarEnunciado() {
        return this.enunciado != null && !this.enunciado.isEmpty();
    }

    // Actualiza el enunciado si es válido
    public boolean actualizarEnunciado(String nuevoEnunciado) {
        if (nuevoEnunciado != null && !nuevoEnunciado.isEmpty()) {
            this.enunciado = nuevoEnunciado;
            return true;
        }
        return false;
    }

    // Muestra los detalles de la pregunta
    public void mostrarDetalles() {
        System.out.println("Enunciado de la Pregunta: " + this.enunciado);
    }

    // Compara si el enunciado de otra pregunta es igual
    public boolean compararCon(Pregunta otraPregunta) {
        return this.enunciado.equals(otraPregunta.getEnunciado());
    }
}

