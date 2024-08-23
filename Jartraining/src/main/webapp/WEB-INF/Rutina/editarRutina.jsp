<%@page import="entities.Rutina"%>
<%@page import="data.DaoRutina"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="style/crear-editar.css">
    <title>Editar Rutina</title>
	
</head>
<body>
<%
	//Aca se cambian los atributos del rutina y se mandan los nuevos atributos al servlet actualizarRutina
    Rutina rut = (Rutina)request.getAttribute("rutina");
%>
    <div class="container">
        <h1>Editar Rutina</h1>
        <form action="actualizarRutina" method="post">
            <input type="hidden" name="id" value="<%=rut.getId()%>">
            <div class="form-group">
                <label for="nombre">Nombre:</label>
                <input type="text" id="nombre" name="nombre" value="<%=rut.getNombre()%>">
            </div>
            <div class="form-group">
                <label for="apellido">Descripcion:</label>
                <input type="text" id="descripcion" name="descripcion" value="<%=rut.getDescripcion()%>">
            </div>
            <div class="form-group">
                <input type="submit" value="Actualizar">
            </div>
        </form>
        <a href="index.html" style="color: red">Volver</a>
    </div>
</body>
</html>