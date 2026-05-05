package servletReceta;

import java.io.IOException;
import java.util.LinkedList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import entities.Receta;
import logic.ctrlReceta;

/**
 * Servlet implementation class eliminarReceta
 */
@WebServlet("/eliminarReceta")
public class eliminarReceta extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public eliminarReceta() {
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
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		ctrlReceta ctrl = new ctrlReceta();
		boolean success = ctrl.eliminarReceta(id);
		if (success) {
			LinkedList<Receta> recetas = ctrl.getAll();
			request.setAttribute("listaRecetas", recetas);
			request.getRequestDispatcher("WEB-INF/Receta/recetaManagement.jsp").forward(request, response);
		} else {
			response.getWriter().append("Error al eliminar la receta."); // Mensaje de error
		}

	}
}
