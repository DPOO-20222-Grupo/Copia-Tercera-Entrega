package exceptions;

public class ActividadYaExistenteException extends Exception{
	private String titulo;
	private String tipo;
	
	public ActividadYaExistenteException (String pTitulo, String pTipo) {
		super();
		this.titulo = pTitulo;
		this.tipo = pTipo;
	}
	
	
	@Override
	
	public String getMessage() {
		String mensaje = "Ya existe una actividad de tipo "+ this.tipo+" con el título '"+ this.titulo+"' creada por usted.";
		return mensaje;
	}
}
