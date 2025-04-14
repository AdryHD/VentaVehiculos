package ventavehiculos.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrincipal extends JFrame {
    private JPanel panelPrincipal;
    private JLabel lblBienvenida;
    private JButton btnInventario;
    private JButton btnVentas;
    private JButton btnCerrarSesion;
    private JLabel lblIconoTitulo;

    private String tipoUsuario;

    public VentanaPrincipal(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;

        setTitle("Menú Principal - SGV");
        setContentPane(panelPrincipal);

        ImageIcon iconoTitulo = new ImageIcon(getClass().getResource("/icons/auto.png"));
        lblIconoTitulo.setIcon(iconoTitulo);
        lblIconoTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);
        setResizable(false);

        // Personaliza tipo de usuario
        if (!tipoUsuario.equalsIgnoreCase("admin")) {
            btnInventario.setVisible(false);
        }

        lblBienvenida.setText("Bienvenido al sistema SGV (" + tipoUsuario + ")");

    }

    public JButton getBtnInventario() {
        return btnInventario;
    }

    public JButton getBtnVentas() {
        return btnVentas;
    }

    public JButton getBtnCerrarSesion() {
        return btnCerrarSesion;
    }
}
