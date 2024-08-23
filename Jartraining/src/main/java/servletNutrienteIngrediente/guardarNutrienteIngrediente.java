package servletNutrienteIngrediente;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Ingrediente;
import logic.ctrlIngrediente;

/**
 * Servlet implementation class guardarIngredienteReceta
 */
@WebServlet("/guardarNutrienteIngrediente")
public class guardarNutrienteIngrediente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public guardarNutrienteIngrediente() {
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
		int idNutriente = Integer.parseInt(request.getParameter("idNutriente"));
		int idIngrediente = Integer.parseInt(request.getParameter("idIngrediente"));
		double cantidad = Double.parseDouble(request.getParameter("cantidad"));
		ctrlIngrediente ctrlI = new ctrlIngrediente();
		Ingrediente ingrediente = ctrlI.getById(idIngrediente);
		boolean success = ctrlI.modificarCantidadNutrienteIngrediente(idNutriente, idIngrediente, cantidad);
		if (success) {
			LinkedList<Map<String, Object>> nutrientesConCantidad = ctrlI.getNutrientesConCantidad(idIngrediente);
			request.setAttribute("ListaNutrientes", nutrientesConCantidad);
			request.setAttribute("Ingrediente", ingrediente);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/ingrediente.jsp");
			dispatcher.forward(request, response);
		} else {
			success = ctrlI.addNutrienteIngrediente(idNutriente, idIngrediente, cantidad);
			if (success) {
				LinkedList<Map<String, Object>> nutrientesConCantidad = ctrlI.getNutrientesConCantidad(idIngrediente);
				request.setAttribute("ListaNutrientes", nutrientesConCantidad);
				request.setAttribute("Ingrediente", ingrediente);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/ingrediente.jsp");
				dispatcher.forward(request, response);
			} else {
				response.getWriter().append("Error");
			}
		}

	}

}