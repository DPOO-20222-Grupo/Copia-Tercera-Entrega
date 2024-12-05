package interfazEstudiante;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import user.Estudiante;

public class PanelOpcionesEstudiante extends JPanel implements ActionListener {

	private Estudiante estudiante;
	
	private static final String INSCRIBIR = "Inscribirse";
	
	private static final String REALIZAR = "Realizar";
	
	private static final String INSCRITOS = "Inscritos";
	
	private static final String PROGRESO = "Progreso";
	
	private static final String RESENAR = "Resenar";
	
	private static final String CALIFICAR = "Calificar";
	
	public PanelOpcionesEstudiante(Estudiante estudiante) {
		super();
		this.estudiante = estudiante;
		
		this.setLayout(new GridLayout(8, 1));
		
		String msj = String.format("	Bienvenido a la aplicación estudiante %s", estudiante.getNombre());
		JLabel lblNombre = new JLabel(msj);
		
		JLabel lblOpciones = new JLabel("	Escoja la opción que desee: ");
		
		JRadioButton btnInscribirse = new JRadioButton("Inscribirse a un Learning Path");
		btnInscribirse.setActionCommand(INSCRIBIR);
		btnInscribirse.addActionListener(this);
		
		JRadioButton btnRealizarActividad = new JRadioButton("Realizar una actividad");
		btnRealizarActividad.setActionCommand(REALIZAR);
		btnRealizarActividad.addActionListener(this);
		
		JRadioButton btnVerInscritos = new JRadioButton("Ver Learning Paths inscritos");
		btnVerInscritos.setActionCommand(INSCRITOS);
		btnVerInscritos.addActionListener(this);
		
		JRadioButton btnVerProgreso = new JRadioButton("Ver progreso de un Learning Path");
		btnVerProgreso.setActionCommand(PROGRESO);
		btnVerProgreso.addActionListener(this);
		
		JRadioButton btnResenarActividad = new JRadioButton("Reseñar o calificar una actividad");
		btnResenarActividad.setActionCommand(RESENAR);
		btnResenarActividad.addActionListener(this);
		
		JRadioButton btnCalificarLP = new JRadioButton("Calificar un Learning Path");
		btnCalificarLP.setActionCommand(CALIFICAR);
		btnCalificarLP.addActionListener(this);
		
		ButtonGroup grupoBotones = new ButtonGroup();
		
		grupoBotones.add(btnInscribirse);
		grupoBotones.add(btnRealizarActividad);
		grupoBotones.add(btnVerInscritos);
		grupoBotones.add(btnVerProgreso);
		grupoBotones.add(btnResenarActividad);
		grupoBotones.add(btnCalificarLP);
		
		this.add(lblNombre);
		this.add(lblOpciones);
		this.add(btnInscribirse);
		this.add(btnRealizarActividad);
		this.add(btnVerInscritos);
		this.add(btnVerProgreso);
		this.add(btnResenarActividad);
		this.add(btnCalificarLP);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		
	}
}
