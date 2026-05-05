<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="entities.Usuario"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Vista Profesional</title>
    <link rel="stylesheet" href="style/vistaClienteStyles.css">
    <%
    Usuario u = (Usuario)session.getAttribute("usuario");
    %>
</head>
<body>
    <div class="logo-container">
      <img alt="Logo" src="style/JarTrainingLogo.png" style="display: block; margin: 0 auto; max-width: 200px; padding-top: 20px;" />
    </div>
    <div class="super-container">
        <h1>Bienvenido <%= u.getNombre() + " " + u.getApellido() %></h1>
        <div class="admin-container">
            <form action="gestionarDisponibilidad" method="get">
                <input type="submit" class="admin-btn" value="Gestionar Disponibilidad" />
            </form>

            <form action="verMiAgenda" method="get">
                <input type="submit" class="admin-btn" value="Mi Agenda" />
            </form>

            <form action="crearRecetaProfesional" method="post">
                <input type="hidden" name="id_profesional" value="<%=u.getIdUsuario()%>">
                <input type="submit" class="admin-btn" value="Crear Receta" />
            </form>
            
            <form action="misRecetas" method="post">
                <input type="hidden" name="idProfesional" value="<%= u.getIdUsuario() %>">
                <input type="submit" class="admin-btn" value="Mis Recetas" />
            </form>
            
            <form action="logout" method="post" style="margin-top: 20px">
                <input type="submit" class="admin-btn" value="Cerrar Sesión" style="background: linear-gradient(135deg, #dc2626 0%, #991b1b 100%);" />
            </form>
        </div>
    </div>
</body>
</html>