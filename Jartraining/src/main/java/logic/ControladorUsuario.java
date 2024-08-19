package logic;

import java.util.LinkedList;

import data.DaoUsuario;
import entities.Usuario;

public class ControladorUsuario {
	private DaoUsuario du;

	public ControladorUsuario() {
		du = new DaoUsuario();
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

	public void add(Usuario u) {
		du.addUsuario(u);
	}

	public boolean modificar(int id, String nombre, String apellido, String email, String nombreUsuario, int tipoUsu) {
		return du.modificarUser(id, nombre, apellido, email, nombreUsuario, tipoUsu);
	}
}
