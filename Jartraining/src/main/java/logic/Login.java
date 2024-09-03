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
}
