package servletEjercicio;

import java.io.IOException;
import java.util.LinkedList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import data.DaoEjercicio;
import entities.Ejercicio;
import logic.ctrlEjercicios;

/**
 * Servlet implementation class guardarEjercicio
 */
@WebServlet("/guardarEjercicio")
public class guardarEjercicio extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public guardarEjercicio() {
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

        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        String zona = request.getParameter("zona");
        String tipoEjercicio = request.getParameter("tipoEjercicio");

        // Crear una instancia del Ejercicio
        Ejercicio nuevoEjercicio = new Ejercicio();
        nuevoEjercicio.setNombre(nombre);
        nuevoEjercicio.setDescripcion(descripcion);
        nuevoEjercicio.setZona(zona);
        nuevoEjercicio.setTipoEjercicio(tipoEjercicio);
        // Redirigir o enviar un mensaje de éxito
        ctrlEjercicios ctrl = new ctrlEjercicios();
        LinkedList<Ejercicio> ejercicios = ctrl.getAll();

        request.setAttribute("listaEjercicios", ejercicios);
        // request.setAttribute("ejercicio", nombreEjercicio);
        request.getRequestDispatcher("WEB-INF/Ejercicio/ejercicioManagement.jsp").forward(request, response);
    }
}