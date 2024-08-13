<%@page import="java.util.LinkedList"%>
<%@page import="entities.Ingrediente"%>
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
        LinkedList<Ingrediente> li = (LinkedList<Ingrediente>)request.getAttribute("ListaIngredientes");
    	int idReceta = (int)request.getAttribute("receta");
    	
    %>
    
</head>
<body>
    <div class="container">
        <h1>Ingredientes</h1>
        <div>
            <table>
                <thead>
                    <tr>
                        <th>Nombre</th>
                        <th>Descripción</th>
                        <th>Agregar Ingrediente</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Ingrediente ing : li) { %>
                    <tr>
                        <td><%=ing.getNombre()%></td>
                        <td><%=ing.getDesc()%></td>
                        <td>
                            <form action="agregarIngredienteReceta" method="post" style="display:inline;">
                                <input type="hidden" name="idIngrediente" value="<%=ing.getId()%>">
                                <input type="hidden" name="idReceta" value="<%=idReceta%>">
                                <input type="submit" value="Agregar" class="action-btn create-btn" onclick="return confirm('¿Estás seguro de que deseas agregar este ingrediente?');">
                            </form>
                        </td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
            <div style="text-align: right; margin-top: 20px;">
                <form action="crearIngrediente" method="post" style="display:inline;">
                	<input type="hidden" name="idReceta" value="<%=idReceta%>">
                    <input type="submit" value="Crear Ingrediente" class="action-btn create-btn">
                </form>
            </div>
        </div>
    </div>
</body>
</html>
