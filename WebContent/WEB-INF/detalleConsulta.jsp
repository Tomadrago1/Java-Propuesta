<%@page import="entities.Consulta"%>
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
        Consulta consulta = (Consulta) request.getAttribute("consulta");
        boolean completada = (consulta != null && consulta.getDesc_resultados() != null && !consulta.getDesc_resultados().trim().isEmpty());
    %>
</head>
<body>
    <% request.setAttribute("pageTitle", completada ? "Ver Consulta" : "Registrar Consulta"); %>
    <jsp:include page="header.jsp" />
    
    <div class="container" style="margin-top: 30px;">
        <h1 style="display:none;"><%=completada ? "Ver Consulta" : "Registrar Consulta"%></h1>
        
        <div class="filters-bar" style="flex-direction: column; align-items: stretch; max-width: 800px; margin: 0 auto;">
            <% if (consulta != null && consulta.getCliente() != null) { %>
                <div style="background: rgba(255, 255, 255, 0.05); padding: 20px; border-radius: 8px; margin-bottom: 20px; border-left: 4px solid var(--primary); display: flex; justify-content: space-between; align-items: center; flex-wrap: wrap; gap: 15px;">
                    <div>
                        <h3 style="color: var(--white); font-family: 'Montserrat', sans-serif; margin-bottom: 15px;">Información del Paciente y Turno</h3>
                        <p style="color: var(--gray-200); margin-bottom: 8px;"><strong>Paciente:</strong> <%=consulta.getCliente().getNombre()%> <%=consulta.getCliente().getApellido()%></p>
                        <p style="color: var(--gray-200); margin-bottom: 8px;"><strong>Fecha:</strong> <%=consulta.getFecha_consulta().toLocalDate().toString()%></p>
                        <p style="color: var(--gray-200); margin-bottom: 0;"><strong>Hora:</strong> <%=consulta.getFecha_consulta().toLocalTime().toString()%></p>
                    </div>
                    <div>
                        <form action="prepararAsignacion" method="get" style="margin: 0;">
                            <input type="hidden" name="id_paciente" value="<%=consulta.getId_cliente()%>">
                            <button type="submit" class="action-btn viewmore-btn" style="padding: 10px 20px; font-size: 0.9rem;">Asignar Plan Nutricional</button>
                        </form>
                    </div>
                </div>

                <form action="guardarResultadosConsulta" method="post" style="display: flex; flex-direction: column; gap: 20px;">
                    <input type="hidden" name="id_cliente" value="<%=consulta.getId_cliente()%>">
                    <input type="hidden" name="fecha_consulta" value="<%=consulta.getFecha_consulta().toString()%>">
                    
                    <div class="filter-group">
                        <label for="desc_resultados" style="font-weight: 600; display: block; margin-bottom: 8px; color: var(--white); font-family: 'Montserrat', sans-serif;">Descripción de Resultados / Notas de la Consulta:</label>
                        <textarea id="desc_resultados" name="desc_resultados" rows="8" class="filter-input" 
                                  style="resize: vertical; min-height: 150px; line-height: 1.5;"
                                  <%=completada ? "readonly" : "required"%>><%=completada ? consulta.getDesc_resultados() : ""%></textarea>
                    </div>
                    
                    <% if (!completada) { %>
                        <div style="text-align: center; margin-top: 10px;">
                            <input type="submit" value="GUARDAR RESULTADOS" class="action-btn create-btn" style="width: 100%; max-width: 300px; font-size: 1.1rem; padding: 15px;">
                        </div>
                    <% } %>
                </form>
            <% } else { %>
                <div style="padding: 20px; background: rgba(239, 68, 68, 0.1); border-left: 4px solid var(--error); color: var(--gray-100); border-radius: 5px; text-align: center;">
                    No se encontró la información de la consulta.
                </div>
            <% } %>
        </div>

        <div style="margin-top: 30px; text-align: center;">
            <form action="verMiAgenda" method="get">
                <% if (consulta != null) { %>
                    <input type="hidden" name="fecha" value="<%=consulta.getFecha_consulta().toLocalDate().toString()%>">
                <% } %>
                <input type="submit" value="VOLVER A LA AGENDA" class="action-btn delete-btn" style="padding: 10px 30px;">
            </form>
        </div>
    </div>
</body>
</html>
