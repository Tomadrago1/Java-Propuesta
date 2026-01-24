<%@page import="java.util.LinkedList"%>
<%@page import="entities.Ejercicio"%>
<%@page import="entities.Rutina"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="style/UserManagementstyles.css">
    
    <%
        LinkedList<Ejercicio> eje = (LinkedList<Ejercicio>)request.getAttribute("listaEjercicios");
    	Rutina r = (Rutina)request.getAttribute("rutina");
    	
    %>
    
</head>
<body>
    <div class="container">
        <h1>Ejercicios para la rutina <%=r.getNombre()%></h1>
        <div>
            <table>
                <thead>
                    <tr>
                        <th>Nombre</th>
                        <th>Descripción</th>
                        <th>Agregar Ejercicio</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Ejercicio e : eje) { %>
                    <tr>
                        <td><%=e.getNombre()%></td>
                        <td><%=e.getDescripcion()%></td>
                        <td>
                            <form action="agregarEjercicioRutina" method="post" style="display:inline;">
                                <input type="hidden" name="id_eje" value="<%=e.getId()%>">
                                <input type="hidden" name="id_rut" value="<%=r.getId()%>">
                                <input type="submit" value="Agregar" class="action-btn create-btn" onclick="return confirm('¿Estás seguro de que deseas agregar este ejercicio a la rutina?');">
                            </form>
                        </td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
            <div class="container-create">
                <form action="crearEjercicio" method="post" style="display:inline;">
                	<input type="hidden" name="id_rut" value="<%=r.getId()%>">
                    <input type="submit" value="Crear Ejercicio" class="action-btn create-btn">
                </form>
            </div>
        </div>
        <a href="index.html" style="color: red">Volver</a>
    </div>
</body>
</html>
