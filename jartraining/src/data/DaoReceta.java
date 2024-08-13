package data;
import entities.*;
import java.sql.*;
import java.util.LinkedList;


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
		
		public LinkedList<Ingrediente> getIngredientesReceta(int idReceta) {
		    PreparedStatement stmt = null;
		    ResultSet rs = null;
		    LinkedList<Ingrediente> ingredientes = new LinkedList<>();
		    try {
		        stmt = DbConnector.getInstancia().getConn().prepareStatement("select i.id,i.nombre,i.descripcion from Receta r inner join ingrediente_receta ir on r.id=ir.id_receta inner join ingrediente i on ir.id_ingrediente=i.id where r.id=?");
		        stmt.setInt(1, idReceta);
		        rs = stmt.executeQuery();
				if(rs!=null) {
					while(rs.next()) {
						Ingrediente i=new Ingrediente();
						i.setId(rs.getInt("i.id"));
						i.setNombre(rs.getString("i.nombre"));
						i.setDesc(rs.getString("i.descripcion"));
						ingredientes.add(i);
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
		return ingredientes;
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

