package ventavehiculos.dao;

import java.sql.Connection;

public class PruebaConexion {
    public static void main(String[] args) {
        Connection conn = ConexionBD.conectar();
        if (conn != null) {
            System.out.println("✅La conexión fue establecida correctamente.");
        } else {
            System.out.println("❌ La conexión falló.");
        }
    }
}
