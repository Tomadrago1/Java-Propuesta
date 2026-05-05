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
      
      <% 
         String mensajeExito = (String) session.getAttribute("mensajeExito");
         if (mensajeExito != null) {
      %>
         <div style="background-color: #d4edda; color: #155724; padding: 15px; margin: 10px 0; border: 1px solid #c3e6cb; border-radius: 5px; text-align: center;">
             <%= mensajeExito %>
         </div>
      <% 
             session.removeAttribute("mensajeExito");
         } 
      %>
      
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
        <form action="verHistorialNecesidades" method="get">
          <input type="submit" class="admin-btn" value="Mi Plan Nutricional" />
        </form>
        <form action="logout" method="post" style="margin-top: 20px">
          <input
            type="submit"
            class="admin-btn"
            value="Cerrar Sesión"
            style="
              background: linear-gradient(135deg, #dc2626 0%, #991b1b 100%);
            "
          />
        </form>
      </div>
    </div>
  </body>
</html>
