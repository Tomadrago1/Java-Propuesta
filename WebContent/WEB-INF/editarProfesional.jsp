<%@page import="entities.Profesional"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="style/editarUsuarioStyles.css">
    <title>Editar Profesional</title>
	
</head>
<body>
<%
    Profesional profesional = (Profesional)request.getAttribute("profesional");
%>
    <div class="container">
        <h1>Editar Profesional</h1>
        <form action="actualizarProfesional" method="post">
            <input type="hidden" name="id" value="<%=profesional.getIdProfesional()%>">
            <div class="form-group">
                <label for="nombre">Nombre:</label>
                <input type="text" id="nombre" name="nombre" value="<%=profesional.getNombre()%>">
            </div>
            <div class="form-group">
                <label for="apellido">Apellido:</label>
                <input type="text" id="apellido" name="apellido" value="<%=profesional.getApellido()%>">
            </div>
            <div class="form-group">
                <label for="email">Profesion:</label>
                <input type="text" id="profesion" name="profesion" value="<%=profesional.getProfesion()%>">
            </div>
            <%--<div class="form-group">
                <label for="nombreUsuario">Nombre de Usuario:</label>
                <input type="text" id="nombreUsuario" name="nombreUsuario" value="<%=profesional.getNombreUsuario()%>">
            </div>--%>
            <div class="form-group">
                <input type="submit" value="Actualizar">
            </div>
        </form>
    </div>
</body>
</html>