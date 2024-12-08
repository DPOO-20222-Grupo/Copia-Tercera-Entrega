package interfazEstudiante;

import javax.swing.JFrame;
import javax.swing.JPanel;

import interfaz.Aplicacion;
import user.Estudiante;

@SuppressWarnings("serial")
public class VentanaEstudiante extends JFrame{
	
	private Aplicacion aplicacion;
	private Estudiante estudiante;
	private PanelOpcionesEstudiante panelOpciones; 
	private JPanel panelContenido;
	
	public VentanaEstudiante(Aplicacion app, Estudiante est) {
		this.aplicacion = app;
		this.estudiante = est;
		
		this.panelOpciones = new PanelOpcionesEstudiante(estudiante, app);
		this.add(panelOpciones);
		
		this.setVisible(true);
		this.setSize(800, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null); 
        this.setResizable(true);
	}

}


