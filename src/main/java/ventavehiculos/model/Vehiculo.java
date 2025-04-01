package ventavehiculos.model;

public class Vehiculo {
    private int id;
    private String marca;
    private String modelo;
    private double precio;
    private boolean disponible;

    // Constructor
    public Vehiculo() {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.precio = precio;
        this.disponible = disponible;
    }

    // set y get
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    // Métodos
    public void marcarComoVendido() {
        this.disponible = false;
    }

    public void mostrarDetalles() {
        System.out.println("Vehículo ID: " + id + " | " + marca + " " + modelo +
                " | Precio: $" + precio + " | Disponible: " + (disponible ? "Sí" : "No"));
    }
}
