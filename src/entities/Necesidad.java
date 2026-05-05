package entities;

import java.time.LocalDate;

public class Necesidad {
    private int id_usuario;
    private int id_nutriente;
    private int id_profesional;
    private LocalDate fecha;
    private double cantidad_min;
    private double cantidad_max;

    // Optional relation objects for UI convenience
    private Nutriente nutriente;
    private Usuario profesional;

    public Necesidad() {}

    public Usuario getProfesional() {
        return profesional;
    }

    public void setProfesional(Usuario profesional) {
        this.profesional = profesional;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_nutriente() {
        return id_nutriente;
    }

    public void setId_nutriente(int id_nutriente) {
        this.id_nutriente = id_nutriente;
    }

    public int getId_profesional() {
        return id_profesional;
    }

    public void setId_profesional(int id_profesional) {
        this.id_profesional = id_profesional;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public double getCantidad_min() {
        return cantidad_min;
    }

    public void setCantidad_min(double cantidad_min) {
        this.cantidad_min = cantidad_min;
    }

    public double getCantidad_max() {
        return cantidad_max;
    }

    public void setCantidad_max(double cantidad_max) {
        this.cantidad_max = cantidad_max;
    }

    public Nutriente getNutriente() {
        return nutriente;
    }

    public void setNutriente(Nutriente nutriente) {
        this.nutriente = nutriente;
    }
}
