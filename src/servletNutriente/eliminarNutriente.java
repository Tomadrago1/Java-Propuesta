package servletNutriente;

import java.io.IOException;
import java.util.LinkedList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import entities.Nutriente;
import logic.ctrlNutriente;

/**
 * Servlet implementation class eliminarNutriente
 */
@WebServlet("/eliminarNutriente")
public class eliminarNutriente extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public eliminarNutriente() {
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
    int id = Integer.parseInt(request.getParameter("idNutriente"));
    ctrlNutriente ctrl = new ctrlNutriente();
    boolean success = ctrl.eliminarNutriente(id); // Llama al método para eliminar el usuario
    if (success) {
      LinkedList<Nutriente> nutrientes = ctrl.getAll();
      request.setAttribute("nutrientes", nutrientes);
      request.getRequestDispatcher("/WEB-INF/Nutriente/nutrienteManagement.jsp").forward(request, response);
    } else {
      response.getWriter().append("Error al eliminar el Nutriente."); // Mensaje de error
    }
  }
}
