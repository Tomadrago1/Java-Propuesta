<%@page import="java.util.LinkedList" %>
<%@page import="entities.Rutina" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
    LinkedList<Rutina> lr = (LinkedList<Rutina>)request.getAttribute("rutinas");
    LinkedList<Rutina> rutinasDelUsuario = (LinkedList<Rutina>)request.getAttribute("rutinasDelUsuario");
    int id_usu = (int)request.getAttribute("id_usu");
  %>

</head>

<body>
  <%-- Header global --%>
  <% request.setAttribute("pageTitle", "Agregar Rutinas"); %>
  <jsp:include page="header.jsp" />
  
  <div class="container">
    <!-- Barra de filtros -->
    <div class="filters-bar">
      <input type="text" id="searchRutina" class="filter-input" placeholder="🔍 Buscar por nombre o descripción..." onkeyup="filterTable()">
      <button type="button" class="action-btn clear-btn" onclick="clearFilters()">Limpiar</button>
    </div>
    
    <div class="div-table">
      <table id="rutinasTable">
        <thead>
          <tr>
            <th>Nombre</th>
            <th>Descripción</th>
            <th>Agregar Rutina</th>
          </tr>
        </thead>
        <tbody>
          <% for (Rutina r : lr) { 
            // Verificar si el usuario ya tiene esta rutina
            boolean yaAgregada = false;
            if (rutinasDelUsuario != null) {
              for (Rutina rutinaUsuario : rutinasDelUsuario) {
                if (rutinaUsuario.getId() == r.getId()) {
                  yaAgregada = true;
                  break;
                }
              }
            }
          %>
            <tr>
              <td><%=r.getNombre()%></td>
              <td><%=r.getDescripcion()%></td>
              <td>
                <% if (yaAgregada) { %>
                  <button type="button" class="action-btn disabled-btn" disabled>Ya agregada</button>
                <% } else { %>
                  <form action="agregarRutinaUsuario" method="post" style="display:inline;">
                    <input type="hidden" name="id_rut" value="<%=r.getId()%>">
                    <input type="hidden" name="id_usu" value="<%=id_usu%>">
                    <input type="submit" value="Agregar" class="action-btn create-btn"
                      onclick="return confirm('¿Estás seguro de que deseas agregar esta rutina?');">
                  </form>
                <% } %>
              </td>
            </tr>
          <% } %>
        </tbody>
      </table>
    </div>
    
    <div style="text-align: center; margin-top: 1.5rem;">
      <a href="javascript:history.back()" class="action-btn edit-btn" style="display: inline-block; text-decoration: none;">
        Volver
      </a>
    </div>
  </div>
  
  <script>
    function filterTable() {
      const searchTerm = document.getElementById('searchRutina').value.toLowerCase();
      
      const table = document.getElementById('rutinasTable');
      const rows = table.getElementsByTagName('tbody')[0].getElementsByTagName('tr');
      
      for (let i = 0; i < rows.length; i++) {
        const cells = rows[i].getElementsByTagName('td');
        const nombre = cells[0].textContent.toLowerCase();
        const descripcion = cells[1].textContent.toLowerCase();
        
        const matchesSearch = nombre.includes(searchTerm) || descripcion.includes(searchTerm);
        
        if (matchesSearch) {
          rows[i].style.display = '';
        } else {
          rows[i].style.display = 'none';
        }
      }
    }
    
    function clearFilters() {
      document.getElementById('searchRutina').value = '';
      filterTable();
    }
  </script>
</body>

</html>