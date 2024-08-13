<%@page import="entities.Profesional"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="style/crearUsuarioStyles.css">
    <title>Crear Profesional</title>
</head>
<body>
    <div class="container">
        <h1>Crear Profesional</h1>
        <form action="GuardarProfesional" method="post">
            <div class="form-group">
                <label for="nombre">Nombre:</label>
                <input type="text" id="nombre" name="nombre" required>
            </div>
            <div class="form-group">
                <label for="apellido">Apellido:</label>
                <input type="text" id="apellido" name="apellido" required>
            </div>
            <div class="form-group">
                <label for="profesion">Profesión:</label>
                <input type="text" id="profesion" name="profesion" required>
            </div>
            <%--<div class="form-group">
                <label for="nombreUsuario">Nombre de Usuario:</label>
                <input type="text" id="nombreUsuario" name="nombreUsuario" required>
            </div>
            <div class="form-group">
                <label for="password">Contraseña:</label>
                <input type="password" id="password" name="password" required>
            </div>--%>
            <div class="form-group">
                <input type="submit" value="Crear">
            </div>
        </form>
    </div>
</body>
</html>
