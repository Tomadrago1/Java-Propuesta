<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <link rel="stylesheet" href="style/crear-editar.css" />
    <title>Crear Ejercicio</title>
  </head>
  <body>
    <div class="container">
      <h1>Crear Ejercicio</h1>
      <form action="guardarEjercicio" method="post">
        <div class="form-group">
          <label for="nombre">Nombre:</label>
          <input type="text" id="nombre" name="nombre" required />
        </div>
        <div class="form-group">
          <label for="descripcion">Descripcion:</label>
          <input type="text" id="descripcion" name="descripcion" required />
        </div>
        <div class="form-group">
          <label for="zona">Zona:</label>
          <select id="zona" name="zona" required>
            <option value="">Seleccionar zona...</option>
            <option value="Pecho">Pecho</option>
            <option value="Espalda">Espalda</option>
            <option value="Hombros">Hombros</option>
            <option value="Bíceps">Bíceps</option>
            <option value="Tríceps">Tríceps</option>
            <option value="Piernas">Piernas</option>
            <option value="Glúteos">Glúteos</option>
            <option value="Abdomen">Abdomen</option>
            <option value="Cardio">Cardio</option>
            <option value="Full Body">Full Body</option>
          </select>
        </div>
        <div class="form-group">
          <label for="tipoEjercicio">Tipo de Ejercicio:</label>
          <select id="tipoEjercicio" name="tipoEjercicio" required>
            <option value="">Seleccionar tipo...</option>
            <option value="Barra">Barra</option>
            <option value="Mancuerna">Mancuerna</option>
            <option value="Polea">Polea</option>
            <option value="Máquina">Máquina</option>
            <option value="Peso Corporal">Peso Corporal</option>
            <option value="TRX">TRX</option>
            <option value="Banda Elástica">Banda Elástica</option>
            <option value="Kettlebell">Kettlebell</option>
            <option value="Cardio">Cardio</option>
          </select>
        </div>
        <div class="form-group">
          <input type="submit" value="Crear" />
        </div>
      </form>
      <% request.setAttribute("pageTitle", "Crear Ejercicio"); %>
      <jsp:include page="../header.jsp" />
    </div>
  </body>
</html>
