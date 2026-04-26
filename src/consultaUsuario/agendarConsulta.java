package consultaUsuario;

import java.io.IOException;
import java.util.LinkedList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import entities.Profesional;
import logic.ctrlUsuario;
import entities.Profesion;
import logic.ctrlProfesion;

/**
 * Servlet implementation class editarNutriente
 */
@WebServlet("/agendarConsulta")
public class agendarConsulta extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public agendarConsulta() {
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
    ctrlProfesion ctrlP = new ctrlProfesion();
    LinkedList<Profesion> listaProfesiones = ctrlP.getAll();
    request.setAttribute("listaProfesiones", listaProfesiones);

    String idProfesionStr = request.getParameter("id_profesion");
    LinkedList<Profesional> profesionales = new LinkedList<>();
    ctrlUsuario ctrl = new ctrlUsuario();

    if (idProfesionStr != null && !idProfesionStr.trim().isEmpty()) {
        int id_profesion = Integer.parseInt(idProfesionStr);
        profesionales = ctrl.getProfesionalesByProfesion(id_profesion);
        request.setAttribute("idProfesionSeleccionada", id_profesion);
    } else {
        // You could fetch all, or leave empty to force user to choose. The user says "en lugar de mostrar un listado de todos los profesionales, debe mostrarse un listado de profesiones para que se seleccione una." So leaving empty is correct.
    }
    
    request.setAttribute("profesionales", profesionales);
    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/agendarConsulta.jsp");
    dispatcher.forward(request, response);
  }
}
