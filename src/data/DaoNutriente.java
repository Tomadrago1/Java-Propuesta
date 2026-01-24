package data;

import entities.*;
import java.sql.*;
import java.util.LinkedList;

public class DaoNutriente {
  public LinkedList<Nutriente> getAll() {
    Statement stmt = null;
    ResultSet rs = null;
    LinkedList<Nutriente> nutrientes = new LinkedList<>();

    try {
      stmt = DbConnector.getInstancia().getConn().createStatement();
      rs = stmt.executeQuery("select * from nutriente");
      if (rs != null) {
        while (rs.next()) {
          Nutriente n = new Nutriente();
          n.setId_nutriente(rs.getInt("id"));
          n.setNombre(rs.getString("nombre"));
          n.setDescripcion(rs.getString("descripcion"));
          nutrientes.add(n);
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();

    } finally {
      try {
        if (rs != null) {
          rs.close();
        }
        if (stmt != null) {
          stmt.close();
        }
        DbConnector.getInstancia().releaseConn();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return nutrientes;
  }

  public boolean addNutriente(Nutriente n) {
    PreparedStatement stmt = null;
    ResultSet keyResultSet = null;
    try {
      stmt = DbConnector.getInstancia().getConn().prepareStatement(
          "insert into nutriente(nombre, descripcion) values(?,?)",
          PreparedStatement.RETURN_GENERATED_KEYS);
      stmt.setString(1, n.getNombre());
      stmt.setString(2, n.getDescripcion());
      stmt.executeUpdate();
      keyResultSet = stmt.getGeneratedKeys();
      if (keyResultSet != null && keyResultSet.next()) {
        n.setId_nutriente(keyResultSet.getInt(1));
      }
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    } finally {
      try {
        if (keyResultSet != null)
          keyResultSet.close();
        if (stmt != null)
          stmt.close();
        DbConnector.getInstancia().releaseConn();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  public Nutriente getNutrienteById(int id) {
    PreparedStatement stmt = null;
    ResultSet rs = null;
    Nutriente n = null;
    try {
      stmt = DbConnector.getInstancia().getConn().prepareStatement("select * from nutriente where id = ?");
      stmt.setInt(1, id);
      rs = stmt.executeQuery();
      if (rs != null && rs.next()) {
        n = new Nutriente();
        n.setId_nutriente(rs.getInt("id"));
        n.setNombre(rs.getString("nombre"));
        n.setDescripcion(rs.getString("descripcion"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (rs != null) {
          rs.close();
        }
        if (stmt != null) {
          stmt.close();
        }
        DbConnector.getInstancia().releaseConn();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return n;
  }

  public boolean modificarNutriente(int id, String nombre, String descripcion) {
    PreparedStatement stmt = null;
    try {
      stmt = DbConnector.getInstancia().getConn()
          .prepareStatement("update nutriente set nombre = ?, descripcion = ? where id = ?");
      stmt.setString(1, nombre);
      stmt.setString(2, descripcion);
      stmt.setInt(3, id);
      stmt.executeUpdate();
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
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

  public boolean eliminarNutriente(int idNutriente) {
    PreparedStatement stmt1 = null;
    PreparedStatement stmt2 = null;
    PreparedStatement stmt3 = null;
    Connection conn = null;
    boolean result = false;

    try {
      conn = DbConnector.getInstancia().getConn();
      conn.setAutoCommit(false); // Iniciar la transacción

      // Elimina las relaciones en la tabla 'Necesidad'
      stmt1 = conn.prepareStatement("DELETE FROM Necesidad WHERE id_nutriente = ?");
      stmt1.setInt(1, idNutriente);
      stmt1.executeUpdate();

      // Elimina las relaciones en la tabla 'Ingrediente'
      stmt2 = conn.prepareStatement("DELETE FROM nutriente_ingrediente WHERE id_nutriente = ?");
      stmt2.setInt(1, idNutriente);
      stmt2.executeUpdate();

      // Elimina el nutriente en la tabla principal
      stmt3 = conn.prepareStatement("DELETE FROM Nutriente WHERE id = ?");
      stmt3.setInt(1, idNutriente);
      stmt3.executeUpdate();

      // Confirma la transacción
      conn.commit();
      result = true;

    } catch (SQLException e) {
      e.printStackTrace();
      if (conn != null) {
        try {
          // Revertir en caso de error
          conn.rollback();
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
      }
    } finally {
      try {
        if (stmt1 != null)
          stmt1.close();
        if (stmt2 != null)
          stmt2.close();
        if (stmt3 != null)
          stmt3.close();
        if (conn != null)
          conn.setAutoCommit(true); // Restablecer el modo de autocommit
        DbConnector.getInstancia().releaseConn();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return result;
  }
}
