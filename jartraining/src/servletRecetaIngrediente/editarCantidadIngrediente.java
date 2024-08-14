package servletRecetaIngrediente;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Ingrediente;
import logic.ctrlIngrediente;

/**
 * Servlet implementation class editarCantidadIngrediente
 */
@WebServlet("/editarCantidadIngrediente")
public class editarCantidadIngrediente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public editarCantidadIngrediente() {
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
		int idIngrediente = Integer.parseInt(request.getParameter("idIngrediente"));
		double cantidad = Double.parseDouble(request.getParameter("cantidad"));
		ctrlIngrediente ctrlI = new ctrlIngrediente();
		Ingrediente ing = ctrlI.getById(idIngrediente);
		request.setAttribute("idReceta", idReceta);
		request.setAttribute("Ingrediente", ing);
		request.setAttribute("cantidad", cantidad);
    	request.getRequestDispatcher("WEB-INF/cantIngrediente.jsp").forward(request, response);
	}
}
