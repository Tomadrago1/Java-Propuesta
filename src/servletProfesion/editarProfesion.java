package servletProfesion;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import entities.Profesion;
import logic.ctrlProfesion;

@WebServlet("/editarProfesion")
public class editarProfesion extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idParam = request.getParameter("id");

        if (idParam != null && !idParam.isEmpty()) {
            try {
                int id = Integer.parseInt(idParam);
                ctrlProfesion ctrl = new ctrlProfesion();
                Profesion p = ctrl.getProfesionById(id);
                
                if (p != null) {
                    request.setAttribute("Profesion", p);
                    request.getRequestDispatcher("WEB-INF/editarProfesion.jsp").forward(request, response);
                    return;
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        response.sendRedirect("verProfesiones");
    }
}
