package servletRecetaUsuario;

import java.io.IOException;

import java.util.LinkedList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import logic.ctrlIngrediente;

import entities.Ingrediente;
import entities.NutrienteIngrediente;

@WebServlet("/verMasIngredienteRecomendado")
public class verMasIngredienteRecomendado extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public verMasIngredienteRecomendado() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // TODO Auto-generated method stub
    response.getWriter().append("Served at: ").append(request.getContextPath());
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    int idIngrediente = Integer.parseInt(request.getParameter("id"));
    ctrlIngrediente ctrl = new ctrlIngrediente();
    Ingrediente ingrediente = ctrl.getById(idIngrediente);
    LinkedList<NutrienteIngrediente> nutrientesConCantidad = ctrl.getNutrientesConCantidad(idIngrediente);
    request.setAttribute("ListaNutrientes", nutrientesConCantidad);
    request.setAttribute("Ingrediente", ingrediente);
    RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/ingredienteRecomendado.jsp");
    dispatcher.forward(request, response);
  }
}
