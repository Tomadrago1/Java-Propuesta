package entities;

public class Ingrediente {
    private int id;
    private String nombre;
    private String desc;

    // Constructor vacío
    public Ingrediente() {
    }

    // Getter para id
    public int getId() {
        return id;
    }

    // Setter para id
    public void setId(int id) {
        this.id = id;
    }

    // Getter para nombre
    public String getNombre() {
        return nombre;
    }

    // Setter para nombre
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Getter para desc
    public String getDesc() {
        return desc;
    }

    // Setter para desc
    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Ingrediente{" +
               "id=" + id +
               ", nombre='" + nombre + '\'' +
               ", desc='" + desc + '\'' +
               '}';
    }
}