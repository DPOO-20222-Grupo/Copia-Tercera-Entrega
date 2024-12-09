package interfazGrafica;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import aplicacion.Aplicacion;
import exceptions.UsuarioYaExistenteException;
import interfazEstudiante.VentanaEstudiante;
import interfazProfesor.VentanaProfesor;
import user.Estudiante;
import user.Profesor;

@SuppressWarnings("serial")
public class VentanaInicial extends JFrame implements ActionListener {

    private JPanel panelBotones;
    private JPanel panelFormulario;
    private Aplicacion aplicacion; 

    public final static String INSCRIBIRSE_PROFESOR = "INSCRIBIRSE COMO PROFESOR";
    public final static String INSCRIBIRSE_ESTUDIANTE = "INSCRIBIRSE COMO ESTUDIANTE";
    public final static String INICIAR_SESION_PROFESOR = "INICIAR SESIÓN COMO PROFESOR";
    public final static String INICIAR_SESION_ESTUDIANTE = "INICIAR SESIÓN COMO ESTUDIANTE";
    public final static String CERRAR_SESION = "CERRAR SESIÓN";

    public VentanaInicial() {
    	
    	aplicacion = new Aplicacion("usuarios.json", "lp.json", "preguntas.json", "actividades.json", "cifrasActividades.json");
    	
        this.setTitle("Menú Principal");
        this.setSize(800, 600); 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null); 
        this.setLayout(new BorderLayout(10, 10));
        this.setResizable(false);

        
        
        JLabel titulo = new JLabel();
        ImageIcon icono = new ImageIcon(getClass().getResource("/imagenes/Universidad_de_los_Andes_(logo).png"));
        Image imagenEscalada = icono.getImage().getScaledInstance(400, 100, Image.SCALE_SMOOTH);
        titulo.setIcon(new ImageIcon(imagenEscalada));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(titulo, BorderLayout.NORTH);


        panelBotones = new JPanel(new GridLayout(5, 1, 10, 10));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelBotones.add(crearBoton("Inscribirse como profesor", INSCRIBIRSE_PROFESOR));
        panelBotones.add(crearBoton("Inscribirse como estudiante", INSCRIBIRSE_ESTUDIANTE));
        panelBotones.add(crearBoton("Iniciar sesión como profesor", INICIAR_SESION_PROFESOR));
        panelBotones.add(crearBoton("Iniciar sesión como estudiante", INICIAR_SESION_ESTUDIANTE));
        panelBotones.add(crearBoton("Cerrar sesión", CERRAR_SESION));
        this.add(panelBotones, BorderLayout.WEST);


        panelFormulario = new JPanel(new GridLayout(4, 2, 10, 10));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.add(panelFormulario, BorderLayout.CENTER);

