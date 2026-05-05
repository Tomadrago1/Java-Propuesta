<%@page import="java.util.LinkedList" %>
  <%@page import="entities.Profesional" %>
  <%@page import="entities.Profesion" %>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
      <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
      <html>

      <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="style/global.css">
        <link rel="stylesheet" href="style/UserManagementstyles.css">

        <%
            LinkedList<Profesional> lp = (LinkedList<Profesional>)request.getAttribute("profesionales");
            LinkedList<Profesion> listaProfesiones = (LinkedList<Profesion>)request.getAttribute("listaProfesiones");
            Integer idProfesionSeleccionada = (Integer)request.getAttribute("idProfesionSeleccionada");
        %>

      </head>

      <body>
        <div class="container">
          <h1>Agendar Consulta</h1>
          
          <div style="margin-bottom: 20px;">
              <form action="agendarConsulta" method="post">
                  <label for="id_profesion" style="font-weight: bold;">Seleccione una profesión:</label>
                  <select name="id_profesion" id="id_profesion" style="padding: 5px; margin-right: 10px; width: 250px;">
                      <option value="">-- Elegir Profesión --</option>
                      <% 
                          if (listaProfesiones != null) {
                              for (Profesion prof : listaProfesiones) {
                                  String selected = (idProfesionSeleccionada != null && idProfesionSeleccionada == prof.getId_profesion()) ? "selected" : "";
                      %>
                      <option value="<%=prof.getId_profesion()%>" <%=selected%>><%=prof.getNombre()%></option>
                      <% 
                              }
                          }
                      %>
                  </select>
                  <input type="submit" value="Buscar Profesionales" class="action-btn create-btn">
              </form>
          </div>

          <% if (idProfesionSeleccionada != null && (lp == null || lp.isEmpty())) { %>
              <div style="padding: 15px; background-color: #f8d7da; color: #721c24; border-radius: 5px; margin-bottom: 20px;">
                  No hay profesionales disponibles para la profesión elegida.
              </div>
          <% } else if (lp != null && !lp.isEmpty()) { %>
          <div class="div-table">
            <table>
              <thead>
                <tr>
                  <th></th>
                  <th>Nombre</th>
                  <th>Apellido</th>
                  <th>Profesión</th>
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
                    </td>
                    <td>
                      <%=prof.getNombre()%>
                    </td>
                    <td>
                      <%=prof.getApellido()%>
                    </td>
                    <td>
                      <%=prof.getProfesion()%>
                    </td>
                  </tr>
                  <%}} %>
              </tbody>
            </table>
          </div>
          <% } %>
        <% request.setAttribute("pageTitle", "Agendar Consulta"); %>
        <jsp:include page="header.jsp" />
        </div>
      </body>

      </html>