package interfazProfesor;

import java.awt.*; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.*;

import Consolas.ProfesorConsole;
import actividades.Actividad;
import exceptions.ActividadYaExistenteException;
import exceptions.LearningPathYaExistenteException;
import interfaz.Aplicacion;
import learningPath.LearningPath;
import preguntas.PreguntaAbierta;
import preguntas.PreguntaCerrada;
import preguntas.PreguntaSeleccionMultiple;
import user.Profesor;

@SuppressWarnings("serial")
public class PanelOpcionesProfesor extends JPanel implements ActionListener {

    private Profesor profesor;
    private Aplicacion aplicacion;
    private JPanel panelCentro;

    private static final String[] OPCIONES = {
            "Crear una actividad para revisar un recurso",
            "Crear una tarea",
            "Crear un quiz",
            "Crear un examen",
            "Crear una encuesta",
            "Crear un Learning Path",
            "Crear una pregunta",
            "Clonar Actividad",
            "Clonar Learning Path",
            "Modificar Learning Path",
            "Modificar Actividad",
            "Calificar Actividad",
            "Modificar una pregunta",
            "Revisar si una actividad ya existe",
            "Revisar si un Learning Path ya existe",
            "Ver mis actividades",
            "Ver mis Learning Paths",
            "Ver mis Preguntas",
            "Ver el progreso de un estudiante en un Learning Path",
            "Reseñar o calificar una actividad",
            "Calificar un Learning Path",
            "Ver calificación de un Learning Path",
            "Ver reseñas y calificación de una actividad",
            "Cerrar sesión"
    };

    public PanelOpcionesProfesor(Profesor profesor) {
    	aplicacion = new Aplicacion("usuarios.json", "lp.json", "preguntas.json", "actividades.json");
        this.profesor = profesor;
        this.setLayout(new BorderLayout());

        JLabel lblNombre = new JLabel("Bienvenido a la Aplicación de Profesores: " + profesor.getNombre(), JLabel.CENTER);
        lblNombre.setFont(new Font("Times New Roman", Font.BOLD, 20));
        this.add(lblNombre, BorderLayout.NORTH);

        JPanel panelOpciones = new JPanel();
        panelOpciones.setLayout(new GridLayout(OPCIONES.length, 1));

        ButtonGroup grupoBotones = new ButtonGroup();
        for (String opcion : OPCIONES) {
            JRadioButton btnOpcion = new JRadioButton(opcion);
            btnOpcion.setFont(new Font("Times New Roman", Font.BOLD, 15));
            btnOpcion.setActionCommand(opcion);
            btnOpcion.addActionListener(this);
            grupoBotones.add(btnOpcion);
            panelOpciones.add(btnOpcion);
        }

        this.add(new JScrollPane(panelOpciones), BorderLayout.WEST);
        
        panelCentro = new JPanel();
        this.add(panelCentro, BorderLayout.CENTER);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        panelCentro.removeAll();
        switch (comando) {
            case "Crear una actividad para revisar un recurso":
            	crearRevisarRecurso(profesor);
                break;
            case "Crear una tarea":
            	crearTarea(profesor);
                break;
            case "Crear un quiz":
            	crearQuiz(profesor);
                break;
            case "Crear un examen":
            	crearExamen(profesor);
                break;
            case "Crear una encuesta":
            	crearEncuesta(profesor);
                break;
            case "Crear un Learning Path":
            	crearLearningPath(profesor);
                break;
            case "Crear una pregunta":
            	crearPregunta(profesor);
                break;
            case "Clonar Actividad":
            	clonarActividad(profesor);
                break;
            case "Clonar Learning Path":
            	clonarLearningPath(profesor);
                break;
            case "Modificar Learning Path":
            	modificarLearningPath(profesor);
                break;
            case "Modificar Actividad":
            	modificarActividad(profesor);
                break;
            case "Calificar Actividad":
            	calificarActividad(profesor);
                break;
            case "Modificar una pregunta":
            	modificarPregunta(profesor);
                break;
            case "Revisar si una actividad ya existe":
            	revisarActividadRepetida(profesor);
                break;
            case "Revisar si un Learning Path ya existe":
            	revisarLearningPathRepetido(profesor);
                break;
            case "Ver mis actividades":
            	verActividades(profesor);
                break;
            case "Ver mis Learning Paths":
            	verLearningPaths(profesor);
                break;
            case "Ver mis Preguntas":
            	verPreguntas(profesor);
                break;
            case "Ver el progreso de un estudiante en un Learning Path":
            	verProgresoLearningPathEstudiante();
                break;
            case "Reseñar o calificar una actividad":
            	calificarResenarActividad();
                break;
            case "Calificar un Learning Path":
            	calificarLearningPath();
                break;
            case "Ver calificación de un Learning Path":
            	verCalificacionLP();
                break;
            case "Ver reseñas y calificación de una actividad":
            	verCalificacionActividad();
                break;
            case "Cerrar sesión":
            	JOptionPane.showMessageDialog(this, "Sesión cerrada.");
                System.exit(0);
                break;
            default:
                JOptionPane.showMessageDialog(this, "Opción no reconocida.", "Error", JOptionPane.ERROR_MESSAGE);
                break;
        }
    }

	private void verCalificacionActividad() {
		
		panelCentro.setLayout(new GridLayout(12, 1));

	    JLabel lblTitulo = new JLabel("Título de la Actividad:");
	    lblTitulo.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JTextField txtTitulo = new JTextField(20);
	    txtTitulo.setFont(new Font("Times New Roman", Font.BOLD, 14));
	    panelCentro.add(lblTitulo);
	    panelCentro.add(txtTitulo);
	    
	    JLabel lblTipo = new JLabel("Tipo de la Actividad:");
	    lblTipo.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JLabel lblOpciones = new JLabel("(Examen/Tarea/Quiz/Recurso/Encuesta)");
	    lblOpciones.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JTextField txtTipo = new JTextField(20);
	    txtTipo.setFont(new Font("Times New Roman", Font.BOLD, 14));
	    panelCentro.add(lblTipo);
	    panelCentro.add(lblOpciones);
	    panelCentro.add(txtTipo);

	    JLabel lblProfesor = new JLabel("Login del Profesor Creador:");
	    lblProfesor.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JTextField txtProfesor = new JTextField(20);
	    txtProfesor.setFont(new Font("Times New Roman", Font.BOLD, 14));
	    panelCentro.add(lblProfesor);
	    panelCentro.add(txtProfesor);
	    
	    JLabel b = new JLabel("");
	    panelCentro.add(b);

	    JButton btnBuscar = new JButton("Ver Calificación");
	    btnBuscar.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    panelCentro.add(btnBuscar);
	    
	    JLabel a = new JLabel("Resultado");
	    a.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    panelCentro.add(a);

	    JTextArea textAreaResultado = new JTextArea();
	    textAreaResultado.setFont(new Font("Times New Roman", Font.BOLD, 14));
	    panelCentro.add(textAreaResultado);
	    
	    JLabel c = new JLabel("");
	    panelCentro.add(c);

	    btnBuscar.addActionListener(e -> {
	        String titulo = txtTitulo.getText().trim();
	        String tipo = txtTipo.getText().trim();
	        String loginProfesor = txtProfesor.getText().trim();

	        if (titulo.isEmpty() || loginProfesor.isEmpty() || tipo.isEmpty()) {
	            textAreaResultado.setText("Error: Por favor, complete todos los campos.");
	            return;
	        }

	        Actividad actividad = aplicacion.getActividad(titulo + " - " + loginProfesor, tipo);

	        if (actividad != null) {
	            String resultado = String.format("""
	                    Calificación: %.2f
	                    Reseñas: %s
	                    """, 
	                    actividad.getRating(),
	            		actividad.getResenas());
	            
	            textAreaResultado.setText(resultado);
	        } else {
	            textAreaResultado.setText("Actividad no encontrada.");
	        }
	    });

	    this.add(panelCentro, BorderLayout.CENTER);
	    this.revalidate();
	    this.repaint();
		
	}

