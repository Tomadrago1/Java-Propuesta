package data;

import entities.*;
import java.sql.*;
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

	/*
	 * public Medida getOne(int id) {
	 * Medida m =new Medida();
	 * PreparedStatement stmt=null;
	 * ResultSet rs=null;
	 * try {
	 * stmt=DbConnector.getInstancia().getConn().prepareStatement(
	 * "Select * from ejercicios where id = ?"
	 * );
	 * stmt.setInt(1, id);
	 * rs=stmt.executeQuery();
	 * if(rs!=null && rs.next()) {
	 * m.setId(rs.getInt("id"));
	 * m.setNombre(rs.getString("nombre"));
	 * m.setDescripcion(rs.getString("descripcion"));
	 * }
	 * } catch (SQLException err) {
	 * err.printStackTrace();
	 * }finally {
	 * try {
	 * if(rs!=null) {rs.close();}
	 * if(stmt!=null) {stmt.close();}
	 * DbConnector.getInstancia().releaseConn();
	 * } catch (SQLException err) {
	 * err.printStackTrace();
	 * }
	 * }
	 * 
	 * return e;
	 * }
	 * 
	 * 
	 * public boolean modificarEjercicio(int id, String nombre, String descripcion)
	 * {
	 * PreparedStatement stmt = null;
	 * try {
	 * stmt = DbConnector.getInstancia().getConn().prepareStatement(
	 * "UPDATE ejercicios SET nombre = ?, descripcion = ? WHERE id = ?");
	 * stmt.setString(1, nombre);
	 * stmt.setString(2, descripcion);
	 * stmt.setInt(3, id);
	 * 
	 * int filasActualizadas = stmt.executeUpdate();
	 * return filasActualizadas > 0; // Retornar true si se actualizó al menos una
	 * fila
	 * } catch (SQLException e) {
	 * e.printStackTrace();
	 * return false; // Retornar false si ocurre un error
	 * } finally {
	 * try {
	 * if (stmt != null) { stmt.close(); }
	 * DbConnector.getInstancia().releaseConn();
	 * } catch (SQLException e) {
	 * e.printStackTrace();
	 * }
	 * }
	 * }
	 * 
	 * 
	 * public boolean eliminarEjercicio(int id) {
	 * PreparedStatement stmt = null;
	 * try {
	 * stmt = DbConnector.getInstancia().getConn().
	 * prepareStatement("DELETE FROM ejercicios WHERE id = ?");
	 * stmt.setInt(1, id);
	 * int filasBorradas = stmt.executeUpdate();
	 * return filasBorradas > 0; // Devuelve true si se eliminó al menos una fila
	 * } catch (SQLException e) {
	 * e.printStackTrace();
	 * return false; // Devuelve false si ocurrió un error
	 * } finally {
	 * try {
	 * if (stmt != null) { stmt.close(); }
	 * DbConnector.getInstancia().releaseConn();
	 * } catch (SQLException e) {
	 * e.printStackTrace();
	 * }
	 * }
	 * }
	 * 
	 * public void addEjercicio(Ejercicio e) {
	 * PreparedStatement stmt= null;
	 * ResultSet keyResultSet=null;
	 * try {
	 * stmt=DbConnector.getInstancia().getConn().
	 * prepareStatement(
	 * "INSERT INTO ejercicios (nombre, descripcion) VALUES(?, ?)",
	 * PreparedStatement.RETURN_GENERATED_KEYS
	 * );
	 * stmt.setString(1, e.getNombre());
	 * stmt.setString(2, e.getDescripcion());
	 * stmt.executeUpdate();
	 * 
	 * keyResultSet=stmt.getGeneratedKeys();
	 * if(keyResultSet!=null && keyResultSet.next()){
	 * e.setId(keyResultSet.getInt(1));
	 * ;
	 * }
	 * 
	 * } catch (SQLException err) {
	 * err.printStackTrace();
	 * } finally {
	 * try {
	 * if(keyResultSet!=null)keyResultSet.close();
	 * if(stmt!=null)stmt.close();
	 * DbConnector.getInstancia().releaseConn();
	 * } catch (SQLException err) {
	 * err.printStackTrace();
	 * }
	 * }
	 * }
	 */
}
