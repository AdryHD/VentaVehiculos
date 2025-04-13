package ventavehiculos.view;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class VentanaLogin extends JFrame {
    private JPanel panelPrincipal;
    private JTextField campoUsuario;
    private JPasswordField campoContraseña;
    private JButton botonIngresar;
    private JLabel lblFooter;
    private JLabel lblTitulo;
    private JLabel lblUsuario;
    private JLabel lblContraseña;

    public VentanaLogin() {
        setTitle("Inicio de Sesión - SGV");
        setContentPane(panelPrincipal);
        //  icono  usuario
        ImageIcon iconoUsuario = new ImageIcon(getClass().getResource("/icons/usuario.png"));
        lblUsuario.setIcon(iconoUsuario);
        ImageIcon iconoContraseña = new ImageIcon(getClass().getResource("/icons/contraseña.png"));
        lblContraseña.setIcon(iconoContraseña);


        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        // Ingresar
        botonIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verificarCredenciales();
            }
        });
    }

    private void verificarCredenciales() {
        String usuario = campoUsuario.getText().trim();
        String contraseña = new String(campoContraseña.getPassword()).trim();

        if (usuario.isEmpty() || contraseña.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos.", "Campos Vacíos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try{
            Socket socket = new Socket("127.0.0.1", 5700);
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

            //Gson de solicitud
            Gson gson = new Gson();
            JsonObject solicitud = new JsonObject();
            solicitud.addProperty("usuario", usuario);
            solicitud.addProperty("contraseña", contraseña);
            dataOutputStream.writeUTF(gson.toJson(solicitud));

            String respuestaJson = dataInputStream.readUTF();
            JsonObject respuesta = gson.fromJson(respuestaJson, JsonObject.class);

            boolean loginCorrecto = respuesta.get("correcto").getAsBoolean();

            if(loginCorrecto){
                String tipoUsuario = respuesta.get("tipo_usuario").getAsString();
                String nombre = respuesta.get("nombre").getAsString();

                JOptionPane.showMessageDialog(this, " Bienvenido, " + nombre);

                // Abrir la ventana principal y cerrar login
                VentanaPrincipal ventanaPrincipal = new VentanaPrincipal(tipoUsuario);
                ventanaPrincipal.setVisible(true);
                dispose();
            }else {
                JOptionPane.showMessageDialog(this, "❌ Usuario o contraseña incorrectos.", "Acceso Denegado", JOptionPane.ERROR_MESSAGE);
            }

        }catch (IOException e){
            JOptionPane.showMessageDialog(this, "❌ " +
                    "Error al conectar con el servidor: \" + e.getMessage(), \"Error\", JOptionPane.ERROR_MESSAGE");
        }

    }


}