	private void verCalificacionLP() {
		
		panelCentro.setLayout(new GridLayout(9, 1));

	    JLabel lblTitulo = new JLabel("Título del Learning Path:");
	    lblTitulo.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JTextField txtTitulo = new JTextField(20);
	    txtTitulo.setFont(new Font("Times New Roman", Font.BOLD, 14));
	    panelCentro.add(lblTitulo);
	    panelCentro.add(txtTitulo);

	    JLabel lblProfesor = new JLabel("Login del Profesor Creador:");
	    lblProfesor.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JTextField txtProfesor = new JTextField(20);
	    txtProfesor.setFont(new Font("Times New Roman", Font.BOLD, 14));
	    panelCentro.add(lblProfesor);
	    panelCentro.add(txtProfesor);
	    
	    JLabel b = new JLabel("");
	    panelCentro.add(b);

	    JButton btnBuscar = new JButton("Ver Calificación");
	    btnBuscar.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    panelCentro.add(btnBuscar);
	    
	    JLabel a = new JLabel("Resultado");
	    a.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    panelCentro.add(a);

	    JTextArea textAreaResultado = new JTextArea();
	    textAreaResultado.setFont(new Font("Times New Roman", Font.BOLD, 14));
	    panelCentro.add(textAreaResultado);
	    
	    JLabel c = new JLabel("");
	    panelCentro.add(c);

	    btnBuscar.addActionListener(e -> {
	        String titulo = txtTitulo.getText().trim();
	        String loginProfesor = txtProfesor.getText().trim();

	        if (titulo.isEmpty() || loginProfesor.isEmpty()) {
	            textAreaResultado.setText("Error: Por favor, complete ambos campos.");
	            return;
	        }

	        LearningPath learningPath = aplicacion.getLearningPath(titulo + " - " + loginProfesor);

	        if (learningPath != null) {
	            String resultado = String.format("""
	                    Título: %s
	                    Profesor creador: %s (%s)
	                    Calificación: %.2f
	                    """, 
	                    learningPath.getTitulo(), 
	                    learningPath.getNombreProfesorCreador(), 
	                    learningPath.getLoginProfesorCreador(), 
	                    learningPath.getRating());
	            textAreaResultado.setText(resultado);
	        } else {
	            textAreaResultado.setText("Learning Path no encontrado.");
	        }
	    });

	    this.add(panelCentro, BorderLayout.CENTER);
	    this.revalidate();
	    this.repaint();
		
	}

	private void calificarLearningPath() {
		
		panelCentro.setLayout(new GridLayout(17, 1));
	    
	    JLabel lblTitulo = new JLabel("Título del Learning Path:");
	    lblTitulo.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JTextField txtTitulo = new JTextField();
	    txtTitulo.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JLabel lblProfesor = new JLabel("Login del Profesor:");
	    lblProfesor.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JTextField txtProfesor = new JTextField();
	    txtProfesor.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JLabel lblCalificacion = new JLabel("Calificación (0.0 - 5.0):");
	    lblCalificacion.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JTextField txtCalificacion = new JTextField();
	    txtCalificacion.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JButton btnRegistrar = new JButton("Registrar Calificación");
	    btnRegistrar.setFont(new Font("Times New Roman", Font.BOLD, 16));

	    panelCentro.add(lblTitulo);
	    panelCentro.add(txtTitulo);
	    panelCentro.add(lblProfesor);
	    panelCentro.add(txtProfesor);
	    panelCentro.add(lblCalificacion);
	    panelCentro.add(txtCalificacion);
	    panelCentro.add(btnRegistrar);

	    btnRegistrar.addActionListener(e -> {
	        try {
	            String titulo = txtTitulo.getText().trim();
	            String profesor = txtProfesor.getText().trim();
	            double calificacion = Double.parseDouble(txtCalificacion.getText().trim());

	            if (titulo.isEmpty() || profesor.isEmpty() || calificacion < 0.0 || calificacion > 5.0) {
	                throw new IllegalArgumentException("El título y el login del profesor son obligatorios y la calificación debe estar entre 0.0 y 5.0.");
	            }

	            LearningPath learningPath = aplicacion.getLearningPath(titulo + " - " + profesor);

	            if (learningPath != null) {
	                aplicacion.calificarLearningPath(learningPath, calificacion);
	                aplicacion.descargarDatos();
	                JOptionPane.showMessageDialog(
	                    null, "¡Calificación registrada con éxito!",
	                    "Éxito", JOptionPane.INFORMATION_MESSAGE
	                );
	            } else {
	                JOptionPane.showMessageDialog(
	                    null, "No se encontró el Learning Path especificado.",
	                    "Error", JOptionPane.ERROR_MESSAGE
	                );
	            }
	        } catch (NumberFormatException ex) {
	            JOptionPane.showMessageDialog(
	                null, "La calificación debe ser un número válido.",
	                "Error", JOptionPane.ERROR_MESSAGE
	            );
	        } catch (IllegalArgumentException ex) {
	            JOptionPane.showMessageDialog(
	                null, ex.getMessage(),
	                "Validación", JOptionPane.WARNING_MESSAGE
	            );
	        } catch (Exception ex) {
	            JOptionPane.showMessageDialog(
	                null, "Ocurrió un error inesperado: " + ex.getMessage(),
	                "Error", JOptionPane.ERROR_MESSAGE
	            );
	        }
	    });

	    this.add(panelCentro, BorderLayout.CENTER);
	    this.revalidate();
	    this.repaint();
		
	}

	private void calificarResenarActividad() {
		// TODO Auto-generated method stub
		
	}

	private void verProgresoLearningPathEstudiante() {
		// TODO Auto-generated method stub
		
	}

