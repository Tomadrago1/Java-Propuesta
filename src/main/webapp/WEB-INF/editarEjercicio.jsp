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
	//Aca se cambian los atributos del ejercicio y se mandan los nuevos atributos al servlet actualizarEjercicio
    Ejercicio eje = (Ejercicio)request.getAttribute("ejercicio");
%>
    <div class="container">
        <h1>Editar Ejercicio</h1>
        <form action="actualizarEjercicio" method="post">
            <input type="hidden" name="id" value="<%=eje.getId()%>">
            <div class="form-group">
                <label for="nombre">Nombre:</label>
                <input type="text" id="nombre" name="nombre" value="<%=eje.getNombre()%>">
            </div>
            <div class="form-group">
                <label for="apellido">Descripcion:</label>
                <input type="text" id="descripcion" name="descripcion" value="<%=eje.getDescripcion()%>">
            </div>
            <div class="form-group">
                <input type="submit" value="Actualizar">
            </div>
        </form>
        <a href="index.html" style="color: red">Volver</a>
    </div>
</body>
</html>