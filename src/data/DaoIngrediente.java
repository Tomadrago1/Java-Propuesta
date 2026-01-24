package data;

import entities.*;
import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class DaoIngrediente {

	public LinkedList<Ingrediente> getAll() {
		Statement stmt = null;
		ResultSet rs = null;
		LinkedList<Ingrediente> ingredientes = new LinkedList<>();

		try {
			stmt = DbConnector.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery("select * from ingrediente");
			if (rs != null) {
				while (rs.next()) {
					Ingrediente i = new Ingrediente();
					i.setId(rs.getInt("id"));
					i.setNombre(rs.getString("nombre"));
					i.setDesc(rs.getString("descripcion"));
					ingredientes.add(i);
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
		return ingredientes;
	}

	public Ingrediente getIngredienteById(int id) {
		Ingrediente i = null; // Inicializar Receta
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"select * from ingrediente where id = ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
				i = new Ingrediente();
				i.setId(rs.getInt("id"));
				i.setNombre(rs.getString("nombre"));
				i.setDesc(rs.getString("descripcion"));
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
		return i;
	}

	public LinkedList<Map<String, Object>> getIngredientesReceta(int idReceta) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		LinkedList<Map<String, Object>> ingredientes = new LinkedList<>();
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"select i.id,i.nombre,i.descripcion,ir.cantidad_porcion from Receta r inner join ingrediente_receta ir on r.id=ir.id_receta inner join ingrediente i on ir.id_ingrediente=i.id where r.id=?");
			stmt.setInt(1, idReceta);
			rs = stmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Map<String, Object> ingrediente = new HashMap<>();
					ingrediente.put("id", rs.getInt("i.id"));
					ingrediente.put("nombre", rs.getString("i.nombre"));
					ingrediente.put("descripcion", rs.getString("i.descripcion"));
					ingrediente.put("cantidad", rs.getDouble("ir.cantidad_porcion"));
					ingredientes.add(ingrediente);
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
		return ingredientes;
	}

	public boolean addIngrediente(Ingrediente i) {
		PreparedStatement stmt = null;
		ResultSet keyResultSet = null;
		boolean añadido = false;

		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"insert into Ingrediente(nombre, descripcion) values(?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, i.getNombre());
			stmt.setString(2, i.getDesc());
			int rowsAffected = stmt.executeUpdate();

			if (rowsAffected > 0) {
				keyResultSet = stmt.getGeneratedKeys();
				if (keyResultSet != null && keyResultSet.next()) {
					i.setId(keyResultSet.getInt(1));
					añadido = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
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

		return añadido;
	}

	public boolean eliminarIngrediente(int idIngrediente) {
		PreparedStatement stmt1 = null;
		PreparedStatement stmt2 = null;
		Connection conn = null;
		boolean result = false;

		try {
			conn = DbConnector.getInstancia().getConn();
			conn.setAutoCommit(false); // Iniciar la transacción

			// Elimina las relaciones en la tabla intermedia
			stmt1 = conn.prepareStatement("DELETE FROM ingrediente_receta WHERE id_ingrediente = ?");
			stmt1.setInt(1, idIngrediente);
			stmt1.executeUpdate();

			// Elimina el ingrediente en la tabla principal
			stmt2 = conn.prepareStatement("DELETE FROM Ingrediente WHERE id = ?");
			stmt2.setInt(1, idIngrediente);
			stmt2.executeUpdate();

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
				if (conn != null)
					conn.setAutoCommit(true); // Restablecer el modo de autocommit
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public boolean modificarIngrediente(int id, String nombre, String descripcion) {
		PreparedStatement stmt = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"UPDATE ingrediente SET nombre = ?, descripcion = ? WHERE id = ?");
			stmt.setString(1, nombre);
			stmt.setString(2, descripcion);
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

	public LinkedList<NutrienteIngrediente> getNutrientesIngrediente(int idIngrediente) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		// cambiar LinkedList<Map> Por una clase nueva que tenga de atributos las 2
		// clases y atributos extras
		LinkedList<NutrienteIngrediente> nutrientes = new LinkedList<>();
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"SELECT \n" + //
							"    ni.cantidad,ni.unidad_medida, ni.id_nutriente, n.nombre AS nombre_nutriente, n.descripcion AS descripcion_nutriente, ni.id_ingrediente, i.nombre AS nombre_ingrediente, i.descripcion AS descripcion_ingrediente\n"
							+ //
							"FROM\n" + //
							"    nutriente_ingrediente ni\n" + //
							"        INNER JOIN\n" + //
							"    nutriente n ON ni.id_nutriente = n.id\n" + //
							"\t\tINNER JOIN\n" + //
							"\tingrediente i ON i.id = ni.id_ingrediente where ni.id_ingrediente = ?");
			stmt.setInt(1, idIngrediente);
			rs = stmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					NutrienteIngrediente ni = new NutrienteIngrediente();

					Nutriente n = new Nutriente();
					n.setId_nutriente(rs.getInt("id_nutriente"));
					n.setNombre(rs.getString("nombre_nutriente"));
					n.setDescripcion(rs.getString("descripcion_nutriente"));
					ni.setNutriente(n);

					Ingrediente i = new Ingrediente();
					i.setId(rs.getInt("id_ingrediente"));
					i.setNombre(rs.getString("nombre_ingrediente"));
					i.setDesc(rs.getString("descripcion_ingrediente"));
					ni.setIngrediente(i);

					ni.setCantidad(rs.getDouble("cantidad"));
					ni.setUnidad_medida(rs.getString("unidad_medida"));
					nutrientes.add(ni);
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

	public boolean modificarCantidadNutrienteIngrediente(int idNutriente, int idIngrediente, double cantidad) {
		PreparedStatement stmt = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"UPDATE nutriente_ingrediente SET cantidad = ? WHERE id_nutriente = ? AND id_ingrediente = ?");
			stmt.setDouble(1, cantidad);
			stmt.setInt(2, idNutriente);
			stmt.setInt(3, idIngrediente);

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

	public boolean addNutrienteIngrediente(int idNutriente, int idIngrediente, double cantidad) {
		PreparedStatement stmt = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"INSERT INTO nutriente_ingrediente (id_nutriente, id_ingrediente, cantidad) VALUES (?, ?, ?)");
			stmt.setInt(1, idNutriente);
			stmt.setInt(2, idIngrediente);
			stmt.setDouble(3, cantidad);

			int filasAfectadas = stmt.executeUpdate();
			return filasAfectadas > 0; // Retornar true si se insertó al menos una fila
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

	public boolean borrarNutrienteIngrediente(int idNutriente, int idIngrediente) {
		PreparedStatement stmt = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"DELETE FROM nutriente_ingrediente WHERE id_nutriente = ? AND id_ingrediente = ?");
			stmt.setInt(1, idNutriente);
			stmt.setInt(2, idIngrediente);

			int filasAfectadas = stmt.executeUpdate();
			return filasAfectadas > 0; // Retornar true si se eliminó al menos una fila
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
}
