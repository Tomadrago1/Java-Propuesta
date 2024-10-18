package data;

import entities.*;

import java.sql.*;
import java.util.LinkedList;

public class DaoRutina {

	public LinkedList<Rutina> getAll() {
		Statement stmt = null;
		ResultSet rs = null;
		LinkedList<Rutina> rutinas = new LinkedList<>();

		try {
			stmt = DbConnector.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery("select * from Rutina");

			if (rs != null) {
				while (rs.next()) {
					Rutina u = new Rutina();
					u.setId(rs.getInt("id"));
					u.setNombre(rs.getString("nombre"));
					u.setDescripcion(rs.getString("descripcion"));
					rutinas.add(u);
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

		return rutinas;
	}

	public boolean eliminarRutina(int id_rut) {
		PreparedStatement stmt = null;
		Connection conn = null;
		try {
			conn = DbConnector.getInstancia().getConn();
			conn.setAutoCommit(false); // Inicia la transacción

			// Elimina las referencias en rutina_ejercicio
			stmt = conn.prepareStatement("DELETE FROM rutina_ejercicio WHERE id_rutina = ?");
			stmt.setInt(1, id_rut);
			stmt.executeUpdate();

			// Elimina la rutina en la tabla principal
			stmt = conn.prepareStatement("DELETE FROM rutina WHERE id = ?");
			stmt.setInt(1, id_rut);
			int filasAfectadas = stmt.executeUpdate();

			conn.commit(); // Confirma la transacción
			return filasAfectadas > 0; // Retorna true si se eliminó la rutina

		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback(); // Revertir la transacción en caso de error
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			e.printStackTrace();
			return false; // Retorna false si ocurre un error
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.setAutoCommit(true);
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public Rutina getOne(int id) {
		Rutina r = new Rutina();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"Select * from rutina where id = ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
				r.setId(rs.getInt("id"));
				r.setNombre(rs.getString("nombre"));
				r.setDescripcion(rs.getString("descripcion"));
			}
		} catch (SQLException err) {
			err.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException err) {
				err.printStackTrace();
			}
		}

		return r;

	}

	public boolean modificarRutina(int id, String nombre, String desc) {
		PreparedStatement stmt = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"UPDATE rutina SET nombre = ?, descripcion = ? WHERE id = ?");
			stmt.setString(1, nombre);
			stmt.setString(2, desc);
			stmt.setInt(3, id);

			int filasActualizadas = stmt.executeUpdate();
			return filasActualizadas > 0; // Retornar true si se actualizó al menos una fila
		} catch (SQLException e) {
			e.printStackTrace();
			return false; // Retornar false si ocurre un error
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

	public void addRutina(Rutina r) {
		PreparedStatement stmt = null;
		ResultSet keyResultSet = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"INSERT INTO rutina (nombre, descripcion) VALUES(?, ?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, r.getNombre());
			stmt.setString(2, r.getDescripcion());
			stmt.executeUpdate();

			keyResultSet = stmt.getGeneratedKeys();
			if (keyResultSet != null && keyResultSet.next()) {
				r.setId(keyResultSet.getInt(1));
				;
			}

		} catch (SQLException err) {
			err.printStackTrace();
		} finally {
			try {
				if (keyResultSet != null)
					keyResultSet.close();
				if (stmt != null)
					stmt.close();
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException err) {
				err.printStackTrace();
			}
		}
	}

	public LinkedList<EjercicioRutina> getEjerciciosByRutina(int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		LinkedList<EjercicioRutina> ejercicios = new LinkedList<>();
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"SELECT eje.id, eje.nombre, eje.descripcion, re.series_aproximadas, re.repeticiones_aproximadas, re.tiempo_aproximado FROM rutina_ejercicio AS re INNER JOIN ejercicio AS eje ON re.id_ejercicio = eje.id WHERE id_rutina = ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			while (rs != null && rs.next()) {
				Ejercicio eje = new Ejercicio();
				eje.setId(rs.getInt("eje.id"));
				eje.setNombre(rs.getString("eje.nombre"));
				eje.setDescripcion(rs.getString("eje.descripcion"));

				EjercicioRutina ejeRut = new EjercicioRutina();
				ejeRut.setEjercicio(eje);
				ejeRut.setSeriesAproximadas(rs.getInt("re.series_aproximadas"));
				ejeRut.setRepesAproximadas(rs.getInt("re.repeticiones_aproximadas"));
				ejeRut.setTiempo(rs.getString("re.tiempo_aproximado"));

				Rutina r = this.getOne(id);
				ejeRut.setRutina(r);

				ejercicios.add(ejeRut);
			}
		} catch (SQLException err) {
			err.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (rs != null) {
					rs.close();
				}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException err) {
				err.printStackTrace();
			}
		}
		return ejercicios;
	}

	public boolean modificarEjercicioRutina(int id_rut, int id_eje, int series, Integer repes, String tiempo) {
		PreparedStatement stmt = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"UPDATE rutina_ejercicio SET repeticiones_aproximadas = ?, series_aproximadas = ?, tiempo_aproximado = ? WHERE (id_rutina = ? AND id_ejercicio = ?)");
			if (repes == null) {
				stmt.setNull(1, Types.INTEGER);
			} else {
				stmt.setInt(1, repes);
			}
			stmt.setInt(2, series);
			if (tiempo == null) {
				stmt.setNull(3, Types.VARCHAR);
			} else {
				stmt.setString(3, tiempo);
			}
			stmt.setInt(4, id_rut);
			stmt.setInt(5, id_eje);

			int filasActualizadas = stmt.executeUpdate();
			return filasActualizadas > 0; // Retornar true si se actualizó al menos una fila
		} catch (SQLException e) {
			e.printStackTrace();
			return false; // Retornar false si ocurre un error
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

	public boolean agregarEjercicioRutina(int id_rut, int id_eje, int series, Integer repes, String tiempo) {
		PreparedStatement stmt = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"INSERT INTO rutina_ejercicio (id_rutina, id_ejercicio, repeticiones_aproximadas, series_aproximadas, tiempo_aproximado) VALUES (?, ?, ?, ?, ?);");
			stmt.setInt(1, id_rut);
			stmt.setInt(2, id_eje);
			if (repes == null) {
				stmt.setNull(3, Types.INTEGER);
			} else {
				stmt.setInt(3, repes);
			}
			stmt.setInt(4, series);
			if (tiempo == null) {
				stmt.setNull(5, Types.VARCHAR);
			} else {
				stmt.setString(5, tiempo);
			}

			int filasActualizadas = stmt.executeUpdate();
			return filasActualizadas > 0; // Retornar true si se actualizó al menos una fila
		} catch (SQLException e) {
			e.printStackTrace();
			return false; // Retornar false si ocurre un error
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

	public boolean quitarEjercicioRutina(int id_rut, int id_eje) {
		PreparedStatement stmt = null;
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement("DELETE FROM rutina_ejercicio WHERE (id_rutina = ? AND id_ejercicio = ?)");
			stmt.setInt(1, id_rut);
			stmt.setInt(2, id_eje);
			int filasBorradas = stmt.executeUpdate();
			return filasBorradas > 0; // Devuelve true si se eliminó al menos una fila
		} catch (SQLException e) {
			e.printStackTrace();
			return false; // Devuelve false si ocurrió un error
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

	public LinkedList<Rutina> getRutinaByUsuario(int id_usuario) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		LinkedList<Rutina> rutinas = new LinkedList<>();

		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"SELECT r.id, r.nombre, r.descripcion FROM rutina AS r INNER JOIN rutina_cliente AS ur ON r.id = ur.id_rutina WHERE ur.id_usuario = ?");
			stmt.setInt(1, id_usuario);
			rs = stmt.executeQuery();

			while (rs != null && rs.next()) {
				Rutina r = new Rutina();
				r.setId(rs.getInt("r.id"));
				r.setNombre(rs.getString("r.nombre"));
				r.setDescripcion(rs.getString("r.descripcion"));
				rutinas.add(r);
			}
		} catch (SQLException err) {
			err.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (rs != null) {
					rs.close();
				}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException err) {
				err.printStackTrace();
			}
		}
		return rutinas;
	}

	public boolean borrarRutinaUsuario(int id_usu, int id_rut) {
		PreparedStatement stmt = null;
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement("DELETE FROM rutina_cliente WHERE (id_usuario = ? AND id_rutina = ?)");
			stmt.setInt(1, id_usu);
			stmt.setInt(2, id_rut);
			int filasBorradas = stmt.executeUpdate();
			return filasBorradas > 0; // Devuelve true si se eliminó al menos una fila
		} catch (SQLException e) {
			e.printStackTrace();
			return false; // Devuelve false si ocurrió un error
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

	public void agregarRutinaUsuario(int id_rut, int id_usu) {
		PreparedStatement stmt = null;
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement("INSERT INTO rutina_cliente (id_rutina, id_usuario) VALUES (?, ?)");
			stmt.setInt(1, id_rut);
			stmt.setInt(2, id_usu);
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

	public int getSeriesAprox(int id_eje, int id_rut) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int series = 0;

		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"SELECT series_aproximadas AS series FROM rutina_ejercicio WHERE id_ejercicio = ? AND id_rutina = ?");
			stmt.setInt(1, id_eje);
			stmt.setInt(2, id_rut);
			rs = stmt.executeQuery();

			if (rs != null && rs.next()) {
				series = rs.getInt("series");
			}
		} catch (SQLException err) {
			err.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (rs != null) {
					rs.close();
				}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException err) {
				err.printStackTrace();
			}
		}
		return series;
	}

	public int getRepsAprox(int id_eje, int id_rut) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int reps = 0;

		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"SELECT repeticiones_aproximadas AS repes FROM rutina_ejercicio WHERE id_ejercicio = ? AND id_rutina = ?");
			stmt.setInt(1, id_eje);
			stmt.setInt(2, id_rut);
			rs = stmt.executeQuery();

			if (rs != null && rs.next()) {
				reps = rs.getInt("repes");
			}
		} catch (SQLException err) {
			err.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (rs != null) {
					rs.close();
				}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException err) {
				err.printStackTrace();
			}
		}
		return reps;
	}
}