<%@page import="entities.Necesidad"%>
<%@page import="java.util.LinkedList"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.util.Map"%>
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
        LinkedHashMap<LocalDate, LinkedList<Necesidad>> historial = (LinkedHashMap<LocalDate, LinkedList<Necesidad>>) request.getAttribute("historial");
    %>
</head>
<body>
    <% request.setAttribute("pageTitle", "Mi Historial Nutricional"); %>
    <jsp:include page="header.jsp" />
    
    <div class="container" style="margin-top: 30px;">
        <h1 style="display:none;">Mi Historial Nutricional</h1>
        
        <div class="filters-bar" style="flex-direction: column; align-items: stretch; max-width: 800px; margin: 0 auto; padding: 20px;">
            <h2 style="color: var(--white); font-family: 'Montserrat', sans-serif; text-align: center; margin-bottom: 30px;">
                Evolución de Mi Plan Nutricional
            </h2>
            
            <% if (historial != null && !historial.isEmpty()) { 
                for (Map.Entry<LocalDate, LinkedList<Necesidad>> entry : historial.entrySet()) {
                    LocalDate fecha = entry.getKey();
                    LinkedList<Necesidad> lista = entry.getValue();
                    String profNombre = "";
                    if (!lista.isEmpty() && lista.get(0).getProfesional() != null) {
                        profNombre = lista.get(0).getProfesional().getNombre() + " " + lista.get(0).getProfesional().getApellido();
                    }
            %>
                <div style="background: rgba(255, 255, 255, 0.05); padding: 20px; border-radius: 8px; margin-bottom: 25px; border-left: 4px solid var(--primary);">
                    <div style="display: flex; justify-content: space-between; align-items: center; border-bottom: 1px solid rgba(255,255,255,0.1); padding-bottom: 10px; margin-bottom: 15px;">
                        <h3 style="color: var(--white); font-family: 'Montserrat', sans-serif; margin: 0;">
                            A partir del <%=fecha.toString()%>
                        </h3>
                        <span style="color: var(--gray-300); font-size: 0.9rem;">
                            Asignado por: <strong><%=profNombre%></strong>
                        </span>
                    </div>
                    
                    <div class="div-table">
                        <table style="width: 100%; border-collapse: separate; border-spacing: 0 5px;">
                            <thead>
                                <tr>
                                    <th style="text-align: left; padding: 10px; color: var(--gray-200); font-weight: 500;">Nutriente</th>
                                    <th style="text-align: center; padding: 10px; color: var(--gray-200); font-weight: 500;">Mínimo</th>
                                    <th style="text-align: center; padding: 10px; color: var(--gray-200); font-weight: 500;">Máximo</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% for (Necesidad nec : lista) { %>
                                    <tr style="background: rgba(0,0,0,0.2);">
                                        <td style="padding: 10px 15px; border-radius: 5px 0 0 5px;">
                                            <strong style="color: var(--white); font-size: 0.95rem;">
                                                <%=nec.getNutriente() != null ? nec.getNutriente().getNombre() : "Desconocido"%>
                                            </strong>
                                        </td>
                                        <td style="padding: 10px; text-align: center; color: var(--gray-100);">
                                            <%=nec.getCantidad_min()%> g
                                        </td>
                                        <td style="padding: 10px; text-align: center; color: var(--gray-100); border-radius: 0 5px 5px 0;">
                                            <%=nec.getCantidad_max()%> g
                                        </td>
                                    </tr>
                                <% } %>
                            </tbody>
                        </table>
                    </div>
                </div>
            <%  }
            } else { %>
                <div style="padding: 20px; background: rgba(239, 68, 68, 0.1); border-left: 4px solid var(--warning); color: var(--gray-100); border-radius: 5px; text-align: center;">
                    Todavía no tienes ningún plan nutricional asignado.
                </div>
            <% } %>
            
            <div style="margin-top: 30px; text-align: center;">
                <form action="signin" method="get">
                    <input type="submit" value="VOLVER AL PANEL" class="action-btn delete-btn" style="padding: 10px 30px;">
                </form>
            </div>
        </div>
    </div>
</body>
</html>
