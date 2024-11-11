package preguntas;

public class PreguntaSeleccionMultiple extends PreguntaCerrada {
	private static int CONTADOR = 1;
	private static String TIPO = "Cerrada";
    private String opcion1;
    private String opcion2;
    private String opcion3;
    private String opcion4;
    private int opcionCorrecta;

    // Constructor
    public PreguntaSeleccionMultiple(String enunciado, String titulo, String opcion1, String opcion2, String opcion3, String opcion4, int opcionCorrecta) {
        super(enunciado, titulo, opcionCorrecta);
        this.opcion1 = opcion1;
        this.opcion2 = opcion2;
        this.opcion3 = opcion3;
        this.opcion4 = opcion4;
        this.id = CONTADOR;
        CONTADOR+=1;
    }

    public String getOpcion1() {
        return opcion1;
    }

    public void setOpcion1(String opcion1) {
        this.opcion1 = opcion1;
    }

    public String getOpcion2() {
        return opcion2;
    }

    public void setOpcion2(String opcion2) {
        this.opcion2 = opcion2;
    }

    public String getOpcion3() {
        return opcion3;
    }

    public void setOpcion3(String opcion3) {
        this.opcion3 = opcion3;
    }

    public String getOpcion4() {
        return opcion4;
    }

    public void setOpcion4(String opcion4) {
        this.opcion4 = opcion4;
    }

    
    //Metodos adicionales 
   
    
    public String getTipo() {
		return TIPO;
	}

	public int getId() {
		return id;
	}


}
