<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String pageTitle = (String) request.getAttribute("pageTitle");
if (pageTitle == null) {
    pageTitle = "Sistema";
}
%>
<header class="main-header">
  <a href="<%= request.getContextPath() %>/signin" class="logo-link">
    <img src="<%= request.getContextPath() %>/style/JarTrainingLogo.png" alt="Logo" class="header-logo" />
  </a>
  <h1 class="header-title"><%= pageTitle %></h1>
</header>
