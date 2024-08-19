package logic;

import java.util.LinkedList;

import data.DaoProfesional;
import entities.Profesional;

public class ControladorProfesional {
	private DaoProfesional dp;

	public ControladorProfesional() {
		dp = new DaoProfesional();
	}

	public boolean modificar(int id, String nombre, String apellido, String profesion/* String nombreUsuario */) {
		// Obtiene todos los profesionales utilizando DaoProfesional
		return dp.modificarProfesional(id, nombre, apellido, profesion/* nombreUsuario */);
	}

	public boolean eliminarProfesional(int id) {
		return dp.eliminarProfesional(id);
	}

	public void add(Profesional p) {
		dp.addProfesional(p);
	}

	// Otros métodos que interactúan con DaoUsuario y DaoProfesional
	public LinkedList<Profesional> getAll() {
		return dp.getAll();
	}

	public Profesional getById(int id) {
		// Obtiene un profesional por su ID utilizando DaoProfesional
		return dp.getProfesionalById(id);
	}
}