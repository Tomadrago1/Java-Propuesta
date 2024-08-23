<<<<<<< HEAD
package servletReceta;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Receta;
import logic.ctrlReceta;

/**
 * Servlet implementation class eliminarReceta
 */
@WebServlet("/eliminarReceta")
public class eliminarReceta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public eliminarReceta() {
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
		    ctrlReceta ctrl = new ctrlReceta();
		    
		    boolean success = ctrl.eliminarReceta(id); // Llama al método para eliminar el usuario
		    if (success) {
	        	LinkedList<Receta> recetas = ctrl.getAll();
	        	request.setAttribute("listaRecetas", recetas);
	        	request.getRequestDispatcher("WEB-INF/Receta/recetaManagement.jsp").forward(request, response);
		    } else {
		        response.getWriter().append("Error al eliminar el usuario."); // Mensaje de error
		    }
		
	}
}
=======
package servletReceta;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Receta;
import logic.ctrlReceta;

/**
 * Servlet implementation class eliminarReceta
 */
@WebServlet("/eliminarReceta")
public class eliminarReceta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public eliminarReceta() {
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
		    ctrlReceta ctrl = new ctrlReceta();
		    
		    boolean success = ctrl.eliminarReceta(id); // Llama al método para eliminar el usuario
		    if (success) {
	        	LinkedList<Receta> recetas = ctrl.getAll();
	        	request.setAttribute("listaRecetas", recetas);
	        	request.getRequestDispatcher("WEB-INF/Receta/recetaManagement.jsp").forward(request, response);
		    } else {
		        response.getWriter().append("Error al eliminar el usuario."); // Mensaje de error
		    }
		
	}
}
>>>>>>> 2a7a2cddb7c46cdba58517d715cf07559706d21c
