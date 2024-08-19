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
    
    public boolean eliminarIngrediente(int id){
    	return di.eliminarIngrediente(id);
    }
    
    public boolean modificar(int id, String nombre, String descripcion) {
        return di.modificarIngrediente(id, nombre, descripcion);
    }
}