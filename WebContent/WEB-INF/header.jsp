<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="entities.Usuario" %>
<%
String pageTitle = (String) request.getAttribute("pageTitle");
if (pageTitle == null) {
    pageTitle = "Sistema";
}

// Obtener el usuario de la sesión para determinar el home correcto
Usuario usuario = (Usuario) session.getAttribute("usuario");
String homeUrl = request.getContextPath() + "/signin";
%>
<header class="main-header">
  <a href="<%= homeUrl %>" class="logo-link">
    <img src="<%= request.getContextPath() %>/style/JarTrainingLogo.png" alt="Logo" class="header-logo" />
  </a>
  <h1 class="header-title"><%= pageTitle %></h1>
</header>
