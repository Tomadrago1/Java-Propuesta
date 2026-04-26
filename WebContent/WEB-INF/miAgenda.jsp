<%@page import="logic.TurnoDTO"%>
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
        String fechaSeleccionada = (String) request.getAttribute("fechaSeleccionada");
        LinkedList<TurnoDTO> agenda = (LinkedList<TurnoDTO>) request.getAttribute("agenda");
    %>
</head>
<body>
    <div class="container">
        <h1>Mi Agenda de Turnos</h1>
        
        <!-- Formulario para elegir fecha -->
        <div style="margin-bottom: 20px; padding: 15px; background-color: #f9f9f9; border: 1px solid #ddd; border-radius: 5px;">
            <form action="verMiAgenda" method="get" style="display: flex; align-items: center; gap: 15px;">
                <label for="fecha" style="font-weight: bold;">Ver agenda del día:</label>
                <input type="date" id="fecha" name="fecha" value="<%=fechaSeleccionada != null ? fechaSeleccionada : ""%>" required style="padding: 8px; border-radius: 4px; border: 1px solid #ccc;">
                <input type="submit" value="Buscar" class="action-btn create-btn">
            </form>
        </div>

        <!-- Mostrar Agenda -->
        <% if (fechaSeleccionada != null) { %>
            <div style="margin-bottom: 20px;">
                <h3>Turnos del <%=fechaSeleccionada%></h3>
                
                <% if (agenda != null && !agenda.isEmpty()) { %>
                    <div class="div-table" style="margin-top: 15px;">
                        <table>
                            <thead>
                                <tr>
                                    <th>Hora</th>
                                    <th>Estado</th>
                                    <th>Paciente</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% for (TurnoDTO turno : agenda) { %>
                                    <tr>
                                        <td style="font-weight: bold;"><%=turno.getHora().toString()%></td>
                                        <td>
                                            <% if (turno.getEstado().equals("Libre")) { %>
                                                <span style="color: #28a745; font-weight: bold;">Libre</span>
                                            <% } else { %>
                                                <span style="color: #dc3545; font-weight: bold;">Ocupado</span>
                                            <% } %>
                                        </td>
                                        <td>
                                            <% if (turno.getCliente() != null) { %>
                                                <%=turno.getCliente().getNombre()%> <%=turno.getCliente().getApellido()%>
                                            <% } else { %>
                                                -
                                            <% } %>
                                        </td>
                                    </tr>
                                <% } %>
                            </tbody>
                        </table>
                    </div>
                <% } else { %>
                    <div style="padding: 15px; background-color: #fff3cd; color: #856404; border-radius: 5px; margin-top: 15px;">
                        No has cargado disponibilidad para esta fecha o no tienes turnos armados. Puedes cargarla en "Gestionar Disponibilidad".
                    </div>
                <% } %>
            </div>
        <% } %>
        
        <div style="margin-top: 20px;">
            <form action="vistaProfesional.jsp" method="get">
                <input type="submit" value="Volver" class="action-btn delete-btn" style="background-color: #6c757d;">
            </form>
        </div>
        
    <% request.setAttribute("pageTitle", "Mi Agenda"); %>
    <jsp:include page="header.jsp" />
    </div>
</body>
</html>
