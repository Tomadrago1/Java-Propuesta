<%@page import="java.util.LinkedList"%>
<%@page import="entities.Ejercicio"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="style/UserManagementstyles.css">
    <title>Gestión de Ejercicios</title>
    
    <%
        LinkedList<Ejercicio> lu = (LinkedList<Ejercicio>)request.getAttribute("listaEjercicios");
    %>
    
</head>
<body>
    <div class="container">
        <h1>Gestión de Ejercicios</h1>
        <div class="table-container">
            <table>
                <thead>
                    <tr>
                        <th>Nombre</th>
                        <th>Descripción</th>
                        <th>Zona</th>
                        <th>Tipo de Ejercicio</th>
                        <th>Editar Ejercicio</th>
                        <th>Eliminar Ejercicio</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Ejercicio eje : lu) {%>
                    <tr>
                        <td><%=eje.getNombre()%></td>
                        <td><%=eje.getDescripcion()%></td>
                        <td><%=eje.getZona() != null ? eje.getZona() : "-"%></td>
                        <td><%=eje.getTipoEjercicio() != null ? eje.getTipoEjercicio() : "-"%></td>
                        <td class="actions">
                            <a href="editarEjercicio?id=<%=eje.getId()%>" class="action-btn edit-btn">Editar</a>
                        </td>
                        <td>    
                            <form action="eliminarEjercicio" method="post" class="inline-form">
                                <input type="hidden" name="id" value="<%=eje.getId()%>">
                                <input type="submit" value="Borrar" class="action-btn delete-btn" onclick="return confirm('¿Estás seguro de que deseas eliminar este ejercicio?');">
                            </form>
                        </td>
                    </tr>
                    <%}%>
                </tbody>
            </table>
            <div class="container-create">
                <form action="crearEjercicio" method="post" style="display:inline;">
                    <input type="submit" value="Crear Ejercicio" class="action-btn create-btn">
                </form>
            </div>
        </div>
    <% request.setAttribute("pageTitle", "Gestión de Ejercicios"); %>
    <jsp:include page="../header.jsp" />
    </div>
</body>
</html>
