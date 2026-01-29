<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@page import="entities.Usuario"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <link rel="stylesheet" href="style/vistaClienteStyles.css" />
    <% Usuario u = (Usuario)session.getAttribute("usuario"); %>
    <title>Vista Usuario</title>
  </head>
  <body>
    <div class="super-container">
      <h1>Bienvenido <%= u.getNombre() + " " + u.getApellido() %></h1>
      <div class="admin-container">
        <form action="misRutinas" method="get">
          <input type="hidden" name="id" value="<%= u.getIdUsuario() %>" />
          <input type="submit" class="admin-btn" value="Mis Rutinas" />
        </form>
        <form action="misEntrenamientos" method="post">
          <input type="hidden" name="id" value="<%= u.getIdUsuario() %>" />
          <input type="submit" class="admin-btn" value="Mis Entrenamientos" />
        </form>
        <form action="misMedidas" method="post">
          <input
            type="hidden"
            name="id_usuario"
            value="<%= u.getIdUsuario() %>"
          />
          <input type="submit" class="admin-btn" value="Mis Medidas" />
        </form>
        <form action="agendarConsulta" method="post">
          <input type="submit" class="admin-btn" value="Agendar Consulta" />
        </form>
        <form action="verRecetasRecomendadas" method="post">
          <input type="hidden" name="idUsuario" value="<%=u.getIdUsuario()%>" />
          <input type="submit" class="admin-btn" value="Recetas Recomendadas" />
        </form>
      </div>
    </div>
  </body>
</html>
