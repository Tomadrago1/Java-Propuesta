<<<<<<< HEAD
package servletEjercicio;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

        // Crear una instancia del Ejercicio
        Ejercicio nuevoEjercicio = new Ejercicio();
        nuevoEjercicio.setNombre(nombre);
        nuevoEjercicio.setDescripcion(descripcion);

        // Instanciar el DAO y guardar el ejercicio
        DaoEjercicio de = new DaoEjercicio();
        de.addEjercicio(nuevoEjercicio);

        // Redirigir o enviar un mensaje de éxito
        ctrlEjercicios ctrl = new ctrlEjercicios();
        LinkedList<Ejercicio> ejercicios = ctrl.getAll();

        request.setAttribute("listaEjercicios", ejercicios);
        // request.setAttribute("ejercicio", nombreEjercicio);
        request.getRequestDispatcher("WEB-INF/Ejercicio/ejercicioManagement.jsp").forward(request, response);
    }
=======
package servletEjercicio;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

        // Crear una instancia del Ejercicio
        Ejercicio nuevoEjercicio = new Ejercicio();
        nuevoEjercicio.setNombre(nombre);
        nuevoEjercicio.setDescripcion(descripcion);

        // Instanciar el DAO y guardar el ejercicio
        DaoEjercicio de = new DaoEjercicio();
        de.addEjercicio(nuevoEjercicio);

        // Redirigir o enviar un mensaje de éxito
        ctrlEjercicios ctrl = new ctrlEjercicios();
        LinkedList<Ejercicio> ejercicios = ctrl.getAll();

        request.setAttribute("listaEjercicios", ejercicios);
        // request.setAttribute("ejercicio", nombreEjercicio);
        request.getRequestDispatcher("WEB-INF/Ejercicio/ejercicioManagement.jsp").forward(request, response);
    }
>>>>>>> 2a7a2cddb7c46cdba58517d715cf07559706d21c
}