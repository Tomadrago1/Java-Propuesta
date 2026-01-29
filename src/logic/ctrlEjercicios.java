package logic;

import java.util.LinkedList;
import data.*;
import entities.*;

public class ctrlEjercicios {
	private DaoEjercicio de = new DaoEjercicio();
	
	public LinkedList<Ejercicio> getAll() {
		return de.getAll();
	}
	
	public boolean modificarEjercicio(int id, String nombre, String desc, String zona, String tipoEjercicio) {
		return de.modificarEjercicio(id, nombre, desc, zona, tipoEjercicio);
	}
	
	public Ejercicio getOne(int idEjercicio) {
		return de.getOne(idEjercicio);
	}

	public LinkedList<Ejercicio> getEjerciciosByRutina(int idRutina) {
		return de.getEjerciciosByRutina(idRutina);
	}
}
