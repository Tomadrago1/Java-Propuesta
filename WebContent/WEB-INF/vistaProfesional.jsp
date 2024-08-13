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
        <a href="crearReceta" class="button">Crear Receta</a>
        <a href="misConsultas" class="button">Mis Consultas</a>
        <a href="misRecetas" class="button">Mis recetas</a>
    </div>
</body>
</html>