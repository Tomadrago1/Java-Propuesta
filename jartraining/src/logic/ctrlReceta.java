package logic;

import java.util.LinkedList;

import data.DaoReceta;
import entities.Ingrediente;
import entities.Receta;

public class ctrlReceta {
    private DaoReceta dr;

    public ctrlReceta() {
        dr = new DaoReceta();
    }

    public Receta getById(int id) {
    	return dr.getRecetaById(id);
    }
    /*
    public Usuario validate(Usuario u) {
        return du.getByUser(u);
    }*/

    public LinkedList<Receta> getAll() {
        return dr.getAll();
    }
    
    public LinkedList<Ingrediente> getIngredientes(int idReceta){
    	return dr.getIngredientesReceta(idReceta);
    }
    /*public void add(Usuario u) {
        du.addUsuario(u);
    }
    
    public boolean eliminar(int id) {
    	return du.eliminarUsuario(id);
    }
    
    public boolean modificar(int id, String nombre, String apellido, String email, String nombreUsuario, int tipoUsu) {
        // Obtiene todos los usuarios utilizando DaoProfesional
        return du.modificarUser(id, nombre, apellido, email, nombreUsuario, tipoUsu);
    }*/
}