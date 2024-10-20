package exceptions;

public class ObjetivoNoExisteException extends Exception{
	
	private String objetivo;
	
	public ObjetivoNoExisteException (String objetivo) {
		super();
		this.objetivo = objetivo;
	}
	
	@Override
	
	public String getMessage() {
		String mensaje = "El objetivo '"+this.objetivo+"' no se encuentra en la lista de objetivos del Learning Path";
		return mensaje;
	}
	
}
