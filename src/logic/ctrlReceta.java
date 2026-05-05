package logic;

import java.util.LinkedList;
import data.DaoReceta;
import entities.IngredienteReceta;
import entities.Receta;
import entities.NutrienteReceta;

public class ctrlReceta {
    private DaoReceta dr;

    public ctrlReceta() {
        dr = new DaoReceta();
    }

    public Receta getById(int id) {
        return dr.getRecetaById(id);
    }

    public LinkedList<Receta> getAll() {
        return dr.getAll();
    }

    public LinkedList<IngredienteReceta> getIngredientesConCantidad(int idReceta) {
        return dr.getIngredientesReceta(idReceta);
    }

    public boolean modificar(int id, String nombre, String descripcion, String nivelDificultad) {
        return dr.modificarReceta(id, nombre, descripcion, nivelDificultad);
    }

    public boolean eliminarReceta(int id) {
        return dr.eliminarReceta(id);
    }

    public void add(Receta r) {
        dr.addReceta(r);
    };

    public boolean addIngredienteReceta(int idReceta, int idIngrediente, double cantidad, String unidadMedida) {
        return dr.addIngredienteReceta(idReceta, idIngrediente, cantidad, unidadMedida);
    };

    public boolean borrarIngredienteReceta(int idReceta, int idIngrediente) {
        return dr.borrarIngredienteReceta(idReceta, idIngrediente);
    };

    public boolean modificarCantidadIngredienteReceta(int idReceta, int idIngrediente, double cantidad,
            String unidadMedida) {
        return dr.modificarCantidadIngredienteReceta(idReceta, idIngrediente, cantidad, unidadMedida);
    };

    public LinkedList<NutrienteReceta> getNutrientesConCantidad(int idReceta) {
        return dr.getNutrientesReceta(idReceta);
    }
    public LinkedList<Receta> getRecetasRecomendadas(int idUsuario) {
        return dr.getRecetasRecomendadas(idUsuario);
    }

    public LinkedList<Receta> getByProf(int idProfesional) {
        return dr.getByProf(idProfesional);
    }
}
