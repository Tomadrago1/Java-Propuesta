<%@page import="java.util.LinkedList" %>
  <%@page import="java.util.Map" %>
    <%@page import="entities.Receta" %>
      <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
        <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
        <html>

        <head>
          <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
          <meta charset="utf-8">
          <meta http-equiv="X-UA-Compatible" content="IE=edge">
          <meta name="viewport" content="width=device-width, initial-scale=1">
          <link rel="stylesheet" href="style/UserManagementstyles.css">

          <% Receta r=(Receta)request.getAttribute("Receta"); LinkedList<Map<String, Object>> li = (LinkedList
            <Map<String, Object>>)request.getAttribute("ListaIngredientes");
              %>

        </head>

        <body>
          <div class="container">
            <h1>
              <%out.print(r.getNombre());%>
            </h1>
            <div>
              <table>
                <thead>
                  <tr>
                    <th></th>
                    <th>Nombre</th>
                    <th>Descripcion</th>
                    <th>Cantidad</th>
                  </tr>
                </thead>
                <tbody>
                  <% for (Map<String, Object> ingrediente : li) { %>
                    <tr>
                      <td>
                        <form action="verMasIngredienteRecomendado" method="post" style="display:inline;">
                          <input type="hidden" name="id" value="<%=ingrediente.get("id")%>">
                          <input type="submit" value="Ver Más" class="action-btn edit-btn">
                        </form>
                      </td>
                      <td>
                        <%= ingrediente.get("nombre") %>
                      </td>
                      <td>
                        <%= ingrediente.get("descripcion") %>
                      </td>
                      <td>
                        <%= ((Double) ingrediente.get("cantidad") % 1==0) ? String.valueOf(((Double)
                          ingrediente.get("cantidad")).intValue()) : ingrediente.get("cantidad") %>
                          <%= ingrediente.get("unidad") %>
                      </td>
                    </tr>
                    <% } %>
                </tbody>
              </table>
              <div style="text-align: right; margin-top: 20px; margin-bottom:20px; padding: 0 20px;">
                <form action="verMacrosReceta" method="post" style="display:inline;">
                  <input type="hidden" name="idReceta" value="<%=r.getId()%>">
                  <input type="submit" value="Ver macronutrientes" class="action-btn create-btn">
                </form>
            </div>
            <a href="index.html" style="color: red">Volver</a>
          </div>
        </body>

        </html>