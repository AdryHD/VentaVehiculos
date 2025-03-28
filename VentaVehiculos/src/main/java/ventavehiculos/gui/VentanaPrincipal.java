package ventavehiculos.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrincipal extends JFrame {
    private JPanel panelPrincipal;
    private JLabel lblBienvenida;
    private JButton btnInventario;
    private JButton btnVentas;
    private JButton btnCerrarSesion;

    private String tipoUsuario;

    public VentanaPrincipal(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;

        setTitle("Menú Principal - SGV");
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        // Personaliza  tipo de usuario
        if (!tipoUsuario.equalsIgnoreCase("admin")) {
            btnInventario.setVisible(false);
        }

        lblBienvenida.setText("Bienvenido al sistema SGV (" + tipoUsuario + ")");

        // Acciones de botones
        btnInventario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VentanaInventario(); // abrir inventario
            }
        });

        btnVentas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VentanaVentas(); // abrir ventas
            }
        });

        btnCerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // cerrar esta ventana
                new VentanaLogin(); // volver al login
            }
        });
    }
}
