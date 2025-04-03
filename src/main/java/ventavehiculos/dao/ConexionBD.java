package ventavehiculos.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    // Datos conex
    private static final String URL = "jdbc:mysql://localhost:3306/gestionVentas";
    private static final String USUARIO = "root";
    private static final String CONTRASEÑA = "ProyectoDB";


    public static Connection conectar() {
        try {
            Connection conexion = DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);
            System.out.println("✅ Conexión exitosa a la base de datos gestionVentas.");
            return conexion;
        } catch (SQLException e) {
            System.err.println("❌ Error al conectar: " + e.getMessage());
            return null;
        }
    }
}
