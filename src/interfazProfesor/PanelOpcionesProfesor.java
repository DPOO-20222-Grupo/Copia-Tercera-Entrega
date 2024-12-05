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
import interfaz.Aplicacion;
import preguntas.PreguntaAbierta;
import user.Profesor;

@SuppressWarnings("serial")
public class PanelOpcionesProfesor extends JPanel implements ActionListener {

    private Profesor profesor;
    private Aplicacion aplicacion;

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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
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
		// TODO Auto-generated method stub
		
	}

	private void verCalificacionLP() {
		// TODO Auto-generated method stub
		
	}

	private void calificarLearningPath() {
		// TODO Auto-generated method stub
		
	}

	private void calificarResenarActividad() {
		// TODO Auto-generated method stub
		
	}

	private void verProgresoLearningPathEstudiante() {
		// TODO Auto-generated method stub
		
	}

	private void verPreguntas(Profesor profesor2) {
		// TODO Auto-generated method stub
		
	}

	private void verLearningPaths(Profesor profesor2) {
		// TODO Auto-generated method stub
		
	}

	private void verActividades(Profesor profesor2) {
		// TODO Auto-generated method stub
		
	}

	private void revisarLearningPathRepetido(Profesor profesor2) {
		// TODO Auto-generated method stub
		
	}

	private void revisarActividadRepetida(Profesor profesor2) {
		// TODO Auto-generated method stub
		
	}

	private void modificarPregunta(Profesor profesor2) {
		// TODO Auto-generated method stub
		
	}

	private void calificarActividad(Profesor profesor2) {
		// TODO Auto-generated method stub
		
	}

	private void modificarActividad(Profesor profesor2) {
		// TODO Auto-generated method stub
		
	}

	private void modificarLearningPath(Profesor profesor2) {
		// TODO Auto-generated method stub
		
	}

	private void clonarLearningPath(Profesor profesor2) {
		// TODO Auto-generated method stub
		
	}

	private void clonarActividad(Profesor profesor) {
		
		JPanel panelRecurso = new JPanel();
	    panelRecurso.setLayout(new GridLayout(17,1));
	    

	    JLabel lblTitulo = new JLabel("Título de la Actividad:");
	    lblTitulo.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JTextField txtTitulo = new JTextField(20);
	    txtTitulo.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    panelRecurso.add(lblTitulo);
	    panelRecurso.add(txtTitulo);
	    
	    JLabel lblProfesor = new JLabel("Login del Profesor Dueño de la Actividad:");
	    lblProfesor.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JTextField txtProfesor = new JTextField(20);
	    txtProfesor.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    panelRecurso.add(lblProfesor);
	    panelRecurso.add(txtProfesor);
	    
	    JLabel lblTipo = new JLabel("Tipo de la actividad que desea clonar: ");
	    lblTipo.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JLabel lblOpciones = new JLabel("(Examen/Tarea/Quiz/Recurso/Encuesta)");
	    lblOpciones.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JTextField txtTipo = new JTextField(20);
	    txtTipo.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    panelRecurso.add(lblTipo);
	    panelRecurso.add(lblOpciones);
	    panelRecurso.add(txtTipo);
	    
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
	    panelRecurso.add(btnGuardar);
	    
	    this.add(panelRecurso, BorderLayout.CENTER);
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
		JPanel panelRecurso = new JPanel();
	    panelRecurso.setLayout(new GridLayout(17,1));
	    

	    JLabel lblTitulo = new JLabel("Título de la Encuesta:");
	    lblTitulo.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JTextField txtTitulo = new JTextField(20);
	    txtTitulo.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    panelRecurso.add(lblTitulo);
	    panelRecurso.add(txtTitulo);
	    
	    JLabel lblDescripcion = new JLabel("Descripción de la Encuesta:");
	    lblDescripcion.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JTextArea txtDescripcion = new JTextArea(10, 20);
	    txtDescripcion.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JScrollPane scrollDescripcion = new JScrollPane(txtDescripcion);
	    panelRecurso.add(lblDescripcion);
	    panelRecurso.add(scrollDescripcion);
	    
	    JLabel lblObjetivos = new JLabel("Objetivos (separados por comas):");
	    lblObjetivos.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JTextField txtObjetivos = new JTextField(20);
	    txtObjetivos.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    panelRecurso.add(lblObjetivos);
	    panelRecurso.add(txtObjetivos);
	    
	    JLabel lblDificultad = new JLabel("Nivel de dificultad:");
	    lblDificultad.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    String[] nivelesDificultad = {"Principiante", "Intermedio", "Avanzado"};
	    JComboBox<String> cbDificultad = new JComboBox<>(nivelesDificultad);
	    cbDificultad.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    panelRecurso.add(lblDificultad);
	    panelRecurso.add(cbDificultad);
	    
	    JLabel lblDuracion = new JLabel("Duración (minutos):");
	    lblDuracion.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JTextField txtDuracion = new JTextField(10);
	    txtDuracion.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    panelRecurso.add(lblDuracion);
	    panelRecurso.add(txtDuracion);
	    
	    JLabel lblFechaLimite = new JLabel("Fecha límite (dd/MM/yyyy):");
	    lblFechaLimite.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JTextField txtFechaLimite = new JTextField(10);
	    txtFechaLimite.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    panelRecurso.add(lblFechaLimite);
	    panelRecurso.add(txtFechaLimite);
	    
	    JLabel lblPreguntas = new JLabel("Preguntas en formato titulo|enunciado separadas por ;");
	    lblPreguntas.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JTextField txtPreguntas = new JTextField(20);
	    txtPreguntas.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    panelRecurso.add(lblPreguntas);
	    panelRecurso.add(txtPreguntas);
	    
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
	    panelRecurso.add(btnGuardar);
	    
	    this.add(panelRecurso, BorderLayout.CENTER);
	    this.revalidate();
	    this.repaint();
		
	}

	private void crearExamen(Profesor profesor) {
		
		JPanel panelRecurso = new JPanel();
	    panelRecurso.setLayout(new GridLayout(17,1));
	    

	    JLabel lblTitulo = new JLabel("Título del Examen:");
	    lblTitulo.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JTextField txtTitulo = new JTextField(20);
	    txtTitulo.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    panelRecurso.add(lblTitulo);
	    panelRecurso.add(txtTitulo);
	    
	    JLabel lblDescripcion = new JLabel("Descripción del Examen:");
	    lblDescripcion.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JTextArea txtDescripcion = new JTextArea(10, 20);
	    txtDescripcion.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JScrollPane scrollDescripcion = new JScrollPane(txtDescripcion);
	    panelRecurso.add(lblDescripcion);
	    panelRecurso.add(scrollDescripcion);
	    
	    JLabel lblObjetivos = new JLabel("Objetivos (separados por comas):");
	    lblObjetivos.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JTextField txtObjetivos = new JTextField(20);
	    txtObjetivos.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    panelRecurso.add(lblObjetivos);
	    panelRecurso.add(txtObjetivos);
	    
	    JLabel lblDificultad = new JLabel("Nivel de dificultad:");
	    lblDificultad.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    String[] nivelesDificultad = {"Principiante", "Intermedio", "Avanzado"};
	    JComboBox<String> cbDificultad = new JComboBox<>(nivelesDificultad);
	    cbDificultad.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    panelRecurso.add(lblDificultad);
	    panelRecurso.add(cbDificultad);
	    
	    JLabel lblDuracion = new JLabel("Duración (minutos):");
	    lblDuracion.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JTextField txtDuracion = new JTextField(10);
	    txtDuracion.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    panelRecurso.add(lblDuracion);
	    panelRecurso.add(txtDuracion);
	    
	    JLabel lblFechaLimite = new JLabel("Fecha límite (dd/MM/yyyy):");
	    lblFechaLimite.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JTextField txtFechaLimite = new JTextField(10);
	    txtFechaLimite.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    panelRecurso.add(lblFechaLimite);
	    panelRecurso.add(txtFechaLimite);
	    
	    JLabel lblPreguntas = new JLabel("Preguntas en formato titulo|enunciado separadas por ;");
	    lblPreguntas.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JTextField txtPreguntas = new JTextField(20);
	    txtPreguntas.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    panelRecurso.add(lblPreguntas);
	    panelRecurso.add(txtPreguntas);
	    
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
	    panelRecurso.add(btnGuardar);
	    
	    this.add(panelRecurso, BorderLayout.CENTER);
	    this.revalidate();
	    this.repaint();
		
	}

	private void crearQuiz(Profesor profesor) {
		
		// TODO Auto-generated method stub
		
	}

	private void crearTarea(Profesor profesor) {
		
		JPanel panelRecurso = new JPanel();
	    panelRecurso.setLayout(new GridLayout(17,1));
	    

	    JLabel lblTitulo = new JLabel("Título de la Tarea:");
	    lblTitulo.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JTextField txtTitulo = new JTextField(20);
	    txtTitulo.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    panelRecurso.add(lblTitulo);
	    panelRecurso.add(txtTitulo);
	    
	    JLabel lblDescripcion = new JLabel("Descripción de la Tarea:");
	    lblDescripcion.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JTextArea txtDescripcion = new JTextArea(10, 20);
	    txtDescripcion.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JScrollPane scrollDescripcion = new JScrollPane(txtDescripcion);
	    panelRecurso.add(lblDescripcion);
	    panelRecurso.add(scrollDescripcion);
	    
	    JLabel lblObjetivos = new JLabel("Objetivos (separados por comas):");
	    lblObjetivos.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JTextField txtObjetivos = new JTextField(20);
	    txtObjetivos.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    panelRecurso.add(lblObjetivos);
	    panelRecurso.add(txtObjetivos);
	    
	    JLabel lblDificultad = new JLabel("Nivel de dificultad:");
	    lblDificultad.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    String[] nivelesDificultad = {"Principiante", "Intermedio", "Avanzado"};
	    JComboBox<String> cbDificultad = new JComboBox<>(nivelesDificultad);
	    cbDificultad.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    panelRecurso.add(lblDificultad);
	    panelRecurso.add(cbDificultad);
	    
	    JLabel lblDuracion = new JLabel("Duración (minutos):");
	    lblDuracion.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JTextField txtDuracion = new JTextField(10);
	    txtDuracion.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    panelRecurso.add(lblDuracion);
	    panelRecurso.add(txtDuracion);
	    
	    JLabel lblFechaLimite = new JLabel("Fecha límite (dd/MM/yyyy):");
	    lblFechaLimite.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JTextField txtFechaLimite = new JTextField(10);
	    txtFechaLimite.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    panelRecurso.add(lblFechaLimite);
	    panelRecurso.add(txtFechaLimite);
	    
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
	    panelRecurso.add(btnGuardar);
	    
	    this.add(panelRecurso, BorderLayout.CENTER);
	    this.revalidate();
	    this.repaint();		
		
	}

	private void crearRevisarRecurso(Profesor profesor) {

	    JPanel panelRecurso = new JPanel();
	    panelRecurso.setLayout(new GridLayout(17,1));
	    

	    JLabel lblTitulo = new JLabel("Título del recurso:");
	    lblTitulo.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JTextField txtTitulo = new JTextField(20);
	    txtTitulo.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    panelRecurso.add(lblTitulo);
	    panelRecurso.add(txtTitulo);
	    
	    JLabel lblDescripcion = new JLabel("Descripción del recurso:");
	    lblDescripcion.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JTextArea txtDescripcion = new JTextArea(10, 20);
	    txtDescripcion.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JScrollPane scrollDescripcion = new JScrollPane(txtDescripcion);
	    panelRecurso.add(lblDescripcion);
	    panelRecurso.add(scrollDescripcion);
	    
	    JLabel lblObjetivos = new JLabel("Objetivos (separados por comas):");
	    lblObjetivos.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JTextField txtObjetivos = new JTextField(20);
	    txtObjetivos.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    panelRecurso.add(lblObjetivos);
	    panelRecurso.add(txtObjetivos);
	    
	    JLabel lblDificultad = new JLabel("Nivel de dificultad:");
	    lblDificultad.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    String[] nivelesDificultad = {"Principiante", "Intermedio", "Avanzado"};
	    JComboBox<String> cbDificultad = new JComboBox<>(nivelesDificultad);
	    cbDificultad.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    panelRecurso.add(lblDificultad);
	    panelRecurso.add(cbDificultad);
	    
	    JLabel lblDuracion = new JLabel("Duración (minutos):");
	    lblDuracion.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JTextField txtDuracion = new JTextField(10);
	    txtDuracion.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    panelRecurso.add(lblDuracion);
	    panelRecurso.add(txtDuracion);
	    
	    JLabel lblFechaLimite = new JLabel("Fecha límite (dd/MM/yyyy):");
	    lblFechaLimite.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JTextField txtFechaLimite = new JTextField(10);
	    txtFechaLimite.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    panelRecurso.add(lblFechaLimite);
	    panelRecurso.add(txtFechaLimite);
	    
	    JLabel lblTipoRecurso = new JLabel("Tipo de recurso:");
	    lblTipoRecurso.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JTextField txtTipoRecurso = new JTextField(20);
	    txtTipoRecurso.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    panelRecurso.add(lblTipoRecurso);
	    panelRecurso.add(txtTipoRecurso);
	    
	    JLabel lblEnlace = new JLabel("Enlace del recurso:");
	    lblEnlace.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    JTextField txtEnlace = new JTextField(20);
	    txtEnlace.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    panelRecurso.add(lblEnlace);
	    panelRecurso.add(txtEnlace);
	    
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
	    panelRecurso.add(btnGuardar);
	    
	    this.add(panelRecurso, BorderLayout.CENTER);
	    this.revalidate();
	    this.repaint();
	}
}

