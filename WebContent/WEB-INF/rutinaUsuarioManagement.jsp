<%@page import="java.util.LinkedList" %>
  <%@page import="entities.Rutina" %>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
      <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
      <html>

      <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="style/UserManagementstyles.css">

        <% LinkedList<Rutina> lr = (LinkedList<Rutina>)request.getAttribute("rutinas");
            int id_usu = (int)request.getAttribute("id_usu");

            %>

      </head>

      <body>
        <div class="container">
          <h1>Rutinas</h1>
          <div>
            <table>
              <thead>
                <tr>
                  <th>Nombre</th>
                  <th>Descripción</th>
                  <th>Agregar Rutina</th>
                </tr>
              </thead>
              <tbody>
                <% for (Rutina r : lr) { %>
                  <tr>
                    <td>
                      <%=r.getNombre()%>
                    </td>
                    <td>
                      <%=r.getDescripcion()%>
                    </td>
                    <td>
                      <form action="agregarRutinaUsuario" method="post" style="display:inline;">
                        <input type="hidden" name="id_rut" value="<%=r.getId()%>">
                        <input type="hidden" name="id_usu" value="<%=id_usu%>">
                        <input type="submit" value="Agregar" class="action-btn create-btn"
                          onclick="return confirm('¿Estás seguro de que deseas agregar esta?');">
                      </form>
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