<<<<<<< HEAD
package data;

import entities.*;
import java.sql.*;
import java.util.LinkedList;

public class DaoEjercicio {
	
	public LinkedList<Ejercicio> getAll(){
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<Ejercicio> ejercicios = new LinkedList<>();
		
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("select * from ejercicio");

			if(rs!=null) {
				while(rs.next()) {
					Ejercicio e =new Ejercicio();
					e.setId(rs.getInt("id"));
					e.setNombre(rs.getString("nombre"));
					e.setDescripcion(rs.getString("descripcion"));
					ejercicios.add(e);
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
		
		return ejercicios;
	}
	
	public Ejercicio getOne(int id) {
		Ejercicio e =new Ejercicio();
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"Select * from ejercicio where id = ?"
					);
			stmt.setInt(1, id);
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				e.setId(rs.getInt("id"));
				e.setNombre(rs.getString("nombre"));
				e.setDescripcion(rs.getString("descripcion"));
			}
		} catch (SQLException err) {
			err.printStackTrace();
		}finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException err) {
				err.printStackTrace();
			}
		}
		
		return e;
	}
	
	
	public boolean modificarEjercicio(int id, String nombre, String descripcion) {
	    PreparedStatement stmt = null;
	    try {
	        stmt = DbConnector.getInstancia().getConn().prepareStatement(
	                "UPDATE ejercicio SET nombre = ?, descripcion = ? WHERE id = ?");
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
	            if (stmt != null) { stmt.close(); }
	            DbConnector.getInstancia().releaseConn();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

	
	public boolean eliminarEjercicio(int id_eje) {
	    PreparedStatement stmt = null;
	    Connection conn = null;
	    try {
	        conn = DbConnector.getInstancia().getConn();
	        conn.setAutoCommit(false); 
	        
	        stmt = conn.prepareStatement("DELETE FROM rutina_ejercicio WHERE id_ejercicio = ?");
	        stmt.setInt(1, id_eje);
	        stmt.executeUpdate();

	        stmt = conn.prepareStatement("DELETE FROM ejercicio WHERE id = ?");
	        stmt.setInt(1, id_eje);
	        int filasAfectadas = stmt.executeUpdate();

	        conn.commit();
	        return filasAfectadas > 0;

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
	            if (stmt != null) { stmt.close(); }
	            if (conn != null) { conn.setAutoCommit(true); conn.close(); }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

	
	public void addEjercicio(Ejercicio e) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"INSERT INTO ejercicio (nombre, descripcion) VALUES(?, ?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setString(1, e.getNombre());
			stmt.setString(2, e.getDescripcion());
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                e.setId(keyResultSet.getInt(1));
                ;
            }
		
		}  catch (SQLException err) {
            err.printStackTrace();
		} finally {
            try {
                if(keyResultSet!=null)keyResultSet.close();
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException err) {
            	err.printStackTrace();
            }
		}
	}
	
}
=======
package data;

import entities.*;
import java.sql.*;
import java.util.LinkedList;

public class DaoEjercicio {
	
	public LinkedList<Ejercicio> getAll(){
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<Ejercicio> ejercicios = new LinkedList<>();
		
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("select * from ejercicio");

			if(rs!=null) {
				while(rs.next()) {
					Ejercicio e =new Ejercicio();
					e.setId(rs.getInt("id"));
					e.setNombre(rs.getString("nombre"));
					e.setDescripcion(rs.getString("descripcion"));
					ejercicios.add(e);
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
		
		return ejercicios;
	}
	
	public Ejercicio getOne(int id) {
		Ejercicio e =new Ejercicio();
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"Select * from ejercicio where id = ?"
					);
			stmt.setInt(1, id);
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				e.setId(rs.getInt("id"));
				e.setNombre(rs.getString("nombre"));
				e.setDescripcion(rs.getString("descripcion"));
			}
		} catch (SQLException err) {
			err.printStackTrace();
		}finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException err) {
				err.printStackTrace();
			}
		}
		
		return e;
	}
	
	
	public boolean modificarEjercicio(int id, String nombre, String descripcion) {
	    PreparedStatement stmt = null;
	    try {
	        stmt = DbConnector.getInstancia().getConn().prepareStatement(
	                "UPDATE ejercicio SET nombre = ?, descripcion = ? WHERE id = ?");
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
	            if (stmt != null) { stmt.close(); }
	            DbConnector.getInstancia().releaseConn();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

	
	public boolean eliminarEjercicio(int id_eje) {
	    PreparedStatement stmt = null;
	    Connection conn = null;
	    try {
	        conn = DbConnector.getInstancia().getConn();
	        conn.setAutoCommit(false); 
	        
	        stmt = conn.prepareStatement("DELETE FROM rutina_ejercicio WHERE id_ejercicio = ?");
	        stmt.setInt(1, id_eje);
	        stmt.executeUpdate();

	        stmt = conn.prepareStatement("DELETE FROM ejercicio WHERE id = ?");
	        stmt.setInt(1, id_eje);
	        int filasAfectadas = stmt.executeUpdate();

	        conn.commit();
	        return filasAfectadas > 0;

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
	            if (stmt != null) { stmt.close(); }
	            if (conn != null) { conn.setAutoCommit(true); conn.close(); }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

	
	public void addEjercicio(Ejercicio e) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"INSERT INTO ejercicio (nombre, descripcion) VALUES(?, ?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setString(1, e.getNombre());
			stmt.setString(2, e.getDescripcion());
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                e.setId(keyResultSet.getInt(1));
                ;
            }
		
		}  catch (SQLException err) {
            err.printStackTrace();
		} finally {
            try {
                if(keyResultSet!=null)keyResultSet.close();
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException err) {
            	err.printStackTrace();
            }
		}
	}
	
}
>>>>>>> 2a7a2cddb7c46cdba58517d715cf07559706d21c
