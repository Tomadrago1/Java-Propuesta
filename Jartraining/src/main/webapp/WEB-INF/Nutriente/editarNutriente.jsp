<%@page import="entities.Nutriente" %>
  <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
      <meta charset="UTF-8">
      <link rel="stylesheet" href="style/editarUsuarioStyles.css">
      <title>Editar Nutriente</title>

    </head>

    <body>
      <% Nutriente nutriente=(Nutriente)request.getAttribute("nutriente"); %>
        <div class="container">
          <h1>Editar Nutriente</h1>
          <form action="actualizarNutriente" method="post">
            <input type="hidden" name="id" value="<%=nutriente.getId_nutriente()%>">
            <div class="form-group">
              <label for="nombre">Nombre:</label>
              <input type="text" id="nombre" name="nombre" value="<%=nutriente.getNombre()%>">
            </div>
            <div class="form-group">
              <label for="apellido">Descripcion:</label>
              <input type="text" id="desc" name="descripcion" value="<%=nutriente.getDescripcion()%>">
            </div>
            <div class="form-group">
              <input type="submit" value="Actualizar">
            </div>
          </form>
          <a href="index.html" style="color: red">Volver</a>
        </div>
    </body>

    </html>