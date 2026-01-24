package servletRutina;

import java.io.IOException;

import java.util.LinkedList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import entities.EjercicioRutina;
import logic.ctrlRutina;

/**
 * Servlet implementation class verEjerciciosRutinas
 */
@WebServlet("/verEjerciciosRutina")
public class verEjerciciosRutina extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public verEjerciciosRutina() {
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
		int id = Integer.parseInt(request.getParameter("id"));
		ctrlRutina ctrl = new ctrlRutina();
		LinkedList<EjercicioRutina> ejercicios = ctrl.getEjerciciosByRutina(id);

		request.setAttribute("ejercicios_rutina", ejercicios);
		request.getRequestDispatcher("WEB-INF/rutinaEjercicioManagement.jsp").forward(request, response);
	}

}
