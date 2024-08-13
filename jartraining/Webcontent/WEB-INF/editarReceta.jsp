<%@page import="entities.Receta"%>
<%@page import="entities.Profesional"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="style/editarUsuarioStyles.css">
    <title>Editar Receta</title>
</head>
<body>
<%
    Receta receta = (Receta)request.getAttribute("receta");
    Profesional profesional = receta.getProfesional();  // Obtiene el profesional asociado, si existe
%>
    <div class="container">
        <h1>Editar Receta</h1>
        <form action="actualizarReceta" method="post">
            <input type="hidden" name="id" value="<%=receta.getId()%>">
            <div class="form-group">
                <label for="nombre">Nombre:</label>
                <input type="text" id="nombre" name="nombre" value="<%=receta.getNombre()%>">
            </div>
            <div class="form-group">
                <label for="desc">Descripción:</label>
                <input type="text" id="desc" name="desc" value="<%=receta.getDesc()%>">
            </div>
            <div class="form-group">
                <label for="nivelDificultad">Nivel de Dificultad:</label>
                <select id="nivelDificultad" name="nivelDificultad">
                    <option value="BAJO" <%= "BAJO".equals(receta.getNivelDificultad()) ? "selected" : "" %>>BAJO</option>
                    <option value="MEDIO" <%= "MEDIO".equals(receta.getNivelDificultad()) ? "selected" : "" %>>MEDIO</option>
                    <option value="ALTO" <%= "ALTO".equals(receta.getNivelDificultad()) ? "selected" : "" %>>ALTO</option>
                </select>
            </div>
            <div class="form-group">
                <input type="submit" value="Actualizar">
            </div>
        </form>
    </div>
</body>
</html>
