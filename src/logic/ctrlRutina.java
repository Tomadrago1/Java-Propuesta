package logic;

import java.util.LinkedList;
import java.util.Map;

import data.DaoRutina;

import entities.Rutina;

public class ctrlRutina {
    private DaoRutina du;

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
    
    public boolean modificarRutina(int id,String nombre,String desc){
    	return du.modificarRutina(id, nombre, desc);
    }
    
    public LinkedList<Map<String, Object>> getEjerciciosByRutina(int id) {
    	return du.getEjerciciosByRutina(id);
    }
    
    public boolean modificarEjercicioRutina(int id_rut,int id_eje,int series,int repes) {
    	return du.modificarEjercicioRutina(id_rut, id_eje, series, repes);
    }
    
    public boolean quitarEjercicioRutina(int id_rut,int id_eje) {
    	return du.quitarEjercicioRutina(id_rut, id_eje);
    }
}