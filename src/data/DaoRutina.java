package data;

import entities.*;

import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class DaoRutina {
	
	public LinkedList<Rutina> getAll(){
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<Rutina> rutinas= new LinkedList<>();
		
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("select * from Rutina");
			
			if(rs!=null) {
				while(rs.next()) {
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
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return rutinas;
	}
	
	public boolean eliminarRutina(int id) {
		PreparedStatement stmt = null;
	    try {
	        stmt = DbConnector.getInstancia().getConn().prepareStatement("DELETE FROM rutina WHERE id = ?");
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
	
	public Rutina getOne(int id) {
		Rutina r =new Rutina();
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"Select * from rutina where id = ?"
					);
			stmt.setInt(1, id);
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				r.setId(rs.getInt("id"));
				r.setNombre(rs.getString("nombre"));
				r.setDescripcion(rs.getString("descripcion"));
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
		
		return r;

	}
	
	public boolean modificarRutina(int id,String nombre,String desc) {
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
	        	if (stmt != null) { stmt.close(); }
	            DbConnector.getInstancia().releaseConn();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	public void addRutina(Rutina r) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"INSERT INTO rutina (nombre, descripcion) VALUES(?, ?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setString(1, r.getNombre());
			stmt.setString(2, r.getDescripcion());
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                r.setId(keyResultSet.getInt(1));
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
	
	public LinkedList<Map<String, Object>> getEjerciciosByRutina(int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		LinkedList<Map<String, Object>> ejercicios = new LinkedList<>();

		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement("SELECT eje.id, eje.nombre, eje.descripcion, re.series_aproximadas, re.repeticiones_aproximadas FROM rutina_ejercicio AS re INNER JOIN ejercicios AS eje ON re.id_ejercicio = eje.id WHERE id_rutina = ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			while (rs != null && rs.next()) {
				Map<String, Object> ejercicio = new HashMap<>();
                ejercicio.put("id_eje", rs.getInt("eje.id"));
                ejercicio.put("id_rut", id);
                ejercicio.put("nombre", rs.getString("eje.nombre"));
                ejercicio.put("descripcion", rs.getString("eje.descripcion"));
                ejercicio.put("series", rs.getInt("re.series_aproximadas"));
                ejercicio.put("repes", rs.getInt("re.repeticiones_aproximadas"));
                ejercicios.add(ejercicio);
               
			}
		} catch (SQLException err) {
			err.printStackTrace();
		} finally {
			try {
				if (stmt != null) { stmt.close(); }
				if (rs != null) { rs.close(); }
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException err) {
				err.printStackTrace();
			}
		}
		return ejercicios;
	}
	
	public boolean modificarEjercicioRutina(int id_rut,int id_eje,int series,int repes) {
		PreparedStatement stmt = null;
	    try {
	        stmt = DbConnector.getInstancia().getConn().prepareStatement(
	                "UPDATE rutina_ejercicio SET repeticiones_aproximadas = ?, series_aproximadas = ? WHERE (id_rutina = ? AND id_ejercicio = ?)");
	        stmt.setInt(1, repes);
	        stmt.setInt(2, series);
	        stmt.setInt(3, id_rut);
	        stmt.setInt(4, id_eje);
	        
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
	
	public boolean quitarEjercicioRutina(int id_rut,int id_eje) {
		PreparedStatement stmt = null;
	    try {
	        stmt = DbConnector.getInstancia().getConn().prepareStatement("DELETE FROM rutina_ejercicio WHERE (id_rutina = ? AND id_ejercicio = ?)");
	        stmt.setInt(1, id_rut);
	        stmt.setInt(2, id_eje);
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
}