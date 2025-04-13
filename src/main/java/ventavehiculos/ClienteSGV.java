package ventavehiculos;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ventavehiculos.gui.VentanaLogin;


import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClienteSGV extends Thread{
    public void run(){
        new VentanaLogin();
    }


}
