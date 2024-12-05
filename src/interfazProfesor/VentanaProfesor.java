package interfazProfesor;

import javax.swing.*; 

import interfaz.Aplicacion;
import interfazProfesor.PanelOpcionesProfesor;
import user.Profesor;

@SuppressWarnings("serial")
public class VentanaProfesor extends JFrame{
	
	private Aplicacion aplicacion;
	private Profesor profesor;
	private PanelOpcionesProfesor panelOpciones; 
	private JPanel panelContenido;
	
	
	public VentanaProfesor(Aplicacion aplicacion, Profesor profesor) {
		this.aplicacion = aplicacion;
		this.profesor = profesor;
		
		this.panelOpciones = new PanelOpcionesProfesor(profesor);
		this.add(panelOpciones);
		
		this.setVisible(true);
		this.setSize(600, 400);	
	}
}

