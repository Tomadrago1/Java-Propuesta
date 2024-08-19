package servletEjercicio;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.*;
import logic.ctrlEjercicios;

/**
 * Servlet implementation class listadoEjercicios
 */

@WebServlet("/listadoEjercicios")
public class listadoEjercicios extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public listadoEjercicios() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ctrlEjercicios ctrl = new ctrlEjercicios();
		LinkedList<Ejercicio> ejercicios = ctrl.getAll();
        request.setAttribute("listaEjercicios", ejercicios);
        request.getRequestDispatcher("WEB-INF/ejercicioManagement.jsp").forward(request, response);
	}

}
