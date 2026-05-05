package servletRutina;

import java.io.IOException;

import java.util.LinkedList;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import entities.Rutina;
import logic.ctrlRutina;

/**
 * Servlet implementation class eliminarRutina
 */
@WebServlet("/eliminarRutina")
public class eliminarRutina extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public eliminarRutina() {
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
		int id = Integer.parseInt(request.getParameter("id"));
		ctrlRutina ctrl = new ctrlRutina();

		boolean success = ctrl.eliminarRutina(id);

		if (success) {
			LinkedList<Rutina> rutinas = ctrl.getAll();
			request.setAttribute("listaRutinas", rutinas);
			request.getRequestDispatcher("WEB-INF/Rutina/rutinaManagement.jsp").forward(request, response);
		} else {
			response.getWriter().append("Error al eliminar el rutina."); // Mensaje de error
		}

	}

}