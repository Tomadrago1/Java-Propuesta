package data;
import entities.*;
import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


public class DaoReceta{
	
		public LinkedList<Receta> getAll(){
			Statement stmt=null;
			ResultSet rs=null;
			LinkedList<Receta> recetas= new LinkedList<>();
			
			try {
				stmt= DbConnector.getInstancia().getConn().createStatement();
				rs= stmt.executeQuery("select r.id,r.nombre,r.descripcion,r.nivel_dificultad,p.nombre,p.apellido,p.id,p.profesion from Receta r left join Profesional p on p.id=r.id_profesional");
				if(rs!=null) {
					while(rs.next()) {
						Receta r=new Receta();
						Profesional p=new Profesional();
						r.setId(rs.getInt("r.id"));
						r.setNombre(rs.getString("r.nombre"));
						r.setDesc(rs.getString("r.descripcion"));
						r.setNivelDificultad(rs.getString("r.nivel_dificultad"));
						if (rs.getObject("p.id") != null){
						p.setNombre(rs.getString("p.nombre"));
						p.setIdProfesional(rs.getInt("p.id"));
						p.setApellido(rs.getString("p.apellido"));
						p.setProfesion(rs.getString("p.profesion"));}else {
							p=null;
						}
						r.setProfesional(p);
						recetas.add(r);
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
		return recetas;
		}
		
		/*public Profesional getByProfesional(Profesional prof) {
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
		}*/
		
		public Receta getRecetaById(int id) {
		    Receta r = null; // Inicializar Receta
		    PreparedStatement stmt = null;
		    ResultSet rs = null;
		    try {
		        stmt = DbConnector.getInstancia().getConn().prepareStatement(
		                "select r.id, r.nombre, r.descripcion, r.nivel_dificultad, p.nombre, p.apellido, p.id, p.profesion " +
		                "from Receta r left join Profesional p on r.id_profesional = p.id where r.id = ?"
		        );
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
		            if (rs.getObject("p.id") != null) {
		                p.setNombre(rs.getString("p.nombre"));
		                p.setIdProfesional(rs.getInt("p.id"));
		                p.setApellido(rs.getString("p.apellido"));
		                p.setProfesion(rs.getString("p.profesion"));
		                r.setProfesional(p);
		            } else {
		                r.setProfesional(null); // Explicitamente asignar null si no hay profesional
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        try {
		            if (rs != null) { rs.close(); }
		            if (stmt != null) { stmt.close(); }
		            DbConnector.getInstancia().releaseConn();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		    return r;
		}
		
		public LinkedList<Map<String, Object>> getIngredientesReceta(int idReceta) {
		    PreparedStatement stmt = null;
		    ResultSet rs = null;
		    LinkedList<Map<String, Object>> ingredientes = new LinkedList<>();
		    try {
		        stmt = DbConnector.getInstancia().getConn().prepareStatement("select i.id,i.nombre,i.descripcion,ir.cantidad_porcion from Receta r inner join ingrediente_receta ir on r.id=ir.id_receta inner join ingrediente i on ir.id_ingrediente=i.id where r.id=?");
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
		            if (rs != null) { rs.close(); }
		            if (stmt != null) { stmt.close(); }
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
		            if (stmt != null) { stmt.close(); }
		            DbConnector.getInstancia().releaseConn();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		}
		
		public boolean eliminarReceta(int idReceta) {
		    PreparedStatement stmt1 = null;
		    PreparedStatement stmt2 = null;
		    PreparedStatement stmt3 = null;
		    Connection conn = null;
		    boolean result = false;

		    try {
		        conn = DbConnector.getInstancia().getConn();
		        conn.setAutoCommit(false); // Iniciar la transacción

		        // Elimina las relaciones en las tablas intermedias
		        stmt1 = conn.prepareStatement("DELETE FROM ingrediente_receta WHERE id_receta = ?");
		        stmt1.setInt(1, idReceta);
		        stmt1.executeUpdate();

		        stmt2 = conn.prepareStatement("DELETE FROM nutriente_receta WHERE id_receta = ?");
		        stmt2.setInt(1, idReceta);
		        stmt2.executeUpdate();

		        // Elimina la receta en la tabla principal
		        stmt3 = conn.prepareStatement("DELETE FROM Receta WHERE id = ?");
		        stmt3.setInt(1, idReceta);
		        stmt3.executeUpdate();

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
		            if (stmt1 != null) stmt1.close();
		            if (stmt2 != null) stmt2.close();
		            if (stmt3 != null) stmt3.close();
		            if (conn != null) conn.setAutoCommit(true); // Restablecer el modo de autocommit
		            DbConnector.getInstancia().releaseConn();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		    return result;
		}

		public void addReceta(Receta r) {
			PreparedStatement stmt= null;
			ResultSet keyResultSet=null;
			try {
				stmt=DbConnector.getInstancia().getConn().
						prepareStatement(
								"insert into Receta(nombre, descripcion, nivel_dificultad, id_profesional) values(?,?,?,?)",
								PreparedStatement.RETURN_GENERATED_KEYS
								);
				stmt.setString(1, r.getNombre());
				stmt.setString(2, r.getDesc());
				stmt.setString(3, r.getNivelDificultad());
				stmt.setObject(4, r.getProfesional());
				stmt.executeUpdate();
				
				keyResultSet=stmt.getGeneratedKeys();
	            if(keyResultSet!=null && keyResultSet.next()){
	                r.setId(keyResultSet.getInt(1));
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
		
		public boolean addIngredienteReceta(int idReceta, int idIngrediente, double cantidad) {
		    PreparedStatement stmt = null;
		    try {
		        stmt = DbConnector.getInstancia().getConn().prepareStatement(
		                "INSERT INTO ingrediente_receta (id_receta, id_ingrediente, cantidad_porcion) VALUES (?, ?, ?)");

		        stmt.setInt(1, idReceta);
		        stmt.setInt(2, idIngrediente);
		        stmt.setDouble(3, cantidad);
		        int rowsAffected = stmt.executeUpdate();
		        return rowsAffected > 0;
		    } catch (SQLException e) {
		        e.printStackTrace();
		        return false;
		    } finally {
		        try {
		            if (stmt != null) stmt.close();
		            DbConnector.getInstancia().releaseConn();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		}
		
		public boolean modificarCantidadIngredienteReceta(int idReceta, int idIngrediente, double cantidad) {
		    PreparedStatement stmt = null;
		    try {
		        stmt = DbConnector.getInstancia().getConn().prepareStatement(
		                "UPDATE ingrediente_receta SET cantidad_porcion = ? WHERE id_ingrediente = ? and id_receta = ?");

		        stmt.setDouble(1, cantidad);
		        stmt.setInt(2, idIngrediente);
		        stmt.setInt(3, idReceta);
		        int rowsAffected = stmt.executeUpdate();
		        return rowsAffected > 0;
		    } catch (SQLException e) {
		        e.printStackTrace();
		        return false;
		    } finally {
		        try {
		            if (stmt != null) stmt.close();
		            DbConnector.getInstancia().releaseConn();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		}
		
		public boolean borrarIngredienteReceta(int idReceta,int idIngrediente) {
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
		            if (stmt != null) stmt.close();
		            DbConnector.getInstancia().releaseConn();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		}

}


