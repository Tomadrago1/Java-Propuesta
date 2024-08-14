<%@page import="java.util.LinkedList"%>
<%@page import="entities.Rutina"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="style/rutinaManagement.css">
    <title>Gestión de Rutina</title>
    
    <%
        LinkedList<Rutina> lu = (LinkedList<Rutina>)request.getAttribute("listaRutinas");
    %>
    
</head>
<body>
    <div class="container">
        <h1>Gestión de Rutinas</h1>
        <div class="table-container">
            <table>
                <thead>
                    <tr>
                        <th>Nombre</th>
                        <th>Descripción</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Rutina rut : lu) {%>
                    <tr>
                        <td><%=rut.getNombre()%></td>
                        <td><%=rut.getDescripcion()%></td>
                        <td class="actions">
                            <a href="editarRutina?id=<%=rut.getId()%>" class="action-btn edit-btn">Editar</a>
                            <form action="eliminarRutina" method="post" class="inline-form">
                                <input type="hidden" name="id" value="<%=rut.getId()%>">
                                <input type="submit" value="Borrar" class="action-btn delete-btn" onclick="return confirm('¿Estás seguro de que deseas eliminar este rutina?');">
                            </form>
                            <form action="verEjerciciosRutina" method="post" class="inline-form">
                                <input type="hidden" name="id" value="<%=rut.getId()%>">
                                <input type="submit" value="Ver Ejercicios" class="action-btn delete-btn">
                            </form>
                        </td>
                    </tr>
                    <%}%>
                </tbody>
            </table>
            <div class="create-btn-container">
                <a href="crearRutina" class="action-btn create-btn">Crear Rutina</a>
            </div>
        </div>
    </div>
</body>
</html>
