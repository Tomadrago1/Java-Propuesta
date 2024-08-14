<%@page import="java.util.LinkedList"%>
<%@page import="entities.Usuario"%>
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
        <h1>Personas</h1>
        <div>
            <table>
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Nombre</th>
                        <th>Apellido</th>
                        <th>Email</th>
                        <th>Nombre de Usuario</th>         
                        <th>Editar usuario</th>
                        <th>Baja usuario</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Usuario usu : lu) { 
                    if (usu.getEstado()) { // Muestra solo si el estado es true (o 1)
                    %>
                    <tr>
                        <td><%=usu.getIdUsuario()%></td>
                        <td><%=usu.getNombre()%></td>
                        <td><%=usu.getApellido()%></td>
                        <td><%=usu.getEmail()%></td>
                        <td><%=usu.getNombreUsuario()%></td>  
                        <td><a href="editarUsuario?id=<%=usu.getIdUsuario()%>" class="action-btn edit-btn">Editar</a></td>
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
                <a href="crearUsuario" class="action-btn create-btn">Crear Usuario</a>
            </div>
        </div>
    </div>
</body>
</html>