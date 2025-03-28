package ventavehiculos.model;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Persona {
    private String telefono;
    private List<Venta> historialCompras;

    // Constructor
    public Cliente(String nombre, String apellido, String correo, String telefono) {
        super(nombre, apellido, correo);
        this.telefono = telefono;
        this.historialCompras = new ArrayList<>();
    }
     // get y set
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<Venta> getHistorialCompras() {
        return historialCompras;
    }

    public void agregarVenta(Venta venta) {
        historialCompras.add(venta);
    }

    // met abstractos
    @Override
    public void mostrarInformacion() {
        System.out.println("Cliente: " + nombre + " " + apellido + " - Tel: " + telefono);
    }

    @Override
    public boolean autenticar(String usuario, String contraseña) {
        // Podrías implementar lógica de autenticación si se desea
        return false; // No aplica si no tiene usuario/contraseña
    }
}
