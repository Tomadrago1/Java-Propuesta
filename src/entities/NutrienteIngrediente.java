package entities;

public class NutrienteIngrediente {
  private Nutriente nutriente;
  private Ingrediente ingrediente;
  private double cantidad;
  private String unidad_medida;

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

  public String getUnidad_medida() {
    return unidad_medida;
  }

  public void setUnidad_medida(String unidad_medida) {
    this.unidad_medida = unidad_medida;
  }
}