	private void verPreguntas(Profesor profesor) {
		
		panelCentro.setLayout(new BorderLayout());

	    JLabel lblTitulo = new JLabel("== Mis Preguntas ==");
	    lblTitulo.setFont(new Font("Times New Roman", Font.BOLD, 20));
	    lblTitulo.setHorizontalAlignment(SwingConstants.CENTER); 
	    panelCentro.add(lblTitulo, BorderLayout.NORTH);

	    JTextArea textArea = new JTextArea(20, 50);
	    textArea.setFont(new Font("Times New Roman", Font.BOLD, 14));
	    textArea.setEditable(false); 
	    JScrollPane scrollPane = new JScrollPane(textArea);
	    panelCentro.add(scrollPane, BorderLayout.CENTER);

	    StringBuilder preguntasTexto = new StringBuilder();

	    preguntasTexto.append("-- Preguntas Abiertas --\n");
	    profesor.getPreguntasAbiertasPropias().forEach((id, pregAbierta) -> 
	        preguntasTexto.append("  - ID: ").append(id).append("\n"));

	    preguntasTexto.append("\n-- Preguntas de Selección Múltiple --\n");
	    profesor.getPreguntasSeleccionPropias().forEach((id, pregSeleccion) -> 
	        preguntasTexto.append("  - ID: ").append(id).append("\n"));

	    preguntasTexto.append("\n-- Preguntas de Verdadero o Falso --\n");
	    profesor.getPreguntasBooleanPropias().forEach((id, pregBoolean) -> 
	        preguntasTexto.append("  - ID: ").append(id).append("\n"));

	    if (preguntasTexto.toString().trim().isEmpty()) {
	        preguntasTexto.append("No tiene preguntas propias");
	    }

	    textArea.setText(preguntasTexto.toString());

	    this.add(panelCentro, BorderLayout.CENTER);
	    this.revalidate();
	    this.repaint();
		
	}

	private void verLearningPaths(Profesor profesor) {
		
		panelCentro.setLayout(new BorderLayout());
	    
	    JLabel lblTitulo = new JLabel("--- Mis Learning Paths ---");
	    lblTitulo.setFont(new Font("Times New Roman", Font.BOLD, 20));
	    lblTitulo.setHorizontalAlignment(SwingConstants.CENTER); 
	    panelCentro.add(lblTitulo, BorderLayout.NORTH);

	    JTextArea textArea = new JTextArea(20, 50);
	    textArea.setFont(new Font("imes New Roman", Font.BOLD, 14));
	    textArea.setEditable(false);  
	    JScrollPane scrollPane = new JScrollPane(textArea);
	    panelCentro.add(scrollPane, BorderLayout.CENTER);

	    if (profesor.getLearningPathPropios().size() > 0) {
	        StringBuilder learningPathsText = new StringBuilder();
	        int i = 1;
	        for (LearningPath lp : profesor.getLearningPathPropios().values()) {
	            learningPathsText.append(String.format("%d. %s\n", i, lp.getTitulo()));
	            i++;
	        }
	        textArea.setText(learningPathsText.toString());
	        textArea.setFont(new Font("Times New Roman", Font.BOLD, 20));
	    } else {
	        textArea.setText("No tiene Learning Paths propios");
	        textArea.setFont(new Font("Times New Roman", Font.BOLD, 20));
	    }

	    this.add(panelCentro, BorderLayout.CENTER);
	    this.revalidate();
	    this.repaint();
		
	}

	private void verActividades(Profesor profesor) {
		
		panelCentro.setLayout(new BorderLayout());
	    
	    JLabel lblTitulo = new JLabel("== Mis Actividades ==");
	    lblTitulo.setFont(new Font("Times New Roman", Font.BOLD, 20));
	    lblTitulo.setHorizontalAlignment(SwingConstants.CENTER); 
	    panelCentro.add(lblTitulo, BorderLayout.NORTH);

	    JTextArea textArea = new JTextArea(20, 50);
	    textArea.setFont(new Font("Times New Roman", Font.BOLD, 20));
	    textArea.setEditable(false);  
	    JScrollPane scrollPane = new JScrollPane(textArea);
	    panelCentro.add(scrollPane, BorderLayout.CENTER);

	    StringBuilder actividadesText = new StringBuilder();

	    profesor.getMapaRecursosPropios().forEach((id, recurso) -> {
	        actividadesText.append("Recurso: ").append(id).append("\n");
	    });

	    profesor.getMapaTareasPropias().forEach((id, tarea) -> {
	        actividadesText.append("Tarea: ").append(id).append("\n");
	    });

	    profesor.getMapaExamenesPropios().forEach((id, examen) -> {
	        actividadesText.append("Examen: ").append(id).append("\n");
	    });
	    
	    profesor.getMapaQuicesPropios().forEach((id, quiz) -> {
	        actividadesText.append("Quiz: ").append(id).append("\n");
	    });

	    profesor.getMapaEncuestasPropias().forEach((id, encuesta) -> {
	        actividadesText.append("Encuesta: ").append(id).append("\n");
	    });

	    textArea.setText(actividadesText.toString());

	    this.add(panelCentro, BorderLayout.CENTER);
	    this.revalidate();
	    this.repaint();
		
	}

	private void revisarLearningPathRepetido(Profesor profesor) {
		
		panelCentro.setLayout(new GridLayout(17, 1));

	    JLabel lblTitulo = new JLabel("Ingrese el título del Learning Path:");
	    lblTitulo.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JTextField txtTitulo = new JTextField(20);
	    txtTitulo.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    panelCentro.add(lblTitulo);
	    panelCentro.add(txtTitulo);
	    
	    JButton btnRevisar = new JButton("Revisar Learning Path");
	    btnRevisar.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    btnRevisar.addActionListener(e -> {
	        try {
	            String titulo = txtTitulo.getText();
	            if (titulo.isEmpty()) {
	                throw new IllegalArgumentException("El título no puede estar vacío.");
	            }
	            
	            aplicacion.revisarLearningPathRepetido(titulo, profesor.getLogin());
	            JOptionPane.showMessageDialog(panelCentro, 
	                "El laearning Path no existe bajo su login, puede ser creado.", 
	                "Revisión Exitosa", 
	                JOptionPane.INFORMATION_MESSAGE);
	        } catch (LearningPathYaExistenteException ex) {
	            JOptionPane.showMessageDialog(panelCentro, 
	                ex.getMessage(), 
	                "Error: Learning Path Ya Existente", 
	                JOptionPane.ERROR_MESSAGE);
	        } catch (IllegalArgumentException ex) {
	            JOptionPane.showMessageDialog(panelCentro, 
	                ex.getMessage(), 
	                "Validación", 
	                JOptionPane.ERROR_MESSAGE);
	        } catch (Exception ex) {
	            JOptionPane.showMessageDialog(panelCentro, 
	                "Error inesperado: " + ex.getMessage(), 
	                "Error", 
	                JOptionPane.ERROR_MESSAGE);
	        }
	    });

	    panelCentro.add(btnRevisar);

	    this.add(panelCentro, BorderLayout.CENTER);
	    this.revalidate();
	    this.repaint();
	
	}

