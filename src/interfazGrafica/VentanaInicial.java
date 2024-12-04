package interfazGrafica;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Consolas.EstudianteConsole;
import Consolas.ProfesorConsole;
import interfaz.Aplicacion;

@SuppressWarnings("serial")
public class VentanaInicial extends JFrame implements ActionListener {

    private Aplicacion aplicacion;
    public final static String inscribirseProfesor = "INSCRIBIRSE COMO PROFESOR";
    public final static String inscribirseEstudiante = "INSCRIBIRSE COMO ESTUDIANTE";
    public final static String iniciarSesionProfesor = "INICIAR SESIÓN COMO PROFESOR";
    public final static String iniciarSesionEstudiante = "INICIAR SESIÓN COMO ESTUDIANTE";
    public final static String cerrar = "CERRAR SESION";

    public VentanaInicial(Aplicacion aplicacion) {
        this.aplicacion = aplicacion;

        this.setTitle("Menú Principal");
        this.setSize(800, 800); 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null); 
        this.setResizable(true); 

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1)); 

        JLabel titulo = new JLabel();
        ImageIcon icono = new ImageIcon(getClass().getResource("/imagenes/Universidad_de_los_Andes_(logo).png"));
        Image imagenEscalada = icono.getImage().getScaledInstance(400, 100, Image.SCALE_SMOOTH);
        titulo.setIcon(new ImageIcon(imagenEscalada));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setVerticalAlignment(SwingConstants.CENTER);
        panel.add(titulo);

        panel.add(crearBoton("Inscribirse como profesor", inscribirseProfesor));
        panel.add(crearBoton("Inscribirse como estudiante", inscribirseEstudiante));
        panel.add(crearBoton("Iniciar sesión como profesor", iniciarSesionProfesor));
        panel.add(crearBoton("Iniciar sesión como estudiante", iniciarSesionEstudiante));
        panel.add(crearBoton("Cerrar registro", cerrar));

        this.add(panel);
    }

    private JButton crearBoton(String texto, String comando) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Times New Roman", Font.BOLD, 40));
        boton.setActionCommand(comando);
        boton.addActionListener(this);
        return boton;
    }

    public static void main(String[] args) {
        Aplicacion aplicacion = new Aplicacion("usuarios.json", "lp.json", "preguntas.json", "actividades.json");
        VentanaInicial ventana = new VentanaInicial(aplicacion);
        ventana.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        switch (comando) {
            case inscribirseProfesor:
                realizarRegistro("Profesor");
                break;
            case inscribirseEstudiante:
                realizarRegistro("Estudiante");
                break;
            case iniciarSesionProfesor:
                iniciarSesion("Profesor");
                break;
            case iniciarSesionEstudiante:
                iniciarSesion("Estudiante");
                break;
            case cerrar:
                System.exit(JFrame.EXIT_ON_CLOSE);
                break;
            default:
                JOptionPane.showMessageDialog(this, "Acción desconocida");
        }
    }

    private void realizarRegistro(String tipoUsuario) {
        String login = JOptionPane.showInputDialog(this, "Ingrese el login:");
        String password = JOptionPane.showInputDialog(this, "Ingrese el password:");
        String nombre = JOptionPane.showInputDialog(this, "Ingrese su nombre completo:");

        if (login == null || password == null || nombre == null) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            aplicacion.revisarUsuarioRepetido(login, tipoUsuario);
            if (tipoUsuario.equals("Profesor")) {
                aplicacion.crearProfesor(login, password, nombre);
                aplicacion.descargarDatos();
            } else {
                aplicacion.crearEstudiante(login, password, nombre);
                aplicacion.descargarDatos();
            }
            JOptionPane.showMessageDialog(this, "Registro exitoso.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void iniciarSesion(String tipoUsuario) {
        String login = JOptionPane.showInputDialog(this, "Ingrese su login:");
        String password = JOptionPane.showInputDialog(this, "Ingrese su password:");

        if (login == null || password == null) {
            JOptionPane.showMessageDialog(this, "Ambos campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            if (tipoUsuario.equals("Profesor")) {
                ProfesorConsole consola = new ProfesorConsole(aplicacion);
                consola.loginPlataforma();
            } else {
                EstudianteConsole consola = new EstudianteConsole(aplicacion);
                consola.loginPlataforma();
            }
            JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

