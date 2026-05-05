package logic;

import java.util.LinkedList;

import data.DaoRutina;
import entities.EjercicioRutina;
import entities.Rutina;

public class ctrlRutina {
    private static DaoRutina du;

    public ctrlRutina() {
        du = new DaoRutina();
    }

    public LinkedList<Rutina> getAll() {
        return du.getAll();
    }

    public boolean eliminarRutina(int id) {
        return du.eliminarRutina(id);
    }

    public Rutina getOne(int id) {
        return du.getOne(id);
    }

    public boolean modificarRutina(int id, String nombre, String desc) {
        return du.modificarRutina(id, nombre, desc);
    }

    public LinkedList<EjercicioRutina> getEjerciciosByRutina(int id) {
        return du.getEjerciciosByRutina(id);
    }

    public boolean modificarEjercicioRutina(int id_rut, int id_eje, int series, Integer repes, String tiempo) {
        return du.modificarEjercicioRutina(id_rut, id_eje, series, repes, tiempo);
    }

    public boolean agregarEjercicioRutina(int id_rut, int id_eje, int series, Integer repes, String tiempo) {
        return du.agregarEjercicioRutina(id_rut, id_eje, series, repes, tiempo);
    }

    public boolean quitarEjercicioRutina(int id_rut, int id_eje) {
        return du.quitarEjercicioRutina(id_rut, id_eje);
    }

    public LinkedList<Rutina> getRutinaByUsuario(int id_usuario) {
        return du.getRutinaByUsuario(id_usuario);
    }

    public boolean borrarRutinaUsuario(int id_usu, int id_rut) {
        return du.borrarRutinaUsuario(id_usu, id_rut);
    }

    public void agregarRutinaUsuario(int id_rut, int id_usu) {
        du.agregarRutinaUsuario(id_rut, id_usu);
    }

    public int getSeriesAprox(int id_eje, int id_rut) {
        return du.getSeriesAprox(id_eje, id_rut);
    }

    public int getRepsAprox(int id_eje, int id_rut) {
        return du.getRepsAprox(id_eje, id_rut);
    }
}