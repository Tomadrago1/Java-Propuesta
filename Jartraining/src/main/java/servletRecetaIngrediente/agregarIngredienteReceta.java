<<<<<<< HEAD
package servletRecetaIngrediente;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Ingrediente;
import logic.ctrlIngrediente;

/**
 * Servlet implementation class agregarIngredienteReceta
 */
@WebServlet("/agregarIngredienteReceta")
public class agregarIngredienteReceta extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public agregarIngredienteReceta() {
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
		ctrlIngrediente ctrlI = new ctrlIngrediente();
		Ingrediente ing = ctrlI.getById(idIngrediente);
		request.setAttribute("idReceta", idReceta);
		request.setAttribute("Ingrediente", ing);
		request.getRequestDispatcher("WEB-INF/cantIngrediente.jsp").forward(request, response);
	}
}
=======
package servletRecetaIngrediente;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Ingrediente;
import logic.ctrlIngrediente;

/**
 * Servlet implementation class agregarIngredienteReceta
 */
@WebServlet("/agregarIngredienteReceta")
public class agregarIngredienteReceta extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public agregarIngredienteReceta() {
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
		ctrlIngrediente ctrlI = new ctrlIngrediente();
		Ingrediente ing = ctrlI.getById(idIngrediente);
		request.setAttribute("idReceta", idReceta);
		request.setAttribute("Ingrediente", ing);
		request.getRequestDispatcher("WEB-INF/cantIngrediente.jsp").forward(request, response);
	}
}
>>>>>>> 2a7a2cddb7c46cdba58517d715cf07559706d21c
