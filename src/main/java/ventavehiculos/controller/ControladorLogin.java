package ventavehiculos.controller;

import ventavehiculos.view.VentanaLogin;
import ventavehiculos.view.VentanaPrincipal;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import javax.swing.JOptionPane;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ControladorLogin {
    private VentanaLogin vista;

    public ControladorLogin(VentanaLogin vista) {
        this.vista = vista;
        initController();
    }

    private void initController() {
        vista.getBotonIngresar().addActionListener(e -> verificarCredenciales());
    }

    private void verificarCredenciales() {
        String usuario = vista.getCampoUsuario().getText().trim();
        String contraseña = new String(vista.getCampoContraseña().getPassword()).trim();

        if (usuario.isEmpty() || contraseña.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Por favor, completa todos los campos.", "Campos Vacíos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            Socket socket = new Socket("127.0.0.1", 5700);
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

            Gson gson = new Gson();
            JsonObject solicitud = new JsonObject();
            solicitud.addProperty("usuario", usuario);
            solicitud.addProperty("contraseña", contraseña);
            dataOutputStream.writeUTF(gson.toJson(solicitud));

            String respuestaJson = dataInputStream.readUTF();
            JsonObject respuesta = gson.fromJson(respuestaJson, JsonObject.class);

            boolean loginCorrecto = respuesta.get("correcto").getAsBoolean();

            if (loginCorrecto) {
                String tipoUsuario = respuesta.get("tipo_usuario").getAsString();
                String nombre = respuesta.get("nombre").getAsString();
                JOptionPane.showMessageDialog(vista, " Bienvenido, " + nombre);

                VentanaPrincipal ventanaPrincipal = new VentanaPrincipal(tipoUsuario);
                new ControladorPrincipal(ventanaPrincipal);
                ventanaPrincipal.setVisible(true);
                vista.dispose();
            } else {
                JOptionPane.showMessageDialog(vista, "❌ Usuario o contraseña incorrectos.", "Acceso Denegado", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(vista, "❌ Error al conectar con el servidor: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
