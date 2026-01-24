package servletRutinaUsuario;

import java.io.IOException;
import java.util.LinkedList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import entities.Rutina;
import logic.ctrlRutina;

/**
 * Servlet implementation class crearRutinaUsuario
 */
@WebServlet("/crearRutinaUsuario")
public class crearRutinaUsuario extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public crearRutinaUsuario() {
    super();
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int id_usu = Integer.parseInt(request.getParameter("id_usu"));
    
    ctrlRutina ctrlR = new ctrlRutina();
    LinkedList<Rutina> rutinas = ctrlR.getAll();
    
    request.setAttribute("rutinas", rutinas);
    request.setAttribute("id_usu", id_usu);
    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/rutinaUsuarioManagement.jsp");
    dispatcher.forward(request, response);
    }
  }