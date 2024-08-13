<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="entities.Usuario"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Vista Cliente</title>
    <link rel="stylesheet" href="style/vistaClienteStyles.css"> <!-- Puedes agregar tu CSS aquí -->
        <%
        Usuario u = (Usuario)session.getAttribute("usuario");
    %>
</head>
<body>
    <h1>Bienvenido <%= u.getNombre() + " " + u.getApellido() %></h1>
    <div class="button-container">
        <a href="misRutinas" class="button">Mis Rutinas</a>
        <a href="misMedidas" class="button">Mis Medidas</a>
        <a href="agendarConsulta" class="button">Agendar Consulta con Profesional</a>
        <a href="verRecetasRecomendadas" class="button">Ver Recetas Recomendadas</a>
    </div>
</body>
</html>