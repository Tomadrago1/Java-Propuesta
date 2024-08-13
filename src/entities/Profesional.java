package entities;

public class Profesional {
    private int idProfesional;
    private String nombre;
    private String apellido;
    private String profesion;
    private String nombreUsuario;
    private String password;
    private boolean estado;

    // Getters
    public int getIdProfesional() {
        return idProfesional;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getProfesion() {
        return profesion;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    // Setters
    public void setIdProfesional(int idProfesional) {
        this.idProfesional = idProfesional;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
    public boolean getEstado() {
        return estado;
    }

    // toString method
    @Override
    public String toString() {
        return "Profesional{" +
                "idProfesional=" + idProfesional +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", profesion='" + profesion + '\'' +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
