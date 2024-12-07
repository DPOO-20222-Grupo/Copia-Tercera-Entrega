package interfazEstudiante;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import exceptions.ModificarEstudianteLearningPathException;
import interfaz.Aplicacion;
import learningPath.LearningPath;
import seguimientoEstudiantes.SeguimientoLearningPath;
import user.Estudiante;

@SuppressWarnings("serial")
public class PanelOpcionesEstudiante extends JPanel implements ActionListener {
	
	private Aplicacion aplicacion;
	
	private Estudiante estudiante;
	
	private static final String INSCRIBIR = "Inscribirse";
	
	private static final String REALIZAR = "Realizar";
	
	private static final String INSCRITOS = "Inscritos";
	
	private static final String PROGRESO = "Progreso";
	
	private static final String RESENAR = "Resenar";
	
	private static final String CALIFICAR = "Calificar";
	
	private JPanel panelEste;
	private JPanel panelOeste;
	
	public PanelOpcionesEstudiante(Estudiante estudiante) {
		super();
		this.estudiante = estudiante;
		this.setLayout(new BorderLayout());
		this.aplicacion = new Aplicacion("usuarios.json", "lp.json", "preguntas.json", "actividades.json");
		
		panelOeste = new JPanel();
		
		panelOeste.setLayout(new GridLayout(9, 1));
		
		String msj = String.format("	Bienvenido a la aplicación, %s.", estudiante.getNombre());
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
		
		JRadioButton btnCerrarS = new JRadioButton("Cerrar Sesión");
		btnCerrarS.setActionCommand("Close");
		btnCerrarS.addActionListener(this);
		
		ButtonGroup grupoBotones = new ButtonGroup();
		
		grupoBotones.add(btnInscribirse);
		grupoBotones.add(btnRealizarActividad);
		grupoBotones.add(btnVerInscritos);
		grupoBotones.add(btnVerProgreso);
		grupoBotones.add(btnResenarActividad);
		grupoBotones.add(btnCalificarLP);
		grupoBotones.add(btnCerrarS);
		
		panelOeste.add(lblNombre);
		panelOeste.add(lblOpciones);
		panelOeste.add(btnInscribirse);
		panelOeste.add(btnRealizarActividad);
		panelOeste.add(btnVerInscritos);
		panelOeste.add(btnVerProgreso);
		panelOeste.add(btnResenarActividad);
		panelOeste.add(btnCalificarLP);
		panelOeste.add(btnCerrarS);
		
		panelEste = new JPanel();
		
		this.add(panelOeste, BorderLayout.WEST);
		this.add(panelEste, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
        panelEste.removeAll();
        switch (comando) {
            
            case "Inscribirse":
            	inscribirseLP(estudiante);
                break;
            case "Inscritos":
            	verLPInscritos(estudiante);
                break;
            case "Progreso":
            	verProgresoLP(estudiante);
                break;
            /*case "Realizar":
            	realizarActividad(estudiante);
                break;
            case "Resenar":
            	resenarActividad(estudiante);
                break;
            case "Calificar":
            	calificarLP(estudiante);
                break;*/
            case "Close":
            	JOptionPane.showMessageDialog(this, "Sesión cerrada.");
                System.exit(0);
                break;
            default:
                JOptionPane.showMessageDialog(this, "Opción no reconocida.", "Error", JOptionPane.ERROR_MESSAGE);
                break;
        }	
	}
	
	private void inscribirseLP(Estudiante est) {
		panelEste.setLayout(new BorderLayout());
		
		 JLabel lTitulo = new JLabel("Inscribirse a un Learning Path");
		 lTitulo.setHorizontalAlignment(JLabel.CENTER);
		 lTitulo.setFont(new Font("Times New Roman", Font.BOLD, 16));
		 panelEste.add(lTitulo, BorderLayout.NORTH);
		 
		 HashMap<String, LearningPath> mapaLP = aplicacion.getMapaLearningPaths();
		
		 JPanel panelOpciones = new JPanel();
		 panelOpciones.setLayout(new GridLayout(mapaLP.size() + 1, 1));

		 JLabel lblInstrucciones = new JLabel("Seleccione un Learning Path:");
		 panelOpciones.add(lblInstrucciones);

		 ButtonGroup grupoLearningPaths = new ButtonGroup();
		 for (String idLP : mapaLP.keySet()) {
			 JRadioButton rb = new JRadioButton(idLP);
			 grupoLearningPaths.add(rb);
			 panelOpciones.add(rb);
		    }

		 JScrollPane scrollPanel = new JScrollPane(panelOpciones);
		 scrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		 scrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		 panelEste.add(scrollPanel, BorderLayout.CENTER);

		 JPanel panelInferior = new JPanel();
		 panelInferior.setLayout(new BorderLayout());

		 JButton btnConfirmar = new JButton("Confirmar inscripción");
		 panelInferior.add(btnConfirmar, BorderLayout.CENTER);

		 btnConfirmar.addActionListener(new ActionListener() {
			 @Override
		     public void actionPerformed(ActionEvent e) {
				 String seleccionado = null;
				 for (int i = 0; i < panelOpciones.getComponentCount(); i++) {
					 if (panelOpciones.getComponent(i) instanceof JRadioButton) {
						 JRadioButton rb = (JRadioButton) panelOpciones.getComponent(i);
						 if (rb.isSelected()) {
							 seleccionado = rb.getText();
							 break;
		                    }
		                }
		            }

				 if (seleccionado != null) {
		                try {
							aplicacion.inscribirEstudianteLearningPath(estudiante, aplicacion.getLearningPath(seleccionado));
						} catch (ModificarEstudianteLearningPathException e1) {
							e1.printStackTrace();
						}
		                JOptionPane.showMessageDialog(panelEste, 
		                    String.format("Se ha inscrito exitosamente al Learning Path: %s", seleccionado),
		                    "Inscripción exitosa", 
		                    JOptionPane.INFORMATION_MESSAGE);
		            } else {
		                JOptionPane.showMessageDialog(panelEste, 
		                    "Por favor, seleccione un Learning Path para inscribirse.",
		                    "Error", 
		                    JOptionPane.ERROR_MESSAGE);
		            }
		        }
		    });

		    panelEste.add(panelInferior, BorderLayout.SOUTH);

		    panelEste.revalidate();
		    panelEste.repaint();
				 
	}
	
	private void verLPInscritos(Estudiante estudiante) {
	    panelEste.setLayout(new BorderLayout());

	    JLabel lTitulo = new JLabel("Learning Paths Inscritos");
	    lTitulo.setHorizontalAlignment(JLabel.CENTER);
	    lTitulo.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    panelEste.add(lTitulo, BorderLayout.NORTH);

	    Set<String> inscritos = estudiante.getLearningPathsInscritos().keySet();

	    if (inscritos.isEmpty()) {
	        JLabel lblMensaje = new JLabel("No tiene ningún Learning Path inscrito.");
	        lblMensaje.setHorizontalAlignment(JLabel.CENTER);
	        panelEste.add(lblMensaje, BorderLayout.CENTER);
	    } else {
	        JPanel panelLista = new JPanel();
	        panelLista.setLayout(new GridLayout(inscritos.size(), 1));

	        for (String i : inscritos) {
	            JLabel lblLP = new JLabel(i);
	            panelLista.add(lblLP);
	        }

	        JScrollPane scrollPanel = new JScrollPane(panelLista);
	        scrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	        scrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	        
	        panelEste.add(scrollPanel, BorderLayout.CENTER);
	    }

	    panelEste.revalidate();
	    panelEste.repaint();
	}
	
	private void verProgresoLP(Estudiante estudiante) {
	    panelEste.setLayout(new GridLayout(3, 1));

	    JLabel lTitulo = new JLabel("Ver progreso de un Learning Path");
	    lTitulo.setHorizontalAlignment(JLabel.CENTER);
	    lTitulo.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    panelEste.add(lTitulo);

	    Map<String, SeguimientoLearningPath> inscritos = estudiante.getLearningPathsInscritos();

	    if (inscritos.isEmpty()) {
	        JLabel lblMensaje = new JLabel("No tiene ningún Learning Path inscrito.");
	        lblMensaje.setHorizontalAlignment(JLabel.CENTER);
	        panelEste.add(lblMensaje);
	    } else {
	        JPanel panelSeleccion = new JPanel();
	        panelSeleccion.setLayout(new GridLayout(inscritos.size() + 1, 1));

	        JLabel lblSeleccionar = new JLabel("Seleccione un Learning Path:");
	        panelSeleccion.add(lblSeleccionar);

	        JComboBox<String> comboLP = new JComboBox<>();
	        for (String s : inscritos.keySet()) {
	            comboLP.addItem(s);
	        }

	        panelSeleccion.add(comboLP);

	        JButton btnVerProgreso = new JButton("Ver progreso");
	        btnVerProgreso.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                String selectedLP = (String) comboLP.getSelectedItem();
	                if (selectedLP != null) {
	                    mostrarProgresoLP(selectedLP, estudiante);
	                }
	            }
	        });

