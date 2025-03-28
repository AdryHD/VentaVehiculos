package ventavehiculos.dao;

import ventavehiculos.model.Vehiculo;

import java.util.List;

public class PruebaVehiculoDAO {
    public static void main(String[] args) {
        VehiculoDAO vehiculoDAO = new VehiculoDAO();

        // 1. Agregar un vehículo nuevo
        Vehiculo nuevoVehiculo = new Vehiculo(0, "Toyota", "Corolla", 18500.00, true);
        boolean agregado = vehiculoDAO.agregarVehiculo(nuevoVehiculo);
        if (agregado) {
            System.out.println("✅ Vehículo agregado correctamente.");
        } else {
            System.out.println("❌ No se pudo agregar el vehículo.");
        }

        // 2. Listar vehículos disponibles
        System.out.println("\n📋 Lista de vehículos disponibles:");
        List<Vehiculo> disponibles = vehiculoDAO.listarVehiculosDisponibles();
        for (Vehiculo v : disponibles) {
            v.mostrarDetalles();
        }

        // 3. Eliminar un vehículo (puedes cambiar el ID si es necesario)
        int idAEliminar = 1; // ejemplo: eliminar vehículo con ID 1
        boolean eliminado = vehiculoDAO.eliminarVehiculo(idAEliminar);
        if (eliminado) {
            System.out.println("\n🗑️ Vehículo con ID " + idAEliminar + " eliminado.");
        } else {
            System.out.println("\n⚠️ No se pudo eliminar el vehículo con ID " + idAEliminar + ".");
        }
    }
}
