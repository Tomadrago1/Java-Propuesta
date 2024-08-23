<%@page import="java.util.LinkedList" %>
  <%@page import="entities.Nutriente" %>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
      <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
      <html>

      <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="style/UserManagementstyles.css">

        <% LinkedList<Nutriente> ln = (LinkedList<Nutriente>)request.getAttribute("nutrientes");
            %>

      </head>

      <body>
        <div class="container">
          <h1>Nutrientes</h1>
          <div>
            <table>
              <thead>
                <tr>
                  <th>Nombre</th>
                  <th>Descripción</th>
                  <th>Editar Nutriente</th>
                  <th>Baja Nutriente</th>
                </tr>
              </thead>
              <tbody>
                <% for (Nutriente nut : ln) { %>
                  <tr>
                    <td>
                      <%=nut.getNombre()%>
                    </td>
                    <td>
                      <%=nut.getDescripcion()%>
                    </td>
                    <td>
                      <form action="editarNutriente" method="post" style="display:inline;">
                        <input type="hidden" name="idNutriente" value="<%=nut.getId_nutriente()%>">
                        <input type="submit" value="Editar" class="action-btn edit-btn">
                      </form>
                    </td>
                    <td>
                      <form action="eliminarNutriente" method="post" style="display:inline;">
                        <input type="hidden" name="idNutriente" value="<%=nut.getId_nutriente()%>">
                        <input type="submit" value="Borrar" class="action-btn delete-btn"
                          onclick="return confirm('¿Estás seguro de que deseas borrar este nutriente?');">
                      </form>
                    </td>
                  </tr>
                  <% } %>
              </tbody>
            </table>
            <div style="text-align: right; margin-top: 20px; margin-bottom:20px; padding: 0 20px;">
              <form action="crearNutriente" method="post" style="display:inline;">
                <input type="submit" value="Crear Nutriente" class="action-btn create-btn">
              </form>
            </div>
          </div>
          <a href="index.html" style="color: red">Volver</a>
        </div>
      </body>

      </html>