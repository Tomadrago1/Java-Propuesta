<%@ page import="java.util.LinkedList" %>
  <%@ page import="java.util.Map" %>
    <%@ page import="entities.Ejercicio" %>
      <%@ page import="java.util.Map.Entry" %>
        <!DOCTYPE html>
        <html lang="es">

        <head>
          <meta charset="UTF-8">
          <title>Iniciar Entrenamiento</title>
          <link rel="stylesheet" href="style/UserManagementstyles.css">
        </head>
        <% 
          LinkedList<Ejercicio> ejercicios = (LinkedList<Ejercicio>) request.getAttribute("ejercicios");
          Map<Integer, Integer[]> seriesReps = (Map<Integer, Integer[]>) request.getAttribute("seriesReps");
        %>
        <body>
          <h1>Iniciar Entrenamiento</h1>
          <form action="guardarEntrenamiento" method="post">
            <input type="hidden" name="id_usuario" value="<%=request.getParameter(" id_usuario")%>">
            <input type="hidden" name="id_rutina" value="<%=request.getParameter(" id_rutina")%>">
            <table>
              <thead>
                <tr>
                  <th>Ejercicio</th>
                  <th>Series</th>
                  <th>Repeticiones</th>
                  <th>Orden</th>
                  <th>Tiempo</th>
                  <th>Peso</th>
                </tr>
              </thead>
              <tbody>
                <% for (Ejercicio ej : ejercicios) {
                        Integer[] approxValues = seriesReps.get(ej.getId());
                        %>
                        <tr>
                          <td>
                            <%= ej.getNombre() %>
                          </td>
                          <td><input type="number" name="series_<%=ej.getId()%>" placeholder="<%=approxValues[0]%>">
                          </td>
                          <td><input type="number" name="repeticiones_<%=ej.getId()%>"
                              placeholder="<%=approxValues[1]%>"></td>
                          <td><input type="number" name="orden_<%=ej.getId()%>" placeholder="1"></td>
                          <td><input type="time" name="tiempo_<%=ej.getId()%>"></td>
                          <td><input type="number" step="0.01" name="peso_<%=ej.getId()%>" placeholder="0.00"></td>
                        </tr>
                        <% } %>
              </tbody>
            </table>
            <input type="submit" value="Guardar Entrenamiento">
          </form>
        </body>

        </html>