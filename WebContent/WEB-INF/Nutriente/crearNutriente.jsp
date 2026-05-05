<%@page import="entities.Nutriente" %>
  <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
      <meta charset="UTF-8">
      <link rel="stylesheet" href="style/editarUsuarioStyles.css">
      <title>Nutriente</title>
      <% Integer idIngrediente=null; 
        String actionUrl="guardarNutrienteNuevo" ; // Valor por defecto 
        try {
          idIngrediente=(Integer) request.getAttribute("idIngrediente"); 
          if (idIngrediente !=null) {
            actionUrl="guardarNutrienteNuevoIngrediente" ;} // Cambia la URL si idIngrediente no es null } 
        }catch (ClassCastException e) {} %>
    </head>

    <body>
      <div class="container">
        <h1>Crear Nutriente</h1>
        <form action="<%= actionUrl %>" method="post">
          <input type="hidden" name="idIngrediente" value="<%= (idIngrediente != null) ? idIngrediente : "" %>">
          <div class="form-group">
            <label for="nombre">Nombre:</label>
            <input type="text" id="nombre" name="nombre">
          </div>
          <div class="form-group">
            <label for="descripcion">Descripción:</label>
            <input type="text" id="descripcion" name="descripcion">
          </div>
          <div class="form-group">
            <input type="submit" value="Crear">
          </div>
        </form>
      <% request.setAttribute("pageTitle", "Crear Nutriente"); %>
      <jsp:include page="../header.jsp" />
      </div>
    </body>

    </html>