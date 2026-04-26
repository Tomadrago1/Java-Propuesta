<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="style/global.css">
    <link rel="stylesheet" href="style/UserManagementstyles.css">
</head>
<body>
    <% request.setAttribute("pageTitle", "Gestionar Disponibilidad"); %>
    <jsp:include page="header.jsp" />
    
    <div class="container" style="margin-top: 30px;">
        <h1 style="display:none;">Gestionar mi Disponibilidad</h1>
        
        <% 
            String error = (String) session.getAttribute("errorDisponibilidad");
            if (error != null) {
        %>
            <div style="background-color: #f8d7da; color: #721c24; padding: 15px; border-radius: 5px; margin-bottom: 20px;">
                <%= error %>
            </div>
        <% 
                session.removeAttribute("errorDisponibilidad");
            } 
        %>

        <% 
            String exito = (String) session.getAttribute("mensajeExito");
            if (exito != null) {
        %>
            <div style="background-color: #d4edda; color: #155724; padding: 15px; border-radius: 5px; margin-bottom: 20px;">
                <%= exito %>
            </div>
        <% 
                session.removeAttribute("mensajeExito");
            } 
        %>

        <div class="filters-bar" style="flex-direction: column; align-items: stretch; margin-top: 20px;">
            <p style="margin-bottom: 20px; color: var(--gray-200); font-size: 1.05rem; text-align: center;">Carga los bloques de tiempo en los que estarás disponible para atender. El sistema automáticamente dividirá estos bloques en turnos de 30 minutos para tus pacientes.</p>
            
            <form action="guardarDisponibilidad" method="post" style="display: flex; flex-direction: column; gap: 20px; width: 100%; max-width: 600px; margin: 0 auto;">
                <div class="filter-group">
                    <label for="fecha" style="font-weight: 600; display: block; margin-bottom: 8px; color: var(--white); font-family: 'Montserrat', sans-serif;">Fecha:</label>
                    <input type="date" id="fecha" name="fecha" required class="filter-input" style="color-scheme: dark;">
                </div>
                
                <div style="display: flex; gap: 20px; width: 100%;">
                    <div class="filter-group" style="flex: 1;">
                        <label for="horaDesde" style="font-weight: 600; display: block; margin-bottom: 8px; color: var(--white); font-family: 'Montserrat', sans-serif;">Hora Inicio:</label>
                        <!-- step=1800 es para saltos de 30 minutos (30 * 60) -->
                        <input type="time" id="horaDesde" name="horaDesde" required step="1800" class="filter-input" style="color-scheme: dark;">
                    </div>
                    
                    <div class="filter-group" style="flex: 1;">
                        <label for="horaHasta" style="font-weight: 600; display: block; margin-bottom: 8px; color: var(--white); font-family: 'Montserrat', sans-serif;">Hora Fin:</label>
                        <input type="time" id="horaHasta" name="horaHasta" required step="1800" class="filter-input" style="color-scheme: dark;">
                    </div>
                </div>
                
                <div style="margin-top: 15px; text-align: center;">
                    <input type="submit" value="GUARDAR HORARIO" class="action-btn create-btn" style="width: 100%; font-size: 1.1rem; padding: 15px;">
                </div>
            </form>
        </div>

        <div style="margin-top: 30px; text-align: center;">
            <form action="signin" method="get">
                <input type="submit" value="VOLVER" class="action-btn delete-btn" style="padding: 10px 30px;">
            </form>
        </div>
    </div>
</body>
</html>
