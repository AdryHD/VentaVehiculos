package ventavehiculos;

import ventavehiculos.controller.ControladorLogin;
import ventavehiculos.controller.ServidorSGV;
import ventavehiculos.controller.ClienteSGV;
import ventavehiculos.view.VentanaLogin;
import ventavehiculos.model.Inventario;
import ventavehiculos.model.MonitoreoInventario;
import ventavehiculos.model.Vehiculo;

public class Main {
    public static void main(String[] args) {
        // inicia los procesos de servidor y cliente
        new ServidorSGV().start();
        new ClienteSGV().start();

        // inicia la ventana de Login con su controlador
        VentanaLogin vistaLogin = new VentanaLogin();
        new ControladorLogin(vistaLogin);
        vistaLogin.setVisible(true);

        // inicia el monitoreo e inventario
        Inventario inventario = new Inventario();
        Thread monitorHilo = new Thread(new MonitoreoInventario(inventario));
        monitorHilo.start();
    }
}
