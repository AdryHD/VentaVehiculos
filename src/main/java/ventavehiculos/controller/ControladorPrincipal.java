package ventavehiculos.controller;

import ventavehiculos.view.VentanaPrincipal;
import ventavehiculos.view.VentanaInventario;
import ventavehiculos.view.VentanaVentas;
import ventavehiculos.view.VentanaLogin;

public class ControladorPrincipal {
    private VentanaPrincipal vista;

    public ControladorPrincipal(VentanaPrincipal vista) {
        this.vista = vista;
        initController();
    }

    private void initController() {
        vista.getBtnInventario().addActionListener(e -> abrirInventario());
        vista.getBtnVentas().addActionListener(e -> abrirVentas());
        vista.getBtnCerrarSesion().addActionListener(e -> cerrarSesion());
    }

    private void abrirInventario() {
        VentanaInventario vi = new VentanaInventario();
        new ControladorInventario(vi);
        vi.setVisible(true);
    }

    private void abrirVentas() {
        VentanaVentas vv = new VentanaVentas();
        new ControladorVentas(vv);
        vv.setVisible(true);
    }

    private void cerrarSesion() {
        vista.dispose();
        VentanaLogin login = new VentanaLogin();
        new ControladorLogin(login);
        login.setVisible(true);
    }
}
