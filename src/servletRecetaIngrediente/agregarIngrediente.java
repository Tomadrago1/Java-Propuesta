package servletRecetaIngrediente;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Ingrediente;
import logic.ctrlIngrediente;

/**
 * Servlet implementation class agregarIngrediente
 */
@WebServlet("/agregarIngrediente")
public class agregarIngrediente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public agregarIngrediente() {
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
		int idReceta = Integer.parseInt(request.getParameter("idReceta"));
		ctrlIngrediente ctrlI = new ctrlIngrediente();
		LinkedList<Ingrediente> ingredientes = ctrlI.getAll();
		request.setAttribute("ListaIngredientes", ingredientes);
		request.setAttribute("receta", idReceta);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/ingredienteRecetaManagement.jsp");
        dispatcher.forward(request, response);
	}

}
