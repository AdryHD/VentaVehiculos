package ventavehiculos.model.dao;

import ventavehiculos.model.Cliente;
import ventavehiculos.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClienteDAO {

    public boolean registrarCliente(Cliente cliente, Usuario usuario) {
        Connection conn = null;
        PreparedStatement psUsuario = null;
        PreparedStatement psCliente = null;

        try {
            conn = ConexionBD.conectar();

            if (conn == null) {
                return false;
            }

            // tabla usuarios
            String sqlUsuario = "INSERT INTO usuarios (nombre, apellido, correo, usuario, contraseña, tipo_usuario) VALUES (?, ?, ?, ?, ?, ?)";
            psUsuario = conn.prepareStatement(sqlUsuario, PreparedStatement.RETURN_GENERATED_KEYS);
            psUsuario.setString(1, usuario.getNombre());
            psUsuario.setString(2, usuario.getApellido());
            psUsuario.setString(3, usuario.getCorreo());
            psUsuario.setString(4, usuario.getNombreUsuario());
            psUsuario.setString(5, usuario.getContraseña());
            psUsuario.setString(6, usuario.getTipoUsuario());
            psUsuario.executeUpdate();

            // Obtener ID generado
            var generatedKeys = psUsuario.getGeneratedKeys();
            int idUsuario = -1;
            if (generatedKeys.next()) {
                idUsuario = generatedKeys.getInt(1);
            }

            // tabla clientes
            String sqlCliente = "INSERT INTO clientes (id_usuario, telefono) VALUES (?, ?)";
            psCliente = conn.prepareStatement(sqlCliente);
            psCliente.setInt(1, idUsuario);
            psCliente.setString(2, cliente.getTelefono());
            psCliente.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.err.println("❌ Error al registrar cliente: " + e.getMessage());
            return false;
        } finally {
            try {
                if (psUsuario != null) psUsuario.close();
                if (psCliente != null) psCliente.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("❌ Error al cerrar conexión: " + e.getMessage());
            }
        }
    }
}
