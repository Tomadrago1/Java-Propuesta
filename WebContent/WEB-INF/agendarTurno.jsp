<%@page import="entities.Profesional"%>
<%@page import="java.time.LocalTime"%>
<%@page import="java.util.LinkedList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="style/global.css">
    <link rel="stylesheet" href="style/UserManagementstyles.css">
    <%
        Profesional profesional = (Profesional) request.getAttribute("profesional");
        String fechaSeleccionada = (String) request.getAttribute("fechaSeleccionada");
        LinkedList<LocalTime> turnosDisponibles = (LinkedList<LocalTime>) request.getAttribute("turnosDisponibles");
    %>
</head>
<body>
    <div class="container">
        <h1>Agendar Turno con <%=profesional != null ? profesional.getNombre() + " " + profesional.getApellido() : ""%></h1>
        
        <!-- Formulario para elegir fecha -->
        <div style="margin-bottom: 20px; padding: 15px; background-color: #f9f9f9; border: 1px solid #ddd; border-radius: 5px;">
            <h3>1. Elige una fecha</h3>
            <form action="verTurnosDisponibles" method="post" style="display: flex; align-items: center; gap: 15px;">
                <input type="hidden" name="id_profesional" value="<%=profesional.getIdUsuario()%>">
                <input type="date" name="fecha" value="<%=fechaSeleccionada != null ? fechaSeleccionada : ""%>" required style="padding: 8px; border-radius: 4px; border: 1px solid #ccc;">
                <input type="submit" value="Ver Turnos Disponibles" class="action-btn create-btn">
            </form>
        </div>

        <!-- Mostrar Turnos -->
        <% if (fechaSeleccionada != null) { %>
            <div style="margin-bottom: 20px;">
                <h3>2. Turnos disponibles para el <%=fechaSeleccionada%></h3>
                
                <% if (turnosDisponibles != null && !turnosDisponibles.isEmpty()) { %>
                    <div style="display: flex; flex-wrap: wrap; gap: 10px; margin-top: 15px;">
                        <% for (LocalTime hora : turnosDisponibles) { %>
                            <form action="confirmarTurno" method="post">
                                <input type="hidden" name="id_profesional" value="<%=profesional.getIdUsuario()%>">
                                <input type="hidden" name="fecha" value="<%=fechaSeleccionada%>">
                                <input type="hidden" name="hora" value="<%=hora.toString()%>">
                                <input type="submit" value="<%=hora.toString()%>" class="action-btn edit-btn" style="width: 100px; height: 50px; font-size: 16px;">
                            </form>
                        <% } %>
                    </div>
                <% } else { %>
                    <div style="padding: 15px; background-color: #fff3cd; color: #856404; border-radius: 5px; margin-top: 15px;">
                        No hay turnos disponibles para la fecha seleccionada. El profesional no atiende ese día o los turnos ya fueron reservados.
                    </div>
                <% } %>
            </div>
        <% } %>
        
        <div style="margin-top: 20px;">
            <form action="agendarConsulta" method="post">
                <input type="submit" value="Volver" class="action-btn delete-btn" style="background-color: #6c757d;">
            </form>
        </div>
        
    <% request.setAttribute("pageTitle", "Agendar Turno"); %>
    <jsp:include page="header.jsp" />
    </div>
</body>
</html>
