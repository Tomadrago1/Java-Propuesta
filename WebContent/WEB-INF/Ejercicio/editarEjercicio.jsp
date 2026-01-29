<%@page import="entities.Ejercicio"%> <%@page import="data.DaoEjercicio"%> <%@
page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <link rel="stylesheet" href="style/crear-editar.css" />
    <title>Editar Ejercicio</title>
  </head>
  <body>
    <% //Aca se cambian los atributos del ejercicio y se mandan los nuevos atributos al servlet actualizarEjercicio
    Ejercicio eje = (Ejercicio)request.getAttribute("ejercicio"); %>
    <div class="container">
      <h1>Editar Ejercicio</h1>
      <form action="actualizarEjercicio" method="post">
        <input type="hidden" name="id" value="<%=eje.getId()%>" />
        <div class="form-group">
          <label for="nombre">Nombre:</label>
          <input
            type="text"
            id="nombre"
            name="nombre"
            value="<%=eje.getNombre()%>"
          />
        </div>
        <div class="form-group">
          <label for="descripcion">Descripcion:</label>
          <input
            type="text"
            id="descripcion"
            name="descripcion"
            value="<%=eje.getDescripcion()%>"
          />
        </div>
        <div class="form-group">
          <label for="zona">Zona:</label>
          <select id="zona" name="zona" required>
            <option value="">Seleccionar zona...</option>
            <option value="Pecho" <%= "Pecho".equals(eje.getZona()) ? "selected" : "" %>>Pecho</option>
            <option value="Espalda" <%= "Espalda".equals(eje.getZona()) ? "selected" : "" %>>Espalda</option>
            <option value="Hombros" <%= "Hombros".equals(eje.getZona()) ? "selected" : "" %>>Hombros</option>
            <option value="Bíceps" <%= "Bíceps".equals(eje.getZona()) ? "selected" : "" %>>Bíceps</option>
            <option value="Tríceps" <%= "Tríceps".equals(eje.getZona()) ? "selected" : "" %>>Tríceps</option>
            <option value="Piernas" <%= "Piernas".equals(eje.getZona()) ? "selected" : "" %>>Piernas</option>
            <option value="Glúteos" <%= "Glúteos".equals(eje.getZona()) ? "selected" : "" %>>Glúteos</option>
            <option value="Abdomen" <%= "Abdomen".equals(eje.getZona()) ? "selected" : "" %>>Abdomen</option>
            <option value="Cardio" <%= "Cardio".equals(eje.getZona()) ? "selected" : "" %>>Cardio</option>
            <option value="Full Body" <%= "Full Body".equals(eje.getZona()) ? "selected" : "" %>>Full Body</option>
          </select>
        </div>
        <div class="form-group">
          <label for="tipoEjercicio">Tipo de Ejercicio:</label>
          <select id="tipoEjercicio" name="tipoEjercicio" required>
            <option value="">Seleccionar tipo...</option>
            <option value="Barra" <%= "Barra".equals(eje.getTipoEjercicio()) ? "selected" : "" %>>Barra</option>
            <option value="Mancuerna" <%= "Mancuerna".equals(eje.getTipoEjercicio()) ? "selected" : "" %>>Mancuerna</option>
            <option value="Polea" <%= "Polea".equals(eje.getTipoEjercicio()) ? "selected" : "" %>>Polea</option>
            <option value="Máquina" <%= "Máquina".equals(eje.getTipoEjercicio()) ? "selected" : "" %>>Máquina</option>
            <option value="Peso Corporal" <%= "Peso Corporal".equals(eje.getTipoEjercicio()) ? "selected" : "" %>>Peso Corporal</option>
            <option value="TRX" <%= "TRX".equals(eje.getTipoEjercicio()) ? "selected" : "" %>>TRX</option>
            <option value="Banda Elástica" <%= "Banda Elástica".equals(eje.getTipoEjercicio()) ? "selected" : "" %>>Banda Elástica</option>
            <option value="Kettlebell" <%= "Kettlebell".equals(eje.getTipoEjercicio()) ? "selected" : "" %>>Kettlebell</option>
            <option value="Cardio" <%= "Cardio".equals(eje.getTipoEjercicio()) ? "selected" : "" %>>Cardio</option>
          </select>
        </div>
        <div class="form-group">
          <input type="submit" value="Actualizar" />
        </div>
      </form>
      <% request.setAttribute("pageTitle", "Editar Ejercicio"); %>
      <jsp:include page="../header.jsp" />
    </div>
  </body>
</html>
