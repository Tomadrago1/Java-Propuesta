<%@page import="entities.Medida"%> <%@ page language="java"
contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <link rel="stylesheet" href="style/crear-editar.css" />
    <% int id = (int)session.getAttribute("id_usuario"); %>
    <title>Crear Medida</title>
  </head>
  <body>
    <div class="container">
      <h1>Nueva Medida</h1>
      <form action="guardarMedida" method="post">
        <input type="hidden" name="id_usuario" value="<%=id%>" />
        <div class="form-group">
          <label for="peso">Peso (kg):</label>
          <input type="text" id="peso" name="peso" required />
        </div>
        <div class="form-group">
          <label for="altura">Altura (metros):</label>
          <input type="text" id="altura" name="altura" required />
        </div>
        <div class="form-group">
          <label for="email">Fecha:</label>
          <input type="Date" id="fecha" name="fecha" required />
        </div>
        <div class="form-group">
          <input type="submit" value="Crear" />
        </div>
      </form>
    </div>
  </body>
</html>
