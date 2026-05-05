<%@page import="java.util.LinkedList" %>
  <%@page import="entities.Receta" %>
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

          <% LinkedList<Receta> lr = (LinkedList<Receta>)request.getAttribute("listaRecetas");
              %>

        </head>

        <body>
          <div class="container">
            <h1>Recetas recomendadas</h1>
            <p style="color: #555; margin-bottom: 15px;">
              Estas recetas coinciden con las necesidades nutricionales que tu profesional cargó para vos.
            </p>
            <div>
              <% if (lr == null || lr.isEmpty()) { %>
                <div style="text-align: center; padding: 40px 20px; color: #888;">
                  <p style="font-size: 18px; margin-bottom: 10px;">No se encontraron recetas que coincidan con tus necesidades nutricionales.</p>
                  <p>Consultá con tu profesional para que cargue o actualice tus necesidades nutricionales.</p>
                </div>
              <% } else { %>
              <table>
                <thead>
                  <tr>
                    <th></th>
                    <th>Nombre</th>
                    <th>Descripcion</th>
                    <th>Nivel de dificultad</th>
                    <th>Profesional</th>
                  </tr>
                </thead>
                <tbody>
                  <% for (Receta rec : lr) { Profesional p=rec.getProfesional(); %>
                    <tr>
                      <td>
                        <form action="verMasRecetaRecomendada" method="post" style="display:inline;">
                          <input type="hidden" name="id" value="<%=rec.getId()%>">
                          <input type="submit" value="Ver Más" class="action-btn edit-btn">
                        </form>
                      </td>
                      <td>
                        <%=rec.getNombre()%>
                      </td>
                      <td>
                        <%=rec.getDesc()%>
                      </td>
                      <td>
                        <%=rec.getNivelDificultad()%>
                      </td>
                      <td>
                        <% if (p !=null) { out.print(p.getNombre() + " " + p.getApellido()); } else { out.print("Sin profesional"); } %>
                      </td>
                    </tr>
                    <% } // Fin del loop %>
                </tbody>
              </table>
              <% } %>
            </div>
            <div style="margin-top: 20px;">
              <a href="signin" onclick="history.back(); return false;" style="color: red">Volver</a>
            </div>
          </div>
        </body>

        </html>