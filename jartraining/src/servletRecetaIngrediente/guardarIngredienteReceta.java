package servletRecetaIngrediente;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Receta;
import logic.ctrlReceta;

/**
 * Servlet implementation class guardarIngredienteReceta
 */
@WebServlet("/guardarIngredienteReceta")
public class guardarIngredienteReceta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public guardarIngredienteReceta() {
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
		System.out.println(idReceta);
		System.out.println(idIngrediente);
		System.out.println(cantidad);
		ctrlReceta ctrlR = new ctrlReceta();
		boolean success = ctrlR.modificarCantidadIngredienteReceta(idReceta,idIngrediente,cantidad);
		if(success){
			Receta receta = ctrlR.getById(idReceta);
	        LinkedList<Map<String, Object>> ingredientesConCantidad = ctrlR.getIngredientesConCantidad(idReceta);
	        request.setAttribute("ListaIngredientes", ingredientesConCantidad);
	        request.setAttribute("Receta", receta);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/receta.jsp");
	        dispatcher.forward(request, response);
	    	request.getRequestDispatcher("WEB-INF/cantIngrediente.jsp").forward(request, response);
		}
		else {
			success = ctrlR.addIngredienteReceta(idReceta,idIngrediente,cantidad);
			if(success) {
				Receta receta = ctrlR.getById(idReceta);
		        LinkedList<Map<String, Object>> ingredientesConCantidad = ctrlR.getIngredientesConCantidad(idReceta);
		        request.setAttribute("ListaIngredientes", ingredientesConCantidad);
		        request.setAttribute("Receta", receta);
		        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/receta.jsp");
		        dispatcher.forward(request, response);
		    	request.getRequestDispatcher("WEB-INF/cantIngrediente.jsp").forward(request, response);
			}else {
				response.getWriter().append("Error");
			}
		}
		
	}

}
