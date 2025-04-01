package ventavehiculos.model;

import java.time.LocalDateTime;
import java.util.List;

public class Venta {
    private int idVenta;
    private Cliente cliente;
    private Vehiculo vehiculo;
    private LocalDateTime fechaVenta;
    private double monto;

    // Constructor
    public Venta(int idVenta, Cliente cliente, Vehiculo vehiculo, LocalDateTime fechaVenta) {
       // List<Venta> ventas =
        this.idVenta = idVenta;
        this.cliente = cliente;
        this.vehiculo = vehiculo;
        this.fechaVenta = fechaVenta;
        this.monto = vehiculo.getPrecio();
    }

    // set u git
    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public LocalDateTime getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(LocalDateTime fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    // Métodos no se usa revisar
    public void generarFactura() {
        System.out.println("----- FACTURA DE VENTA -----");
        System.out.println("Cliente: " + cliente.getNombre() + " " + cliente.getApellido());
        System.out.println("Vehículo: " + vehiculo.getMarca() + " " + vehiculo.getModelo());
        System.out.println("Precio: $" + monto);
        System.out.println("Fecha: " + fechaVenta);
        System.out.println("----------------------------");
    }
}
