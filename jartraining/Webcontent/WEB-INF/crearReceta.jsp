<%@page import="entities.Receta"%>
<%@page import="entities.Profesional"%>
<%@page import="java.util.LinkedList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="style/crearUsuarioStyles.css">
    <title>Crear Receta</title>
</head>
<body>
    <div class="container">
        <h1>Crear Receta</h1>
        <form action="guardarReceta" method="post">
            <div class="form-group">
                <label for="nombre">Nombre:</label>
                <input type="text" id="nombre" name="nombre" required>
            </div>
            <div class="form-group">
                <label for="desc">Descripción:</label>
                <textarea id="desc" name="desc" rows="4" required></textarea>
            </div>
            <div class="form-group">
                <label for="nivelDificultad">Nivel de Dificultad:</label>
                <select id="nivelDificultad" name="nivelDificultad" required>
                    <option value="BAJO">BAJO</option>
                    <option value="MEDIO">MEDIO</option>
                    <option value="ALTO">ALTO</option>
                </select>
            </div>
            <div class="form-group">
                <input type="submit" value="Crear">
            </div>
        </form>
    </div>
</body>
</html>
