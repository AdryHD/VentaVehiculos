package ventavehiculos.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDateTime;
import java.util.List;

public class VentanaVentas extends JFrame {
    private JPanel panelPrincipal;
    private JTable tablaVehiculos;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtCorreo;
    private JTextField txtTelefono;
    private JButton btnConfirmarVenta;
    private JButton btnCerrar;
    private JTextArea areaFactura;

    private DefaultTableModel modeloTabla;

    public VentanaVentas() {
        setTitle("Registro de Ventas - SGV");
        setContentPane(panelPrincipal);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setResizable(false);

        configurarTabla();
        cargarVehiculos();
    }

    private void configurarTabla() {
        modeloTabla = new DefaultTableModel();
        modeloTabla.setColumnIdentifiers(new String[]{"ID", "Marca", "Modelo", "Precio"});
        tablaVehiculos.setModel(modeloTabla);
    }

    private void cargarVehiculos() {
        modeloTabla.setRowCount(0);
    }

    // Getters para el controlador:
    public JTable getTablaVehiculos() {
        return tablaVehiculos;
    }
    public JTextField getTxtNombre() {
        return txtNombre;
    }
    public JTextField getTxtApellido() {
        return txtApellido;
    }
    public JTextField getTxtCorreo() {
        return txtCorreo;
    }
    public JTextField getTxtTelefono() {
        return txtTelefono;
    }
    public JButton getBtnConfirmarVenta() {
        return btnConfirmarVenta;
    }
    public JButton getBtnCerrar() {
        return btnCerrar;
    }
    public JTextArea getAreaFactura() {
        return areaFactura;
    }
    public DefaultTableModel getModeloTabla() {
        return (DefaultTableModel) tablaVehiculos.getModel();
    }
}
