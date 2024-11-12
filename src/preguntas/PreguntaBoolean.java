package preguntas;

public class PreguntaBoolean extends PreguntaCerrada{

	private static int CONTADOR = 1;
	private static String TIPO = "Boolean";
	
	public PreguntaBoolean(String enunciado, String titulo, int opcionCorrecta) {
		super(enunciado, titulo, opcionCorrecta);
		this.id = CONTADOR;
		CONTADOR+=1;	
	}

	@Override
	public String getTipo() {
		return TIPO;
	}

	@Override
	public int getId() {
		return this.id;
	}
	

	
	
	
	

}
