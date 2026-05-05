package servletMedidaUsuario;

import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import entities.Medida;
import logic.ctrlMedida;

/**
 * Servlet implementation class crearUsuario
 */
@WebServlet("/EliminarMedida")
public class EliminarMedida extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public EliminarMedida() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/crearMedida.jsp");
    dispatcher.forward(request, response);
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // TODO Auto-generated method stub
    int id = Integer.parseInt(request.getParameter("id_usuario"));
    LocalDate fecha = LocalDate.parse(request.getParameter("fecha"));
    ctrlMedida ctrl = new ctrlMedida();
    ctrl.delete(id, fecha);
    LinkedList<Medida> medidas = ctrl.getByUsuario(id);
    request.setAttribute("listaMedidas", medidas);
    request.getRequestDispatcher("WEB-INF/medidasManagement.jsp").forward(request, response);
  }

}
