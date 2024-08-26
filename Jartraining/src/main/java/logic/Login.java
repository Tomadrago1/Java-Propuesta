package logic;

import java.util.LinkedList;

import data.DaoUsuario;
import data.DaoProfesional;
import entities.Usuario;
import entities.Profesional;

public class Login {
	private DaoUsuario du;
	private DaoProfesional dp;

	public Login() {
		du = new DaoUsuario();
		dp = new DaoProfesional();
	}

	public Usuario validate(Usuario u) {
		/*
		 * para hacer más seguro el manejo de passwords este sería un lugar
		 * adecuado para generar un hash de la password utilizando un cifrado
		 * asimétrico como sha256 y utilizar el hash en lugar de la password en plano
		 */

		return du.getByUser(u);
	}

	public LinkedList<Usuario> getAll() {
		return du.getAll();
	}

	/*
	 * public Usuario getByDocumento(Persona per) {
	 * return dp.getByDocumento(per);
	 * 
	 * }
	 */

	public void add(Usuario u) {
		du.addUsuario(u);
	}

	public boolean modificar(int id, String nombre, String apellido, String email, String nombreUsuario, int tipoUsu,
			String password) {
		// Obtiene todos los usuarios utilizando DaoProfesional
		return du.modificarUser(id, nombre, apellido, email, nombreUsuario, tipoUsu, password);
	}

	public Profesional getProfesionalById(int id) {
		// Obtiene un profesional por su ID utilizando DaoProfesional
		return dp.getProfesionalById(id);
	}

	public LinkedList<Profesional> getAllProfesionales() {
		// Obtiene todos los profesionales utilizando DaoProfesional
		return dp.getAll();
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
}
