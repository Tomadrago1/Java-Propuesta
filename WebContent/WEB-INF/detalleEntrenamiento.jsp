<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="entities.Ejercicio" %>
<%@ page import="entities.Entrenamiento" %>
<%@ page import="entities.Rutina" %>
<%@ page import="java.time.LocalDateTime" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="style/global.css">
    <link rel="stylesheet" href="style/UserManagementstyles.css">
    <title>Detalle de Entrenamiento</title>
    <%
        Rutina rutina = (Rutina) request.getAttribute("rutina");
        LocalDateTime fechaHora = (LocalDateTime) request.getAttribute("fechaHora");
        LinkedList<Ejercicio> ejercicios = (LinkedList<Ejercicio>) request.getAttribute("ejercicios");
        LinkedList<Entrenamiento> entrenamientos = (LinkedList<Entrenamiento>) request.getAttribute("entrenamientos");
    %>
</head>
<body>
    <%-- Header global --%>
    <% request.setAttribute("pageTitle", "Detalle de Entrenamiento"); %>
    <jsp:include page="header.jsp" />
    
    <div class="container">
        <% 
        java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        %>
        <h2 style="text-align: center; color: #fff; margin-bottom: 1rem;">
            <%= rutina.getNombre() %> - <%= fechaHora != null ? fechaHora.format(formatter) : "-" %>
        </h2>
        
        <div class="div-table">
            <table>
                <thead>
                    <tr>
                        <th>Ejercicio</th>
                        <th>Zona</th>
                        <th>Series</th>
                        <th>Repeticiones</th>
                        <th>Tiempo</th>
                        <th>Peso (kg)</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                    for (int i = 0; i < ejercicios.size(); i++) { 
                        Ejercicio ejercicio = ejercicios.get(i); 
                        Entrenamiento entrenamiento = entrenamientos.get(i); 
                        String tiempoDisplay = (entrenamiento.getTiempo() == null || entrenamiento.getTiempo().trim().isEmpty()) 
                            ? "-" : entrenamiento.getTiempo(); 
                        String repesDisplay = (entrenamiento.getRepes() == null || entrenamiento.getRepes() == 0) 
                            ? "-" : entrenamiento.getRepes().toString(); 
                    %>
                        <tr>
                            <td><%= ejercicio.getNombre() %></td>
                            <td><%= ejercicio.getZona() != null ? ejercicio.getZona() : "-" %></td>
                            <td><%= entrenamiento.getSeries() %></td>
                            <td><%= repesDisplay %></td>
                            <td><%= tiempoDisplay %></td>
                            <td><%= entrenamiento.getPeso() > 0 ? entrenamiento.getPeso() : "-" %></td>
                        </tr>
                    <% } %>
                </tbody>
            </table>
        </div>
        
        <div style="text-align: center; margin-top: 1.5rem;">
            <a href="javascript:history.back()" class="action-btn edit-btn" style="display: inline-block; text-decoration: none;">
                Volver
            </a>
        </div>
    </div>
</body>
</html>
