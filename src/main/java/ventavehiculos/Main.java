 package ventavehiculos;

import ventavehiculos.gui.VentanaLogin;
import ventavehiculos.model.Inventario;
import ventavehiculos.model.MonitoreoInventario;
import ventavehiculos.model.Vehiculo;
import ventavehiculos.servidor_dao.ServidorSGV;

 public class Main {
    public static void main(String[] args) {
        Vehiculo vehiculo = new Vehiculo();
        // para llamar a la ventana de login (pantalla principal)
        ServidorSGV serv = new ServidorSGV();
        serv.start();
        ClienteSGV cli = new ClienteSGV();
        cli.start();
        new VentanaLogin();
        Inventario inventario = new Inventario();
        Thread monitorHilo = new Thread(new MonitoreoInventario(inventario));
        monitorHilo.start();


    }
}
