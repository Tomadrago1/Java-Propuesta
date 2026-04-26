package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import entities.Necesidad;

public class DaoNecesidad {
    
    public boolean guardarNecesidades(List<Necesidad> necesidades) {
        if (necesidades == null || necesidades.isEmpty()) {
            return false;
        }

        PreparedStatement stmt = null;
        try {
            // Utilizamos ON DUPLICATE KEY UPDATE para que si el profesional ya cargó hoy, se actualice en lugar de dar error.
            stmt = DbConnector.getInstancia().getConn().prepareStatement(
                "INSERT INTO necesidad (id_usuario, id_nutriente, id_profesional, fecha, cantidad_min, cantidad_max) " +
                "VALUES (?, ?, ?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE id_profesional = VALUES(id_profesional), " +
                "cantidad_min = VALUES(cantidad_min), cantidad_max = VALUES(cantidad_max)"
            );

            for (Necesidad n : necesidades) {
                stmt.setInt(1, n.getId_usuario());
                stmt.setInt(2, n.getId_nutriente());
                stmt.setInt(3, n.getId_profesional());
                stmt.setDate(4, java.sql.Date.valueOf(n.getFecha()));
                stmt.setDouble(5, n.getCantidad_min());
                stmt.setDouble(6, n.getCantidad_max());
                stmt.addBatch();
            }

            int[] resultados = stmt.executeBatch();
            for (int r : resultados) {
                if (r == PreparedStatement.EXECUTE_FAILED) {
                    return false;
                }
            }
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

    public LinkedList<Necesidad> getNecesidadesPorUsuarioFecha(int idUsuario, java.time.LocalDate fecha) {
        LinkedList<Necesidad> lista = new LinkedList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = DbConnector.getInstancia().getConn().prepareStatement(
                "SELECT id_usuario, id_nutriente, id_profesional, fecha, cantidad_min, cantidad_max " +
                "FROM necesidad WHERE id_usuario = ? AND fecha = ?"
            );
            stmt.setInt(1, idUsuario);
            stmt.setDate(2, java.sql.Date.valueOf(fecha));

            rs = stmt.executeQuery();

            while (rs.next()) {
                Necesidad n = new Necesidad();
                n.setId_usuario(rs.getInt("id_usuario"));
                n.setId_nutriente(rs.getInt("id_nutriente"));
                n.setId_profesional(rs.getInt("id_profesional"));
                n.setFecha(rs.getDate("fecha").toLocalDate());
                n.setCantidad_min(rs.getDouble("cantidad_min"));
                n.setCantidad_max(rs.getDouble("cantidad_max"));
                lista.add(n);
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
        return lista;
    }

    public LinkedList<Necesidad> getUltimasNecesidadesPorUsuario(int idUsuario) {
        LinkedList<Necesidad> lista = new LinkedList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Busca la fecha más reciente registrada para este usuario
            stmt = DbConnector.getInstancia().getConn().prepareStatement(
                "SELECT id_usuario, id_nutriente, id_profesional, fecha, cantidad_min, cantidad_max " +
                "FROM necesidad " +
                "WHERE id_usuario = ? AND fecha = (" +
                "    SELECT MAX(fecha) FROM necesidad WHERE id_usuario = ?" +
                ")"
            );
            stmt.setInt(1, idUsuario);
            stmt.setInt(2, idUsuario);

            rs = stmt.executeQuery();

            while (rs.next()) {
                Necesidad n = new Necesidad();
                n.setId_usuario(rs.getInt("id_usuario"));
                n.setId_nutriente(rs.getInt("id_nutriente"));
                n.setId_profesional(rs.getInt("id_profesional"));
                n.setFecha(rs.getDate("fecha").toLocalDate());
                n.setCantidad_min(rs.getDouble("cantidad_min"));
                n.setCantidad_max(rs.getDouble("cantidad_max"));
                lista.add(n);
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
        return lista;
    }

    public java.util.LinkedHashMap<java.time.LocalDate, LinkedList<Necesidad>> getHistorialNecesidades(int idUsuario) {
        java.util.LinkedHashMap<java.time.LocalDate, LinkedList<Necesidad>> historial = new java.util.LinkedHashMap<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = DbConnector.getInstancia().getConn().prepareStatement(
                "SELECT n.fecha, n.cantidad_min, n.cantidad_max, " +
                "nu.id as id_nutriente, nu.nombre as nombre_nutriente, " +
                "u.id as id_profesional, u.nombre as prof_nombre, u.apellido as prof_apellido " +
                "FROM necesidad n " +
                "INNER JOIN nutriente nu ON n.id_nutriente = nu.id " +
                "INNER JOIN usuario u ON n.id_profesional = u.id " +
                "WHERE n.id_usuario = ? " +
                "ORDER BY n.fecha DESC, nu.nombre ASC"
            );
            stmt.setInt(1, idUsuario);
            rs = stmt.executeQuery();

            while (rs.next()) {
                java.time.LocalDate fecha = rs.getDate("fecha").toLocalDate();
                
                Necesidad n = new Necesidad();
                n.setId_usuario(idUsuario);
                n.setFecha(fecha);
                n.setCantidad_min(rs.getDouble("cantidad_min"));
                n.setCantidad_max(rs.getDouble("cantidad_max"));
                
                entities.Nutriente nutri = new entities.Nutriente();
                nutri.setId_nutriente(rs.getInt("id_nutriente"));
                nutri.setNombre(rs.getString("nombre_nutriente"));
                n.setNutriente(nutri);
                
                entities.Usuario prof = new entities.Usuario();
                prof.setIdUsuario(rs.getInt("id_profesional"));
                prof.setNombre(rs.getString("prof_nombre"));
                prof.setApellido(rs.getString("prof_apellido"));
                n.setProfesional(prof);

                if (!historial.containsKey(fecha)) {
                    historial.put(fecha, new LinkedList<Necesidad>());
                }
                historial.get(fecha).add(n);
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
        return historial;
    }
}
