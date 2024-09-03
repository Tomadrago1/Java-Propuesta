<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="style/crear-editar.css">
    <script>
        function validatePassword() {
            var password = document.getElementById("password").value;
            var confirmPassword = document.getElementById("confirm_password").value;

            if (password !== confirmPassword) {
                document.getElementById("error-message").style.display = "block";
                return false;
            } else {
                document.getElementById("error-message").style.display = "none";
                return true;
            }
        }

        function toggleProfesionField() {
            var tipoUsuario = document.getElementById("tipoUsuario").value;
            var profesionField = document.getElementById("profesionField");

            if (tipoUsuario === "profesional") {
                profesionField.style.display = "block";
            } else {
                profesionField.style.display = "none";
            }
        }
    </script>
    <title>Crear Usuario</title>
</head>

<body>
    <div class="container">
        <h1>Crear Usuario</h1>
        <form action="GuardarUsuario" method="post">
            <div class="form-group">
                <label for="nombre">Nombre:</label>
                <input type="text" id="nombre" name="nombre" required>
            </div>
            <div class="form-group">
                <label for="apellido">Apellido:</label>
                <input type="text" id="apellido" name="apellido" required>
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required>
            </div>
            <div class="form-group">
                <label for="nombreUsuario">Nombre de Usuario:</label>
                <input type="text" id="nombreUsuario" name="nombreUsuario" required>
            </div>
            <div class="form-group">
                <label for="tipoUsuario">Tipo de Usuario:</label>
                <select id="tipoUsuario" name="tipoUsuario" required onchange="toggleProfesionField()">
                    <option value="">Seleccione un tipo de usuario</option>
                    <option value="administrador">Administrador</option>
                    <option value="cliente">Cliente</option>
                    <option value="profesional">Profesional</option>
                </select>
            </div>
            <div class="form-group" id="profesionField" style="display: none;">
                <label for="profesion">Profesion:</label>
                <input type="text" id="profesion" name="profesion">
            </div>
            <div class="form-group">
                <label for="password">Contraseña:</label>
                <input type="password" id="password" name="password" required>
            </div>
            <div class="form-group">
                <label for="confirm_password">Repetir Contraseña:</label>
                <input type="password" id="confirm_password" name="confirm_password" required>
            </div>
            <div id="error-message" style="color: red; display: none;">Las contraseñas no coinciden.</div>
            <div class="form-group">
                <input type="submit" onclick="return validatePassword()" value="Crear"></input>
            </div>
        </form>
        <a href="index.html" style="color: red">Volver</a>
    </div>
</body>

</html>