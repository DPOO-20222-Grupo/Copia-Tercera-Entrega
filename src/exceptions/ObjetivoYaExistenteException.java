package exceptions;

public class ObjetivoYaExistenteException extends Exception {
	
	private String objetivo;
	
	public ObjetivoYaExistenteException(String objetivo) {
		super();
		this.objetivo = objetivo;
				
	}
	@Override
	public String getMessage() {
		String mensaje = "El objetivo '"+this.objetivo+"' ya esta en los objetivos del Learning Path";
		return mensaje;
	}
	

}
