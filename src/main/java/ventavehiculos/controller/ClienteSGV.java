package ventavehiculos.controller;

import ventavehiculos.view.VentanaLogin;

public class ClienteSGV extends Thread{
    public void run(){
        new VentanaLogin();
    }


}
