package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.time.LocalDate;

import entities.Consulta;
import entities.Usuario;

public class DaoConsulta {

    public LinkedList<Consulta> getConsultasPorProfesionalYFecha(int idProfesional, LocalDate fecha) {
        LinkedList<Consulta> consultas = new LinkedList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = DbConnector.getInstancia().getConn().prepareStatement(
                "SELECT c.id_cliente, c.id_profesional, c.fecha_consulta, c.desc_resultados, c.estado, " +
                "u.nombre, u.apellido " +
                "FROM consulta c " +
                "LEFT JOIN usuario u ON c.id_cliente = u.id " +
                "WHERE c.id_profesional = ? AND DATE(c.fecha_consulta) = ?"
            );
            stmt.setInt(1, idProfesional);
            stmt.setDate(2, java.sql.Date.valueOf(fecha));
            rs = stmt.executeQuery();

            while (rs.next()) {
                Consulta c = new Consulta();
                c.setId_cliente(rs.getInt("id_cliente"));
                c.setId_profesional(rs.getInt("id_profesional"));
                
                java.sql.Timestamp ts = rs.getTimestamp("fecha_consulta");
                if (ts != null) c.setFecha_consulta(ts.toLocalDateTime());
                
                c.setDesc_resultados(rs.getString("desc_resultados"));
                c.setEstado(rs.getString("estado"));
                
                Usuario cliente = new Usuario();
                cliente.setIdUsuario(rs.getInt("id_cliente"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                c.setCliente(cliente);
                
                consultas.add(c);
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

        return consultas;
    }
    
    public boolean agregarConsulta(Consulta c) {
        PreparedStatement stmt = null;
        try {
            stmt = DbConnector.getInstancia().getConn().prepareStatement(
                "INSERT INTO consulta (id_cliente, id_profesional, fecha_consulta, desc_resultados, estado) " +
                "VALUES (?, ?, ?, ?, ?)"
            );
            stmt.setInt(1, c.getId_cliente());
            stmt.setInt(2, c.getId_profesional());
            stmt.setTimestamp(3, java.sql.Timestamp.valueOf(c.getFecha_consulta()));
            stmt.setString(4, c.getDesc_resultados());
            stmt.setString(5, c.getEstado());
            
            int filasActualizadas = stmt.executeUpdate();
            return filasActualizadas > 0;
            
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

    public Consulta getConsulta(int idCliente, int idProfesional, java.time.LocalDateTime fechaConsulta) {
        Consulta c = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = DbConnector.getInstancia().getConn().prepareStatement(
                "SELECT c.id_cliente, c.id_profesional, c.fecha_consulta, c.desc_resultados, c.estado, " +
                "u.nombre, u.apellido " +
                "FROM consulta c " +
                "LEFT JOIN usuario u ON c.id_cliente = u.id " +
                "WHERE c.id_cliente = ? AND c.id_profesional = ? AND c.fecha_consulta = ?"
            );
            stmt.setInt(1, idCliente);
            stmt.setInt(2, idProfesional);
            stmt.setTimestamp(3, java.sql.Timestamp.valueOf(fechaConsulta));
            
            rs = stmt.executeQuery();

            if (rs.next()) {
                c = new Consulta();
                c.setId_cliente(rs.getInt("id_cliente"));
                c.setId_profesional(rs.getInt("id_profesional"));
                
                java.sql.Timestamp ts = rs.getTimestamp("fecha_consulta");
                if (ts != null) c.setFecha_consulta(ts.toLocalDateTime());
                
                c.setDesc_resultados(rs.getString("desc_resultados"));
                c.setEstado(rs.getString("estado"));
                
                Usuario cliente = new Usuario();
                cliente.setIdUsuario(rs.getInt("id_cliente"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                c.setCliente(cliente);
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

        return c;
    }

    public boolean updateResultadosConsulta(Consulta c) {
        PreparedStatement stmt = null;
        try {
            stmt = DbConnector.getInstancia().getConn().prepareStatement(
                "UPDATE consulta SET desc_resultados = ?, estado = 'Completado' " +
                "WHERE id_cliente = ? AND id_profesional = ? AND fecha_consulta = ?"
            );
            stmt.setString(1, c.getDesc_resultados());
            stmt.setInt(2, c.getId_cliente());
            stmt.setInt(3, c.getId_profesional());
            stmt.setTimestamp(4, java.sql.Timestamp.valueOf(c.getFecha_consulta()));
            
            int filasActualizadas = stmt.executeUpdate();
            return filasActualizadas > 0;
            
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
}
