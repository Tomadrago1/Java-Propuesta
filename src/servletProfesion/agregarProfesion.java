package servletProfesion;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import entities.Profesion;
import logic.ctrlProfesion;

@WebServlet("/agregarProfesion")
public class agregarProfesion extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombre = request.getParameter("nombre");

        if (nombre != null && !nombre.trim().isEmpty()) {
            ctrlProfesion ctrl = new ctrlProfesion();
            Profesion p = new Profesion();
            p.setNombre(nombre.trim());
            ctrl.addProfesion(p);
        }

        response.sendRedirect("verProfesiones");
    }
}
