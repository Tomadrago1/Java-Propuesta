package data;

import entities.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;

public class DaoMedida {

	public LinkedList<Medida> getByUsuario(int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		LinkedList<Medida> medidas = new LinkedList<>();

		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"Select * from historial_medida where id_usuario = ? order by fecha desc");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Medida m = new Medida();
					m.setId_usuario(rs.getInt("id_usuario"));
					java.sql.Date sqlDate = rs.getDate("fecha");
					if (sqlDate != null) {
						m.setFecha(sqlDate.toLocalDate());
					}
					m.setPeso(rs.getDouble("peso"));
					m.setAltura(rs.getDouble("altura"));
					medidas.add(m);
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

		return medidas;
	}

	public void addMedida(Medida m) {
		PreparedStatement stmt = null;

		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"INSERT INTO historial_medida (id_usuario, fecha, peso, altura) VALUES(?, ?, ?, ?)");
			stmt.setInt(1, m.getId_usuario());
			stmt.setDate(2, Date.valueOf(m.getFecha()));
			stmt.setDouble(3, m.getPeso());
			stmt.setDouble(4, m.getAltura());
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public Medida getByUsuarioFecha(int id, LocalDate fecha) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Medida m = null; // Inicializa m como null
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"SELECT * FROM historial_medida WHERE id_usuario = ? AND fecha = ?");
			stmt.setInt(1, id);
			stmt.setDate(2, Date.valueOf(fecha));
			rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
				m = new Medida(); // Crea la instancia de Medida solo si se encuentra un resultado
				m.setId_usuario(rs.getInt("id_usuario"));
				java.sql.Date sqlDate = rs.getDate("fecha");
				if (sqlDate != null) {
					m.setFecha(sqlDate.toLocalDate());
				}
				m.setPeso(rs.getDouble("peso"));
				m.setAltura(rs.getDouble("altura"));
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
		return m;
	}

	public void modificarMedida(Medida m) {
		PreparedStatement stmt = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"UPDATE historial_medida SET peso = ?, altura = ? WHERE id_usuario = ? AND fecha = ?");
			stmt.setDouble(1, m.getPeso());
			stmt.setDouble(2, m.getAltura());
			stmt.setInt(3, m.getId_usuario());
			stmt.setDate(4, Date.valueOf(m.getFecha()));
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

	public void delete(int id, LocalDate fecha) {
		PreparedStatement stmt = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"DELETE FROM historial_medida WHERE id_usuario = ? AND fecha = ?");
			stmt.setInt(1, id);
			stmt.setDate(2, Date.valueOf(fecha));
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
