<%@page import="entities.Ingrediente"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="style/editarUsuarioStyles.css">
    <title>Ingrediente</title>
    <% int idReceta = (int)request.getAttribute("idReceta");%>
</head>
<body>
    <div class="container">
        <h1>Crear Ingrediente</h1>
        <form action="guardarIngredienteNuevoReceta" method="post">
        	<input type="hidden" name="idReceta" value="<%=idReceta%>">
            <div class="form-group">
                <label for="nombre">Nombre:</label>
                <input type="text" id="nombre" name="nombre">
            </div>
            <div class="form-group">
                <label for="descripcion">Descripción:</label>
                <input type="text" id="descripcion" name="descripcion">
            </div>
            <div class="form-group">
                <input type="submit" value="Crear">
            </div>
        </form>
    </div>
</body>
</html>