package ventavehiculos.controller;

import ventavehiculos.model.Cliente;
import ventavehiculos.model.Vehiculo;
import ventavehiculos.model.Venta;
import ventavehiculos.model.dao.VehiculoDAO;
import ventavehiculos.view.VentanaVentas;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDateTime;
import java.util.List;

public class ControladorVentas {
    private VentanaVentas vista;
    private DefaultTableModel modeloTabla;

    public ControladorVentas(VentanaVentas vista) {
        this.vista = vista;
        initController();
        configurarTabla();
        cargarVehiculos();
    }

    private void initController() {
        vista.getBtnConfirmarVenta().addActionListener(e -> realizarVenta());
        vista.getBtnCerrar().addActionListener(e -> vista.dispose());
    }

    private void configurarTabla() {
        modeloTabla = vista.getModeloTabla();
        modeloTabla.setColumnIdentifiers(new String[]{"ID", "Marca", "Modelo", "Precio"});
        vista.getTablaVehiculos().setModel(modeloTabla);
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
        int fila = vista.getTablaVehiculos().getSelectedRow();

        // Capturar datos del cliente
        String nombre = vista.getTxtNombre().getText().trim();
        String apellido = vista.getTxtApellido().getText().trim();
        String correo = vista.getTxtCorreo().getText().trim();
        String telefono = vista.getTxtTelefono().getText().trim();

        // Validaciones
        if (fila < 0) {
            JOptionPane.showMessageDialog(vista, "Selecciona un vehículo.");
            return;
        }
        if (nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty() || telefono.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Completa todos los datos del cliente.");
            return;
        }

        // Datos del carro elegido
        int idVehiculo = (int) modeloTabla.getValueAt(fila, 0);
        String marca = modeloTabla.getValueAt(fila, 1).toString();
        String modelo = modeloTabla.getValueAt(fila, 2).toString();
        double precio = (double) modeloTabla.getValueAt(fila, 3);

        // Factura
        vista.getAreaFactura().setText("\n========= FACTURA SGV =========\n\n");
        vista.getAreaFactura().append("============== ⏺️ ==============\n\n");
        vista.getAreaFactura().append(nombre + " " + apellido + "\n");
        vista.getAreaFactura().append(correo + "\n");
        vista.getAreaFactura().append(telefono + "\n\n");
        vista.getAreaFactura().append("-------------------------------------\n\n");
        vista.getAreaFactura().append("Vehículo: " + marca + ", " + modelo + "\n");
        vista.getAreaFactura().append("Precio: $" + precio + "\n");
        vista.getAreaFactura().append("Fecha: " + LocalDateTime.now() + "\n\n");
        vista.getAreaFactura().append("=============================\n");

        JOptionPane.showMessageDialog(vista, "✅ Venta registrada.");

        Vehiculo vehiculoSeleccionado = new VehiculoDAO().buscarCarroPorId(idVehiculo);
        if (vehiculoSeleccionado == null) {
            JOptionPane.showMessageDialog(vista, "❌ Error al recuperar detalles del vehiculo");
            return;
        }

        Cliente cliente = new Cliente(nombre, apellido, correo, telefono);
        Venta nuevaVenta = new Venta(0, cliente, vehiculoSeleccionado, LocalDateTime.now());
        nuevaVenta.setMonto(precio);
        nuevaVenta.generarFactura();

        JOptionPane.showMessageDialog(vista, "Factura generada.");
    }
}
