<%@page import="java.util.LinkedList" %>
    <%@page import="entities.*" %>
        <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
            <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
            <html>

            <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <meta charset="utf-8">
                <meta http-equiv="X-UA-Compatible" content="IE=edge">
                <meta name="viewport" content="width=device-width, initial-scale=1">
                <link rel="stylesheet" href="style/UserManagementstyles.css">

                <% Receta r=(Receta)request.getAttribute("Receta"); LinkedList<IngredienteReceta> li = (LinkedList
                    <IngredienteReceta>)request.getAttribute("ListaIngredientes");
                        %>
            </head>

            <body>
                <div class="container">
                    <h1>
                        <%= r.getNombre() %>
                    </h1>
                    <div>
                        <table>
                            <thead>
                                <tr>
                                    <th></th>
                                    <th>Nombre</th>
                                    <th>Descripcion</th>
                                    <th>Cantidad</th>
                                    <th>Editar Cantidad</th>
                                    <th>Baja Ingrediente</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% for (IngredienteReceta ir : li) { %>
                                    <tr>
                                        <td>
                                            <form action="verMasIngrediente" method="post" style="display:inline;">
                                                <input type="hidden" name="id"
                                                    value="<%= ir.getIngrediente().getId() %>">
                                                <input type="submit" value="Ver Más" class="action-btn edit-btn">
                                            </form>
                                        </td>
                                        <td>
                                            <%= ir.getIngrediente().getNombre() %>
                                        </td>
                                        <td>
                                            <%= ir.getIngrediente().getDesc() %>
                                        </td>
                                        <td>
                                            <%= (ir.getCantidad() % 1==0) ? String.valueOf((int) ir.getCantidad()) :
                                                ir.getCantidad() %>
                                                <%= ir.getUnidad_medida() %>
                                        </td>
                                        <td>
                                            <form action="editarCantidadIngrediente" method="post"
                                                style="display:inline;">
                                                <input type="hidden" name="cantidad" value="<%= ir.getCantidad() %>">
                                                <input type="hidden" name="idIngrediente"
                                                    value="<%= ir.getIngrediente().getId() %>">
                                                <input type="hidden" name="idReceta" value="<%= r.getId() %>">
                                                <input type="hidden" name="unidadMedida"
                                                    value="<%= ir.getUnidad_medida() %>">
                                                <input type="submit" value="Editar" class="action-btn edit-btn">
                                            </form>
                                        </td>
                                        <td>
                                            <form action="eliminarIngredienteReceta" method="post"
                                                style="display:inline;">
                                                <input type="hidden" name="idIngrediente"
                                                    value="<%= ir.getIngrediente().getId() %>">
                                                <input type="hidden" name="idReceta" value="<%= r.getId() %>">
                                                <input type="submit" value="Borrar" class="action-btn delete-btn"
                                                    onclick="return confirm('¿Estás seguro de que deseas eliminar este ingrediente?');">
                                            </form>
                                        </td>
                                    </tr>
                                    <% } %>
                            </tbody>
                        </table>
                        <div style="text-align: right; margin-top: 20px; margin-bottom:20px; padding: 0 20px;">
                            <form action="verMacrosReceta" method="post" style="display:inline;">
                                <input type="hidden" name="idReceta" value="<%= r.getId() %>">
                                <input type="submit" value="Ver macronutrientes" class="action-btn create-btn">
                            </form>
                            <form action="agregarIngrediente" method="post" style="display:inline;">
                                <input type="hidden" name="idReceta" value="<%= r.getId() %>">
                                <input type="submit" value="Agregar Ingrediente" class="action-btn create-btn">
                            </form>
                        </div>
                    </div>
                    <a href="index.html" style="color: red">Volver</a>
                </div>
            </body>

            </html>