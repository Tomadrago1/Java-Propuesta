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
					"select id,nombre,apellido,profesion,estado,nombre_usuario,email,tipo_usu from Usuario where tipo_usu=2");
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
					"select id_profesional,nombre,apellido,nombre_usuario,estado,tipo_usu,profesion from Profesional where nombre_usuario=? and contrasena=?");
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
					"select id,nombre,apellido,profesion,estado,nombre_usuario,profesion,tipo_usu,email from Usuario where id=?");
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
			String profesion, String nombreUsuario, String password, String email) {
		PreparedStatement stmt = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"UPDATE usuario SET nombre = ?, apellido = ?, profesion = ?, email = ?, contrasena = ?, nombre_usuario= ? WHERE id = ?");
			stmt.setString(1, nombre);
			stmt.setString(2, apellido);
			stmt.setString(3, profesion);
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
					"insert into Usuario(nombre, apellido, profesion, estado, nombre_usuario, contrasena, tipo_usu, email) values(?,?,?,?,?,?,?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, p.getNombre());
			stmt.setString(2, p.getApellido());
			stmt.setString(3, p.getProfesion());
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

	public LinkedList<Profesional> getProfesionalesByProfesion(String profesion) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		LinkedList<Profesional> profesionales = new LinkedList<>();
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"select id,nombre,apellido,profesion,estado,nombre_usuario,email,tipo_usu from Usuario where profesion=?");
			stmt.setString(1, profesion);
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
