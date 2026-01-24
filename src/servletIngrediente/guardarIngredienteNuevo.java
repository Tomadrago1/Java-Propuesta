package servletIngrediente;

import java.io.IOException;
import java.util.LinkedList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import entities.Ingrediente;
import logic.ctrlIngrediente;

/**
 * Servlet implementation class guardarIngredienteNuevo
 */
@WebServlet("/guardarIngredienteNuevo")
public class guardarIngredienteNuevo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public guardarIngredienteNuevo() {
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
		String nombre = request.getParameter("nombre");
		String descripcion = request.getParameter("descripcion");
		Ingrediente i = new Ingrediente();
		i.setNombre(nombre);
		i.setDesc(descripcion);
		ctrlIngrediente ctrlI = new ctrlIngrediente();
		boolean success = ctrlI.add(i);
		if (success) {
			LinkedList<Ingrediente> ingredientes = ctrlI.getAll();
			request.setAttribute("ingredientes", ingredientes);
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Ingrediente/ingredienteManagement.jsp");
			dispatcher.forward(request, response);
		} else {
			response.getWriter().append("Error");
		}
	}
}
