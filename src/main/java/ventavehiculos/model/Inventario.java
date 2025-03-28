package ventavehiculos.model;

import java.util.ArrayList;
import java.util.List;

public class Inventario {
    private List<Vehiculo> listaVehiculos;

    // Constructor
    public Inventario() {
        listaVehiculos = new ArrayList<>();
    }

    // Agregar carro
    public void agregarVehiculo(Vehiculo v) {
        listaVehiculos.add(v);
    }

    // Eliminar carro
    public boolean eliminarVehiculo(int id) {
        return listaVehiculos.removeIf(v -> v.getId() == id);
    }

    // Buscar carro
    public List<Vehiculo> buscarVehiculosPorMarca(String marca) {
        List<Vehiculo> resultado = new ArrayList<>();
        for (Vehiculo v : listaVehiculos) {
            if (v.getMarca().equalsIgnoreCase(marca)) {
                resultado.add(v);
            }
        }
        return resultado;
    }

    // Listar todos los disponibles
    public List<Vehiculo> listarVehiculosDisponibles() {
        List<Vehiculo> disponibles = new ArrayList<>();
        for (Vehiculo v : listaVehiculos) {
            if (v.isDisponible()) {
                disponibles.add(v);
            }
        }
        return disponibles;
    }

    // Obtener la lista completa (por si se necesita)
    public List<Vehiculo> getListaVehiculos() {
        return listaVehiculos;
    }
}
