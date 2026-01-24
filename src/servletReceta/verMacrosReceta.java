package servletReceta;

import java.io.IOException;

import java.util.LinkedList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import entities.Receta;
import logic.ctrlReceta;
import entities.NutrienteReceta;

@WebServlet("/verMacrosReceta")
public class verMacrosReceta extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public verMacrosReceta() {
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
    int idReceta = Integer.parseInt(request.getParameter("idReceta"));
    ctrlReceta ctrl = new ctrlReceta();
    Receta receta = ctrl.getById(idReceta);
    LinkedList<NutrienteReceta> nutrientesConCantidad = ctrl.getNutrientesConCantidad(idReceta);
    request.setAttribute("ListaNutrientes", nutrientesConCantidad);
    request.setAttribute("Receta", receta);
    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/macronutrientes.jsp");
    dispatcher.forward(request, response);
  }
}
