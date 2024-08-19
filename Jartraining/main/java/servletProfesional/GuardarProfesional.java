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
 * Servlet implementation class GuardarProfesional
 */
@WebServlet("/GuardarProfesional")
public class GuardarProfesional extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GuardarProfesional() {
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
	       // Recoger los parámetros del formulario
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String profesion = request.getParameter("profesion");
        /*String nombreUsuario = request.getParameter("nombreUsuario");*/
        /*String password = request.getParameter("password");*/

        // Crear una instancia del usuario
        Profesional nuevoProfesional = new Profesional();
        nuevoProfesional.setNombre(nombre);
        nuevoProfesional.setApellido(apellido);
        /*nuevoProfesional.setNombreUsuario(nombreUsuario);*/
        nuevoProfesional.setProfesion(profesion);
        /*nuevoProfesional.setPassword(password);*/
        nuevoProfesional.setEstado(true);// Asegúrate de que la clase Usuario tenga este método

        ctrlProfesional ctrl = new ctrlProfesional();
        ctrl.add(nuevoProfesional);
    	LinkedList<Profesional> profesionales = ctrl.getAll();
		request.setAttribute("listaProfesionales", profesionales);
    	request.getRequestDispatcher("WEB-INF/profesionalManagement.jsp").forward(request, response);
    }
}


