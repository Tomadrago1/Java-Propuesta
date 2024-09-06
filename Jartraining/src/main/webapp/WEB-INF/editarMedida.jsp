<%@page import="entities.Medida" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <link rel="stylesheet" href="style/crear-editar.css">
  <% Medida medida=(Medida) request.getAttribute("medida"); %>
    <title>Editar Medida</title>
</head>

<body>
  <div class="container">
    <h1>Editar Medida</h1>
    <form action="actualizarMedida" method="post">
      <input type="hidden" name="id_usuario" value="<%= medida.getId_usuario() %>">

      <div class="form-group">
        <label for="peso">Peso:</label>
        <input type="text" id="peso" name="peso" value="<%= medida.getPeso() %>" required>
      </div>

      <div class="form-group">
        <label for="altura">Altura:</label>
        <input type="text" id="altura" name="altura" value="<%= medida.getAltura() %>" required>
      </div>

      <div class="form-group">
        <label for="fecha">Fecha:</label>
        <input type="date" id="fecha" name="fecha" value="<%= medida.getFecha() %>" readonly>
      </div>

      <div class="form-group">
        <input type="submit" value="Actualizar">
      </div>
    </form>
  </div>
</body>

</html>