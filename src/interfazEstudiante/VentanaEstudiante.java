package interfazEstudiante;

import javax.swing.JFrame;
import javax.swing.JPanel;

import interfaz.Aplicacion;
import user.Estudiante;

public class VentanaEstudiante extends JFrame{
	
	private Aplicacion app;
	
	private Estudiante estudiante;
	
	private PanelOpcionesEstudiante panelOpciones; 
	
	private JPanel panelContenido;
	
	public VentanaEstudiante(Aplicacion app, Estudiante estudiante) {
		this.app = app;
		this.estudiante = estudiante;
		
		this.panelOpciones = new PanelOpcionesEstudiante(estudiante);
		
		this.add(panelOpciones);
		
		this.setVisible(true);
		
		this.setSize(600, 400);

		
		
		
		
	}

}


