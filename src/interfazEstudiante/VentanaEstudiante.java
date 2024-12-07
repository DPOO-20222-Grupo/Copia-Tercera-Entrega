package interfazEstudiante;

import java.awt.BorderLayout;
import java.time.LocalDate;

import javax.swing.JFrame;
import javax.swing.JPanel;

import interfaz.Aplicacion;
import interfazActividadesCompletadas.PanelActividadesDiarias;
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
		this.setLayout(new BorderLayout());
		this.add(panelOpciones, BorderLayout.NORTH);
		
		this.setVisible(true);
		
		this.setSize(600, 400);
		
		this.add(new PanelActividadesDiarias(app, LocalDate.now()), BorderLayout.SOUTH);

		
		
		
		
	}

}


