package servletRutina;

import java.io.IOException;

import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.EjercicioRutina;
import logic.ctrlRutina;

@WebServlet("/quitarEjercicioRutina")
public class quitarEjercicioRutina extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id_rut = Integer.parseInt(request.getParameter("id_rut"));
        int id_eje = Integer.parseInt(request.getParameter("id_eje"));

        ctrlRutina ctrl = new ctrlRutina();

        Boolean success = ctrl.quitarEjercicioRutina(id_rut, id_eje);

        if (success) {
            LinkedList<EjercicioRutina> ejercicios_rutina = ctrl.getEjerciciosByRutina(id_rut);
            request.setAttribute("ejercicios_rutina", ejercicios_rutina);
            request.getRequestDispatcher("WEB-INF/rutinaEjercicioManagement.jsp").forward(request, response);
        } else {
            response.getWriter().append("Error al quitar el usuario.");
        }
    }
}
