package entities;

public class EjercicioRutina {
  private Ejercicio ejercicio;
  private Rutina rutina;
  private int series_aproximadas;
  private Integer repes_aproximadas;
  private String tiempo;

  public EjercicioRutina() {
  }

  public Ejercicio getEjercicio() {
    return ejercicio;
  }

  public void setEjercicio(Ejercicio ejercicio) {
    this.ejercicio = ejercicio;
  }

  public Rutina getRutina() {
    return rutina;
  }

  public void setRutina(Rutina rutina) {
    this.rutina = rutina;
  }

  public int getSeriesAproximadas() {
    return series_aproximadas;
  }

  public void setSeriesAproximadas(int series_aproximadas) {
    this.series_aproximadas = series_aproximadas;
  }

  public Integer getRepesAproximadas() {
    return repes_aproximadas;
  }

  public void setRepesAproximadas(Integer repes_aproximadas) {
    this.repes_aproximadas = repes_aproximadas;
  }

  public String getTiempo() {
    return tiempo;
  }

  public void setTiempo(String tiempo) {
    this.tiempo = tiempo;
  }
}
