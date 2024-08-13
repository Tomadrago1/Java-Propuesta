package entities;

public class Receta {
    private int id;
    private Profesional profesional;
    private String nombre;
    private String desc;
    private String nivelDificultad;

    // Constructor vacío
    public Receta() {
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Profesional getProfesional() {
        return profesional;
    }

    public void setProfesional(Profesional profesional) {
        this.profesional = profesional;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getNivelDificultad() {
        return nivelDificultad;
    }

    public void setNivelDificultad(String nivelDificultad) {
        this.nivelDificultad = nivelDificultad;
    }
    
    @Override
    public String toString() {
        return "Receta{" +
                "id=" + id +
                ", profesional=" + (profesional != null ? profesional.getNombre() + " " + profesional.getApellido() : "Sin profesional") +
                ", nombre='" + nombre + '\'' +
                ", desc='" + desc + '\'' +
                ", nivelDificultad='" + nivelDificultad + '\'' +
                '}';
    }
}



