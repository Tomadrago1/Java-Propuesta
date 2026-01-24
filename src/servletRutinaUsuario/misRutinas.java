package servletRutinaUsuario;

import java.io.IOException;
import java.util.LinkedList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import entities.Rutina;
import entities.Usuario;
import logic.ctrlRutina;
import logic.ctrlUsuario;

@WebServlet("/misRutinas")
public class misRutinas extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    int id_usuario = Integer.parseInt(request.getParameter("id"));

    ctrlRutina ctrlRutina = new ctrlRutina();
    ctrlUsuario ctrlU = new ctrlUsuario();

    Usuario u = ctrlU.getUserById(id_usuario);
    LinkedList<Rutina> rutinas = ctrlRutina.getRutinaByUsuario(id_usuario);

    request.setAttribute("rutinas", rutinas);
    request.setAttribute("usuario", u);
    request.getRequestDispatcher("WEB-INF/misRutinas.jsp").forward(request, response);
  }
}