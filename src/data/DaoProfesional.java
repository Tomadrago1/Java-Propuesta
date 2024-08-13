package data;
import entities.*;
import java.sql.*;
import java.util.LinkedList;


public class DaoProfesional {
	
		public LinkedList<Profesional> getAll(){
			Statement stmt=null;
			ResultSet rs=null;
			LinkedList<Profesional> profesionales= new LinkedList<>();
			
			try {
				stmt= DbConnector.getInstancia().getConn().createStatement();
				rs= stmt.executeQuery("select id,nombre,apellido,profesion,estado from Profesional"); /*nombre_usuario*/
				//intencionalmente no se recupera la password
				if(rs!=null) {
					while(rs.next()) {
						Profesional p=new Profesional();
						p.setIdProfesional(rs.getInt("id"));
						p.setNombre(rs.getString("nombre"));
						p.setApellido(rs.getString("apellido"));
						p.setProfesion(rs.getString("profesion"));
						/*p.setNombreUsuario(rs.getString("nombre_usuario"));*/
						p.setEstado(rs.getBoolean("estado"));
						profesionales.add(p);
					}
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
				
			} finally {
				try {
					if(rs!=null) {rs.close();}
					if(stmt!=null) {stmt.close();}
					DbConnector.getInstancia().releaseConn();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			
			return profesionales;
		}
		
		public Profesional getByProfesional(Profesional prof) {
			Profesional p=null;
			PreparedStatement stmt=null;
			ResultSet rs=null;
			try {
				stmt=DbConnector.getInstancia().getConn().prepareStatement(
						"select id_profesional,nombre,apellido,nombre_usuario,estado from Profesional where nombre_usuario=? and contraseña=?"
						);
				stmt.setString(1, prof.getNombreUsuario());
				stmt.setString(2, prof.getPassword());
				rs=stmt.executeQuery();
				if(rs!=null && rs.next()) {
					p=new Profesional();
					p.setIdProfesional(rs.getInt("id_usuario"));
					p.setNombre(rs.getString("nombre"));
					p.setApellido(rs.getString("apellido"));
					p.setProfesion(rs.getString("email"));
					p.setNombreUsuario(rs.getString("nombre_usuario"));
					p.setEstado(rs.getBoolean("estado"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					if(rs!=null) {rs.close();}
					if(stmt!=null) {stmt.close();}
					DbConnector.getInstancia().releaseConn();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			return p;
		}
		
		public Profesional getProfesionalById(int id) {
			Profesional p=null;
			PreparedStatement stmt=null;
			ResultSet rs=null;
			try {
				stmt=DbConnector.getInstancia().getConn().prepareStatement(
						"select id,nombre,apellido,profesion,estado from Profesional where id=?" /*nombre_usuario*/
						);
				stmt.setInt(1, id);
				rs=stmt.executeQuery();
				if(rs!=null && rs.next()) {
					p=new Profesional();
					p.setIdProfesional(rs.getInt("id"));
					p.setNombre(rs.getString("nombre"));
					p.setApellido(rs.getString("apellido"));
					p.setProfesion(rs.getString("profesion"));
					/*p.setNombreUsuario(rs.getString("nombre_usuario"));*/
					p.setEstado(rs.getBoolean("estado"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					if(rs!=null) {rs.close();}
					if(stmt!=null) {stmt.close();}
					DbConnector.getInstancia().releaseConn();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			return p;
		}
		
		
		public boolean modificarProfesional(int id, String nombre, String apellido, String profesion/*String nombreUsuario*/) {
		    PreparedStatement stmt = null;
		    try {
		        stmt = DbConnector.getInstancia().getConn().prepareStatement(
		                "UPDATE profesional SET nombre = ?, apellido = ?, profesion = ? WHERE id = ?");/*nombre_usuario = ?*/
		        stmt.setString(1, nombre);
		        stmt.setString(2, apellido);
		        stmt.setString(3, profesion);
		        /*stmt.setString(4, nombreUsuario);*/
		        stmt.setInt(4, id);

		        int filasActualizadas = stmt.executeUpdate(); 
		        return filasActualizadas > 0; // Retornar true si se actualizó al menos una fila
		    } catch (SQLException e) {
		        e.printStackTrace();
		        return false; // Retornar false si ocurre un error
		    } finally {
		        try {
		            if (stmt != null) { stmt.close(); }
		            DbConnector.getInstancia().releaseConn();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		}
		
		public boolean eliminarProfesional(int id) {
		    PreparedStatement stmt = null;
		    try {
		        stmt = DbConnector.getInstancia().getConn().prepareStatement("UPDATE profesional SET estado= 0 WHERE id = ?");
		        stmt.setInt(1, id);
		        int filasBorradas = stmt.executeUpdate();
		        return filasBorradas > 0; // Devuelve true si se eliminó al menos una fila
		    } catch (SQLException e) {
		        e.printStackTrace();
		        return false; // Devuelve false si ocurrió un error
		    } finally {
		        try {
		            if (stmt != null) { stmt.close(); }
		            DbConnector.getInstancia().releaseConn();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		}

		public void addProfesional(Profesional p) {
			PreparedStatement stmt= null;
			ResultSet keyResultSet=null;
			try {
				stmt=DbConnector.getInstancia().getConn().
						prepareStatement(
								"insert into Profesional(nombre, apellido, profesion, estado) values(?,?,?,?)", /*nombre_usuario, contrasena*/
								PreparedStatement.RETURN_GENERATED_KEYS
								);
				stmt.setString(1, p.getNombre());
				stmt.setString(2, p.getApellido());
				stmt.setString(3, p.getProfesion());
				/*stmt.setString(4, p.getNombreUsuario());*/
				/*stmt.setString(4, p.getPassword());*/
				stmt.setBoolean(4, p.getEstado());
				stmt.executeUpdate();
				
				keyResultSet=stmt.getGeneratedKeys();
	            if(keyResultSet!=null && keyResultSet.next()){
	                p.setIdProfesional(keyResultSet.getInt(1));
	                ;
	            }
			
			}  catch (SQLException e) {
	            e.printStackTrace();
			} finally {
	            try {
	                if(keyResultSet!=null)keyResultSet.close();
	                if(stmt!=null)stmt.close();
	                DbConnector.getInstancia().releaseConn();
	            } catch (SQLException e) {
	            	e.printStackTrace();
	            }
			}
		}
}

