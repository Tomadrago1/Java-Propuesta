package logic;

import data.DaoEntrenamiento;
import entities.Entrenamiento;

public class ctrlEntrenamiento {
  private DaoEntrenamiento de = new DaoEntrenamiento();
	
  public void guardarEntrenamiento(Entrenamiento ent) {
    de.guardarEntrenamiento(ent);
  }
}
