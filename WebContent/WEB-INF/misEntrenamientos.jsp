<%@ page import="java.util.LinkedList" %>
  <%@ page import="entities.Ejercicio" %>
    <%@ page import="entities.Entrenamiento" %>
      <!DOCTYPE html>
      <html lang="es">

      <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="style/UserManagementstyles.css">
        <title>Lista de Entrenamientos</title>
      </head>

      <body>
        <h1>Lista de Entrenamientos</h1>
        <table border="1">
          <thead>
            <tr>
              <th>Nombre del Ejercicio</th>
              <th>Fecha</th>
              <th>Series</th>
              <th>Repeticiones</th>
              <th>Tiempo</th>
              <th>Peso</th>
            </tr>
          </thead>
          <tbody>
            <% LinkedList<Ejercicio> ejercicios = (LinkedList<Ejercicio>) request.getAttribute("ejercicios");
                LinkedList<Entrenamiento> entrenamientos = (LinkedList<Entrenamiento>)
                    request.getAttribute("entrenamientos");

                    for (int i = 0; i < ejercicios.size(); i++) { Ejercicio ejercicio=ejercicios.get(i); Entrenamiento
                      entrenamiento=entrenamientos.get(i); String tiempoDisplay=(entrenamiento.getTiempo()==null ||
                      entrenamiento.getTiempo().trim().isEmpty()) ? "Sin tiempo" : entrenamiento.getTiempo(); String
                      repesDisplay=(entrenamiento.getRepes()==null) ? "Sin repeticiones" :
                      entrenamiento.getRepes().toString(); %>
                      <tr>
                        <td>
                          <%= ejercicio.getNombre() %>
                        </td>
                        <td>
                          <%= entrenamiento.getFecha() %>
                        </td>
                        <td>
                          <%= entrenamiento.getSeries() %>
                        </td>
                        <td>
                          <%= repesDisplay %>
                        </td>
                        <td>
                          <%= tiempoDisplay %>
                        </td>
                        <td>
                          <%= entrenamiento.getPeso() %>
                        </td>
                      </tr>
                      <% } %>
          </tbody>
        </table>
      </body>

      </html>