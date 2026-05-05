package servletNutriente;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*
Servlet implementation class crearNutriente
*/
@WebServlet("/crearNutriente")
public class crearNutriente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public crearNutriente() {
		super();
	}// TODO Auto-generated constructor stub}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/crearNutriente.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idIngredienteStr = request.getParameter("idIngrediente");
		Integer idIngrediente = null;

		try {
			if (idIngredienteStr != null && !idIngredienteStr.isEmpty()) {
				idIngrediente = Integer.parseInt(idIngredienteStr);
				request.setAttribute("idIngrediente", idIngrediente);
			}
		} catch (NumberFormatException e) {
			// Manejo del caso donde idIngrediente no es un número válido
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de ingrediente inválido.");
			return;
		}

		// Redirigir siempre a crearNutriente.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Nutriente/crearNutriente.jsp");
		dispatcher.forward(request, response);
	}
}
