<%@page import="java.util.LinkedList" %>
  <%@page import="java.util.Map" %>
    <%@page import="entities.Ingrediente" %>
      <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
        <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
        <html>

        <head>
          <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
          <meta charset="utf-8">
          <meta http-equiv="X-UA-Compatible" content="IE=edge">
          <meta name="viewport" content="width=device-width, initial-scale=1">
          <link rel="stylesheet" href="style/UserManagementstyles.css">

          <% Ingrediente i=(Ingrediente)request.getAttribute("Ingrediente"); LinkedList<Map<String, Object>> ln =
            (LinkedList<Map<String, Object>>)request.getAttribute("ListaNutrientes");%>
        </head>

        <body>
          <div class="container">
            <h1>
              <%out.print(i.getNombre());%>
            </h1>
            <div>
              <table>
                <thead>
                  <tr>
                    <th>Nombre</th>
                    <th>Descripcion</th>
                    <th>Cantidad cada 100 gramos</th>
                  </tr>
                </thead>
                <tbody>
                  <% for (Map<String, Object> nutriente : ln) { %>
                    <tr>
                      <td>
                        <%= nutriente.get("nombre") %>
                      </td>
                      <td>
                        <%= nutriente.get("descripcion") %>
                      </td>
                      <td>
                        <%= ((Double) nutriente.get("cantidad") % 1==0) ? String.valueOf(((Double)
                          nutriente.get("cantidad")).intValue()) : nutriente.get("cantidad") %> gramos
                      </td>
                    </tr>
                    <% } %>
                </tbody>
              </table>
            </div>
            <a href="index.html" style="color: red">Volver</a>
          </div>
        </body>

        </html>