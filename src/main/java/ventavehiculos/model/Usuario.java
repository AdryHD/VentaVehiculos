package ventavehiculos.model;

public class Usuario extends Persona {
    private String nombreUsuario;
    private String contraseña;
    private String tipoUsuario; // "admin" o "cliente"

    private static Usuario usuarioActivo; //almacena al usuario que esta en la sesion

    // constructor
    public Usuario(String nombre, String apellido, String correo, String nombreUsuario, String contraseña, String tipoUsuario) {
        super(nombre, apellido, correo);
        this.nombreUsuario = nombreUsuario;
        this.contraseña = contraseña;
        this.tipoUsuario = tipoUsuario;
    }

    // set y gat
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }


    // abstractos
    @Override
    public void mostrarInformacion() {
        System.out.println("Usuario: " + nombre + " " + apellido + " (" + tipoUsuario + ")");
    }

    @Override
    public boolean autenticar(String usuario, String contraseña) {
        return this.nombreUsuario.equals(usuario) && this.contraseña.equals(contraseña);
    }

    public static void setUsuarioActivo (Usuario usuario){
        usuarioActivo = usuario;
    }

    public static Usuario getUsuarioActivo(){
        return usuarioActivo;
    }
}
