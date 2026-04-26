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
    <div class="container">
        <h1>Gestionar mi Disponibilidad</h1>
        
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

        <div style="background-color: #f9f9f9; padding: 20px; border-radius: 5px; border: 1px solid #ddd;">
            <p style="margin-bottom: 15px;">Carga los bloques de tiempo en los que estarás disponible para atender. El sistema automáticamente dividirá estos bloques en turnos de 30 minutos para tus pacientes.</p>
            
            <form action="guardarDisponibilidad" method="post" style="display: flex; flex-direction: column; gap: 15px;">
                <div>
                    <label for="fecha" style="font-weight: bold; display: block; margin-bottom: 5px;">Fecha:</label>
                    <input type="date" id="fecha" name="fecha" required style="padding: 8px; width: 200px; border: 1px solid #ccc; border-radius: 4px;">
                </div>
                
                <div style="display: flex; gap: 20px;">
                    <div>
                        <label for="horaDesde" style="font-weight: bold; display: block; margin-bottom: 5px;">Hora Inicio:</label>
                        <!-- step=1800 es para saltos de 30 minutos (30 * 60) -->
                        <input type="time" id="horaDesde" name="horaDesde" required step="1800" style="padding: 8px; width: 150px; border: 1px solid #ccc; border-radius: 4px;">
                    </div>
                    
                    <div>
                        <label for="horaHasta" style="font-weight: bold; display: block; margin-bottom: 5px;">Hora Fin:</label>
                        <input type="time" id="horaHasta" name="horaHasta" required step="1800" style="padding: 8px; width: 150px; border: 1px solid #ccc; border-radius: 4px;">
                    </div>
                </div>
                
                <div>
                    <input type="submit" value="Guardar Horario" class="action-btn create-btn" style="margin-top: 10px;">
                </div>
            </form>
        </div>

        <div style="margin-top: 30px;">
            <form action="vistaProfesional.jsp" method="get">
                <input type="submit" value="Volver" class="action-btn delete-btn" style="background-color: #6c757d;">
            </form>
        </div>
        
    <% request.setAttribute("pageTitle", "Gestionar Disponibilidad"); %>
    <jsp:include page="header.jsp" />
    </div>
</body>
</html>
