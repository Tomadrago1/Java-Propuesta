package logic;

import java.util.LinkedList;

import data.DaoMedida;
import entities.Medida;

public class ctrlMedida {
    private DaoMedida dm;

    public ctrlMedida() {
        dm = new DaoMedida();
    }

    public LinkedList<Medida> getByUsuario(int id) {
        return dm.getByUsuario(id);
    }

    public void add(Medida m) {
        dm.addMedida(m);
    }
    /*
     * public Medida validate(Medida u) {
     * return dm.getByUser(m;
     * }
     * 
     * public LinkedList<Medida> getAll() {
     * return dm.getAll();
     * }
     * 
     * 
     * 
     * public boolean eliminar(int id) {
     * return dm.eliminarUsuario(id);
     * }
     * 
     * public boolean modificar(int id, String nombre, String apellido, String
     * email, String nombreUsuario, int tipoUsu) {
     * // Obtiene todos los usuarios utilizando DaoProfesional
     * return dm.modificarUser(id, nombre, apellido, email, nombreUsuario, tipoUsu);
     * }
     */
}