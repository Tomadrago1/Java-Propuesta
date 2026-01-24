package servletRecetaIngrediente;

import java.io.IOException;
import java.util.LinkedList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import entities.IngredienteReceta;
import entities.Receta;
import logic.ctrlReceta;

/**
 * Servlet implementation class eliminarIngredienteReceta
 */
@WebServlet("/eliminarIngredienteReceta")
public class eliminarIngredienteReceta extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public eliminarIngredienteReceta() {
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
		int idReceta = Integer.parseInt(request.getParameter("idReceta"));
		int idIngrediente = Integer.parseInt(request.getParameter("idIngrediente"));
		ctrlReceta ctrlR = new ctrlReceta();
		boolean success = ctrlR.borrarIngredienteReceta(idReceta, idIngrediente);
		if (success) {
			Receta receta = ctrlR.getById(idReceta);
			LinkedList<IngredienteReceta> ingredientesConCantidad = ctrlR.getIngredientesConCantidad(idReceta);
			request.setAttribute("ListaIngredientes", ingredientesConCantidad);
			request.setAttribute("Receta", receta);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/receta.jsp");
			dispatcher.forward(request, response);
		} else {
			response.getWriter().append("Error");
		}
	}

}
