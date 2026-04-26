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
    <% request.setAttribute("pageTitle", "Mi Agenda"); %>
    <jsp:include page="header.jsp" />
    
    <div class="container" style="margin-top: 30px;">
        <h1 style="display:none;">Mi Agenda de Turnos</h1>
        
        <!-- Formulario para elegir fecha -->
        <div class="filters-bar" style="justify-content: center;">
            <form action="verMiAgenda" method="get" style="display: flex; align-items: center; gap: 15px; flex-wrap: wrap;">
                <label for="fecha" style="font-weight: 600; color: var(--white); font-family: 'Montserrat', sans-serif;">Ver agenda del día:</label>
                <div class="filter-group" style="min-width: 200px;">
                    <input type="date" id="fecha" name="fecha" value="<%=fechaSeleccionada != null ? fechaSeleccionada : ""%>" required class="filter-input" style="color-scheme: dark;">
                </div>
                <input type="submit" value="Buscar" class="action-btn create-btn">
            </form>
        </div>

        <!-- Mostrar Agenda -->
        <% if (fechaSeleccionada != null) { %>
            <div style="margin-bottom: 30px;">
                <h3 style="color: var(--white); text-align: center; margin-bottom: 15px; font-family: 'Montserrat', sans-serif;">Turnos del <%=fechaSeleccionada%></h3>
                
                <% if (agenda != null && !agenda.isEmpty()) { %>
                    <div class="div-table" style="margin-top: 15px;">
                        <table>
                            <thead>
                                <tr>
                                    <th>Hora</th>
                                    <th>Estado</th>
                                    <th>Paciente</th>
                                    <th>Acciones</th>
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
                                            <% if (turno.getConsulta() != null && turno.getConsulta().getCliente() != null) { %>
                                                <%=turno.getConsulta().getCliente().getNombre()%> <%=turno.getConsulta().getCliente().getApellido()%>
                                            <% } else { %>
                                                -
                                            <% } %>
                                        </td>
                                        <td>
                                            <% if (turno.getEstado().equals("Ocupado") && turno.getConsulta() != null) { 
                                                   String desc = turno.getConsulta().getDesc_resultados();
                                                   boolean completada = (desc != null && !desc.trim().isEmpty());
                                                   String accionTexto = completada ? "Ver consulta" : "Registrar la consulta";
                                                   String btnClass = completada ? "viewmore-btn" : "edit-btn";
                                            %>
                                                <form action="registrarConsulta" method="get" style="margin: 0;">
                                                    <input type="hidden" name="id_cliente" value="<%=turno.getConsulta().getId_cliente()%>">
                                                    <input type="hidden" name="fecha_consulta" value="<%=turno.getConsulta().getFecha_consulta().toString()%>">
                                                    <button type="submit" class="action-btn <%=btnClass%>" style="padding: 4px 8px; font-size: 0.75rem;"><%=accionTexto%></button>
                                                </form>
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
                    <div style="padding: 20px; background: rgba(245, 158, 11, 0.1); border-left: 4px solid var(--warning); color: var(--gray-100); border-radius: 5px; margin-top: 15px; text-align: center;">
                        No has cargado disponibilidad para esta fecha o no tienes turnos armados. Puedes cargarla en "Gestionar Disponibilidad".
                    </div>
                <% } %>
            </div>
        <% } %>
        
        <div style="margin-top: 30px; text-align: center;">
            <form action="signin" method="get">
                <input type="submit" value="VOLVER" class="action-btn delete-btn" style="padding: 10px 30px;">
            </form>
        </div>
    </div>
</body>
</html>
