package logic;

import java.util.LinkedList;

import data.DaoUsuario;
import data.DaoProfesional;
import entities.Profesional;
import entities.Usuario;

public class ctrlUsuario {
    private static DaoUsuario du;
    private static DaoProfesional dp;

    public ctrlUsuario() {
        du = new DaoUsuario();
        dp = new DaoProfesional();
    }

    public Usuario getUserById(int id) {
        return du.getUserById(id);
    }

    public Usuario validate(Usuario u) {
        return du.getByUser(u);
    }

    public LinkedList<Usuario> getAll() {
        return du.getAll();
    }

    public void addUsuario(Usuario u) {
        du.addUsuario(u);
    }

    public boolean eliminarUsuario(int id) {
        return du.eliminarUsuario(id);
    }

    public boolean modificarUsuario(int id, String nombre, String apellido, String email, String nombreUsuario,
            int tipoUsu,
            String password) {
        // Obtiene todos los usuarios utilizando DaoProfesional
        return du.modificarUser(id, nombre, apellido, email, nombreUsuario, tipoUsu, password);
    }

    public Profesional getProfesionalById(int id) {
        return dp.getProfesionalById(id);
    }

    public boolean modificarProfesional(int id, String nombre, String apellido, String profesion, String nombreUsuario,
            String password, String email) {
        return dp.modificarProfesional(id, nombre, apellido, profesion, nombreUsuario, password, email);
    }

    public void addProfesional(Profesional p) {
        dp.addProfesional(p);
    }

    public LinkedList<Profesional> getAllProfesionales() {
        return dp.getAll();
    }

    /*public LinkedList<Profesional> getProfesionalesByProfesion(String profesion) {
        return dp.getProfesionalesByProfesion(profesion);
    }*/
}