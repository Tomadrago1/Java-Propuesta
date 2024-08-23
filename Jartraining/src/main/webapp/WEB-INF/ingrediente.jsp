<<<<<<< HEAD
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

          <% Ingrediente i=(Ingrediente)request.getAttribute("Ingrediente"); 
          LinkedList<Map<String, Object>> ln = (LinkedList<Map<String, Object>>)request.getAttribute("ListaNutrientes");%>
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
                    <th>Editar Cantidad</th>
                    <th>Baja Nutriente</th>
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
                        <%= ((Double) nutriente.get("cantidad") % 1 == 0) ? String.valueOf(((Double) nutriente.get("cantidad")).intValue()) : nutriente.get("cantidad") %> gramos
                      </td>
                      <td>
                        <form action="editarCantidadNutriente" method="post" style="display:inline;">
                          <input type="hidden" name="cantidad" value="<%=nutriente.get("cantidad")%>">
                          <input type="hidden" name="idNutriente" value="<%=nutriente.get("id")%>">
                          <input type="hidden" name="idIngrediente" value="<%=i.getId()%>">
                          <input type="hidden" name="unidadMedida" value="<%=nutriente.get("unidad_medida")%>">
                          <input type="submit" value="Editar" class="action-btn edit-btn">
                        </form>
                      <td>
                        <form action="eliminarNutrienteIngrediente" method="post" style="display:inline;">
                          <input type="hidden" name="idNutriente" value="<%= nutriente.get("id")%>">
                          <input type="hidden" name="idIngrediente" value="<%=i.getId()%>">
                          <input type="submit" value="Borrar" class="action-btn delete-btn"
                            onclick="return confirm('¿Estás seguro de que deseas eliminar este nutriente?');">
                        </form>
                      </td>
                    </tr>
                    <% } %>
                </tbody>
              </table>
              <div style="text-align: right; margin-top: 20px; margin-bottom:20px; padding: 0 20px;">
                <form action="agregarNutriente" method="post" style="display:inline;">
                  <input type="hidden" name="idIngrediente" value="<%=i.getId()%>">
                  <input type="submit" value="Agregar Nutriente" class="action-btn create-btn">
                </form>
              </div>
            </div>
            <a href="index.html" style="color: red">Volver</a>
          </div>
        </body>

=======
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

          <% Ingrediente i=(Ingrediente)request.getAttribute("Ingrediente"); 
          LinkedList<Map<String, Object>> ln = (LinkedList<Map<String, Object>>)request.getAttribute("ListaNutrientes");%>
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
                    <th>Editar Cantidad</th>
                    <th>Baja Nutriente</th>
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
                        <%= ((Double) nutriente.get("cantidad") % 1 == 0) ? String.valueOf(((Double) nutriente.get("cantidad")).intValue()) : nutriente.get("cantidad") %> gramos
                      </td>
                      <td>
                        <form action="editarCantidadNutriente" method="post" style="display:inline;">
                          <input type="hidden" name="cantidad" value="<%=nutriente.get("cantidad")%>">
                          <input type="hidden" name="idNutriente" value="<%=nutriente.get("id")%>">
                          <input type="hidden" name="idIngrediente" value="<%=i.getId()%>">
                          <input type="hidden" name="unidadMedida" value="<%=nutriente.get("unidad_medida")%>">
                          <input type="submit" value="Editar" class="action-btn edit-btn">
                        </form>
                      <td>
                        <form action="eliminarNutrienteIngrediente" method="post" style="display:inline;">
                          <input type="hidden" name="idNutriente" value="<%= nutriente.get("id")%>">
                          <input type="hidden" name="idIngrediente" value="<%=i.getId()%>">
                          <input type="submit" value="Borrar" class="action-btn delete-btn"
                            onclick="return confirm('¿Estás seguro de que deseas eliminar este nutriente?');">
                        </form>
                      </td>
                    </tr>
                    <% } %>
                </tbody>
              </table>
              <div style="text-align: right; margin-top: 20px; margin-bottom:20px; padding: 0 20px;">
                <form action="agregarNutriente" method="post" style="display:inline;">
                  <input type="hidden" name="idIngrediente" value="<%=i.getId()%>">
                  <input type="submit" value="Agregar Nutriente" class="action-btn create-btn">
                </form>
              </div>
            </div>
            <a href="index.html" style="color: red">Volver</a>
          </div>
        </body>

>>>>>>> 2a7a2cddb7c46cdba58517d715cf07559706d21c
        </html>