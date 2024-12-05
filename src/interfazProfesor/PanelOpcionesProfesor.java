package interfazProfesor;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import user.Profesor;

@SuppressWarnings("serial")
public class PanelOpcionesProfesor extends JPanel implements ActionListener {

    private Profesor profesor;

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
        this.profesor = profesor;
        this.setLayout(new BorderLayout());

        JLabel lblNombre = new JLabel("Bienvenido a la Aplicación de Profesores: " + profesor.getNombre(), JLabel.CENTER);
        lblNombre.setFont(new Font("Times New Roman", Font.BOLD, 20));
        this.add(lblNombre, BorderLayout.NORTH);

        JPanel panelOpciones = new JPanel();
        panelOpciones.setLayout(new GridLayout(OPCIONES.length, 1, 5, 5));

        ButtonGroup grupoBotones = new ButtonGroup();
        for (String opcion : OPCIONES) {
            JRadioButton btnOpcion = new JRadioButton(opcion);
            btnOpcion.setFont(new Font("Times New Roman", Font.BOLD, 15));
            btnOpcion.setActionCommand(opcion);
            btnOpcion.addActionListener(this);
            grupoBotones.add(btnOpcion);
            panelOpciones.add(btnOpcion);
        }

        this.add(new JScrollPane(panelOpciones), BorderLayout.CENTER);
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

	private void clonarActividad(Profesor profesor2) {
		// TODO Auto-generated method stub
		
	}

	private void crearPregunta(Profesor profesor2) {
		// TODO Auto-generated method stub
		
	}

	private void crearLearningPath(Profesor profesor2) {
		// TODO Auto-generated method stub
		
	}

	private void crearEncuesta(Profesor profesor2) {
		// TODO Auto-generated method stub
		
	}

	private void crearExamen(Profesor profesor2) {
		// TODO Auto-generated method stub
		
	}

	private void crearQuiz(Profesor profesor2) {
		// TODO Auto-generated method stub
		
	}

	private void crearTarea(Profesor profesor2) {
		// TODO Auto-generated method stub
		
	}

	private void crearRevisarRecurso(Profesor profesor) {
		// TODO Auto-generated method stub
		
	}
}