        this.setVisible(true);
    }

    private JButton crearBoton(String texto, String comando) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Times New Roman", Font.BOLD, 16));
        boton.setActionCommand(comando);
        boton.addActionListener(this);
        return boton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        panelFormulario.removeAll(); 

        switch (comando) {
            case INSCRIBIRSE_PROFESOR:
                mostrarFormularioRegistro("Profesor");
                break;
            case INSCRIBIRSE_ESTUDIANTE:
                mostrarFormularioRegistro("Estudiante");
                break;
            case INICIAR_SESION_PROFESOR:
                mostrarFormularioInicioSesion("Profesor");
                break;
            case INICIAR_SESION_ESTUDIANTE:
                mostrarFormularioInicioSesion("Estudiante");
                break;
            case CERRAR_SESION:
                JOptionPane.showMessageDialog(this, "Sesión cerrada.");
                System.exit(0);
                break;
            default:
                JOptionPane.showMessageDialog(this, "Acción desconocida");
        }

        panelFormulario.revalidate(); 
        panelFormulario.repaint();
    }

    private void mostrarFormularioRegistro(String tipoUsuario) {
        JLabel lblLogin = new JLabel("Login:");
        lblLogin.setFont(new Font("Times New Roman", Font.BOLD, 16));
        JTextField txtLogin = new JTextField();
        txtLogin.setFont(new Font("Times New Roman", Font.BOLD, 16));

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Times New Roman", Font.BOLD, 16));
        JTextField txtPassword = new JPasswordField();
        txtPassword.setFont(new Font("Times New Roman", Font.BOLD, 16));

        JLabel lblNombre = new JLabel("Nombre completo:");
        lblNombre.setFont(new Font("Times New Roman", Font.BOLD, 16));
        JTextField txtNombre = new JTextField();
        txtNombre.setFont(new Font("Times New Roman", Font.BOLD, 16));

        JButton btnEnviar = new JButton("Registrar");
        btnEnviar.setFont(new Font("Times New Roman", Font.BOLD, 16));
        btnEnviar.addActionListener(a -> {
            String login = txtLogin.getText();
            String password = txtPassword.getText();
            String nombre = txtNombre.getText();

            if (login.isEmpty() || password.isEmpty() || nombre.isEmpty()) {
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
                JOptionPane.showMessageDialog(this, "Registro exitoso como " + tipoUsuario + ".");
            } catch (UsuarioYaExistenteException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

            
        panelFormulario.setLayout(new GridLayout(4, 2, 10, 10));
        panelFormulario.add(lblLogin);
        panelFormulario.add(txtLogin);
        panelFormulario.add(lblPassword);
        panelFormulario.add(txtPassword);
        panelFormulario.add(lblNombre);
        panelFormulario.add(txtNombre);
        panelFormulario.add(new JLabel()); 
        panelFormulario.add(btnEnviar);
    }

    private void mostrarFormularioInicioSesion(String tipoUsuario) {
        JLabel lblLogin = new JLabel("Login:");
        lblLogin.setFont(new Font("Times New Roman", Font.BOLD, 16));
        JTextField txtLogin = new JTextField();
        txtLogin.setFont(new Font("Times New Roman", Font.BOLD, 16));

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Times New Roman", Font.BOLD, 16));
        JTextField txtPassword = new JPasswordField();
        txtPassword.setFont(new Font("Times New Roman", Font.BOLD, 16));
        

        JButton btnEnviar = new JButton("Iniciar Sesión");
        btnEnviar.setFont(new Font("Times New Roman", Font.BOLD, 16));
        btnEnviar.addActionListener(a -> {
            String login = txtLogin.getText();
            String password = txtPassword.getText();

            if (login.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ambos campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if(tipoUsuario == "Estudiante") {
            
	            Estudiante estudiante = aplicacion.getMapaEstudiantes().get(login); 
	            if (estudiante != null && estudiante.login(login, password)) {
	                JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso.");
	                new VentanaEstudiante(aplicacion, estudiante); 
	                this.dispose(); 
	            } else {
	                JOptionPane.showMessageDialog(this, "Credenciales incorrectas.", "Error", JOptionPane.ERROR_MESSAGE);
	            }}
            else {
	            Profesor profesor = aplicacion.getMapaProfesores().get(login); 
	            if (profesor != null && profesor.login(login, password)) {
	            	JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso.");
	                new VentanaProfesor(aplicacion, profesor); 
	                this.dispose(); 
	            } else {
	                JOptionPane.showMessageDialog(this, "Credenciales incorrectas.", "Error", JOptionPane.ERROR_MESSAGE);
            }}
        });

        panelFormulario.setLayout(new GridLayout(3, 2, 10, 10));
        panelFormulario.add(lblLogin);
        panelFormulario.add(txtLogin);
        panelFormulario.add(lblPassword);
        panelFormulario.add(txtPassword);
        panelFormulario.add(new JLabel()); 
        panelFormulario.add(btnEnviar);
    }

    public static void main(String[] args) {
        new VentanaInicial();
    }
}

