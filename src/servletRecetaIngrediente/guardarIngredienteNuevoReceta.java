package servletRecetaIngrediente;

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
 * Servlet implementation class guardarIngredienteNuevoReceta
 */
@WebServlet("/guardarIngredienteNuevoReceta")
public class guardarIngredienteNuevoReceta extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public guardarIngredienteNuevoReceta() {
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
			int idReceta = Integer.parseInt(request.getParameter("idReceta"));
			LinkedList<Ingrediente> ingredientes = ctrlI.getAll();
			request.setAttribute("ListaIngredientes", ingredientes);
			request.setAttribute("receta", idReceta);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/ingredienteRecetaManagement.jsp");
			dispatcher.forward(request, response);
		} else {
			response.getWriter().append("Error");
		}
	}

}
