package logic;

import java.util.LinkedList;

import data.DaoEntrenamiento;
import entities.Entrenamiento;

public class ctrlEntrenamiento {
  private DaoEntrenamiento de = new DaoEntrenamiento();

  public void guardarEntrenamiento(Entrenamiento ent) {
    de.guardarEntrenamiento(ent);
  }

  public LinkedList<Entrenamiento> getEntrenamientosByUsuario(int idUsuario) {
    return de.getEntrenamientosByUsuario(idUsuario);
  }
}
