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
    <% request.setAttribute("pageTitle", "Agendar Turno"); %>
    <jsp:include page="header.jsp" />
    
    <div class="container" style="margin-top: 30px;">
        <h1 style="display:none;">Agendar Turno con <%=profesional != null ? profesional.getNombre() + " " + profesional.getApellido() : ""%></h1>
        
        <!-- Formulario para elegir fecha -->
        <div class="filters-bar" style="flex-direction: column; align-items: stretch; max-width: 600px; margin: 0 auto 30px auto;">
            <h3 style="color: var(--white); margin-bottom: 15px; font-family: 'Montserrat', sans-serif;">1. Elige una fecha</h3>
            <form action="verTurnosDisponibles" method="post" style="display: flex; flex-direction: column; gap: 15px;">
                <input type="hidden" name="id_profesional" value="<%=profesional.getIdUsuario()%>">
                <input type="date" name="fecha" value="<%=fechaSeleccionada != null ? fechaSeleccionada : ""%>" required class="filter-input" style="color-scheme: dark;">
                <input type="submit" value="Ver Turnos Disponibles" class="action-btn create-btn" style="width: 100%;">
            </form>
        </div>

        <!-- Mostrar Turnos -->
        <% if (fechaSeleccionada != null) { %>
            <div style="margin-bottom: 30px; text-align: center;">
                <h3 style="color: var(--white); margin-bottom: 20px; font-family: 'Montserrat', sans-serif;">2. Turnos disponibles para el <%=fechaSeleccionada%></h3>
                
                <% if (turnosDisponibles != null && !turnosDisponibles.isEmpty()) { %>
                    <div style="display: flex; flex-wrap: wrap; justify-content: center; gap: 15px; margin-top: 15px;">
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
                    <div style="padding: 20px; background: rgba(245, 158, 11, 0.1); border-left: 4px solid var(--warning); color: var(--gray-100); border-radius: 5px; margin-top: 15px; max-width: 600px; margin: 0 auto;">
                        No hay turnos disponibles para la fecha seleccionada. El profesional no atiende ese día o los turnos ya fueron reservados.
                    </div>
                <% } %>
            </div>
        <% } %>
        
        <div style="margin-top: 40px; text-align: center;">
            <form action="agendarConsulta" method="post">
                <input type="submit" value="VOLVER" class="action-btn delete-btn" style="padding: 10px 30px;">
            </form>
        </div>
    </div>
</body>
</html>
