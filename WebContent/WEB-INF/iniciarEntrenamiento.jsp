<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
          <meta name="viewport" content="width=device-width, initial-scale=1">
          <title>Iniciar Entrenamiento</title>
          <link rel="stylesheet" href="style/global.css">
          <link rel="stylesheet" href="style/UserManagementstyles.css">
          <style>
            /* Estilos para los inputs del formulario */
            table input[type="number"],
            table input[type="text"] {
              width: 100%;
              padding: 0.6rem;
              border: 2px solid rgba(255, 107, 53, 0.3);
              border-radius: 8px;
              background: rgba(26, 26, 46, 0.6);
              color: #fff;
              font-family: "Inter", sans-serif;
              font-size: 0.95rem;
              transition: all 0.3s ease;
              text-align: center;
            }
            
            table input[type="number"]:focus,
            table input[type="text"]:focus {
              outline: none;
              border-color: var(--primary, #ff6b35);
              background: rgba(26, 26, 46, 0.8);
              box-shadow: 0 0 15px rgba(255, 107, 53, 0.3);
            }
            
            table input[type="number"]::placeholder,
            table input[type="text"]::placeholder {
              color: rgba(255, 255, 255, 0.4);
              font-style: italic;
            }
            
            /* Estilo para el botón de submit */
            input[type="submit"] {
              display: block;
              margin: 2rem auto 0;
              padding: 0.875rem 2.5rem;
              background: linear-gradient(135deg, #10b981 0%, #059669 100%);
              color: white;
              border: none;
              border-radius: 10px;
              font-family: "Montserrat", sans-serif;
              font-size: 1rem;
              font-weight: 600;
              cursor: pointer;
              transition: all 0.3s ease;
              box-shadow: 0 4px 15px rgba(16, 185, 129, 0.3);
            }
            
            input[type="submit"]:hover {
              transform: translateY(-2px);
              box-shadow: 0 6px 25px rgba(16, 185, 129, 0.5);
            }
            
            input[type="submit"]:active {
              transform: translateY(0);
            }
          </style>
        </head>
        <% 
          LinkedList<Ejercicio> ejercicios = (LinkedList<Ejercicio>) request.getAttribute("ejercicios");
          Map<Integer, Integer[]> seriesReps = (Map<Integer, Integer[]>) request.getAttribute("seriesReps");
          Rutina rutina = (Rutina) request.getAttribute("rutina");
          int id_usuario = (int) request.getAttribute("id_usuario");

          LinkedList<Entrenamiento> entrenamientos = new LinkedList<Entrenamiento>();
        %>
        <body>
          <%-- Header global --%>
          <% request.setAttribute("pageTitle", "Iniciar Entrenamiento - " + rutina.getNombre()); %>
          <jsp:include page="header.jsp" />
          
          <div class="container">
          <form action="guardarEntrenamiento" method="post">
            <input type="hidden" name="id_rutina" value="<%=rutina.getId()%>">
            <input type="hidden" name="id_usuario" value="<%=id_usuario%>">
            <div class="div-table">
            <table>
              <thead>
                <tr>
                  <th>Ejercicio</th>
                  <th>Series</th>
                  <th>Repeticiones</th>
                  <th>Tiempo</th>
                  <th>Peso (kg)</th>
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
                          <input type="number" name="series_<%=ej.getId()%>" value="<%=approxValues[0]%>" placeholder="Ej: <%=approxValues[0]%>" min="0">
                          </td>
                          <td><input type="number" name="repes_<%=ej.getId()%>" value="<%=approxValues[1]%>" placeholder="Ej: <%=approxValues[1]%>" min="0"></td>
                          <td><input type="text" name="tiempo_<%=ej.getId()%>" class="time-input" placeholder="MM:SS" maxlength="8"></td>
                          <td><input type="number" step="0.5" name="peso_<%=ej.getId()%>" placeholder="0.0 kg" min="0"></td>
                        </tr>
                        <% } %>
              </tbody>
            </table>
            </div>
            <input type="submit" value="Guardar Entrenamiento">
          </form>
          
          <div style="text-align: center; margin-top: 1.5rem;">
            <a href="javascript:history.back()" class="action-btn edit-btn" style="display: inline-block; text-decoration: none;">
              Volver
            </a>
          </div>
          </div>
          
          <script>
            // Máscara automática para el campo tiempo
            document.addEventListener('DOMContentLoaded', function() {
              const timeInputs = document.querySelectorAll('.time-input');
              
              timeInputs.forEach(input => {
                input.addEventListener('input', function(e) {
                  let value = e.target.value.replace(/[^0-9]/g, ''); // Solo números
                  let formatted = '';
                  
                  if (value.length === 0) {
                    e.target.value = '';
                    return;
                  }
                  
                  // Formatear según longitud: MMSS -> MM:SS o HHMMSS -> HH:MM:SS
                  if (value.length <= 2) {
                    formatted = value; // 1, 12
                  } else if (value.length <= 4) {
                    formatted = value.slice(0, 2) + ':' + value.slice(2); // 123 -> 12:3, 1234 -> 12:34
                  } else {
                    formatted = value.slice(0, value.length - 4) + ':' + value.slice(-4, -2) + ':' + value.slice(-2); // 12345 -> 1:23:45
                  }
                  
                  e.target.value = formatted;
                });
                
                // Permitir borrar con backspace
                input.addEventListener('keydown', function(e) {
                  if (e.key === 'Backspace') {
                    const value = e.target.value.replace(/[^0-9]/g, '');
                    if (value.length === 0) return;
                    
                    e.preventDefault();
                    const newValue = value.slice(0, -1);
                    
                    // Reformatear después de borrar
                    let formatted = '';
                    if (newValue.length === 0) {
                      formatted = '';
                    } else if (newValue.length <= 2) {
                      formatted = newValue;
                    } else if (newValue.length <= 4) {
                      formatted = newValue.slice(0, 2) + ':' + newValue.slice(2);
                    } else {
                      formatted = newValue.slice(0, newValue.length - 4) + ':' + newValue.slice(-4, -2) + ':' + newValue.slice(-2);
                    }
                    
                    e.target.value = formatted;
                  }
                });
              });
            });
          </script>
        </body>

        </html>