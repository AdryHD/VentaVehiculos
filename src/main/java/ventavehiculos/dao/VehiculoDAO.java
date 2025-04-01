package ventavehiculos.dao;

import ventavehiculos.model.Vehiculo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehiculoDAO {

    // Agrega un nuevo carro
    public boolean agregarVehiculo(Vehiculo vehiculo) {
        String sql = "INSERT INTO vehiculos (marca, modelo, precio, disponible) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, vehiculo.getMarca());
            ps.setString(2, vehiculo.getModelo());
            ps.setDouble(3, vehiculo.getPrecio());
            ps.setBoolean(4, vehiculo.isDisponible());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("❌ Error al agregar vehículo: " + e.getMessage());
            return false;
        }
    }

    // Lista carros disponibles
    public List<Vehiculo> listarVehiculosDisponibles() {
        List<Vehiculo> lista = new ArrayList<>();
        String sql = "SELECT * FROM vehiculos WHERE disponible = true";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Vehiculo v = new Vehiculo(
                );
                lista.add(v);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al listar vehículos: " + e.getMessage());
        }

        return lista;
    }

    // Eliminar un carro
    public boolean eliminarVehiculo(int idVehiculo) {
        String sql = "DELETE FROM vehiculos WHERE id_vehiculo = ?";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idVehiculo);
            int filas = ps.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar vehículo: " + e.getMessage());
            return false;
        }
    }
}
