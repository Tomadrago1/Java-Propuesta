package servletConsulta;

import java.io.IOException;

import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Profesional;
import logic.ctrlUsuario;

// Aca se modifica el ejercicio y vuelve a la lista de ejercicios

@WebServlet("/seleccionarProfesion")
public class seleccionarProfesion extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String profesion = request.getParameter("profesion");
    ctrlUsuario ctrl = new ctrlUsuario();
    LinkedList<Profesional> profesionales = ctrl.getProfesionalesByProfesion(profesion);
    request.setAttribute("profesionales", profesionales);
    request.getRequestDispatcher("WEB-INF/profesionalesConsultaLista.jsp").forward(request, response);
  }
}