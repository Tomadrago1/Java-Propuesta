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

/**
 * Servlet implementation class agregarRutinaUsuario
 */
@WebServlet("/agregarRutinaUsuario")
public class agregarRutinaUsuario extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public agregarRutinaUsuario() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // TODO Auto-generated method stub
    response.getWriter().append("Served at: ").append(request.getContextPath());
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    int id_rut = Integer.parseInt(request.getParameter("id_rut"));
    int id_usu = Integer.parseInt(request.getParameter("id_usu"));

    ctrlRutina ctrlR = new ctrlRutina();
    ctrlUsuario ctrlU = new ctrlUsuario();
    ctrlR.agregarRutinaUsuario(id_rut, id_usu);

    Usuario u = ctrlU.getUserById(id_usu);
    LinkedList<Rutina> rutinas = ctrlR.getRutinaByUsuario(id_usu);

    request.setAttribute("rutinas", rutinas);
    request.setAttribute("usuario", u);

    request.getRequestDispatcher("WEB-INF/misRutinas.jsp").forward(request, response);
  }
}