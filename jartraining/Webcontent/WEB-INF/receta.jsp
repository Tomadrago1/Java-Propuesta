<%@page import="java.util.LinkedList"%>
<%@page import="java.util.Map"%>
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
LinkedList<Map<String, Object>> li = (LinkedList<Map<String, Object>>)request.getAttribute("ListaIngredientes");
%>
    
</head>
<body>
    <div class="container">
        <h1><%out.print(r.getNombre());%></h1>
        <div>
            <table>
                <thead>
                    <tr>
                        <th>Nombre</th>
                        <th>Descripcion</th>
                        <th>Cantidad</th>
                        <th>Editar Cantidad</th>
                        <th>Baja Ingrediente</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Map<String, Object> ingrediente : li) { %>
                    <tr>
                        <td><%= ingrediente.get("nombre") %></td>
                        <td><%= ingrediente.get("descripcion") %></td>
                        <td><%= ingrediente.get("cantidad") %></td>
                        <td>
                        	<form action="editarCantidadIngrediente" method="post" style="display:inline;">
                        		<input type="hidden" name="cantidad" value="<%=ingrediente.get("cantidad")%>">
                                <input type="hidden" name="idIngrediente" value="<%=ingrediente.get("id")%>">
                                <input type="hidden" name="idReceta" value="<%=r.getId()%>">
                                <input type="submit" value="Editar" class="action-btn edit-btn">
                           	</form>
                        <td>
                            <form action="eliminarIngredienteReceta" method="post" style="display:inline;">
                                <input type="hidden" name="idIngrediente" value="<%= ingrediente.get("id")%>">
                                <input type="hidden" name="idReceta" value="<%=r.getId()%>">
                                <input type="submit" value="Borrar" class="action-btn delete-btn" onclick="return confirm('¿Estás seguro de que deseas eliminar este ingrediente?');">
                            </form>
                        </td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
            <div style="text-align: right; margin-top: 20px; margin-bottom:20px; padding: 0 20px;">
            	<form action="agregarIngrediente" method="post" style="display:inline;">
            		<input type="hidden" name="idReceta" value="<%=r.getId()%>">
                	<input type="submit" value="Agregar Ingrediente" class="action-btn create-btn">
            	</form>
            </div>
        </div>
    </div>
</body>
</html>
