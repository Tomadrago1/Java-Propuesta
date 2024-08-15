package servletIngrediente;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class crearIngrediente
 */
@WebServlet("/crearIngrediente")
public class crearIngrediente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public crearIngrediente() {
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
		String idRecetaStr = request.getParameter("idReceta");
		if (idRecetaStr != null) {
		    try {
		        int idReceta = Integer.parseInt(idRecetaStr);
		        request.setAttribute("idReceta", idReceta);
		        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/crearIngrediente.jsp");
		        dispatcher.forward(request, response);
		    } catch (NumberFormatException e) {
		        // Manejo del caso donde idReceta no es un número válido
		        // Aquí puedes redirigir a una página de error o mostrar un mensaje adecuado
		        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de receta inválido.");
		    }
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/crearIngrediente.jsp");
	        dispatcher.forward(request, response);
		}
	}
}
