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
    <link rel="stylesheet" href="style/global.css">
    <link rel="stylesheet" href="style/UserManagementstyles.css">
    
    <%
        LinkedList<Receta> lr = (LinkedList<Receta>)request.getAttribute("listaRecetas");
    %>
    
</head>
<body>
    <%-- Header global --%>
    <% request.setAttribute("pageTitle", "Recetas"); %>
    <jsp:include page="../header.jsp" />
    <div class="container">
        <!-- Barra de acciones y filtros (misma línea) -->
        <div class="filters-bar">
            <form action="crearReceta" method="post" style="display:inline;" class="create-wrap">
                <input type="submit" value="+ Crear Receta" class="action-btn create-btn">
            </form>
            <input type="text" id="searchName" class="filter-input" placeholder="🔍 Buscar por nombre o descripción..." onkeyup="filterTable()">
            <select id="filterDificultad" class="filter-select" onchange="filterTable()">
                <option value="">Todas las dificultades</option>
                <%
                    // Obtener niveles de dificultad únicos
                    java.util.Set<String> dificultades = new java.util.LinkedHashSet<String>();
                    for (Receta rec : lr) {
                        String dif = rec.getNivelDificultad();
                        if (dif != null && !dif.isEmpty()) {
                            dificultades.add(dif);
                        }
                    }
                    for (String dif : dificultades) {
                %>
                <option value="<%=dif%>"><%=dif%></option>
                <% } %>
            </select>
            <select id="filterProfesional" class="filter-select" onchange="filterTable()">
                <option value="">Todos los profesionales</option>
                <%
                    // Obtener profesionales únicos
                    java.util.Set<String> profesionales = new java.util.LinkedHashSet<String>();
                    for (Receta rec : lr) {
                        Profesional p = rec.getProfesional();
                        if (p != null) {
                            String nombreCompleto = p.getNombre() + " " + p.getApellido();
                            profesionales.add(nombreCompleto);
                        }
                    }
                    for (String prof : profesionales) {
                %>
                <option value="<%=prof%>"><%=prof%></option>
                <% } %>
            </select>
            <button type="button" class="action-btn clear-btn" onclick="clearFilters()">Limpiar</button>
        </div>
        
        <div class="div-table">
            <table id="recetasTable">
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
                        <td>
                            <form action="editarReceta" method="post" style="display:inline;">
                                <input type="hidden" name="id" value="<%=rec.getId()%>">
                                <input type="submit" value="Editar" class="action-btn edit-btn">
                            </form>
                        </td>
                        <td>
                            <form action="eliminarReceta" method="post" style="display:inline;">
                                <input type="hidden" name="id" value="<%=rec.getId()%>">
                                <input type="submit" value="Borrar" class="action-btn delete-btn" onclick="return confirm('¿Estás seguro de que deseas eliminar esta receta?');">
                            </form>
                        </td>
                    </tr>
                    <% 
                    }
                    %>
                </tbody>
            </table>
        </div>
        <a href="index.html">Volver</a>
    </div>
    
    <script>
        function filterTable() {
            const searchName = document.getElementById('searchName').value.toLowerCase();
            const filterDificultad = document.getElementById('filterDificultad').value;
            const filterProfesional = document.getElementById('filterProfesional').value;
            
            const table = document.getElementById('recetasTable');
            const rows = table.getElementsByTagName('tbody')[0].getElementsByTagName('tr');
            
            for (let i = 0; i < rows.length; i++) {
                const cells = rows[i].getElementsByTagName('td');
                const nombre = cells[1].textContent.toLowerCase();
                const descripcion = cells[2].textContent.toLowerCase();
                const dificultad = cells[3].textContent.trim();
                const profesional = cells[4].textContent.trim();
                
                const matchesSearch = nombre.includes(searchName) || descripcion.includes(searchName);
                const matchesDificultad = filterDificultad === '' || dificultad === filterDificultad;
                const matchesProfesional = filterProfesional === '' || profesional === filterProfesional;
                
                if (matchesSearch && matchesDificultad && matchesProfesional) {
                    rows[i].style.display = '';
                } else {
                    rows[i].style.display = 'none';
                }
            }
        }
        
        function clearFilters() {
            document.getElementById('searchName').value = '';
            document.getElementById('filterDificultad').value = '';
            document.getElementById('filterProfesional').value = '';
            filterTable();
        }
    </script>
</body>
</html>