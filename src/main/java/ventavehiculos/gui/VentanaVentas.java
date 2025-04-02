package ventavehiculos.gui;

import ventavehiculos.dao.VehiculoDAO;
import ventavehiculos.model.Vehiculo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        setVisible(true);

        configurarTabla();
        cargarVehiculos();

        //  Confirmar Venta
        btnConfirmarVenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarVenta();
            }
        });

        //  Cerrar
        btnCerrar.addActionListener(e -> dispose());
    }

    private void configurarTabla() {
        modeloTabla = new DefaultTableModel();
        modeloTabla.setColumnIdentifiers(new String[]{"ID", "Marca", "Modelo", "Precio"});
        tablaVehiculos.setModel(modeloTabla);
    }

    private void cargarVehiculos() {
        modeloTabla.setRowCount(0);
        List<Vehiculo> lista = new VehiculoDAO().listarVehiculosDisponibles();
        for (Vehiculo v : lista) {
            modeloTabla.addRow(new Object[]{
                    v.getId(), v.getMarca(), v.getModelo(), v.getPrecio()
            });
        }
    }

    private void realizarVenta() {
        int fila = tablaVehiculos.getSelectedRow();

        // Capturar datos del cliente
        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();
        String correo = txtCorreo.getText().trim();
        String telefono = txtTelefono.getText().trim();

        // Validaciones
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Selecciona un vehículo.");
            return;
        }

        if (nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty() || telefono.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Completa todos los datos del cliente.");
            return;
        }

        // datos del carro seleccionado
        int idVehiculo = (int) modeloTabla.getValueAt(fila, 0);
        String marca = modeloTabla.getValueAt(fila, 1).toString();
        String modelo = modeloTabla.getValueAt(fila, 2).toString();
        double precio = (double) modeloTabla.getValueAt(fila, 3);

        //  factura
        areaFactura.setText( "\n" + "======= FACTURA SGV =======\n" + "\n");
        areaFactura.append( nombre + " " + apellido + "\n");
        areaFactura.append( correo + "\n");
        areaFactura.append( telefono + "\n"+ "\n");
        areaFactura.append("-------------------------------------\n"+ "\n");
        areaFactura.append("Vehículo: " + marca + " " + modelo + "\n");
        areaFactura.append("Precio: $" + precio + "\n");
        areaFactura.append("Fecha: " + LocalDateTime.now() + "\n" + "\n");
        areaFactura.append("===========================\n");

        JOptionPane.showMessageDialog(this, "Venta registrada.");
    }
}
