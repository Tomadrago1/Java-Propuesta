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
 * Servlet implementation class guardarIngredienteReceta
 */
@WebServlet("/guardarIngredienteReceta")
public class guardarIngredienteReceta extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public guardarIngredienteReceta() {
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
		double cantidad = Double.parseDouble(request.getParameter("cantidad"));
		String unidadMedida = request.getParameter("unidadMedida");
		ctrlReceta ctrlR = new ctrlReceta();
		Receta receta = ctrlR.getById(idReceta);
		boolean success = ctrlR.modificarCantidadIngredienteReceta(idReceta, idIngrediente, cantidad, unidadMedida);
		if (success) {
			LinkedList<IngredienteReceta> ingredientesConCantidad = ctrlR.getIngredientesConCantidad(idReceta);
			request.setAttribute("ListaIngredientes", ingredientesConCantidad);
			request.setAttribute("Receta", receta);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/receta.jsp");
			dispatcher.forward(request, response);
		} else {
			success = ctrlR.addIngredienteReceta(idReceta, idIngrediente, cantidad, unidadMedida);
			if (success) {
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

}
