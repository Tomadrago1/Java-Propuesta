package consultaUsuario;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Profesional;
import logic.ctrlUsuario;

/**
 * Servlet implementation class editarNutriente
 */
@WebServlet("/agendarConsulta")
public class agendarConsulta extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public agendarConsulta() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.getWriter().append("Served at: ").append(request.getContextPath());
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    ctrlUsuario ctrl = new ctrlUsuario();
    LinkedList<String> profesiones = ctrl.getAllProfesiones();
    request.setAttribute("profesiones", profesiones);
    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/agendarConsulta.jsp");
    dispatcher.forward(request, response);
  }
}
