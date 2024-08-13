<%@page import="java.util.LinkedList"%>
<%@page import="entities.Receta"%>
<%@page import="entities.Profesional"%>
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
        LinkedList<Receta> lr = (LinkedList<Receta>)request.getAttribute("listaRecetas");
    %>
    
</head>
<body>
    <div class="container">
        <h1>Recetas</h1>
        <div>
            <table>
                <thead>
                    <tr>
                        <th></th>
                        <th>Nombre</th>
                        <th>Descripcion</th>
                        <th>Nivel de dificultad</th>
                        <th>Profesional</th>         
                        <th>Editar Receta</th>
                        <th>Baja Receta</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                    for (Receta rec : lr) { 
                        Profesional p = rec.getProfesional();
                    %>
                    <tr>         
                        <td>
                            <%--- <a href="verMasReceta?id=<%=rec.getId()%>" class="action-btn edit-btn">Ver Más</a>--%>
                            <form action="verMasReceta" method="post" style="display:inline;">
                                <input type="hidden" name="id" value="<%=rec.getId()%>">
                                <input type="submit" value="Ver Más" class="action-btn edit-btn">
                            </form>
                        </td>
                        <td><%=rec.getNombre()%></td>
                        <td><%=rec.getDesc()%></td>
                        <td><%=rec.getNivelDificultad()%></td>
                        <td>
                            <%
                            if (p != null) {
                                out.print(p.getNombre() + " " + p.getApellido());
                            } else {
                                out.print("Sin profesional");
                            }
                            %>
                        </td>       
                        <td><a href="editarReceta?id=<%=rec.getId()%>" class="action-btn edit-btn">Editar</a></td>
                        <td>
                            <form action="EliminarReceta" method="post" style="display:inline;">
                                <input type="hidden" name="id" value="<%=rec.getId()%>">
                                <input type="submit" value="Borrar" class="action-btn delete-btn" onclick="return confirm('¿Estás seguro de que deseas eliminar esta receta?');">
                            </form>
                        </td>
                    </tr>
                    <% 
                    } // Fin del loop
                    %>
                </tbody>
            </table>
            <div style="text-align: right; margin-top: 20px; margin-bottom:20px; padding: 0 20px;">
                <a href="crearReceta" class="action-btn create-btn">Crear Receta</a>
            </div>
        </div>
    </div>
</body>
</html>