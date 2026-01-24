<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="entities.Usuario"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Vista Profesional</title>
    <link rel="stylesheet" href="style/vistaProfesionalStyles.css"> <!-- Puedes agregar tu CSS aquí -->
    <%
    Usuario u = (Usuario)session.getAttribute("usuario");
    %>
</head>
<body>
    <h1>Bienvenido <%= u.getNombre() + " " + u.getApellido() %></h1>
    <div class="button-container">
        <form action="crearRecetaProfesional" method="post" style="display:inline;">
        	<input type="hidden" name="id_profesional" value="<%=u.getIdUsuario()%>">
            <button type="submit" class="button">Crear Receta</button>
        </form>

        <form action="misConsultas" method="post" style="display:inline;">
            <button type="submit" class="button">Mis Consultas</button>
        </form>

        <form action="misRecetas" method="post" style="display:inline;">
            <input type="hidden" name="idProfesional" value="<%= u.getIdUsuario() %>">
            <button type="submit" class="button">Mis Recetas</button>
        </form>
    </div>
</body>
</html>