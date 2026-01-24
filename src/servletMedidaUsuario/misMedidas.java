package servletMedidaUsuario;

import java.io.IOException;
import java.util.LinkedList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import entities.Medida;
import logic.ctrlMedida;

/**
 * Servlet implementation class misMedidas
 */
@WebServlet("/misMedidas")
public class misMedidas extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public misMedidas() {
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
		int id = Integer.parseInt(request.getParameter("id_usuario"));
		ctrlMedida ctrl = new ctrlMedida();
		LinkedList<Medida> medidas = ctrl.getByUsuario(id);
		request.setAttribute("listaMedidas", medidas);
		request.getSession().setAttribute("id_usuario", id);
		request.getRequestDispatcher("WEB-INF/medidasManagement.jsp").forward(request, response);
	}
}
