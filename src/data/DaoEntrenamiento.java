package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import entities.Entrenamiento;

public class DaoEntrenamiento {

  public void guardarEntrenamiento(Entrenamiento ent) {
    PreparedStatement stmt = null;
    try {
      stmt = DbConnector.getInstancia().getConn().prepareStatement(
          "insert into entrenamiento (id_usuario, id_rutina, id_ejercicio, fecha_hora, serie, repeticion, tiempo, peso) values (?,?,?,NOW(),?,?,?,?)");
      stmt.setInt(1, ent.getIdUsuario());
      stmt.setInt(2, ent.getIdRutina());
      stmt.setInt(3, ent.getIdEjercicio());
      stmt.setInt(4, ent.getSeries());
      if (ent.getRepes() == 0) {
        stmt.setNull(5, java.sql.Types.INTEGER);
      } else {
        stmt.setInt(5, ent.getRepes());
      }
      if (ent.getTiempo() == null || ent.getTiempo().trim().isEmpty()) {
        stmt.setNull(6, java.sql.Types.VARCHAR);
      } else {
        stmt.setString(6, ent.getTiempo());
      }
      stmt.setDouble(7, ent.getPeso());
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

  public LinkedList<Entrenamiento> getEntrenamientosByUsuario(int idUsuario) {
    PreparedStatement stmt = null;
    LinkedList<Entrenamiento> entrenamientos = new LinkedList<Entrenamiento>();
    try {
      stmt = DbConnector.getInstancia().getConn().prepareStatement(
          "select * from entrenamiento where id_usuario = ? order by fecha_hora desc");
      stmt.setInt(1, idUsuario);
      if (stmt.execute()) {
        Entrenamiento ent = null;
        ResultSet rs = stmt.getResultSet();
        while (rs.next()) {
          ent = new Entrenamiento();
          ent.setId(rs.getInt("id"));
          ent.setIdUsuario(rs.getInt("id_usuario"));
          ent.setIdRutina(rs.getInt("id_rutina"));
          ent.setIdEjercicio(rs.getInt("id_ejercicio"));
          java.sql.Timestamp timestamp = rs.getTimestamp("fecha_hora");
          if (timestamp != null) {
            ent.setFechaHora(timestamp.toLocalDateTime());
          }
          ent.setSeries(rs.getInt("serie"));
          // Manejar repeticion que puede ser NULL
          int repes = rs.getInt("repeticion");
          ent.setRepes(rs.wasNull() ? 0 : repes);
          ent.setTiempo(rs.getString("tiempo"));
          ent.setPeso(rs.getDouble("peso"));
          entrenamientos.add(ent);
        }
      }
    } catch (SQLException e) {
      System.err.println("Error al obtener entrenamientos del usuario " + idUsuario + ": " + e.getMessage());
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
    return entrenamientos;
  }
}
