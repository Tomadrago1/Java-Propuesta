package servletRutina;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class agregarEjercicioRutina
 */
@WebServlet("/agregarEjercicioRutina")
public class agregarEjercicioRutina extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public agregarEjercicioRutina() {
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

		int idR = Integer.parseInt(request.getParameter("id_rut"));
		int idE = Integer.parseInt(request.getParameter("id_eje"));

		request.setAttribute("id_rut", idR);
		request.setAttribute("id_eje", idE);
		request.getRequestDispatcher("WEB-INF/editarEjercicioRutina.jsp").forward(request, response);
	}
}
