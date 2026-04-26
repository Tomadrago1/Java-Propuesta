package data;

import entities.*;
import java.sql.*;
import java.util.LinkedList;

public class DaoProfesional {

	public LinkedList<Profesional> getAll() {
		Statement stmt = null;
		ResultSet rs = null;
		LinkedList<Profesional> profesionales = new LinkedList<>();

		try {
			stmt = DbConnector.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery(
					"select u.id,u.nombre,u.apellido,u.estado,u.nombre_usuario,u.email,u.tipo_usu,u.id_profesion,p.nombre as profesion from Usuario u left join profesion p on u.id_profesion = p.id where u.tipo_usu=2");
			if (rs != null) {
				while (rs.next()) {
					Profesional p = new Profesional();
					p.setIdUsuario(rs.getInt("id"));
					p.setNombre(rs.getString("nombre"));
					p.setApellido(rs.getString("apellido"));
					p.setProfesion(rs.getString("profesion"));
					p.setNombreUsuario(rs.getString("nombre_usuario"));
					p.setEstado(rs.getBoolean("estado"));
					p.setEmail(rs.getString("email"));
					p.setTipoUsu(rs.getInt("tipo_usu"));
					p.setId_profesion(rs.getInt("id_profesion"));
					profesionales.add(p);
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

		return profesionales;
	}

	public Profesional getByProfesional(Profesional prof) {
		Profesional p = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"select u.id,u.nombre,u.apellido,u.nombre_usuario,u.estado,u.tipo_usu,u.id_profesion,p.nombre as profesion from Usuario u left join profesion p on u.id_profesion = p.id where u.nombre_usuario=? and u.contrasena=? and u.tipo_usu=2");
			stmt.setString(1, prof.getNombreUsuario());
			stmt.setString(2, prof.getPassword());
			rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
				p = new Profesional();
				p.setIdUsuario(rs.getInt("id"));
				p.setNombre(rs.getString("nombre"));
				p.setApellido(rs.getString("apellido"));
				p.setProfesion(rs.getString("profesion"));
				p.setNombreUsuario(rs.getString("nombre_usuario"));
				p.setEstado(rs.getBoolean("estado"));
				p.setNombreUsuario(rs.getString("nombre_usuario"));
				p.setTipoUsu(rs.getInt("tipo_usu"));
				p.setId_profesion(rs.getInt("id_profesion"));
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
		return p;
	}

	public Profesional getProfesionalById(int id) {
		Profesional p = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"select u.id,u.nombre,u.apellido,u.estado,u.nombre_usuario,u.tipo_usu,u.email,u.id_profesion,p.nombre as profesion from Usuario u left join profesion p on u.id_profesion = p.id where u.id=?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
				p = new Profesional();
				p.setIdUsuario(rs.getInt("id"));
				p.setNombre(rs.getString("nombre"));
				p.setApellido(rs.getString("apellido"));
				p.setProfesion(rs.getString("profesion"));
				p.setEmail(rs.getString("email"));
				p.setNombreUsuario(rs.getString("nombre_usuario"));
				p.setEstado(rs.getBoolean("estado"));
				p.setNombreUsuario(rs.getString("nombre_usuario"));
				p.setTipoUsu(rs.getInt("tipo_usu"));
				p.setId_profesion(rs.getInt("id_profesion"));
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
		return p;
	}

	public boolean modificarProfesional(int id, String nombre, String apellido,
			int id_profesion, String nombreUsuario, String password, String email) {
		PreparedStatement stmt = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"UPDATE usuario SET nombre = ?, apellido = ?, id_profesion = ?, email = ?, contrasena = ?, nombre_usuario= ? WHERE id = ?");
			stmt.setString(1, nombre);
			stmt.setString(2, apellido);
			if (id_profesion > 0) {
			    stmt.setInt(3, id_profesion);
			} else {
			    stmt.setNull(3, java.sql.Types.INTEGER);
			}
			stmt.setString(4, email);
			stmt.setString(5, password);
			stmt.setString(6, nombreUsuario);
			stmt.setInt(7, id);

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

	public void addProfesional(Profesional p) {
		PreparedStatement stmt = null;
		ResultSet keyResultSet = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"insert into Usuario(nombre, apellido, id_profesion, estado, nombre_usuario, contrasena, tipo_usu, email) values(?,?,?,?,?,?,?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, p.getNombre());
			stmt.setString(2, p.getApellido());
			if (p.getId_profesion() > 0) {
			    stmt.setInt(3, p.getId_profesion());
			} else {
			    stmt.setNull(3, java.sql.Types.INTEGER);
			}
			stmt.setBoolean(4, p.getEstado());
			stmt.setString(5, p.getNombreUsuario());
			stmt.setString(6, p.getPassword());
			stmt.setInt(7, p.getTipoUsu());
			stmt.setString(8, p.getEmail());
			stmt.executeUpdate();

			keyResultSet = stmt.getGeneratedKeys();
			if (keyResultSet != null && keyResultSet.next()) {
				p.setIdUsuario(keyResultSet.getInt(1));
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

	public LinkedList<Profesional> getProfesionalesByProfesion(int id_profesion) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		LinkedList<Profesional> profesionales = new LinkedList<>();
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"select u.id,u.nombre,u.apellido,u.estado,u.nombre_usuario,u.email,u.tipo_usu,u.id_profesion,p.nombre as profesion from Usuario u left join profesion p on u.id_profesion = p.id where u.id_profesion=? and u.tipo_usu=2");
			stmt.setInt(1, id_profesion);
			rs = stmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Profesional p = new Profesional();
					p.setIdUsuario(rs.getInt("id"));
					p.setNombre(rs.getString("nombre"));
					p.setApellido(rs.getString("apellido"));
					p.setProfesion(rs.getString("profesion"));
					p.setNombreUsuario(rs.getString("nombre_usuario"));
					p.setEstado(rs.getBoolean("estado"));
					p.setEmail(rs.getString("email"));
					p.setTipoUsu(rs.getInt("tipo_usu"));
					p.setId_profesion(rs.getInt("id_profesion"));
					profesionales.add(p);
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
		return profesionales;
	}
}
