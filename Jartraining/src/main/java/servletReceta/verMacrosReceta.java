package servletReceta;

import java.io.IOException;

import java.util.LinkedList;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Receta;
import logic.ctrlReceta;

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
    LinkedList<Map<String, Object>> nutrientesConCantidad = ctrl.getNutrientesConCantidad(idReceta);
    request.setAttribute("ListaNutrientes", nutrientesConCantidad);
    request.setAttribute("Receta", receta);
    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/macronutrientes.jsp");
    dispatcher.forward(request, response);
  }
}
