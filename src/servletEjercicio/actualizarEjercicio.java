package servletEjercicio;

import java.io.IOException;

import java.util.LinkedList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import entities.Ejercicio;
import logic.ctrlEjercicios;

// Aca se modifica el ejercicio y vuelve a la lista de ejercicios

@WebServlet("/actualizarEjercicio")
public class actualizarEjercicio extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        String desc = request.getParameter("descripcion");
        String zona = request.getParameter("zona");
        String tipoEjercicio = request.getParameter("tipoEjercicio");

        ctrlEjercicios ctrl = new ctrlEjercicios();
        Boolean success = ctrl.modificarEjercicio(id, nombre, desc, zona, tipoEjercicio);

        if (success) {
            LinkedList<Ejercicio> ejercicios = ctrl.getAll();
            request.setAttribute("listaEjercicios", ejercicios);
            request.getRequestDispatcher("WEB-INF/Ejercicio/ejercicioManagement.jsp").forward(request, response);
        } else {
            response.getWriter().append("Error al actualizar el ejercicio.");
        }
    }
}