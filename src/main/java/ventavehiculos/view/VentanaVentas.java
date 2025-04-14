package ventavehiculos.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class VentanaVentas extends JFrame {
    private JPanel panelPrincipal;
    private JTable tablaVehiculos;
    private JLabel NombreLbl;
    private JTextField NombreTxt;
    private JLabel ApellidoLbl;
    private JTextField ApellidoTxt;
    private JLabel CorreoLbl;
    private JTextField CorreoTxt;
    private JLabel CedulaLbl;
    private JLabel TelefonoLbl;
    private JTextField TelefonoTxt;
    private DefaultTableModel modeloTabla;
    private JButton btnConfirmarVenta;
    private JButton btnCerrar;
    private JTextArea areaFactura;
    private JTextField CedulaTxt;


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
        return NombreTxt;
    }
    public JTextField getTxtApellido() {
        return ApellidoTxt;
    }
    public JTextField getTxtCorreo() {
        return CorreoTxt;
    }
    public JTextField getTxtCedula(){return CedulaTxt;}
    public JTextField getTxtTelefono() {
        return TelefonoTxt;
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
