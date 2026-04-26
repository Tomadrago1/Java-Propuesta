<%@page import="java.util.LinkedList" %>
<%@page import="entities.Profesion" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="style/UserManagementstyles.css">
    
    <% LinkedList<Profesion> profesiones = (LinkedList<Profesion>)request.getAttribute("listaProfesiones"); %>
</head>

<body>
    <div class="container">
        <h1>Gestión de Profesiones</h1>
        
        <!-- Barra de acciones y filtros (misma línea) -->
        <div class="filters-bar">
            <form action="crearProfesion" method="get" style="display:inline;" class="create-wrap">
                <input type="submit" value="+ Crear Profesión" class="action-btn create-btn">
            </form>
            <input type="text" id="searchName" class="filter-input" placeholder="🔍 Buscar por nombre..." onkeyup="filterTable()">
            <button type="button" class="action-btn clear-btn" onclick="clearFilters()">Limpiar</button>
        </div>

        <!-- Tabla de Profesiones -->
        <div class="div-table">
            <table id="profesionesTable">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Editar</th>
                        <th>Eliminar</th>
                    </tr>
                </thead>
                <tbody>
                    <% if(profesiones != null) { for (Profesion p : profesiones) { %>
                    <tr>
                        <td><%= p.getId_profesion() %></td>
                        <td><%= p.getNombre() %></td>
                        <td>
                            <form action="editarProfesion" method="post" style="display:inline;">
                                <input type="hidden" name="id" value="<%= p.getId_profesion() %>">
                                <input type="submit" value="Editar" class="action-btn edit-btn">
                            </form>
                        </td>
                        <td>
                            <form action="eliminarProfesion" method="post" style="display:inline;" onsubmit="return confirm('¿Estás seguro de que deseas eliminar esta profesión?');">
                                <input type="hidden" name="id" value="<%= p.getId_profesion() %>">
                                <input type="submit" value="Borrar" class="action-btn delete-btn">
                            </form>
                        </td>
                    </tr>
                    <% } } else { %>
                    <tr><td colspan="4" style="text-align: center;">No hay profesiones cargadas</td></tr>
                    <% } %>
                </tbody>
            </table>
        </div>
    <% request.setAttribute("pageTitle", "Gestión de Profesiones"); %>
    <jsp:include page="header.jsp" />
    </div>

    <script>
        function filterTable() {
            const searchName = document.getElementById('searchName').value.toLowerCase();
            const table = document.getElementById('profesionesTable');
            const rows = table.getElementsByTagName('tbody')[0].getElementsByTagName('tr');
            
            for (let i = 0; i < rows.length; i++) {
                // Check if it's the empty state row
                if (rows[i].getElementsByTagName('td').length === 1) continue;

                const cells = rows[i].getElementsByTagName('td');
                const nombre = cells[1].textContent.toLowerCase();
                
                const matchesName = nombre.includes(searchName);
                
                if (matchesName) {
                    rows[i].style.display = '';
                } else {
                    rows[i].style.display = 'none';
                }
            }
        }
        
        function clearFilters() {
            document.getElementById('searchName').value = '';
            filterTable();
        }
    </script>
</body>
</html>
