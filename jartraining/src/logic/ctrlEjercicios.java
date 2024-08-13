package logic;

import java.util.LinkedList;
import data.*;
import entities.*;

public class ctrlEjercicios {
	private DaoEjercicio de = new DaoEjercicio();
	
	public LinkedList<Ejercicio> getAll() {
		return de.getAll();
	}
	
}