	private void revisarActividadRepetida(Profesor profesor) {
		
		panelCentro.setLayout(new GridLayout(17, 1));

	    JLabel lblTitulo = new JLabel("Ingrese el título de la actividad:");
	    lblTitulo.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JTextField txtTitulo = new JTextField(20);
	    txtTitulo.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    panelCentro.add(lblTitulo);
	    panelCentro.add(txtTitulo);

	    JLabel lblTipo = new JLabel("Ingrese el tipo de actividad:");
	    lblTipo.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JLabel lblTipos = new JLabel("Tarea, Quiz, Recurso, Examen, Encuesta");
	    lblTipos.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JTextField txtTipo = new JTextField(20);
	    txtTipo.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    panelCentro.add(lblTipo);
	    panelCentro.add(lblTipos);
	    panelCentro.add(txtTipo);

	    JButton btnRevisar = new JButton("Revisar Actividad");
	    btnRevisar.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    btnRevisar.addActionListener(e -> {
	        try {
	            String titulo = txtTitulo.getText();
	            if (titulo.isEmpty()) {
	                throw new IllegalArgumentException("El título no puede estar vacío.");
	            }

	            String tipo = txtTipo.getText();
	            if (tipo.isEmpty() || 
	               !(tipo.equalsIgnoreCase("Tarea") || tipo.equalsIgnoreCase("Quiz") || 
	                 tipo.equalsIgnoreCase("Recurso") || tipo.equalsIgnoreCase("Examen") || 
	                 tipo.equalsIgnoreCase("Encuesta"))) {
	                throw new IllegalArgumentException("Debe ingresar un tipo válido de actividad (Tarea, Quiz, Recurso, Examen, Encuesta).");
	            }
	            
	            aplicacion.revisarActividadRepetida(titulo, profesor.getLogin(), tipo);
	            JOptionPane.showMessageDialog(panelCentro, 
	                "La actividad no existe bajo su login, puede ser creada.", 
	                "Revisión Exitosa", 
	                JOptionPane.INFORMATION_MESSAGE);
	        } catch (ActividadYaExistenteException ex) {
	            JOptionPane.showMessageDialog(panelCentro, 
	                ex.getMessage(), 
	                "Error: Actividad Ya Existente", 
	                JOptionPane.ERROR_MESSAGE);
	        } catch (IllegalArgumentException ex) {
	            JOptionPane.showMessageDialog(panelCentro, 
	                ex.getMessage(), 
	                "Validación", 
	                JOptionPane.ERROR_MESSAGE);
	        } catch (Exception ex) {
	            JOptionPane.showMessageDialog(panelCentro, 
	                "Error inesperado: " + ex.getMessage(), 
	                "Error", 
	                JOptionPane.ERROR_MESSAGE);
	        }
	    });

	    panelCentro.add(btnRevisar);

	    this.add(panelCentro, BorderLayout.CENTER);
	    this.revalidate();
	    this.repaint();
		
	}

	private void modificarPregunta(Profesor profesor2) {
		// TODO Auto-generated method stub
		
	}

	private void calificarActividad(Profesor profesor) {
		// TODO Auto-generated method stub
		
	}

	private void modificarActividad(Profesor profesor2) {
		// TODO Auto-generated method stub
		
	}

	private void modificarLearningPath(Profesor profesor2) {
		// TODO Auto-generated method stub
		
	}

