<<<<<<< HEAD
package logic;

import java.util.LinkedList;
import data.*;
import entities.*;

public class ctrlEjercicios {
	private DaoEjercicio de = new DaoEjercicio();
	
	public LinkedList<Ejercicio> getAll() {
		return de.getAll();
	}
	
	public boolean modificarEjercicio(int id,String nombre,String desc) {
		return de.modificarEjercicio(id, nombre, desc);
	}
	
	public Ejercicio getOne(int idEjercicio) {
		return de.getOne(idEjercicio);
	}
}
=======
package logic;

import java.util.LinkedList;
import data.*;
import entities.*;

public class ctrlEjercicios {
	private DaoEjercicio de = new DaoEjercicio();
	
	public LinkedList<Ejercicio> getAll() {
		return de.getAll();
	}
	
	public boolean modificarEjercicio(int id,String nombre,String desc) {
		return de.modificarEjercicio(id, nombre, desc);
	}
	
	public Ejercicio getOne(int idEjercicio) {
		return de.getOne(idEjercicio);
	}
}
>>>>>>> 2a7a2cddb7c46cdba58517d715cf07559706d21c
