package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import entities.Profesion;

public class DaoProfesion {

    public LinkedList<Profesion> getAll() {
        Statement stmt = null;
        ResultSet rs = null;
        LinkedList<Profesion> profesiones = new LinkedList<>();

        try {
            stmt = DbConnector.getInstancia().getConn().createStatement();
            rs = stmt.executeQuery("SELECT id, nombre FROM profesion");
            
            while (rs != null && rs.next()) {
                Profesion p = new Profesion();
                p.setId_profesion(rs.getInt("id"));
                p.setNombre(rs.getString("nombre"));
                profesiones.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return profesiones;
    }

    public Profesion getProfesionById(int id) {
        Profesion p = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = DbConnector.getInstancia().getConn().prepareStatement("SELECT id, nombre FROM profesion WHERE id = ?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs != null && rs.next()) {
                p = new Profesion();
                p.setId_profesion(rs.getInt("id"));
                p.setNombre(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return p;
    }

    public boolean addProfesion(Profesion p) {
        PreparedStatement stmt = null;
        ResultSet keyResultSet = null;
        try {
            stmt = DbConnector.getInstancia().getConn().prepareStatement("INSERT INTO profesion(nombre) VALUES(?)", PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, p.getNombre());
            stmt.executeUpdate();
            keyResultSet = stmt.getGeneratedKeys();
            if (keyResultSet != null && keyResultSet.next()) {
                p.setId_profesion(keyResultSet.getInt(1));
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (keyResultSet != null) keyResultSet.close();
                if (stmt != null) stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean modificarProfesion(int id, String nombre) {
        PreparedStatement stmt = null;
        try {
            stmt = DbConnector.getInstancia().getConn().prepareStatement("UPDATE profesion SET nombre = ? WHERE id = ?");
            stmt.setString(1, nombre);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (stmt != null) stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean eliminarProfesion(int id) {
        PreparedStatement stmt = null;
        try {
            stmt = DbConnector.getInstancia().getConn().prepareStatement("DELETE FROM profesion WHERE id = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (stmt != null) stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public LinkedList<Profesion> searchByNombre(String query) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        LinkedList<Profesion> profesiones = new LinkedList<>();

        try {
            stmt = DbConnector.getInstancia().getConn().prepareStatement("SELECT id, nombre FROM profesion WHERE nombre LIKE ?");
            stmt.setString(1, "%" + query + "%");
            rs = stmt.executeQuery();
            
            while (rs != null && rs.next()) {
                Profesion p = new Profesion();
                p.setId_profesion(rs.getInt("id"));
                p.setNombre(rs.getString("nombre"));
                profesiones.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return profesiones;
    }
}
