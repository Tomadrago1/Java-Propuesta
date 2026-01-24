<%@page import="entities.Ingrediente" %>
  <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
      <meta charset="UTF-8">
      <link rel="stylesheet" href="style/editarUsuarioStyles.css">
      <title>Editar Ingrediente</title>

    </head>

    <body>
      <% Ingrediente ingrediente=(Ingrediente)request.getAttribute("ingrediente"); %>
        <div class="container">
          <h1>Editar Ingrediente</h1>
          <form action="actualizarIngrediente" method="post">
            <input type="hidden" name="id" value="<%=ingrediente.getId()%>">
            <div class="form-group">
              <label for="nombre">Nombre:</label>
              <input type="text" id="nombre" name="nombre" value="<%=ingrediente.getNombre()%>">
            </div>
            <div class="form-group">
              <label for="apellido">Descripcion:</label>
              <input type="text" id="desc" name="descripcion" value="<%=ingrediente.getDesc()%>">
            </div>
            <div class="form-group">
              <input type="submit" value="Actualizar">
            </div>
          </form>
          <a href="index.html" style="color: red">Volver</a>
        </div>
    </body>

    </html>