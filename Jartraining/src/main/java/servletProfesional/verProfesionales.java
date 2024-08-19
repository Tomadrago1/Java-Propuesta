package servletProfesional;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Profesional;
import logic.ctrlProfesional;

/**
 * Servlet implementation class verProfesionales
 */
@WebServlet("/verProfesionales")
public class verProfesionales extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public verProfesionales() {
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
		// TODO Auto-generated method stub
		ctrlProfesional ctrl = new ctrlProfesional();
		LinkedList<Profesional> profesionales = ctrl.getAll();
        request.setAttribute("listaProfesionales", profesionales);
        request.getRequestDispatcher("WEB-INF/profesionalManagement.jsp").forward(request, response);
	}

}
