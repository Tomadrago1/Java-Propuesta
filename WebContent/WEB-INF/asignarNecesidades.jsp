<%@page import="entities.Necesidad"%>
<%@page import="java.util.LinkedList"%>
<%@page import="entities.Nutriente"%>
<%@page import="entities.Usuario"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="style/global.css">
    <link rel="stylesheet" href="style/UserManagementstyles.css">
    <%
        Usuario paciente = (Usuario) request.getAttribute("paciente");
        LinkedList<Nutriente> nutrientes = (LinkedList<Nutriente>) request.getAttribute("nutrientes");
        LinkedList<Necesidad> necesidadesHoy = (LinkedList<Necesidad>) request.getAttribute("necesidadesHoy");
    %>
</head>
<body>
    <% request.setAttribute("pageTitle", "Asignar Necesidades"); %>
    <jsp:include page="header.jsp" />
    
    <div class="container" style="margin-top: 30px;">
        <h1 style="display:none;">Asignar Necesidades Nutricionales</h1>
        
        <div class="filters-bar" style="flex-direction: column; align-items: stretch; max-width: 800px; margin: 0 auto;">
            <% if (paciente != null) { %>
                <div style="background: rgba(255, 255, 255, 0.05); padding: 20px; border-radius: 8px; margin-bottom: 25px; border-left: 4px solid var(--primary); text-align: center;">
                    <h3 style="color: var(--white); font-family: 'Montserrat', sans-serif; margin-bottom: 5px;">Paciente: <%=paciente.getNombre()%> <%=paciente.getApellido()%></h3>
                    <p style="color: var(--gray-300); font-size: 0.9rem;">Asigna el rango diario recomendado para cada macronutriente.</p>
                </div>

                <form action="guardarNecesidades" method="post" style="display: flex; flex-direction: column; gap: 15px;">
                    <input type="hidden" name="id_paciente" value="<%=paciente.getIdUsuario()%>">
                    
                    <% if (nutrientes != null && !nutrientes.isEmpty()) { %>
                        <div class="div-table" style="background: rgba(0,0,0,0.2); padding: 15px;">
                            <table style="width: 100%; border-collapse: separate; border-spacing: 0 10px;">
                                <thead>
                                    <tr>
                                        <th style="text-align: left; padding: 10px; color: var(--white);">Nutriente</th>
                                        <th style="text-align: center; padding: 10px; color: var(--white);">Mínimo (g)</th>
                                        <th style="text-align: center; padding: 10px; color: var(--white);">Máximo (g)</th>
                                        <th style="text-align: center; padding: 10px; color: var(--white);">Acción</th>
                                    </tr>
                                </thead>
                                <tbody id="nutrientes-tbody">
                                </tbody>
                            </table>
                        </div>
                        
                        <div style="text-align: left; margin-top: 10px;">
                            <button type="button" class="action-btn viewmore-btn" onclick="agregarFilaNutriente()" style="padding: 10px 20px;">+ Agregar Nutriente</button>
                        </div>
                        
                        <div style="text-align: center; margin-top: 20px;">
                            <input type="submit" value="GUARDAR PLAN NUTRICIONAL" class="action-btn create-btn" style="width: 100%; max-width: 350px; font-size: 1.1rem; padding: 15px;">
                        </div>
                    <% } else { %>
                        <div style="text-align: center; color: var(--warning); padding: 20px;">
                            No hay nutrientes cargados en el sistema.
                        </div>
                    <% } %>
                </form>
            <% } else { %>
                <div style="padding: 20px; background: rgba(239, 68, 68, 0.1); border-left: 4px solid var(--error); color: var(--gray-100); border-radius: 5px; text-align: center;">
                    No se encontró el paciente.
                </div>
            <% } %>
        </div>

        <div style="margin-top: 30px; text-align: center;">
            <form action="signin" method="get">
                <input type="submit" value="VOLVER AL PANEL" class="action-btn delete-btn" style="padding: 10px 30px;">
            </form>
        </div>
    </div>

    <script>
        const nutrientesDisponibles = [
            <% if (nutrientes != null) {
                for(int i=0; i < nutrientes.size(); i++) { 
                    Nutriente n = nutrientes.get(i);
            %>
                { id: <%=n.getId_nutriente()%>, nombre: "<%=n.getNombre()%>" }<%= (i < nutrientes.size()-1) ? "," : "" %>
            <%  }
               } %>
        ];

        function agregarFilaNutriente(idSeleccionado = '', minVal = '', maxVal = '') {
            const tbody = document.getElementById('nutrientes-tbody');
            const rowId = 'row_' + Date.now() + Math.random().toString().substr(2, 5);
            
            let options = '<option value="" disabled ' + (!idSeleccionado ? 'selected' : '') + '>Seleccionar...</option>';
            nutrientesDisponibles.forEach(n => {
                options += '<option value="' + n.id + '" ' + (n.id == idSeleccionado ? 'selected' : '') + '>' + n.nombre + '</option>';
            });

            const tr = document.createElement('tr');
            tr.style.background = 'rgba(255,255,255,0.05)';
            
            tr.innerHTML = 
                '<td style="padding: 15px; border-radius: 8px 0 0 8px;">' +
                    '<select name="id_nutriente" class="filter-select" required style="width: 100%; min-width: 150px;">' +
                        options +
                    '</select>' +
                '</td>' +
                '<td style="padding: 10px; text-align: center;">' +
                    '<input type="number" step="0.01" min="0" name="min_dinamico" value="' + minVal + '" class="filter-input" style="width: 100px; text-align: center;" placeholder="0.0" required>' +
                '</td>' +
                '<td style="padding: 10px; text-align: center;">' +
                    '<input type="number" step="0.01" min="0" name="max_dinamico" value="' + maxVal + '" class="filter-input" style="width: 100px; text-align: center;" placeholder="0.0" required>' +
                '</td>' +
                '<td style="padding: 10px; text-align: center; border-radius: 0 8px 8px 0;">' +
                    '<button type="button" class="action-btn delete-btn" onclick="this.closest(\'tr\').remove()" style="padding: 5px 10px;">Quitar</button>' +
                '</td>';
                
            tbody.appendChild(tr);
        }

        // Precargar necesidades de hoy si existen
        document.addEventListener("DOMContentLoaded", function() {
            let hayPrecarga = false;
            <% if (necesidadesHoy != null && !necesidadesHoy.isEmpty()) { 
                for (Necesidad nec : necesidadesHoy) { %>
                    agregarFilaNutriente('<%=nec.getId_nutriente()%>', '<%=nec.getCantidad_min()%>', '<%=nec.getCantidad_max()%>');
                    hayPrecarga = true;
            <%  }
               } %>
            
            // Si no hay nada precargado, agregar una fila vacía por defecto
            if (!hayPrecarga && nutrientesDisponibles.length > 0) {
                agregarFilaNutriente();
            }
        });
    </script>
    </div>
</body>
</html>
