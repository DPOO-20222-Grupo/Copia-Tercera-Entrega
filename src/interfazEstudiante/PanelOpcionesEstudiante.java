package interfazEstudiante;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.DefaultListCellRenderer;


import actividades.Actividad;
import actividades.Encuesta;
import actividades.Examen;
import actividades.Quiz;
import actividades.RevisarRecurso;
import actividades.Tarea;
import exceptions.ActividadYaCompletadaException;
import exceptions.EstudianteNoInscritoException;
import exceptions.ModificarEstudianteLearningPathException;
import interfaz.Aplicacion;
import learningPath.LearningPath;
import preguntas.PreguntaAbierta;
import preguntas.PreguntaCerrada;
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
		this.aplicacion = new Aplicacion("usuarios.json", "lp.json", "preguntas.json", "actividades.json", "cifrasActividades.json");
		
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
            case "Calificar":
            	calificarLP(estudiante);
                break;
            case "Resenar":
            	resenarActividad(estudiante);
                break;
            case "Realizar":
            	realizarActividad(estudiante);
                break;
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
							aplicacion.descargarDatos();
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
	
	private void calificarLP(Estudiante estudiante) {
	    panelEste.setLayout(new BorderLayout());

	    JLabel lTitulo = new JLabel("Calificar un Learning Path");
	    lTitulo.setHorizontalAlignment(JLabel.CENTER);
	    lTitulo.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    panelEste.add(lTitulo, BorderLayout.NORTH);

	    Map<String, SeguimientoLearningPath> inscritos = estudiante.getLearningPathsInscritos();

	    if (inscritos.isEmpty()) {
	        JLabel lblMensaje = new JLabel("No tiene ningún Learning Path inscrito.");
	        lblMensaje.setHorizontalAlignment(JLabel.CENTER);
	        panelEste.add(lblMensaje, BorderLayout.CENTER);
	    } else {
	        JPanel panelSeleccion = new JPanel();
	        panelSeleccion.setLayout(new BoxLayout(panelSeleccion, BoxLayout.Y_AXIS));

	        JLabel lblSeleccionar = new JLabel("Seleccione un Learning Path:");
	        panelSeleccion.add(lblSeleccionar);

	        JComboBox<String> comboLP = new JComboBox<>();
	        for (String s : inscritos.keySet()) {
	            comboLP.addItem(s);
	        }

	        panelSeleccion.add(comboLP);

	        JPanel panelCalificacion = new JPanel();
	        panelCalificacion.setLayout(new GridLayout(1, 1));

	        JLabel lblCalificacion = new JLabel("Seleccione una calificación (1-5):");
	        panelCalificacion.add(lblCalificacion);

	        JComboBox<Integer> comboCalificacion = new JComboBox<>();
	        for (int i = 1; i <= 5; i++) {
	            comboCalificacion.addItem(i);
	        }

	        panelCalificacion.add(comboCalificacion);

	        JButton btnCalificar = new JButton("Calificar Learning Path");
	        btnCalificar.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                String selectedLP = (String) comboLP.getSelectedItem();
	                Integer calificacion = (Integer) comboCalificacion.getSelectedItem();

	                if (selectedLP != null && calificacion != null) {
	                    calificarLearningPath(selectedLP, calificacion, estudiante);
	                }
	            }
	        });

	        panelSeleccion.add(panelCalificacion);
	        panelSeleccion.add(btnCalificar);

	        JScrollPane scrollPanel = new JScrollPane(panelSeleccion);
	        scrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	        scrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

	        panelEste.add(scrollPanel, BorderLayout.CENTER);
	    }

	    panelEste.revalidate();
	    panelEste.repaint();
	}

	private void calificarLearningPath(String learningPathSeleccionado, int calificacion, Estudiante estudiante) {
	    panelEste.removeAll();

	    JLabel lTitulo = new JLabel("Calificación para el Learning Path: " + learningPathSeleccionado);
	    lTitulo.setHorizontalAlignment(JLabel.CENTER);
	    lTitulo.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    panelEste.add(lTitulo, BorderLayout.NORTH);
	    double calif = (double) calificacion; 

	    LearningPath lp = aplicacion.getLearningPath(learningPathSeleccionado);

	    if (lp != null) {
	        lp.actualizarRating(calif);
	        aplicacion.descargarDatos();

	        JLabel lblConfirmacion = new JLabel(String.format("Calificación asignada: %d", calificacion));
	        panelEste.add(lblConfirmacion, BorderLayout.CENTER);

	        JOptionPane.showMessageDialog(panelEste,
	                String.format("Se ha calificado exitosamente el Learning Path '%s' con un puntaje de %d.",
	                        learningPathSeleccionado, calificacion),
	                "Calificación exitosa",
	                JOptionPane.INFORMATION_MESSAGE);
	    } else {
	        JLabel lblMensaje = new JLabel("No se encontró el Learning Path.");
	        lblMensaje.setHorizontalAlignment(JLabel.CENTER);
	        panelEste.add(lblMensaje, BorderLayout.CENTER);
	    }

	    panelEste.revalidate();
	    panelEste.repaint();
	}
	
	private void resenarActividad(Estudiante estudiante) {
	    panelEste.setLayout(new BorderLayout());

	    JLabel lTitulo = new JLabel("Reseñar una actividad");
	    lTitulo.setHorizontalAlignment(JLabel.CENTER);
	    lTitulo.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    panelEste.add(lTitulo, BorderLayout.NORTH);

	    List<Actividad> actividades = new ArrayList<>();

	    Map<String, SeguimientoLearningPath> mapa = estudiante.getLearningPathsInscritos();

	    for (String id : mapa.keySet()) {
	        List<Actividad> actividadSeg = aplicacion.getMapaLearningPaths().get(id).getActividades();
	        actividades.addAll(actividadSeg);
	    }

	    if (actividades.isEmpty()) {
	        JLabel lblMensaje = new JLabel("No tiene actividades realizadas.");
	        lblMensaje.setHorizontalAlignment(JLabel.CENTER);
	        panelEste.add(lblMensaje, BorderLayout.CENTER);
	    } else {
	        JPanel panelSeleccion = new JPanel();
	        panelSeleccion.setLayout(new BoxLayout(panelSeleccion, BoxLayout.Y_AXIS));

	        JLabel lblSeleccionar = new JLabel("Seleccione una actividad:");
	        panelSeleccion.add(lblSeleccionar);
	        JComboBox<Actividad> comboActividad = new JComboBox<>();
	        for (Actividad a : actividades) {
	            comboActividad.addItem(a); 
	        }

	        comboActividad.setRenderer(new DefaultListCellRenderer() {
	            @Override
	            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
	                if (value instanceof Actividad) {
	                    value = ((Actividad) value).getTitulo();
	                }
	                return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
	            }
	        });

	        panelSeleccion.add(comboActividad);

	        panelSeleccion.add(Box.createVerticalStrut(20));

	        JLabel lblResena = new JLabel("Escriba su reseña:");
	        panelSeleccion.add(lblResena);

	        JTextArea textAreaResena = new JTextArea(5, 20);
	        textAreaResena.setLineWrap(true);
	        textAreaResena.setWrapStyleWord(true);
	        textAreaResena.setBorder(BorderFactory.createLineBorder(Color.GRAY));
	        JScrollPane scrollResena = new JScrollPane(textAreaResena);
	        panelSeleccion.add(scrollResena);

	        JLabel lblCalificacion = new JLabel("Califique la actividad (1 a 5):");
	        panelSeleccion.add(lblCalificacion);

	        JComboBox<Integer> comboCalificacion = new JComboBox<>();
	        for (int i = 1; i <= 5; i++) {
	            comboCalificacion.addItem(i);
	        }
	        panelSeleccion.add(comboCalificacion);

	        panelSeleccion.add(Box.createVerticalStrut(20));

	        JButton btnResenar = new JButton("Guardar reseña y calificación");
	        btnResenar.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                Actividad selectedActividad = (Actividad) comboActividad.getSelectedItem();
	                String resena = textAreaResena.getText().trim();
	                Integer calificacion = (Integer) comboCalificacion.getSelectedItem();

	                if (selectedActividad != null && !resena.isEmpty() && calificacion != null) {
	                    guardarResenaYCalificacion(selectedActividad, resena, calificacion, estudiante);
	                } else {
	                    JOptionPane.showMessageDialog(panelEste,
	                            "Por favor, seleccione una actividad, escriba una reseña y califique la actividad.",
	                            "Error",
	                            JOptionPane.ERROR_MESSAGE);
	                }
	            }
	        });

	        panelSeleccion.add(btnResenar);

	        JScrollPane scrollPanel = new JScrollPane(panelSeleccion);
	        scrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	        scrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

	        panelEste.add(scrollPanel, BorderLayout.CENTER);
	    }

	    panelEste.revalidate();
	    panelEste.repaint();
	}

	private void guardarResenaYCalificacion(Actividad actividadSeleccionada, String resena, Integer calificacion, Estudiante estudiante) {
	    panelEste.removeAll();

	    JLabel lTitulo = new JLabel("Reseña para la actividad: " + actividadSeleccionada.getTitulo());
	    lTitulo.setHorizontalAlignment(JLabel.CENTER);
	    lTitulo.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    panelEste.add(lTitulo, BorderLayout.NORTH);

	    if (actividadSeleccionada != null) {
	        actividadSeleccionada.agregarResena(resena);
	        actividadSeleccionada.actualizarRating(calificacion); 
	        aplicacion.descargarDatos();
	        JLabel lblConfirmacion = new JLabel("Reseña y calificación guardadas exitosamente.");
	        panelEste.add(lblConfirmacion, BorderLayout.CENTER);

	        JOptionPane.showMessageDialog(panelEste,
	                String.format("La reseña y la calificación para la actividad '%s' han sido guardadas.",
	                        actividadSeleccionada.getTitulo()),
	                "Reseña y calificación guardadas",
	                JOptionPane.INFORMATION_MESSAGE);
	    }
	    panelEste.revalidate();
	    panelEste.repaint();
	}
	
	private void realizarActividad(Estudiante estudiante) {
	    panelEste.setLayout(new BorderLayout());

	    JLabel lTitulo = new JLabel("Seleccionar y realizar actividad");
	    lTitulo.setHorizontalAlignment(JLabel.CENTER);
	    lTitulo.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    panelEste.add(lTitulo, BorderLayout.NORTH);

	    Map<String, SeguimientoLearningPath> mapa = estudiante.getLearningPathsInscritos();
	    if (mapa.isEmpty()) {
	        JLabel lblMensaje = new JLabel("No tiene Learning Paths inscritos.");
	        lblMensaje.setHorizontalAlignment(JLabel.CENTER);
	        panelEste.add(lblMensaje, BorderLayout.CENTER);
	    } else {
	        JPanel panelSeleccion = new JPanel();
	        panelSeleccion.setLayout(new BoxLayout(panelSeleccion, BoxLayout.Y_AXIS));

	        JLabel lblSeleccionar = new JLabel("Seleccione un Learning Path:");
	        panelSeleccion.add(lblSeleccionar);

	        JComboBox<LearningPath> comboLearningPath = new JComboBox<>();
	        for (String id : mapa.keySet()) {
	            comboLearningPath.addItem(aplicacion.getMapaLearningPaths().get(id)); 
	        }

	        comboLearningPath.setRenderer(new DefaultListCellRenderer() {
	            @Override
	            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
	                if (value instanceof LearningPath) {
	                    value = ((LearningPath) value).getTitulo();
	                }
	                return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
	            }
	        });

	        panelSeleccion.add(comboLearningPath);
	        panelSeleccion.add(Box.createVerticalStrut(20));

	        JLabel lblActividades = new JLabel("Seleccione una actividad:");
	        panelSeleccion.add(lblActividades);

	        JComboBox<Actividad> comboActividad = new JComboBox<>();
	        panelSeleccion.add(comboActividad);

	        comboLearningPath.addActionListener(e -> {
	            LearningPath selectedLearningPath = (LearningPath) comboLearningPath.getSelectedItem();
	            if (selectedLearningPath != null) {
	                comboActividad.removeAllItems();
	                for (Actividad actividad : selectedLearningPath.getActividades()) {
	                    comboActividad.addItem(actividad);
	                }
	            }
	        });

	        panelSeleccion.add(Box.createVerticalStrut(20));

	        JButton btnRealizar = new JButton("Realizar actividad");
	        btnRealizar.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                Actividad selectedActividad = (Actividad) comboActividad.getSelectedItem();
	                if (selectedActividad != null) {
	                    realizarActividadSeleccionada(selectedActividad, estudiante, (LearningPath) comboLearningPath.getSelectedItem());
	                } else {
	                    JOptionPane.showMessageDialog(panelEste, "Por favor, seleccione una actividad.", "Error", JOptionPane.ERROR_MESSAGE);
	                }
	            }
	        });

	        panelSeleccion.add(btnRealizar);

	        JScrollPane scrollPanel = new JScrollPane(panelSeleccion);
	        scrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	        scrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

	        panelEste.add(scrollPanel, BorderLayout.CENTER);
	    }

	    panelEste.revalidate();
	    panelEste.repaint();
	}

	private void realizarActividadSeleccionada(Actividad actividad, Estudiante estudiante, LearningPath learningPath) {
	    if (actividad instanceof RevisarRecurso) {
	        mostrarRecurso(actividad, estudiante, learningPath);
	    } else if (actividad instanceof Encuesta) {
	        mostrarEncuesta(actividad, estudiante, learningPath);
	    } else if (actividad instanceof Quiz) {
	        mostrarQuiz(actividad, estudiante, learningPath);
	    } else if (actividad instanceof Examen) {
	        mostrarExamen(actividad, estudiante, learningPath);
	    } else if (actividad instanceof Tarea) {
	        mostrarTarea(actividad, estudiante, learningPath);
	    }
	}

	private void mostrarRecurso(Actividad actividad, Estudiante estudiante, LearningPath learningPath) {
	    RevisarRecurso recurso = (RevisarRecurso) actividad;
	    JPanel recursoPanel = new JPanel();
	    recursoPanel.setLayout(new BoxLayout(recursoPanel, BoxLayout.Y_AXIS));

	    JLabel lblRecurso = new JLabel("Tipo de recurso: " + recurso.getTipoRecurso());
	    recursoPanel.add(lblRecurso);

	    JLabel lblLink = new JLabel("Enlace: " + recurso.getEnlaceRecurso());
	    recursoPanel.add(lblLink);

	    JButton btnCompletarRecurso = new JButton("Marcar como completado");
	    btnCompletarRecurso.addActionListener(e -> {
	        try {
				aplicacion.completarEncuestaRecurso(actividad, estudiante, learningPath);
			} catch (EstudianteNoInscritoException | ActividadYaCompletadaException e1) {
				e1.printStackTrace();
			}
	    });

	    recursoPanel.add(btnCompletarRecurso);

	    JFrame recursoFrame = new JFrame("Realizar Recurso");
	    recursoFrame.add(recursoPanel);
	    recursoFrame.setSize(400, 300);
	    recursoFrame.setVisible(true);
	}

	private void mostrarEncuesta(Actividad actividad, Estudiante estudiante, LearningPath learningPath) {
	    Encuesta encuesta = (Encuesta) actividad;
	    JPanel encuestaPanel = new JPanel();
	    encuestaPanel.setLayout(new BoxLayout(encuestaPanel, BoxLayout.Y_AXIS));

	    for (PreguntaAbierta pregunta : encuesta.getPreguntas()) {
	        JLabel lblPregunta = new JLabel(pregunta.getEnunciado());
	        encuestaPanel.add(lblPregunta);
	        JTextArea textAreaRespuesta = new JTextArea(3, 20);
	        encuestaPanel.add(new JScrollPane(textAreaRespuesta));
	    }

	    JButton btnCompletarEncuesta = new JButton("Enviar respuestas");
	    btnCompletarEncuesta.addActionListener(e -> {
	        for (PreguntaAbierta pregunta : encuesta.getPreguntas()) {
	            String respuesta = "";
	            try {
					aplicacion.responderPreguntaEncuesta(encuesta, estudiante, learningPath, pregunta, respuesta);
				} catch (EstudianteNoInscritoException | ActividadYaCompletadaException e1) {
					e1.printStackTrace();
				}
	        }
	        try {
				aplicacion.completarEncuestaRecurso(actividad, estudiante, learningPath);
			} catch (EstudianteNoInscritoException e1) {
				e1.printStackTrace();
			} catch (ActividadYaCompletadaException e1) {
				e1.printStackTrace();
			}
	    });

	    encuestaPanel.add(btnCompletarEncuesta);

	    JFrame encuestaFrame = new JFrame("Realizar Encuesta");
	    encuestaFrame.add(encuestaPanel);
	    encuestaFrame.setSize(400, 300);
	    encuestaFrame.setVisible(true);
	}

	private void mostrarQuiz(Actividad actividad, Estudiante estudiante, LearningPath learningPath) {
	    // Mostrar las preguntas del quiz y permitir responderlas
	    Quiz quiz = (Quiz) actividad;
	    JPanel quizPanel = new JPanel();
	    quizPanel.setLayout(new BoxLayout(quizPanel, BoxLayout.Y_AXIS));

	    for (PreguntaCerrada pregunta : quiz.getPreguntas()) {
	        JLabel lblPregunta = new JLabel(pregunta.getEnunciado());
	        quizPanel.add(lblPregunta);
	        // Agregar opciones de respuesta (por ejemplo, botones de radio)
	    }

	    JButton btnCompletarQuiz = new JButton("Enviar respuestas");
	    btnCompletarQuiz.addActionListener(e -> {
	        // Aquí se debe recorrer las preguntas y obtener las respuestas para llamar a responderPreguntaQuiz()
	        for (PreguntaCerrada pregunta : quiz.getPreguntas()) {
	            int respuesta = 0; // Obtener respuesta seleccionada
	            try {
					aplicacion.responderPreguntaQuiz(quiz, estudiante, learningPath, pregunta, respuesta);
				} catch (EstudianteNoInscritoException e1) {
					e1.printStackTrace();
				}
	        }
	    });

	    quizPanel.add(btnCompletarQuiz);

	    JFrame quizFrame = new JFrame("Realizar Quiz");
	    quizFrame.add(quizPanel);
	    quizFrame.setSize(400, 300);
	    quizFrame.setVisible(true);
	}

	private void mostrarExamen(Actividad actividad, Estudiante estudiante, LearningPath learningPath) {
	    // Mostrar las preguntas del examen y permitir responderlas
	    Examen examen = (Examen) actividad;
	    JPanel examenPanel = new JPanel();
	    examenPanel.setLayout(new BoxLayout(examenPanel, BoxLayout.Y_AXIS));

	    for (PreguntaAbierta pregunta : examen.getPreguntas()) {
	        JLabel lblPregunta = new JLabel(pregunta.getEnunciado());
	        examenPanel.add(lblPregunta);
	        JTextArea textAreaRespuesta = new JTextArea(3, 20);
	        examenPanel.add(new JScrollPane(textAreaRespuesta));
	    }

	    JButton btnEnviarExamen = new JButton("Enviar examen");
	    btnEnviarExamen.addActionListener(e -> {
	        for (PreguntaAbierta pregunta : examen.getPreguntas()) {
	            String respuesta = "";// Obtener respuesta del JTextArea correspondiente
	            try {
					aplicacion.responderPreguntaExamen(examen, estudiante, learningPath, pregunta, respuesta);
				} catch (EstudianteNoInscritoException | ActividadYaCompletadaException e1) {
					e1.printStackTrace();
				}
	        }
	        try {
				aplicacion.enviarExamen(examen, estudiante, learningPath);
			} catch (EstudianteNoInscritoException | ActividadYaCompletadaException e1) {
				e1.printStackTrace();
			}
	    });

	    examenPanel.add(btnEnviarExamen);

	    JFrame examenFrame = new JFrame("Realizar Examen");
	    examenFrame.add(examenPanel);
	    examenFrame.setSize(400, 300);
	    examenFrame.setVisible(true);
	}

	private void mostrarTarea(Actividad actividad, Estudiante estudiante, LearningPath learningPath) {
	    // Mostrar la tarea y permitir enviarla
	    Tarea tarea = (Tarea) actividad;
	    JPanel tareaPanel = new JPanel();
	    tareaPanel.setLayout(new BoxLayout(tareaPanel, BoxLayout.Y_AXIS));

	    JLabel lblTarea = new JLabel("Descripción de la tarea: " + tarea.getDescripcion());
	    tareaPanel.add(lblTarea);

	    JButton btnEnviarTarea = new JButton("Enviar tarea");
	    btnEnviarTarea.addActionListener(e -> {
	        try {
				aplicacion.enviarTarea(tarea, estudiante, learningPath);
			} catch (EstudianteNoInscritoException | ActividadYaCompletadaException e1) {
				e1.printStackTrace();
			}
	    });

	    tareaPanel.add(btnEnviarTarea);

	    JFrame tareaFrame = new JFrame("Realizar Tarea");
	    tareaFrame.add(tareaPanel);
	    tareaFrame.setSize(400, 300);
	    tareaFrame.setVisible(true);
	}


}
