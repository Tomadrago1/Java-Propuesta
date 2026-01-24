package logic;

import java.util.LinkedList;

import data.DaoIngrediente;
import entities.Ingrediente;
import entities.NutrienteIngrediente;

public class ctrlIngrediente {
    private DaoIngrediente di;

    public ctrlIngrediente() {
        di = new DaoIngrediente();
    }

    public Ingrediente getById(int id) {
        return di.getIngredienteById(id);
    }

    public LinkedList<Ingrediente> getAll() {
        return di.getAll();
    }

    public boolean add(Ingrediente i) {
        return di.addIngrediente(i);
    }

    public boolean eliminarIngrediente(int id) {
        return di.eliminarIngrediente(id);
    }

    public boolean modificar(int id, String nombre, String descripcion) {
        return di.modificarIngrediente(id, nombre, descripcion);
    }

    public LinkedList<NutrienteIngrediente> getNutrientesConCantidad(int idIngrediente) {
        return di.getNutrientesIngrediente(idIngrediente);
    }

    public boolean modificarCantidadNutrienteIngrediente(int idNutriente, int idIngrediente, double cantidad) {
        return di.modificarCantidadNutrienteIngrediente(idNutriente, idIngrediente, cantidad);
    }

    public boolean addNutrienteIngrediente(int idNutriente, int idIngrediente, double cantidad) {
        return di.addNutrienteIngrediente(idNutriente, idIngrediente, cantidad);
    }

    public boolean borrarNutrienteIngrediente(int idNutriente, int idIngrediente) {
        return di.borrarNutrienteIngrediente(idNutriente, idIngrediente);
    }

}