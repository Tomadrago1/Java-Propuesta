<%@page import="entities.Ingrediente"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="style/editarUsuarioStyles.css">
    <title>Ingrediente</title>
<%
    Integer idReceta = null;
    String actionUrl = "guardarIngredienteNuevo"; // Valor por defecto

    try {
        idReceta = (Integer) request.getAttribute("idReceta");
        if (idReceta != null) {
            actionUrl = "guardarIngredienteNuevoReceta"; // Cambia la URL si idReceta no es null
        }
    } catch (ClassCastException e) {
        // Maneja la excepción si la conversión falla, idReceta se queda como null
    }
%>
</head>
<body>
    <div class="container">
        <h1>Crear Ingrediente</h1>
        <form action="<%= actionUrl %>" method="post">
            <input type="hidden" name="idReceta" value="<%= (idReceta != null) ? idReceta : "" %>">
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
        <a href="index.html" style="color: red">Volver</a>
    </div>
</body>
</html>