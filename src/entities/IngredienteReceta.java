package entities;

public class IngredienteReceta {
  private Ingrediente ingrediente;
  private Receta receta;
  private double cantidad;
  private String unidad_medida;

  public IngredienteReceta() {
  }

  public Ingrediente getIngrediente() {
    return ingrediente;
  }

  public void setIngrediente(Ingrediente ingrediente) {
    this.ingrediente = ingrediente;
  }

  public Receta getReceta() {
    return receta;
  }

  public void setReceta(Receta receta) {
    this.receta = receta;
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
