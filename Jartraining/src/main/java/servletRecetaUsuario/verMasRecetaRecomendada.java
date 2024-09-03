package servletRecetaUsuario;

import java.io.IOException;

import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*import entities.Ingrediente;*/
import entities.Receta;
import entities.IngredienteReceta;
import logic.ctrlReceta;

@WebServlet("/verMasRecetaRecomendada")
public class verMasRecetaRecomendada extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public verMasRecetaRecomendada() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idReceta = Integer.parseInt(request.getParameter("id"));
        ctrlReceta ctrl = new ctrlReceta();
        Receta receta = ctrl.getById(idReceta);
        LinkedList<IngredienteReceta> ingredientesConCantidad = ctrl.getIngredientesConCantidad(idReceta);
        request.setAttribute("ListaIngredientes", ingredientesConCantidad);
        request.setAttribute("Receta", receta);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/recetaRecomendada.jsp");
        dispatcher.forward(request, response);
    }
}
