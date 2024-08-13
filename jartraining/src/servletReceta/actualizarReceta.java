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
 * Servlet implementation class actualizarReceta
 */
@WebServlet("/actualizarReceta")
public class actualizarReceta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public actualizarReceta() {
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
        String descripcion = request.getParameter("desc");
        String nivelDificultad = request.getParameter("nivelDificultad");

        ctrlReceta ctrl = new ctrlReceta();
        Boolean success= ctrl.modificar(id,nombre, descripcion, nivelDificultad);

        if (success) {
        	LinkedList<Receta> recetas = ctrl.getAll();
    		request.setAttribute("listaRecetas", recetas);
    		request.getRequestDispatcher("WEB-INF/recetaManagement.jsp").forward(request, response);
        } else {
            response.getWriter().append("Error al actualizar el usuario.");
        }
    }

}
