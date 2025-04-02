 package ventavehiculos;

import ventavehiculos.gui.VentanaLogin;
import ventavehiculos.model.Inventario;
import ventavehiculos.model.MonitoreoInventario;
import ventavehiculos.model.Vehiculo;

public class Main {
    public static void main(String[] args) {
        Vehiculo vehiculo = new Vehiculo();
        // para llamar a la ventana de login (pantalla principal)
        new VentanaLogin();
        Inventario inventario = new Inventario();
        Thread monitorHilo = new Thread(new MonitoreoInventario(inventario));
        monitorHilo.start();


    }
}
