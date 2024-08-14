package logic;

import java.util.LinkedList;
import java.util.Map;
import data.DaoReceta;
import entities.Receta;

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
    
    public LinkedList<Map<String, Object>> getIngredientesConCantidad(int idReceta){
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
	public boolean addIngredienteReceta(int idReceta,int idIngrediente,double cantidad) {
		return dr.addIngredienteReceta(idReceta,idIngrediente,cantidad);
	};
	
	public boolean borrarIngredienteReceta(int idReceta,int idIngrediente) {
		return dr.borrarIngredienteReceta(idReceta,idIngrediente);
	};
	public boolean modificarCantidadIngredienteReceta(int idReceta,int idIngrediente,double cantidad) {
		return dr.modificarCantidadIngredienteReceta(idReceta,idIngrediente,cantidad);
	};
}
