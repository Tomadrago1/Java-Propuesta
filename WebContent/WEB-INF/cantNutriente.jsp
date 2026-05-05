<%@page import="entities.Nutriente"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="style/editarUsuarioStyles.css">
    <title>Nutriente</title>
</head>
<body>
<%
  Nutriente nutriente = (Nutriente)request.getAttribute("Nutriente");
	int idIngrediente = (int)request.getAttribute("idIngrediente");
	Double cantidad = null;
	String unidadMedida = null;
	try {
    cantidad = (Double)request.getAttribute("cantidad");
    unidadMedida = (String)request.getAttribute("unidadMedida");
	} catch (Exception e) {
	}
%>
    <div class="container">
        <h1>Editar Nutriente</h1>
        <form action="guardarNutrienteIngrediente" method="post">
            <input type="hidden" name="idNutriente" value="<%=nutriente.getId_nutriente()%>">
            <input type="hidden" name="idIngrediente" value="<%=idIngrediente%>">
            <div class="form-group">
                <label for="nombre">Nombre:</label>
                <input type="text" id="nombre" name="nombre" value="<%=nutriente.getNombre()%>" readonly>
            </div>
            <div class="form-group">
                <label for="descripcion">Descripción:</label>
                <input type="text" id="descripcion" name="descripcion" value="<%=nutriente.getDescripcion()%>" readonly>
            </div>
            <div class="form-group">
            <label for="cantidad ">Cantidad cada 100 gramos:</label>
            <input type="number" id="cantidad" name="cantidad" step="0.01" value="<%= (cantidad != null) ? cantidad : "" %>" required>
			</div>
            <div class="form-group">
                <input type="submit" value="Actualizar">
            </div>
        </form>
    <% request.setAttribute("pageTitle", "Cantidad Nutriente"); %>
    <jsp:include page="header.jsp" />
    </div>
</body>
</html>