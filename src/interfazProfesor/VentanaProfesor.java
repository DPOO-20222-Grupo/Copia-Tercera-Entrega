package interfazProfesor;

import javax.swing.*;

import aplicacion.Aplicacion;
import interfazProfesor.PanelOpcionesProfesor;
import user.Profesor;

@SuppressWarnings("serial")
public class VentanaProfesor extends JFrame{
	
	private Aplicacion aplicacion;
	private Profesor profesor;
	private PanelOpcionesProfesor panelOpciones; 
	
	
	public VentanaProfesor(Aplicacion app, Profesor prof) {
		this.aplicacion = app;
		this.profesor = prof;
		
		this.panelOpciones = new PanelOpcionesProfesor(profesor);
		this.add(panelOpciones);
		
		this.setVisible(true);
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null); 
        this.setResizable(false);
	}
}

