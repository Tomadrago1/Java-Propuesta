package entities;

import java.time.LocalDate;

public class Entrenamiento {
  private int idEjercicio;
  private int idRutina;
  private int idUsuario;
  private LocalDate fecha;
  private int series;
  private Integer repes;
  private String tiempo;
  private double peso;

  public Entrenamiento(int idEjercicio, int idRutina, int idUsuario, LocalDate fecha, int series, Integer repes, String tiempo, double peso) {
    this.idEjercicio = idEjercicio;
    this.idRutina = idRutina;
    this.idUsuario = idUsuario;
    this.fecha = fecha;
    this.series = series;
    this.repes = repes;
    this.tiempo = tiempo;
    this.peso = peso;
  }

  public Entrenamiento() {
  }

  public int getIdEjercicio() {
    return idEjercicio;
  }

  public void setIdEjercicio(int idEjercicio) {
    this.idEjercicio = idEjercicio;
  }

  public int getIdRutina() {
    return idRutina;
  }

  public void setIdRutina(int idRutina) {
    this.idRutina = idRutina;
  }

  public int getIdUsuario() {
    return idUsuario;
  }

  public void setIdUsuario(int idUsuario) {
    this.idUsuario = idUsuario;
  }

  public int getSeries() {
    return series;
  }

  public void setSeries(int series) {
    this.series = series;
  }

  public Integer getRepes() {
    return repes;
  }

  public void setRepes(Integer repes) {
    this.repes = repes;
  }

  public String getTiempo() {
    return tiempo;
  }

  public void setTiempo(String tiempo) {
    this.tiempo = tiempo;
  }

  public double getPeso() {
    return peso;
  }

  public void setPeso(double peso) {
    this.peso = peso;
  }

  public LocalDate getFecha() {
    return fecha;
  }

  public void setFecha(LocalDate fecha) {
    this.fecha = fecha;
  }
}
