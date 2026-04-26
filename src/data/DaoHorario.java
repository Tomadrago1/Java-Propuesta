package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.time.LocalDate;

import entities.Horario;

public class DaoHorario {

    public LinkedList<Horario> getHorariosPorProfesionalYFecha(int idProfesional, LocalDate fecha) {
        LinkedList<Horario> horarios = new LinkedList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Buscamos horarios que solapen con el día elegido
            stmt = DbConnector.getInstancia().getConn().prepareStatement(
                "SELECT id_horario, id_profesional, fecha_hora_desde, fecha_hora_hasta " +
                "FROM horarios " +
                "WHERE id_profesional = ? AND DATE(fecha_hora_desde) = ?"
            );
            stmt.setInt(1, idProfesional);
            stmt.setDate(2, java.sql.Date.valueOf(fecha));
            rs = stmt.executeQuery();

            while (rs.next()) {
                Horario h = new Horario();
                h.setId_horario(rs.getInt("id_horario"));
                h.setId_profesional(rs.getInt("id_profesional"));
                
                // Conversión segura de Timestamp a LocalDateTime
                java.sql.Timestamp tsDesde = rs.getTimestamp("fecha_hora_desde");
                if (tsDesde != null) h.setFecha_hora_desde(tsDesde.toLocalDateTime());
                
                java.sql.Timestamp tsHasta = rs.getTimestamp("fecha_hora_hasta");
                if (tsHasta != null) h.setFecha_hora_hasta(tsHasta.toLocalDateTime());
                
                horarios.add(h);
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

        return horarios;
    }

    public boolean agregarHorario(Horario h) {
        PreparedStatement stmt = null;
        try {
            stmt = DbConnector.getInstancia().getConn().prepareStatement(
                "INSERT INTO horarios (id_profesional, fecha_hora_desde, fecha_hora_hasta) VALUES (?, ?, ?)"
            );
            stmt.setInt(1, h.getId_profesional());
            stmt.setTimestamp(2, java.sql.Timestamp.valueOf(h.getFecha_hora_desde()));
            stmt.setTimestamp(3, java.sql.Timestamp.valueOf(h.getFecha_hora_hasta()));
            
            int filas = stmt.executeUpdate();
            return filas > 0;
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
