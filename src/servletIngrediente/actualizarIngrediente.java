package servletIngrediente;

import java.io.IOException;
import java.util.LinkedList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import entities.Ingrediente;
import logic.ctrlIngrediente;

/**
 * Servlet implementation class actualizarIngrediente
 */
@WebServlet("/actualizarIngrediente")
public class actualizarIngrediente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public actualizarIngrediente() {
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
		String nombre = request.getParameter("nombre");
		String descripcion = request.getParameter("descripcion");
		ctrlIngrediente ctrl = new ctrlIngrediente();
		Boolean success = ctrl.modificar(id, nombre, descripcion);
		if (success) {
			LinkedList<Ingrediente> ingredientes = ctrl.getAll();
			request.setAttribute("ingredientes", ingredientes);
			request.getRequestDispatcher("WEB-INF/Ingrediente/ingredienteManagement.jsp").forward(request, response);
		} else {
			response.getWriter().append("Error al actualizar el usuario.");
		}
	}
}
