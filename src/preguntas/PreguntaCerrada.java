package preguntas;

public abstract class PreguntaCerrada extends Pregunta {
	
	private int opcionCorrecta;
	
	
	public PreguntaCerrada(String enunciado, String titulo, int opcionCorrecta) {
		super(enunciado, titulo);
		this.opcionCorrecta = opcionCorrecta;
	}
	
	public int getOpcionCorrecta() {
		return opcionCorrecta;
	}

	public void setOpcionCorrecta(int opcionCorrecta) {
		this.opcionCorrecta = opcionCorrecta;
	}

	public boolean verificarOpcionCorrecta(int opcionEscogida) {
		
		boolean rta = false;
		
		if (opcionEscogida == this.getOpcionCorrecta()) {
			
			rta = true;
		}
		
		return rta;
		
	}
	
	
}
