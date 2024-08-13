package servletEjercicio;

import java.io.IOException;
import java.util.LinkedList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import data.DaoEjercicio;
import entities.Ejercicio;
import logic.ctrlEjercicios;

/**
 * Servlet implementation class eliminarEjercicio
 */
@WebServlet("/eliminarEjercicio")
public class eliminarEjercicio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public eliminarEjercicio() {
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
		    int id = Integer.parseInt(request.getParameter("id"));
		    DaoEjercicio de = new DaoEjercicio();
		    
		    boolean success = de.eliminarEjercicio(id);
		    if (success) {
		    	ctrlEjercicios ctrl = new ctrlEjercicios();
	        	LinkedList<Ejercicio> ejercicios = ctrl.getAll();
	        	request.setAttribute("listaEjercicios", ejercicios);
	        	request.getRequestDispatcher("WEB-INF/ejercicioManagement.jsp").forward(request, response);
		    } else {
		        response.getWriter().append("Error al eliminar el ejercicio."); // Mensaje de error
		    }
		
	}

}