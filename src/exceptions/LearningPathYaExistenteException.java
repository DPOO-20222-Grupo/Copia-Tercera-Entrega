package exceptions;

public class LearningPathYaExistenteException extends Exception{
	private String titulo;
	
	public LearningPathYaExistenteException (String pTitulo) {
		super();
		this.titulo = pTitulo;
	}
	
	
	@Override
	
	public String getMessage() {
		String mensaje = "Ya existe un Learning Path con el título '"+ this.titulo+"' creado por usted.";
		return mensaje;
	}
}
