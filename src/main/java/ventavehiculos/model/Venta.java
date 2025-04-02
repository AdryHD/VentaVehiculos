package ventavehiculos.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Venta {
    private int idVenta;
    private Cliente cliente;
    private Vehiculo vehiculo;
    private LocalDateTime fechaVenta;
    private double monto;

    //se cambia el path de los archivos respectivamente segun quien acceda el programa
    private static final String FACTURAS_FOLDER = "/Users/mpereira/Desktop/Facturas";

    // Constructor
    public Venta(int idVenta, Cliente cliente, Vehiculo vehiculo, LocalDateTime fechaVenta) {
        // List<Venta> ventas =
        this.idVenta = idVenta;
        this.cliente = cliente;
        this.vehiculo = vehiculo;
        this.fechaVenta = fechaVenta;
        this.monto = vehiculo.getPrecio();
    }

    // set y get
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

    // Metodo que genera factura en .txt
    public void generarFactura() {

        // Verificar el usuario en la sesión
        Usuario usuario = Usuario.getUsuarioActivo();
        if (usuario == null) {
            System.out.println("Error: Usuario activo es null.");
            return;
        }

        // Definir la ruta específica
        String userHome = System.getProperty("user.home");
        String folderPath = FACTURAS_FOLDER;

        // Genera un nombre distinto para cada txt basado en la fecha
        String identificador = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmm"));
        String filePath = folderPath + File.separator + "Factura_" + identificador + ".txt";

        // Imprimir datos para depuración
        System.out.println("Usuario: " + usuario.getNombreUsuario() + " (" + usuario.getTipoUsuario() + ")");

        // se escribe el archivo .txt
        try (FileWriter fileWriter = new FileWriter(filePath);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {

            printWriter.println("====FACTURA DE VENTA====");
            printWriter.println("Usuario que factura: " + usuario.getNombreUsuario() + " (" + usuario.getTipoUsuario() + ")");
            printWriter.println("Cliente: " + cliente.getNombre() + " " + cliente.getApellido());
            printWriter.println("Vehículo: " + vehiculo.getMarca() + " " + vehiculo.getModelo());
            printWriter.println("Precio: $" + monto);
            printWriter.println("Fecha: " + fechaVenta.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

            System.out.println("Factura guardada correctamente en: " + filePath);
        } catch (IOException e) {
            System.out.println("Error al generar la factura: " + e.getMessage());
        }
    }

}
