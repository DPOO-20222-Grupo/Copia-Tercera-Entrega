package interfazProfesor;

import java.awt.*; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.*;

import actividades.Actividad;
import actividades.Examen;
import actividades.Tarea;
import exceptions.ActividadYaExistenteException;
import exceptions.EstudianteNoInscritoException;
import exceptions.LearningPathYaExistenteException;
import exceptions.ModificarObjetivosException;
import exceptions.TipoInvalidoValorException;
import interfaz.Aplicacion;
import interfazActividadesCompletadas.PanelActividadesDiarias;
import learningPath.LearningPath;
import preguntas.Pregunta;
import preguntas.PreguntaAbierta;
import preguntas.PreguntaCerrada;
import preguntas.PreguntaSeleccionMultiple;
import seguimientoEstudiantes.SeguimientoActividad;
import seguimientoEstudiantes.SeguimientoExamen;
import seguimientoEstudiantes.SeguimientoLearningPath;
import seguimientoEstudiantes.SeguimientoQuiz;
import seguimientoEstudiantes.SeguimientoTarea;
import user.Estudiante;
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
        
        this.add(new PanelActividadesDiarias(aplicacion, LocalDate.now()), BorderLayout.SOUTH);
        
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
	        String titulo = txtTitulo.getText();
	        String tipo = txtTipo.getText();
	        String loginProfesor = txtProfesor.getText();

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
	        String titulo = txtTitulo.getText();
	        String loginProfesor = txtProfesor.getText();

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
	            String titulo = txtTitulo.getText();
	            String profesor = txtProfesor.getText();
	            double calificacion = Double.parseDouble(txtCalificacion.getText());

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
		
		panelCentro.setLayout(new BorderLayout());
		
		JPanel panelFormulario = new JPanel(new GridLayout(15,1));

	    JLabel lblTitulo = new JLabel("Título de la actividad:");
	    lblTitulo.setFont(new Font("Times New Roman", Font.BOLD, 20));
	    JTextField txtTitulo = new JTextField();
	    txtTitulo.setFont(new Font("Times New Roman", Font.BOLD, 20));

	    JLabel lblProfesor = new JLabel("Login del profesor:");
	    lblProfesor.setFont(new Font("Times New Roman", Font.BOLD, 20));
	    JTextField txtProfesor = new JTextField();
	    txtProfesor.setFont(new Font("Times New Roman", Font.BOLD, 20));

	    JLabel lblTipo = new JLabel("Tipo de actividad:");
	    lblTipo.setFont(new Font("Times New Roman", Font.BOLD, 20));
	    JComboBox<String> comboTipo = new JComboBox<>(new String[]{"Examen", "Tarea", "Quiz", "Recurso", "Encuesta"});
	    comboTipo.setFont(new Font("Times New Roman", Font.BOLD, 20));
	    
	    JButton btnBuscarActividad = new JButton("Buscar Actividad");
	    btnBuscarActividad.setFont(new Font("Times New Roman", Font.BOLD, 20));

	    panelFormulario.add(lblTitulo);
	    panelFormulario.add(txtTitulo);
	    panelFormulario.add(lblProfesor);
	    panelFormulario.add(txtProfesor);
	    panelFormulario.add(lblTipo);
	    panelFormulario.add(comboTipo);
	    panelFormulario.add(btnBuscarActividad);

	    panelCentro.add(panelFormulario, BorderLayout.CENTER);

	    btnBuscarActividad.addActionListener(e -> {
	        String titulo = txtTitulo.getText();
	        String profesor = txtProfesor.getText();
	        String tipo = (String) comboTipo.getSelectedItem();

	        Actividad actividad = aplicacion.getActividad(titulo + " - "+ profesor, tipo); 

	        if (actividad != null) {
	            String[] opciones = {"Reseñar", "Calificar"};
	            int opcion = JOptionPane.showOptionDialog(
	                    panelCentro,
	                    "Seleccione la acción que desea realizar:",
	                    "Acción",
	                    JOptionPane.DEFAULT_OPTION,
	                    JOptionPane.QUESTION_MESSAGE,
	                    null,
	                    opciones,
	                    opciones[0]
	            );

	            if (opcion == 0) {
	                String resena = JOptionPane.showInputDialog(
	                		panelCentro,
	                        "Ingrese la reseña para la actividad:",
	                        "Reseñar",
	                        JOptionPane.PLAIN_MESSAGE
	                );
	                if (resena != null && !resena.isEmpty()) {
	                    aplicacion.resenarActividad(actividad, resena);
	                    aplicacion.descargarDatos();
	                    JOptionPane.showMessageDialog(panelCentro, "Reseña guardada exitosamente.");
	                }
	            } else if (opcion == 1) { 
	                String inputCalificacion = JOptionPane.showInputDialog(
	                		panelCentro,
	                        "Ingrese la calificación para la actividad:",
	                        "Calificar",
	                        JOptionPane.PLAIN_MESSAGE
	                );
	                try {
	                    double calificacion = Double.parseDouble(inputCalificacion);
	                    aplicacion.calificarActividad(actividad, calificacion);
	                    aplicacion.descargarDatos();
	                    JOptionPane.showMessageDialog(panelCentro, "Calificación guardada exitosamente.");
	                } catch (NumberFormatException ex) {
	                    JOptionPane.showMessageDialog(panelCentro, "Calificación inválida.", "Error", JOptionPane.ERROR_MESSAGE);
	                }
	            }
	        } else {
	            JOptionPane.showMessageDialog(panelCentro, "Actividad no encontrada.", "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    });

	    this.add(panelCentro, BorderLayout.CENTER);
	    this.revalidate();
	    this.repaint();
	}

	private void verProgresoLearningPathEstudiante() {
		
		panelCentro.setLayout(new BorderLayout());

	    JPanel panelEntrada = new JPanel(new GridLayout(8, 1));
	    JLabel lblLogin = new JLabel("Login del estudiante:");
	    lblLogin.setFont(new Font("Times New Roman", Font.BOLD, 20));
	    JTextField txtLogin = new JTextField();
	    txtLogin.setFont(new Font("Times New Roman", Font.BOLD, 20));
	    JLabel lblTituloLearningPath = new JLabel("Título del Learning Path:");
	    lblTituloLearningPath.setFont(new Font("Times New Roman", Font.BOLD, 20));
	    JTextField txtTituloLearningPath = new JTextField();
	    txtTituloLearningPath.setFont(new Font("Times New Roman", Font.BOLD, 20));
	    JLabel lblProfesor = new JLabel("Login del profesor creador del Learning Path:");
	    lblProfesor.setFont(new Font("Times New Roman", Font.BOLD, 20));
	    JTextField txtProfesor = new JTextField();
	    txtProfesor.setFont(new Font("Times New Roman", Font.BOLD, 20));
	    JButton btnConsultar = new JButton("Consultar");
	    btnConsultar.setFont(new Font("Times New Roman", Font.BOLD, 20));

	    panelEntrada.add(lblLogin);
	    panelEntrada.add(txtLogin);
	    panelEntrada.add(lblTituloLearningPath);
	    panelEntrada.add(txtTituloLearningPath);
	    panelEntrada.add(lblProfesor);
	    panelEntrada.add(txtProfesor);

	    JTextArea textAreaResultados = new JTextArea(20, 50);
	    textAreaResultados.setFont(new Font("Times New Roman", Font.BOLD, 20));
	    textAreaResultados.setEditable(false);
	    JScrollPane scrollPane = new JScrollPane(textAreaResultados);

	    panelCentro.add(panelEntrada, BorderLayout.NORTH);
	    panelCentro.add(scrollPane, BorderLayout.CENTER);

	    btnConsultar.addActionListener(e -> {
	        String login = txtLogin.getText();
	        String tituloLearningPath = txtTituloLearningPath.getText();
	        String loginProfesor = txtProfesor.getText();

	        if (login.isEmpty() || tituloLearningPath.isEmpty() || loginProfesor.isEmpty()) {
	            textAreaResultados.setText("Todos los campos son obligatorios. Por favor, complete todos los datos.");
	            return;
	        }

	        Estudiante estudiante = aplicacion.getEstudiante(login);
	        if (estudiante == null) {
	            textAreaResultados.setText("Estudiante no encontrado.");
	            return;
	        }

	        LearningPath learningPath = aplicacion.getLearningPath(tituloLearningPath + " - " + loginProfesor);
	        if (learningPath == null) {
	            textAreaResultados.setText("Learning Path no encontrado.");
	            return;
	        }

	        SeguimientoLearningPath seguimientoEstudiante = estudiante.getLearningPathsInscritos().get(learningPath.getIdLearnginPath());
	        if (seguimientoEstudiante == null) {
	            textAreaResultados.setText("El estudiante no está inscrito en el Learning Path seleccionado.");
	            return;
	        }

	        StringBuilder resultado = new StringBuilder();
	        resultado.append("--- Progreso del Learning Path ---\n");
	        resultado.append("Título: ").append(learningPath.getTitulo()).append("\n");
	        resultado.append("Descripción: ").append(learningPath.getDescripcion()).append("\n");
	        resultado.append(String.format("Progreso: %.2f%%\n", seguimientoEstudiante.getProgreso() * 100));
	        resultado.append(String.format("Porcentaje de Éxito: %.2f%%\n", seguimientoEstudiante.getTasaExito()));

	        resultado.append("\n-- Actividades --\n");
	        HashMap<String, SeguimientoActividad> mapaSeguimientos = seguimientoEstudiante.getMapaSeguimientoActividades();
	        for (SeguimientoActividad actividad : mapaSeguimientos.values()) {
	            String tipo = actividad.getActividadSeguimiento().getTipoActividad();
	            resultado.append("\nTítulo: ").append(actividad.getActividadSeguimiento().getTitulo()).append("\n");
	            resultado.append("Tipo: ").append(tipo).append("\n");
	            resultado.append("Estado: ").append(actividad.getEstado()).append("\n");

	            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	            String fecha = formatter.format(actividad.getActividadSeguimiento().getFechaLimite());
	            resultado.append("Fecha: ").append(fecha).append("\n");
	            resultado.append(String.format("Obligatoria: %b\n",
	                    learningPath.getMapaActividadesObligatorias().get(actividad.getActividadSeguimiento().getIdActividad())));

	            if (tipo.equals("Quiz")) {
	                SeguimientoQuiz segQuiz = (SeguimientoQuiz) actividad;
	                resultado.append(String.format("Calificación: %.2f\n", segQuiz.getNota()));
	            } else if (tipo.equals("Tarea")) {
	                SeguimientoTarea segTarea = (SeguimientoTarea) actividad;
	                resultado.append("Método de envío: ").append(segTarea.getMetodoEnvio()).append("\n");
	            } else if (tipo.equals("Examen")) {
	                SeguimientoExamen segExamen = (SeguimientoExamen) actividad;
	                resultado.append(String.format("Calificación: %.2f\n", segExamen.getNota()));
	            }
	        }

	        textAreaResultados.setText(resultado.toString());
	    });

	    panelEntrada.add(btnConsultar);

	    this.add(panelCentro, BorderLayout.CENTER);
	    this.revalidate();
	    this.repaint();
		
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

	    if (preguntasTexto.toString().isEmpty()) {
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

	private void modificarPregunta(Profesor profesor) {
		
		panelCentro.setLayout(new BorderLayout());

        JLabel lblTitulo = new JLabel("Modificar Pregunta", JLabel.CENTER);
        lblTitulo.setFont(new Font("Times New Roman", Font.BOLD, 18));
        panelCentro.add(lblTitulo, BorderLayout.NORTH);

        JPanel panelFormulario = new JPanel(new GridLayout(8, 2));
        JLabel lblIdPregunta = new JLabel("Ingrese el ID de la pregunta");
        lblIdPregunta.setFont(new Font("Times New Roman", Font.BOLD, 14));
        panelFormulario.add(lblIdPregunta);

        JTextField txtIdPregunta = new JTextField();
        txtIdPregunta.setFont(new Font("Times New Roman", Font.BOLD, 14));
        panelFormulario.add(txtIdPregunta);

        JLabel lblTipoPregunta = new JLabel("Seleccione el tipo de pregunta");
        lblTipoPregunta.setFont(new Font("Times New Roman", Font.BOLD, 14));
        panelFormulario.add(lblTipoPregunta);

        JComboBox<String> comboTipoPregunta = new JComboBox<>(new String[]{"Selección Múltiple", "Verdadero o Falso", "Abierta"});
        comboTipoPregunta.setFont(new Font("Times New Roman", Font.BOLD, 14));
        panelFormulario.add(comboTipoPregunta);

        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER)); 
        JButton btnBuscarModificar = new JButton("Buscar y Modificar");
        btnBuscarModificar.setFont(new Font("Times New Roman", Font.BOLD, 30));
        panelBoton.add(btnBuscarModificar);

        panelFormulario.add(new JLabel()); 
        panelFormulario.add(panelBoton);
        
        JTextArea txtResultado = new JTextArea(5, 30);
        txtResultado.setEditable(false);
        txtResultado.setFont(new Font("Times New Roman", Font.BOLD, 14));
        JScrollPane scrollResultado = new JScrollPane(txtResultado);
        panelCentro.add(scrollResultado, BorderLayout.SOUTH);

        panelCentro.add(panelFormulario, BorderLayout.CENTER);

        btnBuscarModificar.addActionListener(e -> {
            String idPregunta = txtIdPregunta.getText();
            int tipo = comboTipoPregunta.getSelectedIndex();
            String tipoString = switch (tipo) {
                case 0 -> "Cerrada";
                case 1 -> "Verdadero o Falso";
                case 2 -> "Abierta";
                default -> "";
            };

            if (idPregunta.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe ingresar el ID de la pregunta", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Pregunta pregunta = aplicacion.getPregunta(idPregunta, tipoString);
            if (pregunta != null) {
                txtResultado.setText(String.format("Pregunta encontrada\nID: %s\nTipo: %s\nTítulo: %s\n",
                        pregunta.getId(), tipoString, pregunta.getTitulo()));

                String[] opciones = switch (tipoString) {
                    case "Cerrada" -> new String[]{"Título", "Enunciado", "Opción Correcta", "Opciones Posibles"};
                    case "Verdadero o Falso" -> new String[]{"Título", "Enunciado", "Opción Correcta"};
                    case "Abierta" -> new String[]{"Título", "Enunciado"};
                    default -> new String[0];
                };

                String atributo = (String) JOptionPane.showInputDialog(this, "Seleccione el atributo a modificar",
                        "Modificar Pregunta", JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);

                if (atributo != null) {
                    String nuevoValor = JOptionPane.showInputDialog(this, "Ingrese el nuevo valor para " + atributo);
                    if (nuevoValor == null || nuevoValor.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "El valor ingresado no es válido", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (atributo.equals("Título")) {
                        aplicacion.modificarTituloPregunta(pregunta, nuevoValor, profesor);
                        aplicacion.descargarDatos();
                        JOptionPane.showMessageDialog(this, "Pregunta modificada con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        txtResultado.setText("");
                    } else if (atributo.equals("Enunciado")) {
                        aplicacion.modificarEnunciadoPregunta(pregunta, nuevoValor);
                        aplicacion.descargarDatos();
                        JOptionPane.showMessageDialog(this, "Pregunta modificada con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        txtResultado.setText("");
                    } else if (atributo.equals("Opción Correcta")) {
                        if (tipoString.equals("Verdadero o Falso")) {
                            int opcionCorrecta = nuevoValor.equalsIgnoreCase("Verdadero") ? 1 : 0;
                            aplicacion.modificarOpcionCorrectaPreguntaCerrada((PreguntaCerrada) pregunta, opcionCorrecta);
                            aplicacion.descargarDatos();
                            JOptionPane.showMessageDialog(this, "Pregunta modificada con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                            txtResultado.setText("");
                        } else if (tipoString.equals("Cerrada")) {
                            try {
                                int opcion = Integer.parseInt(nuevoValor);
                                aplicacion.modificarOpcionCorrectaPreguntaCerrada((PreguntaCerrada) pregunta, opcion);
                                aplicacion.descargarDatos();
                                JOptionPane.showMessageDialog(this, "Pregunta modificada con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                                txtResultado.setText("");
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(this, "La opción debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    } else if (atributo.equals("Opciones Posibles")) {
                        try {
                            int numOpcion = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese el número de la opción a modificar"));
                            aplicacion.modificarOpcionesPreguntaSeleccion((PreguntaSeleccionMultiple) pregunta, nuevoValor, numOpcion);
                            aplicacion.descargarDatos();
                            JOptionPane.showMessageDialog(this, "Pregunta modificada con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                            txtResultado.setText("");
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(this, "El número ingresado no es válido", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }

                }
            } else {
                txtResultado.setText("La pregunta no fue encontrada");
            }
            
        });
        
        this.add(panelCentro, BorderLayout.CENTER);
	    this.revalidate();
	    this.repaint();
		
	}

	private void calificarActividad(Profesor profesor) {
		
		panelCentro.setLayout(new BorderLayout());

	    Font fuente = new Font("Times New Roman", Font.BOLD, 14);

	    JPanel panelEntrada = new JPanel(new GridLayout(15, 1));

	    JLabel lblTipoActividad = new JLabel("Tipo de actividad (Examen/Tarea):");
	    lblTipoActividad.setFont(fuente);
	    JTextField txtTipoActividad = new JTextField();
	    txtTipoActividad.setFont(fuente);

	    JLabel lblTituloLearningPath = new JLabel("Título del Learning Path:");
	    lblTituloLearningPath.setFont(fuente);
	    JTextField txtTituloLearningPath = new JTextField();
	    txtTituloLearningPath.setFont(fuente);

	    JLabel lblLoginEstudiante = new JLabel("Login del estudiante:");
	    lblLoginEstudiante.setFont(fuente);
	    JTextField txtLoginEstudiante = new JTextField();
	    txtLoginEstudiante.setFont(fuente);

	    JLabel lblTituloActividad = new JLabel("Título de la actividad:");
	    lblTituloActividad.setFont(fuente);
	    JTextField txtTituloActividad = new JTextField();
	    txtTituloActividad.setFont(fuente);

	    JLabel lblLoginProfesor = new JLabel("Login del profesor creador:");
	    lblLoginProfesor.setFont(fuente);
	    JTextField txtLoginProfesor = new JTextField();
	    txtLoginProfesor.setFont(fuente);

	    JLabel lblNota = new JLabel("Nota (solo para examen):");
	    lblNota.setFont(fuente);
	    JTextField txtNota = new JTextField();
	    txtNota.setFont(fuente);

	    JLabel lblExitoso = new JLabel("¿Fue exitoso? (true/false):");
	    lblExitoso.setFont(fuente);
	    JTextField txtExitoso = new JTextField();
	    txtExitoso.setFont(fuente);

	    JButton btnCalificar = new JButton("Calificar");
	    btnCalificar.setFont(fuente);

	    panelEntrada.add(lblTipoActividad);
	    panelEntrada.add(txtTipoActividad);
	    panelEntrada.add(lblTituloLearningPath);
	    panelEntrada.add(txtTituloLearningPath);
	    panelEntrada.add(lblLoginEstudiante);
	    panelEntrada.add(txtLoginEstudiante);
	    panelEntrada.add(lblTituloActividad);
	    panelEntrada.add(txtTituloActividad);
	    panelEntrada.add(lblLoginProfesor);
	    panelEntrada.add(txtLoginProfesor);
	    panelEntrada.add(lblNota);
	    panelEntrada.add(txtNota);
	    panelEntrada.add(lblExitoso);
	    panelEntrada.add(txtExitoso);
	    panelEntrada.add(btnCalificar);

	    JTextArea textAreaResultados = new JTextArea(20, 50);
	    textAreaResultados.setFont(fuente);
	    textAreaResultados.setEditable(false);
	    JScrollPane scrollPane = new JScrollPane(textAreaResultados);

	    panelCentro.add(panelEntrada, BorderLayout.NORTH);
	    panelCentro.add(scrollPane, BorderLayout.CENTER);

	    btnCalificar.addActionListener(e -> {
	        String tipoActividad = txtTipoActividad.getText();
	        String tituloLearningPath = txtTituloLearningPath.getText();
	        String loginEstudiante = txtLoginEstudiante.getText();
	        String tituloActividad = txtTituloActividad.getText();
	        String loginProfesor = txtLoginProfesor.getText();
	        String notaStr = txtNota.getText();
	        String exitosoStr = txtExitoso.getText();

	        if (tipoActividad.isEmpty() || tituloLearningPath.isEmpty() || loginEstudiante.isEmpty() ||
	            tituloActividad.isEmpty() || loginProfesor.isEmpty() || exitosoStr.isEmpty() ||
	            (tipoActividad.equalsIgnoreCase("Examen") && notaStr.isEmpty())) {
	            textAreaResultados.setText("Todos los campos son obligatorios. Por favor, complete todos los datos.");
	            return;
	        }

	        LearningPath learningPath = aplicacion.getLearningPath(aplicacion.generarLlaveLearningsActividades(tituloLearningPath, profesor.getLogin()));
	        if (learningPath == null) {
	            textAreaResultados.setText("Learning Path no encontrado.");
	            return;
	        }

	        Estudiante estudiante = aplicacion.getEstudiante(loginEstudiante);
	        if (estudiante == null) {
	            textAreaResultados.setText("Estudiante no encontrado.");
	            return;
	        }

	        try {
	            if (tipoActividad.equalsIgnoreCase("Examen")) {
	                float nota = Float.parseFloat(notaStr);
	                boolean exitoso = Boolean.parseBoolean(exitosoStr);

	                Examen examen = (Examen) aplicacion.getActividad(tituloActividad + " - " + loginProfesor, "Examen");

	                if (examen != null) {
	                    aplicacion.calificarExamen(examen, estudiante, learningPath, nota, exitoso);
	                    textAreaResultados.setText("Examen calificado con éxito.");
	                } else {
	                    textAreaResultados.setText("Examen no encontrado.");
	                }
	            } else if (tipoActividad.equalsIgnoreCase("Tarea")) {
	                boolean exitoso = Boolean.parseBoolean(exitosoStr);

	                Tarea tarea = (Tarea) aplicacion.getActividad(tituloActividad + " - " + loginProfesor, "Tarea");

	                if (tarea != null) {
	                    aplicacion.calificarTarea(tarea, estudiante, learningPath, exitoso);
	                    textAreaResultados.setText("Tarea calificada con éxito.");
	                } else {
	                    textAreaResultados.setText("Tarea no encontrada.");
	                }
	            } else {
	                textAreaResultados.setText("Tipo de actividad no válido.");
	            }
	        } catch (NumberFormatException ex) {
	            textAreaResultados.setText("Error en el formato de los datos numéricos. Verifique la nota o el estado exitoso.");
	        } catch (EstudianteNoInscritoException ex) {
	            textAreaResultados.setText("Estudiante no inscrito en el Learning Path.");
	        }
	    });

	    panelCentro.revalidate();
	    panelCentro.repaint();
		
		
	}

	private void modificarActividad(Profesor profesor) {
		
		panelCentro.setLayout(new BorderLayout());

	    JLabel lblTitulo = new JLabel("Modificar Actividad ", JLabel.CENTER);
	    lblTitulo.setFont(new Font("Times New Roman", Font.BOLD, 18));
	    panelCentro.add(lblTitulo, BorderLayout.NORTH);

	    JPanel panelFormulario = new JPanel(new GridLayout(10, 2));
	    JLabel lblIdActividad = new JLabel("Ingrese el ID de la Actividad");
	    lblIdActividad.setFont(new Font("Times New Roman", Font.BOLD, 14));
	    panelFormulario.add(lblIdActividad);
	    JTextField txtIdActividad = new JTextField();
	    txtIdActividad.setFont(new Font("Times New Roman", Font.BOLD, 14));
	    panelFormulario.add(txtIdActividad);
	    
	    JLabel lbTipoActividad = new JLabel("Ingrese el tipo de la Actividad");
	    lbTipoActividad.setFont(new Font("Times New Roman", Font.BOLD, 14));
	    panelFormulario.add(lbTipoActividad);
	    JTextField txtIdTipo = new JTextField();
	    txtIdTipo.setFont(new Font("Times New Roman", Font.BOLD, 14));
	    panelFormulario.add(txtIdTipo);

	    JLabel lblTipoAtributo = new JLabel("Seleccione el atributo a modificar");
	    lblTipoAtributo.setFont(new Font("Times New Roman", Font.BOLD, 14));
	    panelFormulario.add(lblTipoAtributo);

	    JComboBox<String> comboTipoAtributo = new JComboBox<>(new String[]{"Titulo", "Descripcion", "Dificultad", "Objetivos", "Duracion"});
	    comboTipoAtributo.setFont(new Font("Times New Roman", Font.BOLD, 14));
	    panelFormulario.add(comboTipoAtributo);

	    JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
	    JButton btnModificar = new JButton("Modificar");
	    btnModificar.setFont(new Font("Times New Roman", Font.BOLD, 30));
	    panelBoton.add(btnModificar);

	    panelFormulario.add(new JLabel());
	    panelFormulario.add(panelBoton);

	    JTextArea txtResultado = new JTextArea(5, 30);
	    txtResultado.setEditable(false);
	    txtResultado.setFont(new Font("Times New Roman", Font.BOLD, 14));
	    JScrollPane scrollResultado = new JScrollPane(txtResultado);
	    panelCentro.add(scrollResultado, BorderLayout.SOUTH);

	    panelCentro.add(panelFormulario, BorderLayout.CENTER);

	    btnModificar.addActionListener(e -> {
	    	String tipoActividad = txtIdTipo.getText();
	        String idActividad = txtIdActividad.getText();
	        int tipo = comboTipoAtributo.getSelectedIndex();
	        String tipoString = switch (tipo) {
	            case 0 -> "Titulo";
	            case 1 -> "Descripcion";
	            case 2 -> "Dificultad";
	            case 3 -> "Objetivos";
	            case 4 -> "Duracion";
	            default -> "";
	        };

	        if (idActividad.isEmpty()) {
	            JOptionPane.showMessageDialog(this, "Debe ingresar el ID de la Actividad", "Error", JOptionPane.ERROR_MESSAGE);
	            return;
	        }

	        Actividad actividad = aplicacion.getActividad(idActividad, tipoActividad);
	        if (actividad == null) {
	            JOptionPane.showMessageDialog(this, "Actividad no encontrada.", "Error", JOptionPane.ERROR_MESSAGE);
	            return;
	        }

	        String nuevoValor = JOptionPane.showInputDialog(this, "Ingrese el nuevo valor para " + tipoString + ":");
	        if (nuevoValor == null || nuevoValor.isEmpty()) {
	            JOptionPane.showMessageDialog(this, "El nuevo valor no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
	            return;
	        }

	        switch (tipo) {
	            case 0: 
				try {
					aplicacion.modificarActividad(actividad, nuevoValor, tipoString, null , null , null);
				} catch (ModificarObjetivosException e1) {
					e1.printStackTrace();
				}
	                aplicacion.descargarDatos();
	                break;
	            case 1: 
				try {
					aplicacion.modificarActividad(actividad, nuevoValor, tipoString, null , null , null);
				} catch (ModificarObjetivosException e1) {
					e1.printStackTrace();
				}
	                aplicacion.descargarDatos();
	                break;
	            case 2: 
				try {
					aplicacion.modificarActividad(actividad, nuevoValor, tipoString, null , null , null);
				} catch (ModificarObjetivosException e1) {
					e1.printStackTrace();
				}
	                aplicacion.descargarDatos();
	                break;
	            case 3: 
	            	String accion = JOptionPane.showInputDialog(this, "Ingrese la accion a realizar (Agregar/Eliminar) " + tipoString + ":");	
	            	
            	try {
            		aplicacion.modificarActividad(actividad, nuevoValor, tipoString, accion , null , null);
				} catch (ModificarObjetivosException e1) {
					e1.printStackTrace();
				}
	                aplicacion.descargarDatos();            	
	                
	                break;
	            
	            case 4: 
	            	
	            	String duracion = JOptionPane.showInputDialog(this, "Ingrese la duracion de la actividad:");	
	            	
					try {
						aplicacion.modificarActividad(actividad, nuevoValor, tipoString, null , null , Integer.parseInt(duracion));
					} catch (ModificarObjetivosException e1) {
						e1.printStackTrace();
					}
		                aplicacion.descargarDatos();
		                break;
	                
	                
	            default:
	                JOptionPane.showMessageDialog(this, "Opción inválida", "Error", JOptionPane.ERROR_MESSAGE);
	                return;
	        }

	        JOptionPane.showMessageDialog(this, "El atributo " + tipoString + " ha sido modificado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	        
	        txtResultado.setText("Actividad modificada:\n" +
	                "ID: " + idActividad + "\n" +
	                "Atributo modificado: " + tipoString + "\n" +
	                "Nuevo valor: " + nuevoValor);
	    });

	    this.add(panelCentro, BorderLayout.CENTER);
	    this.revalidate();
	    this.repaint();
		
	}

	private void modificarLearningPath(Profesor profesor) {
		
		panelCentro.setLayout(new BorderLayout());

	    JLabel lblTitulo = new JLabel("Modificar Learning Path", JLabel.CENTER);
	    lblTitulo.setFont(new Font("Times New Roman", Font.BOLD, 18));
	    panelCentro.add(lblTitulo, BorderLayout.NORTH);

	    JPanel panelFormulario = new JPanel(new GridLayout(8, 2));
	    JLabel lblIdLearning = new JLabel("Ingrese el ID del Learning Path");
	    lblIdLearning.setFont(new Font("Times New Roman", Font.BOLD, 14));
	    panelFormulario.add(lblIdLearning);

	    JTextField txtIdLearning = new JTextField();
	    txtIdLearning.setFont(new Font("Times New Roman", Font.BOLD, 14));
	    panelFormulario.add(txtIdLearning);

	    JLabel lblTipoAtributo = new JLabel("Seleccione el atributo a modificar");
	    lblTipoAtributo.setFont(new Font("Times New Roman", Font.BOLD, 14));
	    panelFormulario.add(lblTipoAtributo);

	    JComboBox<String> comboTipoAtributo = new JComboBox<>(new String[]{"Titulo", "Descripcion", "Dificultad", "Objetivos"});
	    comboTipoAtributo.setFont(new Font("Times New Roman", Font.BOLD, 14));
	    panelFormulario.add(comboTipoAtributo);

	    JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
	    JButton btnModificar = new JButton("Modificar");
	    btnModificar.setFont(new Font("Times New Roman", Font.BOLD, 30));
	    panelBoton.add(btnModificar);

	    panelFormulario.add(new JLabel());
	    panelFormulario.add(panelBoton);

	    JTextArea txtResultado = new JTextArea(5, 30);
	    txtResultado.setEditable(false);
	    txtResultado.setFont(new Font("Times New Roman", Font.BOLD, 14));
	    JScrollPane scrollResultado = new JScrollPane(txtResultado);
	    panelCentro.add(scrollResultado, BorderLayout.SOUTH);

	    panelCentro.add(panelFormulario, BorderLayout.CENTER);

	    btnModificar.addActionListener(e -> {
	        String idLearning = txtIdLearning.getText();
	        int tipo = comboTipoAtributo.getSelectedIndex();
	        String tipoString = switch (tipo) {
	            case 0 -> "Titulo";
	            case 1 -> "Descripcion";
	            case 2 -> "Dificultad";
	            case 3 -> "Objetivos";
	            default -> "";
	        };

	        if (idLearning.isEmpty()) {
	            JOptionPane.showMessageDialog(this, "Debe ingresar el ID del Learning Path", "Error", JOptionPane.ERROR_MESSAGE);
	            return;
	        }

	        LearningPath learningPath = profesor.getLearningPathPropios().get(idLearning);
	        if (learningPath == null) {
	            JOptionPane.showMessageDialog(this, "Learning Path no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
	            return;
	        }

	        String nuevoValor = JOptionPane.showInputDialog(this, "Ingrese el nuevo valor para " + tipoString + ":");
	        if (nuevoValor == null || nuevoValor.isEmpty()) {
	            JOptionPane.showMessageDialog(this, "El nuevo valor no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
	            return;
	        }

	        switch (tipo) {
	            case 0: 
				try {
					aplicacion.modificarAtributosStringLearningPath(learningPath, tipoString, nuevoValor, "");
				} catch (TipoInvalidoValorException | ModificarObjetivosException e1) {
					e1.printStackTrace();
				}
	                aplicacion.descargarDatos();
	                break;
	            case 1: 
				try {
					aplicacion.modificarAtributosStringLearningPath(learningPath, tipoString, nuevoValor, "");
				} catch (TipoInvalidoValorException | ModificarObjetivosException e1) {
					e1.printStackTrace();
				}
	                aplicacion.descargarDatos();
	                break;
	            case 2: 
				try {
					aplicacion.modificarAtributosStringLearningPath(learningPath, tipoString, nuevoValor, "");
				} catch (TipoInvalidoValorException | ModificarObjetivosException e1) {
					e1.printStackTrace();
				}
	                aplicacion.descargarDatos();
	                break;
	            case 3: 
	            	String accion = JOptionPane.showInputDialog(this, "Ingrese la accion a realizar (Agregar/Eliminar) " + tipoString + ":");	
	            	
            	try {
					aplicacion.modificarAtributosStringLearningPath(learningPath, tipoString, nuevoValor, accion);
				} catch (TipoInvalidoValorException | ModificarObjetivosException e1) {
					e1.printStackTrace();
				}
	                aplicacion.descargarDatos();            	
	                
	                break;
	            default:
	                JOptionPane.showMessageDialog(this, "Opción inválida", "Error", JOptionPane.ERROR_MESSAGE);
	                return;
	        }

	        JOptionPane.showMessageDialog(this, "El atributo " + tipoString + " ha sido modificado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	        
	        txtResultado.setText("Learning Path modificado:\n" +
	                "ID: " + idLearning + "\n" +
	                "Atributo modificado: " + tipoString + "\n" +
	                "Nuevo valor: " + nuevoValor);
	    });

	    this.add(panelCentro, BorderLayout.CENTER);
	    this.revalidate();
	    this.repaint();
		
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

	private void crearPregunta(Profesor profesor) {
		
		panelCentro.setLayout(new BorderLayout());
	    JPanel panelEtiquetas = new JPanel();
	    panelEtiquetas.setLayout(new GridLayout(0,1));
		
		
	    JLabel lblTitulo = new JLabel("Título de la pregunta:");
	    lblTitulo.setFont(new Font("Times New Roman", Font.BOLD, 14));
	    JTextField txtTitulo = new JTextField();
	    txtTitulo.setFont(new Font("Times New Roman", Font.BOLD, 14));

	    JLabel lblEnunciado = new JLabel("Enunciado de la pregunta:");
	    lblEnunciado.setFont(new Font("Times New Roman", Font.BOLD, 14));
	    JTextField txtEnunciado = new JTextField();
	    txtEnunciado.setFont(new Font("Times New Roman", Font.BOLD, 14));

	    JLabel lblTipo = new JLabel("Seleccione el tipo de pregunta:");
	    lblTipo.setFont(new Font("Times New Roman", Font.BOLD, 14));
	    String[] opcionesTipo = {"Selección Múltiple", "Verdadero o Falso", "Abierta"};
	    JComboBox<String> comboTipo = new JComboBox<>(opcionesTipo);
	    comboTipo.setFont(new Font("Times New Roman", Font.BOLD, 14));

	    JPanel panelOpciones = new JPanel(new GridLayout(0, 1));

	    JButton btnRegistrar = new JButton("Registrar Pregunta");
	    btnRegistrar.setFont(new Font("Times New Roman", Font.BOLD, 14));

	    comboTipo.addActionListener(e -> {
	        panelOpciones.removeAll();
	        String tipoSeleccionado = (String) comboTipo.getSelectedItem();
	        
	        if ("Selección Múltiple".equals(tipoSeleccionado)) {
	            JLabel lblOpcion1 = new JLabel("Opción 1:");
	            lblOpcion1.setFont(new Font("Times New Roman", Font.BOLD, 14));
	            panelOpciones.add(lblOpcion1);
	            JTextField txtOpcion1 = new JTextField();
	            panelOpciones.add(txtOpcion1);

	            JLabel lblOpcion2 = new JLabel("Opción 2:");
	            lblOpcion2.setFont(new Font("Times New Roman", Font.BOLD, 14));
	            panelOpciones.add(lblOpcion2);
	            JTextField txtOpcion2 = new JTextField();
	            panelOpciones.add(txtOpcion2);

	            JLabel lblOpcion3 = new JLabel("Opción 3:");
	            lblOpcion3.setFont(new Font("Times New Roman", Font.BOLD, 14));
	            panelOpciones.add(lblOpcion3);
	            JTextField txtOpcion3 = new JTextField();
	            panelOpciones.add(txtOpcion3);

	            JLabel lblOpcion4 = new JLabel("Opción 4:");
	            lblOpcion4.setFont(new Font("Times New Roman", Font.BOLD, 14));
	            panelOpciones.add(lblOpcion4);
	            JTextField txtOpcion4 = new JTextField();
	            panelOpciones.add(txtOpcion4);

	            JLabel lblOpcionCorrecta = new JLabel("Número de la opción correcta:");
	            lblOpcionCorrecta.setFont(new Font("Times New Roman", Font.BOLD, 14));
	            panelOpciones.add(lblOpcionCorrecta);
	            JTextField txtOpcionCorrecta = new JTextField();
	            panelOpciones.add(txtOpcionCorrecta);
	        } else if ("Verdadero o Falso".equals(tipoSeleccionado)) {
	            JLabel lblBoolean = new JLabel("Seleccione la respuesta correcta:");
	            lblBoolean.setFont(new Font("Times New Roman", Font.BOLD, 14));
	            panelOpciones.add(lblBoolean);

	            String[] opcionesBoolean = {"Falso (0)", "Verdadero (1)"};
	            JComboBox<String> comboBoolean = new JComboBox<>(opcionesBoolean);
	            comboBoolean.setFont(new Font("Times New Roman", Font.BOLD, 14));
	            panelOpciones.add(comboBoolean);
	        } else if ("Abierta".equals(tipoSeleccionado)) {
	            JLabel lblAbierta = new JLabel("No hay opciones adicionales para preguntas abiertas.");
	            lblAbierta.setFont(new Font("Times New Roman", Font.BOLD, 14));
	            panelOpciones.add(lblAbierta);
	        }

	        
	        panelOpciones.revalidate();
	        panelOpciones.repaint();
	    });

	    btnRegistrar.addActionListener(e -> {
	        try {
	            String titulo = txtTitulo.getText();
	            String enunciado = txtEnunciado.getText();
	            String tipoSeleccionado = (String) comboTipo.getSelectedItem();

	            if (titulo.isEmpty() || enunciado.isEmpty()) {
	                throw new IllegalArgumentException("El título y el enunciado son obligatorios.");
	            }

	            if ("Selección Múltiple".equals(tipoSeleccionado)) {
	                Component[] components = panelOpciones.getComponents();
	                String opcion1 = ((JTextField) components[1]).getText();
	                String opcion2 = ((JTextField) components[3]).getText();
	                String opcion3 = ((JTextField) components[5]).getText();
	                String opcion4 = ((JTextField) components[7]).getText();
	                int opcionCorrecta = Integer.parseInt(((JTextField) components[9]).getText());

	                aplicacion.crearPreguntaSeleccion(enunciado, titulo, opcion1, opcion2, opcion3, opcion4, opcionCorrecta, profesor);
	                aplicacion.descargarDatos();
	            } else if ("Verdadero o Falso".equals(tipoSeleccionado)) {
	                JComboBox<String> comboBoolean = (JComboBox<String>) panelOpciones.getComponent(1);
	                int opcionCorrecta = comboBoolean.getSelectedIndex();
	                aplicacion.crearPreguntaBoolean(enunciado, titulo, opcionCorrecta, profesor);
	                aplicacion.descargarDatos();
	            } else if ("Abierta".equals(tipoSeleccionado)) {
	                aplicacion.crearPreguntaAbierta(enunciado, titulo, profesor);
	                aplicacion.descargarDatos();
	            }

	            JOptionPane.showMessageDialog(
	                null, "¡Pregunta registrada con éxito!",
	                "Éxito", JOptionPane.INFORMATION_MESSAGE
	            );
	        } catch (NumberFormatException ex) {
	            JOptionPane.showMessageDialog(
	                null, "La opción correcta debe ser un número válido.",
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

	    panelEtiquetas.add(lblTitulo);
	    panelEtiquetas.add(txtTitulo);
	    panelEtiquetas.add(lblEnunciado);
	    panelEtiquetas.add(txtEnunciado);
	    panelEtiquetas.add(lblTipo);
	    panelEtiquetas.add(comboTipo);
	    panelCentro.add(panelEtiquetas, BorderLayout.NORTH);
	    panelCentro.add(panelOpciones, BorderLayout.CENTER);
	    panelCentro.add(btnRegistrar, BorderLayout.SOUTH);

	    this.add(panelCentro, BorderLayout.CENTER);
	    this.revalidate();
	    this.repaint();
		
	}

	private void crearLearningPath(Profesor profesor) {
	    panelCentro.setLayout(new GridLayout(16, 1));

	    JLabel lblTitulo = new JLabel("Título del Learning Path:");
	    lblTitulo.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JTextField txtTitulo = new JTextField(20);
	    txtTitulo.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    panelCentro.add(lblTitulo);
	    panelCentro.add(txtTitulo);

	    JLabel lblDescripcion = new JLabel("Descripción del Learning Path:");
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

	    JButton btnAgregarActividad = new JButton("Agregar Actividad");
	    btnAgregarActividad.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    panelCentro.add(btnAgregarActividad);

	    DefaultListModel<String> modelActividades = new DefaultListModel<>();
	    JList<String> listActividades = new JList<>(modelActividades);
	    listActividades.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JScrollPane scrollActividades = new JScrollPane(listActividades);
	    panelCentro.add(scrollActividades);

	    JButton btnGuardar = new JButton("Crear Learning Path");
	    btnGuardar.setFont(new Font("Times New Roman", Font.BOLD, 16));

	    btnAgregarActividad.addActionListener(e -> {
	        String tituloActividad = JOptionPane.showInputDialog(this, "Ingrese el título de la actividad:");
	        if (tituloActividad == null || tituloActividad.isEmpty()) {
	            JOptionPane.showMessageDialog(this, "El título de la actividad no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
	            return;
	        }

	        String loginProfesor = JOptionPane.showInputDialog(this, "Ingrese el login del profesor creador de la actividad:");
	        if (loginProfesor == null || loginProfesor.isEmpty()) {
	            JOptionPane.showMessageDialog(this, "El login del profesor no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
	            return;
	        }

	        String tipoActividad = JOptionPane.showInputDialog(this, "Ingrese el tipo de la actividad:");
	        if (tipoActividad == null || tipoActividad.isEmpty()) {
	            JOptionPane.showMessageDialog(this, "El tipo de la actividad no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
	            return;
	        }

	        // Verificar que la actividad no sea null
	        Actividad actividad = aplicacion.getActividad(tituloActividad + " - " + loginProfesor, tipoActividad);
	        if (actividad == null) {
	            JOptionPane.showMessageDialog(this, "No se encontró la actividad con esos parámetros.", "Error", JOptionPane.ERROR_MESSAGE);
	            return;
	        }

	        modelActividades.addElement(actividad.getTitulo());
	    });

	    btnGuardar.addActionListener(e -> {
	        try {
	            String titulo = txtTitulo.getText();
	            if (titulo.isEmpty()) throw new IllegalArgumentException("El título no puede estar vacío.");

	            String descripcion = txtDescripcion.getText();
	            if (descripcion.isEmpty()) throw new IllegalArgumentException("La descripción no puede estar vacía.");

	            String[] objetivosArray = txtObjetivos.getText().split(",");
	            List<String> objetivos = new ArrayList<>();
	            for (String obj : objetivosArray) {
	                if (!obj.isEmpty()) {
	                    objetivos.add(obj);
	                }
	            }
	            if (objetivos.isEmpty()) throw new IllegalArgumentException("Debe ingresar al menos un objetivo.");

	            String dificultad = nivelesDificultad[cbDificultad.getSelectedIndex()];

	            List<Actividad> actividades = new ArrayList<>();
	            for (int i = 0; i < modelActividades.getSize(); i++) {
	                String tituloActividad = modelActividades.getElementAt(i);
	                String loginProfesor = JOptionPane.showInputDialog(this, "Ingrese el login del profesor para la actividad '" + tituloActividad + "':");
	                String tipoActividad = JOptionPane.showInputDialog(this, "Ingrese el tipo de la actividad '" + tituloActividad + "':");

	                // Verificar que la actividad no sea null
	                Actividad actividad = aplicacion.getActividad(tituloActividad + " - " + loginProfesor, tipoActividad);
	                if (actividad == null) {
	                    JOptionPane.showMessageDialog(this, "No se encontró la actividad con esos parámetros.", "Error", JOptionPane.ERROR_MESSAGE);
	                    return;
	                }

	                actividades.add(actividad);
	            }

	            if (actividades.isEmpty()) {
	                JOptionPane.showMessageDialog(this, "Debe agregar al menos una actividad al Learning Path.", "Error", JOptionPane.ERROR_MESSAGE);
	                return;
	            }

	            Map<String, Boolean> mapaObligatoriedad = new HashMap<>();
	            for (Actividad actividad : actividades) {
	                int respuesta = JOptionPane.showConfirmDialog(this, "¿La actividad '" + actividad.getTitulo() + "' es obligatoria?", "Obligatoriedad", JOptionPane.YES_NO_OPTION);
	                mapaObligatoriedad.put(actividad.getTitulo(), respuesta == JOptionPane.YES_OPTION);
	            }

	            aplicacion.crearLearningPath(titulo, descripcion, objetivos, dificultad, profesor, actividades, mapaObligatoriedad);
	            aplicacion.descargarDatos();
	            JOptionPane.showMessageDialog(this, "Learning Path creado exitosamente.");

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
	                if (!obj.isEmpty()) {
	                    objetivos.add(obj);
	                }
	            }
	            if (objetivos.isEmpty()) throw new IllegalArgumentException("Debe ingresar al menos un objetivo.");
	            
	            String dificultad = nivelesDificultad[cbDificultad.getSelectedIndex()];
	            
	            int duracion = Integer.parseInt(txtDuracion.getText());
	            if (duracion <= 0) throw new IllegalArgumentException("La duración debe ser positiva.");
	            
	            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	            sdf.setLenient(false);
	            Date fechaLimite = sdf.parse(txtFechaLimite.getText());
	            
	            String[] preguntasArray = txtPreguntas.getText().split(";");
	            List<PreguntaAbierta> preguntas = new ArrayList<>();
	            for (String preg : preguntasArray) {
	            	
	            	String[] partes = preg.split("\\|");
	            	String tituloPregunta = partes[0];
	                String enunciado = partes[1];
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
	                if (!obj.isEmpty()) {
	                    objetivos.add(obj);
	                }
	            }
	            if (objetivos.isEmpty()) throw new IllegalArgumentException("Debe ingresar al menos un objetivo.");
	            
	            String dificultad = nivelesDificultad[cbDificultad.getSelectedIndex()];
	            
	            int duracion = Integer.parseInt(txtDuracion.getText());
	            if (duracion <= 0) throw new IllegalArgumentException("La duración debe ser positiva.");
	            
	            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	            sdf.setLenient(false);
	            Date fechaLimite = sdf.parse(txtFechaLimite.getText());
	            
	            String[] preguntasArray = txtPreguntas.getText().split(";");
	            List<PreguntaAbierta> preguntas = new ArrayList<>();
	            for (String preg : preguntasArray) {
	            	
	            	String[] partes = preg.split("\\|");
	            	String tituloPregunta = partes[0];
	                String enunciado = partes[1];
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
                    if (!obj.isEmpty()) {
                        objetivos.add(obj);
                    }
                }
                if (objetivos.isEmpty()) throw new IllegalArgumentException("Debe ingresar al menos un objetivo.");

                String dificultad = nivelesDificultad[cbDificultad.getSelectedIndex()];

                int duracion = Integer.parseInt(txtDuracion.getText());
                if (duracion <= 0) throw new IllegalArgumentException("La duración debe ser positiva.");

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                sdf.setLenient(false);
                Date fechaLimite = sdf.parse(txtFechaLimite.getText());

                double calificacionMinima = Double.parseDouble(txtCalificacionMinima.getText());
                if (calificacionMinima <= 0) throw new IllegalArgumentException("La calificación mínima debe ser positiva.");

                String[] preguntasArray = txtPreguntas.getText().split(";");
                List<PreguntaCerrada> preguntas = new ArrayList<>();
                for (String preg : preguntasArray) {
                    String[] partes = preg.split("\\|");
                    if (partes.length != 2) {
                        throw new IllegalArgumentException("Formato de pregunta inválido. Debe ser título|enunciado.");
                    }
                    String tituloPregunta = partes[0];
                    String enunciado = partes[1];

                    String opcion1 = txtOpcion1.getText();
                    String opcion2 = txtOpcion2.getText();
                    String opcion3 = txtOpcion3.getText();
                    String opcion4 = txtOpcion4.getText();

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
	                if (!obj.isEmpty()) {
	                    objetivos.add(obj);
	                }
	            }
	            if (objetivos.isEmpty()) throw new IllegalArgumentException("Debe ingresar al menos un objetivo.");
	            
	            String dificultad = nivelesDificultad[cbDificultad.getSelectedIndex()];
	            
	            int duracion = Integer.parseInt(txtDuracion.getText());
	            if (duracion <= 0) throw new IllegalArgumentException("La duración debe ser positiva.");
	            
	            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	            sdf.setLenient(false);
	            Date fechaLimite = sdf.parse(txtFechaLimite.getText());
	            
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
	                if (!obj.isEmpty()) {
	                    objetivos.add(obj);
	                }
	            }
	            if (objetivos.isEmpty()) throw new IllegalArgumentException("Debe ingresar al menos un objetivo.");
	            
	            String dificultad = nivelesDificultad[cbDificultad.getSelectedIndex()];
	            
	            int duracion = Integer.parseInt(txtDuracion.getText());
	            if (duracion <= 0) throw new IllegalArgumentException("La duración debe ser positiva.");
	            
	            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	            sdf.setLenient(false);
	            Date fechaLimite = sdf.parse(txtFechaLimite.getText());
	            
	            String tipoRecurso = txtTipoRecurso.getText();
	            if (tipoRecurso.isEmpty()) throw new IllegalArgumentException("El tipo de recurso no puede estar vacío.");
	            
	            String enlace = txtEnlace.getText();
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

