package servletProfesion;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import logic.ctrlProfesion;

@WebServlet("/guardarProfesion")
public class guardarProfesion extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idParam = request.getParameter("id");
        String nombre = request.getParameter("nombre");

        if (idParam != null && !idParam.isEmpty() && nombre != null && !nombre.trim().isEmpty()) {
            try {
                int id = Integer.parseInt(idParam);
                ctrlProfesion ctrl = new ctrlProfesion();
                ctrl.modificarProfesion(id, nombre.trim());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        response.sendRedirect("verProfesiones");
    }
}
