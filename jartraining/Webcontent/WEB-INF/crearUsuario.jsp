<%@page import="entities.Usuario"%>
<%@page import="data.DaoUsuario"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="style/crearUsuarioStyles.css">
    <title>Crear Usuario</title>
</head>
<body>
    <div class="container">
        <h1>Crear Usuario</h1>
        <form action="GuardarUsuario" method="post">
            <div class="form-group">
                <label for="nombre">Nombre:</label>
                <input type="text" id="nombre" name="nombre" required>
            </div>
            <div class="form-group">
                <label for="apellido">Apellido:</label>
                <input type="text" id="apellido" name="apellido" required>
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required>
            </div>
            <div class="form-group">
                <label for="nombreUsuario">Nombre de Usuario:</label>
                <input type="text" id="nombreUsuario" name="nombreUsuario" required>
            </div>
            <div class="form-group">
                <label for="tipoUsuario">Tipo de Usuario:</label>
                <input type="text" id="tipoUsuario" name="tipoUsuario" required>
            </div>
            <div class="form-group">
                <label for="password">Contraseña:</label>
                <input type="password" id="password" name="password" required>
            </div>
            <div class="form-group">
                <input type="submit" value="Crear">
            </div>
        </form>
    </div>
</body>
</html>