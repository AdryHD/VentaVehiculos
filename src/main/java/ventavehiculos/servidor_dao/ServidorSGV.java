package ventavehiculos.servidor_dao;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import ventavehiculos.gui.VentanaPrincipal;


import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServidorSGV extends Thread{

    public void run(){

        //Se establece el socket de comunicación
        ServerSocket serverSocket = null;
        try{
            serverSocket = new ServerSocket(5700);
        } catch(IOException e){
            System.out.println(e);
            return;
        }

        Gson gson = new Gson();
        while (true){
            try {
                Socket socket = serverSocket.accept();
                System.out.println("Cliente conectado");

                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

                String solicitudJson = dataInputStream.readUTF();
                JsonObject solicitud = gson.fromJson(solicitudJson, JsonObject.class);

                String usuario = solicitud.get("usuario").getAsString();
                String contraseña = solicitud.get("contraseña").getAsString();
                JsonObject respuesta = new JsonObject();

                try (Connection conn = ConexionBD.conectar()) {
                    String sql = "SELECT * FROM usuarios WHERE usuario = ? AND contraseña = ?";
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setString(1, usuario);
                    ps.setString(2, contraseña);

                    ResultSet rs = ps.executeQuery();

                    if (rs.next()) {
                        respuesta.addProperty("correcto", true);
                        respuesta.addProperty("nombre", rs.getString("nombre"));
                        respuesta.addProperty("tipo_usuario", rs.getString("tipo_usuario"));
                    } else {
                        respuesta.addProperty("correcto", false);
                    }

                    dataOutputStream.writeUTF(gson.toJson(respuesta));
                    dataOutputStream.flush();

                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "❌ Error de conexión: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }

                socket.close();
            }catch (IOException e){
                System.out.println(e);
            }
        }
        //Servidor escuchando y respondiendo

    }
}
