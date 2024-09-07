<%@ page import="java.util.LinkedList" %>
  <%@ page import="java.util.Map" %>
    <%@ page import="entities.Ejercicio" %>
      <%@ page import="java.util.Map.Entry" %>
        <%@ page import="entities.Rutina" %>
          <%@ page import="entities.Entrenamiento" %>
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
          Rutina rutina = (Rutina) request.getAttribute("rutina");
          int id_usuario = (int) request.getAttribute("id_usuario");

          LinkedList<Entrenamiento> entrenamientos = new LinkedList<Entrenamiento>();
        %>
        <body>
          <h1>Entrenamiento: <%rutina.getNombre();%></h1>
          <form action="guardarEntrenamiento" method="post">
            <input type="hidden" name="id_rutina" value="<%=rutina.getId()%>">
            <input type="hidden" name="id_usuario" value="<%=id_usuario%>">
            <table>
              <thead>
                <tr>
                  <th>Ejercicio</th>
                  <th>Series</th>
                  <th>Repeticiones</th>
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
                          <td>
                          <input type="hidden" name="id_ejercicio_<%=ej.getId()%>" value="<%=ej.getId()%>">
                          <input type="number" name="series_<%=ej.getId()%>" value="<%=approxValues[0]%>" placeholder="<%=approxValues[0]%>">
                          </td>
                          <td><input type="number" name="repes_<%=ej.getId()%>" value="<%=approxValues[1]%>" placeholder="<%=approxValues[1]%>"></td>
                          <td><input type="text" name="tiempo_<%=ej.getId()%>"></td>
                          <td><input type="number" step="1.00" name="peso_<%=ej.getId()%>" placeholder="0.00"></td>
                        </tr>
                        <% } %>
              </tbody>
            </table>
            <input type="submit" value="Guardar Entrenamiento">
          </form>
        </body>

        </html>