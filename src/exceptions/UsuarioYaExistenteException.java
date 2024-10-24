package exceptions;

@SuppressWarnings("serial")
public class UsuarioYaExistenteException extends Exception{

	private String login;
	
	public UsuarioYaExistenteException (String login) {
		super ();
		this.login = login;
		
	}
	
	@Override
	public String getMessage() {
		String mensaje = "El login "+this.login+" ya fue registrado en la plataforma.";
		return mensaje;
	}
}
