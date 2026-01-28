<%@page import="java.util.LinkedList"%>
<%@page import="entities.Usuario"%>
<%@page import="entities.Profesional" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="style/global.css">
    <link rel="stylesheet" href="style/UserManagementstyles.css">
    
    <%
        Usuario u = (Usuario)session.getAttribute("usuario");
        LinkedList<Usuario> lu = (LinkedList<Usuario>)request.getAttribute("listaUsuarios");
    %>
    
</head>
<body>
    <%-- Header global --%>
    <% request.setAttribute("pageTitle", "Usuarios"); %>
    <jsp:include page="../header.jsp" />
    <div class="container">
        <!-- Barra de acciones y filtros (misma línea) -->
        <div class="filters-bar">
            <form action="crearUsuario" method="post" style="display:inline;" class="create-wrap">
                <input type="submit" value="+ Crear Usuario" class="action-btn create-btn">
            </form>
            <input type="text" id="searchName" class="filter-input" placeholder="🔍 Buscar por nombre..." onkeyup="filterTable()">
            <select id="filterTipo" class="filter-select" onchange="filterTable()">
                <option value="">Todos los tipos</option>
                <option value="Admin">Admin</option>
                <option value="Profesional">Profesional</option>
                <option value="Cliente">Cliente</option>
            </select>
            <select id="filterProfesion" class="filter-select" onchange="filterTable()">
                <option value="">Todas las profesiones</option>
                <%
                    // Obtener profesiones únicas
                    java.util.Set<String> profesiones = new java.util.HashSet<String>();
                    for (Usuario usu : lu) {
                        if (usu instanceof Profesional) {
                            String prof = ((Profesional)usu).getProfesion();
                            if (prof != null && !prof.isEmpty()) {
                                profesiones.add(prof);
                            }
                        }
                    }
                    for (String prof : profesiones) {
                %>
                <option value="<%=prof%>"><%=prof%></option>
                <% } %>
            </select>
            <button type="button" class="action-btn clear-btn" onclick="clearFilters()">Limpiar</button>
        </div>
        
        <div class="div-table">
            <table id="usersTable">
                <thead>
                    <tr>
                        <th>Nombre</th>
                        <th>Apellido</th>
                        <th>Email</th>
                        <th>Nombre de Usuario</th>
                        <th>Tipo Usuario</th>
                        <th>Profesion</th>
                        <th>Editar usuario</th>
                        <th>Baja usuario</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Usuario usu : lu) { 
                    if (usu.getEstado()) { // Muestra solo si el estado es true (o 1)
                    %>
                    <tr>
                        <td><%=usu.getNombre()%></td>
                        <td><%=usu.getApellido()%></td>
                        <td><%=usu.getEmail()%></td>
                        <td><%=usu.getNombreUsuario()%></td>
						<td>
                        <%
                            int tipoUsuario = usu.getTipoUsu();
                            String tipoUsuarioStr = "";
                            
                            switch (tipoUsuario) {
                                case 1:
                                    tipoUsuarioStr = "Admin";
                                    break;
                                case 2:
                                    tipoUsuarioStr = "Profesional";
                                    break;
                                case 3:
                                    tipoUsuarioStr = "Cliente";
                                    break;
                                default:
                                    tipoUsuarioStr = "Desconocido";
                                    break;
                            }
                        %>
                        <%= tipoUsuarioStr %>
						</td>
                        <td>
                            <% String profesion="-" ; if (usu instanceof Profesional) {
                                profesion=((Profesional)usu).getProfesion();} %>
                                <%= profesion !=null ? profesion : "-" %>
                        </td>
                        <td>
                            <form action="editarUsuario" method="post" style="display:inline;">
                                <input type="hidden" name="id" value="<%=usu.getIdUsuario()%>">
                                <input type="hidden" name="tipo_usu" value="<%=usu.getTipoUsu()%>">
                                <input type="submit" value="Editar" class="action-btn edit-btn">
                            </form>
                        </td>
                        <td>
                            <form action="EliminarUsuario" method="post" style="display:inline;">
                                <input type="hidden" name="id" value="<%=usu.getIdUsuario()%>">
                                <input type="submit" value="Borrar" class="action-btn delete-btn" onclick="return confirm('¿Estás seguro de que deseas eliminar este usuario?');">
                            </form>
                        </td>
                    </tr>
                    <% 
                    } // Fin de la condición
                    } %>
                </tbody>
            </table>
        </div>
        <a href="index.html">Volver</a>
    </div>
    
    <script>
        function filterTable() {
            const searchName = document.getElementById('searchName').value.toLowerCase();
            const filterTipo = document.getElementById('filterTipo').value;
            const filterProfesion = document.getElementById('filterProfesion').value;
            
            const table = document.getElementById('usersTable');
            const rows = table.getElementsByTagName('tbody')[0].getElementsByTagName('tr');
            
            for (let i = 0; i < rows.length; i++) {
                const cells = rows[i].getElementsByTagName('td');
                const nombre = cells[0].textContent.toLowerCase();
                const apellido = cells[1].textContent.toLowerCase();
                const tipoUsuario = cells[4].textContent.trim();
                const profesion = cells[5].textContent.trim();
                
                const matchesName = nombre.includes(searchName) || apellido.includes(searchName);
                const matchesTipo = filterTipo === '' || tipoUsuario === filterTipo;
                const matchesProfesion = filterProfesion === '' || profesion === filterProfesion;
                
                if (matchesName && matchesTipo && matchesProfesion) {
                    rows[i].style.display = '';
                } else {
                    rows[i].style.display = 'none';
                }
            }
        }
        
        function clearFilters() {
            document.getElementById('searchName').value = '';
            document.getElementById('filterTipo').value = '';
            document.getElementById('filterProfesion').value = '';
            filterTable();
        }
    </script>
</body>
</html>