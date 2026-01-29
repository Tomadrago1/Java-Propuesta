<%@ page import="java.util.Collection" %>
<%@ page import="entities.Usuario" %>
<%@ page import="servletEntrenamiento.misEntrenamientos.EntrenamientoAgrupado" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="style/global.css">
    <link rel="stylesheet" href="style/UserManagementstyles.css">
    <title>Mis Entrenamientos</title>
    <%
        Usuario u = (Usuario)session.getAttribute("usuario");
        Collection<EntrenamientoAgrupado> entrenamientosAgrupados = 
            (Collection<EntrenamientoAgrupado>) request.getAttribute("entrenamientosAgrupados");
    %>
</head>
<body>
    <%-- Header global --%>
    <% request.setAttribute("pageTitle", "Mis Entrenamientos"); %>
    <jsp:include page="header.jsp" />
    
    <div class="container">
        <div class="div-table">
            <% if (entrenamientosAgrupados == null || entrenamientosAgrupados.isEmpty()) { %>
                <p style="text-align: center; color: #666; padding: 2rem;">
                    No tienes entrenamientos registrados todavía.
                </p>
            <% } else { %>
                <table>
                    <thead>
                        <tr>
                            <th>Rutina</th>
                            <th>Descripción</th>
                            <th>Fecha</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for (EntrenamientoAgrupado grupo : entrenamientosAgrupados) { %>
                            <tr>
                                <td><%= grupo.rutina.getNombre() %></td>
                                <td><%= grupo.rutina.getDescripcion() != null ? grupo.rutina.getDescripcion() : "-" %></td>
                                <td><%= grupo.fecha %></td>
                                <td>
                                    <form action="verDetalleEntrenamiento" method="post" style="display:inline;">
                                        <input type="hidden" name="idRutina" value="<%= grupo.idRutina %>">
                                        <input type="hidden" name="fecha" value="<%= grupo.fecha %>">
                                        <input type="hidden" name="idUsuario" value="<%= u.getIdUsuario() %>">
                                        <input type="submit" value="Ver" class="action-btn edit-btn">
                                    </form>
                                </td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>
            <% } %>
        </div>
        <a href="signin">Volver</a>
    </div>
</body>
</html>