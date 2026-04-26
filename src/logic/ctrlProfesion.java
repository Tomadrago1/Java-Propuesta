package logic;

import java.util.LinkedList;
import data.DaoProfesion;
import entities.Profesion;

public class ctrlProfesion {
    private DaoProfesion dp;

    public ctrlProfesion() {
        dp = new DaoProfesion();
    }

    public LinkedList<Profesion> getAll() {
        return dp.getAll();
    }

    public Profesion getProfesionById(int id) {
        return dp.getProfesionById(id);
    }

    public boolean addProfesion(Profesion p) {
        return dp.addProfesion(p);
    }

    public boolean modificarProfesion(int id, String nombre) {
        return dp.modificarProfesion(id, nombre);
    }

    public boolean eliminarProfesion(int id) {
        return dp.eliminarProfesion(id);
    }

    public LinkedList<Profesion> searchByNombre(String query) {
        return dp.searchByNombre(query);
    }
}
