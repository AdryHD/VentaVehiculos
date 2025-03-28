package ventavehiculos.dao;

import ventavehiculos.model.Cliente;
import ventavehiculos.model.Usuario;

public class PruebaRegistroCliente {
    public static void main(String[] args) {

        // Crear un objeto Usuario
        Usuario usuario = new Usuario(
                "Laura",                // nombre
                "Ramírez",              // apellido
                "laura@email.com",      // correo
                "laurita",              // nombre de usuario
                "1234",                 // contraseña
                "cliente"               // tipo de usuario
        );

        // Crear un objeto Cliente (hereda de Persona)
        Cliente cliente = new Cliente(
                "Laura",
                "Ramírez",
                "laura@email.com",
                "7777-8888"             // teléfono
        );

        // Intentar registrar en la base de datos
        ClienteDAO clienteDAO = new ClienteDAO();
        boolean exito = clienteDAO.registrarCliente(cliente, usuario);

        if (exito) {
            System.out.println("✅ Cliente registrado exitosamente.");
        } else {
            System.out.println("❌ Error al registrar el cliente.");
        }
    }
}
