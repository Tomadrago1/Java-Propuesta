package servletNutriente;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Nutriente;
import logic.ctrlNutriente;

/**
 * Servlet implementation class editarNutriente
 */
@WebServlet("/editarNutriente")
public class editarNutriente extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public editarNutriente() {
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
    int idNutriente = Integer.parseInt(request.getParameter("idNutriente"));
    ctrlNutriente ctrl = new ctrlNutriente();
    Nutriente nutriente = ctrl.getById(idNutriente);
    request.setAttribute("nutriente", nutriente);
    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Nutriente/editarNutriente.jsp");
    dispatcher.forward(request, response);
  }
}
