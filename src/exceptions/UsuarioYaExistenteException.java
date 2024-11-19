package exceptions;

@SuppressWarnings("serial")
public class UsuarioYaExistenteException extends Exception{

	private String login;
	private String tipo;
	
	public UsuarioYaExistenteException (String login, String tipo) {
		super ();
		this.login = login;
		this.tipo = tipo;
		
	}
	
	@Override
	public String getMessage() {
		String mensaje = "El login "+this.login+" ya fue registrado en la plataforma como "+tipo;
		return mensaje;
	}
}
