package servletMedidaUsuario;

import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import entities.Medida;
import logic.ctrlMedida;

/**
 * Servlet implementation class guardarReceta
 */
@WebServlet("/actualizarMedida")
public class actualizarMedida extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public actualizarMedida() {
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
    // TODO Auto-generated method stub
    Double peso = Double.parseDouble(request.getParameter("peso"));
    Double altura = Double.parseDouble(request.getParameter("altura"));
    LocalDate fecha = LocalDate.parse(request.getParameter("fecha"));
    int id = Integer.parseInt(request.getParameter("id_usuario"));
    Medida nuevaMedida = new Medida();
    nuevaMedida.setId_usuario(id);
    nuevaMedida.setPeso(peso);
    nuevaMedida.setAltura(altura);
    nuevaMedida.setFecha(fecha);
    ctrlMedida ctrl = new ctrlMedida();
    ctrl.modificarMedida(nuevaMedida);
    LinkedList<Medida> medidas = ctrl.getByUsuario(id);
    request.setAttribute("listaMedidas", medidas);
    request.getRequestDispatcher("WEB-INF/medidasManagement.jsp").forward(request, response);
  }

}
