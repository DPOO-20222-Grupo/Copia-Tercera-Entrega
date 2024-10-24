package exceptions;

@SuppressWarnings("serial")
public class TipoInvalidoValorException extends Exception {
	
	private String tipoEsperado;
	private String tipoRecibido;
	
	public TipoInvalidoValorException (String tipoEsperado, String tipoRecibido) {
		super();
		this.tipoEsperado = tipoEsperado;
		this.tipoRecibido = tipoRecibido;
	}
	
	@Override
	public String getMessage() {
		String mensaje = String.format("Se esperaba un valor de tipo '%1$s' para modificar el parámetro, pero se recibio un valor de tipo '%2$s'.", 
										this.tipoEsperado,this.tipoRecibido);
		return mensaje;
	}
}
