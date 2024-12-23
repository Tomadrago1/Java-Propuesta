package data;

import entities.*;
import java.sql.*;
import java.util.LinkedList;

public class DaoReceta {

	public LinkedList<Receta> getAll() {
		Statement stmt = null;
		ResultSet rs = null;
		LinkedList<Receta> recetas = new LinkedList<>();

		try {
			stmt = DbConnector.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery(
					"select r.id,r.nombre,r.descripcion,r.nivel_dificultad,u.nombre,u.apellido,u.id,u.profesion,u.tipo_usu from Receta r left join usuario u on u.id=r.id_profesional");
			if (rs != null) {
				while (rs.next()) {
					Receta r = new Receta();
					Profesional p = new Profesional();
					r.setId(rs.getInt("r.id"));
					r.setNombre(rs.getString("r.nombre"));
					r.setDesc(rs.getString("r.descripcion"));
					r.setNivelDificultad(rs.getString("r.nivel_dificultad"));
					if (rs.getInt("u.tipo_usu") == 2) {
						p.setNombre(rs.getString("u.nombre"));
						p.setIdUsuario(rs.getInt("u.id"));
						p.setApellido(rs.getString("u.apellido"));
						p.setProfesion(rs.getString("u.profesion"));
					} else {
						p = null;
					}
					r.setProfesional(p);
					recetas.add(r);
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
		return recetas;
	}

	public Receta getRecetaById(int id) {
		Receta r = null; // Inicializar Receta
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"select r.id, r.nombre, r.descripcion, r.nivel_dificultad, u.nombre, u.apellido, u.id, u.profesion " +
							"from Receta r left join Usuario u on r.id_profesional = u.id where r.id = ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
				r = new Receta(); // Inicializar Receta
				Profesional p = new Profesional();
				r.setId(rs.getInt("r.id"));
				r.setNombre(rs.getString("r.nombre"));
				r.setDesc(rs.getString("r.descripcion"));
				r.setNivelDificultad(rs.getString("r.nivel_dificultad"));

				// Verificar si el id del profesional es válido
				if (rs.getObject("u.id") != null) {
					p.setNombre(rs.getString("u.nombre"));
					p.setIdUsuario(rs.getInt("u.id"));
					p.setApellido(rs.getString("u.apellido"));
					p.setProfesion(rs.getString("u.profesion"));
					r.setProfesional(p);
				} else {
					r.setProfesional(null); // Explicitamente asignar null si no hay profesional
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
		return r;
	}

	public LinkedList<IngredienteReceta> getIngredientesReceta(int idReceta) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		LinkedList<IngredienteReceta> ingredientes = new LinkedList<>();
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"select i.id,i.nombre,i.descripcion,ir.cantidad_porcion,ir.unidad_medida,r.nombre,r.descripcion,r.id from Receta r inner join ingrediente_receta ir on r.id=ir.id_receta inner join ingrediente i on ir.id_ingrediente=i.id where r.id=?");
			stmt.setInt(1, idReceta);
			rs = stmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Receta receta = new Receta();
					Ingrediente ingrediente = new Ingrediente();
					IngredienteReceta ir = new IngredienteReceta();
					ingrediente.setId(rs.getInt("i.id"));
					ingrediente.setNombre(rs.getString("i.nombre"));
					ingrediente.setDesc(rs.getString("i.descripcion"));
					ir.setIngrediente(ingrediente);
					receta.setId(rs.getInt("r.id"));
					receta.setNombre(rs.getString("r.nombre"));
					receta.setDesc(rs.getString("r.descripcion"));
					ir.setReceta(receta);
					ir.setCantidad(rs.getDouble("ir.cantidad_porcion"));
					ir.setUnidad_medida(rs.getString("ir.unidad_medida"));
					ingredientes.add(ir);
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

	public boolean modificarReceta(int id, String nombre, String descripcion, String nivelDificultad) {
		PreparedStatement stmt = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"UPDATE receta SET nombre = ?, descripcion = ?, nivel_dificultad = ? WHERE id = ?");
			stmt.setString(1, nombre);
			stmt.setString(2, descripcion);
			stmt.setString(3, nivelDificultad);
			stmt.setInt(4, id);

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

	public boolean eliminarReceta(int idReceta) {
		boolean result = false;

		try (Connection conn = DbConnector.getInstancia().getConn()) {
			conn.setAutoCommit(false); // Iniciar la transacción

			try (PreparedStatement stmt1 = conn.prepareStatement("DELETE FROM ingrediente_receta WHERE id_receta = ?");
					PreparedStatement stmt2 = conn.prepareStatement("DELETE FROM Receta WHERE id = ?")) {

				// Elimina las relaciones en las tablas intermedias
				stmt1.setInt(1, idReceta);
				stmt1.executeUpdate();

				// Elimina la receta en la tabla principal
				stmt2.setInt(1, idReceta);
				stmt2.executeUpdate();

				// Confirma la transacción
				conn.commit();
				result = true;

			} catch (SQLException e) {
				e.printStackTrace();
				conn.rollback(); // Revertir en caso de error
			} finally {
				conn.setAutoCommit(true); // Restablecer el modo de autocommit
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public void addReceta(Receta r) {
		PreparedStatement stmt = null;
		ResultSet keyResultSet = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"insert into Receta(nombre, descripcion, nivel_dificultad, id_profesional) values(?,?,?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, r.getNombre());
			stmt.setString(2, r.getDesc());
			stmt.setString(3, r.getNivelDificultad());
			stmt.setObject(4, r.getProfesional().getIdUsuario());
			stmt.executeUpdate();

			keyResultSet = stmt.getGeneratedKeys();
			if (keyResultSet != null && keyResultSet.next()) {
				r.setId(keyResultSet.getInt(1));
				;
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
	}

	public boolean addIngredienteReceta(int idReceta, int idIngrediente, double cantidad, String unidadMedida) {
		PreparedStatement stmt = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"INSERT INTO ingrediente_receta (id_receta, id_ingrediente, cantidad_porcion, unidad_medida) VALUES (?, ?, ?,?)");

			stmt.setInt(1, idReceta);
			stmt.setInt(2, idIngrediente);
			stmt.setDouble(3, cantidad);
			stmt.setString(4, unidadMedida);
			int rowsAffected = stmt.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
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

	public boolean modificarCantidadIngredienteReceta(int idReceta, int idIngrediente, double cantidad,
			String unidadMedida) {
		PreparedStatement stmt = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"UPDATE ingrediente_receta SET cantidad_porcion = ?, unidad_medida= ? WHERE id_ingrediente = ? and id_receta = ?");

			stmt.setDouble(1, cantidad);
			stmt.setString(2, unidadMedida);
			stmt.setInt(3, idIngrediente);
			stmt.setInt(4, idReceta);
			int rowsAffected = stmt.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
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

	public boolean borrarIngredienteReceta(int idReceta, int idIngrediente) {
		PreparedStatement stmt = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"DELETE FROM ingrediente_receta WHERE id_receta = ? and id_ingrediente = ?");

			stmt.setInt(1, idReceta);
			stmt.setInt(2, idIngrediente);
			int rowsAffected = stmt.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
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

	public LinkedList<NutrienteReceta> getNutrientesReceta(int idReceta) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		LinkedList<NutrienteReceta> nutrientes = new LinkedList<>();
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"SELECT n.id AS id_nutriente, n.nombre AS nombre_nutriente, n.descripcion, " +
							"SUM(ni.cantidad * CASE WHEN ir.unidad_medida = 'kg' THEN (ir.cantidad_porcion * 1000 / 100) " +
							"WHEN ir.unidad_medida = 'gramos' THEN (ir.cantidad_porcion / 100) ELSE 0 END) AS cantidad_total_nutriente "
							+
							"FROM INGREDIENTE_RECETA ir " +
							"JOIN NUTRIENTE_INGREDIENTE ni ON ir.id_ingrediente = ni.id_ingrediente " +
							"JOIN NUTRIENTE n ON ni.id_nutriente = n.id " +
							"WHERE ir.id_receta = ? " +
							"GROUP BY n.id, n.nombre, n.descripcion;");
			stmt.setInt(1, idReceta);
			rs = stmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Nutriente nutriente = new Nutriente();
					Receta receta = new Receta();
					NutrienteReceta nr = new NutrienteReceta();
					nutriente.setId_nutriente(rs.getInt("id_nutriente"));
					nutriente.setNombre(rs.getString("nombre_nutriente"));
					nutriente.setDescripcion(rs.getString("descripcion"));
					nr.setNutriente(nutriente);
					receta.setId(idReceta);
					nr.setReceta(receta);
					nr.setCantidad(rs.getDouble("cantidad_total_nutriente"));
					nutrientes.add(nr);
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

	public LinkedList<Receta> getRecetasRecomendadas(int idUsuario) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		LinkedList<Receta> recetas = new LinkedList<>();
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"SELECT rec.id, rec.nombre, rec.descripcion, rec.nivel_dificultad, u.nombre, u.id, u.apellido, u.profesion FROM receta rec LEFT JOIN usuario u ON rec.id_profesional = u.id WHERE EXISTS( SELECT 1 FROM necesidad ne INNER JOIN nutriente n ON ne.id_nutriente = n.id INNER JOIN ingrediente_receta ir ON rec.id = ir.id_receta INNER JOIN nutriente_ingrediente ni ON ni.id_ingrediente = ir.id_ingrediente WHERE ne.id_usuario = ? AND ne.fecha = CURDATE() AND n.id = ni.id_nutriente GROUP BY rec.id , n.id HAVING SUM(ni.cantidad * CASE WHEN ir.unidad_medida = 'kg' THEN (ir.cantidad_porcion * 1000 / 100) WHEN ir.unidad_medida = 'gramos' THEN (ir.cantidad_porcion / 100) ELSE 0 END) BETWEEN MAX(ne.cantidad_min) AND MAX(ne.cantidad_max)) GROUP BY rec.id , rec.nombre , rec.descripcion , rec.nivel_dificultad , u.nombre , u.id , u.apellido , u.profesion;");
			stmt.setInt(1, idUsuario);
			rs = stmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Receta r = new Receta();
					Profesional p = new Profesional();
					r.setId(rs.getInt("rec.id"));
					r.setNombre(rs.getString("rec.nombre"));
					r.setDesc(rs.getString("rec.descripcion"));
					r.setNivelDificultad(rs.getString("rec.nivel_dificultad"));
					if (rs.getObject("u.id") != null) {
						p.setNombre(rs.getString("u.nombre"));
						p.setIdUsuario(rs.getInt("u.id"));
						p.setApellido(rs.getString("u.apellido"));
						p.setProfesion(rs.getString("u.profesion"));
					} else {
						p = null;
					}
					r.setProfesional(p);
					recetas.add(r);
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
		return recetas;
	}

	public LinkedList<Receta> getByProf(int idProfesional) {
		LinkedList<Receta> recetas = new LinkedList<>();
		String sql = "SELECT r.id, r.nombre, r.descripcion, r.nivel_dificultad, " +
				"u.nombre AS nombre_prof, u.apellido, u.id AS id_prof, u.profesion " +
				"FROM Receta r " +
				"LEFT JOIN usuario u ON u.id = r.id_profesional " +
				"WHERE r.id_profesional = ?";

		try (PreparedStatement stmt = DbConnector.getInstancia().getConn().prepareStatement(sql)) {
			stmt.setInt(1, idProfesional);

			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					Receta receta = new Receta();
					Profesional profesional = new Profesional();

					receta.setId(rs.getInt("r.id"));
					receta.setNombre(rs.getString("r.nombre"));
					receta.setDesc(rs.getString("r.descripcion"));
					receta.setNivelDificultad(rs.getString("r.nivel_dificultad"));

					profesional.setNombre(rs.getString("nombre_prof"));
					profesional.setIdUsuario(rs.getInt("id_prof"));
					profesional.setApellido(rs.getString("u.apellido"));
					profesional.setProfesion(rs.getString("u.profesion"));

					receta.setProfesional(profesional);
					recetas.add(receta);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbConnector.getInstancia().releaseConn();
		}
		return recetas;
	}
}
