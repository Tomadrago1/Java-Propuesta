<%@page import="java.util.LinkedList"%>
<%@page import="entities.Usuario"%>
<%@page import="entities.Profesional" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="style/UserManagementstyles.css">
    
    <%
        Usuario u = (Usuario)session.getAttribute("usuario");
        LinkedList<Usuario> lu = (LinkedList<Usuario>)request.getAttribute("listaUsuarios");
    %>
    
</head>
<body>
    <div class="container">
        <h1>Usuarios</h1>
        <div class="div-table">
            <table>
                <thead>
                    <tr>
                        <th>Nombre</th>
                        <th>Apellido</th>
                        <th>Email</th>
                        <th>Nombre de Usuario</th>
                        <th>Tipo Usuario</th>
                        <th>Profesion</th>
                        <th>Editar usuario</th>
                        <th>Baja usuario</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Usuario usu : lu) { 
                    if (usu.getEstado()) { // Muestra solo si el estado es true (o 1)
                    %>
                    <tr>
                        <td><%=usu.getNombre()%></td>
                        <td><%=usu.getApellido()%></td>
                        <td><%=usu.getEmail()%></td>
                        <td><%=usu.getNombreUsuario()%></td>
						<td>
                        <%
                            int tipoUsuario = usu.getTipoUsu();
                            String tipoUsuarioStr = "";
                            
                            switch (tipoUsuario) {
                                case 1:
                                    tipoUsuarioStr = "Admin";
                                    break;
                                case 2:
                                    tipoUsuarioStr = "Profesional";
                                    break;
                                case 3:
                                    tipoUsuarioStr = "Cliente";
                                    break;
                                default:
                                    tipoUsuarioStr = "Desconocido";
                                    break;
                            }
                        %>
                        <%= tipoUsuarioStr %>
						</td>
                        <td>
                            <% String profesion="-" ; if (usu instanceof Profesional) {
                                profesion=((Profesional)usu).getProfesion();} %>
                                <%= profesion !=null ? profesion : "-" %>
                        </td>
                        <td>
                            <form action="editarUsuario" method="post" style="display:inline;">
                                <input type="hidden" name="id" value="<%=usu.getIdUsuario()%>">
                                <input type="hidden" name="tipo_usu" value="<%=usu.getTipoUsu()%>">
                                <input type="submit" value="Editar" class="action-btn edit-btn">
                            </form>
                        </td>
                        <td>
                            <form action="EliminarUsuario" method="post" style="display:inline;">
                                <input type="hidden" name="id" value="<%=usu.getIdUsuario()%>">
                                <input type="submit" value="Borrar" class="action-btn delete-btn" onclick="return confirm('¿Estás seguro de que deseas eliminar este usuario?');">
                            </form>
                        </td>
                    </tr>
                    <% 
                    } // Fin de la condición
                    } %>
                </tbody>
            </table>
            <div style="text-align: right; margin-top: 20px; margin-bottom:20px; padding: 0 20px;">
                <form action="crearUsuario" method="post" style="display:inline;">
                    <input type="submit" value="Crear Usuario" class="action-btn create-btn">
                </form>
            </div>
        </div>
        <a href="index.html" style="color: red">Volver</a>
    </div>
</body>
</html>