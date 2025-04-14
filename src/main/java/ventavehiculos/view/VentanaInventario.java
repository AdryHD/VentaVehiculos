package ventavehiculos.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class VentanaInventario extends JFrame {
    private JPanel panelPrincipal;
    private JLabel lbltituloInventario;
    private JTable tablaVehiculos;
    private JButton btnAgregar;
    private JButton btnEliminar;
    private JButton btnActualizar;
    private JButton btnCerrar;
    private JScrollPane scrollTabla;
    private JTextField txtMarca;
    private JLabel lblMarca;
    private JLabel lblModelo;
    private JTextField txtModelo;
    private JLabel lblPrecio;
    private JTextField txtPrecio;

    private DefaultTableModel modeloTabla;

    public VentanaInventario() {
        setTitle("Inventario de Vehículos");
        setContentPane(panelPrincipal);
        setSize(600, 400);
        setLocationRelativeTo(null);

        // tabla
        modeloTabla = new DefaultTableModel();
        modeloTabla.setColumnIdentifiers(new String[]{"ID", "Marca", "Modelo", "Precio", "Disponible"});
        tablaVehiculos.setModel(modeloTabla);
    }

    // getters para acceso desde el controlador:
    public JButton getBtnAgregar() {
        return btnAgregar;
    }
    public JButton getBtnEliminar() {
        return btnEliminar;
    }
    public JButton getBtnActualizar() {
        return btnActualizar;
    }
    public JButton getBtnCerrar() {
        return btnCerrar;
    }
    public JTable getTablaVehiculos() {
        return tablaVehiculos;
    }
    public JTextField getTxtMarca() {
        return txtMarca;
    }
    public JTextField getTxtModelo() {
        return txtModelo;
    }
    public JTextField getTxtPrecio() {
        return txtPrecio;
    }
    public DefaultTableModel getModeloTabla() {
        return (DefaultTableModel) tablaVehiculos.getModel();
    }
}
