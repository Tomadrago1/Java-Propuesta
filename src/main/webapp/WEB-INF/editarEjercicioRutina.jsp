<%@page import="entities.Ejercicio"%>
<%@page import="data.DaoEjercicio"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="style/crear-editar.css">
    <title>Editar Ejercicio</title>
	
</head>
<body>
<%
    int id_eje = (int)request.getAttribute("id_eje");
    int id_rut = (int)request.getAttribute("id_rut");
    Integer series = null;
    Integer repes = null;
    try {
    	series = (int)request.getAttribute("series");
        repes = (int)request.getAttribute("repes");
    } catch(Exception e) {}
%>
    <div class="container">
        <h1>Editar Series y Repeticiones</h1>
        <form action="actualizarEjercicioRutina" method="post">
            <input type="hidden" name="id_eje" value="<%=id_eje%>">
            <input type="hidden" name="id_rut" value="<%=id_rut%>">
            <div class="form-group">
                <label for="nombre">Series:</label>
                <input type="text" id="series" name="series" value="<%=(series != null) ? series : ""%>">
            </div>
            <div class="form-group">
                <label for="apellido">Repeticiones:</label>
                <input type="text" id="repes" name="repes" value="<%=(repes != null) ? repes : ""%>">
            </div>
            <div class="form-group">
                <input type="submit" value="Actualizar">
            </div>
        </form>
        <a href="index.html" style="color: red">Volver</a>
    </div>
</body>
</html>