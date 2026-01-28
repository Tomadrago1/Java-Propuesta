<%@page import="java.util.LinkedList" %>
  <%@page import="entities.*" %>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
      <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
      <html>

      <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="style/UserManagementstyles.css">
      </head>

      <body>
        <div class="container">
          <% Ingrediente i=(Ingrediente)request.getAttribute("Ingrediente"); 
          LinkedList<NutrienteIngrediente> ln = (LinkedList<NutrienteIngrediente>) request.getAttribute("ListaNutrientes");
              %>

              <h1>
                <%= i.getNombre() %>
              </h1>

              <div>
                <table>
                  <thead>
                    <tr>
                      <th>Nombre</th>
                      <th>Descripción</th>
                      <th>Cantidad cada 100 gramos</th>
                    </tr>
                  </thead>
                  <tbody>
                    <%for (NutrienteIngrediente nutriente : ln) { %>
                      <tr>
                        <td>
                          <%= nutriente.getNutriente().getNombre() %>
                        </td>
                        <td>
                          <%= nutriente.getNutriente().getDescripcion() %>
                        </td>
                        <td>
                          <%= (nutriente.getCantidad() % 1==0) ? String.valueOf((int) nutriente.getCantidad()) :
                            nutriente.getCantidad() %> gramos
                        </td>
                      </tr>
                      <% } %>
                  </tbody>
                </table>
              </div>

          <% request.setAttribute("pageTitle", "Ingrediente Recomendado"); %>
          <jsp:include page="header.jsp" />
        </div>
      </body>

      </html>