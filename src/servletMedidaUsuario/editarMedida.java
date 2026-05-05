package servletMedidaUsuario;

import java.io.IOException;
import java.time.LocalDate;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import logic.ctrlMedida;
import entities.Medida;

/**
 * Servlet implementation class crearUsuario
 */
@WebServlet("/editarMedida")
public class editarMedida extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public editarMedida() {
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
    Medida medida = ctrl.getByUsuarioFecha(id, fecha);
    request.setAttribute("medida", medida);
    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/editarMedida.jsp");
    dispatcher.forward(request, response);
  }

}
