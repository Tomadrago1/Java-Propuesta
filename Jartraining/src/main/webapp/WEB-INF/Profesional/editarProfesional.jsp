<%@page import="entities.Profesional"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="style/editarUsuarioStyles.css">
    <script>
        function validatePassword() {
            var password = document.getElementById("password").value;
            var confirmPassword = document.getElementById("confirm_password").value;

            if (password !== confirmPassword) {
                document.getElementById("error-message").style.display = "block";
                return false;
            } else {
                document.getElementById("error-message").style.display = "none";
                return true;
            }
        }

        function toggleProfesionField() {
            var tipoUsuario = document.getElementById("tipoUsuario").value;
            var profesionField = document.getElementById("profesionField");

            if (tipoUsuario === "profesional") {
                profesionField.style.display = "block";
            } else {
                profesionField.style.display = "none";
            }
        }
    </script>
    <title>Editar Profesional</title>
	
</head>
<body>
<%
    Profesional profesional = (Profesional)request.getAttribute("profesional");
%>
    <div class="container">
        <h1>Editar Profesional</h1>
        <form action="actualizarProfesional" method="post">
            <input type="hidden" name="id" value="<%=profesional.getIdUsuario()%>">
            <div class="form-group">
                <label for="nombre">Nombre:</label>
                <input type="text" id="nombre" name="nombre" value="<%=profesional.getNombre()%>">
            </div>
            <div class="form-group">
                <label for="apellido">Apellido:</label>
                <input type="text" id="apellido" name="apellido" value="<%=profesional.getApellido()%>">
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="text" id="profesion" name="email" value="<%=profesional.getEmail()%>">
            </div>
            <div class="form-group">
                <label for="nombreUsuario">Nombre de Usuario:</label>
                <input type="text" id="nombreUsuario" name="nombreUsuario" value="<%=profesional.getNombreUsuario()%>">
            </div>
            <div class="form-group">
                <label for="email">Profesion:</label>
                <input type="text" id="profesion" name="profesion" value="<%=profesional.getProfesion()%>">
            </div>
            <div class="form-group">
                <label for="password">Contraseña:</label>
                <input type="password" id="password" name="password" required>
            </div>
            <div class="form-group">
                <label for="confirm_password">Repetir Contraseña:</label>
                <input type="password" id="confirm_password" name="confirm_password" required>
            </div>
            <div id="error-message" style="color: red; display: none;">Las contraseñas no coinciden.</div>
            <div class="form-group">
                <input type="submit" onclick="return validatePassword()" value="Actualizar"></input>
            </div>
        </form>
        <a href="index.html" style="color: red">Volver</a>
    </div>
</body>
</html>