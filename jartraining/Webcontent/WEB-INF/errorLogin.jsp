<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
     <link rel="stylesheet" href="style/errorLoginStyles.css">
    <title>Error de Inicio de Sesión</title>  
</head>
<body>
    <div id="contenedor">
        <div class="titulo">Error de Inicio de Sesión</div>
        <div class="error">
            <p><%= request.getAttribute("errorMessage") %></p>
        </div>
        <form action="index.html" method="get">
            <input type="submit" value="Volver al Login" class="boton-volver">
        </form>
    </div>
</body>
</html>
