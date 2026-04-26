package servletProfesion;

import java.io.IOException;
import java.util.LinkedList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import entities.Profesion;
import logic.ctrlProfesion;

@WebServlet({"/verProfesiones", "/buscarProfesion"})
public class verProfesiones extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();
        ctrlProfesion ctrl = new ctrlProfesion();
        LinkedList<Profesion> profesiones;

        if ("/buscarProfesion".equals(path)) {
            String query = request.getParameter("query");
            if (query != null && !query.trim().isEmpty()) {
                profesiones = ctrl.searchByNombre(query.trim());
            } else {
                profesiones = ctrl.getAll();
            }
        } else {
            profesiones = ctrl.getAll();
        }

        request.setAttribute("listaProfesiones", profesiones);
        request.getRequestDispatcher("WEB-INF/profesionManagement.jsp").forward(request, response);
    }
}
