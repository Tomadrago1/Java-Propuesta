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
 * Servlet implementation class eliminarIngrediente
 */
@WebServlet("/eliminarIngrediente")
public class eliminarIngrediente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public eliminarIngrediente() {
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
		int id = Integer.parseInt(request.getParameter("idIngrediente"));
		ctrlIngrediente ctrl = new ctrlIngrediente();
		boolean success = ctrl.eliminarIngrediente(id); // Llama al método para eliminar el usuario
		if (success) {
			LinkedList<Ingrediente> ingredientes = ctrl.getAll();
			request.setAttribute("ingredientes", ingredientes);
			request.getRequestDispatcher("WEB-INF/Ingrediente/ingredienteManagement.jsp").forward(request, response);
		} else {
			response.getWriter().append("Error al eliminar el Ingrediente."); // Mensaje de error
		}
	}
}
