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
 * Servlet implementation class guardarReceta
 */
@WebServlet("/guardarReceta")
public class guardarReceta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public guardarReceta() {
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
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("desc");
        String profesion = request.getParameter("nivelDificultad");
        Receta nuevoReceta = new Receta();
        nuevoReceta.setNombre(nombre);
        nuevoReceta.setDesc(apellido);
        nuevoReceta.setNivelDificultad(profesion);
        nuevoReceta.setProfesional(null);

        ctrlReceta ctrl = new ctrlReceta();
        ctrl.add(nuevoReceta);
    	LinkedList<Receta> recetas = ctrl.getAll();
		request.setAttribute("listaRecetas", recetas);
    	request.getRequestDispatcher("WEB-INF/recetaManagement.jsp").forward(request, response);
	}

}
