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
import javax.swing.SwingUtilities;
import javax.swing.DefaultListCellRenderer;


import actividades.Actividad;
import actividades.Encuesta;
import actividades.Examen;
import actividades.Quiz;
import actividades.RevisarRecurso;
import actividades.Tarea;
import aplicacion.Aplicacion;
import exceptions.ActividadYaCompletadaException;
import exceptions.EstudianteNoInscritoException;
import exceptions.ModificarEstudianteLearningPathException;
import learningPath.LearningPath;
import preguntas.PreguntaAbierta;
import preguntas.PreguntaCerrada;
import preguntas.PreguntaSeleccionMultiple;
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
	
	public PanelOpcionesEstudiante(Estudiante estudiante, Aplicacion aplicacion) {
		super();
		this.estudiante = estudiante;
		this.setLayout(new BorderLayout());
		this.aplicacion = aplicacion;
		
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
	                // Verificar si el estudiante ya está inscrito en el Learning Path
	                if (est.getLearningPathsInscritos().containsKey(seleccionado)) {
	                    JOptionPane.showMessageDialog(panelEste,
	                            String.format("Ya está inscrito en el Learning Path: %s", seleccionado),
	                            "Error de inscripción",
	                            JOptionPane.WARNING_MESSAGE);
	                } else {
	                    try {
	                        aplicacion.inscribirEstudianteLearningPath(estudiante, aplicacion.getLearningPath(seleccionado));
	                        aplicacion.descargarDatos();
	                        JOptionPane.showMessageDialog(panelEste,
	                                String.format("Se ha inscrito exitosamente al Learning Path: %s", seleccionado),
	                                "Inscripción exitosa",
	                                JOptionPane.INFORMATION_MESSAGE);
	                    } catch (ModificarEstudianteLearningPathException e1) {
	                        e1.printStackTrace();
	                    }
	                }
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
	        JLabel lblProgreso = new JLabel(String.format("Progreso: %.2f%%", progreso*100));
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
		SeguimientoLearningPath seguimiento = learningPath.getEstudiantesInscritos().get(estudiante.getLogin());
	    if (seguimiento == null) {
	        JOptionPane.showMessageDialog(panelEste, "No estás inscrito en este Learning Path.", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
		
	    String estado = seguimiento.getMapaSeguimientoActividades().get(actividad.getIdActividad()).getEstado();
	    if (estado.equalsIgnoreCase("Enviado") || estado.equalsIgnoreCase("Completado") || estado.equalsIgnoreCase("Exitoso") || estado.equalsIgnoreCase("No exitoso")) {
	        JOptionPane.showMessageDialog(panelEste, "Ya has completado esta actividad. No puedes realizarla de nuevo.", "Error", JOptionPane.ERROR_MESSAGE);
	        return; 
	    }
		
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
	    recursoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

	    JLabel lblRecurso = new JLabel("Tipo de recurso: " + recurso.getTipoRecurso());
	    lblRecurso.setAlignmentX(Component.CENTER_ALIGNMENT);
	    recursoPanel.add(lblRecurso);

	    JLabel lblLink = new JLabel("Enlace: " + recurso.getEnlaceRecurso());
	    lblLink.setAlignmentX(Component.CENTER_ALIGNMENT);
	    recursoPanel.add(lblLink);

	    JButton btnCompletarRecurso = new JButton("Marcar como completado");
	    btnCompletarRecurso.setAlignmentX(Component.CENTER_ALIGNMENT);
	    recursoPanel.add(Box.createVerticalStrut(10));
	    recursoPanel.add(btnCompletarRecurso);

	    JFrame recursoFrame = new JFrame("Realizar Recurso");
	    recursoFrame.add(recursoPanel);
	    recursoFrame.setSize(400, 300);
	    recursoFrame.setLocationRelativeTo(null); 
	    recursoFrame.setVisible(true);

	    btnCompletarRecurso.addActionListener(e -> {
	        try {
	            aplicacion.completarEncuestaRecurso(actividad, estudiante, learningPath); // Marca como completado
	            aplicacion.descargarDatos(); 
	            JOptionPane.showMessageDialog(recursoFrame, "Recurso marcado como completado.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	        } catch (Exception ex) {
	            JOptionPane.showMessageDialog(recursoFrame, "Error al completar el recurso: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	            ex.printStackTrace();
	        }
	        recursoFrame.dispose();
	    });
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
	        boolean respuestasCompletas = true;

	        for (PreguntaAbierta pregunta : encuesta.getPreguntas()) {
	            String respuesta = ""; 
	            try {
	                aplicacion.responderPreguntaEncuesta(encuesta, estudiante, learningPath, pregunta, respuesta);
	            } catch (EstudianteNoInscritoException | ActividadYaCompletadaException ex) {
	                respuestasCompletas = false;
	                ex.printStackTrace();
	            }
	        }

	        if (respuestasCompletas) {
	            try {
	                aplicacion.completarEncuestaRecurso(encuesta, estudiante, learningPath);
	                aplicacion.descargarDatos(); 
	                JOptionPane.showMessageDialog(encuestaPanel, "Respuestas enviadas y actividad completada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	            } catch (EstudianteNoInscritoException | ActividadYaCompletadaException ex) {
	                ex.printStackTrace();
	            }
	        } else {
	            JOptionPane.showMessageDialog(encuestaPanel, "Hubo un error al registrar tus respuestas. Por favor, intenta nuevamente.", "Error", JOptionPane.ERROR_MESSAGE);
	        }

	        ((JFrame) SwingUtilities.getWindowAncestor(encuestaPanel)).dispose();
	    });

	    encuestaPanel.add(btnCompletarEncuesta);

	    JFrame encuestaFrame = new JFrame("Realizar Encuesta");
	    encuestaFrame.add(encuestaPanel);
	    encuestaFrame.setSize(400, 300);
	    encuestaFrame.setVisible(true);
	}

	private void mostrarQuiz(Actividad actividad, Estudiante estudiante, LearningPath learningPath) {
	    Quiz quiz = (Quiz) actividad;
	    JPanel quizPanel = new JPanel();
	    quizPanel.setLayout(new BoxLayout(quizPanel, BoxLayout.Y_AXIS));
	    quizPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

	    JLabel lblTitulo = new JLabel("Realizar Quiz: " + quiz.getTitulo());
	    lblTitulo.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
	    quizPanel.add(lblTitulo);

	    Map<PreguntaCerrada, Integer> respuestas = new HashMap<>();

	    for (PreguntaCerrada pregunta : quiz.getPreguntas()) {
	        JPanel preguntaPanel = new JPanel();
	        preguntaPanel.setLayout(new BoxLayout(preguntaPanel, BoxLayout.Y_AXIS));
	        preguntaPanel.setBorder(BorderFactory.createTitledBorder(pregunta.getEnunciado()));

	        ButtonGroup grupoOpciones = new ButtonGroup();
	        if (pregunta instanceof PreguntaSeleccionMultiple) {
	            PreguntaSeleccionMultiple preguntaSeleccionMultiple = (PreguntaSeleccionMultiple) pregunta;

	            JRadioButton opcion1 = new JRadioButton(preguntaSeleccionMultiple.getOpcion1());
	            JRadioButton opcion2 = new JRadioButton(preguntaSeleccionMultiple.getOpcion2());
	            JRadioButton opcion3 = new JRadioButton(preguntaSeleccionMultiple.getOpcion3());
	            JRadioButton opcion4 = new JRadioButton(preguntaSeleccionMultiple.getOpcion4());

	            grupoOpciones.add(opcion1);
	            grupoOpciones.add(opcion2);
	            grupoOpciones.add(opcion3);
	            grupoOpciones.add(opcion4);

	            preguntaPanel.add(opcion1);
	            preguntaPanel.add(opcion2);
	            preguntaPanel.add(opcion3);
	            preguntaPanel.add(opcion4);

	            opcion1.addActionListener(e -> respuestas.put(pregunta, 1));
	            opcion2.addActionListener(e -> respuestas.put(pregunta, 2));
	            opcion3.addActionListener(e -> respuestas.put(pregunta, 3));
	            opcion4.addActionListener(e -> respuestas.put(pregunta, 4));

	        } else {
	            JRadioButton verdadero = new JRadioButton("Verdadero");
	            JRadioButton falso = new JRadioButton("Falso");

	            grupoOpciones.add(verdadero);
	            grupoOpciones.add(falso);

	            preguntaPanel.add(verdadero);
	            preguntaPanel.add(falso);

	            verdadero.addActionListener(e -> respuestas.put(pregunta, 1));
	            falso.addActionListener(e -> respuestas.put(pregunta, 0));
	        }

	        quizPanel.add(preguntaPanel);
	    }

	    JButton btnEnviarQuiz = new JButton("Enviar Quiz");
	    btnEnviarQuiz.setAlignmentX(Component.CENTER_ALIGNMENT);
	    quizPanel.add(Box.createVerticalStrut(10));
	    quizPanel.add(btnEnviarQuiz);

	    JFrame quizFrame = new JFrame("Realizar Quiz");
	    quizFrame.add(new JScrollPane(quizPanel));
	    quizFrame.setSize(600, 400);
	    quizFrame.setLocationRelativeTo(null); 
	    quizFrame.setVisible(true);

	    btnEnviarQuiz.addActionListener(e -> {
	        try {
	            
	            if (respuestas.size() < quiz.getPreguntas().size()) {
	                JOptionPane.showMessageDialog(quizFrame, "Debe responder todas las preguntas.", "Error", JOptionPane.ERROR_MESSAGE);
	                return;
	            }

	            for (Map.Entry<PreguntaCerrada, Integer> entry : respuestas.entrySet()) {
	                aplicacion.responderPreguntaQuiz(quiz, estudiante, learningPath, entry.getKey(), entry.getValue());
	            }

	            learningPath.getEstudiantesInscritos().get(estudiante.getLogin())
	                .getMapaSeguimientoActividades().get(actividad.getIdActividad()).setEstado("completado");
	            aplicacion.descargarDatos();

	            JOptionPane.showMessageDialog(quizFrame, "Quiz enviado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	        } catch (Exception ex) {
	            JOptionPane.showMessageDialog(quizFrame, "Error al enviar el Quiz: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	            ex.printStackTrace();
	        }

	        quizFrame.dispose(); 
	    });
	}


	private void mostrarExamen(Actividad actividad, Estudiante estudiante, LearningPath learningPath) {
	    Examen examen = (Examen) actividad;
	    JPanel examenPanel = new JPanel();
	    examenPanel.setLayout(new BoxLayout(examenPanel, BoxLayout.Y_AXIS));

	    for (PreguntaAbierta pregunta : examen.getPreguntas()) {
	        JLabel lblPregunta = new JLabel(pregunta.getEnunciado());
	        JTextArea textAreaRespuesta = new JTextArea(3, 20);
	        textAreaRespuesta.setLineWrap(true);
	        textAreaRespuesta.setWrapStyleWord(true);
	        textAreaRespuesta.setBorder(BorderFactory.createLineBorder(Color.GRAY));

	        examenPanel.add(lblPregunta);
	        examenPanel.add(new JScrollPane(textAreaRespuesta));
	    }


	    JButton btnEnviarExamen = new JButton("Enviar examen");
	    btnEnviarExamen.addActionListener(e -> {
	        boolean respuestasCompletas = true;

	        for (PreguntaAbierta pregunta : examen.getPreguntas()) {
	            String respuesta = ""; 
	            try {
	                aplicacion.responderPreguntaExamen(examen, estudiante, learningPath, pregunta, respuesta);
	            } catch (EstudianteNoInscritoException | ActividadYaCompletadaException ex) {
	                respuestasCompletas = false;
	                ex.printStackTrace();
	            }
	        }

	        if (respuestasCompletas) {
	            try {
	                aplicacion.enviarExamen(examen, estudiante, learningPath); 
	           
	                aplicacion.descargarDatos(); 
	                JOptionPane.showMessageDialog(examenPanel, "Examen enviado y guardado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	            } catch (EstudianteNoInscritoException | ActividadYaCompletadaException ex) {
	                JOptionPane.showMessageDialog(examenPanel, "Hubo un error al enviar el examen. Por favor, intenta nuevamente.", "Error", JOptionPane.ERROR_MESSAGE);
	                ex.printStackTrace();
	            }
	        } else {
	            JOptionPane.showMessageDialog(examenPanel, "Por favor, responde todas las preguntas antes de enviar el examen.", "Error", JOptionPane.WARNING_MESSAGE);
	        }

	        ((JFrame) SwingUtilities.getWindowAncestor(examenPanel)).dispose();
	    });


	    examenPanel.add(btnEnviarExamen);

	    JFrame examenFrame = new JFrame("Realizar Examen");
	    examenFrame.add(examenPanel);
	    examenFrame.setSize(400, 300);
	    examenFrame.setVisible(true);
	}

	private void mostrarTarea(Actividad actividad, Estudiante estudiante, LearningPath learningPath) {
	    Tarea tarea = (Tarea) actividad;
	    JPanel tareaPanel = new JPanel();
	    tareaPanel.setLayout(new BoxLayout(tareaPanel, BoxLayout.Y_AXIS));
	    tareaPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

	    JLabel lblTitulo = new JLabel("Título: " + tarea.getTitulo());
	    lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
	    tareaPanel.add(lblTitulo);

	    JLabel lblDescripcion = new JLabel("Descripción: " + tarea.getDescripcion());
	    lblDescripcion.setAlignmentX(Component.CENTER_ALIGNMENT);
	    tareaPanel.add(lblDescripcion);

	    JLabel lblMetodoEnvio = new JLabel("Ingrese el método de envío:");
	    lblMetodoEnvio.setAlignmentX(Component.CENTER_ALIGNMENT);
	    tareaPanel.add(lblMetodoEnvio);

	    JTextArea txtMetodoEnvio = new JTextArea(1, 20);
	    txtMetodoEnvio.setLineWrap(true);
	    txtMetodoEnvio.setWrapStyleWord(true);
	    txtMetodoEnvio.setBorder(BorderFactory.createLineBorder(Color.GRAY));
	    tareaPanel.add(txtMetodoEnvio);

	    JButton btnEnviarTarea = new JButton("Enviar tarea");
	    btnEnviarTarea.setAlignmentX(Component.CENTER_ALIGNMENT);
	    tareaPanel.add(Box.createVerticalStrut(10)); 
	    tareaPanel.add(btnEnviarTarea);

	    JFrame tareaFrame = new JFrame("Realizar Tarea");
	    tareaFrame.add(tareaPanel);
	    tareaFrame.setSize(400, 300);
	    tareaFrame.setLocationRelativeTo(null); 
	    tareaFrame.setVisible(true);

	    btnEnviarTarea.addActionListener(e -> {
	        String metodoEnvio = txtMetodoEnvio.getText().trim();
	        if (metodoEnvio.isEmpty()) {
	            JOptionPane.showMessageDialog(tareaFrame, "Debe ingresar un método de envío.", "Error", JOptionPane.ERROR_MESSAGE);
	            return;
	        }

	        try {
	            aplicacion.enviarTarea(tarea, estudiante, learningPath); 
	            aplicacion.actualizarMetodoEnvioTarea(estudiante, learningPath, tarea, metodoEnvio);
	            aplicacion.descargarDatos(); 
	            JOptionPane.showMessageDialog(tareaFrame, "Tarea enviada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	        } catch (Exception ex) {
	            JOptionPane.showMessageDialog(tareaFrame, "Error al enviar la tarea: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	            ex.printStackTrace();
	        }
	        tareaFrame.dispose();
	    });
	}
	
}
