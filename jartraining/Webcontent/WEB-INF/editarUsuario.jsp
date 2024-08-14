<%@page import="entities.Usuario"%>
<%@page import="data.DaoUsuario"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="style/editarUsuarioStyles.css">
    <title>Editar Usuario</title>
	
</head>
<body>
<%
    Usuario usuario = (Usuario)request.getAttribute("usuario");
%>
    <div class="container">
        <h1>Editar Usuario</h1>
        <form action="actualizarUsuario" method="post">
            <input type="hidden" name="id" value="<%=usuario.getIdUsuario()%>">
            <div class="form-group">
                <label for="nombre">Nombre:</label>
                <input type="text" id="nombre" name="nombre" value="<%=usuario.getNombre()%>">
            </div>
            <div class="form-group">
                <label for="apellido">Apellido:</label>
                <input type="text" id="apellido" name="apellido" value="<%=usuario.getApellido()%>">
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="text" id="email" name="email" value="<%=usuario.getEmail()%>">
            </div>
            <div class="form-group">
                <label for="nombreUsuario">Nombre de Usuario:</label>
                <input type="text" id="nombreUsuario" name="nombreUsuario" value="<%=usuario.getNombreUsuario()%>">
            </div>
            <div class="form-group">
                <label for="nombreUsuario">Tipo de usuario</label>
                <input type="text" id="tipoUsuario" name="tipoUsuario" value="<%=usuario.getTipoUsu()%>">
            </div>
            <div class="form-group">
                <input type="submit" value="Actualizar">
            </div>
        </form>
    </div>
</body>
</html>