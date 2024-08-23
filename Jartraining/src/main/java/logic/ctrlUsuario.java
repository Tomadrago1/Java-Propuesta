package logic;

import java.util.LinkedList;

import data.DaoUsuario;
import entities.Usuario;

public class ctrlUsuario {
    private DaoUsuario du;

    public ctrlUsuario() {
        du = new DaoUsuario();
    }

    public Usuario getById(int id) {
    	return du.getUserById(id);
    }
    
    public Usuario validate(Usuario u) {
        return du.getByUser(u);
    }

    public LinkedList<Usuario> getAll() {
        return du.getAll();
    }

    public void add(Usuario u) {
        du.addUsuario(u);
    }
    
    public boolean eliminar(int id) {
    	return du.eliminarUsuario(id);
    }
    
    public boolean modificar(int id, String nombre, String apellido, String email, String nombreUsuario, int tipoUsu) {
        // Obtiene todos los usuarios utilizando DaoProfesional
        return du.modificarUser(id, nombre, apellido, email, nombreUsuario, tipoUsu);
    }
}