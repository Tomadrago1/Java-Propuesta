package servletUsuario;

import java.io.IOException;
import java.util.LinkedList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import entities.Usuario;
import logic.ctrlUsuario;

/**
 * Servlet implementation class EliminarUsuario
 */
@WebServlet("/EliminarUsuario")
public class EliminarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EliminarUsuario() {
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
		ctrlUsuario ctrl = new ctrlUsuario();
		int id = Integer.parseInt(request.getParameter("id"));
		boolean success = ctrl.eliminarUsuario(id); // Llama al método para eliminar el usuario
		if (success) {
			LinkedList<Usuario> usuarios = ctrl.getAll();
			request.setAttribute("listaUsuarios", usuarios);
			request.getRequestDispatcher("WEB-INF/Usuario/UserManagement.jsp").forward(request, response);
		} else {
			response.getWriter().append("Error al eliminar el usuario."); // Mensaje de error
		}

	}

}