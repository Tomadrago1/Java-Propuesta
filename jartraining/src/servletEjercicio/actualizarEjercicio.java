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

// Aca se modifica el ejercicio y vuelve a la lista de ejercicios

@WebServlet("/actualizarEjercicio")
public class actualizarEjercicio extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int id = Integer.parseInt(request.getParameter("id"));
    	String nombre = request.getParameter("nombre");
        String desc = request.getParameter("descripcion");

        DaoEjercicio de = new DaoEjercicio();
        Boolean success= de.modificarEjercicio(id, nombre, desc);

        if (success) {
        	ctrlEjercicios ctrl = new ctrlEjercicios();
    		LinkedList<Ejercicio> ejercicios = ctrl.getAll();
            request.setAttribute("listaEjercicios", ejercicios);
            request.getRequestDispatcher("WEB-INF/ejercicioManagement.jsp").forward(request, response);
        } else {
            response.getWriter().append("Error al actualizar el ejercicio.");
        }
    }
}