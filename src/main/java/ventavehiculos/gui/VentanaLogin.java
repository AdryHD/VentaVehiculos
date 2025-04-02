package ventavehiculos.gui;

import ventavehiculos.dao.ConexionBD;
import ventavehiculos.model.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

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
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        // Ingresar
        botonIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verificarCredenciales();
            }
        });
    }

    private void verificarCredenciales() {
        String usuario = campoUsuario.getText().trim();
        String contraseña = new String(campoContraseña.getPassword()).trim();

        if (usuario.isEmpty() || contraseña.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos.", "Campos Vacíos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try (Connection conn = ConexionBD.conectar()) {
            String sql = "SELECT * FROM usuarios WHERE usuario = ? AND contraseña = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, contraseña);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String tipoUsuario = rs.getString("tipo_usuario");
                String nombreUsuario = rs.getString("usuario");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String correo = rs.getString("correo");

                Usuario usuarioOnline = new Usuario(nombre, apellido, correo, nombreUsuario, contraseña, tipoUsuario);
                Usuario.setUsuarioActivo(usuarioOnline);

                System.out.println("Usuario activo asignado: " + Usuario.getUsuarioActivo().getNombreUsuario());

                JOptionPane.showMessageDialog(this, "Bienvenido, " + nombre + " (" + tipoUsuario + ")");

                // Abrir la ventana principal y cerrar login
                VentanaPrincipal ventanaPrincipal = new VentanaPrincipal(tipoUsuario);
                ventanaPrincipal.setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.", "Acceso Denegado", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error de conexión: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new VentanaLogin();
    }
}
