<%@page import="entities.Ingrediente"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="style/editarUsuarioStyles.css">
    <title>Ingrediente</title>
</head>
<body>
<%
    Ingrediente ingrediente = (Ingrediente)request.getAttribute("Ingrediente");
	int idReceta = (int)request.getAttribute("idReceta");
	Double cantidad = null;
	String unidadMedida = null;
	try {
	    cantidad = (Double)request.getAttribute("cantidad");
	    unidadMedida = (String)request.getAttribute("unidadMedida");
	} catch (Exception e) {
	}
%>
    <div class="container">
        <h1>Editar Ingrediente</h1>
        <form action="guardarIngredienteReceta" method="post">
            <input type="hidden" name="idIngrediente" value="<%=ingrediente.getId()%>">
            <input type="hidden" name="idReceta" value="<%=idReceta%>">
            <div class="form-group">
                <label for="nombre">Nombre:</label>
                <input type="text" id="nombre" name="nombre" value="<%=ingrediente.getNombre()%>" readonly>
            </div>
            <div class="form-group">
                <label for="descripcion">Descripción:</label>
                <input type="text" id="descripcion" name="descripcion" value="<%=ingrediente.getDesc()%>" readonly>
            </div>
            <div class="form-group">
                <label for="cantidad">Cantidad:</label>
                <input type="number" id="cantidad" name="cantidad" step="0.01" value="<%= (cantidad != null) ? cantidad : "" %>" required>
			</div>
			<div class="form-group">
                <label for="unidadMedida">Unidad de Medida:</label>
                <select id="unidadMedida" name="unidadMedida" required>
                    <option value="GRAMOS" <%= ("GRAMOS".equals(unidadMedida) ? "selected" : "") %>>GRAMOS</option>
                    <option value="KG" <%= ("KG".equals(unidadMedida) ? "selected" : "") %>>KG</option>
                </select>
			</div>
            <div class="form-group">
                <input type="submit" value="Actualizar">
            </div>
        </form>
        <a href="index.html" style="color: red">Volver</a>
    </div>
</body>
</html>