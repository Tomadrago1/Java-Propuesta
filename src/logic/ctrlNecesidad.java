package logic;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import data.DaoNecesidad;
import entities.Necesidad;

public class ctrlNecesidad {
    private DaoNecesidad dao;

    public ctrlNecesidad() {
        this.dao = new DaoNecesidad();
    }

    public boolean guardarNecesidades(List<Necesidad> lista) {
        return dao.guardarNecesidades(lista);
    }

    public LinkedList<Necesidad> getNecesidadesPorUsuarioFecha(int idUsuario, LocalDate fecha) {
        return dao.getNecesidadesPorUsuarioFecha(idUsuario, fecha);
    }

    public LinkedList<Necesidad> getUltimasNecesidadesPorUsuario(int idUsuario) {
        return dao.getUltimasNecesidadesPorUsuario(idUsuario);
    }

    public java.util.LinkedHashMap<LocalDate, LinkedList<Necesidad>> getHistorialNecesidades(int idUsuario) {
        return dao.getHistorialNecesidades(idUsuario);
    }
}
