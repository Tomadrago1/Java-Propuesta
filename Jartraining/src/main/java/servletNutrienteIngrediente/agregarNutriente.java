package servletNutrienteIngrediente;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Nutriente;
import logic.ctrlNutriente;

/**
 * Servlet implementation class agregarNutriente
 */
@WebServlet("/agregarNutriente")
public class agregarNutriente extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public agregarNutriente() {
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
    int idIngrediente = Integer.parseInt(request.getParameter("idIngrediente"));
    ctrlNutriente ctrlI = new ctrlNutriente();
    LinkedList<Nutriente> nutrientes = ctrlI.getAll();
    request.setAttribute("ListaNutrientes", nutrientes);
    request.setAttribute("ingrediente", idIngrediente);
    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/nutrienteIngredienteManagement.jsp");
    dispatcher.forward(request, response);
  }
}
