<%@page import="java.util.LinkedList" %>
  <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <html>

    <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <meta charset="utf-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <link rel="stylesheet" href="style/UserManagementstyles.css">

      <% LinkedList<String> lp = (LinkedList<String>) request.getAttribute("profesiones"); %>

    </head>

    <body>
      <div class="container">
        <h1>Listado de profesiones</h1>
        <div>
          <table>
            <thead>
              <tr>
                <th></th>
                <th>Profesion</th>
              </tr>
            </thead>
            <tbody>
              <% for (String profesion : lp) { %>
                <tr>
                  <td>
                    <form action="seleccionarProfesion" method="post">
                      <input type="hidden" name="profesion" value="<%=profesion%>">
                      <input type="submit" value="Seleccionar" class="action-btn edit-btn">
                    </form>
                  </td>
                  <td>
                    <%= profesion %>
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