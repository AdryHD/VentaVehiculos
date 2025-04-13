package ventavehiculos.gui;

import ventavehiculos.dao.VehiculoDAO;
import ventavehiculos.model.Vehiculo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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
        setVisible(true);

        // tabla
        modeloTabla = new DefaultTableModel();
        modeloTabla.setColumnIdentifiers(new String[]{"ID", "Marca", "Modelo", "Precio", "Disponible"});
        tablaVehiculos.setModel(modeloTabla);

        cargarVehiculos();

        //ENLAZAR LOS BOTONES
        // Agregar carro
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarVehiculo();
            }
        });

        // Eliminar carro seleccionado
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarVehiculoSeleccionado();
            }
        });

        // Actualizar tabla
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarVehiculos();
            }
        });

        // Cerrar ventana
        btnCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // cerrar solo esta ventana
            }
        });
    }

    private void cargarVehiculos() {
        modeloTabla.setRowCount(0); // limpiar tabla
        List<Vehiculo> lista = new VehiculoDAO().listarVehiculosDisponibles();
        for (Vehiculo v : lista) {
            modeloTabla.addRow(new Object[]{
                    v.getId(), v.getMarca(), v.getModelo(), v.getPrecio(), v.isDisponible()
            });
        }
    }

    private void agregarVehiculo() {
        String marca = txtMarca.getText().trim();
        String modelo = txtModelo.getText().trim();
        String precioStr = txtPrecio.getText().trim();

        if (marca.isEmpty() || modelo.isEmpty() || precioStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor completa todos los campos.");
            return;
        }

        try {
            double precio = Double.parseDouble(precioStr);
            Vehiculo nuevo = new Vehiculo(0, marca, modelo, precio, true);
            boolean exito = new VehiculoDAO().agregarVehiculo(nuevo);
            if (exito) {
                JOptionPane.showMessageDialog(this, "Vehículo agregado correctamente.");
                cargarVehiculos();
                limpiarCamposAgregar(); // Limpia los campos después
            } else {
                JOptionPane.showMessageDialog(this, "Error al agregar vehículo.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El precio ingresado no es válido.");
        }
    }

    private void limpiarCamposAgregar() {
        txtMarca.setText("");
        txtModelo.setText("");
        txtPrecio.setText("");
    }


    private void eliminarVehiculoSeleccionado() {
        int fila = tablaVehiculos.getSelectedRow();
        if (fila >= 0) {
            int id = (int) modeloTabla.getValueAt(fila, 0);
            String marca = (String) modeloTabla.getValueAt(fila, 1);
            String modelo = (String) modeloTabla.getValueAt(fila, 2);

            int confirmacion = JOptionPane.showConfirmDialog(
                    this,
                    "¿Estás seguro de eliminar el vehículo: " + marca + " " + modelo + "?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );

            if (confirmacion == JOptionPane.YES_OPTION) {
                boolean eliminado = new VehiculoDAO().eliminarVehiculo(id);
                if (eliminado) {
                    JOptionPane.showMessageDialog(this, "Vehículo eliminado.");
                    cargarVehiculos();
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo eliminar.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona un vehículo para eliminar.");
        }
    }

}
