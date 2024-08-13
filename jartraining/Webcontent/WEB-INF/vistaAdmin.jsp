<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="entities.Usuario"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="style/vistaAdminStyles.css">
    <%
        Usuario u = (Usuario)session.getAttribute("usuario");
    %>
    <title>Vista Administrador</title>
</head>
<body>
<div class="super-container">
	<h1>Bienvenido <%= u.getNombre() + " " + u.getApellido() %></h1>
    <div class="admin-container">
        <h2>Panel de Administración</h2>
        <form action="verUsuarios" method="post">
            <input type="submit" class="admin-btn" value="Usuarios">
        </form>
        <form action="verProfesionales" method="post">
            <input type="submit" class="admin-btn" value="Profesionales">
        </form>
        <form action="verRecetas" method="post">
            <input type="submit" class="admin-btn" value="Recetas">
        </form>
        <form action="verRutinas" method="post">
            <input type="submit" class="admin-btn" value="Rutinas">
        </form>
        <form action="listadoEjercicios" method="post">
            <input type="submit" class="admin-btn" value="Ejercicios">
        </form>
    </div>
    </div>
</body>
</html> 