package ventavehiculos.controller;

import ventavehiculos.model.Vehiculo;
import ventavehiculos.model.dao.VehiculoDAO;
import ventavehiculos.view.VentanaInventario;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ControladorInventario {
    private VentanaInventario vista;
    private VehiculoDAO vehiculoDAO;
    private DefaultTableModel modeloTabla;

    public ControladorInventario(VentanaInventario vista) {
        this.vista = vista;
        this.vehiculoDAO = new VehiculoDAO();
        initController();
        cargarVehiculos();
    }

    private void initController() {
        vista.getBtnAgregar().addActionListener(e -> agregarVehiculo());
        vista.getBtnEliminar().addActionListener(e -> eliminarVehiculo());
        vista.getBtnActualizar().addActionListener(e -> cargarVehiculos());
        vista.getBtnCerrar().addActionListener(e -> vista.dispose());
    }

    private void cargarVehiculos() {
        modeloTabla = (DefaultTableModel) vista.getTablaVehiculos().getModel();
        modeloTabla.setRowCount(0);
        List<Vehiculo> lista = vehiculoDAO.listarVehiculosDisponibles();
        for (Vehiculo v : lista) {
            modeloTabla.addRow(new Object[]{
                    v.getId(), v.getMarca(), v.getModelo(), v.getPrecio(), v.isDisponible()
            });
        }
    }

    private void agregarVehiculo() {
        String marca = vista.getTxtMarca().getText().trim();
        String modelo = vista.getTxtModelo().getText().trim();
        String precioStr = vista.getTxtPrecio().getText().trim();

        if (marca.isEmpty() || modelo.isEmpty() || precioStr.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Por favor completa todos los campos.");
            return;
        }

        try {
            double precio = Double.parseDouble(precioStr);
            Vehiculo nuevo = new Vehiculo(0, marca, modelo, precio, true);
            boolean exito = vehiculoDAO.agregarVehiculo(nuevo);
            if (exito) {
                JOptionPane.showMessageDialog(vista, "Vehículo agregado correctamente.");
                cargarVehiculos();
                limpiarCamposAgregar();
            } else {
                JOptionPane.showMessageDialog(vista, "Error al agregar vehículo.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vista, "El precio ingresado no es válido.");
        }
    }

    private void limpiarCamposAgregar() {
        vista.getTxtMarca().setText("");
        vista.getTxtModelo().setText("");
        vista.getTxtPrecio().setText("");
    }

    private void eliminarVehiculo() {
        int fila = vista.getTablaVehiculos().getSelectedRow();
        if (fila >= 0) {
            int id = (int) modeloTabla.getValueAt(fila, 0);
            String marca = (String) modeloTabla.getValueAt(fila, 1);
            String modelo = (String) modeloTabla.getValueAt(fila, 2);

            int confirmacion = JOptionPane.showConfirmDialog(
                    vista,
                    "¿Estás seguro de eliminar el vehículo: " + marca + " " + modelo + "?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );

            if (confirmacion == JOptionPane.YES_OPTION) {
                boolean eliminado = vehiculoDAO.eliminarVehiculo(id);
                if (eliminado) {
                    JOptionPane.showMessageDialog(vista, "Vehículo eliminado.");
                    cargarVehiculos();
                } else {
                    JOptionPane.showMessageDialog(vista, "No se pudo eliminar.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(vista, "Selecciona un vehículo para eliminar.");
        }
    }
}