	private void clonarLearningPath(Profesor profesor) {
		
		panelCentro.setLayout(new GridLayout(17,1));
	    

	    JLabel lblTitulo = new JLabel("Título del Learning Path:");
	    lblTitulo.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JTextField txtTitulo = new JTextField(20);
	    txtTitulo.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    panelCentro.add(lblTitulo);
	    panelCentro.add(txtTitulo);
	    
	    JLabel lblProfesor = new JLabel("Login del Profesor Dueño del Learning Path:");
	    lblProfesor.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JTextField txtProfesor = new JTextField(20);
	    txtProfesor.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    panelCentro.add(lblProfesor);
	    panelCentro.add(txtProfesor);
	    
	    JButton btnGuardar = new JButton("Clonar Learning Path");
	    btnGuardar.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    btnGuardar.addActionListener(e -> {
	        try {
	            String titulo = txtTitulo.getText();
	            if (titulo.isEmpty()) throw new IllegalArgumentException("El título no puede estar vacío.");
	            
	            String profesorOg = txtProfesor.getText();
	            if (profesorOg.isEmpty()) throw new IllegalArgumentException("El profesor no puede estar vacío.");
	            
	            
	            String id = titulo + " - " + profesorOg;
	            LearningPath learningOriginal = aplicacion.getLearningPath(id);
	            aplicacion.clonarLearningPath(learningOriginal, profesor);
	            aplicacion.descargarDatos();
	            JOptionPane.showMessageDialog(this, "Learning Path clonado exitosamente.");
	        } catch (IllegalArgumentException ex) {
	            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Validación", JOptionPane.ERROR_MESSAGE);
	        } catch (Exception ex) {
	            JOptionPane.showMessageDialog(this, "Error inesperado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    });
	    panelCentro.add(btnGuardar);
	    
	    this.add(panelCentro, BorderLayout.CENTER);
	    this.revalidate();
	    this.repaint();
		
	}

	private void clonarActividad(Profesor profesor) {
		
		panelCentro.setLayout(new GridLayout(17,1));
	    

	    JLabel lblTitulo = new JLabel("Título de la Actividad:");
	    lblTitulo.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JTextField txtTitulo = new JTextField(20);
	    txtTitulo.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    panelCentro.add(lblTitulo);
	    panelCentro.add(txtTitulo);
	    
	    JLabel lblProfesor = new JLabel("Login del Profesor Dueño de la Actividad:");
	    lblProfesor.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JTextField txtProfesor = new JTextField(20);
	    txtProfesor.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    panelCentro.add(lblProfesor);
	    panelCentro.add(txtProfesor);
	    
	    JLabel lblTipo = new JLabel("Tipo de la actividad que desea clonar: ");
	    lblTipo.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JLabel lblOpciones = new JLabel("(Examen/Tarea/Quiz/Recurso/Encuesta)");
	    lblOpciones.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JTextField txtTipo = new JTextField(20);
	    txtTipo.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    panelCentro.add(lblTipo);
	    panelCentro.add(lblOpciones);
	    panelCentro.add(txtTipo);
	    
	    JButton btnGuardar = new JButton("Clonar Actividad");
	    btnGuardar.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    btnGuardar.addActionListener(e -> {
	        try {
	            String titulo = txtTitulo.getText();
	            if (titulo.isEmpty()) throw new IllegalArgumentException("El título no puede estar vacío.");
	            
	            String profesorOg = txtProfesor.getText();
	            if (profesorOg.isEmpty()) throw new IllegalArgumentException("El profesor no puede estar vacío.");
	            
	            String tipo = txtTipo.getText();
	            if (tipo.isEmpty()) throw new IllegalArgumentException("El tipo de actividad no puede estar vacío.");
	            
	            String id = titulo + " - " + profesorOg;
	            Actividad actividadOriginal = aplicacion.getActividad(id , tipo);
	            aplicacion.clonarActividad(actividadOriginal, profesor);
	            aplicacion.descargarDatos();
	            JOptionPane.showMessageDialog(this, "Actividad clonada exitosamente.");
	        } catch (IllegalArgumentException ex) {
	            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Validación", JOptionPane.ERROR_MESSAGE);
	        } catch (Exception ex) {
	            JOptionPane.showMessageDialog(this, "Error inesperado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    });
	    panelCentro.add(btnGuardar);
	    
	    this.add(panelCentro, BorderLayout.CENTER);
	    this.revalidate();
	    this.repaint();

	}

	private void crearPregunta(Profesor profesor2) {
		// TODO Auto-generated method stub
		
	}

	private void crearLearningPath(Profesor profesor2) {
		// TODO Auto-generated method stub
		
	}

	private void crearEncuesta(Profesor profesor) {
		
		panelCentro.setLayout(new GridLayout(17,1));
	    

	    JLabel lblTitulo = new JLabel("Título de la Encuesta:");
	    lblTitulo.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JTextField txtTitulo = new JTextField(20);
	    txtTitulo.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    panelCentro.add(lblTitulo);
	    panelCentro.add(txtTitulo);
	    
	    JLabel lblDescripcion = new JLabel("Descripción de la Encuesta:");
	    lblDescripcion.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JTextArea txtDescripcion = new JTextArea(10, 20);
	    txtDescripcion.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JScrollPane scrollDescripcion = new JScrollPane(txtDescripcion);
	    panelCentro.add(lblDescripcion);
	    panelCentro.add(scrollDescripcion);
	    
	    JLabel lblObjetivos = new JLabel("Objetivos (separados por comas):");
	    lblObjetivos.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JTextField txtObjetivos = new JTextField(20);
	    txtObjetivos.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    panelCentro.add(lblObjetivos);
	    panelCentro.add(txtObjetivos);
	    
	    JLabel lblDificultad = new JLabel("Nivel de dificultad:");
	    lblDificultad.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    String[] nivelesDificultad = {"Principiante", "Intermedio", "Avanzado"};
	    JComboBox<String> cbDificultad = new JComboBox<>(nivelesDificultad);
	    cbDificultad.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    panelCentro.add(lblDificultad);
	    panelCentro.add(cbDificultad);
	    
	    JLabel lblDuracion = new JLabel("Duración (minutos):");
	    lblDuracion.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JTextField txtDuracion = new JTextField(10);
	    txtDuracion.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    panelCentro.add(lblDuracion);
	    panelCentro.add(txtDuracion);
	    
	    JLabel lblFechaLimite = new JLabel("Fecha límite (dd/MM/yyyy):");
	    lblFechaLimite.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JTextField txtFechaLimite = new JTextField(10);
	    txtFechaLimite.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    panelCentro.add(lblFechaLimite);
	    panelCentro.add(txtFechaLimite);
	    
	    JLabel lblPreguntas = new JLabel("Preguntas en formato titulo|enunciado separadas por ;");
	    lblPreguntas.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JTextField txtPreguntas = new JTextField(20);
	    txtPreguntas.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    panelCentro.add(lblPreguntas);
	    panelCentro.add(txtPreguntas);
	    
	    JButton btnGuardar = new JButton("Crear Actividad");
	    btnGuardar.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    btnGuardar.addActionListener(e -> {
	        try {
	            String titulo = txtTitulo.getText();
	            if (titulo.isEmpty()) throw new IllegalArgumentException("El título no puede estar vacío.");
	            
	            String descripcion = txtDescripcion.getText();
	            if (descripcion.isEmpty()) throw new IllegalArgumentException("La descripción no puede estar vacía.");
	            
	            String[] objetivosArray = txtObjetivos.getText().split(",");
	            List<String> objetivos = new ArrayList<>();
	            for (String obj : objetivosArray) {
	                if (!obj.trim().isEmpty()) {
	                    objetivos.add(obj.trim());
	                }
	            }
	            if (objetivos.isEmpty()) throw new IllegalArgumentException("Debe ingresar al menos un objetivo.");
	            
	            String dificultad = nivelesDificultad[cbDificultad.getSelectedIndex()];
	            
	            int duracion = Integer.parseInt(txtDuracion.getText().trim());
	            if (duracion <= 0) throw new IllegalArgumentException("La duración debe ser positiva.");
	            
	            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	            sdf.setLenient(false);
	            Date fechaLimite = sdf.parse(txtFechaLimite.getText().trim());
	            
	            String[] preguntasArray = txtPreguntas.getText().split(";");
	            List<PreguntaAbierta> preguntas = new ArrayList<>();
	            for (String preg : preguntasArray) {
	            	
	            	String[] partes = preg.split("\\|");
	            	String tituloPregunta = partes[0].trim();
	                String enunciado = partes[1].trim();
	                PreguntaAbierta pregunta = new PreguntaAbierta(enunciado, tituloPregunta);
	                preguntas.add(pregunta);
	            	
	            }
	            
	            aplicacion.crearEncuesta(titulo, descripcion, objetivos, dificultad, duracion, fechaLimite, profesor, preguntas);
	            aplicacion.descargarDatos();
	            JOptionPane.showMessageDialog(this, "Actividad creada exitosamente.");
	        } catch (IllegalArgumentException ex) {
	            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Validación", JOptionPane.ERROR_MESSAGE);
	        } catch (Exception ex) {
	            JOptionPane.showMessageDialog(this, "Error inesperado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    });
	    panelCentro.add(btnGuardar);
	    
	    this.add(panelCentro, BorderLayout.CENTER);
	    this.revalidate();
	    this.repaint();
		
	}

	private void crearExamen(Profesor profesor) {
		
		panelCentro.setLayout(new GridLayout(17,1));
	    

	    JLabel lblTitulo = new JLabel("Título del Examen:");
	    lblTitulo.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JTextField txtTitulo = new JTextField(20);
	    txtTitulo.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    panelCentro.add(lblTitulo);
	    panelCentro.add(txtTitulo);
	    
	    JLabel lblDescripcion = new JLabel("Descripción del Examen:");
	    lblDescripcion.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JTextArea txtDescripcion = new JTextArea(10, 20);
	    txtDescripcion.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JScrollPane scrollDescripcion = new JScrollPane(txtDescripcion);
	    panelCentro.add(lblDescripcion);
	    panelCentro.add(scrollDescripcion);
	    
	    JLabel lblObjetivos = new JLabel("Objetivos (separados por comas):");
	    lblObjetivos.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JTextField txtObjetivos = new JTextField(20);
	    txtObjetivos.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    panelCentro.add(lblObjetivos);
	    panelCentro.add(txtObjetivos);
	    
	    JLabel lblDificultad = new JLabel("Nivel de dificultad:");
	    lblDificultad.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    String[] nivelesDificultad = {"Principiante", "Intermedio", "Avanzado"};
	    JComboBox<String> cbDificultad = new JComboBox<>(nivelesDificultad);
	    cbDificultad.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    panelCentro.add(lblDificultad);
	    panelCentro.add(cbDificultad);
	    
	    JLabel lblDuracion = new JLabel("Duración (minutos):");
	    lblDuracion.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JTextField txtDuracion = new JTextField(10);
	    txtDuracion.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    panelCentro.add(lblDuracion);
	    panelCentro.add(txtDuracion);
	    
	    JLabel lblFechaLimite = new JLabel("Fecha límite (dd/MM/yyyy):");
	    lblFechaLimite.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JTextField txtFechaLimite = new JTextField(10);
	    txtFechaLimite.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    panelCentro.add(lblFechaLimite);
	    panelCentro.add(txtFechaLimite);
	    
	    JLabel lblPreguntas = new JLabel("Preguntas en formato titulo|enunciado separadas por ;");
	    lblPreguntas.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JTextField txtPreguntas = new JTextField(20);
	    txtPreguntas.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    panelCentro.add(lblPreguntas);
	    panelCentro.add(txtPreguntas);
	    
	    JButton btnGuardar = new JButton("Crear Actividad");
	    btnGuardar.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    btnGuardar.addActionListener(e -> {
	        try {
	            String titulo = txtTitulo.getText();
	            if (titulo.isEmpty()) throw new IllegalArgumentException("El título no puede estar vacío.");
	            
	            String descripcion = txtDescripcion.getText();
	            if (descripcion.isEmpty()) throw new IllegalArgumentException("La descripción no puede estar vacía.");
	            
	            String[] objetivosArray = txtObjetivos.getText().split(",");
	            List<String> objetivos = new ArrayList<>();
	            for (String obj : objetivosArray) {
	                if (!obj.trim().isEmpty()) {
	                    objetivos.add(obj.trim());
	                }
	            }
	            if (objetivos.isEmpty()) throw new IllegalArgumentException("Debe ingresar al menos un objetivo.");
	            
	            String dificultad = nivelesDificultad[cbDificultad.getSelectedIndex()];
	            
	            int duracion = Integer.parseInt(txtDuracion.getText().trim());
	            if (duracion <= 0) throw new IllegalArgumentException("La duración debe ser positiva.");
	            
	            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	            sdf.setLenient(false);
	            Date fechaLimite = sdf.parse(txtFechaLimite.getText().trim());
	            
	            String[] preguntasArray = txtPreguntas.getText().split(";");
	            List<PreguntaAbierta> preguntas = new ArrayList<>();
	            for (String preg : preguntasArray) {
	            	
	            	String[] partes = preg.split("\\|");
	            	String tituloPregunta = partes[0].trim();
	                String enunciado = partes[1].trim();
	                PreguntaAbierta pregunta = new PreguntaAbierta(enunciado, tituloPregunta);
	                preguntas.add(pregunta);
	            	
	            }
	            
	            aplicacion.crearExamen(titulo, descripcion, objetivos, dificultad, duracion, fechaLimite, profesor, preguntas);
	            aplicacion.descargarDatos();
	            JOptionPane.showMessageDialog(this, "Actividad creada exitosamente.");
	        } catch (IllegalArgumentException ex) {
	            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Validación", JOptionPane.ERROR_MESSAGE);
	        } catch (Exception ex) {
	            JOptionPane.showMessageDialog(this, "Error inesperado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    });
	    panelCentro.add(btnGuardar);
	    
	    this.add(panelCentro, BorderLayout.CENTER);
	    this.revalidate();
	    this.repaint();
		
	}

	private void crearQuiz(Profesor profesor) {
		
		panelCentro.setLayout(new GridLayout(18, 1));

        JLabel lblTitulo = new JLabel("Título del Quiz:");
        lblTitulo.setFont(new Font("Times New Roman", Font.BOLD, 16));
        JTextField txtTitulo = new JTextField(20);
        txtTitulo.setFont(new Font("Times New Roman", Font.BOLD, 16));
        panelCentro.add(lblTitulo);
        panelCentro.add(txtTitulo);

        JLabel lblDescripcion = new JLabel("Descripción del Quiz:");
        lblDescripcion.setFont(new Font("Times New Roman", Font.BOLD, 16));
        JTextField txtDescripcion = new JTextField(20);
        txtDescripcion.setFont(new Font("Times New Roman", Font.BOLD, 16));
        panelCentro.add(lblDescripcion);
        panelCentro.add(txtDescripcion);

        JLabel lblObjetivos = new JLabel("Objetivos(separados con comas):");
        lblObjetivos.setFont(new Font("Times New Roman", Font.BOLD, 13));
        JTextField txtObjetivos = new JTextField(20);
        txtObjetivos.setFont(new Font("Times New Roman", Font.BOLD, 16));
        panelCentro.add(lblObjetivos);
        panelCentro.add(txtObjetivos);

        JLabel lblDificultad = new JLabel("Nivel de dificultad:");
        lblDificultad.setFont(new Font("Times New Roman", Font.BOLD, 16));
        String[] nivelesDificultad = {"Principiante", "Intermedio", "Avanzado"};
        JComboBox<String> cbDificultad = new JComboBox<>(nivelesDificultad);
        cbDificultad.setFont(new Font("Times New Roman", Font.BOLD, 16));
        panelCentro.add(lblDificultad);
        panelCentro.add(cbDificultad);

        JLabel lblDuracion = new JLabel("Duración (minutos):");
        lblDuracion.setFont(new Font("Times New Roman", Font.BOLD, 16));
        JTextField txtDuracion = new JTextField(10);
        txtDuracion.setFont(new Font("Times New Roman", Font.BOLD, 16));
        panelCentro.add(lblDuracion);
        panelCentro.add(txtDuracion);

        JLabel lblFechaLimite = new JLabel("Fecha límite (dd/MM/yyyy):");
        lblFechaLimite.setFont(new Font("Times New Roman", Font.BOLD, 16));
        JTextField txtFechaLimite = new JTextField(10);
        txtFechaLimite.setFont(new Font("Times New Roman", Font.BOLD, 16));
        panelCentro.add(lblFechaLimite);
        panelCentro.add(txtFechaLimite);

        JLabel lblCalificacionMinima = new JLabel("Calificación mínima:");
        lblCalificacionMinima.setFont(new Font("Times New Roman", Font.BOLD, 16));
        JTextField txtCalificacionMinima = new JTextField(10);
        txtCalificacionMinima.setFont(new Font("Times New Roman", Font.BOLD, 16));
        panelCentro.add(lblCalificacionMinima);
        panelCentro.add(txtCalificacionMinima);

        JLabel lblPreguntas = new JLabel("Redactar las preguntas asi: ");
        lblPreguntas.setFont(new Font("Times New Roman", Font.BOLD, 17));
        panelCentro.add(lblPreguntas);
        JLabel b = new JLabel("titulo|enunciado;");
        b.setFont(new Font("Times New Roman", Font.BOLD, 17));
        panelCentro.add(b);
        JTextField txtPreguntas = new JTextField(20);
        txtPreguntas.setFont(new Font("Times New Roman", Font.BOLD, 16));
        panelCentro.add(txtPreguntas);
        JLabel c = new JLabel("");
        c.setFont(new Font("Times New Roman", Font.BOLD, 16));
        panelCentro.add(c);

        JLabel lblOpciones = new JLabel("Opciones de respuesta:");
        lblOpciones.setFont(new Font("Times New Roman", Font.BOLD, 16));
        panelCentro.add(lblOpciones);
        
        JLabel a = new JLabel("");
        panelCentro.add(a);
        
        JTextField txtOpcion1 = new JTextField(20);
        txtOpcion1.setFont(new Font("Times New Roman", Font.BOLD, 16));
        JLabel uno = new JLabel("Opción 1:");
        uno.setFont(new Font("Times New Roman", Font.BOLD, 16));
        panelCentro.add(uno);
        panelCentro.add(txtOpcion1);

        JTextField txtOpcion2 = new JTextField(20);
        txtOpcion2.setFont(new Font("Times New Roman", Font.BOLD, 16));
        JLabel dos = new JLabel("Opción 2:");
        dos.setFont(new Font("Times New Roman", Font.BOLD, 16));
        panelCentro.add(dos);
        panelCentro.add(txtOpcion2);

        JTextField txtOpcion3 = new JTextField(20);
        txtOpcion3.setFont(new Font("Times New Roman", Font.BOLD, 16));
        JLabel tres = new JLabel("Opción 3:");
        tres.setFont(new Font("Times New Roman", Font.BOLD, 16));
        panelCentro.add(tres);
        panelCentro.add(txtOpcion3);

        JTextField txtOpcion4 = new JTextField(20);
        txtOpcion4.setFont(new Font("Times New Roman", Font.BOLD, 16));
        JLabel cuatro = new JLabel("Opción 4:");
        cuatro.setFont(new Font("Times New Roman", Font.BOLD, 16));
        panelCentro.add(cuatro);
        panelCentro.add(txtOpcion4);

        JLabel lblOpcionCorrecta = new JLabel("Seleccione la opción correcta:");
        lblOpcionCorrecta.setFont(new Font("Times New Roman", Font.BOLD, 16));
        panelCentro.add(lblOpcionCorrecta);

        String[] opcionesCorrectas = {"1", "2", "3", "4"};
        JComboBox<String> cbOpcionCorrecta = new JComboBox<>(opcionesCorrectas);
        cbOpcionCorrecta.setFont(new Font("Times New Roman", Font.BOLD, 16));
        panelCentro.add(cbOpcionCorrecta);

        JButton btnGuardar = new JButton("Crear Actividad");
        btnGuardar.setFont(new Font("Times New Roman", Font.BOLD, 16));
        btnGuardar.addActionListener(e -> {
            try {
                String titulo = txtTitulo.getText();
                if (titulo.isEmpty()) throw new IllegalArgumentException("El título no puede estar vacío.");

                String descripcion = txtDescripcion.getText();
                if (descripcion.isEmpty()) throw new IllegalArgumentException("La descripción no puede estar vacía.");

                String[] objetivosArray = txtObjetivos.getText().split(",");
                List<String> objetivos = new ArrayList<>();
                for (String obj : objetivosArray) {
                    if (!obj.trim().isEmpty()) {
                        objetivos.add(obj.trim());
                    }
                }
                if (objetivos.isEmpty()) throw new IllegalArgumentException("Debe ingresar al menos un objetivo.");

                String dificultad = nivelesDificultad[cbDificultad.getSelectedIndex()];

                int duracion = Integer.parseInt(txtDuracion.getText().trim());
                if (duracion <= 0) throw new IllegalArgumentException("La duración debe ser positiva.");

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                sdf.setLenient(false);
                Date fechaLimite = sdf.parse(txtFechaLimite.getText().trim());

                double calificacionMinima = Double.parseDouble(txtCalificacionMinima.getText());
                if (calificacionMinima <= 0) throw new IllegalArgumentException("La calificación mínima debe ser positiva.");

                String[] preguntasArray = txtPreguntas.getText().split(";");
                List<PreguntaCerrada> preguntas = new ArrayList<>();
                for (String preg : preguntasArray) {
                    String[] partes = preg.split("\\|");
                    if (partes.length != 2) {
                        throw new IllegalArgumentException("Formato de pregunta inválido. Debe ser título|enunciado.");
                    }
                    String tituloPregunta = partes[0].trim();
                    String enunciado = partes[1].trim();

                    String opcion1 = txtOpcion1.getText().trim();
                    String opcion2 = txtOpcion2.getText().trim();
                    String opcion3 = txtOpcion3.getText().trim();
                    String opcion4 = txtOpcion4.getText().trim();

                    if (opcion1.isEmpty() || opcion2.isEmpty() || opcion3.isEmpty() || opcion4.isEmpty()) {
                        throw new IllegalArgumentException("Debe ingresar todas las opciones de respuesta.");
                    }

                    int opcionCorrecta = Integer.parseInt((String) cbOpcionCorrecta.getSelectedItem());

                    PreguntaCerrada pregunta = new PreguntaSeleccionMultiple(
                        enunciado, tituloPregunta, opcion1, opcion2, opcion3, opcion4, opcionCorrecta
                    );
                    preguntas.add(pregunta);
                }

                aplicacion.crearQuiz(titulo, descripcion, objetivos, dificultad, duracion, fechaLimite, profesor, calificacionMinima, preguntas);
                aplicacion.descargarDatos();
                JOptionPane.showMessageDialog(this, "Actividad creada exitosamente.");
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Validación", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error inesperado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        panelCentro.add(btnGuardar);

        this.add(panelCentro, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
		
	}

	private void crearTarea(Profesor profesor) {

		panelCentro.setLayout(new GridLayout(17,1));
	    

	    JLabel lblTitulo = new JLabel("Título de la Tarea:");
	    lblTitulo.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JTextField txtTitulo = new JTextField(20);
	    txtTitulo.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    panelCentro.add(lblTitulo);
	    panelCentro.add(txtTitulo);
	    
	    JLabel lblDescripcion = new JLabel("Descripción de la Tarea:");
	    lblDescripcion.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JTextArea txtDescripcion = new JTextArea(10, 20);
	    txtDescripcion.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JScrollPane scrollDescripcion = new JScrollPane(txtDescripcion);
	    panelCentro.add(lblDescripcion);
	    panelCentro.add(scrollDescripcion);
	    
	    JLabel lblObjetivos = new JLabel("Objetivos (separados por comas):");
	    lblObjetivos.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JTextField txtObjetivos = new JTextField(20);
	    txtObjetivos.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    panelCentro.add(lblObjetivos);
	    panelCentro.add(txtObjetivos);
	    
	    JLabel lblDificultad = new JLabel("Nivel de dificultad:");
	    lblDificultad.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    String[] nivelesDificultad = {"Principiante", "Intermedio", "Avanzado"};
	    JComboBox<String> cbDificultad = new JComboBox<>(nivelesDificultad);
	    cbDificultad.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    panelCentro.add(lblDificultad);
	    panelCentro.add(cbDificultad);
	    
	    JLabel lblDuracion = new JLabel("Duración (minutos):");
	    lblDuracion.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JTextField txtDuracion = new JTextField(10);
	    txtDuracion.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    panelCentro.add(lblDuracion);
	    panelCentro.add(txtDuracion);
	    
	    JLabel lblFechaLimite = new JLabel("Fecha límite (dd/MM/yyyy):");
	    lblFechaLimite.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JTextField txtFechaLimite = new JTextField(10);
	    txtFechaLimite.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    panelCentro.add(lblFechaLimite);
	    panelCentro.add(txtFechaLimite);
	    
	    JButton btnGuardar = new JButton("Crear Actividad");
	    btnGuardar.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    btnGuardar.addActionListener(e -> {
	        try {
	            String titulo = txtTitulo.getText();
	            if (titulo.isEmpty()) throw new IllegalArgumentException("El título no puede estar vacío.");
	            
	            String descripcion = txtDescripcion.getText();
	            if (descripcion.isEmpty()) throw new IllegalArgumentException("La descripción no puede estar vacía.");
	            
	            String[] objetivosArray = txtObjetivos.getText().split(",");
	            List<String> objetivos = new ArrayList<>();
	            for (String obj : objetivosArray) {
	                if (!obj.trim().isEmpty()) {
	                    objetivos.add(obj.trim());
	                }
	            }
	            if (objetivos.isEmpty()) throw new IllegalArgumentException("Debe ingresar al menos un objetivo.");
	            
	            String dificultad = nivelesDificultad[cbDificultad.getSelectedIndex()];
	            
	            int duracion = Integer.parseInt(txtDuracion.getText().trim());
	            if (duracion <= 0) throw new IllegalArgumentException("La duración debe ser positiva.");
	            
	            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	            sdf.setLenient(false);
	            Date fechaLimite = sdf.parse(txtFechaLimite.getText().trim());
	            
	            aplicacion.crearTarea(titulo, descripcion, objetivos, dificultad, duracion, fechaLimite, profesor);
	            aplicacion.descargarDatos();
	            JOptionPane.showMessageDialog(this, "Actividad creada exitosamente.");
	            
	        } catch (IllegalArgumentException ex) {
	            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Validación", JOptionPane.ERROR_MESSAGE);
	        } catch (Exception ex) {
	            JOptionPane.showMessageDialog(this, "Error inesperado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    });
	    panelCentro.add(btnGuardar);
	    
	    this.add(panelCentro, BorderLayout.CENTER);
	    this.revalidate();
	    this.repaint();		
		
	}

	private void crearRevisarRecurso(Profesor profesor) {

		panelCentro.setLayout(new GridLayout(17,1));
	    

	    JLabel lblTitulo = new JLabel("Título del recurso:");
	    lblTitulo.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JTextField txtTitulo = new JTextField(20);
	    txtTitulo.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    panelCentro.add(lblTitulo);
	    panelCentro.add(txtTitulo);
	    
	    JLabel lblDescripcion = new JLabel("Descripción del recurso:");
	    lblDescripcion.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JTextArea txtDescripcion = new JTextArea(10, 20);
	    txtDescripcion.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JScrollPane scrollDescripcion = new JScrollPane(txtDescripcion);
	    panelCentro.add(lblDescripcion);
	    panelCentro.add(scrollDescripcion);
	    
	    JLabel lblObjetivos = new JLabel("Objetivos (separados por comas):");
	    lblObjetivos.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JTextField txtObjetivos = new JTextField(20);
	    txtObjetivos.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    panelCentro.add(lblObjetivos);
	    panelCentro.add(txtObjetivos);
	    
	    JLabel lblDificultad = new JLabel("Nivel de dificultad:");
	    lblDificultad.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    String[] nivelesDificultad = {"Principiante", "Intermedio", "Avanzado"};
	    JComboBox<String> cbDificultad = new JComboBox<>(nivelesDificultad);
	    cbDificultad.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    panelCentro.add(lblDificultad);
	    panelCentro.add(cbDificultad);
	    
	    JLabel lblDuracion = new JLabel("Duración (minutos):");
	    lblDuracion.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JTextField txtDuracion = new JTextField(10);
	    txtDuracion.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    panelCentro.add(lblDuracion);
	    panelCentro.add(txtDuracion);
	    
	    JLabel lblFechaLimite = new JLabel("Fecha límite (dd/MM/yyyy):");
	    lblFechaLimite.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JTextField txtFechaLimite = new JTextField(10);
	    txtFechaLimite.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    panelCentro.add(lblFechaLimite);
	    panelCentro.add(txtFechaLimite);
	    
	    JLabel lblTipoRecurso = new JLabel("Tipo de recurso:");
	    lblTipoRecurso.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JTextField txtTipoRecurso = new JTextField(20);
	    txtTipoRecurso.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    panelCentro.add(lblTipoRecurso);
	    panelCentro.add(txtTipoRecurso);
	    
	    JLabel lblEnlace = new JLabel("Enlace del recurso:");
	    lblEnlace.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JTextField txtEnlace = new JTextField(20);
	    txtEnlace.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    panelCentro.add(lblEnlace);
	    panelCentro.add(txtEnlace);
	    
	    JButton btnGuardar = new JButton("Crear Actividad");
	    btnGuardar.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    btnGuardar.addActionListener(e -> {
	        try {
	            String titulo = txtTitulo.getText();
	            if (titulo.isEmpty()) throw new IllegalArgumentException("El título no puede estar vacío.");
	            
	            String descripcion = txtDescripcion.getText();
	            if (descripcion.isEmpty()) throw new IllegalArgumentException("La descripción no puede estar vacía.");
	            
	            String[] objetivosArray = txtObjetivos.getText().split(",");
	            List<String> objetivos = new ArrayList<>();
	            for (String obj : objetivosArray) {
	                if (!obj.trim().isEmpty()) {
	                    objetivos.add(obj.trim());
	                }
	            }
	            if (objetivos.isEmpty()) throw new IllegalArgumentException("Debe ingresar al menos un objetivo.");
	            
	            String dificultad = nivelesDificultad[cbDificultad.getSelectedIndex()];
	            
	            int duracion = Integer.parseInt(txtDuracion.getText().trim());
	            if (duracion <= 0) throw new IllegalArgumentException("La duración debe ser positiva.");
	            
	            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	            sdf.setLenient(false);
	            Date fechaLimite = sdf.parse(txtFechaLimite.getText().trim());
	            
	            String tipoRecurso = txtTipoRecurso.getText().trim();
	            if (tipoRecurso.isEmpty()) throw new IllegalArgumentException("El tipo de recurso no puede estar vacío.");
	            
	            String enlace = txtEnlace.getText().trim();
	            if (enlace.isEmpty()) throw new IllegalArgumentException("El enlace no puede estar vacío.");
	            
	            aplicacion.crearRevisarRecurso(titulo, descripcion, objetivos, dificultad, duracion, fechaLimite, tipoRecurso, profesor, enlace);
	            aplicacion.descargarDatos();
	            JOptionPane.showMessageDialog(this, "Actividad creada exitosamente.");
	        } catch (IllegalArgumentException ex) {
	            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Validación", JOptionPane.ERROR_MESSAGE);
	        } catch (Exception ex) {
	            JOptionPane.showMessageDialog(this, "Error inesperado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    });
	    panelCentro.add(btnGuardar);
	    
	    this.add(panelCentro, BorderLayout.CENTER);
	    this.revalidate();
	    this.repaint();
	}
}

