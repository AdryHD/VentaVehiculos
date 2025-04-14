package ventavehiculos.controller;

import ventavehiculos.view.VentanaLogin;

public class ClienteSGV extends Thread{
    public void run(){

        VentanaLogin vistaLogin = new VentanaLogin();
        new ControladorLogin(vistaLogin);
        vistaLogin.setVisible(true);
    }


}
