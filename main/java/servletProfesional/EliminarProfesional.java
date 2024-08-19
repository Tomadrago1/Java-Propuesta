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
 * Servlet implementation class EliminarProfesional
 */
@WebServlet("/EliminarProfesional")
public class EliminarProfesional extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarProfesional() {
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
		    int id = Integer.parseInt(request.getParameter("id"));
		    ctrlProfesional ctrl = new ctrlProfesional();
		    
		    boolean success = ctrl.eliminarProfesional(id); // Llama al método para eliminar el usuario
		    if (success) {
	        	LinkedList<Profesional> profesionales = ctrl.getAll();
	        	request.setAttribute("listaProfesionales", profesionales);
	        	request.getRequestDispatcher("WEB-INF/profesionalManagement.jsp").forward(request, response);
		    } else {
		        response.getWriter().append("Error al eliminar el usuario."); // Mensaje de error
		    }
		
	}
}


