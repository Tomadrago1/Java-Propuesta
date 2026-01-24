package entities;

public class NutrienteReceta {
  private Nutriente nutriente;
  private double cantidad;
  private Receta receta;

  public NutrienteReceta() {
  }

  public Nutriente getNutriente() {
    return nutriente;
  }

  public void setNutriente(Nutriente nutriente) {
    this.nutriente = nutriente;
  }

  public double getCantidad() {
    return cantidad;
  }

  public void setCantidad(double cantidad) {
    this.cantidad = cantidad;
  }

  public Receta getReceta() {
    return receta;
  }

  public void setReceta(Receta receta) {
    this.receta = receta;
  }
}
