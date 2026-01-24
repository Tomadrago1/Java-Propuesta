package servletNutrienteIngrediente;

import java.io.IOException;
import java.util.LinkedList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import entities.Ingrediente;
import entities.NutrienteIngrediente;
import logic.ctrlIngrediente;

@WebServlet("/eliminarNutrienteIngrediente")
public class eliminarNutrienteIngrediente extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public eliminarNutrienteIngrediente() {
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
    int idIngrediente = Integer.parseInt(request.getParameter("idIngrediente"));
    ctrlIngrediente ctrlI = new ctrlIngrediente();
    boolean success = ctrlI.borrarNutrienteIngrediente(idNutriente, idIngrediente);
    if (success) {
      Ingrediente ingrediente = ctrlI.getById(idIngrediente);
      LinkedList<NutrienteIngrediente> nutrientesConCantidad = ctrlI.getNutrientesConCantidad(idIngrediente);
      request.setAttribute("ListaNutrientes", nutrientesConCantidad);
      request.setAttribute("Ingrediente", ingrediente);
      RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/ingrediente.jsp");
      dispatcher.forward(request, response);
    } else {
      response.getWriter().append("Error");
    }
  }

}
