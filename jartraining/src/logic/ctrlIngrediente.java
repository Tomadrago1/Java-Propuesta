package logic;
import java.util.LinkedList;
import data.DaoIngrediente;
import entities.Ingrediente;


public class ctrlIngrediente{
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
    
    /*public LinkedList<Map<String, Object>> getIngredientesConCantidad(int idReceta){
    	return di.getIngredientesReceta(idReceta);
    }
    
    public boolean modificar(int id, String nombre, String descripcion, String nivelDificultad) {
        // Obtiene todos los profesionales utilizando DaoProfesional
        return di.modificarReceta(id, nombre, descripcion, nivelDificultad);
    }
    
    public boolean eliminarReceta(int id) {
    	return di.eliminarReceta(id);
    }
    
    public void add(Ingrediente r) {
    di.addReceta(r);
	}*/
}