<%@page import="java.util.LinkedList" %>
<%@ page import="entities.Usuario" %>
<%@page import="entities.Rutina" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="style/global.css">
  <link rel="stylesheet" href="style/UserManagementstyles.css">
  <title>Mis Rutinas</title>

  <% 
    LinkedList<Rutina> lr = (LinkedList<Rutina>) request.getAttribute("rutinas");
    Usuario u = (Usuario)request.getAttribute("usuario");
    if (u == null) {
      u = (Usuario)session.getAttribute("usuario");
    }
  %>
</head>

<body>
  <%-- Header global --%>
  <% request.setAttribute("pageTitle", "Mis Rutinas"); %>
  <jsp:include page="header.jsp" />
  
  <div class="container">
    <!-- Barra de filtros -->
    <div class="filters-bar">
      <form action="crearRutinaUsuario" method="post" style="display:inline;" class="create-wrap">
        <input type="hidden" name="id_usu" value="<%=u.getIdUsuario()%>">
        <input type="submit" value="+ Agregar Rutina" class="action-btn create-btn">
      </form>
      <input type="text" id="searchRutina" class="filter-input" placeholder="🔍 Buscar por nombre o descripción..." onkeyup="filterTable()">
      <button type="button" class="action-btn clear-btn" onclick="clearFilters()">Limpiar</button>
    </div>
    
    <div class="div-table">
      <table id="rutinasTable">
        <thead>
          <tr>
            <th></th>
            <th>Nombre</th>
            <th>Descripción</th>
            <th>Eliminar Rutina</th>
          </tr>
        </thead>
        <tbody>
          <% for (Rutina rut : lr) {%>
            <tr>
              <td>
                <form action="iniciarEntrenamiento" method="post" class="inline-form">
                  <input type="hidden" name="id_rut" value="<%=rut.getId()%>">
                  <input type="hidden" name="id_usu" value="<%=u.getIdUsuario()%>">
                  <input type="submit" value="Iniciar Entrenamiento" class="action-btn viewmore-btn">
                </form>
              </td>
              <td><%=rut.getNombre()%></td>
              <td><%=rut.getDescripcion()%></td>
              <td class="actions">
                <form action="eliminarRutinaUsuario" method="post" class="inline-form">
                  <input type="hidden" name="id_rut" value="<%=rut.getId()%>">
                  <input type="hidden" name="id_usu" value="<%=u.getIdUsuario()%>">
                  <input type="submit" value="Borrar" class="action-btn delete-btn"
                    onclick="return confirm('¿Estás seguro de que deseas eliminar esta rutina?');">
                </form>
              </td>
            </tr>
          <%}%>
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
        const nombre = cells[1].textContent.toLowerCase();
        const descripcion = cells[2].textContent.toLowerCase();
        
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