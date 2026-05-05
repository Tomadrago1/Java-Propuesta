<%@page import="java.util.LinkedList" %>
  <%@page import="entities.Usuario" %>
    <%@page import="entities.Profesional" %>
      <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
        <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
        <html>

        <head>
          <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
          <meta charset="utf-8">
          <meta http-equiv="X-UA-Compatible" content="IE=edge">
          <meta name="viewport" content="width=device-width, initial-scale=1">
          <link rel="stylesheet" href="style/UserManagementstyles.css">

          <% LinkedList<Profesional> lp = (LinkedList<Profesional>)request.getAttribute("profesionales");%>

        </head>

        <body>
          <div class="container">
            <h1>Profesionales</h1>
            <div class="div-table">
              <table>
                <thead>
                  <tr>
                    <th></th>
                    <th>Nombre</th>
                    <th>Apellido</th>
                    <th>Email</th>
                    <th>Profesion</th>
                  </tr>
                </thead>
                <tbody>
                  <% for (Profesional prof : lp) { if (prof.getEstado()) { // Muestra solo si el estado es true (o 1) %>
                    <tr>
                      <td>
                        <form action="seleccionarProfesional" method="post">
                          <input type="hidden" name="id" value="<%=prof.getIdUsuario()%>">
                          <input type="submit" value="Seleccionar" class="action-btn edit-btn">
                        </form>
                      <td>
                        <%=prof.getNombre()%>
                      </td>
                      <td>
                        <%=prof.getApellido()%>
                      </td>
                      <td>
                        <%=prof.getEmail()%>
                      </td>
                      <td>
                        <%=prof.getProfesion()%>
                      </td>
                    </tr>
                    <% }} // Fin de la condición } %>
                </tbody>
              </table>
            </div>
        <% request.setAttribute("pageTitle", "Profesionales"); %>
        <jsp:include page="header.jsp" />
          </div>
        </body>

        </html>