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
	
	
	public VentanaProfesor(Aplicacion aplicacion, Profesor profesor) {
		this.aplicacion = aplicacion;
		this.profesor = profesor;
		
		this.panelOpciones = new PanelOpcionesProfesor(profesor);
		this.add(panelOpciones);
		
		this.setVisible(true);
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null); 
        this.setResizable(false);
	}
}

