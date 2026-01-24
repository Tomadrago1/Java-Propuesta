package servletProfesionalReceta;

import java.io.IOException;
import java.util.LinkedList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import entities.Receta;
import logic.ctrlReceta;
import entities.Profesional;
import logic.ctrlUsuario;

/**
 * Servlet implementation class guardarReceta
 */
@WebServlet("/guardarRecetaProfesional")
public class guardarRecetaProfesional extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public guardarRecetaProfesional() {
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
        // TODO Auto-generated method stub
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("desc");
        String nivelDificultad = request.getParameter("nivelDificultad");
        int id_profesional = Integer.parseInt(request.getParameter("id_profesional"));
        ctrlUsuario ctrl = new ctrlUsuario();
        Profesional profesional = ctrl.getProfesionalById(id_profesional);
        Receta nuevaReceta = new Receta();
        nuevaReceta.setNombre(nombre);
        nuevaReceta.setDesc(apellido);
        nuevaReceta.setNivelDificultad(nivelDificultad);
        nuevaReceta.setProfesional(profesional);
        ctrlReceta ctrlR = new ctrlReceta();
        ctrlR.add(nuevaReceta);
		LinkedList<Receta> recetas = ctrlR.getByProf(id_profesional);
        request.setAttribute("listaRecetas", recetas);
        request.getRequestDispatcher("WEB-INF/Receta/recetaProfesionalManagement.jsp").forward(request, response);
    }

}