	        panelSeleccion.add(btnVerProgreso);

	        JScrollPane scrollPanel = new JScrollPane(panelSeleccion);
	        scrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	        scrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

	        panelEste.add(scrollPanel);
	    }

	    panelEste.revalidate();
	    panelEste.repaint();
	}

	private void mostrarProgresoLP(String learningPathSeleccionado, Estudiante estudiante) {
	    panelEste.removeAll(); 

	    JLabel lTitulo = new JLabel("Progreso en el Learning Path: " + learningPathSeleccionado);
	    lTitulo.setHorizontalAlignment(JLabel.CENTER);
	    lTitulo.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    panelEste.add(lTitulo, BorderLayout.NORTH);

	    
	    Map<String, SeguimientoLearningPath> inscritos = estudiante.getLearningPathsInscritos();
	    SeguimientoLearningPath seguimiento = inscritos.get(learningPathSeleccionado);

	    if (seguimiento != null) {
	        float progreso = seguimiento.getProgreso();
	        JLabel lblProgreso = new JLabel(String.format("Progreso: %.2f%%", progreso));
	        panelEste.add(lblProgreso, BorderLayout.CENTER);

	    } else {
	        JLabel lblMensaje = new JLabel("No se encontró el seguimiento para el Learning Path.");
	        lblMensaje.setHorizontalAlignment(JLabel.CENTER);
	        panelEste.add(lblMensaje, BorderLayout.CENTER);
	    }

	    panelEste.revalidate();
	    panelEste.repaint();
	}

}
