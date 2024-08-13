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
 * Servlet implementation class actualizarProfesional
 */
@WebServlet("/actualizarProfesional")
public class actualizarProfesional extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public actualizarProfesional() {
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
    	String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String profesion = request.getParameter("profesion");
        /*String nombreUsuario = request.getParameter("nombreUsuario");*/

        ctrlProfesional ctrl = new ctrlProfesional();
        Boolean success= ctrl.modificar(id,nombre, apellido, profesion/*nombreUsuario*/);

        if (success) {
        	LinkedList<Profesional> profesionales = ctrl.getAll();
    		request.setAttribute("listaProfesionales", profesionales);
    		request.getRequestDispatcher("WEB-INF/profesionalManagement.jsp").forward(request, response);
        } else {
            response.getWriter().append("Error al actualizar el usuario.");
        }
    }

}




