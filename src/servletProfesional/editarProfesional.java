package servletProfesional;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import entities.Profesional;
import logic.ctrlUsuario;

/**
 * Servlet implementation class editarProfesional
 */
@WebServlet("/editarProfesional")
public class editarProfesional extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public editarProfesional() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int idProfesional = Integer.parseInt(request.getParameter("id"));
		ctrlUsuario ctrl = new ctrlUsuario();
		Profesional profesional = ctrl.getProfesionalById(idProfesional);
		request.setAttribute("profesional", profesional);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Profesional/editarProfesional.jsp");
		dispatcher.forward(request, response);
	}
}
