package servletNutriente;

import java.io.IOException;
import java.util.LinkedList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import entities.Nutriente;
import logic.ctrlNutriente;

@WebServlet("/guardarNutrienteNuevo")
public class guardarNutrienteNuevo extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public guardarNutrienteNuevo() {
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
    String nombre = request.getParameter("nombre");
    String descripcion = request.getParameter("descripcion");
    Nutriente n = new Nutriente();
    n.setNombre(nombre);
    n.setDescripcion(descripcion);
    ctrlNutriente ctrlN = new ctrlNutriente();
    boolean success = ctrlN.add(n);
    if (success) {
      LinkedList<Nutriente> nutrientes = ctrlN.getAll();
      request.setAttribute("nutrientes", nutrientes);
      RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Nutriente/nutrienteManagement.jsp");
      dispatcher.forward(request, response);
    } else {
      response.getWriter().append("Error");
    }
  }
}
