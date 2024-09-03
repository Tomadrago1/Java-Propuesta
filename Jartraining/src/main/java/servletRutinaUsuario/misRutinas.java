package servletRutinaUsuario;

import java.io.IOException;
import java.util.LinkedList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entities.Rutina;
import entities.Usuario;
import logic.ctrlRutina;
import logic.ctrlUsuario;

@WebServlet("/misRutinas")
public class misRutinas extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));

    ctrlRutina ctrlRutina = new ctrlRutina();
    ctrlUsuario ctrlU = new ctrlUsuario();

    Usuario u = ctrlU.getUserById(id_usuario);
    LinkedList<Rutina> rutinas = ctrlRutina.getRutinaByUsuario(id_usuario);

    request.setAttribute("rutinas", rutinas);
    request.setAttribute("usuario", u);
    request.getRequestDispatcher("WEB-INF/misRutinas.jsp").forward(request, response);
  }
}