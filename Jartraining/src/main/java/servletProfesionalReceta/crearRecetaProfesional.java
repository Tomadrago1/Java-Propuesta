package servletProfesionalReceta;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Profesional;
import logic.ctrlUsuario;

/**
 * Servlet implementation class crearReceta
 */
@WebServlet("/crearRecetaProfesional")
public class crearRecetaProfesional extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public crearRecetaProfesional() {
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
		// TODO Auto-generated method stub
		int id_profesional = Integer.parseInt(request.getParameter("id_profesional"));
		request.setAttribute("id_profesional", id_profesional);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Receta/crearRecetaProfesional.jsp");
		dispatcher.forward(request, response);
	}
}
