package servletIngrediente;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Ingrediente;
import logic.ctrlIngrediente;

/**
 * Servlet implementation class editarIngrediente
 */
@WebServlet("/editarIngrediente")
public class editarIngrediente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public editarIngrediente() {
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
		int idIngrediente = Integer.parseInt(request.getParameter("idIngrediente"));
		ctrlIngrediente ctrl = new ctrlIngrediente();
		Ingrediente ingrediente = ctrl.getById(idIngrediente);
		request.setAttribute("ingrediente", ingrediente);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/editarIngrediente.jsp");
		dispatcher.forward(request, response);
	}
}
