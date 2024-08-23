<<<<<<< HEAD
package servletNutrienteIngrediente;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import entities.Nutriente;
import logic.ctrlNutriente;

/**
 * Servlet implementation class agregarIngredienteReceta
 */
@WebServlet("/agregarNutrienteIngrediente")
public class agregarNutrienteIngrediente extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public agregarNutrienteIngrediente() {
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
    int idNutriente = Integer.parseInt(request.getParameter("idNutriente"));
    ctrlNutriente ctrlN = new ctrlNutriente();
    Nutriente nut = ctrlN.getById(idNutriente);
    request.setAttribute("Nutriente", nut);
    request.setAttribute("idIngrediente", idIngrediente);
    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/cantNutriente.jsp");
    dispatcher.forward(request, response);
  }
}
=======
package servletNutrienteIngrediente;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import entities.Nutriente;
import logic.ctrlNutriente;

/**
 * Servlet implementation class agregarIngredienteReceta
 */
@WebServlet("/agregarNutrienteIngrediente")
public class agregarNutrienteIngrediente extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public agregarNutrienteIngrediente() {
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
    int idNutriente = Integer.parseInt(request.getParameter("idNutriente"));
    ctrlNutriente ctrlN = new ctrlNutriente();
    Nutriente nut = ctrlN.getById(idNutriente);
    request.setAttribute("Nutriente", nut);
    request.setAttribute("idIngrediente", idIngrediente);
    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/cantNutriente.jsp");
    dispatcher.forward(request, response);
  }
}
>>>>>>> 2a7a2cddb7c46cdba58517d715cf07559706d21c
