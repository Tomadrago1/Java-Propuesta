<%@page import="java.util.LinkedList"%>
<%@page import="entities.Profesional"%>
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
        Profesional p = (Profesional)session.getAttribute("profesional");
        LinkedList<Profesional> lp = (LinkedList<Profesional>)request.getAttribute("listaProfesionales");
    %>
    
</head>
<body>
    <div class="container">
        <h1>Profesionales</h1>
        <div>
            <table>
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Nombre</th>
                        <th>Apellido</th>
                        <th>Profesion</th>
                        <th>Nombre Usuario</th>         
                        <th>Editar profesional</th>
                        <th>Baja profesional</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Profesional prof : lp) { 
                    if (prof.getEstado()) { // Muestra solo si el estado es true (o 1)
                    %>
                    <tr>
                        <td><%=prof.getIdProfesional()%></td>
                        <td><%=prof.getNombre()%></td>
                        <td><%=prof.getApellido()%></td>
                        <td><%=prof.getProfesion()%></td>
                        <td><%=prof.getNombreUsuario()%></td>      
                        <td><a href="editarProfesional?id=<%=prof.getIdProfesional()%>" class="action-btn edit-btn">Editar</a></td>
                        <td>
                            <form action="EliminarProfesional" method="post" style="display:inline;">
                                <input type="hidden" name="id" value="<%=prof.getIdProfesional()%>">
                                <input type="submit" value="Borrar" class="action-btn delete-btn" onclick="return confirm('¿Estás seguro de que deseas eliminar este profesional?');">
                            </form>
                        </td>
                    </tr>
                    <% 
                    } // Fin de la condición
                    } %>
                </tbody>
            </table>
            <div style="text-align: right; margin-top: 20px; margin-bottom:20px; padding: 0 20px;">
                <a href="crearProfesional" class="action-btn create-btn">Crear Profesional</a>
            </div>
        </div>
    </div>
</body>
</html>