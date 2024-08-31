package entities;

public class NutrienteIngrediente {
  private Nutriente nutriente;
  private Ingrediente ingrediente;
  private double cantidad;

  public NutrienteIngrediente(Nutriente nutriente, Ingrediente ingrediente, double cantidad) {
    this.nutriente = nutriente;
    this.ingrediente = ingrediente;
    this.cantidad = cantidad;
  }

  public NutrienteIngrediente() {
  }

  public Nutriente getNutriente() {
    return nutriente;
  }

  public void setNutriente(Nutriente nutriente) {
    this.nutriente = nutriente;
  }

  public Ingrediente getIngrediente() {
    return ingrediente;
  }

  public void setIngrediente(Ingrediente ingrediente) {
    this.ingrediente = ingrediente;
  }

  public double getCantidad() {
    return cantidad;
  }

  public void setCantidad(double cantidad) {
    this.cantidad = cantidad;
  }
}
