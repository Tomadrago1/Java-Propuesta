<%@page import="entities.Profesion"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="style/editarUsuarioStyles.css">
    <title>Editar Profesión</title>
</head>
<body>
<%
    Profesion profesion = (Profesion)request.getAttribute("Profesion");
%>
    <div class="container">
        <h1>Editar Profesión</h1>
        <form action="guardarProfesion" method="post">
            <input type="hidden" name="id" value="<%=profesion.getId_profesion()%>">
            
            <div class="form-group">
                <label for="nombre">Nombre:</label>
                <input type="text" id="nombre" name="nombre" value="<%=profesion.getNombre()%>" required>
            </div>
            
            <div class="form-group">
                <input type="submit" value="Actualizar">
            </div>
        </form>
        <div class="form-group" style="text-align: center; margin-top: 10px;">
            <form action="verProfesiones" method="get">
                <input type="submit" value="Volver" style="background-color: #6c757d;">
            </form>
        </div>
    <% request.setAttribute("pageTitle", "Editar Profesión"); %>
    <jsp:include page="header.jsp" />
    </div>
</body>
</html>
