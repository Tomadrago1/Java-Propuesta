package logic;

import java.util.LinkedList;

import data.DaoNutriente;
import entities.Nutriente;

public class ctrlNutriente {
  private DaoNutriente dn;

  public ctrlNutriente() {
    dn = new DaoNutriente();
  }

  public LinkedList<Nutriente> getAll() {
    return dn.getAll();
  }

  public Nutriente getById(int id) {
    return dn.getNutrienteById(id);
  }

  public boolean add(Nutriente n) {
    return dn.addNutriente(n);
  }

  public boolean eliminarNutriente(int id) {
    return dn.eliminarNutriente(id);
  }

  public boolean modificar(int id, String nombre, String descripcion) {
    return dn.modificarNutriente(id, nombre, descripcion);
  }

}
