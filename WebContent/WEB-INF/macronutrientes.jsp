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

          <% Receta r=(Receta)request.getAttribute("Receta"); 
          LinkedList<NutrienteReceta> ln = (LinkedList<NutrienteReceta>)request.getAttribute("ListaNutrientes");%>
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
                    <th>Nombre</th>
                    <th>Descripcion</th>
                    <th>Cantidad</th>
                  </tr>
                </thead>
                <tbody>
                  <% for (NutrienteReceta nr : ln) { %>
                    <tr>
                      <td>
                        <%= nr.getNutriente().getNombre() %>
                      </td>
                      <td>
                        <%= nr.getNutriente().getDescripcion() %>
                      </td>
                      <td>
                        <%= ((Double) nr.getCantidad() % 1==0) ? String.valueOf(((Double)
                          nr.getCantidad()).intValue()) : nr.getCantidad() %> gramos
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