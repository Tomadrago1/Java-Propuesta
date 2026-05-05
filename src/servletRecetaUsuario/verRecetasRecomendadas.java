package servletRecetaUsuario;

import java.io.IOException;
import java.util.LinkedList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import entities.Receta;
import logic.ctrlReceta;

/**
 * Servlet implementation class verUsuarios
 */
@WebServlet("/verRecetasRecomendadas")
public class verRecetasRecomendadas extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public verRecetasRecomendadas() {
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
    int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
		ctrlReceta ctrl = new ctrlReceta();
		LinkedList<Receta> recetas = ctrl.getRecetasRecomendadas(idUsuario);
		request.setAttribute("listaRecetas", recetas);
		request.getRequestDispatcher("WEB-INF/recetaManagementUsuario.jsp").forward(request, response);
	}
}
