package servletRutinaUsuario;

import java.io.IOException;
import java.util.LinkedList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import entities.Rutina;
import entities.Usuario;
import logic.ctrlRutina;
import logic.ctrlUsuario;

/**
 * Servlet implementation class eliminarRutinaUsuario
 */
@WebServlet("/eliminarRutinaUsuario")
public class eliminarRutinaUsuario extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public eliminarRutinaUsuario() {
    super();
  }
  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int id_rut = Integer.parseInt(request.getParameter("id_rut"));
    int id_usu = Integer.parseInt(request.getParameter("id_usu"));
    ctrlRutina ctrlR = new ctrlRutina();
    boolean success = ctrlR.borrarRutinaUsuario(id_usu, id_rut);
    if (success) {

      ctrlUsuario ctrlU = new ctrlUsuario();

      Usuario u = ctrlU.getUserById(id_usu);
      LinkedList<Rutina> rutinas = ctrlR.getRutinaByUsuario(id_usu);

      request.setAttribute("rutinas", rutinas);
      request.setAttribute("usuario", u);
      RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/misRutinas.jsp");
      dispatcher.forward(request, response);
    } else {
      response.getWriter().append("Error");
    }
  }

}