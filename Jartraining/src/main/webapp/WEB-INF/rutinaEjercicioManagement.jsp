<%@page import="java.util.LinkedList"%>
<%@page import="entities.Ejercicio"%>
<%@page import="entities.Rutina"%>
<%@page import="java.util.Map"%>
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
    	LinkedList<Map<String,Object>> lu = (LinkedList<Map<String, Object>>)request.getAttribute("listaEjercicios");
    	Rutina r = (Rutina)request.getAttribute("rutina");
    %>
    
</head>
<body>
    <div class="container">
        <h1>Gestión de Ejercicios de <%= r.getNombre() %></h1>
        <div class="table-container">
            <table>
                <thead>
                    <tr>
                        <th>Nombre</th>
                        <th>Descripción</th>
                        <th>Series</th>
                        <th>Repeticiones</th>
                        <th>Tiempo</th>
                        <th>Editar Ejercicio</th>
                        <th>Eliminar Ejercicio</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Map<String, Object> eje : lu) {%>
                    <tr>
                        <td><%=eje.get("nombre")%></td>
                        <td><%=eje.get("descripcion")%></td>
                        <td><%=eje.get("series")%></td>
                        <td><%=eje.get("repes")%></td>
                        <td><%=eje.get("tiempo")%></td>
                        <td class="actions">
                        	<form action="editarEjercicioRutina" method="post" class="inline-form">
                                <input type="hidden" name="id_rut" value="<%=eje.get("id_rut")%>">
                                <input type="hidden" name="id_eje" value="<%=eje.get("id_eje")%>">
                                <input type="hidden" name="series" value="<%=eje.get("series")%>">
                                <input type="hidden" name="repes" value="<%=eje.get("repes")%>">    
                                <input type="hidden" name="tiempo" value="<%=eje.get("tiempo")%>">                            
                                <input type="submit" value="Editar" class="action-btn edit-btn">
                            </form>
                        </td>
                        <td>
                            <form action="quitarEjercicioRutina" method="post" class="inline-form">
                                <input type="hidden" name="id_rut" value="<%=eje.get("id_rut")%>">
                                <input type="hidden" name="id_eje" value="<%=eje.get("id_eje")%>">
                                <input type="submit" value="Quitar de la rutina" class="action-btn delete-btn" onclick="return confirm('¿Estás seguro de que deseas quitar este ejercicio?');">
                            </form>
                        </td>
                    </tr>
                    <%}%>
                </tbody>
            </table>
            
            <div class="container-create">
            	<form action="addEjercicioRutina" method="post" class="inline-form">
                	<input type="hidden" name="id_rut" value="<%=r.getId()%>">
              		<input type="submit" value="Añadir Ejercicio" class="action-btn create-btn">
            	</form>
            </div>
        </div>
        <a href="index.html" style="color: red">Volver</a>
    </div>
</body>
</html>
