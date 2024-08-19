package entities;

public class Nutriente {
    private int id_nutriente;
    private String nombre;
    private String descripcion;

    public int getId_nutriente() {
        return id_nutriente;
    }

    public void setId_nutriente(int id_nutriente) {
        this.id_nutriente = id_nutriente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
