<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="style/editarUsuarioStyles.css">
    <title>Crear Profesión</title>
</head>
<body>
    <div class="container">
        <h1>Crear Nueva Profesión</h1>
        <form action="agregarProfesion" method="post">
            <div class="form-group">
                <label for="nombre">Nombre de la Profesión:</label>
                <input type="text" id="nombre" name="nombre" placeholder="Ingrese nombre" required>
            </div>
            
            <div class="form-group">
                <input type="submit" value="Crear Profesión">
            </div>
        </form>
        <div class="form-group" style="text-align: center; margin-top: 10px;">
            <form action="verProfesiones" method="get">
                <input type="submit" value="Volver" style="background-color: #6c757d;">
            </form>
        </div>
    <% request.setAttribute("pageTitle", "Crear Profesión"); %>
    <jsp:include page="header.jsp" />
    </div>
</body>
</html>
