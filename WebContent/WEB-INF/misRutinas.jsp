<%@page import="java.util.LinkedList" %>
<%@ page import="entities.Usuario" %>
  <%@page import="entities.Rutina" %>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
      <!DOCTYPE html>
      <html lang="es">

      <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="style/UserManagementstyles.css">
        <title>Mis Rutinas</title>

        <% LinkedList<Rutina> lr = (LinkedList<Rutina>) request.getAttribute("rutinas");
            Usuario u = (Usuario)session.getAttribute("usuario"); %>
      </head>

      <body>
        <div class="container">
          <h1>Mis Rutinas </h1>
          <div class="table-container">
            <table>
              <thead>
                <tr>
                  <th></th>
                  <th>Nombre</th>
                  <th>Descripción</th>
                  <th>Eliminar Rutina</th>
                </tr>
              </thead>
              <tbody>
                <% for (Rutina rut : lr) {%>
                  <tr>
                    <td>
                      <form action="iniciarEntrenamiento" method="post" class="inline-form">
                        <input type="hidden" name="id_rut" value="<%=rut.getId()%>">
                        <input type="hidden" name="id_usu" value="<%=u.getIdUsuario()%>">
                        <input type="submit" value="IniciarEntrenamiento" class="action-btn viewmore-btn">
                      </form>
                    </td>
                    <td>
                      <%=rut.getNombre()%>
                    </td>
                    <td>
                      <%=rut.getDescripcion()%>
                    </td>
                    <td class="actions">
                      <form action="eliminarRutinaUsuario" method="post" class="inline-form">
                        <input type="hidden" name="id_rut" value="<%=rut.getId()%>">
                        <input type="hidden" name="id_usu" value="<%=u.getIdUsuario()%>">
                        <input type="submit" value="Borrar" class="action-btn delete-btn"
                          onclick="return confirm('¿Estás seguro de que deseas eliminar este rutina?');">
                      </form>
                    </td>
                  </tr>
                  <%}%>
              </tbody>
            </table>
            <div class="container-create">
              <form action="crearRutinaUsuario" method="post" style="display:inline;">
                <input type="hidden" name="id_usu" value="<%=u.getIdUsuario()%>">
                <input type="submit" value="Agregar Rutina" class="action-btn create-btn">
              </form>
            </div>
            <a href="index.html" style="color: red">Volver</a>
          </div>
        </div>
      </body>

      </html>