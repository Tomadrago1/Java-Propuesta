package data;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import entities.Entrenamiento;

public class DaoEntrenamiento {
  
  public void guardarEntrenamiento(Entrenamiento ent) {
    PreparedStatement stmt = null;
    try {
      stmt = DbConnector.getInstancia().getConn().prepareStatement(
          "insert into entrenamiento (id_usuario, id_rutina, id_ejercicio, fecha_entrenamiento, serie, repeticion, tiempo, peso) values (?,?,?,?,?,?,?,?)");
      stmt.setInt(1, ent.getIdUsuario());
      stmt.setInt(2, ent.getIdRutina());
      stmt.setInt(3, ent.getIdEjercicio());
      stmt.setDate(4, java.sql.Date.valueOf(ent.getFecha()));
      stmt.setInt(5, ent.getSeries());
      if (ent.getRepes() == 0) {
        stmt.setNull(6, java.sql.Types.INTEGER);
      } else {
        stmt.setInt(6, ent.getRepes());
      }
      if (ent.getTiempo() == null || ent.getTiempo().trim().isEmpty()) {
        stmt.setNull(7, java.sql.Types.VARCHAR);
      } else {
        stmt.setString(7, ent.getTiempo());
      }
      stmt.setDouble(8, ent.getPeso());
      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (stmt != null) {
          stmt.close();
        }
        DbConnector.getInstancia().releaseConn();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
}
