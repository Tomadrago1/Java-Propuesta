<%@page import="java.util.LinkedList" %>
    <%@page import="entities.Ejercicio" %>
        <%@page import="entities.Rutina" %>
            <%@page import="entities.EjercicioRutina" %>
                <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
                    <!DOCTYPE html>
                    <html lang="es">

                    <head>
                        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                        <meta charset="utf-8">
                        <meta http-equiv="X-UA-Compatible" content="IE=edge">
                        <meta name="viewport" content="width=device-width, initial-scale=1">
                        <link rel="stylesheet" href="style/UserManagementstyles.css">
                        <title>Gestión de Ejercicios</title>

                        <% LinkedList<EjercicioRutina> er = (LinkedList<EjercicioRutina>)request.getAttribute("ejercicios_rutina");
                            EjercicioRutina primerElemento = er.getFirst();
                            Rutina rut = primerElemento.getRutina();%>

                    </head>

                    <body>
                        <div class="container">
                            <h1>Gestión de Ejercicios de <%= rut.getNombre() %>
                            </h1>
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
                                        <% for (EjercicioRutina eje_rut : er) { %>
                                            <% Ejercicio eje = eje_rut.getEjercicio(); %>
                                            <tr>
                                                <td>
                                                    <%= eje.getNombre() %>
                                                </td>
                                                <td>
                                                    <%= eje.getDescripcion() %>
                                                </td>
                                                <td>
                                                    <%= (eje_rut.getSeriesAproximadas()) %>
                                                </td>
                                                <td>
                                                    <%= (eje_rut.getRepesAproximadas()==null || (Integer)eje_rut.getRepesAproximadas()==0) ? "-" : eje_rut.getRepesAproximadas() %>
                                                </td>
                                                <td>
                                                    <%= eje_rut.getTiempo() !=null &&
                                                        !eje_rut.getTiempo().toString().isEmpty() ? eje_rut.getTiempo()
                                                        : "-" %>
                                                </td>
                                                <td class="actions">
                                                    <form action="editarEjercicioRutina" method="post"
                                                        class="inline-form">
                                                        <input type="hidden" name="id_rut" value="<%= rut.getId()%>">
                                                        <input type="hidden" name="id_eje" value="<%= eje.getId()%>">
                                                        <input type="hidden" name="series" value="<%= eje_rut.getSeriesAproximadas()%>">
                                                        <input type="hidden" name="repes" value="<%= eje_rut.getRepesAproximadas()%>">
                                                        <input type="hidden" name="tiempo" value="<%= eje_rut.getTiempo()%>">
                                                        <input type="submit" value="Editar" class="action-btn edit-btn">
                                                    </form>
                                                </td>
                                                <td>
                                                    <form action="quitarEjercicioRutina" method="post"
                                                        class="inline-form">
                                                        <input type="hidden" name="id_rut" value="<%= rut.getId()%>">
                                                        <input type="hidden" name="id_eje" value="<%= eje.getId() %>">
                                                        <input type="submit" value="Quitar de la rutina"
                                                            class="action-btn delete-btn"
                                                            onclick="return confirm('¿Estás seguro de que deseas quitar este ejercicio?');">
                                                    </form>
                                                </td>
                                            </tr>
                                            <% } %>
                                    </tbody>
                                </table>

                                <div class="container-create">
                                    <form action="addEjercicioRutina" method="post" class="inline-form">
                                        <input type="hidden" name="id_rut" value="<%= rut.getId() %>">
                                        <input type="submit" value="Añadir Ejercicio" class="action-btn create-btn">
                                    </form>
                                </div>
                            </div>
                            <a href="index.html" style="color: red">Volver</a>
                        </div>
                    </body>

                    </html>