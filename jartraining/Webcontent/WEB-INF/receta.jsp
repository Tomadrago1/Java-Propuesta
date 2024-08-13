<%@page import="java.util.LinkedList"%>
<%@page import="entities.Ingrediente"%>
<%@page import="entities.Receta"%>
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
    	Receta r = (Receta)request.getAttribute("Receta");
        LinkedList<Ingrediente> li = (LinkedList<Ingrediente>)request.getAttribute("ListaIngredientes");
    %>
    
</head>
<body>
    <div class="container">
        <h1><%out.print(r.getNombre());%></h1>
        <div>
            <table>
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Nombre</th>
                        <th>Descripcion</th>
                        <th>Editar Ingrediente</th>
                        <th>Baja Ingrediente</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Ingrediente ing : li) { %>
                    <tr>
                        <td><%= ing.getId() %></td>
                        <td><%= ing.getNombre() %></td>
                        <td><%= ing.getDesc() %></td>
                        <td><a href="editarIngrediente?id=<%= ing.getId() %>" class="action-btn edit-btn">Editar</a></td>
                        <td>
                            <form action="EliminarIngrediente" method="post" style="display:inline;">
                                <input type="hidden" name="id" value="<%= ing.getId() %>">
                                <input type="submit" value="Borrar" class="action-btn delete-btn" onclick="return confirm('¿Estás seguro de que deseas eliminar este ingrediente?');">
                            </form>
                        </td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
            <div style="text-align: right; margin-top: 20px; margin-bottom:20px; padding: 0 20px;">
                <a href="crearIngrediente" class="action-btn create-btn">Crear Ingrediente</a>
            </div>
        </div>
    </div>
</body>
</html>
