<%@page import="java.util.LinkedList"%>
<%@page import="entities.Medida"%>
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
        LinkedList<Medida> lm = (LinkedList<Medida>)request.getAttribute("listaMedidas");
        int id = (int)session.getAttribute("id_usuario");
    %>
    
</head>
<body>
    <div class="container">
        <h1>Medidas</h1>
        <div>
            <table>
                <thead>
                    <tr>
                        <th>Peso</th>
                        <th>Altura</th>
                        <th>Fecha</th>
                        <th>Borrar medida</th>	
                    </tr>
                </thead>
                <tbody>
                    <% for (Medida m : lm) { 
                    %>
                    <tr>
                        <td><%=m.getPeso()%></td>
                        <td><%=m.getAltura()%></td>
                        <td><%=m.getFecha()%></td>  
                        <td>
                            <form action="EliminarMedida" method="post" style="display:inline;">
                                <input type="hidden" name="id_usuario" value="<%=m.getId_usuario()%>">
                                <input type="hidden" name="fecha" value="<%=m.getFecha()%>">
                                <input type="submit" value="Borrar" class="action-btn delete-btn" onclick="return confirm('¿Estás seguro de que deseas eliminar este usuario?');">
                            </form>
                        </td>
                    </tr>
                    <% 
                    } // Fin de la condición
                    %>
                </tbody>
            </table>
            <div style="text-align: right; margin-top: 20px; margin-bottom:20px; padding: 0 20px;">
                <form action="crearMedida" method="post" style="display:inline;">
                    <input type="hidden" name="id_usuario" value="<%=id%>">
                    <input type="submit" value="Crear Medida" class="action-btn create-btn">
                </form>
            </div>
        </div>
    </div>
</body>
</html>