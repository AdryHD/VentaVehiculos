package ventavehiculos.view;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import javax.swing.*;

public class VentanaLogin extends JFrame {
    private JPanel panelPrincipal;
    private JTextField campoUsuario;
    private JPasswordField campoContraseña;
    private JButton botonIngresar;
    private JLabel lblFooter;
    private JLabel lblTitulo;
    private JLabel lblUsuario;
    private JLabel lblContraseña;

    public VentanaLogin() {
        setTitle("Inicio de Sesión - SGV");
        setContentPane(panelPrincipal);

        // Asigna iconos
        ImageIcon iconoUsuario = new ImageIcon(getClass().getResource("/icons/usuario.png"));
        lblUsuario.setIcon(iconoUsuario);
        ImageIcon iconoContraseña = new ImageIcon(getClass().getResource("/icons/contraseña.png"));
        lblContraseña.setIcon(iconoContraseña);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    // getters para el controlador:
    public JTextField getCampoUsuario() {
        return campoUsuario;
    }
    public JPasswordField getCampoContraseña() {
        return campoContraseña;
    }
    public JButton getBotonIngresar() {
        return botonIngresar;
    }
}
